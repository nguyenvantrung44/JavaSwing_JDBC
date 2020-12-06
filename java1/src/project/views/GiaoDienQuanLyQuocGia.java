package project.views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import project.daos.QuocGiaDAO;

import project.dtos.QuocGiaDTO;

import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.border.TitledBorder;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class GiaoDienQuanLyQuocGia extends JFrame implements ActionListener, MouseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static String role;
	public static String maNV;
	private JTable table;
	private DefaultTableModel tableModel;
	private JPanel panel;
	private JLabel lblthongTinQG;
	private JLabel lblmaQG;
	private JLabel lbltenQG;
	private JPanel panel_1;
	private JButton btnthem;
	private JButton btncapNhat;
	private JButton btnxoaRong;
	private JTextField txtmaQG;
	private JTextField txttenQG;

	public GiaoDienQuanLyQuocGia(String role, String maNV) {
		setTitle("Giao Diện Quản Lý Quốc Gia");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1133, 700);
		setLocationRelativeTo(null);
		taoGiaoDien();

		tableModel = (DefaultTableModel) table.getModel();
		getQuocGia();

		GiaoDienQuanLyQuocGia.role = role;
		GiaoDienQuanLyQuocGia.maNV = maNV;
	}

	public void taoGiaoDien() {
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 230, 140));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		String[] header = "Mã Quốc Gia; Tên Quốc Gia".split(";");
		tableModel = new DefaultTableModel(header, 0);
		JScrollPane scrollPane = new JScrollPane(table = new JTable(new DefaultTableModel(new Object[][] {},
				new String[] { "M\u00E3 Qu\u1ED1c Gia", " T\u00EAn Qu\u1ED1c Gia" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		}), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		table.addMouseListener(this);
		table.getTableHeader().setEnabled(false);

		scrollPane.setBounds(10, 10, 562, 640);
		contentPane.add(scrollPane);

		panel = new JPanel();
		panel.setBackground(new Color(240, 230, 140));
		panel.setBounds(582, 10, 512, 640);
		contentPane.add(panel);
		panel.setLayout(null);

		lblthongTinQG = new JLabel("Thông Tin Quốc Gia");
		lblthongTinQG.setForeground(Color.BLUE);
		lblthongTinQG.setFont(new Font("Tahoma", Font.BOLD, 23));
		lblthongTinQG.setBounds(160, 11, 232, 31);
		panel.add(lblthongTinQG);

		lblmaQG = new JLabel("Mã Quốc Gia:");
		lblmaQG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblmaQG.setBounds(105, 73, 121, 31);
		panel.add(lblmaQG);

		lbltenQG = new JLabel("Tên Quốc Gia:");
		lbltenQG.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lbltenQG.setBounds(105, 145, 121, 31);
		panel.add(lbltenQG);

		panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Thanh C\u00F4ng C\u1EE5",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(255, 0, 0)));
		panel_1.setBackground(new Color(240, 230, 140));
		panel_1.setBounds(73, 268, 416, 164);
		panel.add(panel_1);

		btnthem = new JButton("Thêm");
		btnthem.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnthem.setBackground(Color.WHITE);
		btnthem.setBounds(245, 105, 161, 46);
		panel_1.add(btnthem);

		btncapNhat = new JButton("Cập Nhật");
		btncapNhat.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btncapNhat.setBackground(Color.WHITE);
		btncapNhat.setBounds(10, 25, 148, 46);
		panel_1.add(btncapNhat);

		btnxoaRong = new JButton("Xóa Rỗng");
		btnxoaRong.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnxoaRong.setBackground(Color.WHITE);
		btnxoaRong.setBounds(245, 25, 161, 46);
		panel_1.add(btnxoaRong);

		txtmaQG = new JTextField();
		txtmaQG.setEditable(false);
		txtmaQG.setColumns(10);
		txtmaQG.setBounds(299, 77, 190, 31);
		panel.add(txtmaQG);

		txttenQG = new JTextField();
		txttenQG.setColumns(10);
		txttenQG.setBounds(299, 149, 190, 31);
		panel.add(txttenQG);

		txtmaQG.setEditable(false);

		btnxoaRong.addActionListener(this);
		btnthem.addActionListener(this);
		btncapNhat.addActionListener(this);
	}

	public void getQuocGia() {
		QuocGiaDAO dao = new QuocGiaDAO();
		ArrayList<QuocGiaDTO> list = null;
		try {
			list = (ArrayList<QuocGiaDTO>) dao.dsQuocGia();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		tableModel.setRowCount(0);
		for (QuocGiaDTO quocGiaDTO : list) {
			tableModel.addRow(quocGiaDTO.toVector());
		}
	}

	public void getLamSach() {
		txtmaQG.setText("");
		txttenQG.setText("");
		btnthem.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		String maqg = (String) tableModel.getValueAt(row, 0);
		QuocGiaDAO dao = new QuocGiaDAO();
		// btnthem.setVisible(false);
		try {
			QuocGiaDTO dto = dao.layThongTinQuocGia(maqg);
			txtmaQG.setText(maqg);
			txttenQG.setText(dto.getTenQG());
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
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

	public boolean kiemTra() {

		String ten = txttenQG.getText();
		if (ten.matches("")) {
			JOptionPane.showMessageDialog(null, "Tên Quốc Gia không được để trống !!!", "Thông báo !",
					JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
			txttenQG.requestFocus();
			txttenQG.selectAll();
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();

		if (obj.equals(btnxoaRong)) {
			getLamSach();
			getQuocGia();
		} else if (obj.equals(btnthem)) {
			try {
				String ma = txtmaQG.getText();
				String ten = txttenQG.getText();
				if (!ma.matches("")) {
					JOptionPane.showMessageDialog(null, "Không thể thêm Quốc Gia !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					QuocGiaDAO dao = new QuocGiaDAO();
					QuocGiaDTO dto = new QuocGiaDTO(ma, ten);
					dao.themQuocGia(dto);
					getQuocGia();
					getLamSach();
					JOptionPane.showMessageDialog(null, "Thêm thành công!!!", "Thông báo !", JOptionPane.ERROR_MESSAGE,
							new ImageIcon("image/yes.png"));
				}

			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} else if (obj.equals(btncapNhat)) {
			try {
				if (table.getSelectedRowCount() == 0) {
					JOptionPane.showMessageDialog(null, "Bạn cần phải chọn Quốc Gia để cập nhật !!!", "Thông báo !",
							JOptionPane.ERROR_MESSAGE, new ImageIcon("image/warning.png"));
				} else if (kiemTra() == true) {
					String ma = txtmaQG.getText();
					String ten = txttenQG.getText();
					QuocGiaDAO dao = new QuocGiaDAO();
					QuocGiaDTO dto = new QuocGiaDTO(ma, ten);
					if (dao.capnhatQuocGia(dto)) {
						int row = table.getSelectedRow();
						table.setValueAt(dto.getMaQG(), row, 0);
						table.setValueAt(dto.getTenQG(), row, 1);
						getLamSach();
					}
				}	
			} catch (ClassNotFoundException | SQLException e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}

	}
}
