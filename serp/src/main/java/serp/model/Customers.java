package serp.model;
// Generated Jul 28, 2016 7:45:42 PM by Hibernate Tools 4.3.1.Final

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Customers generated by hbm2java
 */
@Entity
@Table(name = "customers", catalog = "serp_qtdh")
public class Customers implements java.io.Serializable {

	private Integer id;
	private String customerId;
	private String companyName;
	private String address;
	private String agent;
	private String phoneNumber;
	private String mobileNumber;
	private String fax;
	private String email;
	private String webSite;
	private Boolean status;
	private String customerName;
	private Set<Orders> orderses = new HashSet<Orders>(0);

	public Customers() {
	}

	public Customers(String customerId) {
		this.customerId = customerId;
	}

	public Customers(String customerId, String companyName, String address, String agent, String phoneNumber,
			String mobileNumber, String fax, String email, String webSite, Boolean status, String customerName,
			Set<Orders> orderses) {
		this.customerId = customerId;
		this.companyName = companyName;
		this.address = address;
		this.agent = agent;
		this.phoneNumber = phoneNumber;
		this.mobileNumber = mobileNumber;
		this.fax = fax;
		this.email = email;
		this.webSite = webSite;
		this.status = status;
		this.customerName = customerName;
		this.orderses = orderses;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "Id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "CustomerID", nullable = false, length = 50)
	public String getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Column(name = "CompanyName", length = 200)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "Address", length = 100)
	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Column(name = "Agent", length = 50)
	public String getAgent() {
		return this.agent;
	}

	public void setAgent(String agent) {
		this.agent = agent;
	}

	@Column(name = "PhoneNumber", length = 20)
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Column(name = "MobileNumber", length = 20)
	public String getMobileNumber() {
		return this.mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Column(name = "Fax", length = 20)
	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	@Column(name = "Email", length = 50)
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "WebSite", length = 50)
	public String getWebSite() {
		return this.webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "CustomerName", length = 100)
	public String getCustomerName() {
		return this.customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "customers")
	public Set<Orders> getOrderses() {
		return this.orderses;
	}

	public void setOrderses(Set<Orders> orderses) {
		this.orderses = orderses;
	}

}
