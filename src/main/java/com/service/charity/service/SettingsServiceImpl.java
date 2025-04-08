package com.service.charity.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.service.charity.model.Settings;
import com.service.charity.reposiroty.SettingsRepository;

@Service
public class SettingsServiceImpl implements SettingsService {

	@Value("${spring.admin.key}") 
	private String adminkey;

	@Value("${spring.logs.api.key}") 
	private String logsapikey;
	
	@Autowired
	private SettingsRepository settingsRepository;

	@Override
	public Settings returndefaultSettings() {
		List<Settings> settings = settingsRepository.findByIsdefault(true);
		
		if (settings == null || settings.size() == 0 || settings.get(0) == null) {
			Settings builtinsettings = new Settings(adminkey, true);
			return builtinsettings;
		}

		return settings.get(0);
	}

	@Override
	public String getlogskey() {
		return logsapikey;
	}
}