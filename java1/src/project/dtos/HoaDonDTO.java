package project.dtos;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class HoaDonDTO {
	private String maHD;
	private String noiNhanHang, maNV, maKH, tenKH, tenNV;
	private Timestamp ngayLapHD, ngayGiaoHang;
	private double tongTien;

	public HoaDonDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HoaDonDTO(String maHD, Timestamp ngayLapHD, double tongTien, String tenKH, String tenNV) {
		super();
		this.maHD = maHD;
		this.tenKH = tenKH;
		this.tenNV = tenNV;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
	}

	public HoaDonDTO(String maHD, String tenKH, Timestamp ngayLapHD, String tenNV, double tongTien) {
		super();
		this.maHD = maHD;
		this.tenKH = tenKH;
		this.tenNV = tenNV;
		this.ngayLapHD = ngayLapHD;
		this.tongTien = tongTien;
	}

	public HoaDonDTO(String maHD, String noiNhanHang, String maNV, String maKH, Timestamp ngayLapHD,
			Timestamp ngayGiaoHang) {
		super();
		this.maHD = maHD;
		this.noiNhanHang = noiNhanHang;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHD = ngayLapHD;
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public HoaDonDTO(String maHD) {
		super();
		this.maHD = maHD;
	}

	public HoaDonDTO(String maHD, String maNV, String maKH, Timestamp ngayGiaoHang, double tongTien) {
		super();
		this.maHD = maHD;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayGiaoHang = ngayGiaoHang;
		this.tongTien = tongTien;
	}

	public HoaDonDTO(String noiNhanHang, String maNV, String maKH, Timestamp ngayLapHD, Timestamp ngayGiaoHang) {
		super();
		this.noiNhanHang = noiNhanHang;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHD = ngayLapHD;
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public HoaDonDTO(String maHD, String noiNhanHang, String maNV, String maKH, Timestamp ngayLapHD,
			Timestamp ngayGiaoHang, Double tongTien) {
		super();
		this.maHD = maHD;
		this.noiNhanHang = noiNhanHang;
		this.maNV = maNV;
		this.maKH = maKH;
		this.ngayLapHD = ngayLapHD;
		this.ngayGiaoHang = ngayGiaoHang;
		this.tongTien = tongTien;
	}

	public HoaDonDTO(String maHD, double tongTien) {
		super();
		this.maHD = maHD;
		this.tongTien = tongTien;
	}

	public HoaDonDTO(String maHD, String tenKH, String tenNV, Timestamp ngayLapHD) {
		super();
		this.maHD = maHD;
		this.tenKH = tenKH;
		this.tenNV = tenNV;
		this.ngayLapHD = ngayLapHD;
	}

	public String getTenKH() {
		return tenKH;
	}

	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getNoiNhanHang() {
		return noiNhanHang;
	}

	public void setNoiNhanHang(String noiNhanHang) {
		this.noiNhanHang = noiNhanHang;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaKH() {
		return maKH;
	}

	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}

	public Timestamp getNgayLapHD() {
		return ngayLapHD;
	}

	public void setNgayLapHD(Timestamp ngayLapHD) {
		this.ngayLapHD = ngayLapHD;
	}

	public Timestamp getNgayGiaoHang() {
		return ngayGiaoHang;
	}

	public void setNgayGiaoHang(Timestamp ngayGiaoHang) {
		this.ngayGiaoHang = ngayGiaoHang;
	}

	public double getTongTien() {
		return tongTien;
	}

	public void setTongTien(double tongTien) {
		this.tongTien = tongTien;
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<String>();
		v.add("");
		v.add(maHD);
		v.add(maNV);
		v.add(maKH);
		v.add(ngayGiaoHang + "");
		return v;
	}

	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
	DecimalFormat df = new DecimalFormat("#,###.#");

	public Vector<String> toVector1() {
		Vector<String> v = new Vector<String>();
		v.add("");
		v.add(maHD);
		v.add(sdf.format(ngayLapHD));
		v.add(df.format(tongTien));
		v.add(tenKH);
		v.add(tenNV);
		return v;
	}

	public Vector<String> toVector2() {
		Vector<String> v = new Vector<String>();
		v.add(maHD);
		v.add(tenKH);
		v.add(tenNV);
		DateFormat dt = new SimpleDateFormat("dd-MM-yyyy");
		v.add(dt.format(ngayLapHD) + "");
		return v;
	}

}
