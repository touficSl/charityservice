package com.service.charity.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.charity.builder.request.DonateRq;
import com.service.charity.builder.request.ProjectListRequest;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.model.Charity;
import com.service.charity.model.Project;
import com.service.charity.model.ProjectImage;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.ProjectImageRepository;
import com.service.charity.reposiroty.ProjectRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

    @Autowired
    private CharityRepository charityRepository;
	
	@Autowired
	ProjectImageRepository projectImageRepository;

	@Value("${spring.file.uploaddir}") 
    private String fileuploaddir;

	
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

	@Override
	public ResponseEntity<?> projectlist(Locale locale, ProjectListRequest request) {
		try {
	        int page = request.getFromrow() / (request.getTorow() - request.getFromrow());
	        int size = request.getTorow() - request.getFromrow();

	        Pageable pageable = PageRequest.of(page, size, Sort.by("dateTime").descending());

	        Page<Project> projectPage;

	        if (request.getTitle() != null && !request.getTitle().isEmpty()) {
	            projectPage = projectRepository.findByTitleContainingIgnoreCase(request.getTitle(), pageable);
	        } else {
	            projectPage = projectRepository.findAll(pageable);
	        }
	        
	        for (Project project : projectPage.getContent()) {
				List<ProjectImage> images = projectImageRepository.findByProjectId(project.getId());
				project.setImages(images);
	        }
			
	        Map<String, Object> response = new HashMap();
	        response.put("projectList", projectPage.getContent());
	        response.put("totalElements", projectPage.getTotalElements());

	        return ResponseEntity.ok(response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
	    }
	}

	@Override
	public ResponseEntity<?> projectdetails(Locale locale, Long id) {
		try {
			Optional<Project> projectopt = projectRepository.findById(id);
			if (!projectopt.isPresent()) 
				return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
			
			Project project = projectopt.get();
			List<ProjectImage> images = projectImageRepository.findByProjectId(project.getId());
			project.setImages(images);
			return ResponseEntity.ok(project);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}



	 
	@Override
	public ResponseEntity<?> downloadfile(String fileName) {
		try {
	        Path filePath = Paths.get(fileuploaddir).resolve(fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());
	
	        if (!resource.exists()) 
				return ResponseEntity.ok(new MessageResponse("resource_not_found", 111));
	
	        String contentType = getFileContentType(resource);
	        return ResponseEntity.ok()
	                .contentType(MediaType.parseMediaType(contentType))
	                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
	                .body(resource);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse("exception_case", 111));
		}
	}
	private String getFileContentType(Resource resource) {
	    try {
	        // Use Java's built-in file type detection
	        return Files.probeContentType(resource.getFile().toPath());
	    } catch (Exception e) {
	        return "application/octet-stream";
	    }
	}
	 
	@Override
	public ResponseEntity<?> returnbase64file(String fileName) {
		try {
	        Path filePath = Paths.get(fileuploaddir).resolve(fileName).normalize();
	        Resource resource = new UrlResource(filePath.toUri());
	
	        if (!resource.exists()) 
				return ResponseEntity.ok(new MessageResponse("resource_not_found", 111));

	        byte[] fileBytes = Files.readAllBytes(filePath);

	        // Encode the byte array into Base64
	        String base64EncodedFile = Base64.getEncoder().encodeToString(fileBytes);

	        // Get MIME type for the file (e.g., image/jpeg, application/pdf)
	        String mimeType = Files.probeContentType(filePath);

//		        // Return the Base64 encoded string in the response body
//		        return ResponseEntity.ok()
//		                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//		                .contentType(MediaType.parseMediaType(mimeType))
//		                .body("data:" + mimeType + ";base64," + base64EncodedFile);

//				return ResponseEntity.ok(new MessageResponse("data:" + mimeType + ";base64," + base64EncodedFile));
			return ResponseEntity.ok("data:" + mimeType + ";base64," + base64EncodedFile);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse("exception_case", 111));
		}
	}
	
}
