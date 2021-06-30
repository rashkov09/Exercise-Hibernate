package exercises;

import entities.Project;

import javax.persistence.TypedQuery;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FindLatest10Projects extends ExerciseImpl {

    @Override
    public void run() {
        getEm().getTransaction().begin();
        TypedQuery<Project> query = getEm().createQuery("SELECT p FROM Project AS p ORDER BY p.startDate DESC",Project.class);
        List<Project> projectList = query.getResultList().stream().sorted(Comparator.comparing(Project::getName)).limit(10).collect(Collectors.toList());
        projectList.forEach(project -> {
            System.out.printf("Project name: %s\n" +
                    "       Project description: %s\n" +
                    "       Project Start Date: %s\n" +
                    "       Project End Date: %s\n",project.getName(),project.getDescription(),project.getStartDate(),project.getEndDate());
        });

        getEm().getTransaction().commit();
        getEm().close();
    }
}
