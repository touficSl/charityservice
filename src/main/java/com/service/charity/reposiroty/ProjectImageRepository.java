package com.service.charity.reposiroty;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.charity.model.ProjectImage;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

}