package org.gestionare_taskuri.rest;


import org.gestionare_taskuri.DTO.SprintDTO;
import org.gestionare_taskuri.exception.ResourceNotFoundException;
import org.gestionare_taskuri.repository.SprintRepository;
import org.gestionare_taskuri.task.SprintPlanning;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.logging.Logger;

/*
 * http://localhost:8080/scrum/rest/data/projects
 */

@RestController @RequestMapping("/rest/data/sprints") // REST.Resource Style
@Transactional
public class SprintDataServiceREST {
    private static final  Logger logger = Logger.getLogger(SprintDataServiceREST.class.getName());

    @Autowired
    public SprintRepository sprintRepository;

    @Autowired(required = false)
    private SprintDTO sprintDTO;

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<SprintPlanning> toCollection() {
        logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO sprints::");
        List<SprintPlanning> sprints = sprintRepository.findAll();
        sprints.forEach(s -> logger.info(">>> Sprints Entites: " + s));

        //return sprints;
        List<SprintPlanning> sprintDTOs = new ArrayList<>();
        //modelMapper.map(sprints, sprintDTOs);
        sprintDTOs = sprintDTO.toDTOList(sprints);
        sprintDTOs.forEach(s -> logger.info(">>> Sprint DTO: " + s));

        logger.info(">>>>> RETURN DTOs");
        return sprintDTOs;
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<SprintPlanning> addIntoCollection(@RequestBody SprintPlanning sprintPlanning) {
        // save aggregate
        sprintRepository.save(sprintPlanning);
        logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
        // return updated collection
        List<SprintPlanning> sprints = sprintRepository.findAll();
        return sprints;
    }

    //@DeleteMapping
    @RequestMapping(value = "/{codSprint}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<SprintPlanning> removeFromCollection(@PathVariable Integer codSprint) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - sprint: " + codSprint);
        sprintRepository.deleteById(codSprint);
        // return updated collection
        List<SprintPlanning> sprints = sprintRepository.findAll();
        return sprints;
    }

    //@GetMapping
    @RequestMapping(value = "/{codSprint}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SprintPlanning getByCod(@PathVariable("codSprint") Integer codSprint) throws ResourceNotFoundException {
        try {
            SprintPlanning sprintPlanning = sprintRepository.findById(codSprint).get();
            logger.info("**** DEBUG SPRING-MVC-REST getById(" + codSprint + ") = " + sprintPlanning);
            return sprintPlanning;
        }catch (Exception e){
            throw new ResourceNotFoundException("Sprint with cod: " + codSprint + " is NOT FOUND!");
        }
    }

    //@PutMapping
    @RequestMapping(value = "/{codSprint}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SprintPlanning add(@PathVariable("codSprint") Integer codSprint, @RequestBody SprintPlanning sprintPlanning) {
        logger.info("**** DEBUG Spring REST saving aggregate PUT: " + sprintPlanning + " [codSprint]:" + codSprint);
        Optional<SprintPlanning> sprintBO = sprintRepository.findById(codSprint);
        if (Objects.equals(sprintBO.getCod(), codSprint)) {
            if (sprintBO.isEmpty()) {
                throw new RuntimeException("Sprint missing!");
            }
        } else {
    throw new RuntimeException("Sprint missing!");
}
        // save aggregate
        sprintPlanning= sprintRepository.save(sprintPlanning);
        // return saved project
        return sprintPlanning;
    }

    //@DeleteMapping
    /* http://localhost:8080/rest/data/sprints?id=11 DELETE */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void remove(@RequestParam(name = "codSprint") Integer codSprint) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - sprint: " + codSprint);
        sprintRepository.deleteById(codSprint);
    }

    @GetMapping(path = "/test")
    //@RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage(){
        return "Sprint DataService SPRING-MVC-REST is working...";
    }
}