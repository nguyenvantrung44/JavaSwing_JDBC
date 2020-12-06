package project.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.daos.DonViTinhDAO;
import project.dtos.DonViTinhDTO;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GiaoDienQuanLyDonViTinh extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static String role;
	public static String maNV;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel panel;
	private JLabel lblthongTinDVT;
	private JLabel lblmaDVT;
	private JLabel lbltenDVT;
	private JPanel panel_1;
	private JButton btnthem;
	private JButton btncapNhat;
	private JButton btnxoaRong;
	private JTextField txtmaDVT;
	private JTextField txttenDVT;

	public GiaoDienQuanLyDonViTinh(String role, String maNV) {
		setTitle("Giao Diện Quản Lý Đơn Vị Tính");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1133, 700);
		setLocationRelativeTo(null);
		taoGiaoDien();

		tableModel = (DefaultTableModel) table.getModel();
		getDonViTinh();

		GiaoDienQuanLyDonViTinh.role = role;
		GiaoDienQuanLyDonViTinh.maNV = maNV;
	}

	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] header = "Mã Quốc Gia; Tên Quốc Gia".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(
				table = new JTable(new DefaultTableModel(new Object[][] {}, new String[] {
						"M\u00E3 \u0110\u01A1n V\u1ECB T\u00EDnh", "T\u00EAn \u0110\u01A1n V\u1ECB T\u00EDnh" }) {
					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;
					boolean[] columnEditables = new boolean[] { false, false };

					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		table.getTableHeader().setEnabled(false);

		scrollPane.setBounds(10, 10, 562, 640);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(582, 10, 512, 640);
		contentPane.add(panel);
		panel.setLayout(null);

		lblthongTinDVT = new JLabel("Thông Tin Đơn Vị Tính");
		lblthongTinDVT.setForeground(Color.BLUE);
		lblthongTinDVT.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblthongTinDVT.setBounds(138, 11, 268, 31);
		panel.add(lblthongTinDVT);

		lblmaDVT = new JLabel("Mã Đơn Vị Tính:\r\n");
		lblmaDVT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmaDVT.setBounds(73, 69, 121, 31);
		panel.add(lblmaDVT);

		lbltenDVT = new JLabel("Tên Đơn Vị Tính:");
		lbltenDVT.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenDVT.setBounds(73, 145, 121, 31);
		panel.add(lbltenDVT);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh C\u00F4ng C\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setBounds(73, 268, 416, 193);
		panel.add(panel_1);

		btnthem = new JButton("Thêm");
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBackground(Color.WHITE);
		btnthem.setBounds(245, 116, 148, 44);
		panel_1.add(btnthem);

		btncapNhat = new JButton("Cập Nhật");
		btncapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncapNhat.setBackground(Color.WHITE);
		btncapNhat.setBounds(22, 25, 148, 44);
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Xóa Rỗng");
		btnxoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoaRong.setBackground(Color.WHITE);
		btnxoaRong.setBounds(245, 25, 161, 44);
		panel_1.add(btnxoaRong);

		txtmaDVT = new JTextField();
		txtmaDVT.setEditable(false);
		txtmaDVT.setColumns(10);
		txtmaDVT.setBounds(312, 77, 177, 27);
		panel.add(txtmaDVT);

		txttenDVT = new JTextField();
		txttenDVT.setColumns(10);
		txttenDVT.setBounds(312, 149, 177, 27);
		panel.add(txttenDVT);

		txtmaDVT.setEditable(false);

		btnxoaRong.addActionListener(this);
		btnthem.addActionListener(this);
		btncapNhat.addActionListener(this);
	}

	public void getDonViTinh() {
		DonViTinhDAO dao = new DonViTinhDAO();
		ArrayList<DonViTinhDTO> list = null;
		try {
			list = (ArrayList<DonViTinhDTO>) dao.dsDonViTinh();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableModel.setRowCount(0);
		for (DonViTinhDTO donViTinhDTO : list) {
			tableModel.addRow(donViTinhDTO.toVector());
		}
	}

	public void getLamSach() {
		txtmaDVT.setText("");
		txttenDVT.setText("");
		btnthem.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		String madvt = (String) tableModel.getValueAt(row, 0);
		DonViTinhDAO dao = new DonViTinhDAO();
		//btnthem.setVisible(false);
		try {
			DonViTinhDTO dto = dao.layThongTinDonViTinh(madvt);
			txtmaDVT.setText(madvt);
			txttenDVT.setText(dto.getTenDVT());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public boolean kiemTra() {
		String ten = txttenDVT.getText();

		if (ten.equals("")) {
			JOptionPane.showMessageDialog(null, "Tên Đơn Vị Tính không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txttenDVT.requestFocus();
			txttenDVT.selectAll();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnxoaRong)) {
			getLamSach();
			getDonViTinh();
		} else if (obj.equals(btnthem)) {
			String ma = txtmaDVT.getText();
			if (!ma.equals("")) {
				JOptionPane.showMessageDialog(null, "Không thể thêm Đơn Vị Tính!!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else if (kiemTra() == true) {
				String ten = txttenDVT.getText();
				DonViTinhDAO dao = new DonViTinhDAO();
				DonViTinhDTO dto = new DonViTinhDTO(ma, ten);
				try {
					dao.themDonViTinh(dto);
					getDonViTinh();
					getLamSach();
					JOptionPane.showMessageDialog(null, "Thêm Đơn Vị Tính thành công!!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (obj.equals(btncapNhat)) {
			
			if (table.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Cần chọn Đơn Vị Tính để cập nhật!!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else if (kiemTra() == true) {
				String ma = txtmaDVT.getText();
				String ten = txttenDVT.getText();
				DonViTinhDAO dao = new DonViTinhDAO();
				DonViTinhDTO dto = new DonViTinhDTO(ma, ten);
				try {
					if (dao.capnhatDonViTinh(dto)) {
						int row = table.getSelectedRow();
						table.setValueAt(dto.getMaDVT(), row, 0);
						table.setValueAt(dto.getTenDVT(), row, 1);
						getLamSach();
						JOptionPane.showMessageDialog(null, "Cập nhật Đơn Vị Tính thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
					}
				} catch (ClassNotFoundException | SQLException e2) {
					// TODO: handle exception
					e2.printStackTrace();
				}
			}
		}

	}
}
