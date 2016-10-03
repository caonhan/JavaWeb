package serp.model;
// Generated Jul 28, 2016 7:45:42 PM by Hibernate Tools 4.3.1.Final

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Orders generated by hbm2java
 */
@Entity
@Table(name = "orders", catalog = "serp_qtdh")
public class Orders implements java.io.Serializable {

	private Integer id;
	private Customers customers;
	private Users users;
	private String orderId;
	private String projectName;
	private Date publishDate;
	private String orderContent;
	private Integer possibility;
	private String judgingContent;
	private Integer approve;
	private String approvalContent;
	private Boolean status;
	private String approver;
	private Set<Estimates> estimateses = new HashSet<Estimates>(0);
	private Set<ROrders> ROrderses = new HashSet<ROrders>(0);

	public Orders() {
	}

	public Orders(Customers customers, Users users, String orderId) {
		this.customers = customers;
		this.users = users;
		this.orderId = orderId;
	}

	public Orders(Customers customers, Users users, String orderId, String projectName, Date publishDate,
			String orderContent, Integer possibility, String judgingContent, Integer approve, String approvalContent,
			Boolean status, String approver, Set<Estimates> estimateses, Set<ROrders> ROrderses) {
		this.customers = customers;
		this.users = users;
		this.orderId = orderId;
		this.projectName = projectName;
		this.publishDate = publishDate;
		this.orderContent = orderContent;
		this.possibility = possibility;
		this.judgingContent = judgingContent;
		this.approve = approve;
		this.approvalContent = approvalContent;
		this.status = status;
		this.approver = approver;
		this.estimateses = estimateses;
		this.ROrderses = ROrderses;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CustomerId", nullable = false)
	public Customers getCustomers() {
		return this.customers;
	}

	public void setCustomers(Customers customers) {
		this.customers = customers;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "UserId", nullable = false)
	public Users getUsers() {
		return this.users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Column(name = "OrderID", nullable = false, length = 50)
	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	@Column(name = "ProjectName", length = 50)
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PublishDate", length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "OrderContent", length = 65535)
	public String getOrderContent() {
		return this.orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	@Column(name = "Possibility")
	public Integer getPossibility() {
		return this.possibility;
	}

	public void setPossibility(Integer possibility) {
		this.possibility = possibility;
	}

	@Column(name = "JudgingContent", length = 65535)
	public String getJudgingContent() {
		return this.judgingContent;
	}

	public void setJudgingContent(String judgingContent) {
		this.judgingContent = judgingContent;
	}

	@Column(name = "Approve")
	public Integer getApprove() {
		return this.approve;
	}

	public void setApprove(Integer approve) {
		this.approve = approve;
	}

	@Column(name = "ApprovalContent", length = 65535)
	public String getApprovalContent() {
		return this.approvalContent;
	}

	public void setApprovalContent(String approvalContent) {
		this.approvalContent = approvalContent;
	}

	@Column(name = "Status")
	public Boolean getStatus() {
		return this.status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	@Column(name = "Approver", length = 50)
	public String getApprover() {
		return this.approver;
	}

	public void setApprover(String approver) {
		this.approver = approver;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<Estimates> getEstimateses() {
		return this.estimateses;
	}

	public void setEstimateses(Set<Estimates> estimateses) {
		this.estimateses = estimateses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "orders")
	public Set<ROrders> getROrderses() {
		return this.ROrderses;
	}

	public void setROrderses(Set<ROrders> ROrderses) {
		this.ROrderses = ROrderses;
	}

}
