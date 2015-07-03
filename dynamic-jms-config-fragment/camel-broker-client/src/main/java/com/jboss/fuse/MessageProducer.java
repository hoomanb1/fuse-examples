package com.jboss.fuse;

import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer extends RouteBuilder {

	private static final Logger logger = LoggerFactory
			.getLogger(MessageProducer.class);

	private ProducerTemplate camelTemplate;

	@Override
	public void configure() throws Exception {
		if (camelTemplate != null) {
			for (int i = 0; i <= 100; i++) {
				logger.info("Sending message -: " + i);
				camelTemplate.sendBody("jms:testQueue", "camel");
			}
		}
	}

	public void setCamelTemplate(ProducerTemplate camelTemplate) {
		this.camelTemplate = camelTemplate;
	}
}
