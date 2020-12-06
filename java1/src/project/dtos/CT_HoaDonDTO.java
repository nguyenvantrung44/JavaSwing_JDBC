package project.dtos;

import java.text.DecimalFormat;
import java.util.Vector;

public class CT_HoaDonDTO {
	private String maHD, maSP, tenSP, maDVT, tenDVT;
	private double donGia;
	private int soLuong, soLuong2;
	private double gia, tongTien;

	public CT_HoaDonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaDVT() {
		return maDVT;
	}

	public void setMaDVT(String maDVT) {
		this.maDVT = maDVT;
	}

	public double getDonGia() {
		return donGia;
	}

	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}

	public int getSoLuong() {
		return soLuong;
	}

	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}

	public double getGia() {
		return gia;
	}

	public void setGia(double gia) {
		this.gia = gia;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public int getSoLuong2() {
		return soLuong2;
	}

	public void setSoLuong2(int soLuong2) {
		this.soLuong2 = soLuong2;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public String getTenDVT() {
		return tenDVT;
	}

	public void setTenDVT(String tenDVT) {
		this.tenDVT = tenDVT;
	}

	public CT_HoaDonDTO(String maHD, int soLuong2, double tongTien) {
		super();
		this.maHD = maHD;
		this.soLuong2 = soLuong2;
		this.tongTien = tongTien;
	}

	public CT_HoaDonDTO(String maHD, String maSP, int soLuong, double donGia) {
		super();
		this.maHD = maHD;
		this.maSP = maSP;
		this.donGia = donGia;
		this.soLuong = soLuong;
	}

	public CT_HoaDonDTO(String tenSP, String tenDVT, double donGia, int soLuong, double gia) {
		super();
		this.tenSP = tenSP;
		this.tenDVT = tenDVT;
		this.donGia = donGia;
		this.soLuong = soLuong;
		this.gia = gia;
	}

	@Override
	public String toString() {
		return "CT_HoaDonDTO [maHD=" + maHD + ", soLuong2=" + soLuong2 + "]";
	}

	DecimalFormat df = new DecimalFormat("#,###.0");

	public Vector<String> toVector() {
		Vector<String> v = new Vector<String>();
		v.add("");
		v.add(tenSP);
		v.add(tenDVT);
		v.add(df.format(donGia));
		v.add(soLuong + "");
		v.add(df.format(donGia * soLuong));
		return v;
	}

}
