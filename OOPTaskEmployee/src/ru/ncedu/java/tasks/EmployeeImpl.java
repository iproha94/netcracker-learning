package ru.ncedu.java.tasks;

public class EmployeeImpl implements Employee {
	
	private int salary;
	private String firstName;
	private String lastName;
	private Employee manager;
	
	public EmployeeImpl(){
		salary = 1000;
		firstName = null;
		lastName = null;
		manager = null;
	}
	
	@Override
	public int getSalary() {
		// TODO Auto-generated method stub
		return salary;
	}

	@Override
	public void increaseSalary(int value) {
		// TODO Auto-generated method stub
		salary += value;
	}

	@Override
	public String getFirstName() {
		// TODO Auto-generated method stub
		return firstName;
	}

	@Override
	public void setFirstName(String firstName) {
		// TODO Auto-generated method stub
		this.firstName = firstName;
	}

	@Override
	public String getLastName() {
		// TODO Auto-generated method stub
		return lastName;
	}

	@Override
	public void setLastName(String lastName) {
		// TODO Auto-generated method stub
		this.lastName = lastName;
	}

	@Override
	public String getFullName() {
		// TODO Auto-generated method stub
		return firstName + " " + lastName;
	}

	@Override
	public void setManager(Employee manager) {
		// TODO Auto-generated method stub
		this.manager = manager;
	}

	@Override
	public String getManagerName() {
		// TODO Auto-generated method stub
		if (manager == null) {
			return "No manager";
		} else {
			return manager.getFullName();
		}
	}

	@Override
	public Employee getTopManager() {
		// TODO Auto-generated method stub
		if (this.manager == null) {
			return this;
		} else {
			return this.manager.getTopManager();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

}
