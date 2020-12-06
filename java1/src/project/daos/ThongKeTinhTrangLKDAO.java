package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.*;

import project.dtos.ThongKeTinhTrangLK;

public class ThongKeTinhTrangLKDAO {
	ArrayList<ThongKeTinhTrangLK> ds;
	ThongKeTinhTrangLK tklk;
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public ThongKeTinhTrangLKDAO() {
		ds = new ArrayList<ThongKeTinhTrangLK>();
	}
	public List<ThongKeTinhTrangLK> ThongKeTTLK(int ngay, int thang, int nam)
			throws ClassNotFoundException, SQLException, ParseException {
		List<ThongKeTinhTrangLK> result = null;
		ThongKeTinhTrangLK dto = null;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "select *from ThongKeLinhKienDaBan(?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, ngay);
			preStm.setInt(2, thang);
			preStm.setInt(3, nam);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String masp = rs.getString("MaSP");
				String tensp = rs.getString("TenSP");
				int soLuongban = rs.getInt("Soluongban");
				Timestamp ngayLap=rs.getTimestamp("NgayLapHD");
				dto = new ThongKeTinhTrangLK(masp, tensp, soLuongban, ngayLap);
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
