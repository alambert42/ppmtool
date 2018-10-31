package de.andreaslambert.ppmtool.web;

import de.andreaslambert.ppmtool.domain.Project;
import de.andreaslambert.ppmtool.services.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

    @Autowired
    private ProjectService projectService;

    @PostMapping("")
    public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            return new ResponseEntity<String>("Invalid object", HttpStatus.BAD_REQUEST);
        }
        Project persistentProject = projectService.saveOrUpdateProject(project);
        return new ResponseEntity<>(persistentProject, HttpStatus.CREATED);
    }
}
