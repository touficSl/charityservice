package com.service.charity.reposiroty;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.service.charity.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

	Page<Activity> findAll(Specification<Activity> spec,
			Pageable pageable);

	List<Activity> findAll(Specification<Activity> spec);

	List<Activity> findByReference(String uuid);
	
	@Query("SELECT COUNT(c) FROM Project c")
	Long getTotalProjects();
	
	Page<Activity> findByTitleContainingIgnoreCase(String title, Pageable pageable);

}