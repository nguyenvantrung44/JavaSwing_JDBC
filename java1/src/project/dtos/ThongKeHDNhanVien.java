package project.dtos;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ThongKeHDNhanVien {

	private String maHD, maNV, tenNV, maLK, tenLK;
	private Double giaBan, thanhTien;
	private int soLuong, tongSHD;
	private Timestamp ngayLap;

	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	DecimalFormat df = new DecimalFormat("#,###.0(VND)");
	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getMaLK() {
		return maLK;
	}

	public void setMaLK(String maLK) {
		this.maLK = maLK;
	}

	public String getTenLK() {
		return tenLK;
	}

	public void setTenLK(String tenLK) {
		this.tenLK = tenLK;
	}

	public Double getthanhTien() {
		return thanhTien;
	}

	public void setthanhTien(Double thanhTien) {
		this.thanhTien = thanhTien;
	}

	public int getTongSHD() {
		return tongSHD;
	}

	public void setTongSHD(int tongSHD) {
		this.tongSHD = tongSHD;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public Timestamp getngayLap() {
		return ngayLap;
	}

	public void setngayLap(Timestamp ngayLap) {
		this.ngayLap = ngayLap;
	}

	public Double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(Double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public ThongKeHDNhanVien() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ThongKeHDNhanVien(String maNV, String tenNV, String maLK, String tenLK, Timestamp ngayLap, Double giaBan,
			Double thanhTien, int soLuong) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.maLK = maLK;
		this.tenLK = tenLK;
		this.ngayLap = ngayLap;
		this.giaBan = giaBan;
		this.thanhTien = thanhTien;
		this.soLuong = soLuong;
	}
	
	public ThongKeHDNhanVien(String tenLK, Timestamp ngayLap, int soLuong, Double thanhTien) {
		super();
		this.tenLK = tenLK;
		this.thanhTien = thanhTien;
		this.soLuong = soLuong;
		this.ngayLap = ngayLap;
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add(tenLK);
		v.add(sdf.format(ngayLap));
		v.add(soLuong + "");
		v.add(thanhTien + "");
		return v;
	}

	

	@Override
	public String toString() {
		return "DSThongKeNhanVien [maHD=" + maHD + ", maNV=" + maNV + ", ngayLap=" + ngayLap.toString() + ", thanhTien="
				+ df.format(getthanhTien()) + ", soLuong=" + String.valueOf(soLuong) + "]";
	}

}
