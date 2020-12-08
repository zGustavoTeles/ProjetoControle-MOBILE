package com.sqlitePack;

import java.sql.SQLException;
import totalcross.sql.Connection;
import totalcross.sql.DriverManager;
import totalcross.sql.PreparedStatement;
import totalcross.sql.ResultSet;
import totalcross.sql.Statement;
import totalcross.sys.Convert;
import totalcross.sys.Settings;

public class SqlitePack {

	private Connection driver = null;

	 private Statement statement;
	public static final String CreatorID_ = "CTRL";

	public SqlitePack() throws Exception{


		 driver = DriverManager.getConnection("jdbc:sqlite:" + Convert.appendPath(Settings.appPath, "Controle.db"));
			
	     (statement = driver.createStatement()).executeUpdate("drop table if exists person");
	     statement.execute("create table PERSON (NAME CHAR(8))");

	}

	public boolean exists(String nomeTabela) throws Exception{
		try {
			statement.executeQuery("Select * from "+nomeTabela);
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}

	public boolean isClosed()throws Exception{
		return driver.isClosed();
	}
	
	public void setAutoCommitFalse()throws Exception{
		driver.setAutoCommit(false);
	}
	
	public void setAutoCommitTrue()throws Exception{
		driver.setAutoCommit(true);
	}
	
	public ResultSet executeQuery(String sql) throws Exception{
		ResultSet res = statement.executeQuery(sql);
		return res;
	}

	public PreparedStatement getPreparedStatement(String sql) throws Exception{
		return driver.prepareStatement(sql);
	}

	public int executeUpdate(String sql) throws Exception{
		int retorno = statement.executeUpdate(sql);
		driver.commit();
		return retorno;
	}
	

	public void execute(String sql)throws Exception{
		statement.execute(sql);
	}	

	public void closeAll() throws SQLException{
		try {
			statement.close();
			driver.close();
		} catch (SQLException e) {
			throw e;
		}
	}

	public void executeUpdateWhithoutException(String sql) {
		try{
			statement.executeUpdate(sql);
		}catch (SQLException e) {
			//System.out.println(e);
		}
	}

	public void executeWhithoutException(String sql) {
		try{
			statement.execute(sql);
		}catch (Exception e) {
			//System.out.println(e);
		}
	}

	public Connection getDriver() {
		return driver;
	}

	public void setDriver(Connection driver) {
		this.driver = driver;
	}

	
}
