package com.group06fall17.banksix.model;

import java.beans.Transient;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import com.group06fall17.banksix.interceptor.ILogs;
import com.group06fall17.banksix.model.User;

/**
 * @author Saurabh
 *
 */

@Entity
@Table(name = "internal_user")
@DynamicUpdate
@SelectBeforeUpdate 
public class InternalUser implements ILogs{	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "usrid", nullable = false)
	private int usrid;
	
	@Column(name = "name", nullable = false)
	private String name;
	
//	@Column(name = "middlename")
//	private String middlename;
//	
//	@Column(name = "lastname", nullable = false)
//	private String lastname;

	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "email")
	private User email;
	

	@Column(name = "address", nullable = false)
	private String address;
	
//	@Column(name = "addressline2")
//	private String addressline2;
	
//	@Column(name = "city", nullable = false)
//	private String city;
//	
//	@Column(name = "state", nullable = false)
//	private String state;
//	
//	@Column(name = "zipcode", nullable = false, columnDefinition = "char")	
//	private String zipcode;
		
	@Column(name = "ssn", nullable = false)
	private String ssn;
	
	@Column(name = "accessprivilege", nullable = false)
	@ColumnDefault("RE1")
	private String accessprivilege;
	
	@Column(name = "piiaccess", nullable = false)
	private int piiaccess;
	
	public int getUsrid() {
		return usrid;
	}

	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}

	public User getEmail() {
		return email;
	}

	public void setEmail(User email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAccessprivilege() {
		return accessprivilege;
	}

	public void setAccessprivilege(String accessprivilege) {
		this.accessprivilege = accessprivilege;
	}

	public void setPiiaccess(int piiaccess) {
		this.piiaccess = piiaccess;
	}
	
	public int getPiiaccess() {
		return piiaccess;
	}
	
	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

/*
	public int getUsrid() {
		return usrid;
	}

	public void setUsrid(int usrid) {
		this.usrid = usrid;
	}

	public String getName() {
		return name;
	}

	public void setFirstname(String name) {
		this.name = name;
	}*/

	/*public String getMiddlename() {
		return middlename;
	}

	public void setMiddlename(String middlename) {
		this.middlename = middlename;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
*/
	/*public Users getEmail() {
		return email;
	}

	public void setEmail(Users email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
*/
	/*public String getAddressline2() {
		return addressline2;
	}

	public void setAddressline2(String addressline2) {
		this.addressline2 = addressline2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
*/
	/*public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}
	
	public String getAccessPrivilege(){
		return  accessprivilege;
	}
	
	public void setAcessPrivilege(String accessprivilege){
		this.accessprivilege = accessprivilege;
	}
	
	public int getPiiaccess() {
		return piiaccess;
	}
	
	public void setPiiacces(int piiaccess) {
		this.piiaccess = piiaccess;
	}
*/
	@Transient
	@Override
	public Long getId() {
		return Long.valueOf(this.usrid);
	}

	@Transient
	@Override
	public String getLogDetail() {
		StringBuilder logString = new StringBuilder();
		
		logString.append(" internal_user ").append(" usrid :" ).append(usrid)
		.append(" name : ").append(name)
/*		.append(" middlename : ").append(middlename)
		.append(" lastname : ").append(lastname)*/
		.append(" email : ").append(email.getUsername())
		.append(" address :").append(address)
		/*.append(" addressline2 : ").append(addressline2)
		.append(" city : ").append(city)
		.append(" state : ").append(state)
		.append(" zipcode :").append(zipcode)*/
		.append(" accessprivilege :").append(accessprivilege)
		.append(" piiacess:").append(piiaccess);

		return logString.toString();
	}
	
}
