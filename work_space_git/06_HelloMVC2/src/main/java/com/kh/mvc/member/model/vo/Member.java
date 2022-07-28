package com.kh.mvc.member.model.vo;

import java.util.Date;

public class Member {
	private int 	no;
	private String 	id;
	private String 	password;
	private String 	role;
	private String 	name;
	private String 	phone;
	private String 	email;
	private String 	address;
	private String 	hobby;
	private String 	status;
	private Date 	enroll_date;
	private Date 	modify_date;

	public Member() {
		super();
	}

	public Member(int no, String id, String password, String role, String name, String phone, String email,
			String address, String hobby, String status, Date enroll_date, Date modify_date) {
		super();
		this.no = no;
		this.id = id;
		this.password = password;
		this.role = role;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.hobby = hobby;
		this.status = status;
		this.enroll_date = enroll_date;
		this.modify_date = modify_date;
	}

	@Override
	public String toString() {
		return "Member [no=" + no + ", id=" + id + ", password=" + password + ", role=" + role + ", name=" + name
				+ ", phone=" + phone + ", email=" + email + ", address=" + address + ", hobby=" + hobby + ", status="
				+ status + ", enroll_date=" + enroll_date + ", modify_date=" + modify_date + "]";
	}

	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getEnroll_date() {
		return enroll_date;
	}

	public void setEnroll_date(Date enroll_date) {
		this.enroll_date = enroll_date;
	}

	public Date getModify_date() {
		return modify_date;
	}

	public void setModify_date(Date modify_date) {
		this.modify_date = modify_date;
	}

}
