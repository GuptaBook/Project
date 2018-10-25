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
 * Servlet implementation class FetchBookByIdServlet
 */
@WebServlet("/FetchBookByIdServlet")
public class FetchBookByIdServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchBookByIdServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int bid=Integer.parseInt(request.getParameter("id"));
		BookDao bookDao=new BookDaoImpl();
		SubjectDao subDao=new SubjectDaoImpl();
		AuthorDao authDao=new AuthorDaoImpl();
		Book b=bookDao.fetchBookById(bid);
		List<Book> ls=subDao.getSubject(b.getSubid());
		List<Book> la=authDao.getAuthor(b.getAuthid());
		request.setAttribute("bookdetail", b);
		request.setAttribute("listSub", ls);
		request.setAttribute("listAuth", la);
		RequestDispatcher rd=request.getRequestDispatcher("views/book/bookdetails.jsp");
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
