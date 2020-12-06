package project.views;

import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.text.Keymap;

import project.daos.LoginDAO;
import project.dtos.LoginDTO;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import java.awt.Toolkit;
import java.awt.event.KeyEvent;

public class Login_JFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;
	private JTextField txTaikhoan;
	private JPasswordField pfmatkhau;
	private JButton btDangnhap;
	private FrmTONG frmTong;

	public Login_JFrame() {
		getContentPane().setBackground(new Color(238, 232, 170));
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/background.jpg"));
		setTitle("Đăng nhập");
		setSize(660, 323);
		setLocationRelativeTo(null);
		setResizable(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
	}

	private void buildUI() {
		JLabel lbTaikhoan = new JLabel("Tài khoản :");
		lbTaikhoan.setForeground(new Color(0, 0, 0));
		lbTaikhoan.setFont(new Font("Tahoma", Font.BOLD, 16));
		JLabel lbMatkhau = new JLabel("Mật khẩu  :");
		lbMatkhau.setForeground(new Color(0, 0, 0));
		lbMatkhau.setFont(new Font("Tahoma", Font.BOLD, 16));

		txTaikhoan = new JTextField();
		pfmatkhau = new JPasswordField();
		btDangnhap = new JButton("Đăng nhập");
		btDangnhap.setFont(new Font("Tahoma", Font.BOLD, 16));
		btDangnhap.setIcon(new ImageIcon("image/logindn.png"));
		btDangnhap.setBackground(Color.WHITE);
		btDangnhap.setForeground(Color.BLACK);

		// System.out.println("x: "+img1.getSize().width+ "y:"+ img1.getSize().height);

		lbTaikhoan.setBounds(308, 87, 100, 40);
		txTaikhoan.setBounds(418, 92, 215, 35);
		lbMatkhau.setBounds(308, 153, 100, 40);
		pfmatkhau.setBounds(418, 158, 215, 35);
		btDangnhap.setBounds(418, 213, 215, 40);

		getContentPane().add(lbTaikhoan);
		getContentPane().add(lbMatkhau);
		getContentPane().add(txTaikhoan);
		getContentPane().add(pfmatkhau);
		getContentPane().add(btDangnhap);
		getContentPane().setLayout(null);

		JLabel lblTieuDe = new JLabel("ĐĂNG NHẬP");
		lblTieuDe.setForeground(new Color(255, 0, 0));
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 24));
		lblTieuDe.setBounds(456, 30, 148, 40);

		getContentPane().add(lblTieuDe);

		JLabel lblNewLabel = new JLabel("");

		lblNewLabel.setIcon(new ImageIcon("image/loggin.png"));
		lblNewLabel.setBounds(0, 0, 309, 284);
		getContentPane().add(lblNewLabel);

		Keymap keymap = pfmatkhau.getKeymap();
		KeyStroke keystroke = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false);
		keymap.removeKeyStrokeBinding(keystroke);
		this.getRootPane().setDefaultButton(btDangnhap);

		btDangnhap.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String userID = txTaikhoan.getText();
		String password = new String(pfmatkhau.getPassword());
		LoginDTO dto;
		if (userID.equals("")) {
			JOptionPane.showMessageDialog(this, "Tên tài khoản không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTaikhoan.requestFocus();
			txTaikhoan.selectAll();
		} else if (password.equals("")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			pfmatkhau.requestFocus();
			pfmatkhau.selectAll();
		} else {
			LoginDAO dao = new LoginDAO();
			try {
				dto = dao.checkLogin(userID, password);
				if (dto == null) {
					JOptionPane.showMessageDialog(this, "Tài khoản hoặc mặt khẩu không đúng !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
					pfmatkhau.setText("");
					pfmatkhau.requestFocus();
				} else {
					if (dto.getRole().equals("admin") || dto.getRole().equals("ban hang")) {
						frmTong = new FrmTONG(dto.getRole(), dto.getMaNV());
						frmTong.pack();
						frmTong.setLocationRelativeTo(null);
						frmTong.setVisible(true);

						dispose();
//					}
//					else if(dto.getRole().equals("ban hang")) {
//						frmTong = new FrmTONG(dto.getRole(), dto.getMaNV());
//						frmTong.pack();
//						frmTong.setLocationRelativeTo(null);
//						FrmTONG.mntmLinhKien.setVisible(false);
//						FrmTONG.mnThongKe.setVisible(false);
//						FrmTONG.mntmLinhKien.setVisible(false);
//						FrmTONG.mntmNCC.setVisible(false);
//						FrmTONG.mntmDonViTinh.setVisible(false);
//						FrmTONG.mntmQuocGia.setVisible(false);
//						frmTong.setVisible(true);
//						dispose();
//					}
					}	
				}
			} catch (ClassNotFoundException | SQLException e1) {
				JOptionPane.showMessageDialog(this, "Không tồn tại Tài Khoản !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			}
		}

	}
}
