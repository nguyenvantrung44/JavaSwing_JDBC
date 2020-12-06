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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.daos.NhaCungCapDAO;
import project.daos.SanPhamDAO;
import project.dtos.NhaCungCapDTO;

public class NhaCungCap_JFrame extends JFrame implements ActionListener {

	public static JPanel contentPane;

	public static String role;
	public static String maNV;

	public static JTextField txSoLuongTon = new JTextField();
	public static JPanel panel_3 = new JPanel();
	public static JTable table3 = new JTable(new DefaultTableModel(new Object[][] {},
			new String[] { "STT", "T\u00EAn nh\u00E0 cung c\u1EA5p", "Email", "S\u1ED1 \u0111i\u1EC7n tho\u1EA1i" }) {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		boolean[] columnEditables = new boolean[] { false, false, false, false };

		public boolean isCellEditable(int row, int column) {
			return columnEditables[column];
		}
	});
	public static JScrollPane scrollPane3 = new JScrollPane(table3);

	private JButton buttonTim3 = new JButton("Tìm kiếm :");
	public static JTextField txTimNhaCungCap = new JTextField();

	private JButton buttonThem3 = new JButton("Thêm");
	private JButton buttonCapnhat3 = new JButton("Cập nhật");
	private JButton buttonXoa3 = new JButton("Xóa");
	private JButton buttonXoaTr3 = new JButton("Xóa trắng");

	public static JLabel labelMaNCC3 = new JLabel("Mã nhà cung cấp:");
	public static JTextField txMaNCC = new JTextField();

	public static JLabel labelTenNCC = new JLabel("Tên nhà cung cấp:");
	public static JTextField txTenNCC = new JTextField();

	public static JLabel labelEmail = new JLabel("Email:");
	public static JTextField txEmail = new JTextField();

	private JLabel labelSDT = new JLabel("Số điện thoại:");
	public static JTextField txSDT = new JTextField();

	public static DefaultTableModel dtm3;

	public static final long serialVersionUID = 1L;
	public static JTextField txSoFax;
	public static JTextField txDiaChi;
	public static JTextField txNgayKT3;
	public static JTextField txNgayKT1;
	public static JTextField txNgayKT2;
	public static JTextArea txMoTa = new JTextArea();

	public static void main(String[] args) {
		new NhaCungCap_JFrame("admin", "MNV001").setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public NhaCungCap_JFrame(String role, String maNV) {
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 1200, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		setBackground(new Color(0, 0, 0));
		table3.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 11));

		table3.getTableHeader().setOpaque(false);
		table3.getTableHeader().setBackground(new Color(32, 136, 203));
		table3.getTableHeader().setForeground(new Color(255, 255, 255));
		table3.getTableHeader().setEnabled(false);
		table3.setRowHeight(25);
		table3.setSelectionBackground(new Color(232, 57, 95));
		table3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = table3.getSelectedRow();
				String tenNCC = (String) dtm3.getValueAt(row, 1);
				NhaCungCapDAO dao = new NhaCungCapDAO();
				// buttonThem3.setVisible(false);
				try {
					NhaCungCapDTO dto = dao.layThongTinNhaCungCap(tenNCC);
					txMaNCC.setText(dto.getMaNCC());
					txTenNCC.setText(tenNCC);
					txEmail.setText(dto.getEmail());
					txSoFax.setText(dto.getSoFax());
					txDiaChi.setText(dto.getDiaChi());
					txSDT.setText(dto.getPhone());
					SimpleDateFormat sdf3 = new SimpleDateFormat("dd-MM-yyyy");
					if (dto.getThoiGianKhoiTao() == null) {
						txNgayKT3.setText("");
					} else {
						txNgayKT3.setText(sdf3.format(dto.getThoiGianKhoiTao()));
					}
					txMaNCC.setEditable(false);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

		buildUI();
		dtm3 = (DefaultTableModel) table3.getModel();
		getNhaCungCap();

		NhaCungCap_JFrame.role = role;
		NhaCungCap_JFrame.maNV = maNV;
	}

	private void buildUI() {
		getContentPane().setLayout(null);
		panel_3 = new JPanel();
		panel_3.setBackground(new Color(240, 230, 140));
		panel_3.setBounds(0, 0, 1200, 700);

		getContentPane().add(panel_3);
		panel_3.setPreferredSize(new Dimension(0, 250));
		panel_3.setLayout(null);

		scrollPane3.setBounds(20, 11, 1170, 433);
		buttonTim3.setIcon(new ImageIcon("image/tim.png"));
		buttonTim3.setBackground(Color.GREEN);
		buttonTim3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonTim3.setBounds(20, 466, 129, 30);

		txTimNhaCungCap.setBounds(159, 465, 243, 35);
		buttonXoaTr3.setIcon(new ImageIcon("image/tay.png"));
		buttonXoaTr3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonXoaTr3.setBackground(Color.GREEN);

		buttonXoaTr3.setBounds(796, 465, 135, 33);
		buttonThem3.setIcon(new ImageIcon("image/iconAdd.png"));
		buttonThem3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonThem3.setBackground(Color.GREEN);

		buttonThem3.setBounds(512, 465, 121, 33);
		buttonCapnhat3.setIcon(new ImageIcon("image/update.png"));
		buttonCapnhat3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonCapnhat3.setBackground(Color.GREEN);

		buttonCapnhat3.setBounds(1069, 463, 121, 33);
		buttonXoa3.setIcon(new ImageIcon("image/delete.png"));
		buttonXoa3.setFont(new Font("Tahoma", Font.BOLD, 11));
		buttonXoa3.setBackground(Color.GREEN);
		buttonXoa3.setVisible(false);

		buttonXoa3.setBounds(20, 633, 121, 33);
		labelMaNCC3.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelMaNCC3.setBounds(20, 538, 100, 20);
		txMaNCC.setEnabled(false);
		txMaNCC.setBounds(159, 531, 243, 35);
		labelTenNCC.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelTenNCC.setBounds(20, 602, 110, 20);
		txTenNCC.setBounds(159, 597, 243, 35);
		labelEmail.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelEmail.setBounds(430, 538, 47, 20);
		txEmail.setBounds(512, 533, 202, 33);
		labelSDT.setFont(new Font("Tahoma", Font.BOLD, 11));

		labelSDT.setBounds(422, 602, 80, 20);
		txSDT.setBounds(512, 597, 202, 35);
		panel_3.setBackground(new Color(240, 230, 140));

		panel_3.add(scrollPane3);
		panel_3.add(buttonTim3);
		panel_3.add(txTimNhaCungCap);
		panel_3.add(buttonXoaTr3);
		panel_3.add(buttonThem3);
		panel_3.add(buttonCapnhat3);
		panel_3.add(buttonXoa3);
		panel_3.add(labelMaNCC3);
		panel_3.add(txMaNCC);
		panel_3.add(labelTenNCC);
		panel_3.add(txTenNCC);
		panel_3.add(labelEmail);
		panel_3.add(txEmail);
		panel_3.add(labelSDT);
		panel_3.add(txSDT);

		panel_3.setLayout(null);

		contentPane.add(panel_3);

		JLabel labelSoFax = new JLabel("Số Fax");
		labelSoFax.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelSoFax.setBounds(724, 540, 53, 16);
		panel_3.add(labelSoFax);

		JLabel labelDiaChi = new JLabel("Địa Chỉ");
		labelDiaChi.setFont(new Font("Tahoma", Font.BOLD, 11));
		labelDiaChi.setBounds(724, 604, 70, 16);
		panel_3.add(labelDiaChi);

		JLabel lblNgayKT = new JLabel("Ngày Nhập");
		lblNgayKT.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNgayKT.setBounds(953, 540, 70, 16);
		panel_3.add(lblNgayKT);

		txSoFax = new JTextField();
		txSoFax.setBounds(796, 533, 147, 33);
		panel_3.add(txSoFax);
		txSoFax.setColumns(10);

		txDiaChi = new JTextField();
		txDiaChi.setBounds(796, 597, 394, 30);
		panel_3.add(txDiaChi);
		txDiaChi.setColumns(10);

		txNgayKT3 = new JTextField();
		txNgayKT3.setBounds(1016, 531, 174, 35);
		panel_3.add(txNgayKT3);
		txNgayKT3.setEditable(false);
		txNgayKT3.setColumns(10);

		table3.getTableHeader().setOpaque(false);
		table3.getTableHeader().setBackground(new Color(32, 136, 203));
		table3.getTableHeader().setForeground(new Color(255, 255, 255));
		table3.setSelectionBackground(new Color(232, 57, 95));

		buttonTim3.addActionListener(this);
		buttonThem3.addActionListener(this);
		buttonXoa3.addActionListener(this);
		buttonCapnhat3.addActionListener(this);
		buttonXoaTr3.addActionListener(this);

	}

	// Nhà Cung Cấp
	public void getNhaCungCap() {
		NhaCungCapDAO dao = new NhaCungCapDAO();
		ArrayList<NhaCungCapDTO> list = null;
		try {
			list = (ArrayList<NhaCungCapDTO>) dao.dsNhaCungCap();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm3.setRowCount(0);
		for (NhaCungCapDTO nhaCungCapDTO : list) {
			dtm3.addRow(nhaCungCapDTO.toVector());
		}
		for (int i = 0; i < dtm3.getRowCount(); i++) {
			dtm3.setValueAt(i + 1, i, 0);
		}
	}

	public void getTimKiemNhaCungCap(String tenNCC) {
		NhaCungCapDAO dao = new NhaCungCapDAO();
		ArrayList<NhaCungCapDTO> list = null;
		try {
			list = (ArrayList<NhaCungCapDTO>) dao.timKiemNhaCungCap(tenNCC);
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dtm3 = (DefaultTableModel) table3.getModel();
		dtm3.setRowCount(0);
		for (NhaCungCapDTO nhaCungCapDTO : list) {
			dtm3.addRow(nhaCungCapDTO.toVector());
		}
		for (int i = 0; i < dtm3.getRowCount(); i++) {
			dtm3.setValueAt(i + 1, i, 0);
		}
	}

	public void demSoThuTu(DefaultTableModel dtm) {
		for (int i = 0; i < dtm.getRowCount(); i++) {
			dtm.setValueAt(i + 1, i, 0);
		}
	}

	public void xoaTrangNhaCC() {
		txMaNCC.setEditable(true);
		txMaNCC.setText("");
		txTenNCC.setText("");
		txSoFax.setText("");
		txDiaChi.setText("");
		txEmail.setText("");
		txSDT.setText("");
		txNgayKT3.setText("");
		txTimNhaCungCap.setText("");
		buttonThem3.setVisible(true);
	}

	public boolean kiemTra() {
		String tenNCC = txTenNCC.getText();
		String soFax = txSoFax.getText();
		String email = txEmail.getText();
		String diaChi = txDiaChi.getText();
		String sDT = txSDT.getText();
		String tenNCCreg = "^([a-zA-Z0-9]+)( [a-zA-Z0-9]+)*$";
		String soFaxreg = "\\d{11}";
		String emailreg = "^\\w+@{1}\\w+\\.\\w+(\\.\\w+)*$";
		String sDTreg = "\\d{9,11}";
		if (tenNCC.equals("")) {
			JOptionPane.showMessageDialog(null, "Tên Nhà Cung Cấp không được rỗng !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenNCC.requestFocus();
			txTenNCC.selectAll();
			return false;
		} else if (email.equals("")) {
			JOptionPane.showMessageDialog(null, "Email Nhà Cung Cấp không được rỗng !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txEmail.requestFocus();
			txEmail.selectAll();
			return false;
		} else if (sDT.equals("")) {
			JOptionPane.showMessageDialog(null, "Số Điện Thoại không được rỗng !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSDT.requestFocus();
			txSDT.selectAll();
			return false;
		} else if (soFax.equals("")) {
			JOptionPane.showMessageDialog(null, "Số Fax Nhà Cung Cấp không được rỗng !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSoFax.requestFocus();
			txSoFax.selectAll();
			return false;
		} else if (diaChi.equals("")) {
			JOptionPane.showMessageDialog(null, "Địa Chỉ không được rỗng !!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
					new ImageIcon("image/warning.png"));
			txDiaChi.requestFocus();
			txDiaChi.selectAll();
			return false;
		} else if (!tenNCC.matches(tenNCCreg)) {
			JOptionPane.showMessageDialog(null, "Tên Nhà Cung Cấp không hợp lệ !!!\n" + "Không nhập ký tự đặc bệt",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txTenNCC.requestFocus();
			txTenNCC.selectAll();
			return false;
		} else if (!email.matches(emailreg)) {
			JOptionPane.showMessageDialog(null, "Email Nhà Cung Cấp không hợp lệ !!!\n" + "Ví du: phongvu@gmail.com",
					"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txEmail.requestFocus();
			txEmail.selectAll();
			return false;
		} else if (!sDT.matches(sDTreg)) {
			JOptionPane.showMessageDialog(null, "Số Điện Thoại phải có từ 9-11 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSDT.requestFocus();
			txSDT.selectAll();
			return false;
		} else if (!soFax.matches(soFaxreg)) {
			JOptionPane.showMessageDialog(null, "Số Fax phải có 11 chữ số !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txSoFax.requestFocus();
			txSoFax.selectAll();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object obj = e.getSource();
		if (obj.equals(buttonThem3)) {
			try {
				String maNCC = txMaNCC.getText();

				if (!maNCC.equals("")) {
					JOptionPane.showMessageDialog(null, "Không thể thêm Nhà Cung Cấp !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					String tenNCC = txTenNCC.getText();
					String soFax = txSoFax.getText();
					String email = txEmail.getText();
					String diaChi = txDiaChi.getText();
					String sDT = txSDT.getText();
					Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
					NhaCungCapDAO dao = new NhaCungCapDAO();
					NhaCungCapDTO dto = new NhaCungCapDTO(maNCC, tenNCC, diaChi, email, sDT, soFax, thoiGianKhoiTao);

					if (dao.themNhaCungCap(dto)) {
						JOptionPane.showMessageDialog(null, "Thêm Nhà cung cấp thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
						Vector<String> v = new Vector<>();
						v.add(dtm3.getRowCount() + 1 + "");
						v.add(tenNCC);
						v.add(email);
						v.add(sDT);
						dtm3.addRow(v);
						xoaTrangNhaCC();
					}
				}
			} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		} else if (obj.equals(buttonTim3)) {
			String tenNCC = txTimNhaCungCap.getText();
			getTimKiemNhaCungCap(tenNCC);

		} else if (obj.equals(buttonCapnhat3)) {
			String maNCC = txMaNCC.getText();
			String tenNCC = txTenNCC.getText();
			String soFax = txSoFax.getText();
			String email = txEmail.getText();
			String diaChi = txDiaChi.getText();
			String sDT = txSDT.getText();

			if (table3.getSelectedRowCount()==0) {
				JOptionPane.showMessageDialog(null, "Cần phải chọn Nhà Cung Cấp để cập nhật thông tin !!!",
						"Thông báo !", JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else if (kiemTra() == true) {
				Timestamp thoiGianKhoiTao = new Timestamp(System.currentTimeMillis());
				NhaCungCapDAO dao = new NhaCungCapDAO();
				NhaCungCapDTO dto = new NhaCungCapDTO(maNCC, tenNCC, diaChi, email, sDT, soFax, thoiGianKhoiTao);
				try {
					if (dao.capnhatNhaCungCap(dto)) {
						JOptionPane.showMessageDialog(null, "Cập Nhật Nhà Cung Cấp thành công !!!", "Thông báo !",
								JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
//							addtoComboBoxNhaCungCap();
						int row = table3.getSelectedRow();
						table3.setValueAt(dto.getTenNCC(), row, 1);
						table3.setValueAt(dto.getEmail(), row, 2);
						table3.setValueAt(dto.getPhone(), row, 3);
						xoaTrangNhaCC();
					}
				} catch (HeadlessException | ClassNotFoundException | SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		} else if (obj.equals(buttonXoaTr3)) {

			xoaTrangNhaCC();
			getNhaCungCap();
//				getSanPham();
		} else if (obj.equals(buttonXoa3)) {
			String maNCC = txMaNCC.getText();
			if (table3.getRowCount() == 0) {
				JOptionPane.showMessageDialog(null, "Cần chọn Nhà cung cấp muốn xóa !!!", "Thông báo !",
						JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			} else {
				SanPhamDAO dao2 = new SanPhamDAO();
				int ntc = 0;
				ntc = JOptionPane.showConfirmDialog(null, "Bạn có muốn xóa Nhà Cung Cấp này hay không ?", "Xóa",
						JOptionPane.YES_NO_OPTION);

				if (ntc == JOptionPane.YES_OPTION) {
					NhaCungCapDAO dao = new NhaCungCapDAO();
					try {
						dao2.xoatatcaSanPhamcuaNCC(maNCC);
						if (dao.xoaNhaCungCap(maNCC)) {
							JOptionPane.showMessageDialog(null, "Xóa Nhà Cung Cấp thành công !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/yes.png"));
//								addtoComboBoxNhaCungCap();
							int row = table3.getSelectedRow();
							dtm3.removeRow(row);
							demSoThuTu(dtm3);
							xoaTrangNhaCC();
//								getSanPham();
						} else {
							JOptionPane.showMessageDialog(null, "Xóa Nhà Cung Cấp thất bại !!!", "Thông báo !",
									JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
						}
					} catch (ClassNotFoundException | SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
			}
		}
	}

}
