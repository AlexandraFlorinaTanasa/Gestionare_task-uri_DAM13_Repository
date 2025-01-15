package org.gestionare_taskuri.rest;


import org.gestionare_taskuri.servicii.SprintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

/* REST.RPC API
 * 	http://localhost:8080/scrum/rest/service/workflow/planNewProject/{projectName}/{startDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject/Rest_Project/05-12-2019
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject?projectName={projectName}&startDate={startDate}
 *  http://localhost:8080/scrum/rest/service/workflow/planNewProject?projectName=Rest_Project&startDate=05-12-2019
 *
 * 	http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject/{projectId}/{featureName}/{featureDescription}
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject/34/Feature_1/REST_RPC_Feature
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId={projectId}&featureName={featureName}&featureDescription={featureDescription}
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription=REST_RPC_Feature
 *  http://localhost:8080/scrum/rest/service/workflow/addFeatureToProject?projectId=34&featureName=Feature_1&featureDescription="REST_RPC Feature"
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
@RequestMapping("/rest/service/sprint") // REST.RPC Style
@Transactional
public class SprintServiceREST {
    private static Logger logger = Logger.getLogger(SprintServiceREST.class.getName());

    @Autowired
    private SprintService sprintService;

    @RequestMapping(
            path = "/planNewsprint/{numeSprint}",
            method = RequestMethod.GET)
    @ResponseBody
    // (1) Create new project with default template: projectName, startDate
    public Integer planNewSprint(
            @PathVariable("numeSprint") String numeSprint,
             throws Exception{
        logger.info(">>> Start Procesing: planNewProject /" + numeSprint + "/");


        Integer codSprint = SprintService.planNewSprint(numeSprint);

        logger.info(">>> End Procesing: planNewSprint /" + numeSprint + "/" +  + codSprint);
        return codSprint;
    }

    @RequestMapping(path = "/planNewSprint",  method = RequestMethod.GET)
    @ResponseBody
    public Integer planNewSprintRequestHandler(
            @RequestParam("numeSprint") String numeSprint,
            throws Exception{
        return planNewSprint(numeSprint);
    }
}









