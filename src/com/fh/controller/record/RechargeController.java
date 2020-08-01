package com.fh.controller.record;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.service.record.BonusManager;
import com.fh.service.record.RechargeManager;
import com.fh.service.setup.MemberManager;
import com.fh.util.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 说明：充值管理
 * 创建人：
 * 创建时间：2019-10-31
 */
@Controller
@RequestMapping(value = "/recharge")
public class RechargeController extends BaseController {

    String menuUrl = "recharge/list.do"; //菜单地址(权限用)
    @Resource(name = "rechargeService")
    private RechargeManager rechargeService;

    @Resource(name = "memberService")
    private MemberManager memberService;

    @Resource(name = "bonusService")
    private BonusManager bonusService;

    /**
     * 确认充值
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/confirmBy")
    public String confirmBy() throws Exception {
        PageData pd = this.getPageData();
        pd = rechargeService.findById(pd);
        pd.put("STATE", "1");
        rechargeService.edit(pd);
        //充值
        double money = ((BigDecimal) pd.get("MONEY")).intValue();

        PageData member = new PageData();
        member.put("MEMBER", pd.getString("PHONE"));
        member = memberService.findByMember(member);

        // 每次充值先把【是否出局、消费总额】重置为0 避免未出局时复投不能延续三倍收益
        member.put("OUTMONEY", 0);
        member.put("CONSUMPTION", 0);

        double WEALTH = ((BigDecimal) member.get("WEALTH")).doubleValue();
        double YEJI = ((BigDecimal) member.get("YEJI")).doubleValue();
        double YEJINEWS = ((BigDecimal) member.get("YEJINEWS")).doubleValue();
        member.put("WEALTH", WEALTH + money);
        member.put("YEJI", YEJI + money);
        member.put("YEJINEWS", YEJINEWS + money);
        double CF = ((BigDecimal) member.get("CF")).doubleValue();
        member.put("CF", CF + money);

        //记录
        PageData bonusPd1 = new PageData();
        bonusPd1.put("PHONE", member.getString("MEMBER"));
        bonusPd1.put("PRODUCE", member.getString("MEMBER"));
        bonusPd1.put("QUANTITY", money);
        bonusPd1.put("TYPE", "充值");
        bonusPd1.put("STATE", "1");
        bonusPd1.put("TIME", Tools.date2Str(new Date()));
        bonusPd1.put("BONUS_ID", this.get32UUID());
        bonusService.save(bonusPd1);
        // 从缓存获取app应用参数
        WebApplicationContext webApplicationContext = ContextLoader.getCurrentWebApplicationContext();
        ServletContext context = webApplicationContext.getServletContext();
        PageData parameter = (PageData) context.getAttribute("parameter");
        // 初始
        double INITIAL = Double.parseDouble(parameter.getString("INITIAL"));
        // 佣金：积分   参数
        double EXCHANGE = Double.parseDouble(parameter.getString("EXCHANGE"));
        // 直推奖参数
        double DIRECT = Double.parseDouble(parameter.getString("DIRECT"));
        // 领导奖参数
        double LEADER = Double.parseDouble(parameter.getString("LEADER"));
        // 小队人数
        int SQUAD = Integer.parseInt(parameter.getString("SQUAD"));
        // 收益到达倍数（判断是否得分享积分）
        int REVENUE = Integer.parseInt(parameter.getString("REVENUE"));
        // 一次性数量
        int COUNT = Integer.parseInt(parameter.getString("COUNT"));
        // 出局倍数
        int MULTIPLE = Integer.parseInt(parameter.getString("MULTIPLE"));
        // 一次性出局倍数
        int ONEMULTIPLE = Integer.parseInt(parameter.getString("ONEMULTIPLE"));

        // 发放分享积分
        paySharePoints(member, SQUAD, ONEMULTIPLE, MULTIPLE, INITIAL, parameter, REVENUE, money, EXCHANGE);
        // 发放直推奖或者领导奖
        PageData referrer = payRecommendationBonus(member, DIRECT, ONEMULTIPLE, MULTIPLE, INITIAL, LEADER, money);

        //升级
        double UPGRADE = ((BigDecimal) member.get("UPGRADE")).doubleValue();
        int GIFT = ((BigDecimal) member.get("GIFT")).intValue();
        double TXMONEY = ((BigDecimal) member.get("TXMONEY")).doubleValue();
        if (!"十星".equals(member.getString("MEM_LEVEL"))) {
            int LEVEL_NUM = ((BigDecimal) member.get("LEVEL_NUM")).intValue();
            if (money / INITIAL >= COUNT) { //判断是否达成一次性消费
                if ("普通".equals(member.getString("MEM_LEVEL"))) {
                    referrer.put("DIRECTPUSH", ((BigDecimal) referrer.get("DIRECTPUSH")).intValue() + 1);
                }
                member.put("MEM_LEVEL", "十星");
                member.put("LEVEL_NUM", 10);
                member.put("ONETIME", "yes");
                int gift = (int) ((money - COUNT * INITIAL) / INITIAL);
                member.put("GIFT", gift + GIFT);
                member.put("UPGRADE", (money + UPGRADE - COUNT * INITIAL) % INITIAL);
                member.put("TXMONEY", INITIAL * 2 * 10 - INITIAL * 2 * LEVEL_NUM);
                member.put("OUTMONEY", "0");
            } else {
                int index = (int) ((UPGRADE + money) / INITIAL + LEVEL_NUM);
                if (index != LEVEL_NUM) {
                    if ("普通".equals(member.getString("MEM_LEVEL"))) {
                        referrer.put("DIRECTPUSH", ((BigDecimal) referrer.get("DIRECTPUSH")).intValue() + 1);
                    }
                    if (index == 1) {
                        member.put("MEM_LEVEL", "一星");
                        member.put("LEVEL_NUM", 1);
                        member.put("TXMONEY", INITIAL * 2);
                        member.put("OUTMONEY", "0");
                    } else if (index == 2) {
                        member.put("MEM_LEVEL", "二星");
                        member.put("LEVEL_NUM", 2);
                        member.put("TXMONEY", INITIAL * 2 * 2 - 2 * INITIAL + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 3) {
                        member.put("MEM_LEVEL", "三星");
                        member.put("LEVEL_NUM", 3);
                        member.put("TXMONEY", INITIAL * 2 * 3 - INITIAL * 2 * 2 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 4) {
                        member.put("MEM_LEVEL", "四星");
                        member.put("LEVEL_NUM", 4);
                        member.put("TXMONEY", INITIAL * 2 * 4 - INITIAL * 2 * 3 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 5) {
                        member.put("MEM_LEVEL", "五星");
                        member.put("LEVEL_NUM", 5);
                        member.put("TXMONEY", INITIAL * 2 * 5 - INITIAL * 2 * 4 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 6) {
                        member.put("MEM_LEVEL", "六星");
                        member.put("LEVEL_NUM", 6);
                        member.put("TXMONEY", INITIAL * 2 * 6 - INITIAL * 2 * 5 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 7) {
                        member.put("MEM_LEVEL", "七星");
                        member.put("LEVEL_NUM", 7);
                        member.put("TXMONEY", INITIAL * 2 * 7 - INITIAL * 2 * 6 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 8) {
                        member.put("MEM_LEVEL", "八星");
                        member.put("LEVEL_NUM", 8);
                        member.put("TXMONEY", INITIAL * 2 * 8 - INITIAL * 2 * 7 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index == 9) {
                        member.put("MEM_LEVEL", "九星");
                        member.put("LEVEL_NUM", 9);
                        member.put("TXMONEY", INITIAL * 2 * 9 - INITIAL * 2 * 8 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    } else if (index >= 10) {
                        member.put("MEM_LEVEL", "十星");
                        member.put("LEVEL_NUM", 10);
                        member.put("GIFT", GIFT + index - 10);
                        member.put("TXMONEY", INITIAL * 2 * 10 - INITIAL * 2 * 9 + TXMONEY);
                        member.put("OUTMONEY", "0");
                    }
                }
            }
            member.put("UPGRADE", (UPGRADE + money) % INITIAL);
        } else {
            if (money / INITIAL >= COUNT) { //判断是否达成一次性消费
                member.put("MEM_LEVEL", "十星");
                member.put("LEVEL_NUM", 10);
                member.put("ONETIME", "yes");
                int gift = (int) ((money - COUNT * INITIAL) / INITIAL);
                member.put("GIFT", gift + GIFT);
                member.put("UPGRADE", (money + UPGRADE - COUNT * INITIAL) % INITIAL);
                member.put("TXMONEY", TXMONEY + money * 2);
                member.put("OUTMONEY", "0");
            } else {
                int gift = (int) ((UPGRADE + money) / INITIAL);
                member.put("GIFT", GIFT + gift);
                member.put("UPGRADE", (UPGRADE + money) % INITIAL);
                if ((UPGRADE + money) / INITIAL > 1) {
                    member.put("TXMONEY", TXMONEY + money * 2);
                    member.put("OUTMONEY", "0");
                }
            }
        }
        memberService.edit(member);
        memberService.edit(referrer);

        return "success";
    }


    /**
     * 功能描述：发放分享积分
     *
     * @param member      用户
     * @param SQUAD       小队人数
     * @param ONEMULTIPLE 一次性出局倍数
     * @param MULTIPLE    出局倍数
     * @param INITIAL     初始
     * @param parameter   app应用参数
     * @param REVENUE     收益到达倍数（判断是否得分享积分）
     * @param money       本次充值金额
     * @param EXCHANGE    佣金：积分参数
     * @author Ajie
     * @date 2020/7/2 0002
     */
    public void paySharePoints(PageData member, int SQUAD, int ONEMULTIPLE, int MULTIPLE, double INITIAL,
                               PageData parameter, int REVENUE, double money, double EXCHANGE) throws Exception {
        String[] array = member.getString("C_PATH").split(",");
        PageData pageData = new PageData();
        pageData.put("array", array);
        if (array.length > 0) {
            List<PageData> allSuperior = memberService.allSuperior(pageData);
            int index = 0;
            for (PageData p : allSuperior) {
                if (index == SQUAD || index == allSuperior.size()) {
                    break;
                }
                int outBei = 0; //出局倍数
                // 直推人数
                int DIRECTPUSH = ((BigDecimal) p.get("DIRECTPUSH")).intValue();
                // 等级数
                int LEVEL_NUM = ((BigDecimal) p.get("LEVEL_NUM")).intValue();
                // 消费总额
                double CONSUMPTION = ((BigDecimal) p.get("CONSUMPTION")).doubleValue();
                // 积分
                double INTEGRAL = ((BigDecimal) p.get("INTEGRAL")).doubleValue();
                // 是否一次性消费
                if ("yes".equals(p.getString("ONETIME"))) {
                    outBei = ONEMULTIPLE + DIRECTPUSH / 2;
                } else {
                    outBei = MULTIPLE + DIRECTPUSH / 2;
                }
                double isOutSum = CONSUMPTION / (INITIAL * LEVEL_NUM);
                // 判断收益是否出局
                if (isOutSum < outBei) {
                    // 出局总额
                    double OUTMONEY = Double.parseDouble(p.getString("OUTMONEY"));
                    //int LEVEL_NUM = ((BigDecimal)p.get("LEVEL_NUM")).intValue();
                    // 等级对应的积分倍数
                    int level = Integer.parseInt(parameter.getString("LEVEL" + LEVEL_NUM));

                    boolean result = OUTMONEY / INITIAL < REVENUE;
                    if (result) {
                        int fenxiang = 0;
                        double l = CONSUMPTION + money * level / EXCHANGE;
                        double r = INITIAL * LEVEL_NUM;
                        if ((l / r) < outBei) {
                            fenxiang = (int) (money * level);
                        } else {
                            fenxiang = (int) ((outBei * INITIAL * LEVEL_NUM - CONSUMPTION) * EXCHANGE);
                        }
                        p.put("INTEGRAL", fenxiang + INTEGRAL);
                        p.put("CONSUMPTION", ((BigDecimal) p.get("CONSUMPTION")).doubleValue() + fenxiang / EXCHANGE);
                        p.put("OUTMONEY", Double.parseDouble(p.getString("OUTMONEY")) + fenxiang / EXCHANGE);
                        double JF = ((BigDecimal) p.get("JF")).doubleValue();
                        p.put("JF", JF + fenxiang);
                        // 创建钱包记录
                        PageData bonusPd = new PageData();
                        bonusPd.put("PHONE", p.getString("MEMBER"));
                        bonusPd.put("PRODUCE", member.getString("MEMBER"));
                        bonusPd.put("QUANTITY", fenxiang);
                        bonusPd.put("TYPE", "分享积分");
                        bonusPd.put("STATE", "1");
                        bonusPd.put("TIME", Tools.date2Str(new Date()));
                        bonusPd.put("BONUS_ID", this.get32UUID());
                        bonusService.save(bonusPd);
                        index++;
                    }
                    memberService.edit(p);
                }
            }
        }
    }

    /**
     * 功能描述：发放推荐奖
     *
     * @param member      用户
     * @param DIRECT      直推奖参数
     * @param ONEMULTIPLE 一次性出局倍数
     * @param MULTIPLE    出局倍数
     * @param INITIAL     初始
     * @param money       本次充值金额
     * @param LEADER      领导奖参数
     * @return referrer 直推上级
     * @author Ajie
     * @date 2020/7/2 0002
     */
    public PageData payRecommendationBonus(PageData member, double DIRECT, int ONEMULTIPLE, int MULTIPLE,
                                           double INITIAL, double LEADER, double money) throws Exception {
        PageData referrer = memberService.findByReferrer(member);
        if (referrer != null) {
            int outBei = 0; //出局倍数
            // 直推人数
            int DIRECTPUSH = ((BigDecimal) referrer.get("DIRECTPUSH")).intValue();
            // 等级数
            int LEVEL_NUM = ((BigDecimal) referrer.get("LEVEL_NUM")).intValue();
            // 消费总额
            double CONSUMPTION = ((BigDecimal) referrer.get("CONSUMPTION")).doubleValue();
            if (!"普通".equals(referrer.getString("MEM_LEVEL"))) {
                if ("yes".equals(referrer.getString("ONETIME"))) {
                    outBei = ONEMULTIPLE + DIRECTPUSH / 2;
                } else {
                    outBei = MULTIPLE + DIRECTPUSH / 2;
                }
                //直推奖
                if (CONSUMPTION / (INITIAL * LEVEL_NUM) < outBei) { //判断收益是否出局
                    double zhitui = 0;
                    if ((CONSUMPTION + money * DIRECT / 100D) / (INITIAL * LEVEL_NUM) < outBei) {
                        zhitui = money * DIRECT / 100D;
                    } else {
                        zhitui = outBei * INITIAL * LEVEL_NUM - CONSUMPTION;
                    }
                    referrer.put("COMMISSION", ((BigDecimal) referrer.get("COMMISSION")).doubleValue() + zhitui);
                    referrer.put("CONSUMPTION", ((BigDecimal) referrer.get("CONSUMPTION")).doubleValue() + zhitui);
                    referrer.put("OUTMONEY", Double.parseDouble(referrer.getString("OUTMONEY")) + zhitui);
                    double YJ = ((BigDecimal) referrer.get("YJ")).doubleValue();
                    referrer.put("YJ", YJ + zhitui);
                    memberService.edit(referrer);
                    // 创建记录
                    PageData bonusPd = new PageData();
                    bonusPd.put("PHONE", referrer.getString("MEMBER"));
                    bonusPd.put("PRODUCE", member.getString("MEMBER"));
                    bonusPd.put("QUANTITY", zhitui);
                    bonusPd.put("TYPE", "直推奖");
                    bonusPd.put("STATE", "1");
                    bonusPd.put("TIME", Tools.date2Str(new Date()));
                    bonusPd.put("BONUS_ID", this.get32UUID());
                    bonusService.save(bonusPd);
                }
            }
            //领导奖
            PageData leader = memberService.findByReferrer(referrer);
            if (leader != null) {
                if (!"普通".equals(leader.getString("MEM_LEVEL"))) {
                    outBei = 0; //出局倍数
                    DIRECTPUSH = ((BigDecimal) leader.get("DIRECTPUSH")).intValue();
                    LEVEL_NUM = ((BigDecimal) leader.get("LEVEL_NUM")).intValue();
                    CONSUMPTION = ((BigDecimal) leader.get("CONSUMPTION")).doubleValue();
                    if ("yes".equals(leader.getString("ONETIME"))) { //获取出局倍数
                        outBei = ONEMULTIPLE + DIRECTPUSH / 2;
                    } else {
                        outBei = MULTIPLE + DIRECTPUSH / 2;
                    }
                    // 判断收益是否出局
                    if (CONSUMPTION / (INITIAL * LEVEL_NUM) < outBei) {
                        double lingdao = 0;
                        if ((CONSUMPTION + money * LEADER / 100D) / (INITIAL * LEVEL_NUM) < outBei) {
                            lingdao = money * LEADER / 100D;
                        } else {
                            lingdao = outBei * INITIAL * LEVEL_NUM - CONSUMPTION;
                        }
                        leader.put("COMMISSION", ((BigDecimal) leader.get("COMMISSION")).doubleValue() + lingdao);
                        leader.put("CONSUMPTION", ((BigDecimal) leader.get("CONSUMPTION")).doubleValue() + lingdao);
                        leader.put("OUTMONEY", Double.parseDouble(leader.getString("OUTMONEY")) + lingdao);
                        double YJ = ((BigDecimal) leader.get("YJ")).doubleValue();
                        leader.put("YJ", YJ + lingdao);
                        memberService.edit(leader);
                        // 创建记录
                        PageData bonusPd = new PageData();
                        bonusPd.put("PHONE", leader.getString("MEMBER"));
                        bonusPd.put("PRODUCE", member.getString("MEMBER"));
                        bonusPd.put("QUANTITY", lingdao);
                        bonusPd.put("TYPE", "领导奖");
                        bonusPd.put("STATE", "1");
                        bonusPd.put("TIME", Tools.date2Str(new Date()));
                        bonusPd.put("BONUS_ID", this.get32UUID());
                        bonusService.save(bonusPd);
                    }
                }
            }
        }
        return referrer;
    }

    /**
     * 拒绝充值
     *
     * @return
     * @throws Exception
     */
    @ResponseBody
    @RequestMapping("/refuse")
    public String refuse() throws Exception {
        PageData pd = new PageData();
        pd = this.getPageData();
        pd = rechargeService.findById(pd);
        pd.put("STATE", "2");
        rechargeService.edit(pd);
        return "success";
    }

    /**
     * 保存
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/save")
    public ModelAndView save() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "新增Recharge");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd.put("RECHARGE_ID", this.get32UUID());    //主键
        rechargeService.save(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 删除
     *
     * @param out
     * @throws Exception
     */
    @RequestMapping(value = "/delete")
    public void delete(PrintWriter out) throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "删除Recharge");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return;
        } //校验权限
        PageData pd = new PageData();
        pd = this.getPageData();
        rechargeService.delete(pd);
        out.write("success");
        out.close();
    }

    /**
     * 修改
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/edit")
    public ModelAndView edit() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "修改Recharge");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
            return null;
        } //校验权限
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        rechargeService.edit(pd);
        mv.addObject("msg", "success");
        mv.setViewName("save_result");
        return mv;
    }

    /**
     * 列表
     *
     * @param page
     * @throws Exception
     */
    @RequestMapping(value = "/list")
    public ModelAndView list(Page page) throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "列表Recharge");
        //if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限(无权查看时页面会有提示,如果不注释掉这句代码就无法进入列表页面,所以根据情况是否加入本句代码)
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        String keywords = pd.getString("keywords");                //关键词检索条件
        if (null != keywords && !"".equals(keywords)) {
            pd.put("keywords", keywords.trim());
        }
        page.setPd(pd);
        List<PageData> varList = rechargeService.list(page);    //列出Recharge列表
        mv.setViewName("record/recharge/recharge_list");
        mv.addObject("varList", varList);
        mv.addObject("pd", pd);
        mv.addObject("QX", Jurisdiction.getHC());    //按钮权限
        return mv;
    }

    /**
     * 去新增页面
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/goAdd")
    public ModelAndView goAdd() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        mv.setViewName("record/recharge/recharge_edit");
        mv.addObject("msg", "save");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 去修改页面
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/goEdit")
    public ModelAndView goEdit() throws Exception {
        ModelAndView mv = this.getModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        pd = rechargeService.findById(pd);    //根据ID读取
        mv.setViewName("record/recharge/recharge_edit");
        mv.addObject("msg", "edit");
        mv.addObject("pd", pd);
        return mv;
    }

    /**
     * 批量删除
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/deleteAll")
    @ResponseBody
    public Object deleteAll() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "批量删除Recharge");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
            return null;
        } //校验权限
        PageData pd = new PageData();
        Map<String, Object> map = new HashMap<String, Object>();
        pd = this.getPageData();
        List<PageData> pdList = new ArrayList<PageData>();
        String DATA_IDS = pd.getString("DATA_IDS");
        if (null != DATA_IDS && !"".equals(DATA_IDS)) {
            String ArrayDATA_IDS[] = DATA_IDS.split(",");
            rechargeService.deleteAll(ArrayDATA_IDS);
            pd.put("msg", "ok");
        } else {
            pd.put("msg", "no");
        }
        pdList.add(pd);
        map.put("list", pdList);
        return AppUtil.returnObject(pd, map);
    }

    /**
     * 导出到excel
     *
     * @param
     * @throws Exception
     */
    @RequestMapping(value = "/excel")
    public ModelAndView exportExcel() throws Exception {
        logBefore(logger, Jurisdiction.getUsername() + "导出Recharge到excel");
        if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
            return null;
        }
        ModelAndView mv = new ModelAndView();
        PageData pd = new PageData();
        pd = this.getPageData();
        Map<String, Object> dataMap = new HashMap<String, Object>();
        List<String> titles = new ArrayList<String>();
        titles.add("充值账号");    //1
        titles.add("充值金额");    //2
        titles.add("充值方式");    //3
        titles.add("凭证");    //4
        titles.add("状态");    //5
        titles.add("时间");    //6
        dataMap.put("titles", titles);
        List<PageData> varOList = rechargeService.listAll(pd);
        List<PageData> varList = new ArrayList<PageData>();
        for (int i = 0; i < varOList.size(); i++) {
            PageData vpd = new PageData();
            vpd.put("var1", varOList.get(i).getString("PHONE"));        //1
            vpd.put("var2", varOList.get(i).get("MONEY").toString());    //2
            vpd.put("var3", varOList.get(i).getString("RSTYLE"));        //3
            vpd.put("var4", varOList.get(i).getString("CERTIFICATE"));        //4
            vpd.put("var5", varOList.get(i).getString("STATE"));        //5
            vpd.put("var6", varOList.get(i).getString("TIME"));        //6
            varList.add(vpd);
        }
        dataMap.put("varList", varList);
        ObjectExcelView erv = new ObjectExcelView();
        mv = new ModelAndView(erv, dataMap);
        return mv;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
    }
}
