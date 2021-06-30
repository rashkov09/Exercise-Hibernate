package exercises;

import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeesWithSalaryOver50000 extends ExerciseImpl {
    @Override
    public void run() {
        getEm().getTransaction().begin();
        TypedQuery<String> query =
                getEm().createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000",String.class);
        List<String> firstNames = query.getResultList();
        firstNames.forEach(System.out::println);


        getEm().getTransaction().commit();
        getEm().close();
    }
}
