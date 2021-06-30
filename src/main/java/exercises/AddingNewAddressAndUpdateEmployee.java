package exercises;

import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class AddingNewAddressAndUpdateEmployee extends ExerciseImpl {

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        getEm().getTransaction().begin();
        Address newAddress = new Address();
        newAddress.setText("Vitoshka 15");
        getEm().persist(newAddress);

        String lastName = sc.nextLine();
        int result = getEm().createQuery("UPDATE Employee AS e " +
                "SET e.address = ?1" +
                "WHERE e.lastName = ?2")
                .setParameter(1,newAddress)
                .setParameter(2,lastName)
                .executeUpdate();
        System.out.println(result);

        getEm().getTransaction().commit();
        getEm().close();
    }
}
