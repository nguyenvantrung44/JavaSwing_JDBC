package project.views;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import project.daos.CT_HoaDonDAO;
import project.dtos.CT_HoaDonDTO;

import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import java.util.ArrayList;

import javax.swing.JLabel;

import java.awt.event.MouseAdapter;

public class GiaoDienDanhSachTimKiemHD extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JTable table, table2;
	public static DefaultTableModel tablemodelHD, tablemodelCTHD;
	private JButton btnTimKiem, btnThoat;
	private String role, maNV;

	public GiaoDienDanhSachTimKiemHD(String role, String maNV) {
		setTitle("Quản Lý Hóa Đơn");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1133, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] colNameCTHD = "STT;Tên Linh Kiện;Đơn Vị Tính;Đơn Giá;Số Lượng;Thành Tiền".split(";");
		tablemodelCTHD = new DefaultTableModel(colNameCTHD, 0);

		JPanel pnlHD = new JPanel();
		pnlHD.setBackground(new Color(240, 230, 140));
		pnlHD.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Hóa đơn", TitledBorder.LEADING,
				TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnlHD.setBounds(41, 317, 1107, 249);
		contentPane.add(pnlHD);
		pnlHD.setLayout(null);

		String[] colNameHD = "Mã Hóa Đơn;Tên Khách Hàng;Tên Nhân Viên;Ngày Lập Hóa Đơn".split(";");
		tablemodelHD = new DefaultTableModel(colNameHD, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(tablemodelHD),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				try {
					clickTable();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		scrollPane.setBounds(24, 23, 1040, 215);
		pnlHD.add(scrollPane);

		btnTimKiem = new JButton("Tìm Kiếm");
		btnTimKiem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTimKiem.setBounds(287, 610, 150, 35);
		contentPane.add(btnTimKiem);

		JLabel lblDanhSachHD = new JLabel("Danh Sách Hóa Đơn");
		lblDanhSachHD.setForeground(new Color(0, 0, 255));
		lblDanhSachHD.setFont(new Font("Sitka Small", Font.BOLD, 22));
		lblDanhSachHD.setBounds(394, 11, 256, 35);
		contentPane.add(lblDanhSachHD);

		btnThoat = new JButton("Thoát Tìm Kiếm");
		btnThoat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnThoat.setBounds(726, 610, 139, 35);
		contentPane.add(btnThoat);

		JPanel pnlChiTiet = new JPanel();
		pnlChiTiet.setBounds(41, 55, 1076, 251);
		contentPane.add(pnlChiTiet);
		pnlChiTiet.setBackground(new Color(240, 230, 140));
		pnlChiTiet.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Chi tiết hóa đơn",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		pnlChiTiet.setLayout(null);
		JScrollPane scrollPane2 = new JScrollPane(table2 = new JTable(tablemodelCTHD),
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollPane2.setBounds(23, 25, 1043, 215);
		pnlChiTiet.add(scrollPane2);

		btnTimKiem.addActionListener(this);
		btnThoat.addActionListener(this);

	}

	public void clickTable() {
		int row = table.getSelectedRow();
		getCT_HoaDonTheoMa(table.getValueAt(row, 0).toString());
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

		tablemodelCTHD.setRowCount(0);
		for (CT_HoaDonDTO ct_HoaDonDTO : list) {

			tablemodelCTHD.addRow(ct_HoaDonDTO.toVector());
		}
		for (int i = 0; i < tablemodelCTHD.getRowCount(); i++) {
			tablemodelCTHD.setValueAt(i + 1, i, 0);
		}

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if (o.equals(btnTimKiem)) {
			new GiaoDienTimKiemHoaDon(role, maNV).setVisible(true);
			dispose();
		} else if (o.equals(btnThoat)) {
			dispose();
		}
	}
}