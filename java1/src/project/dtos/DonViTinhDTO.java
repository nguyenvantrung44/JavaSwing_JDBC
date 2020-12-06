package project.dtos;

import java.util.Vector;

public class DonViTinhDTO {
	private String maDVT, tenDVT;

	public DonViTinhDTO() {
	}

	public DonViTinhDTO(String maDVT, String tenDVT) {
		super();
		this.maDVT = maDVT;
		this.tenDVT = tenDVT;
	}

	public String getMaDVT() {
		return maDVT;
	}

	public void setMaDVT(String maDVT) {
		this.maDVT = maDVT;
	}

	public String getTenDVT() {
		return tenDVT;
	}

	public void setTenDVT(String tenDVT) {
		this.tenDVT = tenDVT;
	}

	@Override
	public String toString() {
		return "DonViTinhDTO [maDVT=" + maDVT + ", tenDVT=" + tenDVT + "]";
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add(maDVT);
		v.add(tenDVT);
		return v;
	}

}
