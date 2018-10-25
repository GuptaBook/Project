package com.thirdware.guptabookstore.servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.dao.EmpDao;
import com.thirdware.guptabookstore.daoimpl.CustomerDaoImpl;
import com.thirdware.guptabookstore.daoimpl.EmpDaoImpl;
import com.thirdware.guptabookstore.models.Customer;
import com.thirdware.guptabookstore.models.Emp;

/**
 * Servlet implementation class EmpRegistration
 */
@WebServlet("/EmpRegistration")
public class EmpRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmpRegistration() {
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
		//int eid=Integer.parseInt(request.getParameter("eid"));
		/*String ename=request.getParameter("ename");
		String email=request.getParameter("email");
		String phoneno=request.getParameter("phoneno");
		//String role=request.getParameter("role");
		String password=request.getParameter("password");
		EmpDao empDao=new EmpDaoImpl();
		Emp e=empDao.empLogin(email);
		System.out.println(e.getEname()+" "+e.getEid()+" "+(e.getEmail()!=null));*/
		String cname=request.getParameter("uname");
		String email=request.getParameter("email");
		String phoneno=request.getParameter("phoneno");
		String roleid=request.getParameter("roleid");
		String password=request.getParameter("password");
		CustomerDao customerDao=new CustomerDaoImpl();
		Customer c=customerDao.customerLogin(email);
		if(c.getEmail()!=null)
		{
			System.out.println(email);
			System.out.println("email is exist i.e,already Registered");
			System.out.println(c.getCname()+" "+c.getPassword());
			System.out.println("Redirecting to Registration page");
			String msg="Email is existing so try with other email";
			request.setAttribute("error", msg);
			RequestDispatcher rd=request.getRequestDispatcher("views/emp/EmpRegis.jsp");
			rd.forward(request, response);
		}
		else
		{
		Customer customer=new Customer();
		//customer.setCid(cid);
		customer.setCname(cname);
		customer.setEmail(email);
		customer.setPhoneno(phoneno);
		customer.setPassword(password);
		customer.setRoleid(2);
		
		
		//EmpDao empDao=new EmpDaoImpl();
		System.out.println(customer.getCid()+" "+cname);
		Customer c1=customerDao.customerRegister(customer);
	    System.out.println("Registered Successfully");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String msg="Successfully registered!";
		request.setAttribute("msg", msg);
		RequestDispatcher rd = request.getRequestDispatcher("views/home.jsp");
		rd.forward(request, response);
		doGet(request, response);
	}
	}
}
