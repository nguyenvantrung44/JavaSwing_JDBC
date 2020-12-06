package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.DonViTinhDTO;

public class DonViTinhDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public DonViTinhDAO() {
		super();
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

	public boolean themDonViTinh(DonViTinhDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "insert into DonViTinh(MaDVT,TenDVT)" + " values (?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaDVT());
			preStm.setString(2, dto.getTenDVT());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<DonViTinhDTO> dsDonViTinh() throws ClassNotFoundException, SQLException {
		List<DonViTinhDTO> result;
		DonViTinhDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaDVT,TenDVT from DonViTinh";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maDVT = rs.getString("MaDVT");
				String tenDVT = rs.getString("TenDVT");
				dto = new DonViTinhDTO(maDVT, tenDVT);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public DonViTinhDTO layThongTinDonViTinh(String MaDVT) throws ClassNotFoundException, SQLException {
		DonViTinhDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaDVT,TenDVT from DonViTinh where MaDVT like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + MaDVT + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maDVT = rs.getString("MaDVT");
				String tenDVT = rs.getString("TenDVT");

				dto = new DonViTinhDTO(maDVT, tenDVT);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public DonViTinhDTO layThongTinDonViTinhBySP(String tenSP) throws ClassNotFoundException, SQLException {
		DonViTinhDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select dvt.MaDVT, dvt.TenDVT from SanPham sp join DonViTinh dvt on sp.MaDVT = dvt.MaDVT\r\n"
					+ "where TenSP like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenSP + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maDVT = rs.getString("MaDVT");
				String tenDVT = rs.getString("TenDVT");
				dto = new DonViTinhDTO(maDVT, tenDVT);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean capnhatDonViTinh(DonViTinhDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "update DonViTinh set TenDVT = ? where MaDVT like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getTenDVT());
			preStm.setString(2, dto.getMaDVT());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}
}
