package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ModelSkor extends DB{
	
	public ModelSkor() throws Exception, SQLException{
		super();
	}
	
	public void getTskor(){
		try{
			String query = "select * from tskor order by skor desc";
			createQuery(query);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public boolean insertTskor(String username, int skor){
		boolean bool=true;
		try{
			String query = "insert into tskor values(NULL,'"+username+"','"+skor+"')";
			createUpdate(query);
		}catch(Exception e){
			bool=false;
			// System.out.println(e.toString());//tidak perlu ditampilkan, masuk ke update
		}
		return bool;
	}
	
	public void updateTskor(String username, int skorBaru){
		try{
			String query = "update tskor set skor='"+skorBaru+"' where username='"+username+"'";
			createUpdate(query);
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}