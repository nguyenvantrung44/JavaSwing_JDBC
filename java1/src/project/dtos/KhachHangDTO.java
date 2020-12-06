package project.dtos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class KhachHangDTO {
	private String maKH, tenKH, diaChi, phone, email;
	private Timestamp dateCreate;

	public KhachHangDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public KhachHangDTO(String maKH) {
		super();
		this.maKH = maKH;
	}

	public KhachHangDTO(String maKH, String tenKH, String diaChi, String phone, String email, Timestamp dateCreate) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.phone = phone;
		this.email = email;
		this.dateCreate = dateCreate;
	}

	public KhachHangDTO(String tenKH, String diaChi, String phone, String email, Timestamp dateCreate) {
		super();

		this.tenKH = tenKH;
		this.diaChi = diaChi;
		this.phone = phone;
		this.email = email;
		this.dateCreate = dateCreate;
	}

	public KhachHangDTO(String maKH, String phone) {
		super();
		this.maKH = maKH;
		this.phone = phone;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
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

	public Timestamp getDateCreate() {
		return dateCreate;
	}

	public void setDateCreate(Timestamp dateCreate) {
		this.dateCreate = dateCreate;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY");

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(tenKH);
		v.add(diaChi);
		v.add(phone);
		v.add(email);
		v.add(sdf.format(dateCreate));
		return v;

	}

	public Vector<String> toVector1() {
		Vector<String> v = new Vector<>();
		v.add(maKH);
		v.add(tenKH);
		v.add(phone);
		v.add(diaChi);
		return v;
	}
}
