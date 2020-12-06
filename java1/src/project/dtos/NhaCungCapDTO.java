package project.dtos;

import java.sql.Timestamp;
import java.util.Vector;

public class NhaCungCapDTO {
	private String maNCC, tenNCC, diaChi, email, phone, soFax;
	private Timestamp thoiGianKhoiTao;

	public NhaCungCapDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NhaCungCapDTO(String maNCC) {
		super();
		this.maNCC = maNCC;
	}

	public NhaCungCapDTO(String maNCC, String tenNCC, String diaChi, String email, String phone, String soFax,
			Timestamp thoiGianKhoiTao) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.email = email;
		this.phone = phone;
		this.soFax = soFax;
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public NhaCungCapDTO(String tenNCC, String diaChi, String email, String phone, String soFax,
			Timestamp thoiGianKhoiTao) {
		super();
		this.tenNCC = tenNCC;
		this.diaChi = diaChi;
		this.email = email;
		this.phone = phone;
		this.soFax = soFax;
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public NhaCungCapDTO(String maNCC, String tenNCC, String email, String phone) {
		super();
		this.maNCC = maNCC;
		this.tenNCC = tenNCC;
		this.email = email;
		this.phone = phone;
	}

	public NhaCungCapDTO(String tenNCC, String email, String phone) {
		super();
		this.tenNCC = tenNCC;
		this.email = email;
		this.phone = phone;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getTenNCC() {
		return tenNCC;
	}

	public void setTenNCC(String tenNCC) {
		this.tenNCC = tenNCC;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSoFax() {
		return soFax;
	}

	public void setSoFax(String soFax) {
		this.soFax = soFax;
	}

	public Timestamp getThoiGianKhoiTao() {
		return thoiGianKhoiTao;
	}

	public void setThoiGianKhoiTao(Timestamp thoiGianKhoiTao) {
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(tenNCC);
		v.add(phone);
		v.add(email);
		return v;
	}

	@Override
	public String toString() {
		return "NhaCungCapDTO [maNCC=" + maNCC + ", tenNCC=" + tenNCC + ", diaChi=" + diaChi + ", email=" + email
				+ ", phone=" + phone + ", soFax=" + soFax + ", thoiGianKhoiTao=" + thoiGianKhoiTao + "]";
	}

}
