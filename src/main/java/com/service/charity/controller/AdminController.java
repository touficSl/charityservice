package com.service.charity.controller;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.service.charity.builder.request.ProjectRq;
import com.service.charity.model.Users;
import com.service.charity.service.AdminService;
import com.service.charity.service.CharityStatisticsService;
import com.service.charity.service.GoogleRecaptchaService;
import com.service.charity.service.UserService;

import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping(path = "/api/admin")
public class AdminController {

	@Autowired
	GoogleRecaptchaService googleRecaptchaService;

	@Autowired
	AdminService adminService;

	@Autowired
	UserService userService;

    @Autowired
    private CharityStatisticsService charityStatisticsService;
	
	@RequestMapping(value = "/project/list", method = RequestMethod.POST)
	public ResponseEntity<?> projectlist(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
								  @RequestHeader(name = "page", required = false, defaultValue = "0") Integer page,
								  @RequestHeader(name = "size", required = false, defaultValue = "0") Integer size,
								  @RequestHeader(name = "search", required = false) String search,
								  @RequestHeader(name = "sortcolumn", required = false) String sortcolumn,
								  @RequestHeader(name = "descending", required = false, defaultValue = "false") Boolean descending,
						          @RequestHeader(name = "draw", required = false, defaultValue = "1") Integer draw,
								  @RequestHeader(name = "username", required = true) String username) {
		
		return adminService.projectlist(locale, false, page, size, search, sortcolumn, descending, draw, username);
	}
	
	@RequestMapping(value = {"/project/save", "/{version}/project/save"}, 
			method = RequestMethod.POST, headers = "Accept=application/json") 
	public ResponseEntity<?> projectsave(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			HttpServletRequest request, 
			@PathVariable(name = "version", required = false) String version,
			@RequestBody ProjectRq rq) throws UnsupportedEncodingException { 

		Users user = (Users) request.getAttribute("user");
		return adminService.projectsave(locale, user, rq);
	} 
	
	@RequestMapping(value = "/charity/list", method = RequestMethod.POST)
	public ResponseEntity<?> charitylist(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			  					  @RequestHeader(name = "projectid", required = true, defaultValue = "0") Long projectId,
								  @RequestHeader(name = "page", required = false, defaultValue = "0") Integer page,
								  @RequestHeader(name = "size", required = false, defaultValue = "0") Integer size,
								  @RequestHeader(name = "search", required = false) String search,
								  @RequestHeader(name = "sortcolumn", required = false) String sortcolumn,
								  @RequestHeader(name = "descending", required = false, defaultValue = "false") Boolean descending,
						          @RequestHeader(name = "draw", required = false, defaultValue = "1") Integer draw,
								  @RequestHeader(name = "username", required = true) String username) {
		
		return adminService.charitylist(locale, false, page, size, search, sortcolumn, descending, draw, username, projectId);
	}

	@RequestMapping(value = "/charity/statistics", method = RequestMethod.POST)
    public ResponseEntity<?> getCharityStatistics(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @RequestHeader(name = "username", required = true) String username) {
        // Call the service to get the charity statistics
        return charityStatisticsService.getCharityStatistics(username);
    }
}
