package project.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.daos.KhachHangDAO;
import project.dtos.KhachHangDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JTextField;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class GiaoDienTimKiemKhachHang extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKH;
	private JTextField txtSDT;

	private JButton btnTimKiem, btnThoat;

	private String role, maNV;

	public GiaoDienTimKiemKhachHang(String role, String maNV) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 417, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel label = new JLabel("Tên Khách Hàng:");
		label.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label.setBounds(10, 84, 116, 23);
		contentPane.add(label);

		txtKH = new JTextField();
		txtKH.setColumns(10);
		txtKH.setBounds(144, 80, 240, 34);
		contentPane.add(txtKH);

		JLabel lblTmKimKhch = new JLabel("Tìm Kiếm Khách Hàng");
		lblTmKimKhch.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTmKimKhch.setBounds(110, 11, 260, 39);
		contentPane.add(lblTmKimKhch);

		JLabel lblSinThoi = new JLabel("Số Điện Thoại:");
		lblSinThoi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSinThoi.setBounds(10, 156, 104, 23);
		contentPane.add(lblSinThoi);

		txtSDT = new JTextField();
		txtSDT.setColumns(10);
		txtSDT.setBounds(144, 152, 240, 34);
		contentPane.add(txtSDT);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(144, 224, 104, 46);
		contentPane.add(btnTimKiem);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(294, 224, 89, 46);
		contentPane.add(btnThoat);

		this.role = role;
		this.maNV = maNV;

		btnTimKiem.addActionListener(this);
		btnThoat.addActionListener(this);
	}

	public boolean kiemTra() {
		String sdt = txtSDT.getText();

		String dtreg = "\\d{9,11}";
		if (sdt.equals("")) {
			return true;
		} else if (!sdt.matches(dtreg)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải 9-10 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnThoat)) {
			dispose();
		} else if (o.equals(btnTimKiem)) {
			if (kiemTra() == true) {
				try {
					String ten = txtKH.getText();
					String sdt = txtSDT.getText();

					KhachHangDAO dao = new KhachHangDAO();
					ArrayList<KhachHangDTO> list = null;

					list = (ArrayList<KhachHangDTO>) dao.timKiemKhachHangByDK(ten, sdt);

					if (!list.isEmpty()) {
						KhachHang_JFrame khachHang_JFrame = new KhachHang_JFrame(role, maNV);
						khachHang_JFrame.setVisible(true);
						khachHang_JFrame.setLocationRelativeTo(null);
						khachHang_JFrame.getTimKiemKhachHang(list);
						dispose();
					} else
						JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin Khách hàng !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
	}
}
