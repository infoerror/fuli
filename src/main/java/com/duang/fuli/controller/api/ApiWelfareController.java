package com.duang.fuli.controller.api;

import java.io.IOException;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.duang.fuli.controller.base.JSONController;
import com.duang.fuli.domain.form.CommentForm;
import com.duang.fuli.domain.page.SubcommentPage;
import com.duang.fuli.domain.page.WelfareCommentPage;
import com.duang.fuli.service.WelfareService;
/**
 * 
 * @author zgq
 * @date 2016年3月23日 上午11:54:06
 */
@Controller
@Scope("prototype")
@RequestMapping("/api/welfare")
public class ApiWelfareController extends JSONController{
	
   
	@Resource(name="welfareService")
	private WelfareService welfareService;
	
	@RequestMapping(value="/hotTags")
	public void getHotWelfareTag() throws IOException{
		writeJson(welfareService.getHotWelfareTags(10));
	}	
	
	
	@RequestMapping(value="/latest/sum")
	public void getLatestWelfares(@PathVariable("welfareId")int sum) throws IOException{
		writeJson(welfareService.getLatestWelfares(sum));
	}	
	
	
	@RequestMapping(value="/{welfareId}/comment",method=RequestMethod.POST)
	public void comment(@PathVariable("welfareId")int welfareId,@RequestBody CommentForm commentForm) throws IOException{
		commentForm.setUser(getCurrentUser());
		commentForm.setWelfareId(welfareId);
		writeJson(welfareService.addComment(commentForm));
	}
	@RequestMapping(value="/{welfareId}/comments")
	public void getCommentForPage(@PathVariable("welfareId")int welfareId,@RequestBody WelfareCommentPage welfareCommentPage) throws IOException{
		welfareCommentPage.setWelfareId(welfareId);
		writeJson(welfareService.getCommentsForPage(welfareCommentPage));
	}
	
	@RequestMapping(value="/comment/{rootId}/subcomments")
	public void getSubcommentForPage(@PathVariable("rootId")int rootId,@RequestBody SubcommentPage subcommentPage) throws IOException{
		subcommentPage.setRootId(rootId);
		writeJson(welfareService.getSubcommentsForPage(subcommentPage));
	}

	@RequestMapping(value="/comment/{rootId}/remainingSubcomments")
	public void getRemainingSubcomment(@PathVariable("rootId")int rootId) throws IOException{
		writeJson(welfareService.getRemainingSubcomments(rootId));
	}
	
}
