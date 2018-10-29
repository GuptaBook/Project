package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thirdware.guptabookstore.dao.CartDao;
import com.thirdware.guptabookstore.dao.HistoryDao;
import com.thirdware.guptabookstore.daoimpl.CartDaoImpl;
import com.thirdware.guptabookstore.daoimpl.HistoryDaoImpl;
import com.thirdware.guptabookstore.models.Cart;
import com.thirdware.guptabookstore.models.History;

/**
 * Servlet implementation class InsertHistoryServlet
 */
@WebServlet("/InsertHistoryServlet")
public class InsertHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("email");
		String date = new Date().toString();
		CartDao cartDao = new CartDaoImpl();
		List<History> list = new ArrayList<History>();
		List<Cart> cart = cartDao.getCart(email);
		History history = new History();
		for (Cart c : cart) {
			history.setBookname(c.getBookName());
			history.setQuantity(c.getQuantity());
			history.setPrice(c.getPrice());
			history.setCid(c.getCustomerId());
			history.setBookid(c.getBookId());
			history.setCustemail(c.getCustomername());
			history.setHisdate(date);
		}
		list.add(history);
		HistoryDao historyDao = new HistoryDaoImpl();
		historyDao.insertHistory(history);
		response.sendRedirect("FetchHistoryServlet");
		doGet(request, response);
	}

}
