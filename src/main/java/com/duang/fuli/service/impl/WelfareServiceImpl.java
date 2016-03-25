package com.duang.fuli.service.impl;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.duang.fuli.dao.UnauditedWelfareDao;
import com.duang.fuli.dao.UserInfoDao;
import com.duang.fuli.dao.WelfareDao;
import com.duang.fuli.dao.WelfareTagDao;
import com.duang.fuli.domain.Comment;
import com.duang.fuli.domain.User;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.form.CommentForm;
import com.duang.fuli.domain.page.SubcommentPage;
import com.duang.fuli.domain.page.WelfareCommentPage;
import com.duang.fuli.service.WelfareService;
import com.duang.fuli.service.result.CommentResult;
import com.duang.fuli.service.result.RootCommentPage;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:53:22
 */
@Service("welfareService")
public class WelfareServiceImpl implements WelfareService {

	@Resource(name = "welfareDao")
	private WelfareDao welfareDao;

	@Resource(name = "welfareTagDao")
	private WelfareTagDao welfareTagDao;
	
	@Resource(name = "unauditedWelfareDao")
	private UnauditedWelfareDao unauditedWelfareDao;
	
	@Resource(name="userInfoDao")
    private UserInfoDao userInfoDao;
	
	@Override
	public Collection<Welfare> getUptodateFulis(int top) {
		return null;
	}

	@Override
	public Collection<Welfare> getFulisOfPage(int pageIndex) {
		return welfareDao.getFulisOfPage(pageIndex);
	}

	@Override
	public Welfare getWelfare(int id) {
		return welfareDao.getWelfare(id);
	}

	/**
	 * 用aop验证了
	 */
	@Override
	public CommentResult addComment(CommentForm commentForm) {

		int id = commentForm.getReplyId();
		int rootId=0;
		if (id != 0) {
			Comment comment = welfareDao.getCommentById(id);
			if (comment == null) {
				return CommentResult.COMMENT_INEXISTENT;
			}
			rootId = comment.getRootId();
			if(rootId==0){
				rootId =comment.getId();
			}
		}
		
		User currentUser= commentForm.getUser();
        Comment comment = new Comment();
        comment.setCommentTime(new Date());
        comment.setReplyId(commentForm.getReplyId());
        comment.setContent(commentForm.getContent());
        comment.setWelfareId(commentForm.getWelfareId());
        comment.setRootId(rootId);
        comment.setUserId(currentUser.getId());
		comment.setNickname(currentUser.getNickname());
		comment.setAvatarUrl(currentUser.getAvatarUrl());
		welfareDao.addComment(comment);
		return CommentResult.succ(comment);
	}

	@Override
	public RootCommentPage getCommentsForPage(WelfareCommentPage commentPage) {
		RootCommentPage rootCommentPage = new RootCommentPage();
		rootCommentPage.setCommentCount(welfareDao.getRootCommentCount(commentPage.getWelfareId()));
		List<Comment> comments = welfareDao.getRootComments(commentPage);
		
		for (Comment comment : comments) {
			SubcommentPage subcommentPage = new SubcommentPage();
			subcommentPage.setRootId(comment.getId());
			subcommentPage.setPageSize(5);
			subcommentPage.setStartIndex(0);
			comment.setCommentCount(welfareDao.getSubcommentCount(comment.getId()));
			comment.setComments(welfareDao.getCommentsForPage(subcommentPage));
		}
		rootCommentPage.setData(comments);
		return rootCommentPage;
	}

	public List<Comment> getTreeComments(int commentId) {
		List<Comment> comments = welfareDao.getCommentList(commentId);
		for (Comment comment : comments) {
			List<Comment> children = getTreeComments(comment.getId());
			comment.setComments(children);
		}
		return comments;
	}

	@Override
	public List<Comment> getSubcommentsForPage(SubcommentPage commentPage) {
		return welfareDao.getCommentsForPage(commentPage);
	}

	@Override
	public List<Comment> getRemainingSubcomments(int rootId) {
		SubcommentPage subcommentPage =new SubcommentPage();
		subcommentPage.setRootId(rootId);
		subcommentPage.setStartIndex(5);
		subcommentPage.setPageSize(9999);
		return welfareDao.getCommentsForPage(subcommentPage);
	}

	@Override
	public List<Welfare> getLatestWelfares(int sum) {
		return null;
	}

	@Override
	public List<WelfareTag> getHotWelfareTags(int top) {
		if(top>30){
			top=30;
		}
		return welfareTagDao.getHotWelfareTags(top);
	}

	@Override
	public List<Welfare> getWelfaresByTagName(String tagName) {
		WelfareTag welfareTag=welfareTagDao.getWelfareTagByName(tagName);
		if(welfareTag==null){
			return null;
		}
		return welfareDao.getWelfaresByTag(welfareTag);
	}


}
