package com.duang.fuli.service;

import java.util.Collection;
import java.util.List;

import com.duang.fuli.domain.Comment;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.form.CommentForm;
import com.duang.fuli.domain.page.SubcommentPage;
import com.duang.fuli.domain.page.WelfareCommentPage;
import com.duang.fuli.service.result.CommentResult;
import com.duang.fuli.service.result.RootCommentPage;

/**
 * 方法后缀名为ForPage的，会自动计算
 * 这个接口的几个方法需要重名名和完善
 * @author zgq
 * @date 2016年3月19日 下午1:52:07
 */
public interface WelfareService {

	/**
	 * 
	 * 获取最新的福利 
	 * @param top 最新条数
	 * @return
	 */
	public Collection<Welfare> getUptodateFulis(int top);

	public Collection<Welfare> getFulisOfPage(int pageIndex);
	
	
	/*
	 * 上面两个方法需要进一步改善
	 */

	public Welfare getWelfare(int id);
	
	public List<Welfare> getLatestWelfares(int sum);
	
	/*
	 * comment start
	 */
	
	/*
	 * 这个评论系统相对复杂了一点，与前端交互的代码有待优化
	 */

	public CommentResult addComment(CommentForm commentForm);
	

	/**
	 * 获得root层评论
	 * @param welfareId
	 * @return
	 */
	public RootCommentPage getCommentsForPage(WelfareCommentPage commentPage);
	

	/**
	 * 获得第二层评论
	 * @param commentPage
	 * @return
	 */
	public List<Comment> getSubcommentsForPage(SubcommentPage commentPage);

	/**
	 * 获得剩下的评论
	 * @param rootId
	 * @return
	 */
	public List<Comment> getRemainingSubcomments(int rootId);
	
	/*
	 * commend end
	 */


	public List<WelfareTag> getHotWelfareTags(int top);

	
	public List<Welfare> getWelfaresByTagName(String tagName);

}
