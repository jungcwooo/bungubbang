package com.jung.bungu.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.jung.bungu.dao.DataSource;
import com.jung.bungu.macha.PojangMacha;
import com.jung.bungu.service.BunguService;
import com.jung.bungu.userVO.UserInfo;

public class BunguServiceImpl extends Thread implements BunguService {
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
		} finally {
			close();
		}

		return list;
	}

	public List<UserInfo> userRank() {
		List<UserInfo> list = new ArrayList<UserInfo>();

		String sql = "SELECT * FROM USERINFO ORDER BY MONUY DESC";
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
		} finally {
			close();
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
		} finally {
			close();
		}
		return vo;
	}

	public int userMonuy(String lId) { // 한 유저가 가지고 있는 돈을 반환
		int haveMonuy = 0;
		String sql = "SELECT MONUY FROM USERINFO WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, lId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo = new UserInfo();
				vo.setMonuy(rs.getInt("monuy"));

				haveMonuy = vo.getMonuy();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return haveMonuy;
	}

	public int haveCleaner(String lId) { // 한 유저가 가지고 청소도구 장비를 반환
		int haveCleaner = 0;
		String sql = "SELECT HAVECLEANER FROM USERINFO WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, lId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo = new UserInfo();
				vo.setHavecleaner(rs.getInt("HAVECLEANER"));

				haveCleaner = vo.getHavecleaner();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return haveCleaner;
	}

	public int haveCase(String lId) { // 한 유저가 가지고 붕어빵틀 장비를 반환
		int haveCase = 0;
		String sql = "SELECT HAVETLE FROM USERINFO WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, lId);
			rs = psmt.executeQuery();

			if (rs.next()) {
				vo = new UserInfo();
				vo.setHavetle(rs.getInt("HAVETLE"));

				haveCase = vo.getHavetle();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return haveCase;
	}

	public int useMonuy(int Monuy, String lId) { // 장비를 업그레이드할때 디비에 돈을 차감하는 메서드 + 장비 업글

		String sql = "UPDATE USERINFO SET MONUY = MONUY - ? WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Monuy);
			psmt.setString(2, lId);
			psmt.executeQuery();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -2;
	}

	public int upCleaner(int Product, String lId) { // 장비를 업그레이드 청소도구

		String sql = "UPDATE USERINFO SET HAVECLEANER =  ? WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Product);
			psmt.setString(2, lId);
			psmt.executeQuery();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -2;
	}

	public int upTle(int Product, String lId) { // 장비를 업그레이드 빵틀

		String sql = "UPDATE USERINFO SET HAVETLE =  ? WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Product);
			psmt.setString(2, lId);
			psmt.executeQuery();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return -2;
	}

	@Override
	public int insertuser(UserInfo user) { // 회원가입 디비
		int n = 0;

		String sql = "INSERT INTO USERINFO VALUES (?,?,?,0,0,0)"; // 이름, ID , 비밀번호 , 돈 0 , 장비 0 으로 생성
		try {

			vo = new UserInfo();
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);

			psmt.setString(1, user.getName());
			psmt.setString(2, user.getId());
			psmt.setString(3, user.getPasswd());
			n = psmt.executeUpdate();

		} catch (Exception e) {
			n = 0;
		} finally {

			close();
		}
		return n;
	}

	@Override
	public int updateuser(String upPasswd,String lId) { // 내 정보 비밀번호를 수정
		int n = 0;
		String sql = "UPDATE USERINFO SET Passwd = ?  WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, upPasswd);
			psmt.setString(2, lId);

			n = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}

		return n;
	}

	@Override
	public int deleteuser(String lId) { // 로그인 상태에서 로그인한 계정 회원탈퇴 탈퇴
		int n = 0;
		String sql = "DELETE FROM USERINFO WHERE ID = ?";

		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, lId);
			n = psmt.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		
		return n;

	}

	public int savingMonuy(int Monuy, String lId) { // 게임 종료시 남은 돈을 디비의 돈에 더하는 메서드

		// 게임 종료 시 남은 돈을 DB에 저장할 메서드
		String sql = "UPDATE USERINFO SET MONUY = MONUY + ? WHERE ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, Monuy);
			psmt.setString(2, lId);
			psmt.executeQuery();

			return 1;

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
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
						return 1; // 로그인 성공
					}
				} else {

					return 0; // 비밀번호 불일치
				}
			} else {

				return -1; // 아이디가 없음

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
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
