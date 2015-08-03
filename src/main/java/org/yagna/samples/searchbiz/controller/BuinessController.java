package org.yagna.samples.searchbiz.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.yagna.samples.searchbiz.config.CacheConfiguration;
import org.yagna.samples.searchbiz.exception.UnableToGetBusinessData;
import org.yagna.samples.searchbiz.model.BusinessData;
import org.yagna.samples.searchbiz.service.BusinessService;

@RestController
public class BuinessController {
	private Logger log = LoggerFactory.getLogger(BuinessController.class); 
	
	@Autowired
	private BusinessService service;

	@RequestMapping(value = "/findByLocation/{lat}/{long}")
	@Cacheable(value=CacheConfiguration.CACHE_BUSINESS_DATA)
	public ResponseEntity<List<BusinessData>> findPlacesByLocation(
			@PathVariable("lat") String latitude,
			@PathVariable("long") String longitude) {
		log.debug("findPlacesByLocation - latitude ='{}', longitude= '{}'", latitude, longitude);
		List<BusinessData> bd = null;
		try {
			bd = this.service.findBusinessByLocation(Double.parseDouble(latitude), Double.parseDouble(longitude));
			log.debug("findPlacesByLocation - result = {}", bd);
		} catch (NumberFormatException e) {
			log.error("findPlacesByLocation - Unable to parse the number latitude ='{}', longitude= '{}'", latitude, longitude, e );
			return new ResponseEntity<List<BusinessData>>(HttpStatus.BAD_REQUEST);
		} catch (UnableToGetBusinessData e) {
			log.error("findPlacesByLocation - Unable to get Busness Data latitude ='{}', longitude= '{}'", latitude, longitude, e );
			return new ResponseEntity<List<BusinessData>>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<List<BusinessData>>(bd,HttpStatus.OK);
	}

}
