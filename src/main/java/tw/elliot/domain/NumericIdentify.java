package tw.elliot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import tw.elliot.util.SecurityContextHolder;

@MappedSuperclass
public class NumericIdentify {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "OID", nullable = true)
	private Long oid;

	@Version
	@Column(name = "VERSION")
	private Integer version;

	@Column(name = "CREATE_ACCOUNT", length = 50)
	private String createAccount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATE_DATE")
	private Date createDate;

	@Column(name = "UPDATE_ACCOUNT", length = 50)
	private String updateAccount;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "UPDATE_DATE")
	private Date updateDate;

	public Long getOid() {
		return oid;
	}

	public void setOid(Long oid) {
		this.oid = oid;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreateAccount() {
		return createAccount;
	}

	public void setCreateAccount(String createAccount) {
		this.createAccount = createAccount;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUpdateAccount() {
		return updateAccount;
	}

	public void setUpdateAccount(String updateAccount) {
		this.updateAccount = updateAccount;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	@PrePersist
	public void preCreate() {
		this.createDate = new Date();
		this.createAccount = SecurityContextHolder.getLoginUserAccount();
	}

	@PreUpdate
	public void preUpdate() {
		this.updateDate = new Date();
		this.updateAccount = SecurityContextHolder.getLoginUserAccount();
	}
}
