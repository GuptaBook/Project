package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.thirdware.guptabookstore.dao.RatingDao;
import com.thirdware.guptabookstore.getconnection.ConnectionProvider;
import com.thirdware.guptabookstore.models.Rating;

public class RatingDaoImpl implements RatingDao {

	@Override
	public int insertRating(Rating rating) {
		// TODO Auto-generated method stub
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
			return 0;
		} else {
			try {
				String query="insert into rating(rating,cemail,bookid) values(?,?,?)";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setFloat(1, rating.getRating());
				psmt.setString(2, rating.getCemail());
				psmt.setInt(3, rating.getBookid());
				int i = psmt.executeUpdate();
				if (i > 0) 
					System.out.println("Inserted Successfully");
				psmt.close();
				con.close();
				return rating.getBookid();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return 0;

	}

	@Override
	public Rating fetchRating(int bookid) {
		// TODO Auto-generated method stub
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
		} else {
			try {
				ArrayList<Rating> ls=new ArrayList<>();
				String query="select sum(rating),count(bookid) from rating where bookid=?";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setInt(1, bookid);
				ResultSet rs=psmt.executeQuery();
				Rating r=new Rating();
				while(rs.next()){
					
					r.setRating(rs.getFloat(1));
					r.setId(rs.getInt(2));
				}
				return r;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return null;
	}

	@Override
	public boolean getRatingById(String email,int bookid) {
		// TODO Auto-generated method stub
		ConnectionProvider databaseConnection = new ConnectionProvider();
		Connection con = databaseConnection.CONN();
		if (con == null) {
			System.out.println("Not Connected, Please check the database details");
			return false;
		} else {
			try {
				String query="select * from rating where bookid=? and cemail=?";
				PreparedStatement psmt = con.prepareStatement(query);
				psmt.setInt(1, bookid);
				psmt.setString(2, email);
				ResultSet rs=psmt.executeQuery();
				Rating r=new Rating();
				while(rs.next()){				 
					r.setId(rs.getInt(1));
					r.setRating(rs.getFloat(2));
					r.setCemail(rs.getString(3));
					r.setBookid(rs.getInt(4));
				}
				if(r.getId()==0) 
					return false;
				else 
					return true;
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return false;
	}

}
