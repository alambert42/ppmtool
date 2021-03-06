package de.andreaslambert.ppmtool.services;

import de.andreaslambert.ppmtool.domain.Project;
import de.andreaslambert.ppmtool.exceptions.ProjectIdException;
import de.andreaslambert.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        try {
            project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
            return projectRepository.save( project );

        } catch (Exception e){
            throw new ProjectIdException("Project ID '"
                    + project.getProjectIdentifier().toUpperCase()
                    + "' already exixsts.");
        }
    }

    public Project findProjectByIdentifier( String projectIdentifier ){
        Project project = projectRepository.findByProjectIdentifier(projectIdentifier.toUpperCase());
        if ( project == null ){
            throw new ProjectIdException("Project ID '"
                    + projectIdentifier.toUpperCase()
                    + "' does not exixst.");
        }
        return project;

    }

    public Iterable<Project> findAllProjects(){
        return projectRepository.findAll();
    }

    public void deleteProjectByIdentifier( String projectId ){
        Project project = projectRepository.findByProjectIdentifier(projectId.toUpperCase());
        if ( project == null ){
            throw new ProjectIdException("Cannot delete project with ID '"
                    + projectId.toUpperCase()
                    + "'. This project does not exist.");
        }
        else {
            projectRepository.delete(project);
        }
    }
}
