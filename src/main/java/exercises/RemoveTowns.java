package exercises;

import entities.Address;
import entities.Employee;
import entities.Town;
import java.util.List;
import java.util.Scanner;

public class RemoveTowns extends ExerciseImpl {
    @Override
    public void run() {
        getEm().getTransaction().begin();
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter town name:");
        String townName = sc.nextLine();

        Town town = (Town) getEm()
                .createQuery("SELECT t FROM Town AS t WHERE t.name = ?1")
                .setParameter(1,townName)
                .getSingleResult();

        List<Address> addresses = getEm()
                .createQuery("SELECT a FROM Address AS a WHERE a.town = ?1",Address.class)
                .setParameter(1,town)
                .getResultList();

        addresses.forEach(address -> {

            List<Employee> employees = getEm()
                    .createQuery("SELECT e FROM Employee AS e WHERE e.address=?1",Employee.class)
                    .setParameter(1,address)
                    .getResultList();

            for (Employee employee: employees){
                employee.setAddress(null);
                address.getEmployees().remove(employee);
            }
            getEm().remove(address);
        });

        getEm().remove(town);

        if (addresses.size() == 1 ){
            System.out.printf("1 address in %s deleted",townName);
        } else {
            System.out.printf("%d addresses in %s deleted",addresses.size(),townName);
        }

        getEm().getTransaction().commit();
        getEm().close();
    }
}
