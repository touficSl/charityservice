package com.service.charity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.service.charity.builder.response.CharityStatisticsDTO;
import com.service.charity.builder.response.MessageResponse;
import com.service.charity.reposiroty.CharityRepository;
import com.service.charity.reposiroty.ProjectRepository;

@Service
public class CharityStatisticsServiceImpl implements CharityStatisticsService {

    @Autowired
    private CharityRepository charityRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public ResponseEntity<?> getCharityStatistics(String username) {
        try {
            // Prepare the response object to return statistics
            CharityStatisticsDTO statistics = new CharityStatisticsDTO();

            // Check if the username is null or empty
            if (username == null || username.trim().isEmpty()) {
                // Retrieve statistics for all charities
                statistics.setTotalCharities(charityRepository.getTotalCharities());
                statistics.setTotalCharitiesAmount(charityRepository.getTotalCharitiesAmount());
                statistics.setTotalCharitiesAmountPerProject(charityRepository.getTotalCharitiesAmountPerProject());
            } else {
                // Retrieve statistics for the specific username
                statistics.setTotalCharities(charityRepository.getTotalCharitiesByUsername(username));
                statistics.setTotalCharitiesAmount(charityRepository.getTotalCharitiesAmountByUsername(username));
                statistics.setTotalCharitiesAmountPerProject(charityRepository.getTotalCharitiesAmountPerProjectByUsername(username));
            }

            // Total projects
            statistics.setTotalProjects(projectRepository.getTotalProjects());

            return new ResponseEntity<>(statistics, HttpStatus.OK);

        } catch (Exception e) {
            // In case of any error, return a meaningful error message
            e.printStackTrace();
            MessageResponse messageResponse = new MessageResponse("Error while fetching charity statistics: " + e.getMessage(), 500);
            return new ResponseEntity<>(messageResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
