package project.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import project.daos.HoaDonDAO;
import project.daos.NhanVienDAO;
import project.dtos.HoaDonDTO;
import project.dtos.NhanVienDTO;

import java.awt.Toolkit;

public class GiaoDienTimKiemHoaDon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtKH;
	private String role, maNV;
	private JComboBox<String> cboNV;

	private JButton btnTimKiem, btnThoat;
	private JDateChooser dateChooser;

	private GiaoDienDanhSachTimKiemHD frmDSHD;

	public GiaoDienTimKiemHoaDon(String role, String maNV) {
		setFont(new Font("Dialog", Font.BOLD, 14));
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/tim.png"));

		this.maNV = maNV;
		this.role = role;

		setTitle("Tìm Kiếm Hóa Đơn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 320);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblTmKimHa = new JLabel("Tìm Kiếm Hóa Đơn");
		lblTmKimHa.setForeground(new Color(0, 0, 255));
		lblTmKimHa.setBounds(101, 11, 216, 39);
		lblTmKimHa.setFont(new Font("Tahoma", Font.BOLD, 23));
		contentPane.add(lblTmKimHa);

		JLabel lblTnKhchHng = new JLabel("Tên Khách Hàng:");
		lblTnKhchHng.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnKhchHng.setBounds(10, 72, 114, 23);
		contentPane.add(lblTnKhchHng);

		JLabel lblTnNhnVin = new JLabel("Tên Nhân Viên:");
		lblTnNhnVin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTnNhnVin.setBounds(10, 131, 100, 23);
		contentPane.add(lblTnNhnVin);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(135, 237, 108, 39);
		contentPane.add(btnTimKiem);

		btnThoat = new JButton("Thoát");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(268, 237, 107, 39);
		contentPane.add(btnThoat);

		JLabel lblNgyLpHa = new JLabel("Ngày Lập Hóa Đơn:");
		lblNgyLpHa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNgyLpHa.setBounds(10, 186, 124, 23);
		contentPane.add(lblNgyLpHa);

		txtKH = new JTextField();
		txtKH.setBounds(135, 69, 240, 33);
		contentPane.add(txtKH);
		txtKH.setColumns(10);
		cboNV = new JComboBox<String>();
		cboNV.setBounds(135, 128, 240, 33);
		contentPane.add(cboNV);

		dateChooser = new JDateChooser();
		dateChooser.setBounds(135, 186, 240, 33);
		contentPane.add(dateChooser);
		JTextFieldDateEditor editor = (JTextFieldDateEditor) dateChooser.getDateEditor();
		editor.setEditable(false);
		ZoneId defaultZoneId = ZoneId.systemDefault();
		LocalDate ngayHt = LocalDate.now();

		Date ngayHTai = Date.from(ngayHt.atStartOfDay(defaultZoneId).toInstant());
		dateChooser.setDate(ngayHTai);
		dateChooser.setDateFormatString("dd-MM-yyyy");

		btnTimKiem.addActionListener(this);
		btnThoat.addActionListener(this);

		addComboBoxNhanVien();

	}

	public void addComboBoxNhanVien() {
		NhanVienDAO dao = new NhanVienDAO();
		try {
			ArrayList<NhanVienDTO> list = (ArrayList<NhanVienDTO>) dao.dsNhanVien();
			cboNV.removeAllItems();
			DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>(new String[] { "" });
			for (NhanVienDTO nhanVienDTO : list) {
				if (model.getIndexOf(nhanVienDTO.getTenNV()) == -1) {
					model.addElement(nhanVienDTO.getTenNV());
					cboNV.addItem(nhanVienDTO.getTenNV());
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//	public boolean kiemtra() {
//
//		String tenKH = txtKH.getText();
//		Date date = dateChooser.getDate();
//
//		if (tenKH.equals("")) {
//			JOptionPane.showMessageDialog(this, "Vui lòng điền tên Khách hàng !!!", "Thông báo !",
//					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
//			txtKH.requestFocus();
//			txtKH.selectAll();
//			return false;
//		} else if (date == null) {
//			JOptionPane.showMessageDialog(this, "Vui lòng chọn ngày lập Hóa đơn !!!", "Thông báo !",
//					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
//			return false;
//		}
//
//		return true;
//	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnTimKiem)) {
//			if (kiemtra() == true) {
			String tenKH = txtKH.getText();
			String tennv = cboNV.getSelectedItem().toString();

			Calendar ngayCld = Calendar.getInstance();
			ngayCld.setTime(dateChooser.getDate());

			int ngay = ngayCld.get(Calendar.DATE);
			int thang = ngayCld.get(Calendar.MONTH) + 1;
			int nam = ngayCld.get(Calendar.YEAR);

			HoaDonDAO dao = new HoaDonDAO();
			List<HoaDonDTO> list = null;
			try {
				list = dao.timKiemHoaDonByDieuKien(tenKH, tennv, ngay, thang, nam);
				if (!list.isEmpty()) {
					frmDSHD = new GiaoDienDanhSachTimKiemHD(role, maNV);
					frmDSHD.setLocationRelativeTo(null);
					frmDSHD.setVisible(true);
					for (HoaDonDTO hoaDonDTO : list) {
						GiaoDienDanhSachTimKiemHD.tablemodelHD.addRow(hoaDonDTO.toVector2());
					}
					dispose();
				} else {
					JOptionPane.showMessageDialog(this, "Không tìm thấy thông tin Hóa đơn !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				}
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}

//			}
		} else if (obj.equals(btnThoat)) {
			dispose();
		}
	}
}
