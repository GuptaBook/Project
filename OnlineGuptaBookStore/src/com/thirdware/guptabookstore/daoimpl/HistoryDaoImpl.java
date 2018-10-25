package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thirdware.guptabookstore.getconnection.ConnectionProvider;
import com.thirdware.guptabookstore.models.Book;
import com.thirdware.guptabookstore.models.History;

public class HistoryDaoImpl {


public int insertHistory(List <History> history){
	ConnectionProvider databaseConnection = new ConnectionProvider();
	Connection con = databaseConnection.CONN();
	if (con == null) {
		System.out.println("Not Connected, Please check the database details");
	} else {
		try {
			String query = "insert into history(bookname, quantity, price, cid, bookid, custemail, hisdate) values(?,?,?,?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(query);
           List<History> list=new ArrayList<History>();
			for(History h : history){
			psmt.setString(1, h.getBookname());
			psmt.setInt(2, h.getQuantity());
			psmt.setFloat(3,h.getPrice());
			psmt.setInt(4,h.getCid());
			psmt.setInt(5,h.getBookid());
			psmt.setString(6, h.getCustemail());
			psmt.setString(7, h.getHisdate());
			}
			int i = psmt.executeUpdate();
			if (i > 0) {
				System.out.println("Inserted Successfully");

			}
			psmt.close();
			con.close();

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	return 0;
}
public List<History> getHistory(String email) {

	ConnectionProvider databaseConnection = new ConnectionProvider();
	Connection con = databaseConnection.CONN();
	if (con == null) {
		System.out.println("Not connected, Please check");
	} else {
		String query = "select * from history where custemail = ?";
		try {
			List<History> ls=new ArrayList<>();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, email);
			
			ResultSet result = psmt.executeQuery();
			
			
			while (result.next()) {
				
				History history=new History();
				
				history.setId(result.getInt(1));
				history.setBookname(result.getString(2));
				history.setQuantity(result.getInt(4));
				history.setPrice(result.getFloat(5));
				history.setCid(result.getInt(6));
				history.setBookid(result.getInt(7));
				history.setCustemail(result.getString(8));
				history.setHisdate(result.getString(8));
				ls.add(history);
			}
			return ls;
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	return null;
}

}

