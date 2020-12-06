package project.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import project.db.MyConnection;
import project.dtos.QuocGiaDTO;

public class QuocGiaDAO {
	Connection conn;
	PreparedStatement preStm;
	ResultSet rs;

	public QuocGiaDAO() {
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

	public boolean themQuocGia(QuocGiaDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = project.db.MyConnection.getConnection();
			String sql = "insert into QuocGia(MaQG,TenQG)" + " values (?,?)";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getMaQG());
			preStm.setString(2, dto.getTenQG());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}

	public List<QuocGiaDTO> dsQuocGia() throws ClassNotFoundException, SQLException {
		List<QuocGiaDTO> result;
		QuocGiaDTO dto;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaQG,TenQG from QuocGia";
			preStm = conn.prepareStatement(sql);
			rs = preStm.executeQuery();
			result = new ArrayList<>();
			while (rs.next()) {
				String maQG = rs.getString("MaQG");
				String tenQG = rs.getString("TenQG");
				dto = new QuocGiaDTO(maQG, tenQG);
				result.add(dto);
			}
		} finally {
			closeConnection();
		}
		return result;
	}

	public QuocGiaDTO layThongTinQuocGia(String MaQG) throws ClassNotFoundException, SQLException {
		QuocGiaDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "Select MaQG,TenQG from QuocGia where MaQG like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + MaQG + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maQG = rs.getString("MaQG");
				String tenQG = rs.getString("TenQG");

				dto = new QuocGiaDTO(maQG, tenQG);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public QuocGiaDTO layThongTinQuocGiaBySP(String tenSP) throws ClassNotFoundException, SQLException {
		QuocGiaDTO dto = null;
		try {
			conn = MyConnection.getConnection();
			String sql = "select qg.MaQG, qg.TenQG from SanPham sp join QuocGia qg on sp.MaQG = qg.MaQG\r\n"
					+ "where TenSP like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, "%" + tenSP + "%");
			rs = preStm.executeQuery();
			if (rs.next()) {
				String maQG = rs.getString("MaQG");
				String tenQG = rs.getString("TenQG");
				dto = new QuocGiaDTO(maQG, tenQG);
			}
		} finally {
			closeConnection();
		}
		return dto;
	}

	public boolean capnhatQuocGia(QuocGiaDTO dto) throws ClassNotFoundException, SQLException {
		boolean check = false;
		try {
			conn = MyConnection.getConnection();
			String sql = "update QuocGia set TenQG=? where MaQG like ?";
			preStm = conn.prepareStatement(sql);
			preStm.setString(1, dto.getTenQG());
			preStm.setString(2, dto.getMaQG());
			check = preStm.executeUpdate() > 0;
		} finally {
			closeConnection();
		}
		return check;
	}
}
