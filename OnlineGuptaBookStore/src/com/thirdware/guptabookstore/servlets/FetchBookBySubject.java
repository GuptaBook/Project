package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.AuthorDao;
import com.thirdware.guptabookstore.dao.BookDao;
import com.thirdware.guptabookstore.dao.SubjectDao;
import com.thirdware.guptabookstore.daoimpl.AuthorDaoImpl;
import com.thirdware.guptabookstore.daoimpl.BookDaoImpl;
import com.thirdware.guptabookstore.daoimpl.SubjectDaoImpl;
import com.thirdware.guptabookstore.models.Book;

/**
 * Servlet implementation class FetchBookBySubject
 */
@WebServlet("/FetchBookBySubject")
public class FetchBookBySubject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBookBySubject() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
        SubjectDao fetch = new SubjectDaoImpl();
		
		
		int id=Integer.parseInt(request.getParameter("id"));
		BookDao bookDao=new BookDaoImpl();
		Book b=bookDao.fetchBookById(id);
		List<Book> bookData = fetch.getSubject(b.getSubid());
		
	
    	request.setAttribute("booksList", bookData);
		
		RequestDispatcher rd = request.getRequestDispatcher("views/book/fetchallbook.jsp");
		rd.forward(request, response);
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
