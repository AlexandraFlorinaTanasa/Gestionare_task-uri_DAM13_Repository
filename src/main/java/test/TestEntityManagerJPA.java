package test;


import jakarta.persistence.EntityManager;
import org.gestionare_taskuri.task.Task;
import org.junit.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;


import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestEntityManagerJPA {

    EntityManager  em;

    @Test
    public void testEntityManager() {
        assertNotNull(em);

        //"SELECT t FROM Task t",
        List<Task> tListEntity = em.createQuery("SELECT t FROM Task t", Task.class)
                .getResultList();

        assertTrue(tListEntity.size() > 0);

        for(Task t: tListEntity)
            System.out.println("Task: " + t);
        //
    }
}