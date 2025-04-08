package com.service.charity.reposiroty;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.charity.model.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

	Page<Project> findAll(Specification<Project> spec,
			Pageable pageable);

	List<Project> findAll(Specification<Project> spec);

	List<Project> findByReference(String uuid);
	
	@Query("SELECT COUNT(c) FROM Project c")
	Long getTotalProjects();
}