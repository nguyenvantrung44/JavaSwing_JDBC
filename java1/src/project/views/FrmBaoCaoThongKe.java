package project.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import java.awt.print.PrinterException;
import javax.swing.ScrollPaneConstants;
import java.awt.Toolkit;

import javax.swing.border.BevelBorder;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JMonthChooser;
import project.daos.*;
import project.dtos.*;

import javax.swing.SwingConstants;
import javax.swing.JPopupMenu;

import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class FrmBaoCaoThongKe extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static JTabbedPane tabbedPane;
	static JPanel pnlThongkeTTLinhKien, pnlToanPhan, pnTab3, pnTab4, panel_2, pnlThongKeBaoCao, pnlThongTinChung, pnlTT,
			pnlThongKeChiTiet, pnlTKtrongCT;
	JLabel lblThongTinKhachHang, lblDiaChi, lblThngKTheo, lblTngSLng_1, lblTngTin, lblMaKH, lblTenKH, lblSDT, lblNN,
			lblMakh, lblNgay, lblMaNV, lblTenNv, lblTongLoaiLK;
	JTextField txtDiaChi, txtTenKH, txtMaKH, txtNN, txtSDT, txtMakn;
	JButton btnTim, btnThem, btnXoa, btnBaoCao, btnXemLKDaBan;
	private static JComboBox<String> cmbMaNV, cmbNam, comboboxNgay;
	ImageIcon background;
	JPanel jpanel;
	JScrollPane scrollPaneTKTTT, scrTKTQ;
	JMonthChooser monthChooser;
	JMonthChooser txtThang;
	public static final String strImagePath = "BoundBall.png";

	public static DefaultTableModel tablemodel = new DefaultTableModel();
	public static DefaultTableModel tablemodel1 = new DefaultTableModel();
	public static DefaultTableModel tablemodel2 = new DefaultTableModel();
	private JLabel lblNgayy;
	public static JTextField txtTongSoLuongLK;
	public static JTextField txtTongSoLoaiLK;
	private JTextField txtTenNV;
	public static JTextField txtTongSLLKDB;
	public static JTextField txtTongTienDaBan;
	public static JTextField txtTongLoaiLK;
	private JTable table_1;

	private JTable table2;
	private JTextField txtTongSLHD;
	private JTextField txtTongTienBanDuoc;
	private JTextField txtLoiNhuanThuDkTQ;
	private JButton btnXemBCaoTKTQ;
	private JTable tblThongKeTongQuat;
	private JPanel panel_12;
	private JPopupMenu popupMenu;
	private JDateChooser txtChonNgay;
	private JDateChooser txtChonNgayThongKeLK;
	private JMenuItem mntmXemChiTiet;
	private NhanVienDAO dao;
	private JButton btnXuatBC, btnXuatLKDB;
	DecimalFormat df = new DecimalFormat("#,###.#");
//
//	public static void main(String[] args) {
//		FrmBaoCaoThongKe frmBaoCaoThongKe = new FrmBaoCaoThongKe();
//		frmBaoCaoThongKe.setVisible(true);
//
//	}

	public FrmBaoCaoThongKe() {

		setBackground(new Color(176, 224, 230));
		setIconImage(Toolkit.getDefaultToolkit().getImage(""));

		setTitle("QUẢN LÍ LINH KIỆN");
		setSize(1220, 700);
		// setLocationRelativeTo(null);
		setResizable(false);
		panel_2 = new JPanel();
		pnlThongTinChung = new JPanel();
		pnlTT = new JPanel();
		pnlTT.setBounds(20, 60, 525, 89);
		pnlThongKeChiTiet = new JPanel();
		pnlTKtrongCT = new JPanel();
		txtTongLoaiLK = new JTextField();
		txtTongLoaiLK.setForeground(Color.BLUE);
		txtTongSLLKDB = new JTextField();
		txtTongSLLKDB.setForeground(Color.BLUE);
		txtTongTienDaBan = new JTextField();
		txtTongTienDaBan.setForeground(Color.BLUE);
		txtTongTienDaBan.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTongTienDaBan.setText("                            ");
		txtTenNV = new JTextField();
		cmbMaNV = new JComboBox<String>();

		pnlThongTinChung.setLayout(null);

		tabbedPane = new JTabbedPane();
		tabbedPane.setForeground(new Color(0, 128, 128));
		tabbedPane.setBounds(0, 0, 1220, 670);
		tabbedPane.setBorder(null);

		pnlToanPhan = new JPanel();
		pnlToanPhan.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlToanPhan.setBackground(new Color(240, 230, 140));

		JPanel pnlTieuDeTKHDTHV = new JPanel();
		pnlTieuDeTKHDTHV.setBackground(new Color(255, 250, 205));
		pnlTieuDeTKHDTHV.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlTieuDeTKHDTHV.setBounds(10, 11, 1177, 71);
		pnlToanPhan.add(pnlTieuDeTKHDTHV);
		pnlTieuDeTKHDTHV.setLayout(null);

		JLabel lblTieuDeTKHDTNV = new JLabel("THỐNG KÊ NHÂN VIÊN LẬP HÓA ĐƠN THEO NGÀY");
		lblTieuDeTKHDTNV.setHorizontalAlignment(SwingConstants.CENTER);
		lblTieuDeTKHDTNV.setForeground(Color.RED);
		lblTieuDeTKHDTNV.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblTieuDeTKHDTNV.setBounds(290, 11, 538, 37);
		pnlTieuDeTKHDTHV.add(lblTieuDeTKHDTNV);

		tabbedPane.addTab("Thống kê hóa đơn theo nhân viên", new ImageIcon("Hinh\\iconthongke.jpg"), pnlToanPhan);
		tabbedPane.setForegroundAt(0, new Color(0, 139, 139));
		pnlToanPhan.setLayout(null);

		pnlThongTinChung.setBackground(new Color(240, 230, 140));
		pnlThongTinChung.setBounds(10, 93, 555, 157);
		pnlToanPhan.add(pnlThongTinChung);

		lblNgay = new JLabel("Ngày:");
		lblNgay.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgay.setBounds(23, 29, 48, 20);
		lblMaNV = new JLabel("Mã Nhân viên:");
		lblMaNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTenNv = new JLabel("Tên Nhân viên:");
		lblTenNv.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongLoaiLK = new JLabel("Tổng số loại linh kiện:");
		lblTongLoaiLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTngSLng_1 = new JLabel("Tổng số lượng linh kiện đã bán:");
		lblTngSLng_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTngTin = new JLabel("Tổng tiền đã bán:");
		lblTngTin.setFont(new Font("Tahoma", Font.PLAIN, 15));

		lblTenNv.setBounds(7, 41, 107, 14);
		lblMaNV.setBounds(7, 8, 107, 14);
		lblTongLoaiLK.setBounds(10, 93, 158, 20);
		lblTngSLng_1.setBounds(10, 9, 210, 20);
		lblTngTin.setBounds(10, 48, 130, 22);

		txtTenNV.setBounds(124, 38, 130, 20);
		txtTongSLLKDB.setBounds(252, 11, 178, 20);
		txtTongTienDaBan.setBounds(252, 50, 178, 20);
		txtTongLoaiLK.setBounds(252, 95, 178, 20);
		txtTenNV.setEditable(false);
		txtTongSLLKDB.setEditable(false);
		txtTongTienDaBan.setEditable(false);
		txtTongLoaiLK.setEditable(false);
		txtTongSLLKDB.setColumns(10);
		txtTenNV.setColumns(10);
		txtTongTienDaBan.setColumns(10);
		txtTongLoaiLK.setColumns(10);

		cmbMaNV.setBounds(124, 5, 130, 20);

		pnlTKtrongCT.add(lblTngTin);
		pnlTKtrongCT.add(lblTngSLng_1);
		pnlTKtrongCT.add(txtTongTienDaBan);
		pnlTKtrongCT.add(lblTongLoaiLK);
		pnlTKtrongCT.add(txtTongLoaiLK);
		pnlTT.add(lblMaNV);
		pnlTT.add(lblTenNv);
		pnlThongTinChung.add(lblNgay);

		pnlTT.add(cmbMaNV);
		addtoComboBoxNhanVien();

		pnlTT.add(txtTenNV);

		pnlTKtrongCT.add(txtTongSLLKDB);

		javax.swing.border.Border southborder4 = BorderFactory.createLineBorder(Color.blue, Font.BOLD);
		TitledBorder southTitleBorder4 = new TitledBorder(southborder4, "Thông tin chung");
		southTitleBorder4.setTitleColor(Color.blue);
		pnlThongTinChung.setBorder(
				new TitledBorder(null, "Th\u00F4ng tin chung", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlToanPhan.add(pnlThongTinChung);

		pnlTT.setBackground(new Color(240, 230, 140));
		pnlTT.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlThongTinChung.add(pnlTT);
		pnlTT.setLayout(null);

		pnlThongKeChiTiet.setBackground(new Color(240, 230, 140));
		pnlThongKeChiTiet.setBounds(575, 93, 612, 157);
		pnlToanPhan.add(pnlThongKeChiTiet);
		pnlThongKeChiTiet.setLayout(null);

		javax.swing.border.Border southborder5 = BorderFactory.createLineBorder(Color.blue, Font.BOLD);
		TitledBorder southTitleBorder5 = new TitledBorder(southborder5, "Chi tiết thống kê");
		southTitleBorder5.setTitleColor(Color.blue);
		pnlThongKeChiTiet.setBorder(new TitledBorder(null, "Th\u1ED1ng k\u00EA chi ti\u1EBFt", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlToanPhan.add(pnlThongKeChiTiet);

		pnlTKtrongCT.setBackground(new Color(240, 230, 140));
		pnlTKtrongCT.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlTKtrongCT.setBounds(30, 24, 513, 122);
		pnlThongKeChiTiet.add(pnlTKtrongCT);
		pnlTKtrongCT.setLayout(null);

		javax.swing.border.Border southborder = BorderFactory.createLineBorder(Color.blue, Font.BOLD);
		TitledBorder southTitleBorder = new TitledBorder(southborder, "Hình thức thống kê");
		southTitleBorder.setTitleColor(Color.blue);
		javax.swing.border.Border southborder1 = BorderFactory.createLineBorder(Color.blue, Font.BOLD);
		TitledBorder southTitleBorder1 = new TitledBorder(southborder1, "Báo cáo sau thống kê");
		southTitleBorder1.setTitleColor(Color.blue);
		getContentPane().setLayout(null);

		getContentPane().add(tabbedPane);
		cmbMaNV.addActionListener(this);

		javax.swing.border.Border southborder2 = BorderFactory.createLineBorder(Color.blue);
		TitledBorder southTitleBorder2 = new TitledBorder(southborder2, "Danh sách thuốc sau thống kê");
		southTitleBorder2.setTitleColor(Color.blue);

		JScrollPane scrDSTK;

		table_1 = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "T\u00EAn linh ki\u1EC7n",
				"Ng\u00E0y l\u1EADp", "S\u1ED1 l\u01B0\u1EE3ng", "T\u1ED5ng ti\u1EC1n" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tablemodel = (DefaultTableModel) table_1.getModel();

		table_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table_1.setBackground(new Color(255, 250, 205));
		table_1.setForeground(new Color(0, 0, 205));
		getContentPane().add(scrDSTK = new JScrollPane(table_1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS), BorderLayout.CENTER);

		popupMenu = new JPopupMenu();

		mntmXemChiTiet = new JMenuItem("Xem Chi Tiết Hóa Đơn");
		popupMenu.add(mntmXemChiTiet);

		table_1.setRowHeight(20);
		table_1.getTableHeader().setEnabled(false);

		txtChonNgay = new JDateChooser();
		txtChonNgay.setForeground(new Color(0, 0, 255));
		txtChonNgay.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtChonNgay.setLocale(new Locale("vi", "VN"));
		txtChonNgay.setDateFormatString("dd/MM/yyyy");
		txtChonNgay.setDate(new Date(System.currentTimeMillis()));
		txtChonNgay.setBounds(81, 29, 142, 20);

		pnlThongTinChung.add(txtChonNgay);

		javax.swing.border.Border southbordert = BorderFactory.createLineBorder(Color.blue);
		TitledBorder southTitleBordert = new TitledBorder(southbordert, "Thông tin chung về thuốc");
		southTitleBordert.setTitleColor(Color.blue);
		scrDSTK.setBorder(new TitledBorder(null, "Danh s\u00E1ch th\u1ED1ng k\u00EA", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		pnlToanPhan.add(scrDSTK);
		scrDSTK.setPreferredSize(new Dimension(0, 250));
		pnlToanPhan.setLayout(null);

		scrDSTK.setBounds(10, 258, 1177, 271);

		JPanel panel_8 = new JPanel();
		scrDSTK.setColumnHeaderView(panel_8);
		panel_8.setBackground(new Color(175, 238, 238));
		panel_8.setLayout(null);
		getContentPane().add(tabbedPane);
		getContentPane().add(tabbedPane);
		javax.swing.border.Border southborder6 = BorderFactory.createLineBorder(Color.blue, Font.BOLD);
		TitledBorder southTitleBorder6 = new TitledBorder(southborder6, "Chức năng");
		southTitleBorder6.setTitleColor(Color.blue);

		panel_12 = new JPanel();
		panel_12.setBackground(new Color(240, 230, 140));
		panel_12.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_12.setBounds(10, 540, 1177, 91);
		pnlToanPhan.add(panel_12);
		panel_12.setLayout(null);

		btnBaoCao = new JButton("Thống Kê");
		btnBaoCao.setBounds(263, 11, 255, 58);
		panel_12.add(btnBaoCao);
		btnBaoCao.setIcon(new ImageIcon("image/thongke.png"));
		btnBaoCao.addActionListener(this);

		btnBaoCao.setForeground(Color.BLACK);
		btnBaoCao.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnBaoCao.setBackground(new Color(0, 206, 209));

		btnXuatBC = new JButton("In Báo Cáo");
		btnXuatBC.setBackground(new Color(0, 206, 209));
		btnXuatBC.setIcon(new ImageIcon("C:\\Users\\TDM\\Downloads\\javaptud\\javaptud\\java1\\image\\Print-icon.png"));
		btnXuatBC.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXuatBC.setBounds(714, 11, 255, 58);
		panel_12.add(btnXuatBC);

		pnlThongkeTTLinhKien = new JPanel();
		pnlThongkeTTLinhKien.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlThongkeTTLinhKien.setBackground(new Color(240, 230, 140));

		tabbedPane.addTab("Thống kê tình trạng linh kiện", new ImageIcon("image/iconthongke.jpg"),
				pnlThongkeTTLinhKien);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 240));
		tabbedPane.setForegroundAt(1, new Color(0, 139, 139));

		pnlThongkeTTLinhKien.setBounds(0, 50, 1100, 650);
		pnlThongkeTTLinhKien.setPreferredSize(new Dimension(0, 240));
		pnlThongkeTTLinhKien.setLayout(null);
		pnlThongkeTTLinhKien.setLayout(null);
		tabbedPane.setBackgroundAt(1, new Color(255, 255, 240));
		tabbedPane.setForegroundAt(1, new Color(0, 139, 139));

		JPanel jp2 = new JPanel();
		jp2.setBounds(10, 107, 515, 239);
		jp2.setBackground(new Color(240, 230, 140));
		pnlThongkeTTLinhKien.add(jp2);
		jp2.setLayout(null);
		jp2.setBorder(new TitledBorder(null, "H\u00ECnh th\u1EE9c th\u1ED1ng k\u00EA", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));

		lblNgayy = new JLabel("Ngày:");
		lblNgayy.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayy.setForeground(new Color(0, 0, 0));
		lblNgayy.setBounds(10, 33, 46, 20);
		jp2.add(lblNgayy);
		//////////////////////////////

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBackground(new Color(240, 230, 140));
		panel_3.setBounds(10, 102, 473, 120);
		jp2.add(panel_3);
		panel_3.setLayout(null);

		btnXemLKDaBan = new JButton(" Xem linh kiện đã bán");
		btnXemLKDaBan.setHorizontalAlignment(SwingConstants.LEFT);
		btnXemLKDaBan.setBounds(22, 27, 425, 65);
		panel_3.add(btnXemLKDaBan);
		btnXemLKDaBan.setBackground(new Color(0, 206, 209));
		btnXemLKDaBan.setForeground(new Color(0, 0, 0));
		btnXemLKDaBan.setIcon(new ImageIcon("image/thongke.png"));
		btnXemLKDaBan.setFont(new Font("Tahoma", Font.BOLD, 16));

		txtChonNgayThongKeLK = new JDateChooser();
		txtChonNgayThongKeLK.getCalendarButton().setForeground(new Color(0, 0, 255));
		txtChonNgayThongKeLK.getCalendarButton().setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txtChonNgayThongKeLK.setLocale(new Locale("vi", "VN"));
		txtChonNgayThongKeLK.setDateFormatString("dd/MM/yyyy");
		txtChonNgayThongKeLK.setBounds(77, 33, 169, 33);
		txtChonNgayThongKeLK.setDate(new Date(System.currentTimeMillis()));

		jp2.add(txtChonNgayThongKeLK);
		JPanel jp3 = new JPanel();
		jp3.setBounds(584, 107, 608, 239);
		jp3.setBackground(new Color(240, 230, 140));
		jp3.setBorder(new TitledBorder(null, "B\u00E1o c\u00E1o sau th\u1ED1ng k\u00EA", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		pnlThongkeTTLinhKien.add(jp3);
		jp3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(240, 230, 140));
		panel_4.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_4.setBounds(23, 45, 563, 177);
		jp3.add(panel_4);
		panel_4.setLayout(null);

		JLabel lblTongSoLuongLK = new JLabel("Tổng số lượng linh kiện:");
		lblTongSoLuongLK.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoLuongLK.setBounds(8, 21, 157, 28);
		panel_4.add(lblTongSoLuongLK);
		lblTongSoLuongLK.setForeground(new Color(0, 0, 0));

		txtTongSoLuongLK = new JTextField();
		txtTongSoLuongLK.setEditable(false);
		txtTongSoLuongLK.setForeground(Color.BLUE);
		txtTongSoLuongLK.setBounds(175, 21, 196, 32);
		panel_4.add(txtTongSoLuongLK);
		txtTongSoLuongLK.setColumns(10);

		JLabel lblTongSoLoaiThuoc = new JLabel("Tổng số loại linh kiện:");
		lblTongSoLoaiThuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoLoaiThuoc.setBounds(8, 77, 143, 17);
		panel_4.add(lblTongSoLoaiThuoc);
		lblTongSoLoaiThuoc.setForeground(new Color(0, 0, 0));

		txtTongSoLoaiLK = new JTextField();
		txtTongSoLoaiLK.setForeground(Color.BLUE);
		txtTongSoLoaiLK.setBounds(175, 71, 196, 32);
		panel_4.add(txtTongSoLoaiLK);
		txtTongSoLoaiLK.setEditable(false);
		txtTongSoLoaiLK.setColumns(10);

		btnXuatLKDB = new JButton("In Báo Cáo");
		btnXuatLKDB
				.setIcon(new ImageIcon("C:\\Users\\TDM\\Downloads\\javaptud\\javaptud\\java1\\image\\Print-icon.png"));
		btnXuatLKDB.setBackground(new Color(0, 206, 209));
		btnXuatLKDB.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnXuatLKDB.setBounds(175, 122, 196, 44);
		panel_4.add(btnXuatLKDB);

		scrollPaneTKTTT = new JScrollPane();
		scrollPaneTKTTT.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPaneTKTTT.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPaneTKTTT.setBounds(10, 355, 1182, 276);
		pnlThongkeTTLinhKien.add(scrollPaneTKTTT);

		table2 = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "STT", "M\u00E3 linh ki\u1EC7n",
				"T\u00EAn linh ki\u1EC7n", "S\u1ED1 l\u01B0\u1EE3ng b\u00E1n", "Ng\u00E0y l\u1EADp" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tablemodel1 = (DefaultTableModel) table2.getModel();
		table2.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		table2.setBackground(new Color(245, 245, 220));
		table2.setForeground(Color.BLUE);
		table2.getTableHeader().setEnabled(false);
		scrollPaneTKTTT.setViewportView(table2);

		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(new Color(255, 250, 205));
		panel.setBounds(10, 25, 1182, 71);
		pnlThongkeTTLinhKien.add(panel);

		JLabel lblT = new JLabel("THỐNG KÊ LINH KIỆN ĐÃ BÁN");
		lblT.setHorizontalAlignment(SwingConstants.CENTER);
		lblT.setForeground(Color.RED);
		lblT.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblT.setBounds(363, 11, 375, 49);
		panel.add(lblT);

		pnlThongKeBaoCao = new JPanel();
		pnlThongKeBaoCao.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		pnlThongKeBaoCao.setBackground(new Color(240, 230, 140));
		tabbedPane.addTab("Thống kê báo cáo         \r\n", new ImageIcon("image/iconthongke.jpg"), pnlThongKeBaoCao);

		pnlThongKeBaoCao.setBounds(0, 50, 1100, 700);
		pnlThongKeBaoCao.setPreferredSize(new Dimension(0, 240));
		pnlThongKeBaoCao.setLayout(null);
		pnlThongKeBaoCao.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 11, 1184, 284);
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		pnlThongKeBaoCao.add(panel_1);
		panel_1.setLayout(null);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 89, 367, 164);
		panel_5.setBackground(new Color(240, 230, 140));
		panel_5.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblNgayTKTQ = new JLabel("Tháng:");
		lblNgayTKTQ.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNgayTKTQ.setBounds(10, 23, 59, 31);
		panel_5.add(lblNgayTKTQ);

		btnXemBCaoTKTQ = new JButton("Thống Kê");
		btnXemBCaoTKTQ.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXemBCaoTKTQ.setIcon(new ImageIcon("image/thongke.png"));
		btnXemBCaoTKTQ.setBackground(new Color(0, 206, 209));
		btnXemBCaoTKTQ.setBounds(74, 100, 243, 53);
		panel_5.add(btnXemBCaoTKTQ);

		comboboxNgay = new JComboBox<String>();
		comboboxNgay.setForeground(new Color(0, 0, 255));
		comboboxNgay.setBounds(79, 23, 65, 31);
		comboboxNgay.addItem("1");
		comboboxNgay.addItem("2");
		comboboxNgay.addItem("3");
		comboboxNgay.addItem("4");
		comboboxNgay.addItem("5");
		comboboxNgay.addItem("6");
		comboboxNgay.addItem("7");
		comboboxNgay.addItem("8");
		comboboxNgay.addItem("9");
		comboboxNgay.addItem("10");
		comboboxNgay.addItem("11");
		comboboxNgay.addItem("12");
		panel_5.add(comboboxNgay);
		comboboxNgay.addActionListener(this);

		JLabel lblNm = new JLabel("Năm:");
		lblNm.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNm.setBounds(165, 24, 59, 28);
		panel_5.add(lblNm);

		cmbNam = new JComboBox<String>();
		cmbNam.setForeground(Color.BLUE);
		cmbNam.setBounds(210, 25, 107, 31);
		panel_5.add(cmbNam);

		cmbNam.addItem("2020");

		JPanel panel_7 = new JPanel();
		panel_7.setBounds(400, 89, 774, 164);
		panel_7.setBackground(new Color(240, 230, 140));
		panel_7.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.add(panel_7);
		panel_7.setLayout(null);

		JPanel pnlThongtinThongKeTQ = new JPanel();
		pnlThongtinThongKeTQ.setBackground(new Color(240, 230, 140));
		pnlThongtinThongKeTQ.setBorder(new TitledBorder(null, "Th\u00F4ng tin th\u1ED1ng k\u00EA ",
				TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlThongtinThongKeTQ.setBounds(10, 11, 425, 142);
		panel_7.add(pnlThongtinThongKeTQ);
		pnlThongtinThongKeTQ.setLayout(null);

		JLabel lblTongSoLuongHD = new JLabel("Tổng số lượng hóa đơn:");
		lblTongSoLuongHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoLuongHD.setBounds(27, 63, 193, 20);
		pnlThongtinThongKeTQ.add(lblTongSoLuongHD);

		txtTongSLHD = new JTextField();
		txtTongSLHD.setForeground(new Color(0, 0, 255));
		txtTongSLHD.setEditable(false);
		txtTongSLHD.setBounds(224, 65, 179, 20);
		pnlThongtinThongKeTQ.add(txtTongSLHD);
		txtTongSLHD.setColumns(10);

		txtTongTienBanDuoc = new JTextField();
		txtTongTienBanDuoc.setForeground(new Color(0, 0, 255));
		txtTongTienBanDuoc.setEditable(false);
		txtTongTienBanDuoc.setColumns(10);
		txtTongTienBanDuoc.setBounds(224, 18, 179, 20);
		pnlThongtinThongKeTQ.add(txtTongTienBanDuoc);

		JLabel lblLoiNhuanThuDuoc = new JLabel("Lợi nhuận thu được:");
		lblLoiNhuanThuDuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblLoiNhuanThuDuoc.setBounds(27, 110, 134, 19);
		pnlThongtinThongKeTQ.add(lblLoiNhuanThuDuoc);

		txtLoiNhuanThuDkTQ = new JTextField();
		txtLoiNhuanThuDkTQ.setForeground(new Color(0, 0, 255));
		txtLoiNhuanThuDkTQ.setEditable(false);
		txtLoiNhuanThuDkTQ.setColumns(10);
		txtLoiNhuanThuDkTQ.setBounds(224, 111, 179, 20);
		pnlThongtinThongKeTQ.add(txtLoiNhuanThuDkTQ);

		JLabel lblTongSoTienBanDuoc = new JLabel("Tổng tiền bán được:");
		lblTongSoTienBanDuoc.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblTongSoTienBanDuoc.setBounds(27, 19, 156, 20);
		pnlThongtinThongKeTQ.add(lblTongSoTienBanDuoc);

		btnXuatBCDT = new JButton("In Báo Cáo");
		btnXuatBCDT.setBackground(new Color(0, 206, 209));
		btnXuatBCDT
				.setIcon(new ImageIcon("C:\\Users\\TDM\\Downloads\\javaptud\\javaptud\\java1\\image\\Print-icon.png"));
		btnXuatBCDT.setFont(new Font("Tahoma", Font.BOLD, 16));
		btnXuatBCDT.setBounds(478, 60, 236, 57);
		panel_7.add(btnXuatBCDT);

		JPanel panel_6 = new JPanel();
		panel_6.setBounds(10, 11, 1164, 71);
		panel_6.setLayout(null);
		panel_6.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_6.setBackground(new Color(255, 250, 205));
		panel_1.add(panel_6);

		JLabel lblThngKTheo_1 = new JLabel("THỐNG KÊ THEO DOANH THU THEO THÁNG");
		lblThngKTheo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblThngKTheo_1.setForeground(Color.RED);
		lblThngKTheo_1.setFont(new Font("Tahoma", Font.PLAIN, 23));
		lblThngKTheo_1.setBounds(259, 11, 500, 37);
		panel_6.add(lblThngKTheo_1);

		JScrollPane scrThongKeTongQuat = new JScrollPane();
		scrThongKeTongQuat.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrThongKeTongQuat.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrThongKeTongQuat.setBounds(10, 306, 1184, 325);
		pnlThongKeBaoCao.add(scrThongKeTongQuat);

		// JScrollPane scrTKTQ;
		tblThongKeTongQuat = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 linh ki\u1EC7n", "T\u00EAn linh ki\u1EC7n",
						"\u0110\u01A1n gi\u00E1 b\u00E1n", "\u0110\u01A1n gi\u00E1 nh\u1EADp",
						"S\u1ED1 l\u01B0\u1EE3ng b\u00E1n", "Ti\u1EC1n b\u00E1n \u0111\u01B0\u1EE3c" }));
		tblThongKeTongQuat.setRowSelectionAllowed(false);
		tblThongKeTongQuat.setColumnSelectionAllowed(true);
		tblThongKeTongQuat.setCellSelectionEnabled(true);
		tblThongKeTongQuat.getTableHeader().setEnabled(false);
		tablemodel2 = (DefaultTableModel) tblThongKeTongQuat.getModel();

		tblThongKeTongQuat.setForeground(new Color(0, 0, 205));
		tblThongKeTongQuat.setBackground(new Color(255, 248, 220));
		tblThongKeTongQuat.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		scrThongKeTongQuat.setViewportView(tblThongKeTongQuat);
		btnXemLKDaBan.addActionListener(this);
		btnXemBCaoTKTQ.addActionListener(this);
		mntmXemChiTiet.addActionListener(this);
		btnXuatBC.addActionListener(this);
		btnXuatBCDT.addActionListener(this);
		btnXuatLKDB.addActionListener(this);

	}

	public void listHoaDonTheoNhanVien(String ma, int ngay, int thang, int nam) {
		int sldb = 0;
		double tiendb = 0;
		ThongKeNhanVienTheoNgay dao = new ThongKeNhanVienTheoNgay();
		ArrayList<ThongKeHDNhanVien> list = null;
		try {
			list = (ArrayList<ThongKeHDNhanVien>) dao.ThongKeHDNhanVienTheoNgay(ma, ngay, thang, nam);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tablemodel.setRowCount(0);
		table_1.getModel();
		for (ThongKeHDNhanVien thongKeHDNhanVien : list) {
			tablemodel.addRow(thongKeHDNhanVien.toVector());
		}
		for (int i = 0; i < tablemodel.getRowCount(); i++) {
			sldb = sldb + Integer.parseInt((String) tablemodel.getValueAt(i, 2));
			tiendb = (tiendb + Double.parseDouble((String) tablemodel.getValueAt(i, 3))) * 1.05;
		}
//		Vector<String> v = new Vector<>();
//		v.add("Tổng tiền bán được:");
//		v.add("");
//		v.add("");
//		v.add(thanhTien + "");
		txtTongSLLKDB.setText("" + sldb);
		txtTongTienDaBan.setText(df.format(tiendb) + " (VND)");
		txtTongLoaiLK.setText("" + tablemodel.getRowCount());
	}

	public void listTinhTrangLK(int ngay, int thang, int nam) {
		ThongKeTinhTrangLKDAO dao = new ThongKeTinhTrangLKDAO();
		ArrayList<ThongKeTinhTrangLK> list = null;
		int sl = 0;
		try {
			list = (ArrayList<ThongKeTinhTrangLK>) dao.ThongKeTTLK(ngay, thang, nam);
			if (!list.isEmpty()) {
				tablemodel1.setRowCount(0);
				table2.getModel();
				for (ThongKeTinhTrangLK thongKeTinhTrangLK : list) {
					tablemodel1.addRow(thongKeTinhTrangLK.toVector());
				}
				for (int i = 0; i < tablemodel1.getRowCount(); i++) {
					tablemodel1.setValueAt(i + 1, i, 0);
					sl = sl + Integer.parseInt((String) tablemodel1.getValueAt(i, 3));
				}
				txtTongSoLoaiLK.setText("" + tablemodel1.getRowCount());
				txtTongSoLuongLK.setText("" + sl);
			} else {
				JOptionPane.showMessageDialog(null,
						"Không có dữ liệu ngày" + " " + ngay + "-" + thang + "-" + nam + " !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private JButton btnXuatBCDT;

	public void listDoanhThuTQ(int thang, int nam) {
		ThongKeDoanhThuTQDAO dao = new ThongKeDoanhThuTQDAO();
		ArrayList<ThongKeDoanhThuDTO> list = null;
		double tongtien = 0;
		int slhd = 0;
		double lnc = 0;
		double ln = 0;
		double bd = 0;

		try {
			list = (ArrayList<ThongKeDoanhThuDTO>) dao.ThongKeDoanhThuTQ(thang, nam);
			if (!list.isEmpty()) {
				tablemodel2.setRowCount(0);
				tblThongKeTongQuat.getModel();
				for (ThongKeDoanhThuDTO thongKeDoanhThuDTO : list) {
					tablemodel2.addRow(thongKeDoanhThuDTO.toVector());
					tongtien += thongKeDoanhThuDTO.getTienBanDuoc();
					slhd += thongKeDoanhThuDTO.getSoluonghd();

					bd = thongKeDoanhThuDTO.getTienBanDuoc();
					ln = bd - (thongKeDoanhThuDTO.getSoluongban() * thongKeDoanhThuDTO.getGiaNhap());

					lnc += ln;
				}
				for (int i = 0; i < tablemodel2.getRowCount(); i++) {
					tablemodel2.setValueAt(i + 1, i, 0);
				}
				txtTongSLHD.setText(slhd + "");
				txtTongTienBanDuoc.setText(df.format(tongtien));
				txtLoiNhuanThuDkTQ.setText(df.format(lnc));
			} else {
				JOptionPane.showMessageDialog(null,
						"Không có dự liệu tháng " + thang + " " + "năm" + " " + nam + " !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void addtoComboBoxNhanVien() {
		dao = new NhanVienDAO();
		try {
			ArrayList<NhanVienDTO> list = (ArrayList<NhanVienDTO>) dao.dsNhanVien();
			pnlTT.add(cmbMaNV);
			cmbMaNV.addItem("");
			for (NhanVienDTO nhaVienDTO : list) {
				cmbMaNV.addItem(nhaVienDTO.getMaNV() + "-" + nhaVienDTO.getTenNV());
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override

	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnBaoCao)) {

			if (cmbMaNV.getSelectedItem().equals("")) {
				Calendar ngayCld = Calendar.getInstance();
				ngayCld.setTime(txtChonNgay.getDate());

				int ngay = ngayCld.get(Calendar.DATE);
				int thang = ngayCld.get(Calendar.MONTH) + 1;
				int nam = ngayCld.get(Calendar.YEAR);

				listHoaDonTheoNhanVien(null, ngay, thang, nam);
			} else {
				String[] matennv = cmbMaNV.getSelectedItem().toString().split("-");
				String manv = matennv[0];
				txtTenNV.setText(matennv[1]);
				Calendar ngayCld = Calendar.getInstance();
				ngayCld.setTime(txtChonNgay.getDate());

				int ngay = ngayCld.get(Calendar.DATE);
				int thang = ngayCld.get(Calendar.MONTH) + 1;
				int nam = ngayCld.get(Calendar.YEAR);

				listHoaDonTheoNhanVien(manv, ngay, thang, nam);
			}
		} else if (obj.equals(btnXemLKDaBan)) {
			Calendar ngayCld = Calendar.getInstance();
			ngayCld.setTime(txtChonNgayThongKeLK.getDate());

			int ngay = ngayCld.get(Calendar.DATE);
			int thang = ngayCld.get(Calendar.MONTH) + 1;
			int nam = ngayCld.get(Calendar.YEAR);

			listTinhTrangLK(ngay, thang, nam);
		} else if (obj.equals(btnXemBCaoTKTQ)) {

			int thang = Integer.parseInt((String) comboboxNgay.getSelectedItem());
			int nam = Integer.parseInt((String) cmbNam.getSelectedItem());

			listDoanhThuTQ(thang, nam);

		} else if (obj.equals(btnXuatBCDT)) {
			int thang = Integer.parseInt((String) comboboxNgay.getSelectedItem());
			int nam = Integer.parseInt((String) cmbNam.getSelectedItem());
			MessageFormat header = new MessageFormat("BÁO CÁO DOANH THU THÁNG " + thang + " NĂM " + nam);

			MessageFormat footer = new MessageFormat("Page{0,number,integer}");
			try {

				tblThongKeTongQuat.print(JTable.PrintMode.FIT_WIDTH, header, footer);

			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (obj.equals(btnXuatBC)) {
			Calendar ngayCld = Calendar.getInstance();
			ngayCld.setTime(txtChonNgay.getDate());

			int ngay = ngayCld.get(Calendar.DATE);
			int thang = ngayCld.get(Calendar.MONTH) + 1;
			int nam = ngayCld.get(Calendar.YEAR);

			if (cmbMaNV.getSelectedItem().equals("")) {
				MessageFormat header = new MessageFormat(
						"LINH KIỆN NHÂN VIÊN BÁN ĐƯỢC " + ngay + " THÁNG " + thang + " NĂM " + nam);
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					table2.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				String[] matennv = cmbMaNV.getSelectedItem().toString().split("-");
				String tnv = matennv[1];
				MessageFormat header = new MessageFormat(
						"LINH KIỆN " + tnv + " BÁN ĐƯỢC " + ngay + " THÁNG " + thang + " NĂM " + nam);
				MessageFormat footer = new MessageFormat("Page{0,number,integer}");
				try {
					table_1.print(JTable.PrintMode.FIT_WIDTH, header, footer);
				} catch (PrinterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (obj.equals(btnXuatLKDB)) {
			Calendar ngayCld = Calendar.getInstance();
			ngayCld.setTime(txtChonNgayThongKeLK.getDate());

			int ngay = ngayCld.get(Calendar.DATE);
			int thang = ngayCld.get(Calendar.MONTH) + 1;
			int nam = ngayCld.get(Calendar.YEAR);
//			BaoCao bc = new BaoCao();

//			bc.xuatFileExcelThongKeLinhKienDaBan(ngay, thang, nam);
			MessageFormat header = new MessageFormat(
					"BÁO CÁO LINH KIỆN ĐÃ BÁN " + ngay + " THÁNG " + thang + " NĂM " + nam);
			MessageFormat footer = new MessageFormat("Page{0,number,integer}");
			try {
				table2.print(JTable.PrintMode.FIT_WIDTH, header, footer);
			} catch (PrinterException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

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
}
