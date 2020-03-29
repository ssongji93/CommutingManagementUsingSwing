package test;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

	JPanel panel, panel_1;
	JLabel vEmpNo, vPwd, vName, vEmpPos, vId, vSignUp, vHead;
	JTextField xEmpNo, xName, xId;
	JPasswordField xPwd;
	JButton btnSignUp, btnLog;
	JComboBox cbEmpPos;
	
	static String[] empNoAndPos;
	static String empNo, empPos;


	public Login() {
		super.setBounds(100, 100, 1280, 720);
//      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(882, 0, 392, 691);
		panel.setLayout(null);
		getContentPane().add(panel);

		vId = new JLabel("사용자 ID");
		vId.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		vId.setBounds(80, 246, 120, 38);
		vId.setHorizontalAlignment(vId.CENTER);
		panel.add(vId);

		vPwd = new JLabel("비밀번호 ");
		vPwd.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		vPwd.setBounds(80, 305, 120, 38);
		vPwd.setHorizontalAlignment(vPwd.CENTER);
		panel.add(vPwd);

		xId = new JTextField(16);
		xId.setBounds(205, 246, 120, 33);
		panel.add(xId);

		xPwd = new JPasswordField(10);
		xPwd.setBounds(205, 305, 120, 33);
		panel.add(xPwd);

		btnLog = new JButton("로그인 ");
		btnLog.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnLog.setBounds(80, 385, 120, 30);
		btnLog.addActionListener(this);
		panel.add(btnLog);

		btnSignUp = new JButton("회원가입 ");
		btnSignUp.addActionListener(null);
		btnSignUp.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		btnSignUp.setBounds(205, 385, 120, 30);
		btnSignUp.addActionListener(this);
		panel.add(btnSignUp);

		panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 215, 0));
		panel_1.setBounds(0, 0, 883, 691);
		panel_1.setLayout(null);
		getContentPane().add(panel_1);

		vHead = new JLabel("KaKao Team");
		vHead.setFont(new Font("굴림", Font.BOLD, 40));
		vHead.setBounds(301, 302, 380, 52);
		panel_1.add(vHead);

		setVisible(true);

	}

	private void loginEmp() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		EmpDTO dto = getIdPwdData();
		EmpDAO dao = new EmpDAO();
		empNoAndPos = dao.getEmpNoAndPos(dto.getId());
		empNo = empNoAndPos[0];
		empPos = empNoAndPos[1];

		boolean ok = dao.empSelectLogin(dto);

		if (ok) {
			JOptionPane.showMessageDialog(null, "로그인 되었습니다", "", JOptionPane.INFORMATION_MESSAGE);
			xId.setText("");
			xPwd.setText("");
			new EmpList(dto);
		} else {
			System.out.println("실패");
			JOptionPane.showMessageDialog(null, "로그인에 실패하였습니다.", "", JOptionPane.ERROR_MESSAGE);
			xId.setText("");
			xPwd.setText("");
		}
//		      if (ok) {
		//
//		         JOptionPane.showMessageDialog(this, "가입이 완료되었습니다.");
//		         dispose();
		//
//		      } else {
		//
//		         JOptionPane.showMessageDialog(this, "가입이 정상적으로 처리되지 않았습니다.");
//		      }
	}

	public EmpDTO getIdPwdData() {

		// 화면에서 사용자가 입력한 내용을 얻는다.
		EmpDTO dto = new EmpDTO();
		String id = xId.getText();
		String pwd = xPwd.getText();

		// dto에 담는다.
		dto.setId(id);
		dto.setPwd(pwd);

		return dto;
	}

	public static void main(String[] args) {
		new Login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnSignUp) {
//	         System.out.println("hi");
			new EmpProc();
		}
		if (e.getSource() == btnLog) {
			loginEmp();
		}

	}
}