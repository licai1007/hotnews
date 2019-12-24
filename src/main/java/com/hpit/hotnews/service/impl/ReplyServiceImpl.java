package com.hpit.hotnews.service.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hpit.hotnews.dao.ReplyDao;
import com.hpit.hotnews.entity.Reply;
import com.hpit.hotnews.service.ReplyService;

@Service
@Transactional
public class ReplyServiceImpl implements ReplyService{
	@Autowired
	private ReplyDao replyDao;
	public void setReplyDao(ReplyDao replyDao) {
		this.replyDao = replyDao;
	}
	@Override
	public void publishReply(Reply reply) {
		replyDao.insertReply(reply);
	}
	@Override
	public List<Reply> getReply(BigDecimal cid) {
		return replyDao.getReplyByCid(cid);
	}

}
