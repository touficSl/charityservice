package com.service.charity.service;

import org.springframework.data.jpa.domain.Specification;

import com.service.charity.model.Charity;
import com.service.charity.model.Project;

import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Subquery;

public class JPASpecification {

    public static Specification<Project> returnProjecttSpecification(String search, String sortColumn, boolean descending) {
        return (root, query, criteriaBuilder) -> {
        	
            if (descending) 
                query.orderBy(criteriaBuilder.desc(root.get(sortColumn)));
            else 
                query.orderBy(criteriaBuilder.asc(root.get(sortColumn)));
            
            if (search == null || search.isEmpty()) {
                return criteriaBuilder.conjunction(); // No filtering
            }
            String searchPattern = search + "%";
            return criteriaBuilder.or(
                criteriaBuilder.like(root.get("budget"), searchPattern),
                criteriaBuilder.like(root.get("title"), searchPattern),
                criteriaBuilder.like(root.get("description"), searchPattern),
                criteriaBuilder.like(root.get("status"), searchPattern),
                criteriaBuilder.like(root.get("username"), searchPattern),
                criteriaBuilder.like(root.get("reference"), searchPattern)
            );
        };
    }

	public static Specification<Project> returnCharityProjecttSpecification(String search, String sortColumn,
			Boolean descending, String username) {
		 return (root, query, criteriaBuilder) -> {
		        query.distinct(true);

		        // Join Charity entity to filter projects that have charities by the given username
		        Subquery<Long> subquery = query.subquery(Long.class);
		        Root<Charity> charityRoot = subquery.from(Charity.class);
		        subquery.select(charityRoot.get("project").get("id"))
		                .where(criteriaBuilder.equal(charityRoot.get("username"), username));

		        // Main predicate: project.id IN (select project ids from Charity where username = :username)
		        Predicate predicate = root.get("id").in(subquery);

		        // Apply search filters if present
		        if (search != null && !search.trim().isEmpty()) {
		            String pattern = "%" + search.trim() + "%";

		            Predicate searchPredicate = criteriaBuilder.or(
		                criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern.toLowerCase()),
		                criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern.toLowerCase()),
		                criteriaBuilder.like(criteriaBuilder.lower(root.get("reference")), pattern.toLowerCase()),
		                criteriaBuilder.like(criteriaBuilder.lower(root.get("status")), pattern.toLowerCase()),
		                criteriaBuilder.like(criteriaBuilder.lower(root.get("type")), pattern.toLowerCase())
		            );

		            predicate = criteriaBuilder.and(predicate, searchPredicate);
		        }

		        // Apply sorting
		        if (sortColumn != null && !sortColumn.isEmpty()) {
		            if (Boolean.TRUE.equals(descending)) {
		                query.orderBy(criteriaBuilder.desc(root.get(sortColumn)));
		            } else {
		                query.orderBy(criteriaBuilder.asc(root.get(sortColumn)));
		            }
		        }

		        return predicate;
	        };
	}

	public static Specification<Charity> returnCharitytSpecification(String search, String sortColumn,
			Boolean descending, Long projectId, String currentusername) {
	    return (root, query, criteriaBuilder) -> {
	    	 // Handle sorting safely
	        if (sortColumn != null && !sortColumn.isEmpty()) {
	            if (Boolean.TRUE.equals(descending)) {
	                query.orderBy(criteriaBuilder.desc(root.get(sortColumn)));
	            } else {
	                query.orderBy(criteriaBuilder.asc(root.get(sortColumn)));
	            }
	        }

	        // Start with a true predicate
	        Predicate predicate = criteriaBuilder.conjunction();

	        // Filter by projectId if provided
	        if (projectId != null) {
	            predicate = criteriaBuilder.and(
	                predicate,
	                criteriaBuilder.equal(root.get("project").get("id"), projectId)
	            );
	        }

	        // Filter by current username if provided
	        if (currentusername != null) {
	            predicate = criteriaBuilder.and(
	                predicate,
	                criteriaBuilder.equal(root.get("username"), currentusername)
	            );
	        }

	        // Apply search filters if provided
	        if (search != null && !search.trim().isEmpty()) {
	            String searchPattern = "%" + search.trim() + "%";

	            Predicate searchPredicate = criteriaBuilder.or(
	                criteriaBuilder.like(root.get("username"), searchPattern),
	                criteriaBuilder.like(root.get("paymentReference"), searchPattern),
	                criteriaBuilder.like(root.get("paymentStatus"), searchPattern),
	                criteriaBuilder.like(root.get("amount").as(String.class), searchPattern)
	            );

	            predicate = criteriaBuilder.and(predicate, searchPredicate);
	        }

	        return predicate;
	    };
	}
}