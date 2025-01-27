/**
 * Class representing a developer, inheriting from employee and implementing Payable
 * Includes methods for presenting developer information as a string, calculating salary (including any bonuses)
 * and logging expenses on their project.
 * @author Thomas Hague
 */

public class Developer extends Employee implements Payable {

    private int age;
    private String programmingLanguage;
    private Project currentProject;
    private double monthlySalary;
    private double yearlySalary;

    /**
     * Creates a Developer using specified parameters, calling the constructor from Employee. Increases employee count
     * by 1 once the Developer is initialised.
     * @param name
     * @param ID
     * @param age , must be non-negative
     * @param department
     * @param programmingLanguage
     * @param currentProject
     * @throws InvalidInputException if the age is less than 0
     */
    public Developer(String name, int ID, int age, String department, String programmingLanguage, Project currentProject) throws InvalidInputException {
        super(name, ID, department);
        if (age < 0) {
            throw new InvalidInputException("Invalid age entry: " + age);
        }
        this.age = age;
        this.programmingLanguage = programmingLanguage;
        this.currentProject = currentProject;
        Employee.employeeCount++;
    }

    /**
     * Creates a Developer using specified parameters, calling the constructor from Employee. Increases employee count
     * by 1 once the Developer is initialised.
     * Sets default programming lagnauge to Java.
     * @param name
     * @param ID
     * @param age , must be non-negative
     * @param department
     * @param currentProject
     * @throws InvalidInputException if the age is less than 0
     */
    public Developer(String name, int ID, String department, int age, Project currentProject) throws InvalidInputException{
        super(name, ID, department);
        if (age < 0) {
            throw new InvalidInputException("Invalid age entry: " + age);
        }
        this.age = age;
        this.programmingLanguage = "Java";
        this.currentProject = currentProject;
        Employee.employeeCount++;
    }

    /**
     * Getters for accessing private fields and setters for updating.
     */
    public String getProgrammingLanguage () {
        return programmingLanguage;
    }

    public int getAge() {
        return age;
    }

    public void setInfo (String name, int ID, int age, String department, String programmingLanguage, Project currentProject) {
        setInfo(name, ID, department);
        this.age = age;
        this.programmingLanguage = programmingLanguage;
        this.currentProject = currentProject;

    }

    /**
     * Retrieves the details of the developer, including their name, ID, department, age,
     * programming language, and current project's details.
     * @return A string containing the developer's details in specified format
     */
    @Override
    public String getDetails () {
        return "Name: " + getName() + ", ID:" + getID() + ", Department: " + getDepartment() + ", Age: " + getAge() + ", Programming Language: " + getProgrammingLanguage()
                + ", Current project: " + currentProject.getProjectDetails();
    }


    /**
     * Calculates the monthly base salary of a developer based on the hours worked, before any bonus.
     * @param hoursWorked  in a month by the developer
     * @return the monthly salary in $.
     */
    public double calcMonthlySalary(int hoursWorked) {
        monthlySalary = hoursWorked * 30;
        return monthlySalary;
    }

    /**
     * Calculates the monthly salary of a developer after a bonus has been added to their monthly salary.
     * @param bonus received by the developer
     * @return the monthly salary in $.
     */
    public double addMonthlyBonus(double bonus) {
        return monthlySalary + bonus;
    }

    /**
     * Calculates the yearly base salary of a developer based on the hours worked, before any bonus.
     * @return the yearly salary in $.
     */
    public double calculateYearlyPay() {
        yearlySalary = monthlySalary * 12;
        return yearlySalary;
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
