package project.views;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.daos.DonViTinhDAO;
import project.daos.NhaCungCapDAO;
import project.daos.QuocGiaDAO;
import project.daos.SanPhamDAO;
import project.dtos.DonViTinhDTO;
import project.dtos.NhaCungCapDTO;
import project.dtos.QuocGiaDTO;
import project.dtos.SanPhamDTO;
import javax.swing.border.TitledBorder;
import javax.swing.UIManager;
import java.awt.Toolkit;

public class SanPham_JFrame extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;

	public static String role;
	public static String maNV;

	public static JLabel labelManhom = new JLabel("Mã nhóm:");
	public static JTextField txMaNhom = new JTextField();

	public static JLabel labelTennhom = new JLabel("Tên nhóm:");
	public static JTextField txTenNhom = new JTextField();

	public static JTextField txTimNhomSanPham = new JTextField();

	public static JTabbedPane tabbedPane = new JTabbedPane();

	public static JPanel panel_2 = new JPanel();
	public static JTable table2 = new JTable(new DefaultTableModel(new Object[][] {},
			new String[] { "STT", "Tên Sản Phẩm", "Giá Gốc", "Giá Bán", "Số Lượng", "Đơn Vị Tính" }) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		boolean[] columnEditables = new boolean[] { false, false, false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});

	public static JScrollPane scrollPane2 = new JScrollPane(table2);

	private JButton buttonTim2 = new JButton("Tìm kiếm :");
	public static JTextField txTimSanPham = new JTextField();

	private JButton buttonThem2 = new JButton("Thêm");;
	private JButton buttonCapnhat2 = new JButton("Cập nhật");
	private JButton buttonXoa2 = new JButton("Xóa");
	private JButton buttonXoaTr2 = new JButton("Xóa trắng");
	private JButton btnTim = new JButton("Tìm Kiếm");
	private JButton btnThoat = new JButton("Thoát Tìm Kiếm");

	public static JLabel labelMaSp = new JLabel("Mã sản phẩm:");
	public static JTextField txMaSP = new JTextField();

	public static JLabel labelTenSp = new JLabel("Tên sản phẩm:");
	public static JTextField txTenSP = new JTextField();

	public static JLabel labelQuocGia2 = new JLabel("Quốc gia:");

	public static JLabel labelMaNCC = new JLabel("Tên nhà cung cấp:");

	public static JLabel labelDV = new JLabel("Đơn vị:");

	private JLabel labelGia = new JLabel("Giá gốc:");
	public static JTextField txGiaGoc = new JTextField();

	public JLabel lblNewLabel_3 = new JLabel("Giá bán:");
	public static JTextField txGiaBan = new JTextField();

	private JLabel labelSLTon = new JLabel("Số lượng tồn:");
	public static JTextField txSoLuongTon = new JTextField();

	private final JLabel labelMoTa = new JLabel("Mô Tả:");
	public static JTextArea txMoTa = new JTextArea();
	public static JTextField txNgayKT2;
	public static DefaultTableModel dtm2;

	public static JComboBox<String> cbMaNCC = new JComboBox<String>();
	public static JComboBox<String> cbbQG = new JComboBox<String>();
	public static JComboBox<String> cbDonViTinh = new JComboBox<String>();

	/**
	 * Create the frame.
	 * 
	 * @param role
	 * @param maNV
	 */

	public static void main(String[] args) {
		new SanPham_JFrame("admin", "MNV001").setVisible(true);
	}

	public SanPham_JFrame(String role, String maNV) {
		setIconImage(Toolkit.getDefaultToolkit()
				.getImage("image/background.jpg"));
		setTitle("Danh Sách Linh Kiện");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1190, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setBackground(new Color(0, 0, 0));

		table2.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));

		table2.getTableHeader().setOpaque(false);
		table2.getTableHeader().setBackground(new Color(32, 136, 203));
		table2.getTableHeader().setForeground(new Color(255, 255, 255));
		table2.getTableHeader().setEnabled(false);
		table2.setRowHeight(25);
		table2.setSelectionBackground(new Color(232, 57, 95));
		table2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// buttonThem2.setVisible(false);
				int row = table2.getSelectedRow();
				String tenSP = (String) dtm2.getValueAt(row, 1);

				SanPhamDAO dao = new SanPhamDAO();
				NhaCungCapDAO dao1 = new NhaCungCapDAO();
				DonViTinhDAO dao2 = new DonViTinhDAO();
				QuocGiaDAO dao3 = new QuocGiaDAO();

				try {
					SanPhamDTO dto = dao.layThongTinSanPham(tenSP);
					NhaCungCapDTO dto1 = dao1.layThongTinNhaCungCapBySP(tenSP);

					DonViTinhDTO dto2 = dao2.layThongTinDonViTinhBySP(tenSP);
					QuocGiaDTO dto3 = dao3.layThongTinQuocGiaBySP(tenSP);

					DecimalFormat df = new DecimalFormat("#,###.# (VND)");
					txMaSP.setText(dto.getMaSP());
					txTenSP.setText(tenSP);
					txGiaGoc.setText(dto.getGiaGoc() + "");
					txSoLuongTon.setText(dto.getSoLuongTon() + "");
					txMoTa.setText(dto.getMoTa());
					txGiaBan.setText(df.format(dto.getGiaBan()) + "");

					cbbQG.setSelectedItem(dto3.getMaQG() + "-" + dto3.getTenQG());

					cbDonViTinh.setSelectedItem(dto2.getMaDVT() + "-" + dto2.getTenDVT());

					cbMaNCC.setSelectedItem(dto1.getMaNCC() + "-" + dto1.getTenNCC());

					SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-YYYY");
					if (dto.getThoiGianKhoiTao() == null) {
						txNgayKT2.setText("");
					} else {
						txNgayKT2.setText(sdf2.format(dto.getThoiGianKhoiTao()));
					}
					txMaSP.setEditable(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		buildUI();
		dtm2 = (DefaultTableModel) table2.getModel();

		getSanPham();
		addtoComboBoxDonViTinh();
		addtoComboBoxNhaCungCap();
		addtoComboBoxQuocGia();
		SanPham_JFrame.role = role;
		SanPham_JFrame.maNV = maNV;
	}

	private void buildUI() {
		getContentPane().setLayout(null);
		panel_2 = new JPanel();
		panel_2.setBackground(new Color(240, 230, 140));
		panel_2.setBounds(0, 0, 1180, 660);

		getContentPane().add(panel_2);
		panel_2.setPreferredSize(new Dimension(0, 250));
		panel_2.setLayout(null);

		scrollPane2.setBounds(20, 10, 1138, 350);
		buttonTim2.setIcon(new ImageIcon("image/tim.png"));
		buttonTim2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonTim2.setBackground(Color.GREEN);

		buttonTim2.setBounds(20, 368, 120, 30);
		txTimSanPham.setBounds(160, 368, 250, 32);
		buttonXoaTr2.setIcon(new ImageIcon("image/tay.png"));
		buttonXoaTr2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonXoaTr2.setBackground(Color.GREEN);

		buttonXoaTr2.setBounds(839, 367, 149, 33);
		buttonThem2.setIcon(new ImageIcon("image/iconAdd.png"));
		buttonThem2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonThem2.setBackground(Color.GREEN);

		buttonThem2.setBounds(661, 367, 135, 33);
		buttonCapnhat2.setIcon(new ImageIcon("image/update.png"));
		buttonCapnhat2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonCapnhat2.setBackground(Color.GREEN);

		buttonCapnhat2.setBounds(1031, 368, 127, 30);
		buttonXoa2.setIcon(new ImageIcon("image/delete.png"));
		buttonXoa2.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonXoa2.setBackground(Color.GREEN);
		buttonXoa2.setVisible(false);

		buttonXoa2.setBounds(535, 368, 109, 30);
		labelMaSp.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelMaSp.setBounds(22, 427, 80, 20);
		txMaSP.setEnabled(false);
		txMaSP.setBounds(160, 422, 250, 30);
		labelTenSp.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelTenSp.setBounds(20, 468, 99, 20);
		txTenSP.setBounds(160, 463, 250, 30);
		labelQuocGia2.setFont(new Font("Tahoma", Font.BOLD, 12));

		labelQuocGia2.setBounds(535, 426, 80, 20);
		labelMaNCC.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelMaNCC.setBounds(20, 513, 109, 20);
		labelDV.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelDV.setBounds(535, 468, 46, 20);
		labelGia.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelGia.setBounds(861, 427, 56, 20);
		txGiaGoc.setBounds(985, 422, 173, 30);
		labelSLTon.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelSLTon.setBounds(535, 513, 89, 20);
		txSoLuongTon.setBounds(661, 509, 161, 28);
		panel_2.setBackground(new Color(240, 230, 140));

		cbMaNCC.setBounds(160, 508, 250, 30);

		panel_2.add(scrollPane2);
		panel_2.add(buttonTim2);
		panel_2.add(txTimSanPham);
		panel_2.add(buttonXoaTr2);
		panel_2.add(buttonThem2);
		panel_2.add(buttonCapnhat2);
		panel_2.add(buttonXoa2);
		panel_2.add(labelMaSp);
		panel_2.add(txMaSP);
		panel_2.add(labelTenSp);
		panel_2.add(txTenSP);
		panel_2.add(labelQuocGia2);
		panel_2.add(labelMaNCC);
		panel_2.add(labelDV);
		panel_2.add(labelGia);
		panel_2.add(txGiaGoc);
		panel_2.add(labelSLTon);
		panel_2.add(txSoLuongTon);
		panel_2.setLayout(null);
		panel_2.add(cbMaNCC);
		JLabel labelNgayKT2 = new JLabel("Ngày Nhập:");
		labelNgayKT2.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelNgayKT2.setBounds(864, 515, 71, 16);
		panel_2.add(labelNgayKT2);

		txNgayKT2 = new JTextField();
		txNgayKT2.setBounds(985, 508, 173, 30);
		panel_2.add(txNgayKT2);
		txNgayKT2.setEditable(false);
		txNgayKT2.setColumns(10);
		labelMoTa.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelMoTa.setBounds(20, 551, 56, 16);

		panel_2.add(labelMoTa);
		txMoTa.setBounds(160, 547, 250, 86);

		panel_2.add(txMoTa);

		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_3.setBounds(861, 470, 46, 17);
		panel_2.add(lblNewLabel_3);

		table2.getTableHeader().setOpaque(false);
		table2.getTableHeader().setBackground(new Color(32, 136, 203));
		table2.getTableHeader().setForeground(new Color(255, 255, 255));
		table2.setSelectionBackground(new Color(232, 57, 95));

		cbDonViTinh.setBounds(661, 466, 161, 25);
		panel_2.add(cbDonViTinh);

		cbbQG.setBounds(661, 423, 159, 29);
		panel_2.add(cbbQG);

		txGiaBan = new JTextField();
		txGiaBan.setEditable(false);
		txGiaBan.setBounds(985, 463, 173, 30);
		panel_2.add(txGiaBan);
		txGiaBan.setColumns(10);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh t\u00ECm ki\u1EBFm",
				TitledBorder.LEADING, TitledBorder.TOP, null, Color.RED));
		panel.setBounds(535, 548, 635, 90);
		panel_2.add(panel);
		panel.setLayout(null);

		btnTim = new JButton();
		btnTim.setText("Tìm Kiếm");
		btnTim.setBounds(128, 27, 159, 34);
		btnTim.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnTim);

		btnThoat = new JButton();
		btnThoat.setText("Thoát Tìm Kiếm");
		btnThoat.setBounds(449, 27, 176, 34);
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		panel.add(btnThoat);

		buttonTim2.addActionListener(this);
		buttonXoaTr2.addActionListener(this);
		buttonThem2.addActionListener(this);
		buttonCapnhat2.addActionListener(this);
		buttonXoa2.addActionListener(this);

		btnTim.addActionListener(this);
		btnThoat.addActionListener(this);

	}

	public void demSoThuTu(DefaultTableModel dtm) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void getSanPham() {
		SanPhamDAO dao = new SanPhamDAO();
		ArrayList<SanPhamDTO> list = null;
		try {
			list = (ArrayList<SanPhamDTO>) dao.dsSanPham();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm2.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm2.addRow(sanPhamDTO.toVector());
		}
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			dtm2.setValueAt(i + 1, i, 0);
		}
	}

	public static void getTimKiem(ArrayList<SanPhamDTO> list1) {
		ArrayList<SanPhamDTO> list = null;
		try {
			list = list1;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm2 = (DefaultTableModel) table2.getModel();
		dtm2.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm2.addRow(sanPhamDTO.toVector4());
		}
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			dtm2.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemSanPham(String id, String maNCC) {
		SanPhamDAO dao = new SanPhamDAO();
		ArrayList<SanPhamDTO> list = null;
		try {
			list = (ArrayList<SanPhamDTO>) dao.timkiemSanPham(id, maNCC);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm2 = (DefaultTableModel) table2.getModel();

		dtm2.setRowCount(0);
		for (SanPhamDTO sanPhamDTO : list) {
			dtm2.addRow(sanPhamDTO.toVector());
		}
		for (int i = 0; i < dtm2.getRowCount(); i++) {
			dtm2.setValueAt(i + 1, i, 0);
		}
	}

	private void addtoComboBoxDonViTinh() {
		DonViTinhDAO dao = new DonViTinhDAO();
		try {
			ArrayList<DonViTinhDTO> list = (ArrayList<DonViTinhDTO>) dao.dsDonViTinh();
			cbDonViTinh.removeAllItems();
			cbDonViTinh.addItem("");
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "" });
			for (DonViTinhDTO donViTinhDTO : list) {
				if (model.getIndexOf(donViTinhDTO.getMaDVT() + "-" + donViTinhDTO.getTenDVT()) == -1) {
					model.addElement(donViTinhDTO.getMaDVT() + "-" + donViTinhDTO.getTenDVT());
					cbDonViTinh.addItem(donViTinhDTO.getMaDVT() + "-" + donViTinhDTO.getTenDVT());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addtoComboBoxQuocGia() {
		QuocGiaDAO dao = new QuocGiaDAO();
		try {
			ArrayList<QuocGiaDTO> list = (ArrayList<QuocGiaDTO>) dao.dsQuocGia();
			cbbQG.removeAllItems();
			cbbQG.addItem("");
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "" });
			for (QuocGiaDTO quocGiaDTO : list) {
				if (model.getIndexOf(quocGiaDTO.getMaQG() + "-" + quocGiaDTO.getTenQG()) == -1) {
					model.addElement(quocGiaDTO.getMaQG() + "-" + quocGiaDTO.getTenQG());
					cbbQG.addItem(quocGiaDTO.getMaQG() + "-" + quocGiaDTO.getTenQG());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// -- ComboBox Nhà Cung Cấp
	private void addtoComboBoxNhaCungCap() {
		NhaCungCapDAO dao = new NhaCungCapDAO();
		try {
			ArrayList<NhaCungCapDTO> list = (ArrayList<NhaCungCapDTO>) dao.dsNhaCungCap();
			cbMaNCC.removeAllItems();
			cbMaNCC.addItem("");
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "" });
			for (NhaCungCapDTO nhaCungCapDTO : list) {
				if (model.getIndexOf(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC()) == -1) {
					model.addElement(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC());
					cbMaNCC.addItem(nhaCungCapDTO.getMaNCC() + "-" + nhaCungCapDTO.getTenNCC());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void xoaTrangSanPham() {
		txMaSP.setEditable(true);
		txMaSP.setText("");
		txTenSP.setText("");
		cbDonViTinh.removeAllItems();
		addtoComboBoxDonViTinh();
		txGiaGoc.setText("");
		txMoTa.setText("");
		txNgayKT2.setText("");
		txTimSanPham.setText("");
		txSoLuongTon.setText("");
		txGiaBan.setText("");
		cbbQG.removeAllItems();
		addtoComboBoxQuocGia();
		cbMaNCC.removeAllItems();
		addtoComboBoxNhaCungCap();
		buttonThem2.setVisible(true);
	}

	public boolean kiemTra() {
		String tenSP = txTenSP.getText();
		String soLuongTon = txSoLuongTon.getText();
		String moTa = txMoTa.getText();

		String[] maDVT = cbDonViTinh.getSelectedItem().toString().split("-");
		String[] maQG = cbbQG.getSelectedItem().toString().split("-");
		String[] maNCC = cbMaNCC.getSelectedItem().toString().split("-");

		String tenSPreg = "^([a-zA-Z0-9]+)( [a-zA-Z0-9]+)*$";
		String giaGocreg = "\\d{1,9}";
		String soLuongTonreg = "\\d{1,9}";

		String giaGoc = txGiaGoc.getText();

		String giaGocSplit = giaGoc;
		String[] split1 = giaGocSplit.split("\\.");
		if (tenSP.equals("")) {
			JOptionPane.showMessageDialog(null, "Tên Sản Phẩm không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenSP.requestFocus();
			txTenSP.selectAll();
			return false;
		} else if (maNCC[0].equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy chọn một nhà cung cấp !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			return false;
		} else if (maQG[0].equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy chọn một quốc gia !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			return false;
		} else if (maDVT[0].equals("")) {
			JOptionPane.showMessageDialog(null, "Hãy chọn một đơn vị tính !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			return false;
		} else if (moTa.equals("")) {
			JOptionPane.showMessageDialog(null, "Mô Tả Sản Phẩm không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txMoTa.requestFocus();
			txMoTa.selectAll();
			return false;
		} else if (split1[0].equals("")) {
			JOptionPane.showMessageDialog(null, "Giá Gốc không được để trống !!!\n", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txGiaGoc.requestFocus();
			txGiaGoc.selectAll();
			return false;
		} else if (soLuongTon.equals("")) {
			JOptionPane.showMessageDialog(null, "Số Lương Tồn không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSoLuongTon.requestFocus();
			txSoLuongTon.selectAll();
			return false;
		} else if (!tenSP.matches(tenSPreg)) {
			JOptionPane.showMessageDialog(null, "Tên Sản Phẩm không hợp lệ!!!\n" + "Ví dụ: RAMXII0123 với chữ IN HOA",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenSP.requestFocus();
			txTenSP.selectAll();
			return false;
		} else if (!split1[0].matches(giaGocreg)) {
			JOptionPane.showMessageDialog(null, "Giá Gốc chỉ được nhập số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txGiaGoc.requestFocus();
			txGiaGoc.selectAll();
			return false;
		} else if (!soLuongTon.matches(soLuongTonreg)) {
			JOptionPane.showMessageDialog(null, "Số lượng chỉ nhập số !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			txSoLuongTon.requestFocus();
			txSoLuongTon.selectAll();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(buttonTim2)) {
			String[] maNCC = cbMaNCC.getSelectedItem().toString().split("-");
			String id = txTimSanPham.getText();
			getTimKiemSanPham(id, maNCC[0]);
		} else if (obj.equals(buttonXoaTr2)) {
			xoaTrangSanPham();
			getSanPham();
		} else if (obj.equals(buttonXoa2)) {
			String maSP = txMaSP.getText();
			if (maSP.equals("")) {
				JOptionPane.showMessageDialog(null, "Bạn cần phải chọn Sản Phẩm để xóa !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else {
				int ntc = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Sản Phẩm này hay không?", "Xóa",
						JOptionPane.YES_NO_OPTION);
				if (ntc == JOptionPane.YES_OPTION) {
					SanPhamDAO dao = new SanPhamDAO();
					try {
						if (dao.xoaSanPham(maSP)) {
							JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm thành công !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
							int row = table2.getSelectedRow();
							dtm2.removeRow(row);
							demSoThuTu(dtm2);
							xoaTrangSanPham();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa Sản Phẩm thất bại !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
						}
					} catch (HeadlessException | ClassNotFoundException | SQLException e1) {

						e1.printStackTrace();
					}
				}
			}

		} else if (obj.equals(buttonThem2)) {
			
				String maSP = txMaSP.getText();
				
				if (!maSP.equals("")) {
					JOptionPane.showMessageDialog(null, "Không thể thêm Sản Phẩm !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					String tenSP = txTenSP.getText();
					String soLuongTon = txSoLuongTon.getText().trim();
					String moTa = txMoTa.getText();

					String[] maDVT = cbDonViTinh.getSelectedItem().toString().split("-");
					String[] maQG = cbbQG.getSelectedItem().toString().split("-");
					String[] maNCC = cbMaNCC.getSelectedItem().toString().split("-");

					String[] giaGoc = txGiaGoc.getText().toString().split("\\.");
					Double giaBan = Double.parseDouble(giaGoc[0]) * 1.2;
					Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
					SanPhamDAO dao = new SanPhamDAO();
					SanPhamDTO dto = new SanPhamDTO(maSP, tenSP, maNCC[0], maDVT[0], moTa, maQG[0],
							Double.parseDouble(giaGoc[0]), giaBan, Integer.parseInt(soLuongTon), thoiGianKhoiTao);
					try {
					if (dao.themSanPham(dto)) {
						Vector<String> v = new Vector<>();
						v.add(dtm2.getRowCount() + 1 + "");
						v.add(tenSP);
						v.add(giaGoc[0] + "");
						v.add(giaBan + "");
						v.add(soLuongTon + "");
						v.add(maDVT[1]);
						dtm2.addRow(v);
						xoaTrangSanPham();
						JOptionPane.showMessageDialog(null, "Thêm Sản Phẩm thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
					} 
				}catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}

			}
		} else if (obj.equals(buttonCapnhat2)) {
			try {
				String maSP = txMaSP.getText();
				String tenSP = txTenSP.getText();
				String soLuongTon = txSoLuongTon.getText().trim();
				String moTa = txMoTa.getText();

				String[] maDVT = cbDonViTinh.getSelectedItem().toString().split("-");
				String[] maQG = cbbQG.getSelectedItem().toString().split("-");
				String[] maNCC = cbMaNCC.getSelectedItem().toString().split("-");

				String giaGoc = txGiaGoc.getText();
				if (table2.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Bạn cần phải chọn Sản Phẩm để cập nhật !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					
					Double giaBan = Double.parseDouble(giaGoc) * 1.2;

					DecimalFormat df = new DecimalFormat("###.#");
					String giab = df.format(giaBan);

					Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
					SanPhamDAO dao = new SanPhamDAO();
					SanPhamDTO dto = new SanPhamDTO(maSP, tenSP, maNCC[0], maDVT[0], moTa, maQG[0],
							Double.parseDouble(giaGoc), Double.parseDouble(giab), Integer.parseInt(soLuongTon),
							thoiGianKhoiTao);

					if (dao.capnhatSanPham(dto)) {
						JOptionPane.showMessageDialog(null, "Cập nhật Sản Phẩm thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						dtm2.setRowCount(0);
						getSanPham();
						xoaTrangSanPham();

					}
				}

			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		} else if (obj.equals(btnTim)) {
			dispose();
			new GiaoDienTimKiemLinhKien(role, maNV).setVisible(true);
		} else if (obj.equals(btnThoat)) {
			dispose();
		}
	}
}
