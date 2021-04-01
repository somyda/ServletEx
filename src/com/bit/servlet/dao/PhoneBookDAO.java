package com.bit.servlet.dao;

import java.util.List;

public interface PhoneBookDAO {
	List<PhoneBook> getList();
	int addPhone(PhoneBook phoneBook);
	int deletePhone(int deleteId);
	List<PhoneBook> getSearch(String keyword);
	int delete(Long no);
	int insert(PhoneBook vo);
	
}
