package com.fdm.pmsuibackend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fdm.pmscommon.dto.outgoing.PositionResponse;


@Service
public class PositionCalculationService {

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;


    // Scheduled method to make a REST request every 10 seconds (example)
    @Scheduled(fixedRate = 1000) // Every 1 seconds
    public void callPositionCalculator() {
        String url = "http://localhost:8082/api/positions/download"; // Replace with actual API endpoint

        try {
//            this part needs to be changed
            // Make the REST call (example get request)
            ResponseEntity<PositionResponse> response = restTemplate.getForEntity(url, PositionResponse.class);
            // check is it rep every sec
            // Log the response
            if (response.getStatusCode().is2xxSuccessful()) {
                //check with Tony on what data structure he is expecting. and also the topic name
                System.out.println("Received position data: " + response.getBody().getPositionData());
                simpMessagingTemplate.convertAndSend("/topic/positionData", response.getBody());
                // Send to UI then they sub and respond
            } else {
                System.out.println("Failed to get position: " + response.getStatusCode());
            }
        } catch (Exception e) {
            System.err.println("Error during the REST request: " + e.getMessage());
        }
    }
}



