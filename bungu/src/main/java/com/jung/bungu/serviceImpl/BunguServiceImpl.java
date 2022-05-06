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
	private Connection conn = dao.getConnection(); // connection연결

	private PreparedStatement psmt; // sql 명령실행
	private ResultSet rs; // select 결과 담음

	Scanner scn = new Scanner(System.in);

	UserInfo vo = new UserInfo();

	public List<UserInfo> userSelect() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM USERINFO";
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

	public List<UserInfo> userRank() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM USERINFO ORDER BY = MONUY";
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
	public UserInfo selectuser(String lId) { // 한명 유저 리스트 회원 아이디를 검색해서 조회

		String sql = "SELECT * FROM USERINFO WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, lId);
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
		} 
		return vo;
	}

	@Override
	public int insertuser(UserInfo user) { // 회원가입 디비
		int n = 0;

		String sql = "INSERT INTO USERINFO VALUES (?,?,?,0)"; // 이름, ID , 비밀번호 , 0 으로 생성
		try {

			vo = new UserInfo();
			psmt = conn.prepareStatement(sql);
			System.out.println("=== 회원가입 ===");
			System.out.print("이름를 입력해주세요 => ");
			String lName = scn.next();
			System.out.print("ID를 입력해주세요 => ");
			String lId = scn.next();
			System.out.print("Passwd를 입력해주세요 => ");
			String lPasswd = scn.next();

			psmt.setString(1, lName);
			psmt.setString(2, lId);
			psmt.setString(3, lPasswd);
			n = psmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return n;
	}

	@Override
	public int updateuser(String lId) { // 회원 정보 수정 아이디를 검색해서 수정
		int n = 0;
		String sql = "UPDATE USERINFO SET Passwd = ?  WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			System.out.println("변경 하실 비밀번호를 입력하세요");
			String upPasswd = scn.next();
			psmt.setString(1, upPasswd);
			psmt.setString(2, lId);

			n = psmt.executeUpdate();
			

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return n;
	}

	@Override
	public int deleteuser(String lId) { // 회원탈퇴 아이디를 검색해서 탈퇴
		int n = 0;
		String sql = "DELETE FROM USERINFO WHERE ID = ?";
		System.out.println("정말로 회원탈퇴를 진행하시겠습니까?(Y/N)");
		
		String check = scn.next();
		
		if (check.equalsIgnoreCase("y")) {
			try {
				psmt = conn.prepareStatement(sql);
				psmt.setString(1, lId);
				n = psmt.executeUpdate();
				
			} catch (Exception e) {
				e.printStackTrace();
			} 
		} else if(check.equalsIgnoreCase("n")){
			
			System.out.println("취소 하셨습니다.");
			System.out.println("이전 페이지로 돌아갑니다.");
			return 0;
			
		} else {
			System.out.println("잘못입력하셨습니다.");
			return 0;
		}
		System.out.println("정상적으로 탈퇴되셨습니다.");
		return n;
	}

	public int savingMonuy(int Monuy, String lId) { // 게임 종료시 남은 돈을 디비의 돈에 더하는 메서드

		// 게임 종료 시 남은 돈을 DB에 저장할 메서드
		String sql = "UPDATE USERINFO SET MONUY = MONUY + ? WHERE ID = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Monuy);
			psmt.setString(2, lId);
			psmt.executeQuery();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		}
		return -2;
	}

	public int Login(String userId, String userPasswd) { // 로그인 메서드 (디비에서 ID,PASSWD값을 가져와서 비교)

		String sql = "SELECT PASSWD FROM USERINFO WHERE ID = ?";
		String sql2 = "SELECT NAME FROM USERINFO WHERE ID = ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();

			if (rs.next()) {

				if (rs.getString(1).contentEquals(userPasswd)) {
					psmt = conn.prepareStatement(sql2);
					psmt.setString(1, userId);
					rs = psmt.executeQuery();
					if (rs.next()) {
						System.out.println(rs.getString(1) + "님 환영합니다.");
						return 1; // 로그인 성공
					}
				} else {
					System.out.println("비밀번호가 다릅니다.");
					return 0; // 비밀번호 불일치
				}
			} else {
				System.out.println("아이디를 찾을 수 없습니다.");
				return -1; // 아이디가 없음

			}

		} catch (Exception e) {
			e.printStackTrace();
		} 

		return -2;

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
