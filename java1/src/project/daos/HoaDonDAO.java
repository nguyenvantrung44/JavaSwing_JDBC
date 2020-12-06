package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.HoaDonDTO;

public class HoaDonDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public HoaDonDAO() {
		super();
		// TODO Auto-generated constructor stub
	}

	private void closeConnection() throws SQLException {
		if (rs != null) {
			rs.close();
		}
		if (preStm != null) {
			preStm.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

	public boolean themHoaDon(HoaDonDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "Insert into HoaDon(MaHD,NgayLapHD,NgayGiaoHang,NoiNhanHang,MaNV,MaKH,TongTien) values(?,?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaHD());
			preStm.setTimestamp(2, dto.getNgayLapHD());
			preStm.setTimestamp(3, dto.getNgayGiaoHang());
			preStm.setString(4, dto.getNoiNhanHang());
			preStm.setString(5, dto.getMaNV());
			preStm.setString(6, dto.getMaKH());
			preStm.setDouble(7, dto.getTongTien());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public String layMaHoaDon() throws Exception {
		String maHD = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select top 1 MaHD from HoaDon order by MaHD DESC";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			if (rs.next()) {
				maHD = rs.getString("MaHD");
			}
		} finally {
			closeConnection();
		}
		return maHD;
	}

	public List<HoaDonDTO> dsHoaDon() throws Exception {
		List<HoaDonDTO> result;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select ct.MaHD,NgayLapHD,TongTien,kh.TenKH,nv.HoNV,nv.TenNV\r\n"
					+ "		from CT_HoaDon ct join HoaDon h on ct.MaHD=h.MaHD\r\n"
					+ "						join KhachHang kh on kh.MaKH=h.MaKH\r\n"
					+ "						join NhanVien nv on nv.MaNV=h.MaNV";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				Timestamp ngayLapHD = rs.getTimestamp("NgayLapHD");
				Double tTien = rs.getDouble("TongTien");
				String tKH = rs.getString("TenKH");
				String hNV = rs.getString("HoNV");
				String tNV = rs.getString("TenNV");
				String tennhanV = hNV + " " + tNV;
				dto = new HoaDonDTO(maHD, ngayLapHD, tTien, tKH, tennhanV);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> dsHoaDonByMaHD(String ma) throws Exception {
		List<HoaDonDTO> result;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select ct.MaHD,NgayLapHD,TongTien,kh.TenKH,nv.HoNV,nv.TenNV\r\n"
					+ "		from CT_HoaDon ct join HoaDon h on ct.MaHD=h.MaHD\r\n"
					+ "						join KhachHang kh on kh.MaKH=h.MaKH\r\n"
					+ "						join NhanVien nv on nv.MaNV=h.MaNV where ct.MaHD like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + ma + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				Timestamp ngayLapHD = rs.getTimestamp("NgayLapHD");
				Double tTien = rs.getDouble("TongTien");
				String tKH = rs.getString("TenKH");
				String hNV = rs.getString("HoNV");
				String tNV = rs.getString("TenNV");
				String tennhanV = hNV + " " + tNV;
				dto = new HoaDonDTO(maHD, ngayLapHD, tTien, tKH, tennhanV);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> dsHoaDonByTenKH(String ten) throws Exception {
		List<HoaDonDTO> result;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select ct.MaHD,NgayLapHD,TongTien,kh.TenKH,nv.HoNV,nv.TenNV\r\n"
					+ "		from CT_HoaDon ct join HoaDon h on ct.MaHD=h.MaHD\r\n"
					+ "						join KhachHang kh on kh.MaKH=h.MaKH\r\n"
					+ "						join NhanVien nv on nv.MaNV=h.MaNV where kh.TenKH Like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + ten + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				Timestamp ngayLapHD = rs.getTimestamp("NgayLapHD");
				Double tTien = rs.getDouble("TongTien");
				String tKH = rs.getString("TenKH");
				String hNV = rs.getString("HoNV");
				String tNV = rs.getString("TenNV");
				String tennhanV = hNV + " " + tNV;
				dto = new HoaDonDTO(maHD, ngayLapHD, tTien, tKH, tennhanV);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> dsHoaDonByTenNV(String tennv) throws Exception {
		List<HoaDonDTO> result;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select ct.MaHD,NgayLapHD,TongTien,kh.TenKH,nv.HoNV,nv.TenNV\r\n"
					+ "		from CT_HoaDon ct join HoaDon h on ct.MaHD=h.MaHD\r\n"
					+ "						join KhachHang kh on kh.MaKH=h.MaKH\r\n"
					+ "						join NhanVien nv on nv.MaNV=h.MaNV where nv.TenNV Like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tennv + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				Timestamp ngayLapHD = rs.getTimestamp("NgayLapHD");
				Double tTien = rs.getDouble("TongTien");
				String tKH = rs.getString("TenKH");
				String hNV = rs.getString("HoNV");
				String tNV = rs.getString("TenNV");
				String tennhanV = hNV + " " + tNV;
				dto = new HoaDonDTO(maHD, ngayLapHD, tTien, tKH, tennhanV);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> dsHoaDonByNgayLap(int ngay, int thang, int nam) throws Exception {
		List<HoaDonDTO> result;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select ct.MaHD,NgayLapHD,TongTien,kh.TenKH,nv.HoNV,nv.TenNV\r\n"
					+ "from CT_HoaDon ct join HoaDon h on ct.MaHD=h.MaHD\r\n"
					+ "					join KhachHang kh on kh.MaKH=h.MaKH\r\n"
					+ "					join NhanVien nv on nv.MaNV=h.MaNV \r\n"
					+ "					where Day(NgayLapHD)=" + ngay + "and MONTH(NgayLapHD)=" + thang + "and\r\n"
					+ "							YEAR(NgayLapHD)=" + nam + "";
			preStm = conn.prepareStatement(sql);

			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				Timestamp ngayLapHD = rs.getTimestamp("NgayLapHD");
				Double tTien = rs.getDouble("TongTien");
				String tKH = rs.getString("TenKH");
				String hNV = rs.getString("HoNV");
				String tNV = rs.getString("TenNV");
				String tennhanV = hNV + " " + tNV;
				dto = new HoaDonDTO(maHD, ngayLapHD, tTien, tKH, tennhanV);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> timKiemHoaDon(String id) throws ClassNotFoundException, SQLException {
		List<HoaDonDTO> result = null;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaHD, MaNV, MaKH, NgayGiaoHang,TongTien from HoaDon where MaHD LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + id + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				String maNV = rs.getString("MaNV");
				String maKH = rs.getString("MaKH");
				Timestamp ngayGiaoHang = rs.getTimestamp("NgayGiaoHang");
				Double tongtien = rs.getDouble("TongTien");
				dto = new HoaDonDTO(maHD, maNV, maKH, ngayGiaoHang, tongtien);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public List<HoaDonDTO> timKiemHoaDonByDieuKien(String tenKH, String tenNV, int ngay, int thang, int nam)
			throws ClassNotFoundException, SQLException {
		List<HoaDonDTO> result = null;
		HoaDonDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from TimKiemHoaDon(?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenKH + "%");
			preStm.setString(2,tenNV);
			preStm.setInt(3, ngay);
			preStm.setInt(4, thang);
			preStm.setInt(5, nam);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String ma = rs.getString("MaHoaDon");
				String ten = rs.getString("TenKhachHang");
				String tennv = rs.getString("TenNhanVien");
				Timestamp ngaylap = rs.getTimestamp("NgayLapHoaDon");
				dto = new HoaDonDTO(ma, ten, tennv, ngaylap);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean xoaHoaDon(int id) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from HoaDon where MaHD = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, id);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean xoatatcaChiTietHoaDon(List<HoaDonDTO> maHD) {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "Delete from CT_HoaDon where MaHD = ?";
			preStm = conn.prepareStatement(sql);
			for (int i = 0; i < maHD.size(); i++) {
				preStm.setString(1, maHD.get(i).getMaHD());
				preStm.addBatch();
			}
			check = preStm.executeBatch().length > (maHD.size() - 1);
			conn.commit();
			preStm.clearBatch();
			closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	/*
	 * Kiểm Tra Khách Hàng có mua lập Hóa Đon hay không Xóa tất cả các Chi tiết của
	 * Hóa Dơn thep danh sách các Mã Hóa Đon được lập theo Khách Hàng Xóa tất cả các
	 * Hóa Đơn theo Mã Khách Hàng
	 */
	public List<HoaDonDTO> kiemtraHoaDontrongKhachHang(String maKH) throws ClassNotFoundException, SQLException {
		List<HoaDonDTO> result;
		HoaDonDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaHD from HoaDon where MaKH = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maKH);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				dto = new HoaDonDTO(maHD);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean xoatatcaHoaDontrongKhachHang(String maKH) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from HoaDon where MaKH = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maKH);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	/*
	 * Kiểm Tra Nhân Viên có lập Hóa Đơn hay không Xóa tất cả Chi Tiết Hóa Đơn do
	 * Nhân Viên lập theo danh sách Mã Hóa Đơn được Nhân Viên Lập Xóa tất cả các Hóa
	 * Đơn theo Mã Nhân Viên
	 */
	public List<HoaDonDTO> kiemtraHoaDontrongNhanVien(String maNV) throws ClassNotFoundException, SQLException {
		List<HoaDonDTO> result;
		HoaDonDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaHD from HoaDon where MaNV = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNV);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maHD = rs.getString("MaHD");
				dto = new HoaDonDTO(maHD);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean xoatatcaHoaDontrongNhanVien(String maNV) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from HoaDon where MaKH = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNV);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

}
