package com.duang.fuli.dao;

import java.util.Collection;
import java.util.List;

import com.duang.fuli.domain.Comment;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.mtm.Welfare_Tag;
import com.duang.fuli.domain.page.SubcommentPage;
import com.duang.fuli.domain.page.WelfareCommentPage;

public interface WelfareDao {

	public Collection<Welfare> getFulisOfPage(int pageIndex);

	public void addWelfare(Welfare welfare);

	public void addTagsToWelfare(Welfare_Tag welfare_Tag);

	public Welfare getWelfare(int id);

	public void addComment(Comment comment);

	public List<Comment> getCommentList(int id);

	public List<Comment> getRootComments(WelfareCommentPage commentPage);

	public List<Comment> getCommentsForPage(SubcommentPage subcommentPage);

	public Comment getCommentById(int id);

	public Integer getSubcommentCount(int rootId);

	public Integer getRootCommentCount(int welfareId);

	public List<Welfare> getWelfaresByTag(WelfareTag welfareTag);


	
}
