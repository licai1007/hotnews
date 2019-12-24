package com.hpit.hotnews.service;

import java.math.BigDecimal;
import java.util.List;

import com.hpit.hotnews.entity.Reply;

public interface ReplyService {
	public void publishReply(Reply reply);
	public List<Reply> getReply(BigDecimal cid);
}
