package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.NhaCungCapDTO;

public class NhaCungCapDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public NhaCungCapDAO() {
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

	public boolean themNhaCungCap(NhaCungCapDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "insert into Nhacungcap(MaNCC,TenNCC,Diachi,Phone,SoFax,Email,Thoigiankhoitao) values(?,?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaNCC());
			preStm.setString(2, dto.getTenNCC());
			preStm.setString(3, dto.getDiaChi());
			preStm.setString(4, dto.getPhone());
			preStm.setString(5, dto.getSoFax());
			preStm.setString(6, dto.getEmail());
			preStm.setTimestamp(7, dto.getThoiGianKhoiTao());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<NhaCungCapDTO> dsNhaCungCap() throws Exception {
		List<NhaCungCapDTO> result = null;
		NhaCungCapDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select MaNCC, TenNCC, Email, Phone from Nhacungcap";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maNCC = rs.getString("MaNCC");
				String tenNCC = rs.getString("TenNCC");
				String email = rs.getString("Email");
				String phone = rs.getString("Phone");
				dto = new NhaCungCapDTO(maNCC, tenNCC, phone, email);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public NhaCungCapDTO layThongTinNhaCungCap(String tenNCC) throws ClassNotFoundException, SQLException {
		NhaCungCapDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNCC, TenNCC, Diachi, Phone, SoFax, Email, Thoigiankhoitao from Nhacungcap where TenNCC LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, tenNCC);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maNCC = rs.getString("MaNCC");
				String tenNCC2 = rs.getString("TenNCC");
				String diaChi = rs.getString("DiaChi");
				String phone = rs.getString("Phone");
				String soFax = rs.getString("SoFax");
				String email = rs.getString("Email");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				dto = new NhaCungCapDTO(maNCC, tenNCC2, diaChi, email, phone, soFax, thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public NhaCungCapDTO layThongTinNhaCungCapBySP(String tenSP) throws ClassNotFoundException, SQLException {
		NhaCungCapDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from SanPham sp join Nhacungcap ncc on sp.MaNCC=ncc.MaNCC\r\n"
					+ "						where sp.TenSP Like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, tenSP);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maNCC = rs.getString("MaNCC");
				String tenNCC2 = rs.getString("TenNCC");
				String diaChi = rs.getString("DiaChi");
				String phone = rs.getString("Phone");
				String soFax = rs.getString("SoFax");
				String email = rs.getString("Email");
				Timestamp thoiGianKhoiTao = rs.getTimestamp("Thoigiankhoitao");
				dto = new NhaCungCapDTO(maNCC, tenNCC2, diaChi, email, phone, soFax, thoiGianKhoiTao);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean xoaNhaCungCap(String maNCC) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from Nhacungcap where MaNCC = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, maNCC);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean capnhatNhaCungCap(NhaCungCapDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Update Nhacungcap set TenNCC = ?, Diachi = ?, Email = ?, Phone = ?, SoFax = ?, Thoigiankhoitao = ? where MaNCC LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getTenNCC());
			preStm.setString(2, dto.getDiaChi());
			preStm.setString(3, dto.getEmail());
			preStm.setString(4, dto.getPhone());
			preStm.setString(5, dto.getSoFax());
			preStm.setTimestamp(6, dto.getThoiGianKhoiTao());
			preStm.setString(7, dto.getMaNCC());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<NhaCungCapDTO> timKiemNhaCungCap(String tenNCC) throws ClassNotFoundException, SQLException {
		List<NhaCungCapDTO> result = null;
		NhaCungCapDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaNCC, TenNCC, Email, Phone from Nhacungcap where TenNCC LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenNCC + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maNCC = rs.getString("MaNCC");
				String tenNCC2 = rs.getString("TenNCC");
				String phone = rs.getString("Email");
				String email = rs.getString("Phone");
				dto = new NhaCungCapDTO(maNCC, tenNCC2, email, phone);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}
}