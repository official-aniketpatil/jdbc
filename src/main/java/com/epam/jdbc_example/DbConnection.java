package com.epam.jdbc_example;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.epam.exception.DbConnectionFailedException;

public class DbConnection {

	private static final Logger logger = LogManager.getLogger(DbConnection.class);
	private static final String PROPERTY_FILE_URL = "src/main/resources/application.properties";
	private Properties properties;

	private void loadProperties() {

		properties = new Properties();

		try (InputStream inputStream = new FileInputStream(PROPERTY_FILE_URL);) {

			properties.load(inputStream);

		} catch (IOException ex) {
			logger.error("Property file for database details not found.");
		}

	}

	public Connection getDbConnection() {
		Connection conn = null ;
		loadProperties();
		
		try {
			
			conn = DriverManager.getConnection(
					"jdbc:" + properties.getProperty("db.dbms") + "://" + properties.getProperty("db.serverName") + ":"
							+ properties.getProperty("db.port") + "/",
					properties.getProperty("db.user"), properties.getProperty("db.password"));
		
		} catch (SQLException e) {
			
			logger.error("sql exception while establishing connection with server");
			throw new DbConnectionFailedException("unable to connect database server");
			
		}

		logger.info("connected to database");

		return conn;
	}
}
