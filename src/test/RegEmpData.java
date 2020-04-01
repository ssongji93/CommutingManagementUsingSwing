package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegEmpData {
	
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@localhost:1521:XE";

	private static final String USER = "testdb"; // DB ID
	private static final String PASS = "1234"; // DB 패스워드
	EmpList empList;

	public RegEmpData() {

	}

	public RegEmpData(EmpList empList) {
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
	
	/**사원번호 존재유무 체크 */
	public String checkEmpNo() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String countEmpNo = "";
		

		try {
			con = getConn();
			String sql = "select count(emp_pos) from emp";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while(rs.next()) {
				countEmpNo = rs.getString(1);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return countEmpNo;
		
	}
	
	/**DB에서 사원번호 가져오기 */
	public String getEmpNo() {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String getEmpNo = "";
		
		
		try {
			con = getConn();
			String sql = "select emp_no from emp order by emp_no desc";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				getEmpNo = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return getEmpNo;
	}
	
	/**DB에서 아이디 존재유무 체크 */
	public String checkEmpId(String id) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		String checkEmpId = "";
		
		
		try {
			con = getConn();
			String sql = "select count(id) from emp where id = '" + id + "'";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				checkEmpId = rs.getString(1);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return checkEmpId;
	}

}
