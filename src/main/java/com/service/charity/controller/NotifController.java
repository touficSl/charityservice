package com.service.charity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.service.charity.service.NotifService;


@RestController
@RequestMapping(path = "/api")
public class NotifController {

	@Autowired
	NotifService notifService;

	@RequestMapping(value = {"/notif", "/{version}/notif"}, 
	method = RequestMethod.POST, headers = "Accept=application/json") 
    public String sendNotification(@RequestParam String token,
                                   @RequestParam String title,
                                   @RequestParam String body) {
        return notifService.sendnotification(token, title, body);
    }
}
