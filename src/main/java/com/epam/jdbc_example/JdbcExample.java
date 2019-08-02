package com.epam.jdbc_example;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.exception.DbConnectionFailedException;

public class JdbcExample {
	
	private static final Logger logger = LogManager.getLogger(JdbcExample.class);
	
	public static void main(String[] args) {
		DbConnection dbConnection = new DbConnection();
		
		try(Connection connection = dbConnection.getDbConnection();){
			
			logger.trace("connection established");
			
		} catch (SQLException sqle) {
			
			logger.error("sql exception occured");
			throw new DbConnectionFailedException("unable to connect database server");
		}
	}

}
