package exercises;

import entities.Department;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesFromDepartment extends ExerciseImpl {

    @Override
    public void run() {

        getEm().getTransaction().begin();

        TypedQuery<Department> firsQuery = getEm().createQuery("SELECT d FROM Department AS d WHERE d.name = 'Research and Development'",Department.class);
        Department criteria = firsQuery.getSingleResult();

        TypedQuery<Employee> query = getEm().createQuery(
                "SELECT e FROM Employee AS e " +
                        "WHERE e.department = ?1 " +
                        "ORDER BY e.salary ASC, e.id ASC",Employee.class);
        List<Employee> result = query.setParameter(1,criteria).getResultList();

        result.forEach(e -> {
            System.out.printf("%s %s from %s - $%.2f\n",e.getFirstName(),e.getLastName(),e.getDepartment().getName(),e.getSalary());
        });
        getEm().getTransaction().commit();
        getEm().close();
    }
}
