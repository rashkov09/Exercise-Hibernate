import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

        em.getTransaction().begin();
        TypedQuery<Address> query =  em.createQuery( "SELECT a FROM Address AS a  ",Address.class);
        List<Address> result = query.getResultList()
                .stream()
                .sorted((a1,a2)->a2.getEmployees().size() - a1.getEmployees().size())
                .limit(10)
                .collect(Collectors.toList());

        result.forEach(a ->{
            System.out.printf("%s, %s - %d employees\n",a.getText(),a.getTown().getName(),a.getEmployees().size());
        });


        em.getTransaction().commit();
        em.close();
    }
}
