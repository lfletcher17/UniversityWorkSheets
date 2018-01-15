
public class Employee {
	
	private String name;
	private double hourlySalary;
	private int numberOfHours;
	
	public Employee(String name, double hourlySalary, int numberOfHours){
		this.name = name;
		this.hourlySalary = hourlySalary;
		this.numberOfHours = numberOfHours;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(double hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public int getNumberOfHours() {
		return numberOfHours;
	}

	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
	public String toString() {
		return name + " has an hourly salary of " + hourlySalary + 
				" £ and has worked last month for " + numberOfHours + " hours."; 
	}
	
	public Double monthlySalary(double taxRate) {
		double grossSalary = hourlySalary * numberOfHours;
		taxRate = taxRate / 100;
		return grossSalary * (1 - taxRate);
	}
	
	public void increaseSalary(double percentage) {;
		percentage = percentage / 100;
		hourlySalary = hourlySalary * (1 + percentage);
	}

}
