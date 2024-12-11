package org.gestionare_taskuri.test;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import task.Task;

import java.util.List;

import static com.github.javaparser.utils.Utils.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TestEntityManagerJPA {
    @Autowired
    EntityManager  em;

    @Test
    void testEntityManager() {
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