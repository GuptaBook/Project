package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thirdware.guptabookstore.dao.HistoryDao;
import com.thirdware.guptabookstore.daoimpl.HistoryDaoImpl;
import com.thirdware.guptabookstore.models.History;

/**
 * Servlet implementation class ReportServlet
 */
@WebServlet("/ReportServlet")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReportServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stubHistoryDao historyDao=new
		// HistoryDaoImpl();
		/*
		 * DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		 * LocalDateTime now = LocalDateTime.now();
		 */
		String date = request.getParameter("hisdate");

		System.out.println(date);
		HistoryDao historyDao = new HistoryDaoImpl();
		List<History> historyList = historyDao.getAllHistory(date);
		System.out.println(historyList.isEmpty());
		if(historyList.isEmpty()){
			String msg="Sorry no transaction is available on "+date+". Please select some other date!";
			request.setAttribute("msg", msg);
		}
		request.setAttribute("historyList", historyList);
		RequestDispatcher rd = request.getRequestDispatcher("views/report.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
