package exercises;

import entities.Employee;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Scanner;

public class FindEmployeesByFirstName extends ExerciseImpl {
    @Override
    public void run() {
        getEm().getTransaction().begin();
        Scanner sc = new Scanner(System.in);

        TypedQuery<Employee> query = getEm().createQuery("SELECT e FROM Employee AS e",Employee.class);
        System.out.println("Input search criteria:");
        String criteria = sc.nextLine();

        List<Employee> employees = query.getResultList();

        employees
                .stream()
                .filter(employee -> employee.getFirstName().toLowerCase().startsWith(criteria.toLowerCase()))
                .forEach(employee -> {
            System.out.printf("%s %s - %s - ($%.2f)\n",
                    employee.getFirstName(),employee.getLastName(),employee.getJobTitle(),employee.getSalary());
        });


        getEm().getTransaction().commit();
        getEm().close();
    }
}
