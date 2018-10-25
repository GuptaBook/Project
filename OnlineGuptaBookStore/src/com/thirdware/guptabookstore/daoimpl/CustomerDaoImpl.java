package com.thirdware.guptabookstore.daoimpl;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.thirdware.guptabookstore.dao.CustomerDao;
import com.thirdware.guptabookstore.getconnection.ConnectionProvider;
import com.thirdware.guptabookstore.models.Customer;


public class CustomerDaoImpl implements CustomerDao {
	ConnectionProvider dbConnection=new ConnectionProvider();
	Connection con=dbConnection.CONN();
	public Customer customerRegister(Customer customer) {
		if(con==null)
		{
			System.out.println("Not Connected,Try again");
		}
		else
		{
			String query="insert into customer(cid,cname,email,phoneno,roleid,password) values(?,?,?,?,?,?)";
			try
			{
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1, getMaxId()+1);
				pstmt.setString(2,customer.getCname());
				pstmt.setString(3,customer.getEmail());
				pstmt.setString(4,customer.getPhoneno());
				pstmt.setInt(5,customer.getRoleid());
				pstmt.setString(6,customer.getPassword());
				int count=pstmt.executeUpdate();
				if(count>0)
				{
					System.out.println("Records affected:"+count);
				}
				if(con!=null)
					con.close();
				if(pstmt!=null)
					   pstmt.close();
				return customer;
				
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	
	}

	private int getMaxId() {
		
		int maxId=0;
		String query="select max(cid) from customer";
		try{
			ConnectionProvider databaseConnection = new ConnectionProvider();
			Connection con = databaseConnection.CONN();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery(query);
			while(rs.next()){
				maxId=rs.getInt(1);
			}
			System.out.println(maxId);
			return maxId;
		}
	    catch(Exception e)
		{
	    	System.out.println(e);
	    	}
		return -1;
	}

	@Override
	public Customer customerLogin(String email)
	{
		ConnectionProvider dbConnection=new ConnectionProvider();
		Connection con=dbConnection.CONN();
		if(con==null)
		{
			System.out.println("Not Connected,Try again");
		}
		else
		{
			String query="select * from customer where email=?";
			try
			{
				PreparedStatement pstmt=con.prepareStatement(query);
			    Customer customer=new Customer();
				pstmt.setString(1,email);
				System.out.println("checking in daoimpl "+email);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
					customer.setCid(rs.getInt(1));
					customer.setCname(rs.getString(2));
					customer.setEmail(rs.getString(3));
					customer.setPhoneno(rs.getString(4));
					customer.setPassword(rs.getString(5));
					customer.setRoleid(rs.getInt(6));					
				}
				if(con!=null)
					con.close();
				if(pstmt!=null)
					   pstmt.close();
				return customer;
			}
			catch(SQLException e)
			{
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public String role(int id) {
		// TODO Auto-generated method stub
		String name="";
		if(con==null)
		{
			System.out.println("Not Connected,Try again");
			return null;
		}
		else
		{
			String query="select rolename from role where roleid=?";
			try
			{
				PreparedStatement pstmt=con.prepareStatement(query);
				pstmt.setInt(1,id);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
					name=rs.getString(1);
				return name;
				
			}catch(Exception e){System.out.println(e);}
		return null;
	}
	}
}

