package com.service.charity.service;

import org.springframework.data.jpa.domain.Specification;

import com.service.charity.model.Charity;
import com.service.charity.model.Project;

import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;

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

	            // Apply sorting if requested
	            if (sortColumn != null && !sortColumn.isEmpty()) {
	                if (Boolean.TRUE.equals(descending)) {
	                    query.orderBy(criteriaBuilder.desc(root.get(sortColumn)));
	                } else {
	                    query.orderBy(criteriaBuilder.asc(root.get(sortColumn)));
	                }
	            }

	            // Join with Charity to filter by username
	            Join<Object, Object> charityJoin = root.join("charities", JoinType.INNER);
	            Predicate predicate = criteriaBuilder.equal(charityJoin.get("username"), username);

	            // Apply search filters if present
	            if (search != null && !search.trim().isEmpty()) {
	                String pattern = "%" + search.trim() + "%";

	                Predicate searchPredicate = criteriaBuilder.or(
	                    criteriaBuilder.like(root.get("title"), pattern),
	                    criteriaBuilder.like(root.get("description"), pattern),
	                    criteriaBuilder.like(root.get("reference"), pattern),
	                    criteriaBuilder.like(root.get("status"), pattern),
	                    criteriaBuilder.like(root.get("type"), pattern)
	                );

	                predicate = criteriaBuilder.and(predicate, searchPredicate);
	            }

	            return predicate;
	        };
	}

	public static Specification<Charity> returnCharitytSpecification(String search, String sortColumn,
			Boolean descending, Long projectId) {
	    return (root, query, criteriaBuilder) -> {
	        // Handle sorting safely
	        if (sortColumn != null && !sortColumn.isEmpty()) {
	            if (Boolean.TRUE.equals(descending)) {
	                query.orderBy(criteriaBuilder.desc(root.get(sortColumn)));
	            } else {
	                query.orderBy(criteriaBuilder.asc(root.get(sortColumn)));
	            }
	        }

	        // Start with true predicate
	        Predicate predicate = criteriaBuilder.conjunction();

	        // Filter by projectId if provided
	        if (projectId != null) {
	            predicate = criteriaBuilder.and(
	                predicate,
	                criteriaBuilder.equal(root.get("project").get("id"), projectId)
	            );
	        }

	        // Apply search filters if search string is provided
	        if (search != null && !search.isEmpty()) {
	            String searchPattern = search + "%";

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