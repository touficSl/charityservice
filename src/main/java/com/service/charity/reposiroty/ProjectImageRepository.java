package com.service.charity.reposiroty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.charity.model.ProjectImage;

@Repository
public interface ProjectImageRepository extends JpaRepository<ProjectImage, Long> {

	@Query("SELECT c FROM ProjectImage c WHERE c.project.id = :projectid")
	List<ProjectImage> findByProjectId(@Param("projectid") Long projectid);
}