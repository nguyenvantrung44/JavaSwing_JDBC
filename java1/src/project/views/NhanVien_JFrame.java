package project.views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import project.daos.HoaDonDAO;
import project.daos.NhanVienDAO;
import project.dtos.HoaDonDTO;
import project.dtos.NhanVienDTO;

import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.BevelBorder;
import javax.swing.JRadioButton;

public class NhanVien_JFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static String role;
	public static String maNV;
	public static JTextField txMaNV;
	public static JTextField txHoNV;
	public static JTextField txDiachi;
	public static JTextField txSdt;
	public static JTextField txEmail;
	public static JTextField txTimKiem;
	public static JCheckBox cbNu;
	public static JComboBox<String> cbbRole;

	public static JButton btThem;
	public static JButton btXoa, btXoaTrang;
	public static JButton btCapnhap;
	public static JButton btTimKiem;

	public static JTable table;
	public static JTextField txNgKT;
	private JLabel lblTen;
	public static JTextField txTenNV;
	public static DefaultTableModel dtm;
	public static JLabel lblRole;
	public static JRadioButton rdbtnTen;
	public static JRadioButton rdbtnSDT;

	public static JPanel pnNorth;

	public NhanVien_JFrame(String role, String maNV) {
		setTitle("Khách hàng");
		setSize(1200, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
		dtm = (DefaultTableModel) table.getModel();
		getNhanVien();

		NhanVien_JFrame.role = role;
		NhanVien_JFrame.maNV = maNV;
	}

	private void buildUI() {

		// NORTH
		pnNorth = new JPanel();
		pnNorth.setBackground(new Color(240, 230, 140));
		pnNorth.setBounds(0, 0, 1133, 700);
		getContentPane().add(pnNorth);
		pnNorth.setPreferredSize(new Dimension(0, 250));
		pnNorth.setLayout(null);
		JLabel lbtitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lbtitle.setForeground(Color.BLUE);
		Font ft = new Font("arial", Font.BOLD, 20);
		lbtitle.setFont(ft);

		JLabel lbMaNV = new JLabel("Mã nhân viên :");
		lbMaNV.setFont(new Font("Tahoma", Font.BOLD, 12));

		JLabel lbHoNV = new JLabel("Họ :");
		lbHoNV.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbDiaChi = new JLabel("Địa chỉ :");
		lbDiaChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbSdt = new JLabel("Số điện thoại :");
		lbSdt.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbEmail = new JLabel("Email :");
		lbEmail.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbGioiTinh = new JLabel("Giới tính :");
		lbGioiTinh.setFont(new Font("Tahoma", Font.BOLD, 12));
		JLabel lbNu = new JLabel("Nữ");
		lbNu.setFont(new Font("Tahoma", Font.BOLD, 12));

		txMaNV = new JTextField();
		txMaNV.setEnabled(false);
		txHoNV = new JTextField();
		txDiachi = new JTextField();
		txSdt = new JTextField();
		txEmail = new JTextField();
		txTimKiem = new JTextField();
		cbNu = new JCheckBox("Nữ");
		cbNu.setBackground(new Color(240, 230, 140));

		btThem = new JButton("Thêm");
		btThem.setIcon(new ImageIcon("image/add.png"));
		btThem.setFont(new Font("Tahoma", Font.BOLD, 11));

		btCapnhap = new JButton("Cập nhập");
		btCapnhap.setIcon(new ImageIcon("image/update.png"));
		btCapnhap.setFont(new Font("Tahoma", Font.BOLD, 11));

		btTimKiem = new JButton("Tìm Kiếm");
		btTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));
		btTimKiem.setIcon(new ImageIcon("image/tim.png"));

		btXoa = new JButton("Xóa");
		btXoa.setFont(new Font("Tahoma", Font.BOLD, 11));
		btXoa.setIcon(new ImageIcon("image/delete.png"));
		btXoa.setVisible(false);

		lbMaNV.setBounds(53, 42, 100, 40);
		txMaNV.setBounds(187, 50, 335, 27);
		lbHoNV.setBounds(53, 82, 52, 30);
		txHoNV.setBounds(187, 85, 119, 27);
		lbDiaChi.setBounds(53, 123, 52, 40);
		txDiachi.setBounds(187, 131, 335, 27);
		lbSdt.setBounds(618, 37, 100, 50);
		txSdt.setBounds(755, 50, 335, 27);
		lbEmail.setBounds(618, 81, 52, 40);
		txEmail.setBounds(755, 89, 335, 27);
		lbGioiTinh.setBounds(618, 123, 68, 40);
		cbNu.setBounds(755, 131, 20, 27);
		lbNu.setBounds(781, 130, 20, 27);
		lbtitle.setBounds(447, 0, 237, 50);
		txTimKiem.setBounds(187, 219, 205, 30);
		btThem.setBounds(755, 254, 127, 40);
		btXoa.setBounds(618, 256, 127, 37);
		btCapnhap.setBounds(1042, 255, 140, 39);
		btTimKiem.setBounds(402, 219, 120, 30);

		pnNorth.add(lbMaNV);
		pnNorth.add(lbHoNV);
		pnNorth.add(lbDiaChi);
		pnNorth.add(lbSdt);
		pnNorth.add(lbEmail);
		pnNorth.add(lbGioiTinh);
		pnNorth.add(lbtitle);
		pnNorth.add(lbNu);
		pnNorth.add(txHoNV);
		pnNorth.add(txDiachi);
		pnNorth.add(txEmail);
		pnNorth.add(txSdt);
		pnNorth.add(txMaNV);
		pnNorth.add(txTimKiem);
		pnNorth.add(cbNu);
		pnNorth.add(btThem);
		pnNorth.add(btTimKiem);
		pnNorth.add(btXoa);
		pnNorth.add(btCapnhap);

		txNgKT = new JTextField();
		txNgKT.setBounds(755, 170, 335, 27);
		pnNorth.add(txNgKT);
		txNgKT.setEditable(false);
		txNgKT.setColumns(10);

		JLabel lblNgayTao = new JLabel("Ngày Tạo:");
		lblNgayTao.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNgayTao.setBounds(618, 162, 68, 40);
		pnNorth.add(lblNgayTao);

		lblTen = new JLabel("Tên:");
		lblTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTen.setBounds(316, 82, 30, 30);
		pnNorth.add(lblTen);

		txTenNV = new JTextField();
		txTenNV.setBounds(356, 85, 166, 27);
		pnNorth.add(txTenNV);
		txTenNV.setColumns(10);

		lblRole = new JLabel("Quyền Truy Cập:");
		lblRole.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblRole.setBounds(618, 220, 119, 27);
		pnNorth.add(lblRole);

		cbbRole = new JComboBox<String>();
		cbbRole.addItem("ban hang");
		cbbRole.addItem("admin");
		cbbRole.setBounds(755, 220, 166, 28);
		pnNorth.add(cbbRole);

		btXoaTrang = new JButton("Xóa Trắng");
		btXoaTrang.setIcon(new ImageIcon("image/tay.png"));
		btXoaTrang.setFont(new Font("Tahoma", Font.BOLD, 11));

		btXoaTrang.setBounds(892, 254, 140, 40);
		pnNorth.add(btXoaTrang);

		rdbtnTen = new JRadioButton("Tên");
		rdbtnTen.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnTen.setBackground(new Color(240, 230, 140));
		rdbtnTen.setBounds(356, 171, 166, 23);
		pnNorth.add(rdbtnTen);

		JLabel lblTieuChi = new JLabel("Tiêu chí tìm :");
		lblTieuChi.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTieuChi.setBounds(53, 173, 85, 19);
		pnNorth.add(lblTieuChi);

		rdbtnSDT = new JRadioButton("Số điện thoại");
		rdbtnSDT.setSelected(true);
		rdbtnSDT.setFont(new Font("Tahoma", Font.BOLD, 12));
		rdbtnSDT.setBackground(new Color(240, 230, 140));
		rdbtnSDT.setBounds(187, 171, 109, 23);
		pnNorth.add(rdbtnSDT);

		ButtonGroup buttonGroup = new ButtonGroup();
		buttonGroup.add(rdbtnTen);
		buttonGroup.add(rdbtnSDT);
		table = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "H\u1ECD", "T\u00EAn",
				"S\u1ED1 \u0110i\u1EC7n Tho\u1EA1i", "Quy\u1EC1n Truy C\u1EADp" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		table.setForeground(Color.BLUE);
		table.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setFont(new Font("Tahoma", Font.BOLD, 11));
		table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));

		table.getTableHeader().setOpaque(false);
		table.getTableHeader().setBackground(new Color(32, 136, 203));
		table.getTableHeader().setForeground(new Color(255, 255, 255));
		table.getTableHeader().setEnabled(false);
		table.setRowHeight(25);
		table.setSelectionBackground(new Color(232, 57, 95));
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int row = table.getSelectedRow();
				String sdt = (String) dtm.getValueAt(row, 3);
				NhanVienDAO dao = new NhanVienDAO();
				// btThem.setVisible(false);
				try {
					NhanVienDTO dto = dao.layThongTinNhanVien(sdt);
					txMaNV.setText(dto.getMaNV());
					txHoNV.setText(dto.getHoNV());
					txTenNV.setText(dto.getTenNV());
					txDiachi.setText(dto.getDiaChi());
					txEmail.setText(dto.getEmail());
					txSdt.setText(dto.getPhone());

					if (dto.isGioiTinh()) {
						cbNu.setSelected(true);
					} else {
						cbNu.setSelected(false);
					}

					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					if (dto.getThoiGianKhoiTao() == null) {
						txNgKT.setText("");
					} else {
						txNgKT.setText(sdf.format(dto.getThoiGianKhoiTao()));
					}
					if (dto.getRole().equals("ban hang")) {
						cbbRole.setSelectedItem("ban hang");
					} else {
						cbbRole.setSelectedItem("admin");
					}
					txMaNV.setEditable(false);

				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		JScrollPane s = new JScrollPane(table);
		s.setBounds(10, 305, 1172, 355);
		pnNorth.add(s);
		s.setViewportBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		table.setPreferredScrollableViewportSize(new Dimension(790, 305));
		setBackground(new Color(0, 0, 0));

		btThem.addActionListener(this);
		btCapnhap.addActionListener(this);
		btTimKiem.addActionListener(this);
		btXoa.addActionListener(this);
		btXoaTrang.addActionListener(this);
	}

	public void getNhanVien() {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVienDTO> list = null;
		try {
			list = (ArrayList<NhanVienDTO>) dao.dsNhanVien();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm.setRowCount(0);
		for (NhanVienDTO nhanVienDTO : list) {
			dtm.addRow(nhanVienDTO.toVector());
		}
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhanVien(String sdt) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVienDTO> list = null;
		try {
			list = (ArrayList<NhanVienDTO>) dao.timkiemNhanVien(sdt);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (NhanVienDTO nhanVienDTO : list) {
			dtm.addRow(nhanVienDTO.toVector());
		}
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhanVienTen(String ten) {
		NhanVienDAO dao = new NhanVienDAO();
		ArrayList<NhanVienDTO> list = null;
		try {
			list = (ArrayList<NhanVienDTO>) dao.timkiemNhanVienTen(ten);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm = (DefaultTableModel) table.getModel();
		dtm.setRowCount(0);
		for (NhanVienDTO nhanVienDTO : list) {
			dtm.addRow(nhanVienDTO.toVector());
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

	public void xoaTrangNhanVien() {
		txMaNV.setEditable(true);
		txMaNV.setText("");
		txHoNV.setText("");
		txTenNV.setText("");
		txDiachi.setText("");
		txSdt.setText("");
		txEmail.setText("");
		cbNu.setSelected(false);
		txNgKT.setText("");
		txTimKiem.setText("");
		btThem.setVisible(true);
	}

	public boolean kiemTra() {
		String hoNV = txHoNV.getText();
		String tenNV = txTenNV.getText();
		String diaChi = txDiachi.getText();
		String phone = txSdt.getText();
		String email = txEmail.getText();
		String hoNVreg = "^([a-zA-Z]+)( [a-zA-Z]+)*$";
		String tenNVreg = "^([a-zA-Z]+)*$";
		String phonereg = "\\d{9,11}";
		String emailreg = "^\\w+@{1}\\w+\\.\\w+(\\.\\w+)*$";

		if (hoNV.equals("")) {
			JOptionPane.showMessageDialog(null, "Họ Nhân Viên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txHoNV.requestFocus();
			txHoNV.selectAll();
			return false;
		} else if (tenNV.equals("")) {
			JOptionPane.showMessageDialog(null, "Tên Nhân Viên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenNV.requestFocus();
			txTenNV.selectAll();
			return false;
		} else if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(null, "Địa Chỉ Nhân Viên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txDiachi.requestFocus();
			txDiachi.selectAll();
			return false;
		} else if (phone.equals("")) {
			JOptionPane.showMessageDialog(null, "Số Điện THoại không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSdt.requestFocus();
			txSdt.selectAll();
			return false;
		} else if (email.equals("")) {
			JOptionPane.showMessageDialog(null, "Email Nhân Viên không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txEmail.requestFocus();
			txEmail.selectAll();
			return false;
		} else if (!hoNV.matches(hoNVreg)) {
			JOptionPane.showMessageDialog(null,
					"Họ Nhân Viên không hợp lệ !!!\n" + "Ví dụ: NGUYEN VAN hoặc Nguyen Van được viết không dấu !!!",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txHoNV.requestFocus();
			txHoNV.selectAll();
			return false;
		} else if (!tenNV.matches(tenNVreg)) {
			JOptionPane.showMessageDialog(null,
					"Tên Nhân Viên không hợp lệ !!!\n" + "Ví dụ: PHAT hoặc Phat được viết không dấu !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenNV.requestFocus();
			txTenNV.selectAll();
			return false;
		} else if (!phone.matches(phonereg)) {
			JOptionPane.showMessageDialog(null, "Số Điện Thoại phải 9-11 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSdt.requestFocus();
			txSdt.selectAll();
			return false;
		} else if (!email.matches(emailreg)) {
			JOptionPane.showMessageDialog(null,
					"Email Nhân Viên không hợp lệ !!!\n" + "Ví dụ: nguyenvana@gmail.com !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
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
				String maNV = txMaNV.getText();
				if (!maNV.equals("")) {
					JOptionPane.showMessageDialog(null, "Không thể thêm Nhân Viên !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					String hoNV = txHoNV.getText();
					String tenNV = txTenNV.getText();
					String diaChi = txDiachi.getText();
					String phone = txSdt.getText();
					String email = txEmail.getText();
					boolean gioiTinh = cbNu.isSelected();
					Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
					NhanVienDAO dao = new NhanVienDAO();
					NhanVienDTO dto = new NhanVienDTO(maNV, cbbRole.getSelectedItem().toString(), hoNV, tenNV, diaChi,
							phone, email, gioiTinh, thoiGianKhoiTao);

					if (dao.themNhanVien(dto)) {
						JOptionPane.showMessageDialog(null, "Thêm nhân viên thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						Vector<String> v = new Vector<>();
						v.add(dtm.getRowCount() + 1 + "");
						v.add(hoNV);
						v.add(tenNV);
						v.add(phone);
						v.add(cbbRole.getSelectedItem().toString());
						dtm.addRow(v);
						xoaTrangNhanVien();
					}
				}
			} catch (ClassNotFoundException | SQLException e1) {
				// e1.printStackTrace();
			}
		} else if (obj.equals(btCapnhap)) {
			String maNV = txMaNV.getText();
			if (table.getSelectedRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Chọn Nhân Viên cần cập nhật thông tin !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else if (kiemTra() == true) {
				String hoNV = txHoNV.getText();
				String tenNV = txTenNV.getText();
				String diaChi = txDiachi.getText();
				String phone = txSdt.getText();
				String email = txEmail.getText();
				boolean gioiTinh = cbNu.isSelected();
				Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
				NhanVienDAO dao = new NhanVienDAO();
				NhanVienDTO dto = new NhanVienDTO(maNV, cbbRole.getSelectedItem().toString(), hoNV, tenNV, diaChi,
						phone, email, gioiTinh, thoiGianKhoiTao);
				try {
					if (dao.capnhatNhanVien(dto)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Nhân viên thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						int row = table.getSelectedRow();
						table.setValueAt(dto.getHoNV(), row, 1);
						table.setValueAt(dto.getTenNV(), row, 2);
						table.setValueAt(dto.getPhone(), row, 3);
						table.setValueAt(dto.getRole(), row, 4);
						xoaTrangNhanVien();
					} 
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (obj.equals(btTimKiem)) {
			if (rdbtnSDT.isSelected()) {
				String sdt = txTimKiem.getText();
				getTimKiemNhanVien(sdt);
			} else if (rdbtnTen.isSelected()) {
				String ten = txTimKiem.getText();
				getTimKiemNhanVienTen(ten);
			}
		} else if (obj.equals(btXoa)) {
			String maNV = txMaNV.getText();
			if (maNV.equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn cẩn chọn Nhân Viên để xóa !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else {
				HoaDonDAO daoHD = new HoaDonDAO();
				List<HoaDonDTO> dtoHD = null;
				try {
					dtoHD = daoHD.kiemtraHoaDontrongNhanVien(maNV);
				} catch (ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int ntc = 0;
				if (dtoHD == null) {
					ntc = JOptionPane.showConfirmDialog(null, "Bạn có muốn XÓA Nhân Viên này hay không?", "Xóa",
							JOptionPane.YES_NO_OPTION);
				} else {
					ntc = JOptionPane.showConfirmDialog(null, "Bạn có muốn XÓA Nhân Viên này hay không?\n"
							+ "Nhân Viên này có lập các Hóa Đơn mua hàng!!", "Xóa", JOptionPane.YES_NO_OPTION);
				}
				if (ntc == JOptionPane.YES_OPTION) {
					NhanVienDAO daoNV = new NhanVienDAO();
					try {
						daoHD.xoatatcaChiTietHoaDon(dtoHD);
						daoHD.xoatatcaHoaDontrongNhanVien(maNV);
						if (daoNV.xoaNhanVien(maNV)) {
							JOptionPane.showMessageDialog(null, "Xóa Nhân viên thành công !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
							int row = table.getSelectedRow();
							dtm.removeRow(row);
							demSoThuTu();
							xoaTrangNhanVien();
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
						e1.printStackTrace();
					}
				}
			}
		} else if (obj.equals(btXoaTrang)) {
			xoaTrangNhanVien();
			getNhanVien();
		}
	}
}
