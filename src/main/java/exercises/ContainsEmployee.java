package exercises;

import entities.Employee;

import javax.persistence.*;
import java.util.List;
import java.util.Scanner;

public class ContainsEmployee extends ExerciseImpl{

    @Override
    public void run() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Insert first and last name seperated by space:");
        String[] inputFullName = sc.nextLine().split(" ");

        getEm().getTransaction().begin();
        TypedQuery<Employee> query =  getEm().createQuery("SELECT e \n" +
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

        getEm().getTransaction().commit();
        getEm().close();
    }
}
