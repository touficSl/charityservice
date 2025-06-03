package com.service.charity.service;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.service.charity.builder.request.ProjectRq;
import com.service.charity.model.Users;

public interface AdminService {

	ResponseEntity<?> projectlist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username, Users user);

	ResponseEntity<?> projectsave(Locale locale, Users user, ProjectRq rq);

	ResponseEntity<?> charitylist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username, Long projectId);
	

	ResponseEntity<?> fileslist(Locale locale, Long projectid, Users user);

	ResponseEntity<?> uploadfiles(Locale locale, Users user, MultipartFile[] files, Long projectid);

	ResponseEntity<?> removefile(Locale locale, Long id, Users user);

	ResponseEntity<?> projectremove(Locale locale, Users user, Long id);

}
