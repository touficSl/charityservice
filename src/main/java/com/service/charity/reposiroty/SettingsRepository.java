package com.service.charity.reposiroty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.service.charity.model.Settings;

@Repository
public interface SettingsRepository extends JpaRepository<Settings, Long> {

	List<com.service.charity.model.Settings> findByIsdefault(boolean isdefault);
}