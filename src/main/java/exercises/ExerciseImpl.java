package exercises;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public abstract class ExerciseImpl implements Exercise{
    private  EntityManagerFactory factory ;
    private  EntityManager em;

    public ExerciseImpl() {
        setFactory();
        setEm(factory);
    }

    private void setFactory() {
        this.factory = Persistence.createEntityManagerFactory("soft_uni");
    }

    protected EntityManager getEm() {
        return em;
    }

    private void setEm(EntityManagerFactory factory) {
        this.em = factory.createEntityManager();
    }
}
