/**
 * Payable is an interface that provides methods for calculating monthly salary, adding a monthly bonus,
 * and determining yearly pay of employees.
 */

public interface Payable {

    public double addMonthlyBonus(double bonus);

    public double calcMonthlySalary(int hoursWorked);

    public double calculateYearlyPay();
}
