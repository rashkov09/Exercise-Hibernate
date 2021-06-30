package exercises;

import javax.persistence.EntityManager;

public class ChangeCasing extends ExerciseImpl{

    @Override
    public void run() {


        getEm().getTransaction().begin();

        int result = updateTowns(getEm());
        System.out.println(result);

        getEm().getTransaction().commit();
        getEm().close();
    }

    private static int updateTowns(EntityManager em) {
        return em.createQuery(
                "UPDATE \tTown AS t\n" +
                        "SET t.name = UPPER(t.name)" +
                        "WHERE CHAR_LENGTH(t.name) > ?1")
                .setParameter(1,(long) 5)
                .executeUpdate();
    }

}
