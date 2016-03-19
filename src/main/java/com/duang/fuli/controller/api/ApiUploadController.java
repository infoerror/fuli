package com.duang.fuli.controller.api;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.duang.fuli.config.AppConfigs;
import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.User;
import com.duang.fuli.service.result.UploadResult;
import com.duang.fuli.utils.ImageUtils;
import com.duang.fuli.utils.UploadUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/upload")
public class ApiUploadController extends JSONController{

	private static final Logger LOG=Logger.getLogger(ApiUploadController.class);
	
	/**
	 * 有待完善
	 * @param request
	 * @param response
	 * @throws IllegalStateException
	 * @throws IOException
	 */
	@RequestMapping(value="/avatar")
	public void uploadAvater(HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		MultipartFile file=UploadUtils.getOnlyFile(request);
		if(file==null){
			writeJson(UploadResult.NO_FILE_UPLOAD);
			return;
		}
		
	      
        //检查图片大小  
        BufferedImage src = ImageIO.read(file.getInputStream()); // 读入文件                         
        int imgSrcWidth = src.getWidth(); // 得到源图宽
        int imgSrcHeight = src.getHeight(); // 得到源图长  
                
        int scale = imgSrcWidth*10/imgSrcHeight;
        
        //重新指定大小  
        int imgWidth = imgSrcWidth > AppConfigs.TEMP_AVATAR_WIDTH?  AppConfigs.TEMP_AVATAR_WIDTH : imgSrcWidth;  
          
        int imgHeight =imgWidth/scale*10;  
		
		User currentUser= getCurrentUser();
		//String webapp=request.getServletContext().getContextPath();
		String webappPath=request.getServletContext().getRealPath("/");
		long uploadTime=System.currentTimeMillis();
		//String suffix=FileUtils.getFileSuffix(file.getOriginalFilename());
		// 重命名上传后的文件名
		String fileName = "store\\images\\temp\\"+uploadTime+""+currentUser.getId()+".jpg";
		String webPath= "/store/images/temp/"+uploadTime+""+currentUser.getId()+".jpg";
		
        //按照图片的设置大小生成  
        ImageUtils.scale(src,webappPath+fileName,imgWidth,imgHeight); 
		
		writeJson(UploadResult.uploadSucc(webPath));
	}
}
