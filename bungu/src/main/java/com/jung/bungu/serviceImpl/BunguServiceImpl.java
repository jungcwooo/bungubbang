package com.jung.bungu.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jung.bungu.dao.DataSource;
import com.jung.bungu.service.BunguService;
import com.jung.bungu.userVO.UserInfo;

public class BunguServiceImpl implements BunguService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn = DataSource.getConnection(); // connection연결

	private PreparedStatement psmt; // sql 명령실행
	private ResultSet rs; // select 결과 담음

	Scanner scn = new Scanner(System.in);

	UserInfo vo = new UserInfo();

	public List<UserInfo> userSelect() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM USER";
		try {
			
			conn = dao.getConnection(); // DB와 커넥션을 연결
			psmt = conn.prepareStatement(sql); // 연결을 하고 psmt에 sql을 담아서 보냄
			rs = psmt.executeQuery(); // select는 resultSet에 결과가 담김

			while (rs.next()) { // rs에 저장된 결과값을 하나씩 읽음
				vo = new UserInfo();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setMonuy(rs.getInt("monuy"));

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}
	
	
	public List<UserInfo> userRank(){
		List<UserInfo> list = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM USER ORDER BY = MONUY";
		try {
			conn = dao.getConnection(); 
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery(); 

			while (rs.next()) { 
				vo = new UserInfo();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setMonuy(rs.getInt("monuy"));

				list.add(vo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public UserInfo selectuser(UserInfo user) { // 한명 유저 리스트 회원 아이디를 검색해서 조회

		String sql = "SELECT * FROM USER WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo = new UserInfo();
				vo.setId(rs.getString("ID"));
				vo.setName(rs.getString("name"));
				vo.setPasswd(rs.getString("passwd"));
				vo.setMonuy(rs.getInt("monuy"));

				

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();

		}
		return vo;
	}

	@Override
	public int insertuser(UserInfo user) { // 회원가입
		int n = 0;
		String sql = "INSERT INTO USER VALUES (?,?,?,0)";	// 이름, ID , 비밀번호 , 0 으로 생성
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getName());
			psmt.setString(2, vo.getId());
			psmt.setString(3, vo.getPasswd());
			n = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();

		}

		return n;
	}

	@Override
	public int updateuser(UserInfo user) { // 회원 정보 수정 아이디를 검색해서 수정
		int n = 0;
		String sql = "UPDATE USER SET Passwd = ?  WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getPasswd());
			psmt.setString(2, vo.getId());

			n = psmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();

		}

		return n;
	}

	@Override
	public int deleteuser(UserInfo user) { // 회원탈퇴 아이디를 검색해서 탈퇴
		int n = 0;
		String sql = "DELETE FROM USER WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, vo.getId());
			n = psmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();

		}

		return n;
	}
	
	private void savingMonuy(int Monuy, String id) {
		
		// 게임 종료 시 남은 돈을 DB에 저장할 메서드
		String sql = "UPDATE USER SET MONUY = MONUY + ? WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Monuy);
			psmt.setString(2, id);
			psmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void close() {
		try {
			if (rs != null)
				rs.close();
			if (psmt != null)
				psmt.close();
			if (conn != null)
				conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
