package com.thirdware.guptabookstore.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thirdware.guptabookstore.dao.RatingDao;
import com.thirdware.guptabookstore.daoimpl.RatingDaoImpl;
import com.thirdware.guptabookstore.models.Rating;

/**
 * Servlet implementation class InsertRatingServlet
 */
@WebServlet("/InsertRatingServlet")
public class InsertRatingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertRatingServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		float r=Float.parseFloat(request.getParameter("star"));
		System.out.println(r);
		int bookid=Integer.parseInt(request.getParameter("bookid"));
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		RatingDao ratingDao=new RatingDaoImpl();
		Rating rating=new Rating();
		rating.setBookid(bookid);
		rating.setRating(r);
		rating.setCemail(email);
		ratingDao.insertRating(rating);
		String msg="Rated successfully!";
		request.setAttribute("msg", msg);
		RequestDispatcher rd=request.getRequestDispatcher("FetchBookByIdServlet?id="+bookid);
		rd.forward(request, response);
		doGet(request, response);
	}

}
