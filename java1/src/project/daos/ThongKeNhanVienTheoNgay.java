package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.dtos.ThongKeHDNhanVien;

public class ThongKeNhanVienTheoNgay {
	ArrayList<ThongKeHDNhanVien> ds;
	ThongKeHDNhanVien tknv;
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public ThongKeNhanVienTheoNgay() {
		ds = new ArrayList<ThongKeHDNhanVien>();
	}

	public List<ThongKeHDNhanVien> ThongKeHDNhanVienTheoNgay(String ma, int ngay, int thang, int nam)
			throws ClassNotFoundException, SQLException {
		List<ThongKeHDNhanVien> result = null;
		ThongKeHDNhanVien dto = null;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "select *from ThongKeSanPhamNhanVienDaBanTheoNgay(?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1,ma);
			preStm.setInt(2, ngay);
			preStm.setInt(3, thang);
			preStm.setInt(4, nam);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String tensp = rs.getString("TenSanPham");
				Timestamp ngayLap = rs.getTimestamp("NgayLapHD");
				int soLuongBan = rs.getInt("SoLuong");
				double tongTien = rs.getDouble("TongTien");
				
				dto = new ThongKeHDNhanVien(tensp, ngayLap, soLuongBan, tongTien);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
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

}
