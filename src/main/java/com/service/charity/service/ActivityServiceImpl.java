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

import com.service.charity.builder.request.ActivityRq;
import com.service.charity.builder.response.DatatableResponse;
import com.service.charity.builder.response.IdRs;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.config.Constants;
import com.service.charity.config.Utils;
import com.service.charity.model.Activity;
import com.service.charity.model.ActivityImage;
import com.service.charity.model.Users;
import com.service.charity.reposiroty.ActivityImageRepository;
import com.service.charity.reposiroty.ActivityRepository;


@Service
public class ActivityServiceImpl implements ActivityService {

	@Autowired
	ActivityRepository activityRepository;

    @Autowired
    private MessageService messageService;

	@Autowired
	ActivityImageRepository activityImageRepository;

	@Value("${spring.file.activity.uploaddir}") 
    private String fileuploaddir;

	@Value("${spring.system.endpoint}") 
    private String endpoint;

	@Value("${spring.file.activity.accessurl}") 
    private String fileaccessurl;

	@Value("${spring.file.activity.trash.dir}") 
    private String filetrashdir;
	
	@Override
	public ResponseEntity<?> activitylist(Locale locale, boolean b, Integer page, Integer size, String search,
			String sortcolumn, Boolean descending, Integer draw, String username, Users user) {

		try {
			Page<Activity> userspage = null;
			long totalrows = activityRepository.count();
			long recordsFiltered = totalrows;

			Specification<Activity> spec = null;
			if (Utils.isapiauthorized("showallusers", null, user.getAuthorizedapis()))
				spec = JPASpecification.returnActivitytSpecification(search, sortcolumn, descending);
			else
				spec = JPASpecification.returnCharityActivitytSpecification(search, sortcolumn, descending, user.getUsername());
			
		    Pageable pageable = PageRequest.of(page, size);
		    userspage = activityRepository.findAll(spec, pageable);
		    
			if (search != null && !search.trim().equals("")) {
				List<Activity> allusersbysearch = activityRepository.findAll(spec);
				recordsFiltered = allusersbysearch.size();
			} 
	
	        List<Activity> list = new ArrayList<Activity>(userspage.getContent());
	        
	        DatatableResponse<Activity> datatableresponse = new DatatableResponse<Activity>(draw, totalrows, recordsFiltered, list);
		       
			return ResponseEntity.ok(datatableresponse);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}

	@Override
	public ResponseEntity<?> activitysave(Locale locale, Users user, ActivityRq rq) {
		try {

			Activity existingactivity = null;
			if (rq.getId() != null && rq.getId() > 0) {
				Optional<Activity> activityopt = activityRepository.findById(rq.getId());
				if (!activityopt.isPresent())
					return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
				existingactivity = activityopt.get();
				rq.setReference(existingactivity.getReference());
			} 
			else 
				rq.setReference(generateUniqueActivityReference());
			
			Activity Activity = new Activity(rq);
			Activity = activityRepository.save(Activity);
			return ResponseEntity.ok(Activity);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale) + "  > " + e.getMessage(), 111));
		}
	}

	private String generateUniqueActivityReference() {
		String uuid = Utils.generateUUID(Constants.ACTIVITY_PREFIX);
		List<Activity> activitys = activityRepository.findByReference(uuid);
		if (activitys != null && activitys.size() > 0)
			generateUniqueActivityReference();
		
		return uuid;
	}
	

	@Override
	public ResponseEntity<?> fileslist(Locale locale, Long activityid, Users user) {
		try {
			List<ActivityImage> list = activityImageRepository.findByActivityId(activityid);
			if (list == null)
				return ResponseEntity.ok(new ArrayList<ActivityImage>());
				
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale), 111));
		}
	}
	

	@Override
	public ResponseEntity<?> uploadfiles(Locale locale, Users user, MultipartFile[] files, Long activityid) {

		try {
	        Activity activity = null;
            if (activityid == null) 
    			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("invalid_params", locale), 111));

        	Optional<Activity> opt = activityRepository.findById(activityid);
        	if (!opt.isPresent())
    			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("invalid_params", locale), 111));
        		
        	activity = opt.get();
            

            List<ActivityImage> savedfiles = new ArrayList<ActivityImage>();
            for (MultipartFile file : files)
            	savedfiles.add(savefile(user, file, activity));
            	
        	return ResponseEntity.ok(savedfiles);
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("exception_case", locale) + " > " + e.getMessage(), 111));
		}
    }
	
	private ActivityImage savefile(Users user, MultipartFile file, Activity activity) throws IOException {

//		try {
	        
            String originalFilename = file.getOriginalFilename();
            String fileName = Utils.generateUniqueString(Constants.PROJECT_KEY) + originalFilename;
            
            // Save file metadata to the database
            ActivityImage metadata = new ActivityImage();
            metadata.setName(fileName);
            metadata.setPath(endpoint + fileaccessurl + fileName);
            metadata.setUrl(metadata.getPath());
            metadata.setDateTime(new Date());
            metadata.setActivity(activity);
            metadata.setOrder(0);
            metadata = activityImageRepository.save(metadata);
            
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
			
			Optional<ActivityImage> opt = activityImageRepository.findById(id);
			
			if (opt.isPresent()) {
				ActivityImage pi = opt.get();
				
				movefiletotrash(fileuploaddir + pi.getName(), filetrashdir + pi.getName());
				
				long piid = pi.getActivity().getId();
				activityImageRepository.delete(pi);
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
	public ResponseEntity<?> activityremove(Locale locale, Users user, Long id) {

		Optional<Activity> activityopt = activityRepository.findById(id);
		if (!activityopt.isPresent())
			return ResponseEntity.ok(new MessageResponse(messageService.getMessage("not_found", locale), 122));
		Activity existingactivity = activityopt.get();
		
		activityImageRepository.deleteByActivityId(existingactivity.getId());
		activityRepository.delete(existingactivity);

		return ResponseEntity.ok(new MessageResponse(messageService.getMessage("success", locale)));

	}

}
