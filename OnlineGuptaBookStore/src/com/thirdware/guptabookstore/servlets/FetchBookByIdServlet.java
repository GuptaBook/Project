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

import com.thirdware.guptabookstore.dao.AuthorDao;
import com.thirdware.guptabookstore.dao.BookDao;
import com.thirdware.guptabookstore.dao.CartDao;
import com.thirdware.guptabookstore.dao.CommentDao;
import com.thirdware.guptabookstore.dao.RatingDao;
import com.thirdware.guptabookstore.dao.SubjectDao;
import com.thirdware.guptabookstore.daoimpl.AuthorDaoImpl;
import com.thirdware.guptabookstore.daoimpl.BookDaoImpl;
import com.thirdware.guptabookstore.daoimpl.CartDaoImpl;
import com.thirdware.guptabookstore.daoimpl.CommentDaoImpl;
import com.thirdware.guptabookstore.daoimpl.RatingDaoImpl;
import com.thirdware.guptabookstore.daoimpl.SubjectDaoImpl;
import com.thirdware.guptabookstore.models.Book;
import com.thirdware.guptabookstore.models.Comment;
import com.thirdware.guptabookstore.models.Customer;
import com.thirdware.guptabookstore.models.Rating;

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
		HttpSession session=request.getSession();
		String email=(String)session.getAttribute("email");
		BookDao bookDao=new BookDaoImpl();
		SubjectDao subDao=new SubjectDaoImpl();
		AuthorDao authDao=new AuthorDaoImpl();
		Book b=bookDao.fetchBookById(bid);
		CommentDao commentDao=new CommentDaoImpl();
		CartDao cartDao=new CartDaoImpl();
		Customer cust=cartDao.getCustomer(email);
		List<Comment> cmt=commentDao.getAll(b.getBookid());
		List<Book> ls=subDao.getSubject(b.getSubid());
		List<Book> la=authDao.getAuthor(b.getAuthid());
		request.setAttribute("bookdetail", b);
		request.setAttribute("listSub", ls);
		request.setAttribute("listAuth", la);
		request.setAttribute("listcmt", cmt);
		RatingDao ratingDao=new RatingDaoImpl();
		Rating lr=ratingDao.fetchRating(bid);
		float ratingCum=(lr.getRating()/lr.getId());
		float checked=5-ratingCum;
		int cur,uncur;
		cur=(int) ratingCum;
		uncur=(int) checked;
		
		lr.setRating(ratingCum);
		request.setAttribute("listrating", cur);
		request.setAttribute("unchecked", uncur);
		System.out.println(ratingDao.getRatingById(email,bid));
		if(ratingDao.getRatingById(email,bid)){
			String rated=null;
			request.setAttribute("rate", rated);
		}else{
			String rated="not rated this user";
			request.setAttribute("rate", rated);
		}
		request.setAttribute("msg", request.getAttribute("msg"));
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
