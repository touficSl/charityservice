package com.service.charity.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.service.charity.builder.request.ProjectRq;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.IdRs;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.config.Constants;
import com.service.charity.config.Utils;
import com.service.charity.model.Charity;
import com.service.charity.model.Project;
import com.service.charity.model.ProjectImage;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.ProjectImageRepository;
import com.service.charity.reposiroty.ProjectRepository;


@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	ProjectRepository projectRepository;

    @Autowired
    private MessageService messageService;

	@Autowired
	CharityRepository charityRepository;

	@Autowired
	ProjectImageRepository projectImageRepository;

	@Value("${spring.file.uploaddir}") 
    private String fileuploaddir;

	@Value("${spring.system.endpoint}") 
    private String endpoint;

	@Value("${spring.file.accessurl}") 
    private String fileaccessurl;

	@Value("${spring.file.trash.dir}") 
    private String filetrashdir;
	
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

			if (rq.getId() != null && rq.getId() > 0) {
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
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale) + "  > " + e.getMessage(), 111));
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
	

	@Override
	public ResponseEntity<?> fileslist(Locale locale, Long projectid, Users user) {
		try {
			List<ProjectImage> list = projectImageRepository.findByProjectId(projectid);
			if (list == null)
				return ResponseEntity.ok(new ArrayList<ProjectImage>());
				
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}
	

	@Override
	public ResponseEntity<?> uploadfiles(Locale locale, Users user, MultipartFile[] files, Long projectid) {

		try {
	        Project project = null;
            if (projectid == null) 
    			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("invalid_params", locale), 111));

        	Optional<Project> opt = projectRepository.findById(projectid);
        	if (!opt.isPresent())
    			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("invalid_params", locale), 111));
        		
        	project = opt.get();
            

            List<ProjectImage> savedfiles = new ArrayList<ProjectImage>();
            for (MultipartFile file : files)
            	savedfiles.add(savefile(user, file, project));
            	
        	return ResponseEntity.ok(savedfiles);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale) + " > " + e.getMessage(), 111));
		}
    }
	
	private ProjectImage savefile(Users user, MultipartFile file, Project project) throws IOException {

//		try {
	        
            String originalFilename = file.getOriginalFilename();
            String fileName = Utils.generateUniqueString(Constants.PROJECT_KEY) + originalFilename;
            
            // Save file metadata to the database
            ProjectImage metadata = new ProjectImage();
            metadata.setName(fileName);
            metadata.setPath(endpoint + fileaccessurl + fileName);
            metadata.setUrl(metadata.getPath());
            metadata.setDateTime(new Date());
            metadata.setProject(project);
            metadata.setOrder(0);
            metadata = projectImageRepository.save(metadata);
            
            checkAndCreateDirectory(fileuploaddir);
            Path filePath = Paths.get(fileuploaddir, fileName);

            
            // Save file to the server
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
	
	        return metadata;
//		} catch (Exception e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	public static void checkAndCreateDirectory(String filePath){
		try {
	        Path path = Paths.get(filePath);
	
	        if (Files.notExists(path)) {
	            Files.createDirectories(path);
	            System.out.println("Directory created: " + path.toString());
	        }
		}catch (Exception e) {
			e.printStackTrace();
		}
    }

	@Override
	public ResponseEntity<?> removefile(Locale locale, Long id, Users user) {
		try {
			
			Optional<ProjectImage> opt = projectImageRepository.findById(id);
			
			if (opt.isPresent()) {
				ProjectImage pi = opt.get();
				
				movefiletotrash(fileuploaddir + pi.getName(), filetrashdir + pi.getName());
				
				long piid = pi.getProject().getId();
				projectImageRepository.delete(pi);
				IdRs rs = new IdRs();
				rs.setId(piid);
				return ResponseEntity.ok(rs);
			}

			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 222));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}
	
	private void movefiletotrash(String filepath, String trashpath) {

		try {
			Path sourcePath = Paths.get(filepath);

	        Path destinationPath = Paths.get(trashpath);
	        if (!Files.exists(destinationPath.getParent())) {
	            Files.createDirectories(destinationPath.getParent());
	        }

            Files.move(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("File moved successfully!");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public ResponseEntity<?> projectremove(Locale locale, Users user, Long id) {

		Optional<Project> projectopt = projectRepository.findById(id);
		if (!projectopt.isPresent())
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
		Project existingproject = projectopt.get();
		
		List<Charity> list = charityRepository.findByProjectId(id);
		if (list != null && list.size() > 0)
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("charity_exist", locale), 144));

		projectImageRepository.deleteByProjectId(existingproject.getId());
		projectRepository.delete(existingproject);

		return ResponseEntity.ok(new MessageResponse(messageService.getMessage("success", locale)));

	}

}
