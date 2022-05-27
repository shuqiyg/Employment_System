import java.util.*;


public class CompanyHR { // to manipulate the employees objects inside this class
	static int empSize = 0; // increase by 1 when an employee is added to the company
	Employee[] employees = {}; // contains all the employees objects
	
	public void initiateMenu() {
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome to HR System, Please Choose one of the following options ");
		boolean exit = false;
		while(!exit) {
			System.out.println("\n1. Add New Employee\n2. Update current Employee\n3. Show Employee Infos\n4. Exit");		
			int option = input.nextInt();
			switch(option) {
			case 1:
				addNewEmp(); 
				break;
			case 2:
				System.out.println("Please Enter Emp ID: ");
				int id = input.nextInt();
				Employee empTemp = searchByID(id);
				
				boolean exitUpdate = false;
				while(!exitUpdate) {
					System.out.println("Please Choose one of the following:\n1. Update Address\n2. Update Status\n3. Update Employment Type\n4. Update Basic Salary(Daily)\n5. Exit");
					int updateOpt = input.nextInt();
					switch(updateOpt){
					case 1:
						chgAddr(empTemp);
						break;
					case 2:
						chgEmpStatus(empTemp);
						break;
					case 3:
						chgEmpType(empTemp);
						break;
					case 4:
						chgBasicSal(empTemp);
						break;
					case 5:
						 System.out.println("Exiting Employee Info Update...");
						 exitUpdate = true;
						 break;
					default:
						System.out.println("Invalid Entry, Please Try again....\n");	
					}
				}				
				break;
			case 3:
				boolean e = false;
				while(!e) {
					System.out.println("Please choose one of the following options: \n1. Show All Employees\n2. Search By ID\n3. Search By Name\n4. Search By Status\n5. Quit");
					Scanner input1 = new Scanner(System.in);
					int option1 = input.nextInt();
					switch(option1) {
					case 1:
						if(employees != null) {
							showAllEmp();
						}else {
							System.out.println("Empty Employee List\n");
						}
						
						break;
					case 2:
						System.out.println("Please Enter ID number: ");
						int id1 = input.nextInt();
						Employee emp = searchByID(id1);
						if(emp.empID == 0) {
							System.out.println("Employee " + id1 + " doesn't not exist.\n");
						}else {
							emp.print();
						}
						
						break;
					case 3:
						System.out.println("Please Enter the Name: ");
						String name = input.next();
						Employee[] temp = searchByName(name);
						for(Employee sEmp : temp) {
							if(sEmp != null) {
							 sEmp.print();
							 break;
							}else {
								System.out.println("Name doesn't exist....\n");
								break;
							}
						}
						break;
					case 4:
						System.out.println("PLease CHOOSE one of the Status Number: 1.Active 2.Onleave 3.Terminated)");
						int statOption = input.nextInt();
						Employee[] empStats;
						if(statOption == 1) {
							empStats = searchByStatus(StatusEmp.Active);
						}else if(statOption == 2) {
							empStats = searchByStatus(StatusEmp.Onleave);
						}else if(statOption == 3) {
							empStats = searchByStatus(StatusEmp.Terminated);
						}else {
							System.out.println("Invalid Status....\n");
							break;
						}
						if(empStats[0] == null) {
							System.out.println("No employee exists with this Status.....\n");
							//break;
						}else {
							for(Employee e2: empStats) {	
								if(e2 == null) {							
									break;
								}
								e2.print();
							}							
						}					
						break;					
					case 5:
						e = true;
						break;
					default:
						System.out.println("Invalid Entry, Please Try again.....\n");
					}
				}
				break;
			case 4:
				System.out.println("Good Bye!");
				exit = true;
				break;
			default:
				System.out.println("Invalid Entry, Please try again....\n");
			}
			
		}	
		input.close();
	}
	
	
	public void chgEmpType(Employee emp) {
		System.out.println("Please Enter Choose a NEW Employment Type: 1. DailyWages 2. Permanent 3.Commision)");
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		switch(option){
			case 1:
				emp.setEmpType(EmpType.DailyWages); 
				break;
			case 2:
				emp.setEmpType(EmpType.Permanent); 
				break;
			case 3: 
				emp.setEmpType(EmpType.Commision); 
				break;
			default:
				System.out.println("Invalid Option, please Try again.");
		};
	}
	public void chgEmpStatus(Employee emp) {
		System.out.println("Please Choose one of the Status: (1.Active 2.Onleave 3.Terminated)");
		Scanner input = new Scanner(System.in);
		int option = input.nextInt();
		switch(option){
			case 1:
				emp.setStatus(StatusEmp.Active); 
				break;
			case 2:
				emp.setStatus(StatusEmp.Onleave); 
				break;
			case 3: 
				emp.setStatus(StatusEmp.Terminated); 
				break;
			default:
				System.out.println("Invalid Option, please Try again.");
		};
	}
	public void chgBasicSal(Employee emp) {
		System.out.println("Please Enter New Basic Salary: ");
		Scanner input = new Scanner(System.in);
		emp.setBaseSalary(input.nextDouble());
		
	}
	public void chgAddr(Employee emp) {
		System.out.println("Please Enter New Address: ");
		Scanner input = new Scanner(System.in);
		emp.setAddr(input.nextLine());
	}
	
	public void showAllEmp() {
		if(empSize == 0) {
			System.out.println("\nNo Employee available....\n");
		}
		for(Employee emp: employees) {
			emp.print();
		}
	}
	public void showEmp(int empID) {
		searchByID(empID).print();
	}
	
	public int addNewEmp() {
		Employee newEmp = null;
		System.out.println("Please Enter Name of the New Employee: ");
		Scanner input1 = new Scanner(System.in);
		String name = input1.next();
		System.out.println("Please Enter Base Salary(Daily) of the New Employee: ");
		double salary = input1.nextDouble();
		System.out.println("Please Enter Address of the New Employee: ");
		input1.nextLine();
		String addr = input1.nextLine();
		System.out.println("Please Enter Join Date of the New Employee as following(YYYY/MM/DD): ");
		System.out.println("YEAR: ");
		int year = input1.nextInt();
		System.out.println("MONTH: ");
		int month = input1.nextInt() - 1;
		System.out.println("DAY: ");
		int day = input1.nextInt();
		Calendar joinDate = Calendar.getInstance();
		joinDate.set(year,month, day);
		System.out.println("Please Enter Date of Birth of the New Employee as following(YYYY/MM/DD): ");
		System.out.println("YEAR: ");
		int year_dob = input1.nextInt();
		System.out.println("MONTH: ");
		int month_dob = input1.nextInt() - 1;
		System.out.println("DAY: ");
		int day_dob = input1.nextInt();
		Calendar DOB = Calendar.getInstance();
		DOB.set(year_dob,month_dob, day_dob);
		System.out.println("Please Enter One of the Employee Type: (1.DailyWages 2.Permanent 3.Commision) ");
		String empType = input1.next();
		double allowance = 0;
		if(empType.equals(EmpType.Permanent.name())){
			System.out.println("Please Enter Yearly Allowance: ");
			allowance = input1.nextDouble();
		}
		System.out.println("Please Enter Employee Status: (1.Active 2.Onleave 3.Terminated)");
		String empStat = input1.next();
		System.out.println("Please Enter Employee Martial Status: (1.Single 2.Married 3.Divorced)");
		String marStat = input1.next();
		int empID = genNewID();
		newEmp = new Employee(empID, name, salary, allowance, addr, joinDate, DOB, EmpType.valueOf(empType), StatusEmp.valueOf(empStat), MarStatus.valueOf(marStat));
		employees = Arrays.copyOf(employees, empSize + 1);
		employees[employees.length-1] = newEmp;
		empSize++;
		System.out.println("\nNew Employee No." + empID + " has been created\n");
		//input1.close();
		return empID;
	}
	
	public Employee[] searchByName(String name) { //search by a specific criteria
		Employee[] empResults = new Employee[empSize];
		int i = 0;
		for(Employee emp: employees) {
			if(emp.Name.equals(name)) {
				empResults[i] = emp;
				i++;
			}
		}
		return empResults;
	}
	
	public Employee[] searchByStatus(StatusEmp sEmp) {
		Employee[] empResults = new Employee[empSize];
		int i = 0;
		for(Employee emp: employees) {
			if(emp.sEmp == sEmp) {
				empResults[i] = emp;
				i++;
			}
		}
		return empResults;
	}
	
	public Employee searchByID(int empID) {
		Employee empTemp = new Employee();
		for(Employee emp : employees) {
			if(emp.empID == empID) {
				empTemp = emp;
			}
		}
		return empTemp;
	}
	
	public int genNewID() { // when a new employee is created, a new ID will be attached to him/her
		if(empSize == 0) {
			return 1;
		}
		return employees[empSize -1].empID + 1;
	}
}
