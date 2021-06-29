import entities.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        String[] inputFullName = sc.nextLine().split(" ");

        em.getTransaction().begin();
       TypedQuery<Employee> query =  em.createQuery("SELECT e \n" +
                "FROM\tEmployee AS e\n" +
                "WHERE e.firstName = ?1" +
                "AND e.lastName = ?2", Employee.class);
       try {
           Employee result = query.setParameter(1, inputFullName[0])
                   .setParameter(2, inputFullName[1])
                   .getSingleResult();

           System.out.println("Yes");
       }catch (NoResultException e){
           System.out.println("No");
        }

        em.getTransaction().commit();
        em.close();
    }
}
