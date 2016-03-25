package com.duang.fuli.dao.impl;

import java.util.Collection;
import java.util.List;

import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.duang.fuli.dao.WelfareDao;
import com.duang.fuli.domain.Comment;
import com.duang.fuli.domain.Page;
import com.duang.fuli.domain.Welfare;
import com.duang.fuli.domain.WelfareTag;
import com.duang.fuli.domain.mtm.Welfare_Tag;
import com.duang.fuli.domain.page.SubcommentPage;
import com.duang.fuli.domain.page.WelfareCommentPage;
import com.duang.fuli.utils.PageUtils;

/**
 * 
 * @author zgq
 * @date 2016年3月19日 下午1:51:18
 */
@Repository("welfareDao")
public class WelfareDaoImpl extends SqlSessionDaoSupport implements WelfareDao {

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public Collection getFulisOfPage(int pageIndex) {
		Page page = new Page();
		page.setPageCount(PageUtils.showCount);
		page.setPageIndex(pageIndex*PageUtils.showCount);
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectFulisOfPage",page);
	}

	@Override
	public void addWelfare(Welfare welfare) {
		 this.getSqlSession().insert("com.duang.fuli.domain.Welfare.insertWelfare",welfare);
	}

	@Override
	public void addTagsToWelfare(Welfare_Tag welfare_Tag) {
		 this.getSqlSession().insert("com.duang.fuli.domain.Welfare.buildWelfareAndTagRelation",welfare_Tag);
	}

	@Override
	public Welfare getWelfare(int id) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.Welfare.selectWelfareById",id);
	}

	@Override
	public Comment getCommentById(int id) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.Welfare.selectCommentById",id);
	}

	@Override
	public void addComment(Comment commentForm) {
		 this.getSqlSession().insert("com.duang.fuli.domain.Welfare.insertComment",commentForm);
	}

	@Override
	public List<Comment> getCommentList(int id) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectSubTreeComments",id);
	}

	@Override
	public List<Comment> getRootComments(WelfareCommentPage commentPage) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectRootComments",commentPage);
	}

	@Override
	public List<Comment> getCommentsForPage(SubcommentPage subcommentPage) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectSubCommentsForPage",subcommentPage);
	}

	@Override
	public Integer getSubcommentCount(int rootId) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.Welfare.selectSubcommentCount",rootId);
	}

	@Override
	public Integer getRootCommentCount(int welfareId) {
		return this.getSqlSession().selectOne("com.duang.fuli.domain.Welfare.selectRootCommentCount",welfareId);
	}

	@Override
	public List<Welfare> getWelfaresByTag(WelfareTag welfareTag) {
		return this.getSqlSession().selectList("com.duang.fuli.domain.Welfare.selectWelfaresByTag",welfareTag);
	}



}
