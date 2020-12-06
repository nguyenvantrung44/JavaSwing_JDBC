/**
 * Người làm: Nguyen Van Trung
 */
package project.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.Toolkit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import com.toedter.calendar.JDateChooser;

import project.daos.CT_HoaDonDAO;
import project.daos.HoaDonDAO;
import project.dtos.CT_HoaDonDTO;
import project.dtos.HoaDonDTO;

public class FrmQuanLyHoaDon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	private JTextField txtMa;
	private JTextField txtTenKH;
	private JTextField txtNVLap;
	private JTable tblHoaDon;
	private JTextField txtTongTien;
	private JTable tblChiTiet;

	private DefaultTableModel tblModelHoaDon = new DefaultTableModel();
	private DefaultTableModel tblModelChiTiet = new DefaultTableModel();

	private JTextField txtNgayLap;
	private JRadioButton radMaHD, radTenKH, radTenNV;

	private JScrollPane scrHoaDon;

	private HoaDonDAO dao = new HoaDonDAO();
	private JTextField txtTim;
	private JDateChooser date;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					FrmQuanLyHoaDon frame = new FrmQuanLyHoaDon();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public FrmQuanLyHoaDon() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("Hinh\\pm.png"));
		setType(Type.POPUP);
		setResizable(false);
		setForeground(new Color(176, 224, 230));
		setBackground(new Color(176, 224, 230));
		setTitle("Phần mềm quản lý linh kiện");
		setBounds(100, 100, 1200, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pnlThongTinHD = new JPanel();
		pnlThongTinHD.setBackground(new Color(240, 230, 140));
		pnlThongTinHD.setBorder(
				new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Th\u00F4ng tin h\u00F3a \u0111\u01A1n",
						TitledBorder.LEFT, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnlThongTinHD.setBounds(10, 45, 482, 208);
		contentPane.add(pnlThongTinHD);
		pnlThongTinHD.setLayout(null);

		JLabel lblMHan = new JLabel("Mã hóa đơn:");
		lblMHan.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblMHan.setBounds(10, 20, 122, 30);
		pnlThongTinHD.add(lblMHan);

		txtMa = new JTextField();
		txtMa.setEnabled(false);
		txtMa.setBounds(155, 22, 285, 30);
		pnlThongTinHD.add(txtMa);
		txtMa.setColumns(10);

		JLabel lblTnKhchHng = new JLabel("Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTnKhchHng.setBounds(10, 132, 147, 30);
		pnlThongTinHD.add(lblTnKhchHng);

		txtTenKH = new JTextField();
		txtTenKH.setEditable(false);
		txtTenKH.setBounds(155, 132, 285, 30);
		pnlThongTinHD.add(txtTenKH);
		txtTenKH.setColumns(10);

		JLabel lblNhanVien = new JLabel("Nhân viên :");
		lblNhanVien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNhanVien.setBounds(10, 98, 122, 30);
		pnlThongTinHD.add(lblNhanVien);

		txtNVLap = new JTextField();
		txtNVLap.setEditable(false);
		txtNVLap.setBounds(154, 98, 285, 30);
		pnlThongTinHD.add(txtNVLap);
		txtNVLap.setColumns(10);

		JLabel lblNgayLap = new JLabel("Ngày lập:");
		lblNgayLap.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblNgayLap.setBounds(10, 58, 100, 30);
		pnlThongTinHD.add(lblNgayLap);

		JLabel lblTongtien = new JLabel("Tổng Tiền:");
		lblTongtien.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblTongtien.setBounds(10, 171, 100, 30);
		pnlThongTinHD.add(lblTongtien);

		txtTongTien = new JTextField();
		txtTongTien.setColumns(10);
		txtTongTien.setBounds(155, 165, 285, 30);
		pnlThongTinHD.add(txtTongTien);

		txtNgayLap = new JTextField();
		txtNgayLap.setColumns(10);
		txtNgayLap.setBounds(154, 58, 285, 30);
		pnlThongTinHD.add(txtNgayLap);

		JPanel pnlChucNang = new JPanel();
		pnlChucNang.setBackground(new Color(240, 230, 140));
		pnlChucNang.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"C\u00E1c ch\u1EE9c n\u0103ng", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlChucNang.setBounds(10, 473, 1174, 187);
		contentPane.add(pnlChucNang);
		pnlChucNang.setLayout(null);

		JPanel pnlTimKiem = new JPanel();
		pnlTimKiem.setBackground(new Color(240, 230, 140));
		pnlTimKiem.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"T\u00ECm ki\u1EBFm h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlTimKiem.setBounds(29, 21, 1119, 114);
		pnlChucNang.add(pnlTimKiem);
		pnlTimKiem.setLayout(null);

		JButton btnTimKiem = new JButton("Tìm kiếm");
		btnTimKiem.setBounds(973, 18, 136, 30);
		pnlTimKiem.add(btnTimKiem);
		btnTimKiem.setFont(new Font("Times New Roman", Font.PLAIN, 16));

		JLabel lblNhpThngTin = new JLabel("Nhập thông tin tìm kiếm:");
		lblNhpThngTin.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNhpThngTin.setBounds(20, 18, 162, 30);
		pnlTimKiem.add(lblNhpThngTin);

		JLabel lblTmTheo = new JLabel("Tìm theo:");
		lblTmTheo.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblTmTheo.setBounds(20, 61, 63, 30);
		pnlTimKiem.add(lblTmTheo);

		radTenKH = new JRadioButton("Tên Khách Hàng ");
		radTenKH.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radTenKH.setBounds(320, 62, 141, 29);
		pnlTimKiem.add(radTenKH);

		radMaHD = new JRadioButton("Mã Hóa Đơn");
		radMaHD.setBounds(184, 61, 122, 30);
		pnlTimKiem.add(radMaHD);
		radMaHD.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radMaHD.setSelected(true);

		radTenNV = new JRadioButton("Tên nhân viên");
		radTenNV.setFont(new Font("Tahoma", Font.PLAIN, 15));
		radTenNV.setBounds(481, 62, 129, 29);
		pnlTimKiem.add(radTenNV);

		JPanel pnlHoaDon = new JPanel();
		pnlHoaDon.setLayout(null);
		pnlHoaDon.setForeground(Color.BLACK);
		pnlHoaDon.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Danh s\u00E1ch h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		pnlHoaDon.setBackground(new Color(240, 230, 140));
		pnlHoaDon.setBounds(10, 254, 1174, 208);
		contentPane.add(pnlHoaDon);

		scrHoaDon = new JScrollPane();
		scrHoaDon.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrHoaDon.setBounds(23, 22, 1128, 172);
		pnlHoaDon.add(scrHoaDon);

		tblHoaDon = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "M\u00E3 h\u00F3a \u0111\u01A1n", "Ng\u00E0y l\u1EADp", "T\u1ED5ng ti\u1EC1n",
						"T\u00EAn kh\u00E1ch h\u00E0ng", "Nh\u00E2n vi\u00EAn l\u1EADp" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblModelHoaDon = (DefaultTableModel) tblHoaDon.getModel();

		tblHoaDon.getTableHeader().setEnabled(false);
		scrHoaDon.setViewportView(tblHoaDon);

		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBackground(new Color(240, 230, 140));
		pnlChiTiet.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"),
				"Th\u00F4ng tin chi ti\u1EBFt h\u00F3a \u0111\u01A1n", TitledBorder.LEADING, TitledBorder.TOP, null,
				new Color(255, 0, 0)));
		pnlChiTiet.setBounds(520, 45, 664, 208);
		contentPane.add(pnlChiTiet);
		pnlChiTiet.setLayout(null);

		JScrollPane scrChiTiet = new JScrollPane();
		scrChiTiet.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrChiTiet.setBounds(30, 21, 611, 176);
		pnlChiTiet.add(scrChiTiet);

		tblChiTiet = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "STT", "T\u00EAn linh ki\u1EC7n", "\u0110\u01A1n v\u1ECB t\u00EDnh",
						"\u0110\u01A1n gi\u00E1", "S\u1ED1 l\u01B0\u1EE3ng", "Th\u00E0nh ti\u1EC1n" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});
		tblModelChiTiet = (DefaultTableModel) tblChiTiet.getModel();
		tblChiTiet.setFont(new Font("Tahoma", Font.BOLD, 11));
		tblChiTiet.getTableHeader().setEnabled(false);
		scrChiTiet.setViewportView(tblChiTiet);
		ButtonGroup group = new ButtonGroup();
		group.add(radMaHD);
		group.add(radTenNV);
		group.add(radTenKH);

		txtTim = new JTextField();
		txtTim.setBounds(184, 18, 426, 30);
		pnlTimKiem.add(txtTim);
		txtTim.setColumns(10);

		date = new JDateChooser();
		date.setBounds(767, 18, 136, 30);
		pnlTimKiem.add(date);

		JLabel lblNewLabel = new JLabel("Ngày lập :");
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		lblNewLabel.setBounds(672, 21, 85, 24);
		pnlTimKiem.add(lblNewLabel);

		JButton btnLamMoi = new JButton("Làm mới");
		btnLamMoi.setBackground(new Color(0, 206, 209));
		btnLamMoi.setHorizontalAlignment(SwingConstants.LEFT);
		btnLamMoi.setIcon(new ImageIcon("image/refresh.png"));
		btnLamMoi.setFont(new Font("Times New Roman", Font.BOLD, 16));
		btnLamMoi.setBounds(538, 137, 135, 39);
		pnlChucNang.add(btnLamMoi);

		JLabel lblTieuDe = new JLabel("Xem Hóa Đơn");
		lblTieuDe.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.RED);
		lblTieuDe.setBounds(440, 11, 154, 23);
		contentPane.add(lblTieuDe);

		getHoaDon();
		tblHoaDon.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					cilckMouse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});
		btnLamMoi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				loadTable();
			}
		});
		btnTimKiem.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TimKiem();
			}
		});
	}

	public void xoaTableChiTiet() {
		tblModelChiTiet.addRow(new Object[] {

		});
		DefaultTableModel tblModel1 = (DefaultTableModel) tblChiTiet.getModel();
		tblModel1.getDataVector().removeAllElements();
	}

	/**
	 * Xóa dữ liệu bảng hóa đơn
	 */
	public void xoaTableHoaDon() {
		tblModelHoaDon.addRow(new Object[] {

		});
		DefaultTableModel tblModel2 = (DefaultTableModel) tblHoaDon.getModel();
		tblModel2.getDataVector().removeAllElements();
	}

	/**
	 * làm mởi lại giao diện
	 */
	public void lamMoi() {
		txtMa.setText("");
		txtNgayLap.setText("");
		txtNVLap.setText("");
		txtTenKH.setText("");
		txtTongTien.setText("");
		xoaTableChiTiet();
		xoaTableHoaDon();
		txtTim.setText("");
		date.setDate(null);
	}

	public void loadTable() {
		txtMa.setText("");
		txtNgayLap.setText("");
		txtNVLap.setText("");
		txtTenKH.setText("");
		txtTongTien.setText("");
		xoaTableChiTiet();
		txtTim.setText("");
		getHoaDon();
	}

	public void getHoaDon() {
		dao = new HoaDonDAO();
		ArrayList<HoaDonDTO> list = null;
		try {
			list = (ArrayList<HoaDonDTO>) dao.dsHoaDon();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tblModelHoaDon.setRowCount(0);
		for (HoaDonDTO hoaDonDTO : list) {
			tblModelHoaDon.addRow(hoaDonDTO.toVector1());
		}
		for (int i = 0; i < tblModelHoaDon.getRowCount(); i++) {
			tblModelHoaDon.setValueAt(i + 1, i, 0);
		}
	}

	SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");
	DecimalFormat df = new DecimalFormat("#,###.0 (VND)");

	public void cilckMouse() {
		int row = tblHoaDon.getSelectedRow();
		CT_HoaDonDAO dao = new CT_HoaDonDAO();
		HoaDonDTO dto = new HoaDonDTO();

		txtMa.setText(tblHoaDon.getValueAt(row, 1).toString());
		try {
			dto = dao.layThongTinCT_HDByMa(tblHoaDon.getValueAt(row, 1).toString());
			txtNgayLap.setText(sdf2.format(dto.getNgayLapHD()));
			txtNVLap.setText(dto.getTenNV());
			txtTenKH.setText(dto.getTenKH());
			txtTongTien.setText(df.format(dto.getTongTien()));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		getCT_HoaDonTheoMa(tblHoaDon.getValueAt(row, 1).toString());

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
		tblModelChiTiet.setRowCount(0);
		for (CT_HoaDonDTO ct_HoaDonDTO : list) {
			tblModelChiTiet.addRow(ct_HoaDonDTO.toVector());
		}
		for (int i = 0; i < tblModelChiTiet.getRowCount(); i++) {
			tblModelChiTiet.setValueAt(i + 1, i, 0);
		}
	}

	public void TimKiem() {
		if (radMaHD.isSelected()) {
			HoaDonDAO dao = new HoaDonDAO();
			List<HoaDonDTO> list = null;
			String ma = txtTim.getText();
			try {
				list = dao.dsHoaDonByMaHD(ma);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lamMoi();
			for (HoaDonDTO hoaDonDTO : list) {
				tblModelHoaDon.addRow(hoaDonDTO.toVector1());
			}
			for (int i = 0; i < tblModelHoaDon.getRowCount(); i++) {
				tblModelHoaDon.setValueAt(i + 1, i, 0);
			}
		} else if (radTenKH.isSelected()) {
			HoaDonDAO dao = new HoaDonDAO();
			List<HoaDonDTO> list = null;
			String ten = txtTim.getText();
			try {
				list = dao.dsHoaDonByTenKH(ten);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lamMoi();
			for (HoaDonDTO hoaDonDTO : list) {
				tblModelHoaDon.addRow(hoaDonDTO.toVector1());
			}
			for (int i = 0; i < tblModelHoaDon.getRowCount(); i++) {
				tblModelHoaDon.setValueAt(i + 1, i, 0);
			}
		} else if (radTenNV.isSelected()) {
			HoaDonDAO dao = new HoaDonDAO();
			List<HoaDonDTO> list = null;
			String ten = txtTim.getText();
			try {
				list = dao.dsHoaDonByTenNV(ten);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lamMoi();
			for (HoaDonDTO hoaDonDTO : list) {
				System.out.println(hoaDonDTO.toString());
				tblModelHoaDon.addRow(hoaDonDTO.toVector1());
			}
			for (int i = 0; i < tblModelHoaDon.getRowCount(); i++) {
				tblModelHoaDon.setValueAt(i + 1, i, 0);
			}
		}
		if (date.getDate() != null) {
			Calendar ngayCld = Calendar.getInstance();
			ngayCld.setTime(date.getDate());

			int ngay = ngayCld.get(Calendar.DATE);
			int thang = ngayCld.get(Calendar.MONTH) + 1;
			int nam = ngayCld.get(Calendar.YEAR);
			HoaDonDAO dao = new HoaDonDAO();
			List<HoaDonDTO> list = null;
			try {
				list = dao.dsHoaDonByNgayLap(ngay, thang, nam);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lamMoi();
			for (HoaDonDTO hoaDonDTO : list) {
				tblModelHoaDon.addRow(hoaDonDTO.toVector1());
			}
			for (int i = 0; i < tblModelHoaDon.getRowCount(); i++) {
				tblModelHoaDon.setValueAt(i + 1, i, 0);
			}
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub

	}
}
