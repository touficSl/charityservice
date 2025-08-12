package com.service.charity.reposiroty;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.service.charity.model.ActivityImage;

import jakarta.transaction.Transactional;

@Repository
public interface ActivityImageRepository extends JpaRepository<ActivityImage, Long> {

	@Query("SELECT c FROM ActivityImage c WHERE c.activity.id = :activityid")
	List<ActivityImage> findByActivityId(@Param("activityid") Long activityid);

    @Modifying
    @Transactional
	@Query("DELETE FROM ActivityImage c WHERE c.activity.id = :activityid")
    void deleteByActivityId(@Param("activityid") Long activityid);
}