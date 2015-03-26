package com.jboss.fuse.rest.resource.controller;

import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.redhat.support.entity.Billing;
import org.redhat.support.service.BillingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/billing")
public class BillingResource {
	
	private BillingService billingService;
	
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
			}	
		} catch (Exception exception) {
			exception.getStackTrace();
		}
	}

	public void setBillingService(final BillingService billingService) {
		this.billingService = billingService;
	} 

}
