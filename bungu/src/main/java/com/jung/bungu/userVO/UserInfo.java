package com.jung.bungu.userVO;

import lombok.Data;

@Data
public class UserInfo {
	private String name;
	private String id;
	private String passwd;
	private int monuy;
	private int havecleaner;
	private int havetle;
	
	
	
	public UserInfo() {
		
	}
	
	public UserInfo(String name, String id, String passwd) {
		super();
		this.name = name;
		this.id = id;
		this.passwd = passwd;
	}

	public UserInfo(String name, String id, String passwd, int monuy) {
		super();
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.monuy = monuy;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public int getMonuy() {
		return monuy;
	}

	public void setMonuy(int monuy) {
		this.monuy = monuy;
	}

	@Override
	public String toString() {
		
		System.out.printf(" 이름 : %4s, ID : %5s, 가진 금액 : %6d원 ",name,id,monuy);
		return "";
	}

	public UserInfo(String name, String id, String passwd, int monuy, int havecleaner, int havetle) {
		super();
		this.name = name;
		this.id = id;
		this.passwd = passwd;
		this.monuy = monuy;
		this.havecleaner = havecleaner;
		this.havetle = havetle;
	}
	

	
	
}
