package com.student.app.messaging;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.Topic;

public interface IAdminService {

	Queue createQueue(String physicalName) throws JMSException;

	Topic createTopic(String physicalName) throws JMSException;

}
