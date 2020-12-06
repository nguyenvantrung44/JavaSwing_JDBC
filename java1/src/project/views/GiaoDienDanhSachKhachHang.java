package project.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import project.daos.KhachHangDAO;
import project.dtos.KhachHangDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Font;

public class GiaoDienDanhSachKhachHang extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField txtsdt;
	private JButton btntimKiem, btnLapHD;
	private JLabel lblsdt;
	private JButton btnLoadKH;
	private String maNV, role;
	private JTextField txtHoTen;
	private JTextField txtDiaChi;
	private JTextField txtSDT;
	private JTextField txtEmail;
	public static KhachHangDAO dao;
	public static KhachHangDTO dto;

//	public static void main(String[] args) {
//		new GiaoDienDanhSachKhachHang("admin", "MNV001").setVisible(true);
//	}

	public GiaoDienDanhSachKhachHang(String role, String maNV) {
		this.maNV = maNV;
		this.role = role;
		setTitle("Giao Diện Khách Hàng");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		taoGiaoDien();
		getKhachHang();
	}

	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] header = "Mã Khách Hàng; Tên Khách Hàng; Số Điện Thoại ; Địa chỉ".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		contentPane.add(scrollPane);
		scrollPane.setBounds(10, 74, 815, 446);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh T\u00ECm Ki\u1EBFm ", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(10, 531, 596, 106);
		contentPane.add(panel);
		panel.setLayout(null);

		lblsdt = new JLabel("Số Điện Thoại:");
		lblsdt.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblsdt.setBounds(10, 41, 95, 25);
		panel.add(lblsdt);

		txtsdt = new JTextField();
		txtsdt.setBounds(133, 35, 255, 41);
		panel.add(txtsdt);
		txtsdt.setColumns(10);
		txtsdt.setBackground(Color.WHITE);

		btntimKiem = new JButton("Tìm kiếm");
		btntimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btntimKiem.setBounds(413, 37, 129, 39);
		panel.add(btntimKiem);
		btntimKiem.setBackground(Color.WHITE);

		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "C\u00E1c Thao T\u00E1c", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setBounds(646, 531, 528, 106);
		contentPane.add(panel_1);

		btnLapHD = new JButton("Lập hóa đơn");
		btnLapHD.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLapHD.setBounds(80, 37, 153, 39);
		panel_1.add(btnLapHD);
		btnLapHD.setBackground(Color.GREEN);

		btnLoadKH = new JButton("Load Khách Hàng");
		btnLoadKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoadKH.setBackground(Color.WHITE);
		btnLoadKH.setBounds(324, 37, 153, 39);
		panel_1.add(btnLoadKH);

		JLabel lblDanhSchKhch = new JLabel("Danh Sách Khách Hàng");
		lblDanhSchKhch.setForeground(Color.BLUE);
		lblDanhSchKhch.setFont(new Font("Arial", Font.BOLD, 30));
		lblDanhSchKhch.setBounds(213, 11, 353, 52);
		contentPane.add(lblDanhSchKhch);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panel_2.setBorder(new TitledBorder(null, "Kh\u00E1ch h\u00E0ng m\u1EDBi", TitledBorder.LEADING,
				TitledBorder.TOP, null, Color.RED));
		panel_2.setBounds(835, 67, 339, 453);
		contentPane.add(panel_2);
		panel_2.setLayout(null);

		JLabel lblTenKH = new JLabel("Họ Tên:");
		lblTenKH.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTenKH.setBounds(10, 61, 60, 23);
		panel_2.add(lblTenKH);

		JLabel lblDiaChi = new JLabel("Địa Chỉ:");
		lblDiaChi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDiaChi.setBounds(10, 145, 60, 23);
		panel_2.add(lblDiaChi);

		JLabel lblPhone = new JLabel("Số điện thoại:");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPhone.setBounds(10, 243, 84, 23);
		panel_2.add(lblPhone);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(10, 330, 46, 23);
		panel_2.add(lblEmail);

		txtHoTen = new JTextField();
		txtHoTen.setBounds(108, 59, 221, 31);
		panel_2.add(txtHoTen);
		txtHoTen.setColumns(10);

		txtDiaChi = new JTextField();
		txtDiaChi.setBounds(108, 143, 221, 31);
		panel_2.add(txtDiaChi);
		txtDiaChi.setColumns(10);

		txtSDT = new JTextField();
		txtSDT.setBounds(108, 241, 221, 31);
		panel_2.add(txtSDT);
		txtSDT.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(108, 328, 221, 31);
		panel_2.add(txtEmail);
		txtEmail.setColumns(10);

		JLabel lblTieuDe = new JLabel("Khách Hàng Mới");
		lblTieuDe.setForeground(Color.BLUE);
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblTieuDe.setBounds(79, 11, 195, 37);
		panel_2.add(lblTieuDe);

		JButton btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnXoaTrang.setBounds(140, 384, 150, 35);
		panel_2.add(btnXoaTrang);

		btntimKiem.addActionListener(this);
		btnLapHD.addActionListener(this);
		btnLoadKH.addActionListener(this);
	}

	public void getTimKiem(String phone) {
		KhachHangDAO dao = new KhachHangDAO();
		ArrayList<KhachHangDTO> list = null;
		try {
			list = (ArrayList<KhachHangDTO>) dao.timKiemKhachHang(phone);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableModel = (DefaultTableModel) table.getModel();
		tableModel.setRowCount(0);
		for (KhachHangDTO khachHangDTO : list) {
			tableModel.addRow(khachHangDTO.toVector1());
		}
	}

	public void getKhachHang() {
		KhachHangDAO dao = new KhachHangDAO();
		ArrayList<KhachHangDTO> list = null;
		try {
			list = (ArrayList<KhachHangDTO>) dao.dsKhachHang();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableModel.setRowCount(0);
		for (KhachHangDTO khachHangDTO : list) {
			tableModel.addRow(khachHangDTO.toVector1());
		}

	}

	public boolean kiemTra() {
		String ht = txtHoTen.getText();
		String htreg = "^[a-zA-Z\\sàáạã_-]{3,25}$";
		String dc = txtDiaChi.getText();
		String dt = txtSDT.getText();
		String dtreg = "\\d{9,11}";
		String em = txtEmail.getText();
		String emreg = "^\\w+@{1}\\w+\\.\\w+(\\.\\w+)*$";

		if (ht.equals("")) {
			JOptionPane.showMessageDialog(this, "Họ Tên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtHoTen.requestFocus();
			txtHoTen.selectAll();
			return false;
		} else if (dc.equals("")) {
			JOptionPane.showMessageDialog(this, "Địa chỉ không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtDiaChi.requestFocus();
			txtDiaChi.selectAll();
			return false;
		} else if (dt.equals("")) {
			JOptionPane.showMessageDialog(this, "Số Điện Thoại không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		} else if (em.equals("")) {
			JOptionPane.showMessageDialog(this, "Email không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;
		} else if (!ht.matches(htreg)) {
			JOptionPane.showMessageDialog(this,
					"Họ và Tên không được có chữ số !!!\n"
							+ "Ví dụ: NGUYEN VAN A hoặc Nguyen Van A được viết không dấu !!!",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtHoTen.requestFocus();
			txtHoTen.selectAll();
			return false;
		} else if (!dt.matches(dtreg)) {
			JOptionPane.showMessageDialog(this, "Số điện thoại phải 9-11 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtSDT.requestFocus();
			txtSDT.selectAll();
			return false;
		} else if (!em.matches(emreg)) {
			JOptionPane.showMessageDialog(this, "Email không hợp lệ !!!" + "Ví dụ: nguyenvana@gmail.com !!!",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txtEmail.requestFocus();
			txtEmail.selectAll();
			return false;

		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btntimKiem)) {
			getTimKiem(txtsdt.getText());
		} else if (obj.equals(btnLoadKH)) {
			getKhachHang();
		} else if (obj.equals(btnLapHD)) {
			String makh = "";
			String tenKH = txtHoTen.getText();
			String dc = txtDiaChi.getText();
			String sdt = txtSDT.getText();
			String mail = txtEmail.getText();
			int row = table.getSelectedRow();
			if (row != -1) {
				String maKH = (String) table.getValueAt(row, 0);

				ChiTietHoaDon_JFrame.panel_1.removeAll();
				ChiTietHoaDon_JFrame.panel_1.revalidate();
				ChiTietHoaDon_JFrame.panel_1.repaint();

				new ChiTietHoaDon_JFrame(role, maNV, maKH);
				FrmTONG.contentPane.removeAll();
				FrmTONG.contentPane.add(ChiTietHoaDon_JFrame.panel_1);
				FrmTONG.contentPane.revalidate();
				FrmTONG.contentPane.repaint();

			} else if (kiemTra() == true) {
				Timestamp dateCreate = new Timestamp(System.currentTimeMillis());
				dao = new KhachHangDAO();
				KhachHangDTO dto = new KhachHangDTO(makh, tenKH, dc, sdt, mail, dateCreate);
				try {
					if (dao.themKhachHang(dto)) {
						new ChiTietHoaDon_JFrame(role, maNV,dao.timKhachHang());
						FrmTONG.contentPane.removeAll();
						FrmTONG.contentPane.add(ChiTietHoaDon_JFrame.panel_1);
						FrmTONG.contentPane.revalidate();
						FrmTONG.contentPane.repaint();

					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block

				}
			}
		}
	}
}
