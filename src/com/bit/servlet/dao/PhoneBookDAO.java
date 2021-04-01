package com.bit.servlet.dao;

import java.util.List;

public interface PhoneBookDAO {
	List<PhoneBook> getList();
	int insert(PhoneBook phoneBook);
	int delete(Integer deleteId);
	List<PhoneBook> getSearch(String keyword);
}
