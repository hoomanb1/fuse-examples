package org.redhat.support.service;

import javax.persistence.EntityManager;

import org.redhat.support.entity.Billing;

public class BillingServiceImpl implements BillingService {
	
	private EntityManager entityManager;
	
	public BillingServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	
	public BillingServiceImpl() {
		
	}

	public void create(Billing billing) {
		if (billing != null) {
			Billing billingObject = new Billing();
			billingObject.setBillingEventId(billing.getBillingEventId());
			billingObject.setUuid(billing.getUuid());
			billingObject.setClientCode(billing.getClientCode());
			billingObject.setBranchCode(billing.getBranchCode());
			billingObject.setUserCode(billing.getUserCode());
			billing.setUserName(billing.getUserName());
			entityManager.persist(billingObject);
			
		}
	}

}
