//이름 규칙 : 테이블명DAO , 테이블명DTO
//CRUD : Create;insert , Read;Select, Update, delete
package test;
import java.sql.*;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

//DB 처리
public class WkAndPayData {

	private static final String DRIVER
		= "oracle.jdbc.OracleDriver";
	private static final String URL
		= "jdbc:oracle:thin:@127.0.0.1:1521:XE";
	
	private static final String USER = "testdb"; //DB ID
	private static final String PASS = "1234"; //DB 패스워드
	
	public WkAndPayData() {
	}
	
	
	/**DB연결 메소드*/
	public Connection getConn(){
		Connection con = null;
		
		try {
			Class.forName(DRIVER); //1. 드라이버 로딩
			con = DriverManager.getConnection(URL,USER,PASS); //2. 드라이버 연결
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}
	
	/** 출근일 및 출근시간 insert */
	public void insertWkDayAndStart(String wkDay, String wkStartEnd) {
		String empNo = Login.empNo;
		EmpList empList = new EmpList();
		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			con = getConn();

			String sql = "insert into emp_wk(EMP_NO, WK_DAY, WK_START) " + "values('" + empNo + "', '" + wkDay + "', '"
					+ wkStartEnd + "')";

			ps = con.prepareStatement(sql);

			int r = ps.executeUpdate(); // 실행 -> 수정
			// 1~n: 성공 , 0 : 실패
			// rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 출근일 및 출근시간 데이터 가져오기 */
	public String[] getWkDay(String empNo) {
		EmpList empList = new EmpList();
		String[] wkDayAndStart = new String[2];
		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			con = getConn();

			String sql = "select wk_day, wk_start from emp_wk where emp_no = '" + empNo + "' order by wk_day desc";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

//			int r = ps.executeUpdate(); // 실행 -> 수정
//			if(r > 0)
			// 1~n: 성공 , 0 : 실패
			
			if(rs.next()) {
				wkDayAndStart[0] = rs.getString(1);
				wkDayAndStart[1] = rs.getString(2);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wkDayAndStart;
	}
	
	/** 직급에 따른 시급 데이터 가져오기 */
	public String getHourlyWage(String empPos) {	
		String hourWageDb ="";
		Connection con = null;
		PreparedStatement ps = null;

		ResultSet rs = null;

		try {
			con = getConn();

			String sql = "select hourly_wage from emp_pos where emp_pos = '" + empPos +"'";

			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();

//			int r = ps.executeUpdate(); // 실행 -> 수정
//			if(r > 0)
			// 1~n: 성공 , 0 : 실패
			
			if(rs.next()) {
				hourWageDb = rs.getString(1);
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return hourWageDb;
	}
	
	/** 퇴근시간 및 임금 업데이트 */
	public void updateWkEndAndPay(String empNo, String wkDay, String nowHourMin, String strPay) {
		EmpList empList = new EmpList();
		Connection con = null;
		PreparedStatement ps = null;
		
		ResultSet rs = null;
		
		try {
			con = getConn();
			
			String sql = "update emp_wk set wk_end = '" + nowHourMin + "', daily_wage = '" + strPay + "' "
					+ "where wk_day = '" + wkDay + "' and emp_no = '" + empNo + "'";
			
			ps = con.prepareStatement(sql);
			
			int r = ps.executeUpdate(); // 실행 -> 수정
			// 1~n: 성공 , 0 : 실패
			
			if(r > 0) {  
				System.out.println("퇴근시간 및 임금 업데이트 완료");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}
