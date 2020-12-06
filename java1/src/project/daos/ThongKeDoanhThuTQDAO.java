package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import project.dtos.ThongKeDoanhThuDTO;

public class ThongKeDoanhThuTQDAO {
	ArrayList<ThongKeDoanhThuDTO> ds;
	ThongKeDoanhThuDTO tkdt;
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public ThongKeDoanhThuTQDAO() {
		ds = new ArrayList<ThongKeDoanhThuDTO>();
	}

	public List<ThongKeDoanhThuDTO> ThongKeDoanhThuTQ(int thang, int nam)
			throws ClassNotFoundException, SQLException, ParseException {
		List<ThongKeDoanhThuDTO> result = null;
		ThongKeDoanhThuDTO dto = null;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "select sp.MaSP,sp.TenSP,sp.DonGia,sp.GiaGoc,sum(ct.Soluong)\"soluongban\",\r\n" + 
					"sum(ct.Soluong*sp.DonGia) as \"TienBan\",count(ct.MaHD)\"Soluonghd\"\r\n" + 
					"from CT_HoaDon ct  join SanPham sp on ct.MaSP=sp.MaSP\r\n" + 
					"join HoaDon h on ct.MaHD=h.MaHD\r\n" + 
					"	where MONTH(h.NgayLapHD) like ? and YEAR(h.NgayLapHD) like ?\r\n" + 
					"	group by  sp.MaSP,sp.TenSP,sp.DonGia,sp.GiaGoc";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, thang);
			preStm.setInt(2, nam);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String masp = rs.getString("MaSP");
				String tensp = rs.getString("TenSP");
				double dongia = rs.getDouble("DonGia");
				double giagoc = rs.getDouble("GiaGoc");
				int soluongban = rs.getInt("soluongban");
				double tienban = rs.getDouble("TienBan");
				int solghd=rs.getInt("Soluonghd");

				dto = new ThongKeDoanhThuDTO(masp, tensp, dongia, giagoc, tienban, soluongban,solghd);
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
