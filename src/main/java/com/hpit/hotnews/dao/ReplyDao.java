package com.hpit.hotnews.dao;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.Reply;

public interface ReplyDao {
	public void insertReply(Reply reply);
	public List<Reply> getReplyByCid(BigDecimal cid);
}
