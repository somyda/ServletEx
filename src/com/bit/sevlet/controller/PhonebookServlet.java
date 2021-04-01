package com.bit.sevlet.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bit.servlet.dao.PhoneBook;
import com.bit.servlet.dao.PhoneBookDAO;
import com.bit.servlet.dao.PhoneBookDAOOrclImpl;

@WebServlet(name="PhoneBook", urlPatterns="/phonebook")
public class PhonebookServlet {

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("a");
		
		if ("form".equals(action)) {
			RequestDispatcher rd =
					getServletContext().getRequestDispatcher("/WEB-INF/views/phonebooklist/form.jsp");
			rd.forward(req, resp);
		} else if ("delete".equals(action)) {
			Long no = Long.valueOf(req.getParameter("no"));
			
			PhoneBookDAO dao = new PhoneBookDAOOrclImpl();
			dao.delete(no);
			resp.sendRedirect(req.getContextPath() + "/phonebook");
		} else {
			PhoneBookDAO dao = new PhoneBookDAOOrclImpl();
			List<PhoneBook> list = dao.getList();
			
			req.setAttribute("list", list);
			
			RequestDispatcher rd =
					getServletContext().getRequestDispatcher("/WEB-INF/views/phonebooklist/index.jsp");
			rd.forward(req, resp);
		}
	}
	

	private ServletRequest getServletContext() {
		// TODO Auto-generated method stub
		return null;
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String action = req.getParameter("action");
		
		if ("insert".equals(action)) {
			String id = req.getParameter("id");
			String name = req.getParameter("name");
			String hp = req.getParameter("hp");
			String tel = req.getParameter("tel");
			
			PhoneBook vo = new PhoneBook();
			vo.setId(id);
			vo.setName(name);
			vo.setHp(hp);
			vo.setTel(tel);
			
			PhoneBookDAO dao = new PhoneBookDAOOrclImpl();
			
			dao.insert(vo);
			
			resp.sendRedirect(req.getContextPath() + "/phonebook");
		} else {
			doGet(req, resp);
		}
	}
}
