package exercises;

import entities.Employee;
import entities.Project;

import java.util.Comparator;
import java.util.Scanner;
import java.util.concurrent.ConcurrentMap;

public class GetEmployeeWithProject extends ExerciseImpl{

    @Override
    public void run() {
        getEm().getTransaction().begin();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter employee ID:");
        int employeeId = Integer.parseInt(sc.nextLine());
        Employee employee = getEm().find(Employee.class,employeeId);
        if (employee != null){
            System.out.printf("%s %s - %s\n",employee.getFirstName(),employee.getLastName(),employee.getJobTitle());
            employee.getProjects().stream().sorted(Comparator.comparing(Project::getName)).forEach(p->{
                System.out.println("      "+p.getName());
            });
        }

        getEm().getTransaction().commit();
        getEm().close();

    }
}
