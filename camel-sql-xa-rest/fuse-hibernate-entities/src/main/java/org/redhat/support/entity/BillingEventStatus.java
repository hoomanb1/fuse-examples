package org.redhat.support.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BILLING_EVENT_STATUS", schema="test")
public class BillingEventStatus implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BILLING_EVENT_STATUS_ID")
	private Long billingEventStatusId;
	
	@Column(name = "EVENT_COMPLETE")
	private int eventComplete;
	
	@Column(name = "EVENT_STATUS")
	private String eventStatus;

	
	public Long getBillingEventStatusId() {
		return billingEventStatusId;
	}

	public void setBillingEventStatusId(Long billingEventStatusId) {
		this.billingEventStatusId = billingEventStatusId;
	}

	public int getEventComplete() {
		return eventComplete;
	}

	public void setEventComplete(int eventComplete) {
		this.eventComplete = eventComplete;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

}
