package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import project.dtos.LoginDTO;

public class LoginDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public LoginDAO() {

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

	public LoginDTO checkLogin(String username, String password) throws ClassNotFoundException, SQLException {
		LoginDTO dto = null;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "select TenTK, MatKhau, nv.MaNV,Role\r\n" + "from TaiKhoan tk join\r\n"
					+ "	 NhanVien nv on\r\n" + "	 tk.MaNV = nv.MaNV\r\n" + "where TenTK like ? and MatKhau like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, username);
			preStm.setString(2,password);
			rs = preStm.executeQuery();
			if (rs.next()) {
				String role = rs.getString("Role");
				String manv = rs.getString("MaNV");
				String ten = rs.getString("TenTK");
				String mk = rs.getString("Matkhau");

				dto = new LoginDTO(ten, manv, mk, role);
				return dto;

			}
		} finally {
			closeConnection();
		}
		return dto;
	}
}
