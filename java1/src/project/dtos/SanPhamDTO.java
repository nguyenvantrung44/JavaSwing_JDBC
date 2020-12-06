package project.dtos;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.util.Vector;

public class SanPhamDTO {
	private String maSP, tenSP, maNCC, maDVT, moTa, maQG,tenDVT;
	private double giaGoc, giaBan;
	private int soLuongTon;
	private Timestamp thoiGianKhoiTao;

	public SanPhamDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SanPhamDTO(String maSP) {
		super();
		this.maSP = maSP;
	}

	public SanPhamDTO(String maSP, String tenSP, String maNCC, String maDVT, String moTa, String maQG, double giaGoc,
			double giaBan, int soLuongTon, Timestamp thoiGianKhoiTao) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.maNCC = maNCC;
		this.maDVT = maDVT;
		this.moTa = moTa;
		this.maQG = maQG;
		this.giaGoc = giaGoc;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public SanPhamDTO(String maSP, String tenSP, double giaGoc, double giaBan, int soLuongTon, String maDVT) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaGoc = giaGoc;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
		this.maDVT = maDVT;
	}

	public SanPhamDTO(String maSP, String tenSP, double giaGoc, int soLuongTon) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaGoc = giaGoc;
		this.soLuongTon = soLuongTon;
	}

	public SanPhamDTO(String tenSP, double giaGoc, double giaBan, int soLuongTon, String maDVT) {
		super();
		this.tenSP = tenSP;
		this.giaGoc = giaGoc;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
		this.maDVT = maDVT;
	}

	public SanPhamDTO(String maSP, int soLuongTon) {
		super();
		this.maSP = maSP;
		this.soLuongTon = soLuongTon;
	}

	public String getMaSP() {
		return maSP;
	}

	public void setMaSP(String maSP) {
		this.maSP = maSP;
	}

	public String getTenSP() {
		return tenSP;
	}

	public void setTenSP(String tenSP) {
		this.tenSP = tenSP;
	}

	public String getMaNCC() {
		return maNCC;
	}

	public void setMaNCC(String maNCC) {
		this.maNCC = maNCC;
	}

	public String getMaDVT() {
		return maDVT;
	}

	public void setMaDVT(String maDVT) {
		this.maDVT = maDVT;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public double getGiaGoc() {
		return giaGoc;
	}

	public void setGiaGoc(double giaGoc) {
		this.giaGoc = giaGoc;
	}

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public int getSoLuongTon() {
		return soLuongTon;
	}

	public void setSoLuongTon(int soLuongTon) {
		this.soLuongTon = soLuongTon;
	}

	public Timestamp getThoiGianKhoiTao() {
		return thoiGianKhoiTao;
	}

	public void setThoiGianKhoiTao(Timestamp thoiGianKhoiTao) {
		this.thoiGianKhoiTao = thoiGianKhoiTao;
	}

	public String getMaQG() {
		return maQG;
	}

	public void setMaQG(String maQG) {
		this.maQG = maQG;
	}

	public String getTenDVT() {
		return tenDVT;
	}

	public void setTenDVT(String tenDVT) {
		this.tenDVT = tenDVT;
	}
	public SanPhamDTO(String tenSP, String tenDVT, double giaGoc, double giaBan, int soLuongTon) {
		super();
		this.tenSP = tenSP;
		this.tenDVT = tenDVT;
		this.giaGoc = giaGoc;
		this.giaBan = giaBan;
		this.soLuongTon = soLuongTon;
	}

	DecimalFormat df = new DecimalFormat("#,###.#");

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(tenSP);
		v.add(df.format(giaGoc) + "");
		v.add(df.format(giaBan) + "");
		v.add(soLuongTon + "");
		v.add(maDVT);
		return v;
	}

	public Vector<String> toVector3() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(maSP);
		v.add(tenSP);
		v.add(df.format(giaBan) + "");
		v.add(soLuongTon + "");
		v.add(maDVT);
		return v;
	}

	public Vector<String> toVector2() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(tenSP);
		v.add(soLuongTon + "");
		v.add(giaGoc + "");
		return v;
	}
	public Vector<String> toVector4() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(tenSP);
		v.add(df.format(giaGoc) + "");
		v.add(df.format(giaBan) + "");
		v.add(soLuongTon + "");
		v.add(tenDVT);
		return v;
	}
	@Override
	public String toString() {
		return "SanPhamDTO [tenSP=" + tenSP + ", maNCC=" + maNCC + ", tenDVT=" + tenDVT + ", giaGoc=" + giaGoc
				+ ", giaBan=" + giaBan + ", soLuongTon=" + soLuongTon + "]";
	}
	

}
