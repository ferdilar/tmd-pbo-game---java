package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class ModelKata extends DB{

	public ModelKata() throws Exception, SQLException{
		super();
	}
	
	public void getTkata(){
		try{
			String query = "select * from tkata";
			createQuery(query);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	public void countTkata(){//ga ke-pake
		try{
			String query = "select count(id_kata) from tkata";
			createQuery(query);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
	public void getInisial(int id_kata){//id_kata diterima dari nilai random
		try{
			String query = "select hurufDepan from tkata where id_kata='"+id_kata+"'";
			createQuery(query);
		}
		catch(Exception e){
			System.out.println(e.toString());
		}
	}
	
}