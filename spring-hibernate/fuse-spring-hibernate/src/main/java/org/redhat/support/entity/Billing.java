package org.redhat.support.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="BILLING_EVENT", schema="testdb")
public class Billing {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "BILLING_EVENT_ID")
	private Long billingEventId;
	
	@Column(name = "UUID")
	private String uuid;
	
	@Column(name = "CLIENT_CODE")
	private String clientCode;
	
	@Column(name = "BRANCH_CODE")
	private String branchCode;
	
	@Column(name = "USER_CODE")
	private String userCode;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	public Long getBillingEventId()
	{
		return billingEventId;
	}
	public void setBillingEventId(Long billingEventId)
	{
		this.billingEventId = billingEventId;
	}
	public String getUuid()
	{
		return uuid;
	}
	public void setUuid(String uuid)
	{
		this.uuid = uuid;
	}
	public String getClientCode()
	{
		return clientCode;
	}
	public void setClientCode(String clientCode)
	{
		this.clientCode = clientCode;
	}
	public String getBranchCode()
	{
		return branchCode;
	}
	public void setBranchCode(String branchCode)
	{
		this.branchCode = branchCode;
	}
	public String getUserCode()
	{
		return userCode;
	}
	public void setUserCode(String userCode)
	{
		this.userCode = userCode;
	}
	public String getUserName()
	{
		return userName;
	}
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
}
	
