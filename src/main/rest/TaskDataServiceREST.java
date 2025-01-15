package org.gestionare_taskuri.rest;


import org.gestionare_taskuri.DTO.TaskDTO;
import org.gestionare_taskuri.exception.ResourceNotFoundException;
import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.task.Task;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;

/*
 * http://localhost:8080/rest/data/tasks
 */

@RestController @RequestMapping("/rest/data/tasks") // REST.Resource Style
@Transactional
public class TaskDataServiceREST {
    private final static Logger logger = Logger.getLogger(TaskDataServiceREST.class.getName());

  @Autowired
    private TaskRepository taskRepository;

@Autowired
    private  TaskDTO taskDTO;

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<Task> toCollection() {
        logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO tasks::");
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(t -> logger.info(">>> Task Entites: " + t));

        //return tasks;
        //modelMapper.map(tasks, taskDTOs);
        List<Task> taskDTOs = List.of();
        List<Task> tasksDTOs = tasks.toDTOList(taskDTOs);
        taskDTOs.forEach(t -> logger.info(">>> Task DTO: " + t));

        logger.info(">>>>> RETURN DTOs");
        return taskDTOs;
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<Task> addIntoCollection(@RequestBody Task task) {
        // save aggregate
        taskRepository.save(task);
        logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
        // return updated collection
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    //@DeleteMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<Task> removeFromCollection(@PathVariable Integer cod) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - task: " + cod);
        taskRepository.deleteById(cod);
        // return updated collection
        List<Task> tasks = taskRepository.findAll();
        return tasks;
    }

    //@GetMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Task getById(@PathVariable("cod") Integer cod) throws ResourceNotFoundException {
        try {
            Task task = taskRepository.findById(cod).get();
            logger.info("**** DEBUG SPRING-MVC-REST getById(" + cod + ") = " + task);
            return task;
        }catch (Exception e){
            throw new ResourceNotFoundException("Task with Cod: " + cod + " is NOT FOUND!");
        }
    }

    //@PutMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Task add(@PathVariable("cod") Integer cod, @RequestBody Task task) {
        logger.info("**** DEBUG Spring REST saving aggregate PUT: " + task + " [task]:" + task);
       Task taskBO = taskRepository.findByTask(cod);
        if (taskBO == null || !taskBO.getTask().equals(cod))
            throw new RuntimeException("Task missing!");
        // save aggregate
        task = taskRepository.save(task);
        // return saved task
        return task;
    }

    //@DeleteMapping
    /* http://localhost:8080/rest/data/tasks?id=11 DELETE */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void remove(@RequestParam(name = "cod") Integer cod) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - project: " + cod);
        taskRepository.deleteById(cod);
    }

    @GetMapping(path = "/test")
    //@RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage(){
        return "Project DataService SPRING-MVC-REST is working...";
    }
}