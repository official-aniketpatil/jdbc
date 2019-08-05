package com.epam.jdbc_example;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.exception.DbConnectionFailedException;

public class JdbcExample {

	private static final Logger logger = LogManager.getLogger(JdbcExample.class);

	public void createTable(String tableName) {
		try (Connection connection = new DbConnection().getDbConnection();
				Statement stmt = connection.createStatement();) {
			stmt.execute("use epam");
			stmt.execute("create table " + tableName + "(id int, name varchar(40), level varchar(40))");
			logger.trace("table : " + tableName + " is created");
		} catch (DbConnectionFailedException ex) {
			logger.error(ex.getMessage());
		} catch (SQLException sqle) {
			logger.error(sqle.getMessage());
		}
	}

	public void helper() {
		DbConnection dbConnection = new DbConnection();

		try (Connection connection = dbConnection.getDbConnection();) {

			logger.trace("connection established");

		} catch (SQLException sqle) {

			logger.error("sql exception occured");
			throw new DbConnectionFailedException("unable to connect database server");
		}
	}

	public static void main(String[] args) {
		JdbcExample jdbcExample = new JdbcExample();
		jdbcExample.createTable("employee");
	}

}
