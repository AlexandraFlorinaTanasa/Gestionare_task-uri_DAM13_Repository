package org.gestionare_taskuri.rest;
import jakarta.annotation.PostConstruct;
import org.gestionare_taskuri.DTO.SprintDTO;
import org.gestionare_taskuri.repository.SprintRepository;
import org.gestionare_taskuri.task.SprintPlanning;
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
 * http://localhost:8080/rest/service/sprints
 */

@RestController @RequestMapping("/rest/service/sprints") // REST.Resource Style
@Transactional
public class SprintAppServiceREST {
    private static Logger logger = Logger.getLogger(SprintAppServiceREST.class.getName());

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private ModelMapper modelMapper;

    //@GetMapping
    @RequestMapping(method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public List<SprintDTO> toCollection() {
        logger.info("**** DEBUG SPRING-MVC-REST toCollection() >>> get All DTO sprints::");
        List<SprintPlanning> sprints = sprintRepository.findAll();
        sprints.forEach(s -> logger.info(">>> Sprint Entites: " + s));

        // prepare dtos
        List<SprintDTO> sprintsDTOs = new ArrayList<>();
        sprintsDTOs = sprints.stream().map(s -> modelMapper.map(s, SprintDTO.class)).collect(Collectors.toList());
        sprintsDTOs.forEach(s -> logger.info(">>> Sprint DTO: " + s));

        logger.info(">>>>> RETURN DTOs");
        return sprintsDTOs;
    }

    //@PostMapping
    @RequestMapping(method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<SprintDTO> addIntoCollection(@RequestBody SprintDTO sprintDTO) {
        SprintPlanning sprintPlanning = modelMapper.map(sprintDTO, SprintPlanning.class);
        // fix back references

        // save aggregate
        sprintRepository.save(sprintPlanning);
        logger.info("**** DEBUG SPRING-MVC-REST save aggregate POST!");
        // return updated collection
        List<SprintPlanning> sprintPlannings = sprintRepository.findAll();
        //
        List<SprintDTO> sprintDTOs = new ArrayList<>();
        sprintDTOs = sprintDTOs.stream().map(s -> modelMapper.map(s, SprintDTO.class)).collect(Collectors.toList());
        sprintDTOs.forEach(s -> logger.info(">>> Sprint DTO: " + s));

        logger.info(">>>>> RETURN DTOs");
        return sprintDTOs;
    }

    //@DeleteMapping
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE,
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Collection<SprintDTO> removeFromCollection(@PathVariable Integer codSprint) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - sprint: " + codSprint);
        sprintRepository.deleteById(codSprint);
        // return updated collection
        List<SprintPlanning> sprintPlannings = sprintRepository.findAll();
        List<SprintDTO> sprintDTOs = new ArrayList<>();
        sprintDTOs = sprintDTOs.stream().map(s -> modelMapper.map(s, SprintDTO.class)).collect(Collectors.toList());
        sprintDTOs.forEach(s -> logger.info(">>>Sprint DTO: " + s));

        logger.info(">>>>> RETURN DTOs");
        return sprintDTOs;
    }

    //@GetMapping
    @RequestMapping(value = "/{icodSprint}", method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SprintDTO getById(@PathVariable("codSprint") Integer codSprint) {
        SprintPlanning sprintPlanning = sprintRepository.findById(codSprint).get();
        logger.info("**** DEBUG SPRING-MVC-REST getById(" + codSprint + ") = " + sprintPlanning);
        //
        return modelMapper.map(sprintPlanning, SprintDTO.class);
    }

    //@PutMapping
    @RequestMapping(value = "/{codSprint}", method = RequestMethod.PUT,
            consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public SprintDTO add(@PathVariable("codSprint") Integer codSprint, @RequestBody SprintDTO sprintDTO) {
        logger.info("**** DEBUG Spring REST saving aggregate PUT: " + sprintDTO + " [codSprint]:" + codSprint);
        Optional<SprintPlanning> sprintBO = sprintRepository.findById(codSprint);
        if (sprintBO == null || !sprintBO.get().equals(codSprint))
            throw new RuntimeException("Project missing!");
        // save aggregate
        SprintPlanning sprintPlanning = modelMapper.map(sprintDTO, SprintPlanning.class);

        //
        SprintPlanning savedSprint = sprintRepository.save(sprintPlanning);
        // return saved sprint
        return modelMapper.map(savedSprint, SprintDTO.class);
    }

    //@DeleteMapping
    /* http://localhost:8080/scrum/rest/service/projects?id=11 DELETE */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public void remove(@RequestParam(name = "codSprint") Integer codSprint) {
        logger.info("DEBUG: called SPRING-MVC-REST REMOVE - project: " + codSprint);
        sprintRepository.deleteById(codSprint);
    }

    @GetMapping(path = "/test")
    //@RequestMapping(path = "/test", method = RequestMethod.GET)
    @ResponseBody
    public String getMessage() {
        return "Project DataService SPRING-MVC-REST is working...";
    }

    @PostConstruct
    private void setUps() {
        logger.info(">>>>> Setting MAPPER");
        // Setup Project DTO
        TypeMap<SprintPlanning, SprintDTO> sprintDTOMapper = this.modelMapper.createTypeMap(SprintPlanning.class, SprintDTO.class);
        sprintDTOMapper.addMappings(mapper -> mapper.map(src -> src.getCodSprint(), SprintDTO::setCodSprint));
        sprintDTOMapper.addMappings(mapper -> mapper.map(src -> src.getNumeSprint(), SprintDTO::setNume));

    }
}