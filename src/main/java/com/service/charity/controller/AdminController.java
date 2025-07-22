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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.service.charity.builder.request.ActivityRq;
import com.service.charity.builder.request.ProjectRq;
import com.service.charity.model.Users;
import com.service.charity.service.ActivityService;
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
	ActivityService activityService;

	@Autowired
	UserService userService;

    @Autowired
    private CharityStatisticsService charityStatisticsService;
	
	@RequestMapping(value = "/project/list", method = RequestMethod.POST)
	public ResponseEntity<?> projectlist(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
									HttpServletRequest request, 
								  @RequestHeader(name = "page", required = false, defaultValue = "0") Integer page,
								  @RequestHeader(name = "size", required = false, defaultValue = "0") Integer size,
								  @RequestHeader(name = "search", required = false) String search,
								  @RequestHeader(name = "sortcolumn", required = false) String sortcolumn,
								  @RequestHeader(name = "descending", required = false, defaultValue = "false") Boolean descending,
						          @RequestHeader(name = "draw", required = false, defaultValue = "1") Integer draw,
								  @RequestHeader(name = "username", required = true) String username) {

		Users user = (Users) request.getAttribute("user");
		return adminService.projectlist(locale, false, page, size, search, sortcolumn, descending, draw, username, user);
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
	
	@RequestMapping(value = {"/project/remove", "/{version}/project/save"}, 
			method = RequestMethod.POST, headers = "Accept=application/json") 
	public ResponseEntity<?> projectremove(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			HttpServletRequest request, 
			@PathVariable(name = "version", required = false) String version,
		  @RequestHeader(name = "id", required = true) Long id) throws UnsupportedEncodingException { 

		Users user = (Users) request.getAttribute("user");
		return adminService.projectremove(locale, user, id);
	} 
	
	@RequestMapping(value = "/charity/list", method = RequestMethod.POST)
	public ResponseEntity<?> charitylist(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
									HttpServletRequest request, 
			  					  @RequestHeader(name = "projectid", required = true, defaultValue = "0") Long projectId,
								  @RequestHeader(name = "page", required = false, defaultValue = "0") Integer page,
								  @RequestHeader(name = "size", required = false, defaultValue = "0") Integer size,
								  @RequestHeader(name = "search", required = false) String search,
								  @RequestHeader(name = "sortcolumn", required = false) String sortcolumn,
								  @RequestHeader(name = "descending", required = false, defaultValue = "false") Boolean descending,
						          @RequestHeader(name = "draw", required = false, defaultValue = "1") Integer draw,
								  @RequestHeader(name = "username", required = true) String username) {

		Users user = (Users) request.getAttribute("user");
		return adminService.charitylist(locale, false, page, size, search, sortcolumn, descending, draw, username, projectId, user);
	}

	@RequestMapping(value = "/charity/statistics", method = RequestMethod.POST)
    public ResponseEntity<?> getCharityStatistics(
            @RequestHeader(name = "Accept-Language", required = false) Locale locale,
            @RequestHeader(name = "username", required = true) String username) {
        // Call the service to get the charity statistics
        return charityStatisticsService.getCharityStatistics(username);
    }
	
	

	@RequestMapping(value = "/project/files/list", method = RequestMethod.POST)
	public ResponseEntity<?> fileslist(HttpServletRequest request,
											  @RequestHeader(name = "Accept-Language", required = false) Locale locale,
									          @RequestHeader(name = "projectid", required = true) Long projectid) {

        Users user = (Users) request.getAttribute("user");
		return adminService.fileslist(locale, projectid, user);
	}
	

	@RequestMapping(value = "/project/files/upload", method = RequestMethod.POST)
    public ResponseEntity<?> uploadfiles(HttpServletRequest request, 
			  							@RequestHeader(name = "Accept-Language", required = false) Locale locale,
										@RequestHeader(name = "projectid", required = true) Long projectid,
										@RequestParam("file") MultipartFile[] files) {
 
        Users user = (Users) request.getAttribute("user");
        return adminService.uploadfiles(locale, user, files, projectid);
    }

	@RequestMapping(value = "/project/file/remove/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> fileremove(HttpServletRequest request,
											  @RequestHeader(name = "Accept-Language", required = false) Locale locale,
											  @PathVariable(name = "id", required = true) Long id) {

        Users user = (Users) request.getAttribute("user");
		return adminService.removefile(locale, id, user);
	}
	
	
	
	
	
	/**
	 * Activity APIs
	 */
	

	@RequestMapping(value = "/activity/list", method = RequestMethod.POST)
	public ResponseEntity<?> activitylist(@RequestHeader(name = "Accept-Language", required = false) Locale locale,
									HttpServletRequest request, 
								  @RequestHeader(name = "page", required = false, defaultValue = "0") Integer page,
								  @RequestHeader(name = "size", required = false, defaultValue = "0") Integer size,
								  @RequestHeader(name = "search", required = false) String search,
								  @RequestHeader(name = "sortcolumn", required = false) String sortcolumn,
								  @RequestHeader(name = "descending", required = false, defaultValue = "false") Boolean descending,
						          @RequestHeader(name = "draw", required = false, defaultValue = "1") Integer draw,
								  @RequestHeader(name = "username", required = true) String username) {

		Users user = (Users) request.getAttribute("user");
		return activityService.activitylist(locale, false, page, size, search, sortcolumn, descending, draw, username, user);
	}
	
	@RequestMapping(value = {"/activity/save", "/{version}/activity/save"}, 
			method = RequestMethod.POST, headers = "Accept=application/json") 
	public ResponseEntity<?> activitysave(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			HttpServletRequest request, 
			@PathVariable(name = "version", required = false) String version,
			@RequestBody ActivityRq rq) throws UnsupportedEncodingException { 

		Users user = (Users) request.getAttribute("user");
		return activityService.activitysave(locale, user, rq);
	} 
	
	@RequestMapping(value = {"/activity/remove", "/{version}/activity/save"}, 
			method = RequestMethod.POST, headers = "Accept=application/json") 
	public ResponseEntity<?> activityremove(
			@RequestHeader(name = "Accept-Language", required = false) Locale locale,
			HttpServletRequest request, 
			@PathVariable(name = "version", required = false) String version,
		  @RequestHeader(name = "id", required = true) Long id) throws UnsupportedEncodingException { 

		Users user = (Users) request.getAttribute("user");
		return activityService.activityremove(locale, user, id);
	} 

	@RequestMapping(value = "/activity/files/list", method = RequestMethod.POST)
	public ResponseEntity<?> activityfileslist(HttpServletRequest request,
											  @RequestHeader(name = "Accept-Language", required = false) Locale locale,
									          @RequestHeader(name = "activityid", required = true) Long activityid) {

        Users user = (Users) request.getAttribute("user");
		return activityService.fileslist(locale, activityid, user);
	}
	

	@RequestMapping(value = "/activity/files/upload", method = RequestMethod.POST)
    public ResponseEntity<?> activityuploadfiles(HttpServletRequest request, 
			  							@RequestHeader(name = "Accept-Language", required = false) Locale locale,
										@RequestHeader(name = "activityid", required = true) Long activityid,
										@RequestParam("file") MultipartFile[] files) {
 
        Users user = (Users) request.getAttribute("user");
        return activityService.uploadfiles(locale, user, files, activityid);
    }

	@RequestMapping(value = "/activity/file/remove/{id}", method = RequestMethod.POST)
	public ResponseEntity<?> activityfileremove(HttpServletRequest request,
											  @RequestHeader(name = "Accept-Language", required = false) Locale locale,
											  @PathVariable(name = "id", required = true) Long id) {

        Users user = (Users) request.getAttribute("user");
		return activityService.removefile(locale, id, user);
	}
}
