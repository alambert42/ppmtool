package de.andreaslambert.ppmtool.services;

import de.andreaslambert.ppmtool.domain.Project;
import de.andreaslambert.ppmtool.repositories.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    public Project saveOrUpdateProject(Project project){
        project.setCreated_at(LocalDate.now());
        return projectRepository.save( project );
    }

}
