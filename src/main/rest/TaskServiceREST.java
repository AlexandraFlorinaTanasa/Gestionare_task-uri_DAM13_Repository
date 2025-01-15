package org.gestionare_taskuri.rest;


import org.gestionare_taskuri.servicii.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.logging.Logger;

/* REST.RPC API
 * 	http://localhost:8080/rest/service/workflow/planNewProject/{projectName}/{startDate}
 *  http://localhost:8080/rest/service/workflow/planNewProject/Rest_Project/05-12-2019
 *  http://localhost:8080/rest/service/workflow/planNewProject?projectName={projectName}&startDate={startDate}
 *  http://localhost:8080/rest/service/workflow/planNewProject?projectName=Rest_Project&startDate=05-12-2019
 *
 * 	http://localhost:8080/rest/service/workflow/addFeatureToProject/{projectId}/{featureName}/{featureDescription}
 *  http://localhost:8080/rest/service/workflow/addFeatureToProject/34/Feature_1/REST_RPC_Feature
 *  http://localhost:8080/rest/service/workflow/addFeatureToProject?projectId={projectId}&featureName={featureName}&featureDescription={featureDescription}
 *  http://localhost:8080//rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription=REST_RPC_Feature
 *  http://localhost:8080/rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription="REST_RPC Feature"
 *
 *	http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease/{projectId}/{publishDate}
 *	http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease/34/05-03-2020
 *  http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease?projectId={projectId}&publishDate={publishDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planCurrentRelease?projectId=34&publishDate=07-03-2020
 *
 * 	http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData/{projectId}
 * 	http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData/34
 *  http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData?projectId={projectId}
 *  http://localhost:8080/scrum/rest/service/workflow/getProjectSummaryData?projectId=34
 */
@RestController
@RequestMapping("/rest/service/task") // REST.RPC Style
@Transactional
public class TaskServiceREST {
    private static Logger logger = Logger.getLogger(TaskServiceREST.class.getName());

    @Autowired
    private TaskService taskService;

    @RequestMapping(
            path = "/planNewTask/{nume}/{dataInceput}",
            method = RequestMethod.GET)
    @ResponseBody
    // (1) Create new task with default template: nume, dataInceput
    public Integer planNewTask(
            @PathVariable("nume") String nume,
            @PathVariable("dataInceput") LocalDate dataInceput) throws Exception {
        logger.info(">>> Start Procesing: planNewTask /" + nume + "/" + dataInceput);

        Date startDateFromString = new SimpleDateFormat("dd-MM-yyyy").parse(String.valueOf(dataInceput));
        Integer cod = TaskService.planNewTask(nume, startDateFromString);

        logger.info(">>> End Procesing: planNewTask /" + nume + "/" + dataInceput + ": " + cod);
        return cod;
    }

    @RequestMapping(path = "/planNewPTask", method = RequestMethod.GET)
    @ResponseBody
    public Integer planNewtaskRequestHandler(
            @RequestParam("nume") String nume,
            @RequestParam("dataInceput") LocalDate dataInceput) throws Exception {
        return planNewProject(nume, dataInceput);
    }


}