import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdateEmployee {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();
        Scanner sc = new Scanner(System.in);
        em.getTransaction().begin();
        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        em.persist(newAddress);

        String lastName = sc.nextLine();
        int result = em.createQuery("UPDATE Employee AS e " +
                "SET e.address = ?1" +
                "WHERE e.lastName = ?2")
                .setParameter(1,newAddress)
                .setParameter(2,lastName)
                .executeUpdate();
        System.out.println(result);

        em.getTransaction().commit();
        em.close();
    }
}
