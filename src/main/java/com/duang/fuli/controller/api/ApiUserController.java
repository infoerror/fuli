package com.duang.fuli.controller.api;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.form.BasicInfoForm;
import com.duang.fuli.domain.form.ModifyAvatarForm;
import com.duang.fuli.service.UserService;
import com.duang.fuli.utils.FileUtils;
import com.duang.fuli.utils.ImageUtils;

@Controller
@Scope("prototype")
@RequestMapping(value = "/api/user")
public class ApiUserController extends JSONController{
	
	@Resource(name="userService")
	private UserService userService;
	
	
	@RequestMapping(value = "/modifyBasicInfo")
	public void modifyBasicInfo(@RequestBody BasicInfoForm basicInfo) throws IOException{
		User currentUser=getCurrentUser();
		basicInfo.setUser(currentUser);
		writeJson(userService.modifyBasicInfo(basicInfo));
	}
	
	
	@RequestMapping(value = "/modifyAvatar")
	public void modifyAvatar(@RequestBody ModifyAvatarForm modifyAvatarForm) throws IOException{
		//
		  //WEB应用程序根路径  
        String realAppPath = getRealPath("/");
        realAppPath=realAppPath.substring(0, realAppPath.length()-1);
        String origin=realAppPath+modifyAvatarForm.getPath().replace('/', File.separatorChar);
        
        Calendar calendar=Calendar.getInstance();
        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        int day=calendar.get(Calendar.DAY_OF_MONTH);
        User currentUser=getCurrentUser();
        String dirPath="/store/images/avatar/"+year+"/"+month+"/"+day+"/";
        FileUtils.mkdirs(realAppPath+dirPath.replace('/', File.separatorChar));
        
        String avatar=dirPath+currentUser.getId()+".jpg";
        String avatarPath=realAppPath+avatar.replace('/', File.separatorChar);
        ImageUtils.abscut(origin, avatarPath, modifyAvatarForm.getX(),modifyAvatarForm.getY(),modifyAvatarForm.getWidth(), modifyAvatarForm.getHeight());

        modifyAvatarForm.setUser(currentUser);
        modifyAvatarForm.setAvatar(avatar);
        writeJson(userService.modifyAvatar(modifyAvatarForm));
	}
	
}