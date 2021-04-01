package com.bit.sevlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.servlet.dao.PhoneBook;
import com.bit.servlet.dao.PhoneBookDAO;
import com.bit.servlet.dao.PhoneBookDAOImpl;

@WebServlet(name="PhoneBook", urlPatterns="/phonebook")
public class PhonebookServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if ("form".equals(action)) {
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/phonebook/form.jsp");
			rd.forward(req, res);
		} else if ("delete".equals(action)) {
			Integer id = Integer.valueOf(req.getParameter("id"));
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			dao.delete(id);
			res.sendRedirect(req.getContextPath() + "/phonebook");
		} else {
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBook> list = dao.getList();
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd =	getServletContext().getRequestDispatcher("/WEB-INF/views/phonebook/list.jsp");
			rd.forward(req, res);
		}
	}
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			PhoneBook vo = new PhoneBook();
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);
			
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			
			dao.insert(vo);
			
			res.sendRedirect(req.getContextPath() + "/phonebook?a=form");
		} else if("search".equals(action)) {
			String keyword = req.getParameter("keyword");
			PhoneBookDAO dao = new PhoneBookDAOImpl();
			List<PhoneBook> list = dao.getSearch(keyword);
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/views/phonebook/list.jsp");
			rd.forward(req, res);
		} else {
			doGet(req, res);
		}
	}
}
