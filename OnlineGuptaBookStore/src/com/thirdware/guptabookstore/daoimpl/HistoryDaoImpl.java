package com.thirdware.guptabookstore.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.thirdware.guptabookstore.dao.HistoryDao;
import com.thirdware.guptabookstore.getconnection.ConnectionProvider;
import com.thirdware.guptabookstore.models.History;

public class HistoryDaoImpl implements HistoryDao{


public int insertHistory(History history){
	ConnectionProvider databaseConnection = new ConnectionProvider();
	Connection con = databaseConnection.CONN();
	if (con == null) {
		System.out.println("Not Connected, Please check the database details");
	} else {
		try {
			String query = "insert into history(bookname, quantity, price, cid, bookid, custemail, hisdate) values(?,?,?,?,?,?,?)";
			PreparedStatement psmt = con.prepareStatement(query);
          /* LIST<HISTORY> LIST=NEW ARRAYLIST<HISTORY>();*/
			
			psmt.setString(1, history.getBookname());
			psmt.setInt(2, history.getQuantity());
			psmt.setFloat(3,history.getPrice());
			psmt.setInt(4,history.getCid());
			psmt.setInt(5,history.getBookid());
			psmt.setString(6, history.getCustemail());
			psmt.setString(7, history.getHisdate());
			
			int i = psmt.executeUpdate();
			if (i > 0) {
				System.out.println("Inserted Successfully");

			}
			psmt.close();
			con.close();
return 1;
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
				history.setQuantity(result.getInt(3));
				history.setPrice(result.getFloat(4));
				history.setCid(result.getInt(5));
				history.setBookid(result.getInt(6));
				history.setCustemail(result.getString(7));
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
@Override
public List<History> getAllHistory(String date) {
	// TODO Auto-generated method stub
	ConnectionProvider databaseConnection = new ConnectionProvider();
	Connection con = databaseConnection.CONN();
	if (con == null) {
		System.out.println("Not connected, Please check");
	} else {
		String query = "select bookid,bookname,sum(quantity),sum(price),count(bookid) from history where hisdate=? group by bookname,bookid having count(bookid)>=1";
		try {
			List<History> ls=new ArrayList<>();
			PreparedStatement psmt = con.prepareStatement(query);
			psmt.setString(1, date);
			
			ResultSet result = psmt.executeQuery();
			
			
			while (result.next()) {
				
				History history=new History();
				
				history.setBookid(result.getInt(1));
				history.setBookname(result.getString(2));
				history.setQuantity(result.getInt(3));
				history.setPrice(result.getFloat(4));
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

