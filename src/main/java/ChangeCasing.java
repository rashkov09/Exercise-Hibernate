import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("soft_uni");
        EntityManager em = factory.createEntityManager();

       em.getTransaction().begin();

        int result = updateTowns(em);
        System.out.println(result);

        em.getTransaction().commit();
        em.close();
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
