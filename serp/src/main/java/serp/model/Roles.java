package serp.model;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

/**
 * Roles generated by hbm2java
 */
@Entity
@Table(name = "roles", catalog = "serp_qtdh")
public class Roles implements java.io.Serializable {

	private int roleId;
	private String roleName;
	private String description;
	private Set<Users> userses = new HashSet<Users>(0);

	public Roles() {
	}

	public Roles(int roleId) {
		this.roleId = roleId;
	}

	public Roles(int roleId, String roleName, String description, Set<Users> userses) {
		this.roleId = roleId;
		this.roleName = roleName;
		this.description = description;
		this.userses = userses;
	}

	@Id

	@Column(name = "RoleID", unique = true, nullable = false)
	public int getRoleId() {
		return this.roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Column(name = "RoleName", length = 50)
	public String getRoleName() {
		return this.roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Column(name = "Description", length = 100)
	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "user_role", catalog = "serp_qtdh", joinColumns = {
			@JoinColumn(name = "RoleID", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "UserId", nullable = false, updatable = false) })
	public Set<Users> getUserses() {
		return this.userses;
	}

	public void setUserses(Set<Users> userses) {
		this.userses = userses;
	}

}
