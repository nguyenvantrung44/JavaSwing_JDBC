package project.dtos;

import java.text.DecimalFormat;
import java.util.Vector;

public class ThongKeDoanhThuDTO {
	private String maSP, tenSP;
	private double giaBan, giaNhap, tienBanDuoc;
	private int  soluongban,soluonghd;

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

	public double getGiaBan() {
		return giaBan;
	}

	public void setGiaBan(double giaBan) {
		this.giaBan = giaBan;
	}

	public double getGiaNhap() {
		return giaNhap;
	}

	public void setGiaNhap(double giaNhap) {
		this.giaNhap = giaNhap;
	}

	public double getTienBanDuoc() {
		return tienBanDuoc;
	}

	public void setTienBanDuoc(double tienBanDuoc) {
		this.tienBanDuoc = tienBanDuoc;
	}

	public int getSoluongban() {
		return soluongban;
	}

	public void setSoluongban(int soluongban) {
		this.soluongban = soluongban;
	}
	
	public int getSoluonghd() {
		return soluonghd;
	}

	public void setSoluonghd(int soluonghd) {
		this.soluonghd = soluonghd;
	}

	public ThongKeDoanhThuDTO(String maSP, String tenSP, double giaBan, double giaNhap, 
			double tienBanDuoc, int soluongban,int soluonghd) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.giaBan = giaBan;
		this.giaNhap = giaNhap;
		this.tienBanDuoc = tienBanDuoc;
		this.soluongban = soluongban;
		this.soluonghd=soluonghd;
	}

	public ThongKeDoanhThuDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "ThongKeDoanhThuDTO [maSP=" + maSP + ", tenSP=" + tenSP + ", giaBan=" + giaBan + ", giaNhap=" + giaNhap
				+ ", tienBanDuoc=" + tienBanDuoc + ", soluongban=" + soluongban + ", soluonghd=" + soluonghd + ", df="
				+ df + "]";
	}


	DecimalFormat df = new DecimalFormat("#,###.#");
	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(maSP);
		v.add(tenSP);
		v.add(df.format(giaBan));
		v.add(df.format(giaNhap));
		v.add(soluongban + "");
		v.add(df.format(tienBanDuoc));
		return v;
	}

}
