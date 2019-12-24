package com.hpit.hotnews.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpit.hotnews.dao.CommentDao;
import com.hpit.hotnews.entity.Comment;
import com.hpit.hotnews.service.CommentService;

@Service
@Transactional
public class CommentServiceImpl implements CommentService{
	@Autowired
	private CommentDao commentDao;
	public void setCommentDao(CommentDao commentDao) {
		this.commentDao = commentDao;
	}
	@Override
	public List<Comment> showComment(BigDecimal nid) {
		return commentDao.getCommentByNewsid(nid);
	}
	@Override
	public boolean publishComment(Comment comment) {
		int i = commentDao.insertComment(comment);
		return (i == 1)?true:false;
	}
	@Override
	public void agree(BigDecimal commentid, BigDecimal agree) {
		commentDao.updateAgree(commentid, agree);
	}

}
