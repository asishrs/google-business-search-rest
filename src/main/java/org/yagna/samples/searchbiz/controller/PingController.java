package org.yagna.samples.searchbiz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PingController {

	@RequestMapping(value = "/")
	public String findPlacesByLocation() {
		return "I am up :)";
	}

}
