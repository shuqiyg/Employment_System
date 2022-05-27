
import java.util.Calendar;

enum EmpType{
	DailyWages,
	Permanent,
	Commision
}
enum StatusEmp{
	Active,
	Onleave,
	Terminated
}
enum MarStatus{
	Single,
	Married,
	Divorced
}
public class Employee {
	int empID = 0;
	double bSalary = 0, yearlyAllw = 0;
	String Name = "", Address= "";
	Calendar DOB, joinDate;
	EmpType empType;
	StatusEmp sEmp;
	MarStatus mStatus;
	Employee(){};
	
	Employee(int empID, String name, double salary, double allw , String addr, Calendar joinDate,Calendar DOB, EmpType et, StatusEmp sEmp, MarStatus mStatus){
		this.Name = name;
		this.bSalary = salary;
		this.Address = addr;
		this.joinDate = joinDate;
		this.DOB = DOB;
		this.empType = et;
		this.sEmp = sEmp;
		this.mStatus = mStatus;
		this.empID = empID;
		this.yearlyAllw = allw;
	}
	
	public double calculateSalary(double bSalary) {
		
		return bSalary * 365;
	}
	
    public double calculateSalary(double bSalary, double allowance) {
		
		return bSalary*365 + allowance;
	}
    
    public double calculateSalary(double bSalary, Calendar joinDate) {
    	double years = (((double)(Calendar.getInstance().getTimeInMillis() - joinDate.getTimeInMillis()))/1000/60/60/24/365);
    	double commRate = 0;
		if(years < 5) {
			commRate = .1;
		}else if(5 <= years && years <= 10) {
			commRate = .25;
		}else {
			commRate = .25 + (int)((years - 10)/2) * .05;
		}
		return (bSalary *= (1+commRate))* 365;
	}
	
	public void setStatus(StatusEmp sEmp) {
		this.sEmp = sEmp;
	}
	
	public void setBaseSalary(double s) {
		this.bSalary = s;
	}
	
	public void setEmpID(int ID) {
		this.empID = ID;
	}
	
	public void setEmpType(EmpType empType) {
		this.empType = empType;
	}
	
	public void setAddr(String addr) {
		this.Address = addr;
	}
	
	public void setDOB(Calendar Calendar) {
		this.DOB = Calendar;
	}
	
	public void setMStatus(MarStatus mStat) {
		this.mStatus = mStat;
	}
	
	public void setjoinDate(Calendar Calendar) {
		this.joinDate = Calendar;
	}
	
	
	public StatusEmp getEmpStatus() {
		return sEmp;
	} 
	
	public EmpType getEmpType() {
		return empType;
	}
	
	public String getAddr() {
		return Address;
	}
	
	public Calendar getDOB() {
		return DOB;
	}
	
	public MarStatus getMStatus() {
		return mStatus;
	}
	
	public Calendar getjoinDate() {
		return joinDate;
	}
	
	public void print() {
		System.out.println("*********Employee Information***********");
		System.out.println("ID: " + empID);
		System.out.println("Name: " + Name);
		System.out.println("Address: " + Address);
		System.out.println("DOB: " + getDOB().get(Calendar.YEAR) + "/" + DOB.get(Calendar.MONTH) + "/" + DOB.get(Calendar.DAY_OF_MONTH));
		System.out.println("Martial Status: " + getMStatus().toString());
		System.out.println("Employmnet Status: " + getEmpStatus().toString());
		System.out.println("Employment Type: " + getEmpType().toString());
		System.out.println("Joing date: " + getjoinDate().get(Calendar.YEAR) + "/" + DOB.get(Calendar.MONTH) + "/" + DOB.get(Calendar.DAY_OF_MONTH));
		System.out.println("Basic Salary: ");
		if(sEmp == StatusEmp.Active) {
			if(getEmpType() == EmpType.DailyWages) {
				System.out.println(calculateSalary(bSalary));
			}else if(getEmpType() == EmpType.Permanent) {
				System.out.printf("%.2f\n",calculateSalary(bSalary, yearlyAllw ));
			}else {
				System.out.printf("%.2f\n", calculateSalary(bSalary, joinDate));
			}
		}else if(sEmp == StatusEmp.Onleave) {
			System.out.println("Employee " + empID + " is on leave, no salary available.");
		}else {
			System.out.println("Employee " + empID + " has been terminated, no salary available.");
		}
		System.out.println("*****************************************\n");
	}
}
