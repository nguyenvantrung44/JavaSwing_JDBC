package project.dtos;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Vector;

public class ThongKeTinhTrangLK {
	private String maSP;
	private String tenSP;
	private int soLuongBan;
	private Timestamp ngayLap;

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
	public int getSoLuongBan() {
		return soLuongBan;
	}
	public void setSoLuongBan(int soLuongBan) {
		this.soLuongBan = soLuongBan;
	}
	public Timestamp getNgayLap() {
		return ngayLap;
	}
	public void setNgayLap(Timestamp ngayLap) {
		this.ngayLap = ngayLap;
	}
	
	public ThongKeTinhTrangLK(String maSP, String tenSP, int soLuongBan, Timestamp ngayLap) {
		super();
		this.maSP = maSP;
		this.tenSP = tenSP;
		this.soLuongBan = soLuongBan;
		this.ngayLap = ngayLap;
	}
	public ThongKeTinhTrangLK() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "ThongKeTinhTrangLK [maSP=" + maSP + ", tenSP=" + tenSP + ", soLuongBan=" + soLuongBan + ", ngayLap="
				+ ngayLap + ", sdf=" + sdf + "]";
	}

	SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add("");
		v.add(maSP);
		v.add(tenSP);
		v.add(soLuongBan + "");
		v.add(sdf.format(ngayLap));
		return v;
	}
}
