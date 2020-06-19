package thirdclass;

import java.io.Serializable;

public class Student {
	
	private int stuAge;
	private int stuNo;
	private String stuName;
	private String stuClass;
	private boolean stuSex;
	private Address address;//这个address类型包含了数据库中的homeaddress和schooladdress,嵌套属性，具体看address类
	private StudentCard studentCard;// 将学生证studentCard类作为Student的成员
	
	public boolean isStuSex() {
		return stuSex;
	}

	public StudentCard getStudentCard() {
		return studentCard;
	}

	public void setStudentCard(StudentCard studentCard) {
		this.studentCard = studentCard;
	}
	
	public void setStuSex(boolean stuSex) {
		this.stuSex = stuSex;
	}

	public Student() {
		
	}
	
	public Student(int stuAge, int stuNo, String stuName, String stuClass) {
		this.stuAge = stuAge;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuClass = stuClass;
		//this.stuSex = stuSex;
	}
	public Student(int stuAge, int stuNo, String stuName, String stuClass,boolean stuSex) {
		this.stuAge = stuAge;
		this.stuNo = stuNo;
		this.stuName = stuName;
		this.stuClass = stuClass;
		this.stuSex = stuSex;
	}
	public int getStuAge() {
		return stuAge;
	}
	public void setStuAge(int stuAge) {
		this.stuAge = stuAge;
	}
	public int getStuNo() {
		return stuNo;
	}
	public void setStuNo(int stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuClass() {
		return stuClass;
	}
	public void setStuClass(String stuClass) {
		this.stuClass = stuClass;
	}
	
	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
//		return this.stuNo+";"+this.stuName+";"+this.stuAge+";"+this.stuClass+"性别："+this.stuSex+"   homeaddress:"+this.address+"\n";
		return this.stuNo+";"+this.stuName+";"+this.stuAge+";"+this.studentCard.getCardId()+";"+this.studentCard.getCardInfo();//一对一关联查询，resultMap方式

	}

}
