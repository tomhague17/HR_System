/**
 * Class representing a Manager, inheriting from employee and implementing Payable
 * Includes methods for presenting manager information as a string, calculating salary (including any bonuses)
 * and logging expenses on their project.
 * @author Thomas Hague
 */

public class Manager extends Employee implements Payable{

    private Project currentProject;
    private double monthlySalary;
    private double yearlySalary;
    private double totalMonthlyBonus;

    /**
     * Creates a Manager using specified parameters, calling the constructor from Employee. Increases employee count
     * by 1 once the Manager is initialised.
     * @param name
     * @param ID
     * @param department
     * @param currentProject
     */
    public Manager(String name, int ID, String department, Project currentProject){
        super(name, ID, department);
        this.currentProject = currentProject;
        Employee.employeeCount++;
        this.totalMonthlyBonus = 0;
    }

    /**
     * Getters for accessing private fields and setters for updating.
     */

    public void setMonthlyBonus(double bonus) {
        totalMonthlyBonus = totalMonthlyBonus + bonus;
    }

    public void setInfo(String name, int ID, String department, Project currentProject) {
        setInfo(name, ID, department);
        this.currentProject = currentProject;
    }

    /**
     * Retrieves the details of the Manager, including their name, ID, department and current project's details.
     * @return A string containing the Manager's details in specified format
     */
    @Override
    public String getDetails() {
        return "Name: " + getName() + ", ID:" + getID() + ", Department: " + getDepartment() +
                ", Current project: " + currentProject.getProjectDetails();
    }

    /**
     * Calculates the monthly base salary of a manager based on the hours worked, before any bonus.
     * @param hoursWorked  in a month by the manager
     * @return the monthly salary in $.
     */
    public double calcMonthlySalary(int hoursWorked) {
        monthlySalary = hoursWorked * 50;
        return monthlySalary;
    }

    /**
     * Calculates the monthly salary of a manager after a bonus has been added to their monthly salary.
     * @param bonus received by the manager
     * @return the monthly salary in $.
     */
    public double addMonthlyBonus(double bonus) {
        setMonthlyBonus(bonus);
        return totalMonthlyBonus + monthlySalary;
    }

    /**
     * Calculates the yearly base salary of a manager based on the hours worked, before any bonus.
     * @return the yearly salary in $.
     */
    public double calculateYearlyPay() {
        yearlySalary = monthlySalary * 12;
        return yearlySalary + totalMonthlyBonus;
    }

    /**
     * Logs an expense for the current project by adding the specified amount to the project's expenses.
     * @param amount the amount of expense to be logged; must be greater than 0 and within the project's budget.
     * @throws IllegalArgumentException if the amount exceeds the project's budget.
     */
    protected void logProjectExpense(double amount) {
        currentProject.addExpense(amount);
    }
}
