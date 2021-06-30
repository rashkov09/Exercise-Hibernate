package exercises;

import entities.Department;
import entities.Employee;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class IncreaseSalaries extends ExerciseImpl {
    @Override
    public void run() {
        getEm().getTransaction().begin();
        TypedQuery<Employee> query = getEm().createQuery("SELECT e FROM Employee AS e WHERE e.department IN (?1,?2,?3,?4)",Employee.class);
        query.setParameter(1, getEm().find(Department.class,1));
        query.setParameter(2, getEm().find(Department.class,2));
        query.setParameter(3, getEm().find(Department.class,4));
        query.setParameter(4, getEm().find(Department.class,11));

        List<Employee> employees = query.getResultList();

       employees.forEach(employee -> {
           getEm().detach(employee);
           employee.setSalary(employee.getSalary().multiply(BigDecimal.valueOf(1.12)));
           getEm().merge(employee);
           System.out.printf("%s %s ($%.2f)\n",employee.getFirstName(),employee.getLastName(), employee.getSalary());
       });

        getEm().getTransaction().commit();
        getEm().close();


    }
}
