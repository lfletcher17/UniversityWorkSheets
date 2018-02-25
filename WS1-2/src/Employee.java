/**
 * A class for an Employee created from an Employee name, their hourlySalary
 * and their numberOfHours.
 * Employee consists of the following field variables:
 * 	name - the name of the Employee
 *	hourlySalary - the hourly salary of the Employee
 *	numberOfHours- the number of hours an Employee works 
 * @author lxf736
 * @version 2017-10-17
 *
 */

public class Employee {
	
	private String name;
	private double hourlySalary;
	private int numberOfHours;
	
	/**
	 * @param name is the name as String
	 * @param hourlySalary is the hourlySalary as double
	 * @param numberOfHours is the numberOfHours as int
	 */
	public Employee(String name, double hourlySalary, int numberOfHours){
		this.name = name;
		this.hourlySalary = hourlySalary;
		this.numberOfHours = numberOfHours;
	}

	/** 
     *  @return the name of the Employee as String
     */
	public String getName() {
		return name;
	}

	/**
     *  sets the name of Employee
     *  @param  name for the changed name
     */
	public void setName(String name) {
		this.name = name;
	}

	/** 
     *  @return the hourlySalary of the Employee as double
     */
	public double getHourlySalary() {
		return hourlySalary;
	}

	/**
     *  sets the hourlySalary of Employee
     *  @param  hourlySalary for the changed hourlySalary
     */
	public void setHourlySalary(double hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	/** 
     *  @return the numberOfHours of the Employee as int
     */
	public int getNumberOfHours() {
		return numberOfHours;
	}

	/**
     *  sets the numberOfHours of Employee
     *  @param  numberOfHours for the changed numberOfHours
     */
	public void setNumberOfHours(int numberOfHours) {
		this.numberOfHours = numberOfHours;
	}
	
    /** 
     *  toString defines how to print an Employee
     *  @return  the print type of an Employee
     */
	public String toString() {
		return name + " has an hourly salary of " + hourlySalary + 
				" \u00a3 and has worked last month for " + numberOfHours + " hours."; 
	}
	
    /**
     *  Method to return the monthlySalary of Employee.
     *  @param taxRate is the rate of tax applied to an Employee salary.
     *  @return The monthlySalary of an Employee after tax
     */
	public Double monthlySalary(double taxRate) {
		double grossSalary = hourlySalary * numberOfHours;
		taxRate = taxRate / 100;
		return grossSalary * (1 - taxRate);
	}
	
    /**
     *  Method to increase the hourlySalary of an Employee.
     *  @param percentage is the percentage by which an Employee salary is to increase.
     */
	public void increaseSalary(double percentage) {;
		percentage = percentage / 100;
		hourlySalary = hourlySalary * (1 + percentage);
	}

}