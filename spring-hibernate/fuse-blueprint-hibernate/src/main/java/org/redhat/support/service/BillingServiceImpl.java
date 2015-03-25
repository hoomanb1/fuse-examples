package org.redhat.support.service;

import javax.persistence.EntityManager;

import org.redhat.support.entity.Billing;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BillingServiceImpl implements BillingService {

	private final EntityManager entityManager;

	private static final Logger log = LoggerFactory
			.getLogger(BillingServiceImpl.class);

	public BillingServiceImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public void create(Billing billing) {
		log.info("This is from the hibernate bundle ...");
		try {
			if (billing != null) {
				log.info("Persist Billing ...");
				entityManager.persist(billing);
			}
		} catch (Exception e) {
			e.getStackTrace();
		}

	}
}
