import java.io.*;
import java.util.*;

/**
 * Abstract class representing an employee.
 * Includes methods for comparing two employees, counting, searching for and sorting employees, saving
 * their details to a file, loading details from a file, recording, storing and retrieving
 * attendances, booking time off and filtering based on a performance score or department.
 * @author Thomas Hague
 */

public abstract class Employee {

    private String name;
    private int ID;
    private String department;

    public static int employeeCount;

    public static ArrayList<Employee> employees = new ArrayList<>();
    private static HashMap<Employee, Integer> EmployeeHM = new HashMap<>();
    private HashMap<String, Boolean> attendance = new HashMap<>();
    private ArrayList<Integer> perfScores = new ArrayList<>();
    private ArrayList<String[]> timeOffRequests = new ArrayList<>();

    /**
     * Creates an Employeee with specified parameters.
     * @param name
     * @param ID
     * @param department
     */
    public Employee(String name, int ID, String department) {
        this.name = name;
        this.ID = ID;
        this.department = department;
    }

    /**
     * Getters for accessing private fields and setters for updating.
     */

    public String getName() {
        return name;
    }

    public int getID() {
        return ID;
    }

    public String getDepartment() {
        return department;
    }

    public void setInfo(String name, int ID, String department) {
        this.name = name;
        this.ID = ID;
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    /**
     * Overrides the existing object class equals and hashCode methods to determine if
     * two employees are the same if they have the same name and ID.
     * @param obj , the object to be compared.
     * @return true if they are equal, false if not.
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Employee) {
            Employee e = (Employee) obj;
            return this.getName().equals(e.getName()) && this.getID() == e.getID();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getID();
        return result;
    }

    /**
     * Abstract method
     * @return employee information as a string
     */
    protected abstract String getDetails();

    /**
     * Returns the type of the employee.
     * @return a string representing the type of the employee.
     */
    protected String getType() {
        return "Employee";
    }

    /**
     * Saves an array of employees to a file named "employees.txt", by calling the getDetails
     * method to display their information.
     * If the array is empty, no file is created and a message is printed to the console.
     * File not found errors are handled.
     * @param Employees , an array of Employees.
     */
    protected static void saveToFile(Employee[] Employees) {
        if (Employees.length != 0) {
            try (PrintWriter saveDetails = new PrintWriter(new FileWriter("employees.txt"))) {
                System.out.println("Save File printed");
                for (Employee employee : Employees) {
                    if (employee != null) {
                        saveDetails.println(employee.getDetails());
                    }
                    saveDetails.flush();
                }
            } catch (IOException e) {
                System.out.println("File not found");
            }
        } else {
            System.out.println("Employees list is empty.");
        }
    }

    /**
     * Adds an array of Employee objects to an ArrayList.
     * @param Employees an array of Employee objects to be added to the list.
     * @return the updated ArrayList containing the Employee objects.
     */
    protected static ArrayList<Employee> addArrayList(Employee[] Employees) {
        for (Employee e : Employees) {
            employees.add(e);
        }
        return employees;
    }

    /**
     * Sorts a list of employees in ascending order using their IDs.
     * @param employees the list of Employee objects to be sorted
     */
    protected static void sortID(ArrayList<Employee> employees) {
        Collections.sort(employees, Comparator.comparingInt(Employee::getID));
    }

    /**
     * Sorts a list of employees in ascending order using their names.
     * @param employees the list of Employee objects to be sorted
     */
    protected static void sortName(ArrayList<Employee> employees) {
        Collections.sort(employees, Comparator.comparing(Employee::getName));
    }

    /**
     * Loads employee details from a file named "employees.txt".
     * Utilizes a Scanner for file reading and handles exceptions related to file access or file not found.
     */
    protected static void loadFromFile() {
        try (Scanner loadDetails = new Scanner(new FileReader("employees.txt"))) {
            while (loadDetails.hasNextLine()) {
                System.out.println(loadDetails.nextLine());
            }
            System.out.println("File printed");

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Records the attendance of an employee for a specific date.
     * If attendance for the given date is already recorded, it prints a message indicating this.
     * Otherwise, it adds the attendance for the date.
     * @param date       the date for which the attendance is being recorded
     * @param isPresent  a boolean, meaning true if the employee was present or false if they were absent.
     */
    protected void recordAttendance(String date, boolean isPresent) {
        if (attendance.containsKey(date)) {
            System.out.println("Attendance already recorded for this date");
        } else {
            attendance.put(date, isPresent);
            System.out.println("Attendance is recorded for " + date + " is " + isPresent);
        }
    }

    /**
     * Returns a string indicating whether the employee is "Present" or "Absent".
     * @param isPresent a boolean indicating the attendance, True if the employee is present, false otherwise.
     * @return a string of the employees' attendance status.
     */
    private String formatAttendanceStatus(boolean isPresent) {
        return isPresent ? "Present" : "Absent";
    }

    /**
     * Displays the attendance summary for the specified employee, showing the attendance
     * status for each recorded date. If no attendance has been recorded, a message
     * is printed indicating there is no attendance data.
     * @param e the employee whose attendance will be displayed.
     */
    protected void getAttendanceSummary(Employee e) {
        System.out.println("Attendance for " + e.getName() + ": ");
        if (!attendance.isEmpty()) {
            for (Map.Entry<String, Boolean> entry : attendance.entrySet()) {
                String date = entry.getKey();
                boolean isPresent = entry.getValue();
                System.out.println(date + ": " + formatAttendanceStatus(isPresent));
            }
        } else {
            System.out.println("No attendance recorded");
        }
    }

    /**
     * Searches for employees in an ArrayList by mame.
     * @param employees the list of Employees to search through
     * @param name the name to search for
     * @return an ArrayList of Employee objects whose names match the specified name
     */
    protected static ArrayList<Employee> searchByName(ArrayList<Employee> employees, String name) {
        ArrayList<Employee> matchName = new ArrayList<>();
        for (int i = 0; i < employees.size(); i++) {
            Employee employee = employees.get(i);
            if (employee.getName().equals(name)) {
                matchName.add(employee);
            }
        }
        return matchName;
    }

    /**
     * Prints the details of employees in the provided list using the getDetails method.
     * @param matchName an ArrayList of Employee objects whose details.
     */
    protected static void printNameSearch(ArrayList<Employee> matchName) {
        for (Employee employee : matchName) {
            System.out.println(employee.getDetails());
        }
    }

    /**
     * Filters a list of employees and returns an ArrayList of managers who belong to the specified department.
     * @param employees the list of Employees to filter
     * @param department the department to filter by
     * @return an ArrayList of Manager objects who are part of the specified department
     */
    protected static ArrayList<Employee> filterByDepartment(ArrayList<Employee> employees, String department) {
        ArrayList<Employee> departmentManagers = new ArrayList<>();
        for (Employee employee : employees) {
            if (employee instanceof Manager) {
                Manager manager = (Manager) employee;
                if (manager.getDepartment().equals(department)) {
                    departmentManagers.add(manager);
                }
            }
        }
        return departmentManagers;
    }


    /**
     * Prints the details of each department manager in a list of employees, using the getDetails method.
     * @param departmentManagers an ArrayList of Employees.
     */
    protected static void printDepartmentManager(ArrayList<Employee> departmentManagers) {
        for (Employee employee : departmentManagers) {
            System.out.println(employee.getDetails());
        }
    }

    /**
     * Adds a performance score to the employee's record.
     * If the score is not between 0 and 100, an error message is printed, and an IllegalArgumentException is thrown.
     * @param score the performance score to be added, must be between 0 and 100 inclusive
     */
    protected void addPerformanceScore(int score) {
        if (score <= 100 && score >= 0 ) {
            perfScores.add(score);
        }
        else {
            System.out.println("Score needs to be between 0-100");
            throw new IllegalArgumentException();
        }
    }

    /**
     * Calculates and returns the average performance score of an employee.
     * If no performance scores are recorded, it prints a message and returns 0.
     * @return the average of the recorded performance scores, or 0 if no scores are available.
     */
    protected double getAveragePerformanceScore() {
        if (!perfScores.isEmpty()) {
            double totalScores = 0;
            for (int score : perfScores) {
                totalScores += score;
            }
            return totalScores / perfScores.size();
        } else {
            System.out.println("No performance scores have been added.");
        }
        return 0;
    }

    /**
     * Submits a time-off request for an employee by adding the date and reason
     * to a list of time off requests.
     * @param date   the date of the requested time
     * @param reason the reason for requesting time off
     */
    protected void requestTimeOff(String date, String reason) {
        timeOffRequests.add(new String[]{date, reason});
    }

    /**
     * Displays the time-off requests for the employee, printing each request's date and reason.
     */
    protected void getTimeOffRequests() {
        for (String[] requests : timeOffRequests) {
        System.out.println(String.format("Date: %s, Reason: %s", requests[0], requests[1])); }
    }

    /**
     * Generates a report for a specific department, printing the details of managers, developers, and interns within the specified department
     * by using their getDetails method.
     * If the department has no employees, a message is printed saying that the department is empty.
     * @param employees the list of Employees to search through
     * @param department the name of the department
     */
    protected static void generateDepartmentReport(ArrayList<Employee> employees, String department) {
        System.out.println("Department: " + department);
        for (Employee employee: employees) {
            if (employee.getDepartment().equals(department)) {
                if (employee instanceof Manager) {
                    Manager m = (Manager) employee;
                    if (m.getDepartment().equals(department)) {
                        System.out.println(m.getDetails());
                    }
                } else if (employee instanceof Developer) {
                    Developer d = (Developer) employee;
                    System.out.println(d.getDetails());
                } else if (employee instanceof Intern) {
                    Intern i = (Intern) employee;
                    System.out.println(i.getDetails());
                } else {
                    System.out.println("Department is empty");
                }
            }
        }
    }
}