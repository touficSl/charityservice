package com.service.charity.service;

import com.service.charity.model.Settings;

public interface SettingsService {

	Settings returndefaultSettings();
	
	String getlogskey();
}