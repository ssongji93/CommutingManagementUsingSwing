package test;
//이름 규칙 : 테이블명DAO , 테이블명DTO

//CRUD : Create;insert , Read;Select, Update, delete

import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

//DB 처리
public class EmpDAO {

	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	private static final String USER = "testdb"; // DB ID
	private static final String PASS = "1234"; // DB 패스워드
	EmpList empList;

	public EmpDAO() {

	}

	public EmpDAO(EmpList empList) {
		this.empList = empList;
		System.out.println("DAO=>" + empList);
	}

	/** DB연결 메소드 */
	public static Connection getConn() {
		Connection con = null;

		try {
			Class.forName(DRIVER); // 1. 드라이버 로딩
			con = DriverManager.getConnection(URL, USER, PASS); // 2. 드라이버 연결

		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	/** 한사람의 회원 정보를 얻는 메소드 */
	public EmpDTO getEmpDTO(String id) {

		EmpDTO dto = new EmpDTO();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String sql = "select *from emp where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setEmpNo(rs.getString("emp_no"));
				dto.setName(rs.getString("name"));
				dto.setEmpPos(rs.getString("emp_pos"));
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return dto;
	}

	/** 로그인시 사원번호 및 직급 받아오기 */
	public String[] getEmpNoAndPos(String id) {
		String[] empNoAndPos = new String[2];
		EmpDTO dto = new EmpDTO();

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String sql = "select emp_no, emp_pos from emp where id=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

			rs = ps.executeQuery();

			if (rs.next()) {
				dto.setEmpNo(rs.getString(1));
				dto.setEmpPos(rs.getString(2));
			}
			empNoAndPos[0] = dto.getEmpNo();
			empNoAndPos[1] = dto.getEmpPos();
			
		} catch (Exception e) {
			e.printStackTrace();
		}

		return empNoAndPos;
	}

	/** id를 받아 한사람의 멤버리스트 출력 */
	public Vector getEmpList(EmpDTO dto) {
		String loginId = dto.getId();
		System.out.println(loginId);
		Vector data = new Vector(); // Jtable에 값을 쉽게 넣는 방법 1. 2차원배열 2. Vector 에 vector추가

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		ResultSet rs = null; // 결과

		try {

			con = getConn();
			String sql = "select emp.emp_no, name, emp_pos, id, wk_day, wk_start, wk_end, daily_wage from emp "
					+ "join emp_wk on emp.emp_no = emp_wk.emp_no where id = '" + loginId + "' order by WK_DAY desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			while (rs.next()) {
				String empNo = rs.getString(1);
				String name = rs.getString(2);
				String empPos = rs.getString(3);
				String id = rs.getString(4);
				String wk_day = rs.getString(5);
				String wk_start = rs.getString(6);
				String wk_end = rs.getString(7);
				String daily_wage = rs.getString(8);

				Vector row = new Vector();
				row.add(empNo);
				row.add(name);
				row.add(empPos);
				row.add(id);
				row.add(wk_day);
				row.add(wk_start);
				row.add(wk_end);
				row.add(daily_wage);

				data.add(row);
			} // while
		} catch (Exception e) {
			e.printStackTrace();
		}
		return data;

	}// getEmpList()

	/** 회원 등록 */
	public boolean insertEmp(EmpDTO dto) {

		boolean ok = false;

		Connection con = null; // 연결
		PreparedStatement ps = null; // 명령
		PreparedStatement ps2 = null; // 명령

		try {

			con = getConn();
			String sql = "insert into emp(emp_no, name, emp_pos, id, pwd) values(?,?,?,?,?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, dto.getEmpNo());
			ps.setString(2, dto.getName());
			ps.setString(3, dto.getEmpPos());
			ps.setString(4, dto.getId());
			ps.setString(5, dto.getPwd());

			int r = ps.executeUpdate(); // 실행 -> 저장

			if (r > 0) {
				System.out.println("가입 성공");
				ok = true;
			} else {
				System.out.println("가입 실패");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

//		try {
//
//			con = getConn();
//			String sql2 = "insert into emp_wk(emp_no, wk_day, wk_start, wk_end, pay) values(?,'9999-12-31',?,?,?)";
//			ps2 = con.prepareStatement(sql2);
//			ps2.setString(1, dto.getEmpNo());
//			ps2.setString(2, dto.getWkStart());
//			ps2.setString(3, dto.getWkEnd());
//			ps2.setString(4, dto.getPay());
//			ps2.executeUpdate();
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		return ok;
	}// insertMmeber

	/** 검색 */
	public static void selectSearchEmpNo(DefaultTableModel model, String search) {
		String empNo = Login.empNo;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			con = getConn();
			String sql = "SELECT EMP_wk.EMP_NO, NAME, EMP_POS, ID, wk_day, wk_start, wk_end, daily_wage from emp_wk "
					+ "join emp on emp.emp_no = emp_wk.emp_no where wk_day = '" + search + "' and emp.emp_no = '" +empNo + "'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

			// DefaultTableModel에 있는 데이터 지우기
			for (int i = 0; i < model.getRowCount();) {
				model.removeRow(0);
			}

			while (rs.next()) {
				Object data[] = { 
						rs.getString(1), 
						rs.getString(2), 
						rs.getString(3), 
						rs.getString(4),
						rs.getString(5),
						rs.getString(6),
						rs.getString(7),
						rs.getString(8)};

				model.addRow(data);
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	/**
	 * 회원정보 삭제 : tip: 실무에서는 회원정보를 Delete 하지 않고 탈퇴여부만 체크한다.
	 */
//	public boolean deleteEmp(String id, String pwd) {
//
//		boolean ok = false;
//		Connection con = null;
//		PreparedStatement ps = null;
//
//		try {
//			con = getConn();
//			String sql = "delete from tb_member where id=? and pwd=?";
//
//			ps = con.prepareStatement(sql);
//			ps.setString(1, id);
//			ps.setString(2, pwd);
//			int r = ps.executeUpdate(); // 실행 -> 삭제
//
//			if (r > 0)
//				ok = true; // 삭제됨;
//
//		} catch (Exception e) {
//			System.out.println(e + "-> 오류발생");
//		}
//		return ok;
//	}

	/** DB데이터 다시 불러오기(login 구현부분) */
	public boolean empSelectLogin(EmpDTO dto) {

		String id = dto.getId();
		String pwd = dto.getPwd();

		boolean ok = true;

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			con = getConn();
			String sql = "select id from emp where id = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, id);

//			ps.setString(2, dto.getPwd());
			rs = ps.executeQuery();

			// DefaultTableModel에 있는 데이터 지우기
//			for (int i = 0; i < model.getRowCount();) {
//				model.removeRow(0);
//			}
			if (rs.next() == false || id.isEmpty() == true) {
				return !ok;
			} else {
				sql = "select pwd from (select pwd from emp where id = ?)";
				ps = con.prepareStatement(sql);
				ps.setString(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					if (rs.getString(1).equals(pwd)) {
						return ok;
					} else
						return !ok;
				}
			}

		} catch (SQLException e) {
			System.out.println(e + "=> userSelectAll fail");
		} finally {

			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}

		return ok;
	}
//
//	/** DB데이터 다시 불러오기 */
//	public void userSelectAll(DefaultTableModel model) {
//
//		Connection con = null;
//		PreparedStatement ps = null;
//		ResultSet rs = null;
//
//		try {
//			con = getConn();
//			String sql = "select * from tb_member order by name asc";
//			ps = con.prepareStatement(sql);
//			rs = ps.executeQuery();
//
//			// DefaultTableModel에 있는 데이터 지우기
//			for (int i = 0; i < model.getRowCount();) {
//				model.removeRow(0);
//			}
//
//			while (rs.next()) {
//				Object data[] = { 
//						rs.getString(1), 
//						rs.getString(2), 
//						rs.getString(3), 
//						rs.getString(4), 
//						rs.getString(5),
//						rs.getString(6), 
//						rs.getString(7), 
//						rs.getString(8), 
//						rs.getString(9), 
//						rs.getString(10) };
//
//				model.addRow(data);
//			}
//
//		} catch (SQLException e) {
//			System.out.println(e + "=> userSelectAll fail");
//		} finally {
//
//			if (rs != null)
//				try {
//					rs.close();
//				} catch (SQLException e2) {
//					// TODO Auto-generated catch block
//					e2.printStackTrace();
//				}
//			if (ps != null)
//				try {
//					ps.close();
//				} catch (SQLException e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}
//			if (con != null)
//				try {
//					con.close();
//				} catch (SQLException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//		}
//	}

}