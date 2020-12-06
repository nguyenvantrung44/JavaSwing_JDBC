package project.views;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.JMenu;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.JLabel;

public class FrmTONG extends JFrame implements ActionListener, MenuListener {

	/**
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public static JPanel contentPane;
	public static JMenuBar menuBar;
	public static JMenu mnHoaDon, mnDanhMuc, mnThongKe;
	public static JMenuItem mntmXuLy, mntmLinhKien, mntmNCC, mntmTKNV, mntmTKLK, mntmTKDT,mntmDonViTinh,mntmQuocGia;

	private String role;
	private String maNV;
	private JMenu mnHeThong;
	private JMenuItem mntmDoiMatKhau;
	private JMenuItem mntmDangXuat;
	private JMenuItem mntmThoat;
	private JMenuItem mntmKhachHang;
	private static JMenuItem mntmNhanVien;
	private JMenuItem mntmHoaDon;
	private JMenu mnTimKiem;
	private JMenuItem mntmTKLinhKien;
	private JMenuItem mntmTKHoaDon;
	private JMenuItem mntmTKKhachHang;
	private JLabel lblNewLabel;

	private static FrmTONG frmTONG;
	

	public static void main(String[] args) {
		frmTONG = new FrmTONG("admin", "MNV001");
		frmTONG.pack();
		frmTONG.setLocationRelativeTo(null);
		frmTONG.setVisible(true);

	}

	public FrmTONG(String role, String maNV) {
		setIconImage(Toolkit.getDefaultToolkit().getImage("image/gioithieu.jpg"));
		setTitle("Lựa chọn chức năng");
		setSize(1133, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		MainGUI();

		if (role.equals("ban hang")) {
			mntmNhanVien.setVisible(false);
		}
		if (role.equals("admin")) {
			mnHoaDon.setVisible(true);
			mnDanhMuc.setVisible(true);
			mnThongKe.setVisible(true);
			mnHeThong.setVisible(true);

		}
		this.role = role;
		this.maNV = maNV;
	}

	public void MainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1133, 730);

		contentPane = new JPanel();
		setContentPane(contentPane);
		menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		mnHeThong = new JMenu("Hệ thống");
		mnHeThong.setIcon(new ImageIcon("image/system.png"));
		mnHeThong.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnHeThong);

		mntmDoiMatKhau = new JMenuItem("Đổi mật khẩu");
		mntmDoiMatKhau.setIcon(new ImageIcon("image/change-password-icon.png"));
		mntmDoiMatKhau.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.ALT_MASK));
		mntmDoiMatKhau.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHeThong.add(mntmDoiMatKhau);

		mntmDangXuat = new JMenuItem("Đăng xuất");
		mntmDangXuat.setIcon(new ImageIcon("image/gnome_logout.png"));
		mntmDangXuat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, InputEvent.ALT_MASK));
		mntmDangXuat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHeThong.add(mntmDangXuat);

		mntmThoat = new JMenuItem("Thoát");
		mntmThoat.setIcon(new ImageIcon("image/exit2.png"));
		mntmThoat.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0));
		mntmThoat.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHeThong.add(mntmThoat);

		mnDanhMuc = new JMenu("Danh Mục");
		mnDanhMuc.setIcon(new ImageIcon("image/danhmuc.png"));
		mnDanhMuc.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnDanhMuc);

		mntmLinhKien = new JMenuItem("Linh Kiện");
		mntmLinhKien.setIcon(new ImageIcon("image/linhkien.png"));
		mntmLinhKien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
		mntmLinhKien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmLinhKien);

		mntmNCC = new JMenuItem("Nhà cung cấp");
		mntmNCC.setIcon(new ImageIcon("image/supplier.png"));
		mntmNCC.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
		mntmNCC.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmNCC);

		mntmKhachHang = new JMenuItem("Khách hàng");
		mntmKhachHang.setIcon(new ImageIcon("image/Users-icon.png"));
		mntmKhachHang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		mntmKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmKhachHang);

		mntmNhanVien = new JMenuItem("Nhân viên");
		mntmNhanVien.setIcon(new ImageIcon("image/Admin-icon.png"));
		mntmNhanVien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_4, InputEvent.ALT_MASK));
		mntmNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmNhanVien);

		mntmQuocGia = new JMenuItem("Quốc gia");
		mntmQuocGia.setIcon(new ImageIcon("image/Country-icon.png"));
		mntmQuocGia.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_5, InputEvent.ALT_MASK));
		mntmQuocGia.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmQuocGia);

		mntmDonViTinh = new JMenuItem("Đơn vị tính");
		mntmDonViTinh.setIcon(new ImageIcon("image/Editing-Type-icon.png"));
		mntmDonViTinh.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_6, InputEvent.ALT_MASK));
		mntmDonViTinh.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmDonViTinh);

		mntmHoaDon = new JMenuItem("Hóa đơn\r\n");
		mntmHoaDon.setIcon(new ImageIcon("image/Ecommerce-Bill-icon 24.png"));
		mntmHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_7, InputEvent.ALT_MASK));
		mntmHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnDanhMuc.add(mntmHoaDon);
		mntmLinhKien.addActionListener(this);
		mntmNCC.addActionListener(this);

		mnHoaDon = new JMenu("Xử lý");
		mnHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHoaDon.setIcon(new ImageIcon("image/xuli.png"));
		menuBar.add(mnHoaDon);

		mntmXuLy = new JMenuItem("Thêm Hóa Đơn Mới ");
		mntmXuLy.setIcon(new ImageIcon("image/Actions-contact-new-icon.png"));

		mntmXuLy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mntmXuLy.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnHoaDon.add(mntmXuLy);

//		mntmHuyHD = new JMenuItem("Hủy hóa đơn");
//		mntmHuyHD.setIcon(new ImageIcon("image/Actions-edit-delete-icon.png"));
//		mntmHuyHD.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				contentPane.removeAll();
//				dispose();
//				frmTONG = new FrmTONG(role, maNV);
//				frmTONG.pack();
//				frmTONG.setLocationRelativeTo(null);
//				frmTONG.setVisible(true);
//				contentPane.revalidate();
//				contentPane.repaint();
//			}
//		});
//		mntmHuyHD.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
//		mntmHuyHD.setFont(new Font("Segoe UI", Font.BOLD, 14));
//		mnHoaDon.add(mntmHuyHD);

		mnTimKiem = new JMenu("Tìm kiếm");
		mnTimKiem.setIcon(new ImageIcon("image/folder-search-icon.png"));
		mnTimKiem.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnTimKiem);

		mntmTKLinhKien = new JMenuItem("Linh kiện");
		mntmTKLinhKien.setIcon(new ImageIcon("image/linhkien.png"));
		mntmTKLinhKien.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mntmTKLinhKien.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.ALT_MASK));
		mnTimKiem.add(mntmTKLinhKien);

		mntmTKHoaDon = new JMenuItem("Hóa đơn");
		mntmTKHoaDon.setIcon(new ImageIcon("image/Ecommerce-Bill-icon 24.png"));
		mntmTKHoaDon.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.ALT_MASK));
		mntmTKHoaDon.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnTimKiem.add(mntmTKHoaDon);

		mntmTKKhachHang = new JMenuItem("Khách hàng");
		mntmTKKhachHang.setIcon(new ImageIcon("image/Users-icon.png"));
		mntmTKKhachHang.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.ALT_MASK));
		mntmTKKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnTimKiem.add(mntmTKKhachHang);

		mnThongKe = new JMenu("Thống Kê/Báo cáo");
		mnThongKe.setIcon(new ImageIcon("image/SEO-icon.png"));
		mnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 14));
		menuBar.add(mnThongKe);

		mntmTKNV = new JMenuItem("Thống kê theo nhân viên");
		mntmTKNV.setIcon(new ImageIcon("image/NVstatistic-icon.png"));
		mntmTKNV.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, InputEvent.ALT_MASK));
		mntmTKNV.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnThongKe.add(mntmTKNV);

		mntmTKLK = new JMenuItem("Thống kê tình trạng linh kiện");
		mntmTKLK.setIcon(new ImageIcon("image/pie-chart-icon.png"));
		mntmTKLK.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.ALT_MASK));
		mntmTKLK.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnThongKe.add(mntmTKLK);

		mntmTKDT = new JMenuItem("Thống kê doanh thu");
		mntmTKDT.setIcon(new ImageIcon("image/Actions-view-statistics-icon.png"));
		mntmTKDT.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, InputEvent.ALT_MASK));
		mntmTKDT.setFont(new Font("Segoe UI", Font.BOLD, 14));
		mnThongKe.add(mntmTKDT);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));

		ImageIcon img = new ImageIcon("image/backgroundtong.jpg");
		lblNewLabel = new JLabel("", img, JLabel.CENTER);
		lblNewLabel.setBounds(0, 0, 1133, 700);
		contentPane.add(lblNewLabel, BorderLayout.CENTER);

		mntmXuLy.addActionListener(this);
		mntmTKNV.addActionListener(this);
		mntmTKLK.addActionListener(this);
		mntmTKDT.addActionListener(this);
		mntmLinhKien.addActionListener(this);
		mntmKhachHang.addActionListener(this);
		mntmNhanVien.addActionListener(this);
		mntmHoaDon.addActionListener(this);
		mntmDangXuat.addActionListener(this);
		mntmThoat.addActionListener(this);
		mntmQuocGia.addActionListener(this);
		mntmDonViTinh.addActionListener(this);
		mntmTKHoaDon.addActionListener(this);
		mntmTKLinhKien.addActionListener(this);
		mntmTKKhachHang.addActionListener(this);
		mntmDoiMatKhau.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object obj = e.getSource();
		if (obj.equals(mntmXuLy)) {
			new GiaoDienDanhSachKhachHang(role, maNV);

			contentPane.removeAll();
			contentPane.revalidate();
			contentPane.repaint();
			contentPane.add(GiaoDienDanhSachKhachHang.contentPane);

		} else if (obj.equals(mntmLinhKien)) {
//			tabbedPane.remove(tabbedPane.getSelectedComponent());
//			new SanPham_JFrame(role,maNV);
//			tabbedPane.add(SanPham_JFrame.panel_2);
			new SanPham_JFrame(role, maNV);
			contentPane.removeAll();
			contentPane.add(SanPham_JFrame.panel_2);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmNCC)) {
			new NhaCungCap_JFrame(role, maNV);
			contentPane.removeAll();
			contentPane.add(NhaCungCap_JFrame.panel_3);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmTKNV)) {
			new FrmBaoCaoThongKe();
			contentPane.removeAll();
			contentPane.add(FrmBaoCaoThongKe.pnlToanPhan);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmTKLK)) {
			new FrmBaoCaoThongKe();
			contentPane.removeAll();
			contentPane.add(FrmBaoCaoThongKe.pnlThongkeTTLinhKien);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmTKDT)) {
			new FrmBaoCaoThongKe();
			contentPane.removeAll();
			contentPane.add(FrmBaoCaoThongKe.pnlThongKeBaoCao);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmNhanVien)) {
			new NhanVien_JFrame(role, maNV);
			contentPane.removeAll();
			contentPane.add(NhanVien_JFrame.pnNorth);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmKhachHang)) {
			new KhachHang_JFrame(role, maNV);
			contentPane.removeAll();
			contentPane.add(KhachHang_JFrame.pnNorth);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmHoaDon)) {
			new FrmQuanLyHoaDon();
			contentPane.removeAll();
			contentPane.add(FrmQuanLyHoaDon.contentPane);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmDangXuat)) {
			new Login_JFrame().setVisible(true);
			dispose();
		} else if (obj.equals(mntmThoat)) {
			this.setVisible(false);
			dispose();
		} else if (obj.equals(mntmQuocGia)) {
			new GiaoDienQuanLyQuocGia(role, maNV);
			contentPane.removeAll();
			contentPane.add(GiaoDienQuanLyQuocGia.contentPane);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmDonViTinh)) {
			new GiaoDienQuanLyDonViTinh(role, maNV);
			contentPane.removeAll();
			contentPane.add(GiaoDienQuanLyDonViTinh.contentPane);
			contentPane.revalidate();
			contentPane.repaint();
		} else if (obj.equals(mntmTKHoaDon)) {
			new GiaoDienTimKiemHoaDon(role, maNV).setVisible(true);

		}else if(obj.equals(mntmTKLinhKien)) {
			new GiaoDienTimKiemLinhKien(role, maNV).setVisible(true);
		}else if(obj.equals(mntmTKKhachHang)) {
			new GiaoDienTimKiemKhachHang(role, maNV).setVisible(true);
		}else if(obj.equals(mntmDoiMatKhau)) {
			new TaiKhoan_JFrame(role, maNV).setVisible(true);
		}

	}

	@Override
	public void menuSelected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuDeselected(MenuEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void menuCanceled(MenuEvent e) {
		// TODO Auto-generated method stub

	}

}
