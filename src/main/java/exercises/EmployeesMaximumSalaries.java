package exercises;

import entities.Department;

import javax.persistence.TypedQuery;
import java.math.BigDecimal;
import java.util.List;

public class EmployeesMaximumSalaries extends ExerciseImpl{
    @Override
    public void run() {
        getEm().getTransaction().begin();
        TypedQuery<Department> query = getEm().createQuery("SELECT d\n" +
                "FROM Department AS d\n" +
                "JOIN\tEmployee AS e\n" +
                "ON\td= e.department\n" +
                "GROUP BY d\n" +
                "HAVING MAX(e.salary) NOT BETWEEN 30000 AND 70000",Department.class);

        TypedQuery<BigDecimal> maxSal = getEm().createQuery("SELECT MAX(e.salary)\n" +
                                "FROM Employee AS e\n" +
                                "WHERE e.department = ?1",BigDecimal.class);

        List<Department> departments = query.getResultList();

        departments.forEach(department->{
            maxSal.setParameter(1,department);
            System.out.printf("%s %.2f\n",department.getName(), maxSal.getSingleResult());
        });


        getEm().getTransaction().commit();
        getEm().close();
    }
}
