import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.swing.*;
import javax.swing.text.html.StyleSheet;
import java.util.List;

public class EmployeesWithSalaryOver50000 {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        em.getTransaction().begin();
        TypedQuery<String> query =
                em.createQuery("SELECT e.firstName FROM Employee AS e WHERE e.salary > 50000",String.class);
        List<String> firstNames = query.getResultList();
        firstNames.forEach(System.out::println);


        em.getTransaction().commit();
        em.close();
    }
}
