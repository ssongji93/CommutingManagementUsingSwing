package test;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class EmpProc extends JFrame implements ActionListener {

	
	JPanel panel, panel_1;
	JLabel vEmpNo, vPwd, vName, vEmpPos, vID, vSignUp, vHead, vWork;
	JTextField xEmpNo, xName, xId;
	JPasswordField xPwd;
	JButton btnSignUp, btnWkStart, btnWkEnd, btnClose;
	JComboBox cbEmpPos;
	String[] div = { "대표이사", "고문", "부장", "차장", "과장", "대리", "사원", "인턴" };
	

	GridBagLayout gb;
	GridBagConstraints gbc;
	EmpList mList;


	public EmpProc() { // 가입용 생성자

		createUI(); // UI작성해주는 메소드
		vWork.setEnabled(false);
		vWork.setVisible(false);
		btnWkStart.setEnabled(false);
		btnWkStart.setVisible(false);
		btnWkEnd.setEnabled(false);
		btnWkEnd.setVisible(false);

	}// 생성자
	
	/*
	public EmpProc(EmpList mList) { // 가입용 생성자

		createUI(); // UI작성해주는 메소드
		btnUpdate.setEnabled(false);
		btnUpdate.setVisible(false);
		btnDelete.setEnabled(false);
		btnDelete.setVisible(false);
		this.mList = mList;

	}// 생성자
	*/
	
	public EmpProc(String id, EmpList mList) { // 수정/삭제용 생성자
		createUI();
		vSignUp.setEnabled(false);
		vSignUp.setVisible(false);
		btnSignUp.setEnabled(false);
		btnSignUp.setVisible(false);
		
		this.mList = mList;
		
		EmpDAO dao = new EmpDAO();
		EmpDTO vMem = dao.getEmpDTO(id);
		viewData(vMem);

//		EmpDAO dao = new EmpDAO();
//		EmpDTO vMem = dao.getEmpDTO(id);
//		viewData(vMem);

	}// id를 가지고 생성
	
	
	// EmpDTO 의 회원 정보를 가지고 화면에 셋팅해주는 메소드
	private void viewData(EmpDTO vMem) {

		String empNo = vMem.getEmpNo();
		String name = vMem.getName();
		String id = vMem.getId();
		String pwd = vMem.getPwd();

		// 화면에 세팅
		xEmpNo.setText(empNo);
		xEmpNo.setEnabled(false);
		xId.setText(id);
		xId.setEnabled(false);
		xName.setText(name);
		xName.setEnabled(false);
		
	}// viewData
	

	private void createUI() {
//		frame = new JFrame();
//		super.setLocationRelativeTo(null);
//		setVisible(true);
//		super.setResizable(false);
		// frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setBounds(320, 160, 1280, 720);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 215, 0));
		panel.setForeground(new Color(255, 255, 255));
		panel.setBounds(0, 0, 300, 685);
		panel.setLayout(null);
		getContentPane().add(panel);

		vHead = new JLabel("Kakao team");
		vHead.setForeground(new Color(82, 54, 55));
		vHead.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		vHead.setBounds(47, 10, 210, 71);
		panel.add(vHead);

		panel_1 = new JPanel();
		panel_1.setForeground(new Color(255, 255, 255));
		panel_1.setBackground(new Color(255, 255, 255));
		panel_1.setBounds(299, 0, 965, 685);
		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		vSignUp = new JLabel("회원가입");
		vSignUp.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		vSignUp.setBounds(160, 60, 150, 70);
		panel_1.add(vSignUp);
		
		vWork = new JLabel("회원수정");
		vWork.setFont(new Font("함초롬돋움", Font.BOLD, 35));
		vWork.setBounds(160, 60, 150, 70);
		panel_1.add(vWork);		

		vEmpNo = new JLabel("사번");
		vEmpNo.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		vEmpNo.setBounds(160, 180, 130, 40);
		panel_1.add(vEmpNo);

		vName = new JLabel("이름");
		vName.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		vName.setBounds(160, 230, 130, 40);
		panel_1.add(vName);

		vEmpPos = new JLabel("직급");
		vEmpPos.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		vEmpPos.setBounds(160, 283, 130, 40);
		panel_1.add(vEmpPos);

		vID = new JLabel("아이디");
		vID.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		vID.setBounds(160, 330, 130, 40);
		panel_1.add(vID);

		vPwd = new JLabel("비밀번호");
		vPwd.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		vPwd.setBounds(160, 380, 130, 40);
		panel_1.add(vPwd);

		xEmpNo = new JTextField();
		xEmpNo.setBounds(320, 193, 180, 27);
		panel_1.add(xEmpNo);
		xEmpNo.setColumns(10);

		xName = new JTextField();
		xName.setColumns(10);
		xName.setBounds(320, 240, 180, 27);
		panel_1.add(xName);

		xPwd = new JPasswordField();
		xPwd.setColumns(10);
		xPwd.setBounds(320, 390, 180, 27);
		panel_1.add(xPwd);

		xId = new JTextField();
		xId.setColumns(10);
		xId.setBounds(320, 343, 180, 27);
		panel_1.add(xId);

		cbEmpPos = new JComboBox<String>(div);
		cbEmpPos.setBounds(320, 293, 180, 27);
		panel_1.add(cbEmpPos);

		btnSignUp = new JButton("가입하기");
		btnSignUp.setForeground(new Color(82, 54, 55));
		btnSignUp.setBackground(new Color(255, 215, 0));
		btnSignUp.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		btnSignUp.setBounds(320, 520, 130, 40);
		panel_1.add(btnSignUp);
		btnSignUp.addActionListener(this);
		
		btnWkStart = new JButton("수정하기");
		btnWkStart.setForeground(new Color(82, 54, 55));
		btnWkStart.setBackground(new Color(255, 215, 0));
		btnWkStart.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		btnWkStart.setBounds(160, 520, 130, 40);
		panel_1.add(btnWkStart);
		btnWkStart.addActionListener(this);
		
		btnWkEnd = new JButton("탈퇴하기");
		btnWkEnd.setForeground(new Color(82, 54, 55));
		btnWkEnd.setBackground(new Color(255, 215, 0));
		btnWkEnd.setFont(new Font("함초롬돋움", Font.BOLD, 15));
		btnWkEnd.setBounds(370, 520, 130, 40);
		panel_1.add(btnWkEnd);
		btnWkEnd.addActionListener(this);
		

//		setExtendedState(MAXIMIZED_BOTH);
		setVisible(true);
	}

	// 그리드백레이아웃에 붙이는 메소드
	private void gbAdd(JComponent c, int x, int y, int w, int h) {
		gbc.gridx = x;
		gbc.gridy = y;
		gbc.gridwidth = w;
		gbc.gridheight = h;
		gb.setConstraints(c, gbc);
		gbc.insets = new Insets(2, 2, 2, 2);
		add(c, gbc);
	}// gbAdd

	public static void main(String[] args) {

		new EmpProc();
	}

	@Override
	public void actionPerformed(ActionEvent ae) {
		if (ae.getSource() == btnSignUp) {
			insertEmp();
			System.out.println("insertEmp() 호출 종료");
		} 
//			else if (ae.getSource() == btnCancel) {
//			this.dispose(); // 창닫기 (현재창만 닫힘)
//			// system.exit(0)=> 내가 띄운 모든 창이 다 닫힘
//		} else if (ae.getSource() == btnUpdate) {
//			UpdateEmp();
//		} else if (ae.getSource() == btnDelete) {
//			// int x = JOptionPane.showConfirmDialog(this,"정말 삭제하시겠습니까?");
//			int x = JOptionPane.showConfirmDialog(this, "정말 삭제하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
//
//			if (x == JOptionPane.OK_OPTION) {
//				deleteEmp();
//			} else {
//				JOptionPane.showMessageDialog(this, "삭제를 취소하였습니다.");
//			}
//		}

		// jTable내용 갱신 메소드 호출
//		mList.jTableRefresh();

	}// actionPerformed

	/*
	private void deleteEmp() {
		String id = tfId.getText();
		String pwd = pfPwd.getText();
		if (pwd.length() == 0) { // 길이가 0이면

			JOptionPane.showMessageDialog(this, "비밀번호를 꼭 입력하세요!");
			return; // 메소드 끝
		}
		// System.out.println(mList);
		EmpDAO dao = new EmpDAO();
		boolean ok = dao.deleteEmp(id, pwd);

		if (ok) {
			JOptionPane.showMessageDialog(this, "삭제완료");
			dispose();

		} else {
			JOptionPane.showMessageDialog(this, "삭제실패");

		}

	}// deleteEmp

	private void UpdateEmp() {

		// 1. 화면의 정보를 얻는다.
		EmpDTO dto = getViewData();
		// 2. 그정보로 DB를 수정
		EmpDAO dao = new EmpDAO();
		boolean ok = dao.updateEmp(dto);

		if (ok) {
			JOptionPane.showMessageDialog(this, "수정되었습니다.");
			this.dispose();
		} else {
			JOptionPane.showMessageDialog(this, "수정실패: 값을 확인하세요");
		}
	}
	*/

	private void insertEmp() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		EmpDTO dto = getViewData();
		EmpDAO dao = new EmpDAO();
		boolean ok = dao.insertEmp(dto);

		if (ok) {

			JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
			dispose();

		} else {

			JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
		}

	}// insertEmp

	public EmpDTO getViewData() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		EmpDTO dto = new EmpDTO();
		String empNo = xEmpNo.getText();
		String name = xName.getText();
		String empPos = (String) cbEmpPos.getSelectedItem();
		String id = xId.getText();
		String pwd = xPwd.getText();

		// dto에 담는다.
		dto.setEmpNo(empNo);
		dto.setName(name);
		dto.setEmpPos(empPos);
		dto.setId(id);
		dto.setPwd(pwd);

		return dto;
	}
}// end