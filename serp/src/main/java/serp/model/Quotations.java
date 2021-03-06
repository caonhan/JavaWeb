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
 * Quotations generated by hbm2java
 */
@Entity
@Table(name = "quotations", catalog = "serp_qtdh")
public class Quotations implements java.io.Serializable {

	private Integer id;
	private Estimates estimates;
	private String quotationId;
	private Integer numOfValidityDays;
	private Integer numOfDaysToComplete;
	private String paymentMethod1;
	private String paymentMethod2;
	private Integer status;
	private String userId;
	private Date publishDate;
	private Double amount;
	private Double vat;
	private Double totalAmount;
	private Set<QuotationDetails> quotationDetailses = new HashSet<QuotationDetails>(0);

	public Quotations() {
	}

	public Quotations(Estimates estimates, String quotationId) {
		this.estimates = estimates;
		this.quotationId = quotationId;
	}

	public Quotations(Estimates estimates, String quotationId, Integer numOfValidityDays, Integer numOfDaysToComplete,
			String paymentMethod1, String paymentMethod2, Integer status, String userId, Date publishDate,
			Double amount, Double vat, Double totalAmount, Set<QuotationDetails> quotationDetailses) {
		this.estimates = estimates;
		this.quotationId = quotationId;
		this.numOfValidityDays = numOfValidityDays;
		this.numOfDaysToComplete = numOfDaysToComplete;
		this.paymentMethod1 = paymentMethod1;
		this.paymentMethod2 = paymentMethod2;
		this.status = status;
		this.userId = userId;
		this.publishDate = publishDate;
		this.amount = amount;
		this.vat = vat;
		this.totalAmount = totalAmount;
		this.quotationDetailses = quotationDetailses;
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
	@JoinColumn(name = "EstimateID", nullable = false)
	public Estimates getEstimates() {
		return this.estimates;
	}

	public void setEstimates(Estimates estimates) {
		this.estimates = estimates;
	}

	@Column(name = "QuotationID", nullable = false, length = 50)
	public String getQuotationId() {
		return this.quotationId;
	}

	public void setQuotationId(String quotationId) {
		this.quotationId = quotationId;
	}

	@Column(name = "NumOfValidityDays")
	public Integer getNumOfValidityDays() {
		return this.numOfValidityDays;
	}

	public void setNumOfValidityDays(Integer numOfValidityDays) {
		this.numOfValidityDays = numOfValidityDays;
	}

	@Column(name = "NumOfDaysToComplete")
	public Integer getNumOfDaysToComplete() {
		return this.numOfDaysToComplete;
	}

	public void setNumOfDaysToComplete(Integer numOfDaysToComplete) {
		this.numOfDaysToComplete = numOfDaysToComplete;
	}

	@Column(name = "PaymentMethod1", length = 50)
	public String getPaymentMethod1() {
		return this.paymentMethod1;
	}

	public void setPaymentMethod1(String paymentMethod1) {
		this.paymentMethod1 = paymentMethod1;
	}

	@Column(name = "PaymentMethod2", length = 55)
	public String getPaymentMethod2() {
		return this.paymentMethod2;
	}

	public void setPaymentMethod2(String paymentMethod2) {
		this.paymentMethod2 = paymentMethod2;
	}

	@Column(name = "Status")
	public Integer getStatus() {
		return this.status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@Column(name = "UserID", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "PublishDate", length = 10)
	public Date getPublishDate() {
		return this.publishDate;
	}

	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	@Column(name = "Amount", precision = 22, scale = 0)
	public Double getAmount() {
		return this.amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Column(name = "VAT", precision = 22, scale = 0)
	public Double getVat() {
		return this.vat;
	}

	public void setVat(Double vat) {
		this.vat = vat;
	}

	@Column(name = "TotalAmount", precision = 22, scale = 0)
	public Double getTotalAmount() {
		return this.totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "quotations")
	public Set<QuotationDetails> getQuotationDetailses() {
		return this.quotationDetailses;
	}

	public void setQuotationDetailses(Set<QuotationDetails> quotationDetailses) {
		this.quotationDetailses = quotationDetailses;
	}

}
