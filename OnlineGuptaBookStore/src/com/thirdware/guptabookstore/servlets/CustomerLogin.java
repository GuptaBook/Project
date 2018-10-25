package com.thirdware.guptabookstore.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.models.Customer;

/**
 * Servlet implementation class CustomerLogin
 */
@WebServlet("/CustomerLogin")
public class CustomerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CustomerLogin() {
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
		String rootContext = request.getServletContext().getRealPath("/");
		
		String email=request.getParameter("email");
	    String password=request.getParameter("password");
		
		Customer customer=new Customer();
		
		customer.setEmail(email);
		customer.setPassword(password);
		System.out.println(email+" "+customer.getEmail()+" "+password+" "+customer.getPassword());
		CustomerDao customerDao=new CustomerDaoImpl();
		Customer c=customerDao.customerLogin(email);
		System.out.println("checking "+c.getEmail()+" "+(password.equals(c.getPassword())));
		if(c.getEmail()==null)
		{
			System.out.println("email is not Registered");
			System.out.println(c.getCname()+" "+c.getPassword());
			System.out.println("Redirecting to Registration page");
			String msg="Email does not exists!";
			request.setAttribute("error", msg);
			RequestDispatcher rd = request.getRequestDispatcher("views/customer/CustRegis.jsp");
			rd.forward(request, response);			
		}
		/*else if(c.getEmail().equals(email))
		{
			System.out.println("Email Registered i.e,Registered User");
		}*/
		 else
		{
			System.out.println("Email is not Registered");
		}
		if(password.equals(c.getPassword()))
			{
				System.out.println("Logged in");
				HttpSession session=request.getSession();  
				session.setAttribute("email",c.getEmail());
				session.setAttribute("username", c.getCname());
				String name=customerDao.role(c.getRoleid());
				session.setAttribute("rolename",name);
				System.out.println("session "+session.getAttribute("rolename"));
				response.sendRedirect("FetchAllAuthoServlet");
				//response.sendRedirect("CustRegis.jsp");
			}else{
				String msg="Password is incorrect!";
				request.setAttribute("error", msg);
				RequestDispatcher rd = request.getRequestDispatcher("views/emp/LoginPage.jsp");
				rd.forward(request, response);
			}
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doGet(request, response);
		
	}
}
