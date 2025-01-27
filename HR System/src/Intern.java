/**
 * Class representing an Intern, inheriting from employee.
 * Includes method for presenting intern information as a string.
 * @author Thomas Hague
 */

public class Intern extends Employee {

    // fields
    private String school;
    private int duration;

    public Intern(String name, int ID, String department, String school, int duration) throws InvalidInputException {
        super(name, ID, department);
        this.school = school;
        if (duration < 0) {
            throw new InvalidInputException("Invalid duration entry: " + duration);
        }
        this.duration = duration;
        Employee.employeeCount++;

    }

    /**
     * Creates a Intern using specified parameters, calling the constructor from Employee. Increases employee count
     * by 1 once the intern is initialised.
     * @param name
     * @param ID
     * @param department
     */
    public Intern(String name, int ID, String department) {
        super(name, ID, department);
        this.school = "Unknown";
        this.duration = 3;
        Employee.employeeCount++;
    }

    /**
     * Getters for accessing private fields and setters for updating.
     */
    public String getSchool() {
        return school;
    }

    public int getDuration() {
        return duration;
    }

    /**
     * Retrieves the details of the intern, including their name, ID, department, school and duration.
     * @return A string containing the intern's details in specified format
     */
    @Override
    public String getDetails() {
        return "Name: " + getName() + ", ID:" + getID() + ", Department: " + getDepartment() + ", School: " + getSchool() + ", Duration: " + getDuration();
    }

}
