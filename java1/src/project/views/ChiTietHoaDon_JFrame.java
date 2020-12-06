package project.views;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import project.daos.CT_HoaDonDAO;
import project.daos.HoaDonDAO;
import project.daos.KhachHangDAO;

import project.daos.NhaCungCapDAO;
import project.daos.NhanVienDAO;
import project.daos.SanPhamDAO;
import project.dtos.CT_HoaDonDTO;
import project.dtos.HoaDonDTO;
import project.dtos.KhachHangDTO;
import project.dtos.NhaCungCapDTO;
import project.dtos.NhanVienDTO;
import project.dtos.SanPhamDTO;

import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.UIManager;
import java.awt.Toolkit;
import java.awt.Component;

public class ChiTietHoaDon_JFrame extends JFrame implements ActionListener {
	public static String role, maNV, maKH;
	public static JTable table_1 = new JTable(new DefaultTableModel(new Object[][] {},
			new String[] { "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Giá", "Số lượng", "Đơn vị" }) {
		/**
		 * 
		 */
		public static final long serialVersionUID = 1L;
		boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
	public static JScrollPane scrollPane_1 = new JScrollPane(table_1);

	public static JPanel panel_1 = new JPanel();
	public static JButton buttonTimKiem = new JButton("Tìm Kiếm "), btnThanhToan, btnThem;

	public static JLabel labelMaNV = new JLabel("Mã Nhân Viên:");
	public static JTextField txMaNV = new JTextField();
	public static JLabel labelMaKH = new JLabel("Mã Khách Hàng:");
	public static JTextField txMaKH = new JTextField();
	public static JComboBox<String> cbMaNCC = new JComboBox<String>();
	public static DefaultTableModel dtm1;
	public static DefaultTableModel dtm2;
	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static JTextField txTimKiem;
	public static JTextField txTongTien;
	public static final JButton btnTrHng = new JButton("");
	public static final JLabel lblNewLabel_4 = new JLabel("Nơi Nhận Hàng:");
	public static final JTextField txNoiNhanHang = new JTextField();
	private final JScrollPane scrollPane_2 = new JScrollPane((Component) null);
	public static JTable table_2;

	public static Login_JFrame frameLogin_JFrame;
	public static JMenuItem mntmXoaGH = new JMenuItem("txXoaGH");
	public static JDateChooser txNgayGiaoHang;

	public static FrmXuatHD frmXuatHD;

	/**
	 * @wbp.parser.constructor
	 */
	public ChiTietHoaDon_JFrame(String role, String maNV, String maKH) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/Kill-Bill-Vol-2-icon.png"));
		setBackground(Color.CYAN);
		table_1.setBackground(Color.WHITE);
		table_1.setFont(new Font("Tahoma", Font.BOLD, 11));
		getContentPane().setBackground(new Color(240, 230, 140));
		setTitle("Lập Hóa Đơn");
	//	setSize(1133, 700);
		setBounds(0,0, 1220, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();

		dtm1 = (DefaultTableModel) table_1.getModel();
		dtm2 = (DefaultTableModel) table_2.getModel();
		txMaNV.setBackground(new Color(192, 192, 192));
		getSanPham();

		addtoComboBoxNhaCungCap();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table_2.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table_2.getRowCount()) {
					table_2.setRowSelectionInterval(r, r);
				} else {
					table_2.clearSelection();
				}
				// Row index is found ...
				int rowIndex = table_2.getSelectedRow();
				if (rowIndex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = createPopUp(rowIndex, table_2);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				int r = table_1.rowAtPoint(e.getPoint());
				if (r >= 0 && r < table_1.getRowCount()) {
					table_1.setRowSelectionInterval(r, r);
				} else {
					table_1.clearSelection();
				}
				// Row index is found ...
				int rowIndex = table_1.getSelectedRow();
				if (rowIndex < 0)
					return;
				if (e.isPopupTrigger() && e.getComponent() instanceof JTable) {
					JPopupMenu popup = createPopUp1(rowIndex, table_1);
					popup.show(e.getComponent(), e.getX(), e.getY());
				}
			}
		});

		ChiTietHoaDon_JFrame.role = role;
		ChiTietHoaDon_JFrame.maNV = maNV;
		ChiTietHoaDon_JFrame.maKH = maKH;
		txMaNV.setText(maNV);
		txMaKH.setText(maKH);

	}

	public void buildUI() {

		getContentPane().setLayout(null);
		panel_1.setBounds(0,0, 1204, 671);
		getContentPane().add(panel_1);
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setForeground(Color.BLACK);

		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setLayout(null);
		scrollPane_1.setBounds(10, 72, 525, 378);
		panel_1.add(scrollPane_1);
		txMaNV.setBounds(754, 468, 114, 30);
		panel_1.add(txMaNV);
		txMaNV.setEditable(false);

		scrollPane_2.setBounds(662, 72, 532, 378);

		panel_1.add(scrollPane_2);

		table_2 = new JTable();
		table_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table_2.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "M\u00E3 S\u1EA3n Ph\u1EA9m",
				"T\u00EAn S\u1EA3n Ph\u1EA9m", "Gi\u00E1", "S\u1ED1 L\u01B0\u1EE3ng", "\u0110\u01A1n v\u1ECB" }));
		table_2.getColumnModel().getColumn(0).setResizable(false);
		table_2.getColumnModel().getColumn(1).setResizable(false);
		table_2.getColumnModel().getColumn(2).setResizable(false);
		table_2.getColumnModel().getColumn(2).setPreferredWidth(81);
		table_2.getColumnModel().getColumn(3).setResizable(false);
		table_2.getColumnModel().getColumn(4).setResizable(false);
		table_2.getColumnModel().getColumn(5).setResizable(false);
		table_2.setEnabled(false);
		
		scrollPane_2.setViewportView(table_2);

		JPopupMenu popupMenu = new JPopupMenu();
		addPopup(table_2, popupMenu);

		JMenuItem mntmSuaSL = new JMenuItem("txSua");
		popupMenu.add(mntmSuaSL);

		popupMenu.add(mntmXoaGH);
		/*------------------------------------------*/
		JPopupMenu popupMenu1 = new JPopupMenu();
		addPopup(table_1, popupMenu1);

		JMenuItem mntmLoadDL = new JMenuItem("txLoad");
		popupMenu1.add(mntmLoadDL);

		JLabel labelTitle = new JLabel("LẬP HÓA ĐƠN");
		labelTitle.setForeground(Color.BLUE);
		labelTitle.setFont(new Font("Arial", Font.BOLD, 25));
		labelTitle.setBackground(Color.LIGHT_GRAY);
		labelTitle.setBounds(483, 11, 191, 29);
		panel_1.add(labelTitle);
		buttonTimKiem.setBounds(10, 493, 120, 30);
		panel_1.add(buttonTimKiem);
		buttonTimKiem.setBackground(Color.GREEN);
		buttonTimKiem.setIcon(new ImageIcon("image/iconSearch.png"));
		buttonTimKiem.setFont(new Font("Tahoma", Font.BOLD, 11));

		txTimKiem = new JTextField();
		txTimKiem.setBounds(171, 493, 266, 30);
		panel_1.add(txTimKiem);
		txTimKiem.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Mã Nhà Cung Cấp:");
		lblNewLabel_1.setBounds(10, 575, 129, 23);
		panel_1.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelMaNV.setBounds(662, 468, 91, 30);
		panel_1.add(labelMaNV);
		labelMaNV.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMaKH.setBounds(662, 521, 91, 30);
		panel_1.add(labelMaKH);
		labelMaKH.setFont(new Font("Tahoma", Font.BOLD, 11));
		txMaKH.setBounds(754, 521, 114, 30);
		panel_1.add(txMaKH);
		txMaKH.setEnabled(false);
		btnTrHng.setBounds(545, 293, 107, 50);
		panel_1.add(btnTrHng);
		btnTrHng.setBackground(new Color(240, 230, 140));
		btnTrHng.setIcon(new ImageIcon("image/Button-Previous-icon.png"));
		btnTrHng.setFont(new Font("Tahoma", Font.BOLD, 11));

		lblNewLabel_4.setBounds(662, 574, 91, 25);
		panel_1.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD, 11));
		txNoiNhanHang.setBounds(754, 571, 242, 30);
		panel_1.add(txNoiNhanHang);
		txNoiNhanHang.setColumns(10);

		btnThem = new JButton("");
		btnThem.setBounds(545, 178, 107, 50);
		panel_1.add(btnThem);
		btnThem.setBackground(new Color(240, 230, 140));
		btnThem.setIcon(new ImageIcon("image/Button-Next-icon.png"));
		btnThem.setFont(new Font("Tahoma", Font.BOLD, 11));

		JLabel lblNewLabel_3 = new JLabel("Tổng Tiền: ");
		lblNewLabel_3.setBounds(1003, 468, 66, 30);
		panel_1.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));

		txTongTien = new JTextField();
		txTongTien.setBounds(1068, 468, 126, 30);
		panel_1.add(txTongTien);
		txTongTien.setEditable(false);
		txTongTien.setColumns(10);

		JLabel lblNewLabel = new JLabel("Ngày giao:");
		lblNewLabel.setBounds(1003, 525, 75, 23);
		panel_1.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 11));

		txNgayGiaoHang = new JDateChooser();
		txNgayGiaoHang.setBounds(1068, 521, 126, 30);
		panel_1.add(txNgayGiaoHang);
		txNgayGiaoHang.setDateFormatString("dd-MM-yyyy");
		txNgayGiaoHang.getCalendarButton().setIcon(new ImageIcon("image/lich.png"));

		btnThanhToan = new JButton("Thanh Toán");
		btnThanhToan.setBounds(1003, 571, 191, 30);
		panel_1.add(btnThanhToan);
		btnThanhToan.setBackground(Color.GREEN);
		btnThanhToan.setIcon(new ImageIcon("image/payment.png"));
		btnThanhToan.setFont(new Font("Tahoma", Font.BOLD, 11));

		table_1.getTableHeader().setOpaque(false);
		table_1.getTableHeader().setBackground(new Color(32, 136, 203));
		table_1.getTableHeader().setForeground(new Color(255, 255, 255));
		table_1.setSelectionBackground(new Color(232, 57, 95));
		table_1.getTableHeader().setEnabled(false);

		table_2.getTableHeader().setOpaque(false);
		table_2.getTableHeader().setBackground(new Color(32, 136, 203));
		table_2.getTableHeader().setForeground(new Color(255, 255, 255));
		table_2.setSelectionBackground(new Color(232, 57, 95));
		table_2.getTableHeader().setEnabled(false);

		buttonTimKiem.addActionListener(this);
		btnTrHng.addActionListener(this);
		btnThem.addActionListener(this);
		btnThanhToan.addActionListener(this);

	}

	public int TimViTri(String m) {
		for (int i = 0; i < table_1.getSelectedColumn(); i++) {
			if (table_1.getValueAt(i, 1).equals(m)) {
				return i;
			}
		}
		return -1;
	}

	// --ComboBox Nhà Cung Cấp
	private void addtoComboBoxNhaCungCap() {
		NhaCungCapDAO dao = new NhaCungCapDAO();
		try {
			ArrayList<NhaCungCapDTO> list = (ArrayList<NhaCungCapDTO>) dao.dsNhaCungCap();
			cbMaNCC.setBounds(171, 573, 266, 26);
			panel_1.add(cbMaNCC);
			cbMaNCC.addItem("");
			for (NhaCungCapDTO nhaCungCapDTO : list) {
				cbMaNCC.addItem(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -- ComboBox Mã Khuyến Mãi

	public boolean kiemtraDuLieuTrung(String id) {
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			String maSP = (String) dtm2.getValueAt(i, 1);
			if (id.equals(maSP)) {
				return false;
			}
		}
		return true;
	}

	public void getTimKiemSanPhamBH(String id, String maNCC) {
		SanPhamDAO dao = new SanPhamDAO();
		ArrayList<SanPhamDTO> list = null;
		try {
			list = (ArrayList<SanPhamDTO>) dao.timkiemSanPhamBanHang(id, maNCC);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm1.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm1.addRow(sanPhamDTO.toVector3());
		}
		for (int i = 0; i < dtm1.getRowCount(); i++) {
			dtm1.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemSanPham(String tenSP, String maNCC) {
		SanPhamDAO dao = new SanPhamDAO();
		ArrayList<SanPhamDTO> list = null;
		try {
			list = (ArrayList<SanPhamDTO>) dao.timkiemSanPham(tenSP, maNCC);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm1.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm1.addRow(sanPhamDTO.toVector3());
		}
		for (int i = 0; i < dtm1.getRowCount(); i++) {
			dtm1.setValueAt(i + 1, i, 0);
		}
	}

	public void demSoThuTu(DefaultTableModel dtm) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}

	}

	public static double getTongTien() {
		double tt = 0;
		SanPhamDAO dao = new SanPhamDAO();
		SanPhamDTO dto = new SanPhamDTO();
		double sl, giaBan;
		for (int i = 0; i < dtm2.getRowCount(); i++) {

			String ma = (String) table_2.getValueAt(i, 1);
			String s = (String) table_2.getValueAt(i, 4);
			sl = Double.valueOf(s);
			try {
				dto = dao.layThongTinSanPhamByMa(ma);
				giaBan = dto.getGiaBan();
				tt += sl * giaBan;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tt;
	}

	public void getTimMaKhachHangTheoSoDienThoai(String phone) {
		KhachHangDAO dao = new KhachHangDAO();
		KhachHangDTO dto = null;
		try {
			dto = dao.timKhachHangTheoSoDienThoai(phone);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		txMaKH.setText(dto.getMaKH());
	}

	public static void getLamSach() {
		txMaKH.setText("");
		txNoiNhanHang.setText("");
		txTimKiem.setText("");
		txTongTien.setText("");
		cbMaNCC.setSelectedItem("");
		dtm2.setRowCount(0);
		txNgayGiaoHang.setDate(null);
	}

	public static void getSanPham() {
		SanPhamDAO dao = new SanPhamDAO();
		ArrayList<SanPhamDTO> list = null;
		try {
			list = (ArrayList<SanPhamDTO>) dao.dsSanPham();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm1.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm1.addRow(sanPhamDTO.toVector3());
		}
		for (int i = 0; i < dtm1.getRowCount(); i++) {
			dtm1.setValueAt(i + 1, i, 0);
		}
	}

	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}

	DecimalFormat df = new DecimalFormat("#,###.#");

	public JPopupMenu createPopUp(int rowIndex, JTable table) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem edit = new JMenuItem("Sửa số lượng");
		JMenuItem xoaGH = new JMenuItem("Xóa giỏ hàng");

		edit.addActionListener(new ActionListener() {
			SanPhamDAO dao = new SanPhamDAO();

			@Override
			public void actionPerformed(ActionEvent e) {
				String soluong = JOptionPane.showInputDialog(null, "Nhập số lượng", "Số lượng",
						JOptionPane.INFORMATION_MESSAGE);

				int x = table_2.getSelectedRow();
				if (!soluong.matches("\\.*[1-9].*")) {
					JOptionPane.showMessageDialog(null, "Bạn cần phải nhập số > 0 !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else {
					String ma = (String) table_2.getValueAt(x, 1);
					int solg2 = Integer.parseInt(soluong);

					int vt = TimViTri(ma);

					SanPhamDTO dto = null;
					try {
						String ten = (String) table_1.getValueAt(vt, 2);
						dto = dao.layThongTinSanPham(ten);
					} catch (ClassNotFoundException | SQLException e1) {
					}

					int solg1 = dto.getSoLuongTon();

					int kq = solg1 - solg2;
					if (kq < 0) {
						JOptionPane.showMessageDialog(null, "Vượt quá số lượng !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
					} else {
						table_2.setValueAt(String.valueOf(solg2), table_2.getSelectedRow(), 4);
						table_1.setValueAt(kq, vt, 4);
						txTongTien.setText(df.format(getTongTien()));

					}

				}
			}
		});
		xoaGH.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtm2.setRowCount(0);
				txTongTien.setText("");
				getSanPham();
			}
		});
		popup.add(edit);
		popup.add(xoaGH);

		return popup;
	}

	public JPopupMenu createPopUp1(int rowIndex, JTable table) {
		JPopupMenu popup = new JPopupMenu();
		JMenuItem load = new JMenuItem("Load Sản Phẩm");

		load.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtm1.setRowCount(0);
				getSanPham();
			}
		});

		popup.add(load);

		return popup;
	}

	public void getCT_HoaDonTheoMa(String ma) {
		CT_HoaDonDAO dao = new CT_HoaDonDAO();
		ArrayList<CT_HoaDonDTO> list = null;
		try {
			list = (ArrayList<CT_HoaDonDTO>) dao.getCT_HoaDon(ma);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FrmXuatHD.tableModel.setRowCount(0);
		for (CT_HoaDonDTO ct_HoaDonDTO : list) {
			FrmXuatHD.tableModel.addRow(ct_HoaDonDTO.toVector());
		}
		for (int i = 0; i < FrmXuatHD.tableModel.getRowCount(); i++) {
			FrmXuatHD.tableModel.setValueAt(i + 1, i, 0);
		}
	}

	public void setDuLieuFrmInHd(String maHoaDon, String ngayLap, String hoTenKh, String soDT, String diaChi,
			String soLuong, String tongTienLK, String tongTienThanhToan, String tenNguoiBan, String tenKhachHang,
			String email) {
		FrmXuatHD.lblMaHD.setText(maHoaDon);
		FrmXuatHD.lblNgayLap1.setText(ngayLap);
		FrmXuatHD.lblTenKH1.setText(tenKhachHang);
		FrmXuatHD.lblDCKH1.setText(diaChi);
		FrmXuatHD.lblsdtkh1.setText(soDT);
		FrmXuatHD.lblSL.setText(soLuong);
		FrmXuatHD.lblTongLKien.setText(tongTienLK);
		FrmXuatHD.lblTongT.setText(tongTienThanhToan);
		FrmXuatHD.lblNguoiBan.setText(tenNguoiBan);
		FrmXuatHD.lblGhiTen.setText(tenKhachHang);
		FrmXuatHD.lblEmail1.setText(email);
		FrmXuatHD.lblGhiTen.setText(tenKhachHang);

	}
	public boolean kiemTra() {
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date ngayNhan=txNgayGiaoHang.getDate();
		LocalDate ngayHt= LocalDate.now();
		
		Date ngayHTai=Date.from(ngayHt.atStartOfDay(defaultZoneId).toInstant());
		String noiNhan=txNoiNhanHang.getText();
	
		if (dtm2.getRowCount() == 0) {
			JOptionPane.showMessageDialog(null, "Giỏ hàng rỗng !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			return false;
		}else if(ngayNhan==null) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn ngày Giao Hàng !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			return false;
		}else if(noiNhan.equals("")) {
			JOptionPane.showMessageDialog(null, "Vui lòng điền nơi nhận hàng !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			return false;
		}else if(ngayNhan.before(ngayHTai)) {
			JOptionPane.showMessageDialog(null, "Vui lòng chọn Ngày giao hàng sau Ngày hiện tại!!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			return false;
		}
	
		return true;
	}
	public void themHoaDonCT_HoaDon() {
		frmXuatHD = new FrmXuatHD();
		 if(kiemTra()==true) {
			String maHD = "";
			String maNV = txMaNV.getText();
			String maKH = txMaKH.getText();
			String noiNhanHang = txNoiNhanHang.getText();
			Double tongtien = Double.parseDouble(getTongTien() + "") * 1.05;
			Timestamp ngayGiaoHang = new Timestamp(txNgayGiaoHang.getDate().getTime());

			Timestamp ngayLapHD = new Timestamp(System.currentTimeMillis());
			HoaDonDAO dao = new HoaDonDAO();
			KhachHangDAO daoKH = new KhachHangDAO();
			KhachHangDTO dtoKH = new KhachHangDTO();
			NhanVienDAO daonv = new NhanVienDAO();
			NhanVienDTO dtonv = new NhanVienDTO();

			ArrayList<CT_HoaDonDTO> chiTietHoaDon = new ArrayList<CT_HoaDonDTO>();
			CT_HoaDonDAO daoCTHD = new CT_HoaDonDAO();
			CT_HoaDonDTO dtoCTHD = null;
			SanPhamDAO daoSP = new SanPhamDAO();
			SanPhamDTO dtoSP = new SanPhamDTO();

			String maSP2;
			int soLuong2 = 0, soLuong;
			double giaBan2;

			try {
				HoaDonDTO dto = new HoaDonDTO(maHD, noiNhanHang, maNV, maKH, ngayLapHD, ngayGiaoHang, tongtien);
				if (dao.themHoaDon(dto)) {
					for (int i = 0; i < dtm2.getRowCount(); i++) {
						maSP2 = (String) dtm2.getValueAt(i, 1);
						soLuong2 = Integer.parseInt((String) dtm2.getValueAt(i, 4));
						String[] gb = dtm2.getValueAt(i, 3).toString().split(",");
						String giaBan = "";
						for (int j = 0; j < gb.length; j++) {
							giaBan = giaBan + gb[j];
						}
						giaBan2 = Double.parseDouble(giaBan);

						String maHD2 = dao.layMaHoaDon();

						dtoCTHD = new CT_HoaDonDTO(maHD2, maSP2, soLuong2, giaBan2);
						chiTietHoaDon.add(dtoCTHD);

						dtoSP = daoSP.layThongTinSanPhamByMa((String) dtm2.getValueAt(i, 1));
						soLuong = dtoSP.getSoLuongTon() - soLuong2;
						daoSP.capnhatSLToncuaSanPham(maSP2, soLuong);
						getSanPham();

					}
					daoCTHD.themChiTietHoaDon(chiTietHoaDon);
				}
				dtoCTHD = daoCTHD.laySoLuogLK();
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

				dtoKH = daoKH.layThongTinKhachHangByMaKH(txMaKH.getText());
				dtonv = daonv.layThongTinNhanVienByMaNV(txMaNV.getText());
				DecimalFormat df = new DecimalFormat("#,###.0");

				setDuLieuFrmInHd(dtoCTHD.getMaHD(), sdf.format(dto.getNgayLapHD()), dtoKH.getTenKH(), dtoKH.getPhone(),
						dtoKH.getDiaChi(), String.valueOf(dtoCTHD.getSoLuong2()), df.format(getTongTien()),
						df.format(tongtien), dtonv.getHoNV() + " " + dtonv.getTenNV(), dtoKH.getTenKH(),
						dtoKH.getEmail());
				getCT_HoaDonTheoMa(dtoCTHD.getMaHD());
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			ChiTietHoaDon_JFrame.frmXuatHD.setVisible(true);
			frmXuatHD.printingHoaDon();
			dispose();
			ChiTietHoaDon_JFrame.frmXuatHD.dispose();
			JOptionPane.showMessageDialog(this, "Xuất hóa đơn thành công !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/yes.png"));

			getLamSach();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(buttonTimKiem)) {

			String[] maNCC = cbMaNCC.getSelectedItem().toString().split("-");

			String ten = txTimKiem.getText();
			String maNCC1 = maNCC[0];
			getTimKiemSanPham(ten, maNCC1);

		} else if (obj.equals(btnTrHng)) {

			try {
				int row = table_2.getSelectedRow();
				int row2 = table_2.getSelectedRow();
				String maSP = (String) table_2.getValueAt(row2, 1);
				int vt = TimViTri(maSP);

				SanPhamDTO dto = null;
				String ten = (String) table_1.getValueAt(vt, 2);
				SanPhamDAO dao = new SanPhamDAO();

				try {
					dto = dao.layThongTinSanPham(ten);
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				int solg1 = dto.getSoLuongTon();
				table_1.setValueAt(solg1, vt, 4);
				dtm2.removeRow(row);
			} catch (ArrayIndexOutOfBoundsException e1) {

			}

			demSoThuTu(dtm2);
			txTongTien.setText(df.format(getTongTien()));

		} else if (obj.equals(btnThem)) {
			int row = table_1.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Cần phải chọn Sản Phẩm !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else {
				String maSP = (String) table_1.getValueAt(row, 1);
				if (kiemtraDuLieuTrung(maSP)) {
					int soLuong = Integer.parseInt(table_1.getValueAt(row, 4) + "");
					String input = JOptionPane.showInputDialog(null, "Nhập số lượng", "Số lượng",
							JOptionPane.INFORMATION_MESSAGE);
					if (!input.matches("\\.*[1-9].*")) {
						JOptionPane.showMessageDialog(this, "Bạn cần phải nhập số > 0 !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
					} else {
						int sl = Integer.parseInt(input);
						if (sl > soLuong) {
							JOptionPane.showMessageDialog(this, "Vượt quá số lượng hiện có !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
						} else {
							int kq = soLuong - sl;

							table_1.setValueAt(kq, row, 4);

							Vector<String> v = new Vector<String>();
							v.add(dtm2.getRowCount() + 1 + "");
							v.add(maSP);
							v.add((String) table_1.getValueAt(row, 2));
							v.add((String) table_1.getValueAt(row, 3));
							v.add(input);
							v.add((String) table_1.getValueAt(row, 5));

							dtm2.addRow(v);
							demSoThuTu(dtm2);
							txTongTien.setText(df.format(getTongTien()));
						}
					}

				} else {
					JOptionPane.showMessageDialog(this, "Sản phẩm này đã có trông giỏ hàng !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				}
			}
		} else if (obj.equals(btnThanhToan)) {
			themHoaDonCT_HoaDon();
//			frmXuatHD.setVisible(true);
		}
	}
}
