/**
 * Class representing a project.
 * Includes methods to return project information as a string, add expenses to the project and reduce the budget.
 */

public class Project {

    private String projectName;
    private double budget;
    private int deadline;

    /**
     * Constructs a Project with the specified details.
     * @param projectName
     * @param budget
     * @param deadline
     */
    public Project(String projectName, double budget, int deadline) {
        this.projectName = projectName;
        this.budget = budget;
        this.deadline = deadline;
    }

    /**
     * Getters for accessing private fields and setters for updating.
     */

    public String getProjectName() {
        return projectName;
    }

    public double getBudget() {
        return budget;
    }

    public int getDeadline() {
        return deadline;
    }

    /**
     * Retrieves the details of the project, including name, budget and deadline.
     * @return A string containing the project's details in specified format
     */
    public String getProjectDetails() {
        return "Project: " + getProjectName() + ", Budget: " + getBudget() + ", Deadline: " + getDeadline();
    }

    /**
     * Reduces the current budget by the specified amount.
     * @param amount The amount to be subtracted from the budget.
     * @return The new budget after the specified amount is deducted.
     */
    protected double reduceBudget(double amount) {
        return getBudget() - amount;
    }

    /**
     * Deducts a specified amount from the project's budget, and outputs the remaining budget.
     * If the amound deducted is larger than the budget itself, or not greater than 0, errors are handled.
     * @param amount to be deducted from the project's current budget.
     * @return The updated remaining budget after the deduction is made or Returns -1 if the amount is invalid.
     */
    protected double addExpense(double amount) {
        if (amount > 0) {
            if (amount <= getBudget()) {
                budget = getBudget() - amount;
                System.out.println("You have removed " + amount + " from " + getProjectName());
                System.out.println("Remaining budget is: " + budget);
                return budget;
            } else {
                throw new IllegalArgumentException();
            }
        }
        else {System.out.println("Amount entered needs to be greater than 0"); }
        return -1;
    }
}
