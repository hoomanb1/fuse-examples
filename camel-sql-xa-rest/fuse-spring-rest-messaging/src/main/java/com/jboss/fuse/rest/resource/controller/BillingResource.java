package com.jboss.fuse.rest.resource.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.redhat.support.entity.Billing;
import org.redhat.support.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jboss.fuse.rest.service.MessageProducer;

@Path("/billing")
public class BillingResource {
	
	private BillingService billingService;
	private MessageProducer messageProducer;
	
	private static final Logger log = LoggerFactory.getLogger(BillingResource.class); 
	
	@PUT
	@Path("/create")
	@Consumes(MediaType.APPLICATION_JSON)
	public void createBilling(final Billing pBilling) {
		log.info("Creating billing with billingEventId ... : " + pBilling.getBillingEventId());
		try {
			if (pBilling != null) {
				log.info("Inseting into the billing Event table ..." + pBilling.getUserName());
				billingService.create(pBilling);
				// send a message to a queue to notify the camel route on the persisted record
				messageProducer.templateSendToQueue(pBilling);
			}	
		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}

	public void setBillingService(final BillingService billingService) {
		this.billingService = billingService;
	}

	public void setMessageProducer(MessageProducer messageProducer) {
		this.messageProducer = messageProducer;
	} 

}
