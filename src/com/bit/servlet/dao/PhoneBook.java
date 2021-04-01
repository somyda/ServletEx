package com.bit.servlet.dao;

public class PhoneBook {
		
	private Integer id;
	private String name;
	private String hp;
	private String tel;
	
	public PhoneBook() {
			super();
	}
	
	public PhoneBook(String name, String hp, String tel) {
		super();
		this.id = null;
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}
	
	public PhoneBook(Integer id, String neme, String hp, String tel) {
		super();
		this.id = id;
		this.name = name;
		this.hp = hp;
		this.tel = tel;
	}
	
	public Integer getId() {
		return id;
	}
	public void setld(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	
	@Override
	public String toString() {
		return "Phonelnfo [name=" + name + ", hp+" + hp + ", tel+" + tel + "]";
	}

	public void setId(String id2) {
		
	}
	
}
