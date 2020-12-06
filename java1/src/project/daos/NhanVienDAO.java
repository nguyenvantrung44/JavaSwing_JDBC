package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.NhanVienDTO;

public class NhanVienDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public NhanVienDAO() {
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

	public boolean themNhanVien(NhanVienDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "Insert into NhanVien(MaNV,Role,HoNV,TenNV,DiaChi,Phone,Email,GioiTinh,Thoigiankhoitao)"
					+ " values(?,?,?,?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaNV());
			preStm.setString(2, dto.getRole());
			preStm.setString(3, dto.getHoNV());
			preStm.setString(4, dto.getTenNV());
			preStm.setString(5, dto.getDiaChi());
			preStm.setString(6, dto.getPhone());
			preStm.setString(7, dto.getEmail());
			preStm.setBoolean(8, dto.isGioiTinh());
			preStm.setTimestamp(9, dto.getThoiGianKhoiTao());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<NhanVienDTO> dsNhanVien() throws Exception {
		List<NhanVienDTO> result = null;
		NhanVienDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select  MaNV, HoNV, TenNV, Phone, Role from NhanVien";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maNV = rs.getString("MaNV");
				String hoNV = rs.getString("HoNV");
				String tenNV = rs.getString("TenNV");
				String phone = rs.getString("Phone");
				String role = rs.getString("Role");
				dto = new NhanVienDTO(maNV, hoNV, tenNV, phone, role);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public NhanVienDTO layThongTinNhanVien(String sdt) throws ClassNotFoundException, SQLException {
		NhanVienDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNV,Role,HoNV,TenNV,DiaChi,Phone,Email,"
					+ "GioiTinh,Thoigiankhoitao from NhanVien where Phone = ?";

			preStm = conn.prepareStatement(sql);
			preStm.setString(1, sdt);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maNV = rs.getString("MaNV");
				String role = rs.getString("Role");
				String hoNV = rs.getString("HoNV");
				String tenNV = rs.getString("TenNV");
				String diaChi = rs.getString("DiaChi");
				String phone = rs.getString("Phone");
				String email = rs.getString("Email");
				boolean gioiTinh = rs.getBoolean("GioiTinh");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				dto = new NhanVienDTO(maNV, role, hoNV, tenNV, diaChi, phone, email, gioiTinh, thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public NhanVienDTO layThongTinNhanVienByMaNV(String ma) throws ClassNotFoundException, SQLException {
		NhanVienDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNV,Role,HoNV,TenNV,DiaChi,Phone,Email,"
					+ "GioiTinh,Thoigiankhoitao from NhanVien where MaNV = ?";

			preStm = conn.prepareStatement(sql);
			preStm.setString(1, ma);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maNV = rs.getString("MaNV");
				String role = rs.getString("Role");
				String hoNV = rs.getString("HoNV");
				String tenNV = rs.getString("TenNV");
				String diaChi = rs.getString("DiaChi");
				String phone = rs.getString("Phone");
				String email = rs.getString("Email");
				boolean gioiTinh = rs.getBoolean("GioiTinh");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				dto = new NhanVienDTO(maNV, role, hoNV, tenNV, diaChi, phone, email, gioiTinh, thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean xoaNhanVien(String maNV) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from NhanVien where MaNV = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNV);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean capnhatNhanVien(NhanVienDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Update NhanVien set  Role = ?, HoNV = ?, TenNV = ?, "
					+ "DiaChi = ?, Phone = ?, Email = ?, GioiTinh = ? where MaNV = ?";
			preStm = conn.prepareStatement(sql);

			preStm.setString(1, dto.getRole());
			preStm.setString(2, dto.getHoNV());
			preStm.setString(3, dto.getTenNV());
			preStm.setString(4, dto.getDiaChi());
			preStm.setString(5, dto.getPhone());
			preStm.setString(6, dto.getEmail());
			preStm.setBoolean(7, dto.isGioiTinh());
			preStm.setString(8, dto.getMaNV());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<NhanVienDTO> timkiemNhanVien(String sdt) throws ClassNotFoundException, SQLException {
		List<NhanVienDTO> result = null;
		NhanVienDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select HoNV, TenNV, Phone, Role from NhanVien where Phone LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String hoNV = rs.getString("HoNV");
				String tenNV = rs.getString("TenNV");
				String phone = rs.getString("Phone");
				String role = rs.getString("Role");
				dto = new NhanVienDTO(hoNV, tenNV, phone, role);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public List<NhanVienDTO> timkiemNhanVienTen(String ten) throws ClassNotFoundException, SQLException {
		List<NhanVienDTO> result = null;
		NhanVienDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNV, HoNV, TenNV, Phone, Role from NhanVien where TenNV LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + ten + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String hoNV = rs.getString("HoNV");
				String tenNV = rs.getString("TenNV");
				String phone = rs.getString("Phone");
				String role = rs.getString("Role");
				dto = new NhanVienDTO(hoNV, tenNV, phone, role);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public NhanVienDTO timkiemNhanVienMa(String ma) throws ClassNotFoundException, SQLException {

		NhanVienDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNV, Role from NhanVien where MaNV LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + ma + "%");
			rs = preStm.executeQuery();

			while (rs.next()) {
				String manv = rs.getString("MaNV");
				String role = rs.getString("Role");

				dto = new NhanVienDTO(manv, role);
				return dto;
			}
		} finally {
			closeConnection();
		}
		return dto = null;
	}
}