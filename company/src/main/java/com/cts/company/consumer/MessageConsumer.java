package com.cts.company.consumer;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.cts.company.config.MessagingConfig;
import com.cts.company.entities.Company;

@Component
public class MessageConsumer {
	/*@RabbitListener(queues = MessagingConfig.QUEUE)
	public void consumeMessageFromQueue(List<Company> companyList) {
		System.out.println("Message Received from Queue:"+companyList);
	}*/

}
