package com.service.charity.reposiroty;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.charity.builder.response.ProjectCharityAmount;
import com.service.charity.model.Charity;

@Repository
public interface CharityRepository extends JpaRepository<Charity, Long> {

	Page<Charity> findAll(Specification<Charity> spec,
			Pageable pageable);

	List<Charity> findAll(Specification<Charity> spec);
	
	@Query("SELECT COUNT(c) FROM Charity c")
	Long getTotalCharities();
	
	@Query("SELECT SUM(c.amount) FROM Charity c")
	BigDecimal getTotalCharitiesAmount();
	
	@Query("SELECT c.project.id AS projectId, SUM(c.amount) AS totalAmount FROM Charity c GROUP BY c.project.id")
	List<ProjectCharityAmount> getTotalCharitiesAmountPerProject();
	
	// For specific username
	
	@Query("SELECT COUNT(c) FROM Charity c WHERE c.username = :username")
	Long getTotalCharitiesByUsername(@Param("username") String username);

	@Query("SELECT SUM(c.amount) FROM Charity c WHERE c.username = :username")
	BigDecimal getTotalCharitiesAmountByUsername(@Param("username") String username);

	@Query("SELECT c.project AS project, SUM(c.amount) AS totalAmount FROM Charity c WHERE c.username = :username GROUP BY c.project")
	List<ProjectCharityAmount> getTotalCharitiesAmountPerProjectByUsername(@Param("username") String username);

}