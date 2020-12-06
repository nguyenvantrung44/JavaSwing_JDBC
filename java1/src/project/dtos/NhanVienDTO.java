package project.dtos;

import java.sql.Timestamp;
import java.util.Vector;

public class NhanVienDTO {
	private String maNV, role, hoNV, tenNV, diaChi, phone, email;
	private boolean gioiTinh;
	private Timestamp thoiGianKhoiTao;

	public NhanVienDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhanVienDTO(String maNV, String role) {
		super();
		this.maNV = maNV;
		this.role = role;
	}

	public NhanVienDTO(String maNV, String role, String hoNV, String tenNV, String diaChi, String phone, String email,
			boolean gioiTinh, Timestamp thoiGianKhoiTao) {
		super();
		this.maNV = maNV;
		this.role = role;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.diaChi = diaChi;
		this.phone = phone;
		this.email = email;
		this.gioiTinh = gioiTinh;
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public NhanVienDTO(String maNV, String hoNV, String tenNV, String phone, String role) {
		super();
		this.maNV = maNV;
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.phone = phone;
		this.role = role;
	}

	public NhanVienDTO(String hoNV, String tenNV, String phone, String role) {
		super();
		this.hoNV = hoNV;
		this.tenNV = tenNV;
		this.phone = phone;
		this.role = role;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getHoNV() {
		return hoNV;
	}

	public void setHoNV(String hoNV) {
		this.hoNV = hoNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public Timestamp getThoiGianKhoiTao() {
		return thoiGianKhoiTao;
	}

	public void setThoiGianKhoiTao(Timestamp thoiGianKhoiTao) {
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	@Override
	public String toString() {
		return "NhanVienDTO [maNV=" + maNV + ", role=" + role + ", hoNV=" + hoNV + ", tenNV=" + tenNV + ", diaChi="
				+ diaChi + ", phone=" + phone + ", email=" + email + ", gioiTinh=" + gioiTinh + ", thoiGianKhoiTao="
				+ thoiGianKhoiTao + "]";
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(hoNV);
		v.add(tenNV);
		v.add(phone);
		v.add(role);
		return v;
	}
}
