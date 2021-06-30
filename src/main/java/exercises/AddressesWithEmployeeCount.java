package exercises;

import entities.Address;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

public class AddressesWithEmployeeCount extends ExerciseImpl {

    @Override
    public void run() {

        getEm().getTransaction().begin();
        TypedQuery<Address> query =  getEm().createQuery( "SELECT a FROM Address AS a  ",Address.class);
        List<Address> result = query.getResultList()
                .stream()
                .sorted((a1,a2)->a2.getEmployees().size() - a1.getEmployees().size())
                .limit(10)
                .collect(Collectors.toList());

        result.forEach(a ->{
            System.out.printf("%s, %s - %d employees\n",a.getText(),a.getTown().getName(),a.getEmployees().size());
        });


        getEm().getTransaction().commit();
        getEm().close();
    }
}
