package com.service.charity.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.charity.builder.request.ProjectRq;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.config.Constants;
import com.service.charity.config.Utils;
import com.service.charity.model.Charity;
import com.service.charity.model.Project;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.ProjectRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

	@Autowired
	CharityRepository charityRepository;
	
	@Override
	public ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username) {

		try {
			Page<Project> userspage = null;
			long totalrows = projectRepository.count();
			long recordsFiltered = totalrows;

			Specification<Project> spec = JPASpecification.returnProjecttSpecification(search, sortcolumn, descending);
		    Pageable pageable = PageRequest.of(page, size);
		    userspage = projectRepository.findAll(spec, pageable);
		    
			if (search != null && !search.trim().equals("")) {
				List<Project> allusersbysearch = projectRepository.findAll(spec);
				recordsFiltered = allusersbysearch.size();
			} 
	
	        List<Project> list = new ArrayList<Project>(userspage.getContent());
	        
	        DatatableResponse<Project> datatableresponse = new DatatableResponse<Project>(draw, totalrows, recordsFiltered, list);
		       
			return ResponseEntity.ok(datatableresponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	@Override
	public ResponseEntity<?> projectsave(Locale locale, Users user, ProjectRq rq) {
		try {

			if (rq.getId() != null) {
				Optional<Project> projectopt = projectRepository.findById(rq.getId());
				if (!projectopt.isPresent())
					return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
				Project existingproject = projectopt.get();
				rq.setReference(existingproject.getReference());
			} 
			else 
				rq.setReference(generateUniqueProjectReference());
			
			Project Project = new Project(rq);
			Project = projectRepository.save(Project);
			return ResponseEntity.ok(Project);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	private String generateUniqueProjectReference() {
		String uuid = Utils.generateUUID(Constants.PROJECT_PREFIX);
		List<Project> projects = projectRepository.findByReference(uuid);
		if (projects != null && projects.size() > 0)
			generateUniqueProjectReference();
		
		return uuid;
	}

	@Override
	public ResponseEntity<?> charitylist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username, Long projectId) {

		try {
			Page<Charity> pages = null;
			long totalrows = charityRepository.count();
			long recordsFiltered = totalrows;

			Specification<Charity> spec = JPASpecification.returnCharitytSpecification(search, sortcolumn, descending, projectId);
		    Pageable pageable = PageRequest.of(page, size);
		    pages = charityRepository.findAll(spec, pageable);
		    
			if (search != null && !search.trim().equals("")) {
				List<Charity> allusersbysearch = charityRepository.findAll(spec);
				recordsFiltered = allusersbysearch.size();
			} 
	
	        List<Charity> list = new ArrayList<Charity>(pages.getContent());
	        
	        DatatableResponse<Charity> datatableresponse = new DatatableResponse<Charity>(draw, totalrows, recordsFiltered, list);
		       
			return ResponseEntity.ok(datatableresponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}


}
