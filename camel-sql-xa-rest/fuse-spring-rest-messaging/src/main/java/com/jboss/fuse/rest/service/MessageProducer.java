package com.jboss.fuse.rest.service;

import org.apache.camel.ProducerTemplate;
import org.redhat.support.entity.Billing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MessageProducer {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);
	
    private ProducerTemplate camelTemplate;
	
	public void templateSendToQueue(Billing billing) throws Exception {
		
		if(logger.isDebugEnabled()) {
			logger.debug("Instantiating new messageTemplate");
		}
		
		if (camelTemplate != null) {
			logger.info("Sending message into the queue..." );		
			camelTemplate.sendBodyAndHeader("activemq:billing.records", "Camel", "uuid", billing.getUuid());
			
		}
	}

	public void setCamelTemplate(ProducerTemplate camelTemplate) {
		this.camelTemplate = camelTemplate;
	}

}
