package com.service.charity.service;

import java.util.Locale;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.service.charity.builder.request.ActivityRq;
import com.service.charity.model.Users;

public interface ActivityService {

	ResponseEntity<?> activitylist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username, Users user);

	ResponseEntity<?> activitysave(Locale locale, Users user, ActivityRq rq);
	

	ResponseEntity<?> fileslist(Locale locale, Long activityid, Users user);

	ResponseEntity<?> uploadfiles(Locale locale, Users user, MultipartFile[] files, Long activityid);

	ResponseEntity<?> removefile(Locale locale, Long id, Users user);

	ResponseEntity<?> activityremove(Locale locale, Users user, Long id);

}
