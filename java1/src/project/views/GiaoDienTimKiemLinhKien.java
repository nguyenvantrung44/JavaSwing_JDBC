package project.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import project.daos.NhaCungCapDAO;
import project.daos.SanPhamDAO;
import project.dtos.NhaCungCapDTO;
import project.dtos.SanPhamDTO;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Toolkit;
import java.awt.Color;

public class GiaoDienTimKiemLinhKien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtLinhKien;
	private JComboBox<String> cboNCC, cbbGiaTien;
	private JButton btnTimKiem, btnThoat;
	private JLabel lblNewLabel_1;
	private String role, maNV;

	public GiaoDienTimKiemLinhKien(String role, String maNV) {
		setTitle("Tìm kiếm linh kiện");
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/tim.png"));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 460, 388);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTmKimLinh = new JLabel("Tìm Kiếm Linh Kiện\r\n");
		lblTmKimLinh.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTmKimLinh.setBounds(136, 11, 200, 39);
		contentPane.add(lblTmKimLinh);

		JLabel lblTnLinhKin = new JLabel("Tên Linh Kiện:");
		lblTnLinhKin.setFont(new Font("Dialog", Font.PLAIN, 14));
		lblTnLinhKin.setBounds(10, 78, 103, 23);
		contentPane.add(lblTnLinhKin);

		txtLinhKien = new JTextField();
		txtLinhKien.setColumns(10);
		txtLinhKien.setBounds(138, 81, 249, 31);
		contentPane.add(txtLinhKien);

		JLabel label_3 = new JLabel("Nhà Cung Cấp:");
		label_3.setFont(new Font("Tahoma", Font.PLAIN, 14));
		label_3.setBounds(10, 145, 102, 22);
		contentPane.add(label_3);

		cboNCC = new JComboBox<String>();
		cboNCC.setBounds(136, 147, 251, 31);
		contentPane.add(cboNCC);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(136, 279, 108, 39);
		contentPane.add(btnTimKiem);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(279, 280, 108, 38);
		contentPane.add(btnThoat);

		cbbGiaTien = new JComboBox<String>();
		cbbGiaTien.setBounds(136, 213, 251, 31);
		contentPane.add(cbbGiaTien);

		cbbGiaTien.addItem("0000000 - 1000000");
		cbbGiaTien.addItem("1000000 - 3000000");
		cbbGiaTien.addItem("3000000 - 5000000");
		cbbGiaTien.addItem("5000000 - 70000000");
		cbbGiaTien.addItem("7000000 - 10000000");

		JLabel lblNewLabel = new JLabel("Giá Tiền:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 221, 91, 23);
		contentPane.add(lblNewLabel);

		lblNewLabel_1 = new JLabel("(VND)");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(398, 213, 46, 31);
		contentPane.add(lblNewLabel_1);

		btnTimKiem.addActionListener(this);
		btnThoat.addActionListener(this);

		this.role = role;
		this.maNV = maNV;

		addtoComboBoxNhaCungCap();

	}

	private void addtoComboBoxNhaCungCap() {
		NhaCungCapDAO dao = new NhaCungCapDAO();
		try {
			ArrayList<NhaCungCapDTO> list = (ArrayList<NhaCungCapDTO>) dao.dsNhaCungCap();
			cboNCC.removeAllItems();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "" });
			for (NhaCungCapDTO nhaCungCapDTO : list) {
				if (model.getIndexOf(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC()) == -1) {
					model.addElement(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC());
					cboNCC.addItem(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();

		if (obj.equals(btnTimKiem)) {
			try {

				SanPhamDAO dao = new SanPhamDAO();
				String tenlk = txtLinhKien.getText();
				String[] mancc = cboNCC.getSelectedItem().toString().split("-");
				String[] tien = cbbGiaTien.getSelectedItem().toString().split("-");

				double giaMin = Double.parseDouble(tien[0]);
				double giaMax = Double.parseDouble(tien[1]);
				String mncc = mancc[0];
				ArrayList<SanPhamDTO> list = null;

				list = (ArrayList<SanPhamDTO>) dao.timkiemSanPham1(giaMin, giaMax, mncc, tenlk);

				if (!list.isEmpty()) {
					SanPham_JFrame sanPham_JFrame = new SanPham_JFrame(role, maNV);
					sanPham_JFrame.setVisible(true);
					sanPham_JFrame.setLocationRelativeTo(null);
					SanPham_JFrame.getTimKiem(list);
					dispose();

				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy sản phẩm !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (obj.equals(btnThoat)) {
			dispose();
		}
	}
}
