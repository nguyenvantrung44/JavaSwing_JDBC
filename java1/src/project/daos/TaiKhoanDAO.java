package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.TaiKhoanDTO;

public class TaiKhoanDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public TaiKhoanDAO() {
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

	public List<TaiKhoanDTO> dsTaiKhoan(String maNV) throws ClassNotFoundException, SQLException {
		List<TaiKhoanDTO> result;
		TaiKhoanDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from TaiKhoan where MaNV like ?";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String manv = rs.getString("MaNV");
				String tentk = rs.getString("TenTK");
				String mk = rs.getString("MatKhau");
				dto = new TaiKhoanDTO(manv, tentk, mk);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public TaiKhoanDTO layThongTinTaiKhoan(String MaNV) throws ClassNotFoundException, SQLException {
		TaiKhoanDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select *from TaiKhoan\r\n" + "	where MaNV like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + MaNV + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String manv = rs.getString("MaNV");
				String tentk = rs.getString("TenTK");
				String matkhau = rs.getString("MatKhau");

				dto = new TaiKhoanDTO(manv, tentk, matkhau);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean doiMatKhau(String maNV, String matKhau) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "update TaiKhoan set MatKhau=? where MaNV like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, matKhau);
			preStm.setString(2, maNV);

			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}
}
