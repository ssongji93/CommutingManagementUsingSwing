package test;

public class EmpDTO {

	private String empNo;
	private String name;
	private String empPos;
	private String id;
	private String pwd;
	private String wkDay;
	private String wkStart;
	private String wkEnd;
	private String pay;

	// 이클립스팁 : Getter/Setter 만들기
	// 우클릭 -> source->Generate Getters And Setters-> [Select AlL] -> [OK]

	public String getEmpNo() {
		return empNo;
	}

	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmpPos() {
		return empPos;
	}

	public void setEmpPos(String empPos) {
		this.empPos = empPos;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}


	public String getWkDay() {
		return wkDay;
	}

	public void setWkDay(String wkDay) {
		this.wkDay = wkDay;
	}

	public String getWkStart() {
		return wkStart;
	}

	public void setWkStart(String wkStart) {
		this.wkStart = wkStart;
	}

	public String getWkEnd() {
		return wkEnd;
	}

	public void setWkEnd(String wkEnd) {
		this.wkEnd = wkEnd;
	}

	public String getPay() {
		return pay;
	}

	public void setPay(String pay) {
		this.pay = pay;
	}

	// DTO 객체 확인
	// 이클립스팁 : toString() 자동생성: 우클릭 -> source -> Generate toString->[OK]
	@Override
	public String toString() {
		return "EmpDTO [empNo=" + empNo + ", namel=" + name + ", empPos=" + empPos + ", id=" + id + ", pwd=" + pwd
				+ "]";
	}

}
