package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.CT_HoaDonDTO;
import project.dtos.HoaDonDTO;

public class CT_HoaDonDAO {
	static Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public CT_HoaDonDAO() {
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

	public List<CT_HoaDonDTO> getCT_HoaDon(String maHD) throws ClassNotFoundException, SQLException {
		List<CT_HoaDonDTO> list = new ArrayList<CT_HoaDonDTO>();
		CT_HoaDonDTO dto = new CT_HoaDonDTO();
		try {
			conn = MyConnection.getConnection();
			String sql = "select sp.MaSP, sp.TenSP,dv.TenDVT,sp.Dongia,ct.Soluong,sp.DonGia*ct.Soluong\"ThanhTien\"\r\n"
					+ "		from CT_HoaDon ct join SanPham sp on ct.MaSP = sp.MaSP\r\n"
					+ "							join DonViTinh dv on sp.MaDVT=dv.MaDVT\r\n"
					+ "							join HoaDon h on h.MaHD=ct.MaHD\r\n" + "		where ct.MaHD like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + maHD + "%");
			rs = preStm.executeQuery();
			while (rs.next()) {
				String ten = rs.getString("TenSP");
				String donvt = rs.getString("TenDVT");
				Double dgia = rs.getDouble("Dongia");
				int sl = rs.getInt("Soluong");
				double tTien = rs.getDouble("ThanhTien");
				dto = new CT_HoaDonDTO(ten, donvt, dgia, sl, tTien);
				list.add(dto);

			}
			return list;
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		
	}

	public boolean themChiTietHoaDon(ArrayList<CT_HoaDonDTO> ctHD) {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			conn.setAutoCommit(false);
			String sql = "Insert into CT_HoaDon(MaHD,MaSP,Soluong,Dongia) values(?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			for (int i = 0; i < ctHD.size(); i++) {
				preStm.setString(1, ctHD.get(i).getMaHD());
				preStm.setString(2, ctHD.get(i).getMaSP());
				preStm.setInt(3, ctHD.get(i).getSoLuong());
				preStm.setDouble(4, ctHD.get(i).getDonGia());
				preStm.addBatch();
			}
			check = preStm.executeBatch().length > (ctHD.size() - 1);
			conn.commit();
			preStm.clearBatch();
			closeConnection();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return check;
	}

	public CT_HoaDonDTO laySoLuogLK() throws ClassNotFoundException, SQLException {

		CT_HoaDonDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select top 1 MaHD, SUM(ct.Soluong)\"Soluong\" ,SUM(Dongia)\"TongTien\"\r\n"
					+ "					from CT_HoaDon ct group by MaHD order by MaHD desc";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();

			while (rs.next()) {
				int sl = rs.getInt("Soluong");
				String mahd = rs.getString("MaHD");
				double tongt = rs.getDouble("TongTien");
				dto = new CT_HoaDonDTO(mahd, sl, tongt);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public HoaDonDTO layThongTinCT_HDByMa(String MaHD) throws ClassNotFoundException, SQLException {
		HoaDonDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select h.MaHD,h.NgayLapHD,nv.HoNV,nv.TenNV,k.TenKH,h.TongTien\r\n"
					+ "	from HoaDon h join KhachHang k on h.MaKH=k.MaKH\r\n"
					+ "		join NhanVien nv on h.MaNV=nv.MaNV where h.MaHD like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + MaHD + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String mahd = rs.getString("MaHD");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("NgayLapHD");
				String hnv = rs.getString("HoNV");
				String tnv = rs.getString("TenNV");
				String tenKH = rs.getString("TenKH");
				double tt = rs.getDouble("TongTien");
				dto = new HoaDonDTO(mahd, thoiGianKhoiTao, tt, tenKH, hnv + " " + tnv);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}
}
