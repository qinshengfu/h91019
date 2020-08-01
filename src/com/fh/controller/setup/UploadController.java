package com.fh.controller.setup;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fh.controller.base.BaseController;
import com.fh.util.FileUtil;

/** 
 * 说明：后台上传图片
 * 创建人：
 * 创建时间：2019-10-25
 */
@Controller
@RequestMapping(value="/upload")
public class UploadController extends BaseController {
	
	  /**
     * 上传图片
     */
    @ResponseBody
    @RequestMapping(value="/upload")
    public Object uploadImage( HttpServletRequest request) {
    	
        CommonsMultipartResolver cmr = new CommonsMultipartResolver(
                request.getServletContext());
        String path = null;
        if (cmr.isMultipart(request)) {
            MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) (request);
            Iterator<String> files = mRequest.getFileNames();

            while (files.hasNext()) {
                MultipartFile mFile = mRequest.getFile(files.next());
                mFile.getName();
                if (mFile != null) {
                    path = FileUtil.saveFile(mFile,request,"adminImg");
                    path = path.substring(path.indexOf("adminImg"),path.length());
                }
            }
        }
        //System.out.println("图片路径==>>"+path);
        return path;
    }
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
}
