public abstract class Employee
{
    private String Name;
    private int Salary;

    public Employee(String name, int salary)
    {
        this.Name = name;
        this.Salary = salary;
    }

    public int getSalary()
    {
        return this.Salary;
    }
    public String getName()
    {
        return this.Name;
    }

    public void raiseSalary(double percent)
    {
        this.Salary *=(1.0 + percent/100.0);
    }
}

