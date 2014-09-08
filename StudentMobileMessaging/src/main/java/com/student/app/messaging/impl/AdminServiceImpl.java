package com.student.app.messaging.impl;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.student.app.messaging.IAdminService;

@Service("iAdminService")
@Transactional
public class AdminServiceImpl implements IAdminService {

	@Autowired
	private CachingConnectionFactory cachingConnectionFactory;

	@Override
	public Queue createQueue(String physicalName) throws JMSException {
		Connection conn = cachingConnectionFactory.createConnection();
		Session session = conn.createSession(true, Session.SESSION_TRANSACTED);
		return session.createQueue(physicalName);
	}

	@Override
	public Topic createTopic(String physicalName) throws JMSException {
		cachingConnectionFactory.setClientId("123");
		Connection conn = cachingConnectionFactory.createConnection();
		conn.start();
		Session session = conn.createSession(false, Session.SESSION_TRANSACTED);
		Topic topic = session.createTopic(physicalName);
		session.createDurableSubscriber(topic, "SUB1");
		session.commit();
		session.close();
		conn.stop();
		conn.close();
		return topic;
	}

}
