package project.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import project.daos.HoaDonDAO;
import project.daos.KhachHangDAO;
import project.dtos.HoaDonDTO;
import project.dtos.KhachHangDTO;

import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.border.TitledBorder;

public class KhachHang_JFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static String role;
	public static String maNV;
	public static JTextField txMaKH;
	public static JTextField txHoTen;
	public static JTextField txDiachi;
	public static JTextField txSdt;
	public static JTextField txEmail;
	public static JTextField txTimKiem;
	public static JButton btThem;
	public static JButton btXoa, btnXoaTrang;
	public static JButton btCapnhap;
	public static JButton btTimkiem;
	public static JTable table;
	public static DefaultTableModel dtm;
	public static JTextField txNgKT;

	public static JPanel pnNorth;
	private JPanel panel;
	private JButton btnTim;
	private JButton btnThoat;

	public KhachHang_JFrame(String role, String maNV) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("image/Users-icon.png"));
		setTitle("Danh Sách Khách Hàng");
		setSize(1200, 680);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		buildUI();
		dtm = (DefaultTableModel) table.getModel();
		getKhachHang();

		KhachHang_JFrame.role = role;
		KhachHang_JFrame.maNV = maNV;
	}

	private void buildUI() {
		getContentPane().setLayout(null);
		// NORTH
		pnNorth = new JPanel();
		pnNorth.setBackground(new Color(240, 230, 140));
		pnNorth.setBounds(0, 0, 1200, 652);

		getContentPane().add(pnNorth);
		pnNorth.setPreferredSize(new Dimension(0, 250));
		pnNorth.setLayout(null);
		JLabel lbtitle = new JLabel("THÔNG TIN KHÁCH HÀNG");
		lbtitle.setForeground(Color.BLUE);
		Font ft = new Font("arial", Font.BOLD, 20);
		lbtitle.setFont(ft);

		JLabel lbMaKH = new JLabel("Mã khách hàng :");
		lbMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbHoTen = new JLabel("Họ và tên :");
		lbHoTen.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbDiaChi = new JLabel("Địa chỉ :");
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbSdt = new JLabel("Số điện thoại :");
		lbSdt.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		JLabel lbTimKiem = new JLabel("Tìm kiếm :");
		lbTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));

		txMaKH = new JTextField();
		txMaKH.setEnabled(false);
		txHoTen = new JTextField();
		txDiachi = new JTextField();
		txSdt = new JTextField();
		txEmail = new JTextField();
		txTimKiem = new JTextField();

		btThem = new JButton("Thêm");
		btThem.setBackground(Color.GREEN);
		btThem.setIcon(new ImageIcon("image/iconAdd.png"));
		btThem.setFont(new Font("Tahoma", Font.BOLD, 11));

		btCapnhap = new JButton("Cập nhật");
		btCapnhap.setBackground(Color.GREEN);
		btCapnhap.setIcon(new ImageIcon("image/update.png"));
		btCapnhap.setFont(new Font("Tahoma", Font.BOLD, 11));

		btTimkiem = new JButton("Tìm kiếm");
		btTimkiem.setBackground(Color.GREEN);
		btTimkiem.setIcon(new ImageIcon("image/tim.png"));
		btTimkiem.setFont(new Font("Tahoma", Font.BOLD, 11));

		btXoa = new JButton("Xóa");
		btXoa.setBackground(Color.GREEN);
		btXoa.setIcon(new ImageIcon("image/delete.png"));
		btXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		btXoa.setVisible(false);

		lbMaKH.setBounds(72, 37, 100, 50);
		txMaKH.setBounds(204, 49, 295, 35);
		lbHoTen.setBounds(72, 115, 87, 43);
		txHoTen.setBounds(204, 115, 295, 35);
		lbDiaChi.setBounds(72, 176, 65, 43);
		txDiachi.setBounds(204, 188, 295, 38);
		lbSdt.setBounds(645, 41, 87, 43);
		txSdt.setBounds(770, 45, 395, 35);
		lbEmail.setBounds(645, 115, 60, 35);
		txEmail.setBounds(770, 115, 395, 35);
		lbtitle.setBounds(423, 11, 256, 43);
		lbTimKiem.setBounds(72, 248, 65, 50);
		txTimKiem.setBounds(204, 256, 161, 35);
		btThem.setBounds(770, 230, 123, 35);
		btXoa.setBounds(645, 230, 115, 35);
		btCapnhap.setBounds(1042, 230, 123, 35);
		btTimkiem.setBounds(375, 256, 124, 35);

		pnNorth.add(lbMaKH);
		pnNorth.add(lbHoTen);
		pnNorth.add(lbDiaChi);
		pnNorth.add(lbSdt);
		pnNorth.add(lbEmail);
		pnNorth.add(lbTimKiem);
		pnNorth.add(lbtitle);
		pnNorth.add(txHoTen);
		pnNorth.add(txDiachi);
		pnNorth.add(txEmail);
		pnNorth.add(txSdt);
		pnNorth.add(txMaKH);
		pnNorth.add(txTimKiem);
		pnNorth.add(btThem);
		pnNorth.add(btTimkiem);
		pnNorth.add(btXoa);
		pnNorth.add(btCapnhap);

		// Chèn icon vào nút quay lại

		txNgKT = new JTextField();
		txNgKT.setBounds(770, 184, 395, 35);
		pnNorth.add(txNgKT);
		txNgKT.setEditable(false);
		txNgKT.setColumns(10);

		JLabel lbNgKT = new JLabel("Ngày Tạo :");
		lbNgKT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lbNgKT.setBounds(645, 179, 65, 37);
		pnNorth.add(lbNgKT);

		btnXoaTrang = new JButton("Xóa Trắng");
		btnXoaTrang.setBackground(Color.GREEN);
		btnXoaTrang.setIcon(new ImageIcon("image/tay.png"));
		btnXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 11));

		btnXoaTrang.setBounds(903, 230, 129, 35);
		pnNorth.add(btnXoaTrang);
		table = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "Họ và Tên", "Địa chỉ", "Số điện thoại", "Email", "Ngày lập" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setForeground(new Color(0, 0, 0));
		table.setFont(new Font("Tahoma", Font.PLAIN, 11));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table.getSelectedRow();
				String sdt = (String) dtm.getValueAt(row, 3);
				KhachHangDAO dao = new KhachHangDAO();
				// btThem.setVisible(false);
				try {
					KhachHangDTO dto = dao.layThongTinKhachHang(sdt);
					txMaKH.setText(dto.getMaKH());
					txHoTen.setText(dto.getTenKH());
					txDiachi.setText(dto.getDiaChi());
					txSdt.setText(sdt);
					txEmail.setText(dto.getEmail());
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					txNgKT.setText(sdf.format(dto.getDateCreate()));
					txMaKH.setEditable(false);
				} catch (ClassNotFoundException | SQLException ex) {
					// TODO Auto-generated catch block
					ex.printStackTrace();
				}
			}
		});
		// table.getTableHeader().setFont(new Font ("Tahoma",Font.BOLD,11));

		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setEnabled(false);
		table.setSelectionBackground(new Color(232, 57, 95));
		JScrollPane s = new JScrollPane(table);
		s.setBounds(27, 376, 1143, 265);
		pnNorth.add(s);

		panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBorder(new TitledBorder(null, "Thanh t\u00ECm ki\u1EBFm", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		panel.setToolTipText("");
		panel.setBounds(645, 276, 520, 89);
		pnNorth.add(panel);
		panel.setLayout(null);

		btnTim = new JButton("Tìm Kiếm");
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTim.setBounds(89, 22, 124, 40);
		panel.add(btnTim);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(326, 22, 123, 40);
		panel.add(btnThoat);

		setBackground(new Color(0, 0, 0));

		btThem.addActionListener(this);
		btXoa.addActionListener(this);
		btCapnhap.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btTimkiem.addActionListener(this);
		btnTim.addActionListener(this);
		btnThoat.addActionListener(this);
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
		dtm.setRowCount(0);
		for (KhachHangDTO khachHangDTO : list) {
			dtm.addRow(khachHangDTO.toVector());
		}
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
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
		dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (KhachHangDTO khachHangDTO : list) {
			dtm.addRow(khachHangDTO.toVector());
		}
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemKhachHang(ArrayList<KhachHangDTO> list1) {
		ArrayList<KhachHangDTO> list = null;
		try {
			list = list1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (KhachHangDTO khachHangDTO : list) {
			dtm.addRow(khachHangDTO.toVector());
		}
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void demSoThuTu() {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void xoaTrangKhachHang() {
		txMaKH.setEditable(true);
		txMaKH.setText("");
		txHoTen.setText("");
		txSdt.setText("");
		txDiachi.setText("");
		txEmail.setText("");
		txNgKT.setText("");
		txTimKiem.setText("");
		btThem.setVisible(true);
	}

	public boolean kiemTra() {
		String ht = txHoTen.getText();
		String dc = txDiachi.getText();
		String dt = txSdt.getText();
		String em = txEmail.getText();
		String dtreg = "\\d{9,11}";
		String htreg = "^([a-zA-Z]+)( [a-zA-Z]+)*$";
		String emreg = "^\\w+@{1}\\w+\\.\\w+(\\.\\w+)*$";

		if (ht.equals("")) {
			JOptionPane.showMessageDialog(null, "Họ Tên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txHoTen.requestFocus();
			txHoTen.selectAll();
			return false;
		} else if (dc.equals("")) {
			JOptionPane.showMessageDialog(null, "Địa chỉ không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txDiachi.requestFocus();
			txDiachi.selectAll();
			return false;
		} else if (dt.equals("")) {
			JOptionPane.showMessageDialog(null, "Số Điện Thoại không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSdt.requestFocus();
			txSdt.selectAll();
			return false;
		} else if (em.equals("")) {
			JOptionPane.showMessageDialog(null, "Email không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txEmail.requestFocus();
			txEmail.selectAll();
			return false;
		} else if (!ht.matches(htreg)) {
			JOptionPane.showMessageDialog(null,
					"Họ và Tên không được có chữ số !!!\n"
							+ "Ví dụ: NGUYEN VAN A hoặc Nguyen Van A được viết không dấu !!!",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txHoTen.requestFocus();
			txHoTen.selectAll();
			return false;
		} else if (!dt.matches(dtreg)) {
			JOptionPane.showMessageDialog(null, "Số điện thoại phải 9-11 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSdt.requestFocus();
			txSdt.selectAll();
			return false;
		} else if (!em.matches(emreg)) {
			JOptionPane.showMessageDialog(null, "Email không hợp lệ !!!\n" + "Ví dụ: nguyenvana@gmail.com !!!",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txEmail.requestFocus();
			txEmail.selectAll();
			return false;
		}

		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(btThem)) {
			try {
				String maKH = txMaKH.getText();
				if (!maKH.equals("")) {
					JOptionPane.showMessageDialog(null, "Không thể thêm Khách Hàng !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					String ht = txHoTen.getText();
					String dc = txDiachi.getText();
					String dt = txSdt.getText();
					String em = txEmail.getText();
					Timestamp dateCreate = new Timestamp(System.currentTimeMillis());
					KhachHangDAO dao = new KhachHangDAO();
					KhachHangDTO dto = new KhachHangDTO(maKH, ht, dc, dt, em, dateCreate);

					if (dao.themKhachHang(dto)) {
						JOptionPane.showMessageDialog(null, "Thêm Khách hàng thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						Vector<String> v = new Vector<>();
						v.add(dtm.getRowCount() + 1 + "");
						v.add(ht);
						v.add(dc);
						v.add(dt);
						v.add(em);
						v.add(dateCreate.toString());
						dtm.addRow(v);
						xoaTrangKhachHang();
					} else {
						JOptionPane.showMessageDialog(null, "Thêm Khách hàng thất bại !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {

			}
		} else if (obj.equals(btCapnhap)) {
			String maKH = txMaKH.getText();
			String hoTen = txHoTen.getText();
			String diaChi = txDiachi.getText();
			String phone = txSdt.getText();
			String email = txEmail.getText();
			if (table.getSelectedRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Cần phải chọn khách hàng để cập nhật thông tin !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else if (kiemTra() == true) {
				KhachHangDAO dao = new KhachHangDAO();
				KhachHangDTO dto = new KhachHangDTO(maKH, hoTen, diaChi, phone, email, null);
				try {
					if (dao.capnhatKhachHang(dto)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Khách hàng thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						int row = table.getSelectedRow();
						table.setValueAt(dto.getTenKH(), row, 1);
						table.setValueAt(dto.getDiaChi(), row, 2);
						table.setValueAt(dto.getPhone(), row, 3);
						table.setValueAt(dto.getEmail(), row, 4);
						xoaTrangKhachHang();
					}
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		} else if (obj.equals(btTimkiem)) {

			String phone = txTimKiem.getText();

			getTimKiem(phone);
		} else if (obj.equals(btXoa)) {
			String maKH = txMaKH.getText();
			if (maKH.equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn cần chon Khách Hàng muốn xóa !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else {
				HoaDonDAO daoHD = new HoaDonDAO();
				List<HoaDonDTO> dtoHD = null;
				try {
					dtoHD = daoHD.kiemtraHoaDontrongKhachHang(maKH);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int ntc = 0;
				if (dtoHD == null) {
					ntc = JOptionPane.showConfirmDialog(null, "Bạn có muốn XÓA Khách Hàng này không", "Xóa",
							JOptionPane.YES_NO_OPTION);
				} else {
					ntc = JOptionPane.showConfirmDialog(null,
							"Bạn có muốn XÓA Khách Hàng này không ?\n" + "Khách Hàng này đã từng mua hàng tại đây",
							"Xóa", JOptionPane.YES_NO_OPTION);
				}
				if (ntc == JOptionPane.YES_OPTION) {
					KhachHangDAO daoKH = new KhachHangDAO();
					try {
						daoHD.xoatatcaChiTietHoaDon(dtoHD);
						daoHD.xoatatcaHoaDontrongKhachHang(maKH);
						if (daoKH.xoaKhachHang(maKH)) {
							JOptionPane.showMessageDialog(null, "Xóa Khách hàng thành công !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
							int row = table.getSelectedRow();
							dtm.removeRow(row);
							demSoThuTu();
							xoaTrangKhachHang();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa Khách hàng thất bại !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		} else if (obj.equals(btnXoaTrang)) {
			xoaTrangKhachHang();
			getKhachHang();

		} else if (obj.equals(btnThoat)) {
			dispose();
		} else if (obj.equals(btnTim)) {
			dispose();
			GiaoDienTimKiemKhachHang khachHang = new GiaoDienTimKiemKhachHang(role, maNV);
			khachHang.setVisible(true);
			khachHang.setLocationRelativeTo(null);
		}
	}
}
