package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
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
 * Servlet implementation class FetchHistoryServlet
 */
@WebServlet("/FetchHistoryServlet")
public class FetchHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchHistoryServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HistoryDao historyDao=new HistoryDaoImpl();
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		List<History> historyList=historyDao.getHistory(email);
		request.setAttribute("historyList", historyList);
		RequestDispatcher rd = request.getRequestDispatcher("views/cart/cart.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
		
		doGet(request, response);
	}

}
