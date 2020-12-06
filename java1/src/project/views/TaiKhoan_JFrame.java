package project.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.daos.TaiKhoanDAO;
import project.dtos.TaiKhoanDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPasswordField;

public class TaiKhoan_JFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwordOld;
	private JPasswordField passwordNew;
	private JPasswordField passwordPreNew;
	private static String role;
	private static String maNV;
	private static JButton btnXacNhan, btnHuy;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TaiKhoan_JFrame frame = new TaiKhoan_JFrame(role, maNV);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TaiKhoan_JFrame(String role, String maNV) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 311);
		setLocationRelativeTo(null);
		TaiKhoan_JFrame.role = role;
		TaiKhoan_JFrame.maNV = maNV;
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTieuDe = new JLabel("Thay Đổi Mật Khẩu");
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTieuDe.setBounds(103, 11, 230, 36);
		contentPane.add(lblTieuDe);

		JLabel lblPWO = new JLabel("Mật khẩu cũ:");
		lblPWO.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPWO.setBounds(10, 58, 98, 28);
		contentPane.add(lblPWO);

		JLabel lblNewMK = new JLabel("Mật khẩu mới:");
		lblNewMK.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewMK.setBounds(10, 109, 108, 22);
		contentPane.add(lblNewMK);

		JLabel lblNewPrePW = new JLabel("Nhập lại mật khẩu:");
		lblNewPrePW.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewPrePW.setBounds(10, 160, 139, 22);
		contentPane.add(lblNewPrePW);

		btnXacNhan = new JButton("Xác Nhận");
		btnXacNhan.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXacNhan.setBounds(143, 222, 108, 39);
		contentPane.add(btnXacNhan);

		btnHuy = new JButton("Thoát");
		btnHuy.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnHuy.setBounds(275, 222, 108, 39);
		contentPane.add(btnHuy);

		passwordOld = new JPasswordField();
		passwordOld.setBounds(143, 58, 240, 28);
		contentPane.add(passwordOld);

		passwordNew = new JPasswordField();
		passwordNew.setBounds(143, 108, 240, 28);
		contentPane.add(passwordNew);

		passwordPreNew = new JPasswordField();
		passwordPreNew.setBounds(143, 163, 240, 28);
		contentPane.add(passwordPreNew);
		btnXacNhan.addActionListener(this);
		btnHuy.addActionListener(this);
	}

	public boolean kiemTra() throws ClassNotFoundException, SQLException {
		char[] c = passwordOld.getPassword();
		char[] n = passwordNew.getPassword();
		char[] p = passwordPreNew.getPassword();

		String mkc = "";
		String mkm = "";
		String mkpn = "";

		for (int i = 0; i < c.length; i++) {
			mkc += c[i];
		}
		for (int i = 0; i < n.length; i++) {
			mkm += n[i];
		}
		for (int i = 0; i < p.length; i++) {
			mkpn += p[i];
		}
		if (mkc.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ không được để trống");
			passwordOld.selectAll();
			passwordOld.requestFocus();
			return false;
		} else if (mkm.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới không được để trống");
			passwordNew.selectAll();
			passwordNew.requestFocus();
			return false;
		} else if (mkpn.matches("\\s*")) {
			JOptionPane.showMessageDialog(this, "Mật khẩu mới nhập lại không được để trống");
			passwordPreNew.selectAll();
			passwordPreNew.requestFocus();
			return false;
		}
		TaiKhoanDAO dao = new TaiKhoanDAO();
		TaiKhoanDTO dto = dao.layThongTinTaiKhoan(maNV);
		if (!dto.getMatKhau().equals(mkc)) {
			JOptionPane.showMessageDialog(this, "Mật khẩu cũ sai");
			passwordOld.setText("");
			passwordNew.setText("");
			passwordPreNew.setText("");
			return false;
		} else {
			if (!mkm.matches("[a-zA-Z0-9]+")) {
				JOptionPane.showMessageDialog(this, "Nhập Mật khẩu mới. Mật khẩu không được có các kí tự đặc biệt!");
				passwordOld.setText("");
				passwordOld.requestFocus();
				passwordNew.setText("");
				passwordPreNew.setText("");
				return false;
			}
			else if (mkm.length() < 5) {
				JOptionPane.showMessageDialog(this, "Nhập mật khẩu mới. Mật khẩu mới phải từ 5 kí tự trở lên!");
				passwordOld.setText("");
				passwordOld.requestFocus();
				passwordNew.setText("");
				passwordPreNew.setText("");
				return false;
			}
			else if (mkc.equals(mkm)) {
				JOptionPane.showMessageDialog(this, "Mật khẩu mới không được giống mật khẩu cũ!");
				passwordOld.setText("");
				passwordOld.requestFocus();
				passwordNew.setText("");
				passwordPreNew.setText("");
				return false;
			}
			else if (!mkm.equals(mkpn)) {
				JOptionPane.showMessageDialog(this, "Nhập lại mật khẩu mới không khớp!");
				passwordOld.setText("");
				passwordOld.requestFocus();
				passwordNew.setText("");
				passwordPreNew.setText("");
				return false;
			}
		}
		return true;
	}

	public void getLamSach() {
		passwordOld.setText("");
		passwordNew.setText("");
		passwordPreNew.setText("");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(btnXacNhan)) {
			try {
				if (kiemTra() == true) {
					char[] n = passwordNew.getPassword();
					String mkm = "";
					for (int i = 0; i < n.length; i++) {
						mkm += n[i];
					}
					TaiKhoanDAO dao = new TaiKhoanDAO();
					dao.doiMatKhau(maNV, mkm);
					JOptionPane.showMessageDialog(null, "Cập nhật mật khẩu thành công !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
					getLamSach();
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj.equals(btnHuy)) {
			dispose();
		}

	}
}
