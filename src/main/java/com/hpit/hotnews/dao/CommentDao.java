package com.hpit.hotnews.dao;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.Comment;

public interface CommentDao {
	public List<Comment> getCommentByNewsid(BigDecimal nid);
	public int insertComment(Comment comment);
	public void updateAgree(BigDecimal commentid,BigDecimal agree);
}
