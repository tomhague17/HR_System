import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {

        try {

            Project aiImplementation = new Project("Ai Implementation", 5000, 28);
            Project javaMigration = new Project("Java Migration", 10000, 50);
            Project reactFrontend = new Project("React Frontend", 1000.50, 20);
            Project ibmIntegration = new Project("IBM Integration", 20000, 150);

            Manager Barry = new Manager("Barry", 123, "Computer Science", ibmIntegration);
            Developer Steve = new Developer("Steve", 607,  25, "Sales", "Java", reactFrontend);
            Intern John = new Intern("John", 403, "Computer Science", "Newcastle University", 12);
            Developer Tom = new Developer("Tom", 420, 32, "Computer Science", ".NET", javaMigration);
            Intern Bob = new Intern("Bob", 190, "eCommerce", "Northumbria", 6);
            Manager Peter = new Manager("Peter", 493, "General", aiImplementation);
            Developer Derek = new Developer("Derek", 901, "Sales", 41, ibmIntegration);
            Intern Tim = new Intern("Tim", 854, "eCommerce");
            Developer Tim2 = new Developer("Tim", 854, "eCommerce", 29, ibmIntegration);

            System.out.println(Tim.equals(Tim2));
            System.out.println(Tim.equals(Derek));

            Employee[] Employees = new Employee[8];
            Employees[0] = Barry;
            Employees[1] = Steve;
            Employees[2] = John;
            Employees[3] = Tom;
            Employees[4] = Bob;
            Employees[5] = Peter;
            Employees[6] = Derek;
            Employees[7] = Tim;
            for (Employee employee : Employees) {
                System.out.println(employee.getDetails());
                if (employee instanceof Payable) {
                    Payable payingEmployee = (Payable) employee;
                    System.out.println(employee.getName() + "'s Pay: $" + payingEmployee.calcMonthlySalary(40));
                }
            }
            Employee.addArrayList(Employees);
            Employee.saveToFile(Employees);
            System.out.println("");

            Employee.sortID(Employee.employees);
            System.out.println("Sorted by ID:");
            for (Employee e : Employee.employees) {
                System.out.println(e.getDetails());
            }
            System.out.println("");

            Employee.sortName(Employee.employees);
            System.out.println("Sorted by name: ");
            for (Employee e : Employee.employees) {
                System.out.println(e.getDetails());
            }
            System.out.println("");

            HashMap<String, Integer> EmployeeHM = new HashMap<>();
            EmployeeHM.put(Barry.getName(), Barry.getID());
            EmployeeHM.put(Steve.getName(), Steve.getID());
            EmployeeHM.put(John.getName(), John.getID());
            EmployeeHM.put(Tom.getName(), Tom.getID());
            EmployeeHM.put(Bob.getName(), Bob.getID());
            EmployeeHM.put(Peter.getName(), Peter.getID());
            EmployeeHM.put(Derek.getName(), Derek.getID());
            EmployeeHM.put(Tim.getName(), Tim.getID());
            EmployeeHM.put(Tim2.getName(), Tim2.getID());
            System.out.println("Employees in HashMap: " + EmployeeHM.size());
            System.out.println("No. of employees: " + Employee.getEmployeeCount());

            System.out.println("");

            Employee.loadFromFile();

            System.out.println("");
            System.out.println("Record attendances: ");
            Tom.recordAttendance("18/12/2024", false);
            Tom.recordAttendance("17/12/2024", true);
            Tom.getAttendanceSummary(Tom);

            System.out.println("");
            Barry.logProjectExpense(1000);
            Tom.logProjectExpense(5000);

            System.out.println("");
            Employee.printNameSearch(Employee.searchByName(Employee.employees, "Tom"));
            Employee.printDepartmentManager(Employee.filterByDepartment(Employee.employees, "Computer Science"));

            System.out.println("");
            System.out.println("Tom's Monthly salary: " + Tom.calcMonthlySalary(160));
            System.out.println("Tom's Monthly salary after bonus: " + Tom.addMonthlyBonus(200));
            System.out.println("Tom's Yearly salary: " + Tom.calculateYearlyPay());

            System.out.println("");
            Tom.addPerformanceScore(75);
            Tom.addPerformanceScore(80);
            Tom.addPerformanceScore(85);
            System.out.println("Tom's Average performance score: " + Tom.getAveragePerformanceScore());

            System.out.println("");
            Tom.requestTimeOff("24/12/24", "Christmas");
            Tom.requestTimeOff("8/6/25", "Birthday");
            Tom.getTimeOffRequests();

            System.out.println("");
            Employee.generateDepartmentReport(Employee.employees, "Computer Science");
            Employee.generateDepartmentReport(Employee.employees, "Sales");
            Employee.generateDepartmentReport(Employee.employees, "eCommerce");
            Employee.generateDepartmentReport(Employee.employees, "General");

            System.out.println("");
            sortEmployees(Employee.employees, Comparator.comparing(Employee::getName));
            System.out.println("Sorted by name: ");
            for (Employee employee : Employee.employees) {
                System.out.println(employee.getDetails());
            }
            System.out.println("Sorted by ID: ");
            sortEmployees(Employee.employees, Comparator.comparing(Employee::getID));
            for (Employee employee : Employee.employees) {
                System.out.println(employee.getDetails());
            }
            sortEmployees(Employee.employees, Comparator.comparing(Employee::getDepartment));
            System.out.println("Sorted by department: ");
            for (Employee employee : Employee.employees) {
                System.out.println(employee.getDetails());
            }

        } catch (InvalidInputException e) {
            System.out.println(e);
        }

    }

    private static <T> void sortEmployees(ArrayList<T> employees, Comparator<T> comparing) {
        employees.sort(comparing);
    }
}