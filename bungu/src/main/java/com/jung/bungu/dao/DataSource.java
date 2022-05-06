package com.jung.bungu.dao;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DataSource {
	private static DataSource dataSource = new DataSource(); // 자기 자신의 객체를 생성 (메서드로 객체를 만들수있게)

	private DataSource() {	// 다른곳에서 생성 못하게 생성자를 private로 묶음
	};

	private static Connection conn;	// 연결
	private String driver;
	private String url;
	private String user;
	private String passwd;
	
	public static DataSource getInstance() {	// 생성이 묶여 객체를 만들기 위한 방법
		return dataSource;
	}

	public Connection getConnection() {
		dbConfig();
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, user, passwd);
			System.out.println("DB 연결 성공!!!!!!!!!!!!!!!");
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
			System.out.println("ㅋㅋ실퍀ㅋㅋ");
		}
		
		
		
		
		return conn;
	}
	
	private void dbConfig() {
		Properties properties = new Properties();
		String resource = getClass().getResource("/db.properties").getPath();
		try {
			properties.load(new FileInputStream(resource));
			driver = properties.getProperty("driver");
			url = properties.getProperty("url");
			user = properties.getProperty("user");
			passwd = properties.getProperty("passwd");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
