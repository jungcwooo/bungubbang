package com.jung.bungu.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
	private static DataSource dataSource = new DataSource(); // 자기 자신의 객체를 생성 (메서드로 객체를 만들수있게)
	private static Connection conn;	// 연결

	private DataSource() {	// 다른곳에서 생성 못하게 생성자를 private로 묶음
	};

	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private static String user = "jung";
	private static String passwd = "1234";

	public static Connection getConnection() {
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
	
	public static DataSource getInstance() {	// 생성이 묶여 객체를 만들기 위한 방법
		return dataSource;
	}

}
