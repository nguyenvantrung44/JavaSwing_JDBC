package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.KhachHangDTO;

public class KhachHangDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public KhachHangDAO() {
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

	public boolean themKhachHang(KhachHangDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "Insert into KhachHang(MaKH,TenKh,DiaChi,Phone,Email,Thoigiankhoitao) values(?,?,?,?,?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaKH());
			preStm.setString(2, dto.getTenKH());
			preStm.setString(3, dto.getDiaChi());
			preStm.setString(4, dto.getPhone());
			preStm.setString(5, dto.getEmail());
			preStm.setTimestamp(6, dto.getDateCreate());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<KhachHangDTO> dsKhachHang() throws Exception {
		List<KhachHangDTO> result;
		KhachHangDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaKH,TenKh, Phone, DiaChi, Email, Thoigiankhoitao from KhachHang";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tKH = rs.getString("TenKH");
				String ph = rs.getString("Phone");
				String dc = rs.getString("DiaChi");
				String email = rs.getString("Email");
				Timestamp thKT = rs.getTimestamp("Thoigiankhoitao");
				dto = new KhachHangDTO(maKH, tKH, dc, ph, email, thKT);
				result.add(dto);
			}
		} finally {
			// TODO Auto-generated catch block
			closeConnection();
		}
		return result;
	}

	public KhachHangDTO layThongTinKhachHang(String Phone) throws ClassNotFoundException, SQLException {
		KhachHangDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaKH, TenKh ,DiaChi, Phone, Email, Thoigiankhoitao from KhachHang where Phone LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, Phone);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String mKH = rs.getString("MaKH");
				String tKH = rs.getString("TenKH");
				String dC = rs.getString("DiaChi");
				String ph = rs.getString("Phone");
				String em = rs.getString("Email");
				Timestamp thKT = rs.getTimestamp("Thoigiankhoitao");
				dto = new KhachHangDTO(mKH, tKH, dC, ph, em, thKT);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public KhachHangDTO layThongTinKhachHangByMaKH(String makh) throws ClassNotFoundException, SQLException {
		KhachHangDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaKH, TenKh ,DiaChi, Phone, Email, Thoigiankhoitao from KhachHang where MaKH LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, makh);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String mKH = rs.getString("MaKH");
				String tKH = rs.getString("TenKH");
				String dC = rs.getString("DiaChi");
				String ph = rs.getString("Phone");
				String em = rs.getString("Email");
				Timestamp thKT = rs.getTimestamp("Thoigiankhoitao");
				dto = new KhachHangDTO(mKH, tKH, dC, ph, em, thKT);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean xoaKhachHang(String id) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Delete from KhachHang where MaKH = ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, id);
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public boolean capnhatKhachHang(KhachHangDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "Update KhachHang set TenKh = ?, DiaChi = ?, Phone = ?, Email = ? where MaKH LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getTenKH());
			preStm.setString(2, dto.getDiaChi());
			preStm.setString(3, dto.getPhone());
			preStm.setString(4, dto.getEmail());
			preStm.setString(5, dto.getMaKH());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<KhachHangDTO> timKiemKhachHang(String sdt) throws ClassNotFoundException, SQLException {
		List<KhachHangDTO> result = null;
		KhachHangDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaKH,TenKh, DiaChi, Phone, Email, Thoigiankhoitao from KhachHang where Phone LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maKH = rs.getString("MaKH");
				String tenKH = rs.getString("TenKH");
				String dc = rs.getString("DiaChi");
				String phone = rs.getString("Phone");
				String email = rs.getString("Email");
				Timestamp thKT = rs.getTimestamp("Thoigiankhoitao");
				dto = new KhachHangDTO(maKH, tenKH, dc, phone, email, thKT);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public List<KhachHangDTO> timKiemKhachHangByDK(String ten, String sdt) throws ClassNotFoundException, SQLException {
		List<KhachHangDTO> result = null;
		KhachHangDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from TimKiemKhachHang(?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + ten + "%");
			preStm.setString(2, "%" + sdt + "%");
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String tenKH = rs.getString("tenKH");
				String dc = rs.getString("diaChi");
				String phone = rs.getString("Phone");
				String email1 = rs.getString("Email");
				Timestamp thKT = rs.getTimestamp("Thoigiankhoitao");
				dto = new KhachHangDTO(tenKH, dc, phone, email1, thKT);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public KhachHangDTO timKhachHangTheoSoDienThoai(String phone) throws ClassNotFoundException, SQLException {
		KhachHangDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaKH, Phone from KhachHang where Phone LIKE ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + phone + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String id = rs.getString("MaKH");
				dto = new KhachHangDTO(id, phone);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public String timKhachHang() throws ClassNotFoundException, SQLException {
		String id = "";
		try {
			conn = MyConnection.getConnection();
			String sql = "select top 1 MaKH from KhachHang order by MaKH desc";
			preStm = conn.prepareStatement(sql);
			// preStm.setString(1, "%" + phone + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				id = rs.getString("MaKH");
			}
		} finally {
			closeConnection();
		}
		return id;
	}
}
