package com.hpit.hotnews.service;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.Comment;

public interface CommentService {
	public List<Comment> showComment(BigDecimal nid);
	public boolean publishComment(Comment comment);
	public void agree(BigDecimal commentid,BigDecimal agree);
}
