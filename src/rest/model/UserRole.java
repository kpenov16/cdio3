package rest.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlRootElement;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@XmlRootElement
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRole implements Serializable, Cloneable {	
	private static final long serialVersionUID = -4458015632888402304L;
	public static final String ADMIN = "admin";
	public static final String LEADER = "leader";
	public static final String PHARMACIST = "pharmacist";
	public static final String TECHNICIAN = "technician";
	
	private boolean admin = false;	
	private boolean leader = false;
	private boolean pharmacist = false;
	private boolean technician = false;
	
	public UserRole() {
		this(false,false,false,false);	
	}	
	
	public UserRole(boolean admin, boolean leader, boolean pharmacist, boolean technician) {		
		setAdmin(admin);
		setLeader(leader);
		setPharmacist(pharmacist);
		setTechnician(technician);
	}	
	
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public boolean isLeader() {
		return leader;
	}
	public void setLeader(boolean leader) {
		this.leader = leader;
	}
	public boolean isPharmacist() {
		return pharmacist;
	}
	public void setPharmacist(boolean pharmacist) {
		this.pharmacist = pharmacist;
	}
	public boolean isTechnician() {
		return technician;
	}
	public void setTechnician(boolean technician) {
		this.technician = technician;
	}
	public boolean setRole(String role, boolean isThatRole) {
		boolean succsess = true;
		switch(role) {
		case ADMIN:
			setAdmin(isThatRole);
			break;
		case LEADER:
			setLeader(isThatRole);
			break;
		case PHARMACIST:
			setPharmacist(isThatRole);
			break;
		case TECHNICIAN:
			setTechnician(isThatRole);
			break;
		default:
			System.out.format("error: The role: %s is not supported by the UserRole class!", role);
			succsess = false;
			break;
		}
		return succsess;
	}
	
	@Override
	public String toString() {
		return String.format("UserRole: %s= %b, %s= %b, %s= %b, %s= %b"
		, ADMIN, isAdmin(), LEADER, isLeader(),  PHARMACIST, isPharmacist(), TECHNICIAN, isTechnician());
	}
	@Override
	public UserRole clone() {
	 return	new UserRole(isAdmin(), isLeader(), isPharmacist(), isTechnician());
	}
}
