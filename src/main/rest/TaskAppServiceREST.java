package org.gestionare_taskuri.rest;

import jakarta.annotation.PostConstruct;

import org.aspectj.weaver.World;
import org.gestionare_taskuri.DTO.TaskDTO;
import org.gestionare_taskuri.repository.TaskRepository;
import org.gestionare_taskuri.task.Task;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/*
 * http://localhost:8080/rest/service/tasks
 */

@RestController @RequestMapping("/rest/service/tasks") // REST.Resource Style
@Transactional
public class TaskAppServiceREST {
    private static Logger logger = Logger.getLogger(TaskAppServiceREST.class.getName());

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private ModelMapper modelMapper;


    //@GetMapping
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<TaskDTO> toCollection() {
        logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO projects::");
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(t -> logger.info(">>> Task Entites: " + t));

        // prepare dtos
        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskDTOs = tasks.stream().map(t -> modelMapper.map(t, TaskDTO.class)).collect(Collectors.toList());
        taskDTOs.forEach(t -> logger.info(">>> Task DTO: " + t));

        logger.info(">>>>> RETURN DTOs");
        return taskDTOs;
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<TaskDTO> addIntoCollection(@RequestBody TaskDTO taskDTO) {
        Task task = modelMapper.map(taskDTO, Task.class);
        // fix back references
        //task.getLansare().forEach(l -> {
          //  l.setTask(task);
       // });
        // save aggregate
        taskRepository.save(task);
        logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
        // return updated collection
        List<Task> tasks = taskRepository.findAll();
        //
        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskDTOs = tasks.stream().map(t -> modelMapper.map(t, TaskDTO.class)).collect(Collectors.toList());
        taskDTOs.forEach(t -> logger.info(">>> Task DTO: " + t));

        logger.info(">>>>> RETURN DTOs");
        return taskDTOs;
    }

    //@DeleteMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<TaskDTO> removeFromCollection(@PathVariable Integer cod) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - task: " + cod);
        taskRepository.deleteById(cod);
        // return updated collection
        List<Task> tasks = taskRepository.findAll();
        List<TaskDTO> taskDTOs = new ArrayList<>();
        taskDTOs = tasks.stream().map(p -> modelMapper.map(p, TaskDTO.class)).collect(Collectors.toList());
        taskDTOs.forEach(t -> logger.info(">>> Task DTO: " + t));

        logger.info(">>>>> RETURN DTOs");
        return taskDTOs;
    }

    //@GetMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TaskDTO getById(@PathVariable("cod") Integer cod) {
        Task task = taskRepository.findById(cod).get();
        logger.info("**** DEBUG SPRING-MVC-REST getById(" + cod + ") = " + task);
        //
        return modelMapper.map(task, TaskDTO.class);
    }

    //@PutMapping
    @RequestMapping(value = "/{cod}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public TaskDTO add(@PathVariable("cod") Integer cod, @RequestBody TaskDTO taskDTO) {
        logger.info("**** DEBUG Spring REST saving aggregate PUT: " + taskDTO + " [cod]:" + cod);
        Optional<Task> taskBO = taskRepository.findById(cod);
        if (taskBO == null || !taskBO.get().equals(cod))
            throw new RuntimeException("Task missing!");
        // save aggregate
        Task task = modelMapper.map(taskDTO, Task.class);
        // fix back references
        //task.getLansare().forEach(l -> l.setTask(task));
        //
        Task savedTask = taskRepository.save(task);
        // return saved project
        return modelMapper.map(savedTask, TaskDTO.class);
    }

    //@DeleteMapping
    /* http://localhost:8080/rest/service/tasks?cod=11 DELETE */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void remove(@RequestParam(name = "cod") Integer cod) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - task: " + cod);
        taskRepository.deleteById(cod);
    }

    @GetMapping(path = "/test")
    //@RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage() {
        return "Task DataService SPRING-MVC-REST is working...";
    }

    @PostConstruct
    private void setUps() {
        logger.info(">>>>> Setting up MAPPER");

        // Creează mapări personalizate pentru Task -> TaskDTO
        TypeMap<Task, TaskDTO> taskDTOMapper = this.modelMapper.createTypeMap(Task.class, TaskDTO.class);

        taskDTOMapper.addMappings(mapper -> mapper.map(Task::getTask, TaskDTO::setCod));
        taskDTOMapper.addMappings(mapper -> mapper.map(Task::getNume, TaskDTO::setNume));
        taskDTOMapper.addMappings(mapper -> mapper.map(Task::getDataInceput, TaskDTO::setDataInceput));
        taskDTOMapper.addMappings(mapper -> mapper.map(Task::getLansare, TaskDTO::setLansare));

        logger.info(">>>>> MAPPER configured successfully");
    }
}