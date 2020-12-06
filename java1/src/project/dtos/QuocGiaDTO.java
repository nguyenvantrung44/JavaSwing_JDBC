package project.dtos;

import java.util.Vector;

public class QuocGiaDTO {
	private String maQG, tenQG;

	public String getMaQG() {
		return maQG;
	}

	public void setMaQG(String maQG) {
		this.maQG = maQG;
	}

	public String getTenQG() {
		return tenQG;
	}

	public void setTenQG(String tenQG) {
		this.tenQG = tenQG;
	}

	public QuocGiaDTO(String maQG, String tenQG) {
		super();
		this.maQG = maQG;
		this.tenQG = tenQG;
	}

	public QuocGiaDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "QuocGia_DTO [maQG=" + maQG + ", tenQG=" + tenQG + "]";
	}

	public Vector<String> toVector() {
		Vector<String> v = new Vector<>();
		v.add(maQG);
		v.add(tenQG);
		return v;
	}
}
