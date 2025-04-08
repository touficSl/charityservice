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

import com.service.charity.builder.request.DonateRq;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.model.Charity;
import com.service.charity.model.Project;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.ProjectRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CharityRepository charityRepository;
	
	@Override
	public ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username) {

		try {
			Page<Project> pages = null;
			long totalrows = projectRepository.count();
			long recordsFiltered = totalrows;

			Specification<Project> spec = JPASpecification.returnCharityProjecttSpecification(search, sortcolumn, descending, username);
		    Pageable pageable = PageRequest.of(page, size);
		    pages = projectRepository.findAll(spec, pageable);
		    
			if (search != null && !search.trim().equals("")) {
				List<Project> allusersbysearch = projectRepository.findAll(spec);
				recordsFiltered = allusersbysearch.size();
			} 
	
	        List<Project> list = new ArrayList<Project>(pages.getContent());
	        
	        DatatableResponse<Project> datatableresponse = new DatatableResponse<Project>(draw, totalrows, recordsFiltered, list);
		       
			return ResponseEntity.ok(datatableresponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	@Override
	public ResponseEntity<?> donate(Locale locale, Users user, DonateRq rq) {
		try {
			Optional<Project> projectopt = projectRepository.findById(rq.getProjectid());
			if (!projectopt.isPresent()) 
				return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
			
			rq.setUsername(user.getUsername());
			Project project = projectopt.get();
			
			Charity charity = new Charity(rq, project);
			charity = charityRepository.save(charity);
			return ResponseEntity.ok(charity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}


}
