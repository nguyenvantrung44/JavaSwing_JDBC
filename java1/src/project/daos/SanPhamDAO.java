package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.SanPhamDTO;

public class SanPhamDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public SanPhamDAO() {
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

	public boolean themSanPham(SanPhamDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "Insert into SanPham(MaSP,TenSP,MaNCC,MaDVT,GiaGoc,MoTa,SLTon,Thoigiankhoitao,DonGia,MaQG)"
					+ "values(?,?,?,?,?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaSP());
			preStm.setString(2, dto.getTenSP());
			preStm.setString(3, dto.getMaNCC());
			preStm.setString(4, dto.getMaDVT());
			preStm.setDouble(5, dto.getGiaGoc());
			preStm.setString(6, dto.getMoTa());
			preStm.setInt(7, dto.getSoLuongTon());
			preStm.setTimestamp(8, dto.getThoiGianKhoiTao());
			preStm.setDouble(9, dto.getGiaBan());
			preStm.setString(10, dto.getMaQG());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<SanPhamDTO> dsSanPham() throws ClassNotFoundException, SQLException {
		List<SanPhamDTO> result;
		SanPhamDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaSP, TenSP, GiaGoc, DonGia, SLTon, sp.MaDVT,dvt.TenDVT from SanPham sp join DonViTinh dvt\r\n"
					+ "												on sp.MaDVT=dvt.MaDVT";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP = rs.getString("TenSP");
				double giaGoc = rs.getDouble("GiaGoc");
				double giaBan = rs.getDouble("DonGia");
				int soLuongTon = rs.getInt("SLTon");
				String donViTinh = rs.getString("TenDVT");
				dto = new SanPhamDTO(maSP, tenSP, giaGoc, giaBan, soLuongTon, donViTinh);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public SanPhamDTO layThongTinSanPham(String tenSP) throws ClassNotFoundException, SQLException {
		SanPhamDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaSP,TenSP,MaNCC,MaDVT,GiaGoc,MoTa,SLTon,Thoigiankhoitao,DonGia,MaQG from SanPham where TenSP LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenSP + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP2 = rs.getString("TenSP");
				String maNCC = rs.getString("MaNCC");
				String maDVT = rs.getString("MaDVT");
				double giaGoc = rs.getDouble("GiaGoc");
				double giaBan = rs.getDouble("DonGia");
				String moTa = rs.getString("MoTa");
				int soLuongTon = rs.getInt("SLTon");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				String maQG = rs.getString("MaQG");
				dto = new SanPhamDTO(maSP, tenSP2, maNCC, maDVT, moTa, maQG, giaGoc, giaBan, soLuongTon,
						thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public SanPhamDTO layThongTinSanPhamByMa(String MaSP) throws ClassNotFoundException, SQLException {
		SanPhamDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaSP,TenSP,MaNCC,MaDVT,GiaGoc,MoTa,SLTon,Thoigiankhoitao,DonGia,MaQG from SanPham where MaSP LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + MaSP + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP2 = rs.getString("TenSP");
				String maNCC = rs.getString("MaNCC");
				String maDVT = rs.getString("MaDVT");
				double giaGoc = rs.getDouble("GiaGoc");
				double giaBan = rs.getDouble("DonGia");
				String moTa = rs.getString("MoTa");
				int soLuongTon = rs.getInt("SLTon");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				String maQG = rs.getString("MaQG");
				dto = new SanPhamDTO(maSP, tenSP2, maNCC, maDVT, moTa, maQG, giaGoc, giaBan, soLuongTon,
						thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean xoaSanPham(String maSP) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from SanPham where MaSP LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maSP);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean xoatatcaSanPhamcuaNCC(String maNCC) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from SanPham where MaNCC LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNCC);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean capnhatSanPham(SanPhamDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Update SanPham set TenSP = ?, MaNCC = ?, MaQG = ?, MaDVT = ?,"
					+ " GiaGoc = ?, MoTa = ?, SLTon = ?, Thoigiankhoitao = ?, DonGia=? where MaSP LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getTenSP());
			preStm.setString(2, dto.getMaNCC());
			preStm.setString(3, dto.getMaQG());
			preStm.setString(4, dto.getMaDVT());
			preStm.setDouble(5, dto.getGiaGoc());
			preStm.setString(6, dto.getMoTa());
			preStm.setInt(7, dto.getSoLuongTon());
			preStm.setTimestamp(8, dto.getThoiGianKhoiTao());
			preStm.setDouble(9, dto.getGiaBan());
			preStm.setString(10, dto.getMaSP());

			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<SanPhamDTO> timkiemSanPham(String tenSP, String maNCC) throws ClassNotFoundException, SQLException {
		List<SanPhamDTO> result;
		SanPhamDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaSP, TenSP, GiaGoc, DonGia, SLTon, MaDVT from SanPham "
					+ "where TenSP LIKE ? and MaNCC LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenSP + "%");
			preStm.setString(2, "%" + maNCC + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP2 = rs.getString("TenSP");
				double giaGoc = rs.getDouble("GiaGoc");
				double giaBan = rs.getDouble("DonGia");
				int soLuongTon = rs.getInt("SLTon");
				String maDVT = rs.getString("MaDVT");
				dto = new SanPhamDTO(maSP, tenSP2, giaGoc, giaBan, soLuongTon, maDVT);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public List<SanPhamDTO> timkiemSanPhamBanHang(String id, String maNCC) throws ClassNotFoundException, SQLException {
		List<SanPhamDTO> result;
		SanPhamDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaSP , TenSP, isnull(SLTon - (Select sum(Soluong) from CT_HoaDon where MaSP = SanPham.MaSP),SanPham.SLTon) as SoLuongDaDung, GiaGoc \r\n"
					+ "from SanPham \r\n"
					+ "where MaSP LIKE ? and MaNCC LIKE ? and isnull(SLTon - (Select sum(Soluong) from CT_HoaDon where MaSP = SanPham.MaSP),SanPham.SLTon) > 0\r\n"
					+ "";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + id + "%");
			preStm.setString(2, "%" + maNCC + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maSP = rs.getString("MaSP");
				String tenSP = rs.getString("TenSP");
				int soLuongTon = rs.getInt("SoLuongDaDung");
				double giaGoc = rs.getDouble("GiaGoc");
				dto = new SanPhamDTO(maSP, tenSP, giaGoc, soLuongTon);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public boolean capnhatSLToncuaSanPham(String ma, int slTon) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "update SanPham set SLTon = ? where MaSP LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setInt(1, slTon);
			preStm.setString(2, "%" + ma + "%");
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<SanPhamDTO> timkiemSanPham1(double giad, double giac, String mancc, String tensp)
			throws ClassNotFoundException, SQLException {
		List<SanPhamDTO> result;
		SanPhamDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from TimKiemLinhKien(?,?,?,?)";
			preStm = conn.prepareStatement(sql);

			preStm.setString(1, "%" + tensp + "%");
			preStm.setString(2, mancc);
			preStm.setDouble(3, giad);
			preStm.setDouble(4, giac);

			rs = preStm.executeQuery();
			result = new ArrayList<>();

			while (rs.next()) {
				String tenSP = rs.getString("TenSanPham");
				double giaGoc = rs.getDouble("GiaGoc");
				double giaBan = rs.getDouble("DonGia");
				int sl = rs.getInt("SoLuongTon");
				String tenDVT = rs.getString("TenDonViTinh");

				dto = new SanPhamDTO(tenSP, tenDVT, giaGoc, giaBan, sl);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

}
