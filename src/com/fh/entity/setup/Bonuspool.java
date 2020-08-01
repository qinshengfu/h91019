package com.fh.entity.setup;

public class Bonuspool {

	private String BONUSPOOL_ID;            //ID';
	private double ONE;            //一级推荐人数';
	private double ONE_Y;            //一级一代奖励%';
	private double TOW;            //二级代推荐人数';
	private double TOW_Y;            //转出手续费
	private double TOW_R;            //二级二代挖币%';
	private double THREE;            //三级推荐人数';
	private double THREE_Y;            //动态收益每次提取;
	private int THREE_R;            //几个月后可以全部提取
	private double THREE_S;            //三级三代挖币%';
	private double FOUR;            //四级人数';
	private double FOUR_Q;            //第几代起';
	private double FOUR_Z;            //第几代止';
	private double FOUR_J;            //锁仓奖金%';
	private double FOUR_SXF;            //锁仓奖金手续费';
	private String CODE1;            //微信二维码';
	private String KFWX1;            //客服微信';
	public String getBONUSPOOL_ID() {
		return BONUSPOOL_ID;
	}
	public void setBONUSPOOL_ID(String bONUSPOOL_ID) {
		BONUSPOOL_ID = bONUSPOOL_ID;
	}
	public double getONE() {
		return ONE;
	}
	public void setONE(double oNE) {
		ONE = oNE;
	}
	public double getONE_Y() {
		return ONE_Y;
	}
	public void setONE_Y(double oNE_Y) {
		ONE_Y = oNE_Y;
	}
	public double getTOW() {
		return TOW;
	}
	public void setTOW(double tOW) {
		TOW = tOW;
	}
	public double getTOW_Y() {
		return TOW_Y;
	}
	public void setTOW_Y(double tOW_Y) {
		TOW_Y = tOW_Y;
	}
	public double getTOW_R() {
		return TOW_R;
	}
	public void setTOW_R(double tOW_R) {
		TOW_R = tOW_R;
	}
	public double getTHREE() {
		return THREE;
	}
	public void setTHREE(double tHREE) {
		THREE = tHREE;
	}
	public double getTHREE_Y() {
		return THREE_Y;
	}
	public void setTHREE_Y(double tHREE_Y) {
		THREE_Y = tHREE_Y;
	}
	public int getTHREE_R() {
		return THREE_R;
	}
	public void setTHREE_R(int tHREE_R) {
		THREE_R = tHREE_R;
	}
	public double getTHREE_S() {
		return THREE_S;
	}
	public void setTHREE_S(double tHREE_S) {
		THREE_S = tHREE_S;
	}
	public double getFOUR() {
		return FOUR;
	}
	public void setFOUR(double fOUR) {
		FOUR = fOUR;
	}
	public double getFOUR_Q() {
		return FOUR_Q;
	}
	public void setFOUR_Q(double fOUR_Q) {
		FOUR_Q = fOUR_Q;
	}
	public double getFOUR_Z() {
		return FOUR_Z;
	}
	public void setFOUR_Z(double fOUR_Z) {
		FOUR_Z = fOUR_Z;
	}
	public double getFOUR_J() {
		return FOUR_J;
	}
	public void setFOUR_J(double fOUR_J) {
		FOUR_J = fOUR_J;
	}
	public double getFOUR_SXF() {
		return FOUR_SXF;
	}
	public void setFOUR_SXF(double fOUR_SXF) {
		FOUR_SXF = fOUR_SXF;
	}
	public String getCODE1() {
		return CODE1;
	}
	public void setCODE1(String cODE1) {
		CODE1 = cODE1;
	}
	public String getKFWX1() {
		return KFWX1;
	}
	public void setKFWX1(String kFWX1) {
		KFWX1 = kFWX1;
	}
	@Override
	public String toString() {
		return "Bonuspool [BONUSPOOL_ID=" + BONUSPOOL_ID + ", ONE=" + ONE + ", ONE_Y=" + ONE_Y + ", TOW=" + TOW
				+ ", TOW_Y=" + TOW_Y + ", TOW_R=" + TOW_R + ", THREE=" + THREE + ", THREE_Y=" + THREE_Y + ", THREE_R="
				+ THREE_R + ", THREE_S=" + THREE_S + ", FOUR=" + FOUR + ", FOUR_Q=" + FOUR_Q + ", FOUR_Z=" + FOUR_Z
				+ ", FOUR_J=" + FOUR_J + ", FOUR_SXF=" + FOUR_SXF + ", CODE1=" + CODE1 + ", KFWX1=" + KFWX1 + "]";
	}
	
	
	
	 
	 
	 
	 
	
	
	
	
	 
	 
}
