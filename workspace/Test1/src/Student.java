
public class Student  extends Person{
	
	String classNo;
	
	public String getClassNo() {
		return classNo;
	}

	public void setClassNo(String classNo) {
		this.classNo = classNo;
	}

	public void doSomething()
	{
		System.out.print(name);

		System.out.print(age);

		System.out.print(classNo);
		
	}
	

}
