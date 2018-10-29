package com.thirdware.guptabookstore.servlets;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
import com.thirdware.guptabookstore.models.Book;
import com.thirdware.guptabookstore.models.Cart;
import com.thirdware.guptabookstore.models.History;

/**
 * Servlet implementation class PlaceOrderServlet
 */
@WebServlet("/PlaceOrderServlet")
public class PlaceOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PlaceOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		CartDao cartDao = new CartDaoImpl();
		int qunt=0;
		System.out.println("entering into place order");
		HttpSession session=request.getSession();
		String email=(String) session.getAttribute("email");
		List<Cart> cart=cartDao.getCart(email);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");  
		   LocalDateTime now = LocalDateTime.now();  
		HistoryDao historyDao=new HistoryDaoImpl();
		for(Cart c:cart){
			Book b=new Book();
			b.setBookid(c.getBookId());
			Book book=cartDao.getBookDetails(b);
			qunt=book.getQuantity();
			System.out.println("qnt "+qunt+" "+book.getQuantity());
			History history = new History();
			if(qunt>=c.getQuantity()){
				System.out.println("quantity "+c.getQuantity());
				qunt-=c.getQuantity();
				book.setQuantity(qunt);
				book.setBookid(c.getBookId());
				cartDao.updateBookQunatity(book);
				history.setBookname(c.getBookName());
				history.setQuantity(c.getQuantity());
				history.setPrice(c.getPrice());
				history.setCid(c.getCustomerId());
				history.setBookid(c.getBookId());
				history.setCustemail(c.getCustomername());
				history.setHisdate(dtf.format(now));
				historyDao.insertHistory(history);
				cartDao.deleteCart(c.getCartId());
			}
			else{
				System.out.println("Product quantity is low");
				String error="Product quantity is low! we will update as well as soon";
				request.setAttribute("error", error);
				RequestDispatcher rd=request.getRequestDispatcher("FetchCartServlet");
				rd.forward(request, response);
			}
			}
	
			String msg="Thank you for shopping!";
			request.setAttribute("msg", msg);
			response.sendRedirect("views/thankyoupage.jsp");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
