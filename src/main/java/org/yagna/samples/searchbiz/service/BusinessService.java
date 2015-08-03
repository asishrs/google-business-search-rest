package org.yagna.samples.searchbiz.service;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yagna.samples.searchbiz.constants.Constants;
import org.yagna.samples.searchbiz.constants.PlacesType;
import org.yagna.samples.searchbiz.controller.BuinessController;
import org.yagna.samples.searchbiz.exception.UnableToGetBusinessData;
import org.yagna.samples.searchbiz.model.BusinessData;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

@Service
public class BusinessService {
	
	private Logger log = LoggerFactory.getLogger(BuinessController.class); 

	@Autowired
	private GooglePlaces googlePlaces;

	public List<BusinessData> findBusinessByLocation(final double latitude, final double longitude)
			throws UnableToGetBusinessData {
		return findBusinessByLocation(latitude, longitude, Constants.DEFAULT_RADIUS, Constants.DEAFULT_API_DELIMITER);
	}

	public List<BusinessData> findBusinessByLocation(final double latitude, final double longitude, final double radius)
			throws UnableToGetBusinessData {
		return findBusinessByLocation(latitude, longitude, radius, Constants.DEAFULT_API_DELIMITER);
	}

	public List<BusinessData> findBusinessByLocation(final double latitude, final double longitude,
			final double radius, final char delimiter) throws UnableToGetBusinessData {
		log.debug("findBusinessByLocation - latitude = '{}', longitude = '{}', radius = '{}', delimiter = '{}",
				latitude,longitude, radius, delimiter);
		List<BusinessData> businessData = new ArrayList<BusinessData>();
		try {
			List<Place> places = googlePlaces.getPlacesByRadar(
					latitude,
					longitude,
					radius,
					GooglePlaces.MAXIMUM_RESULTS,
					Param.name(Constants.PARAM_TYPES).value(
							URLEncoder.encode(getTypesWithDelimiter(delimiter), Constants.DEFAULT_ENCODE_TYPE)));
			for (Place place : places) {
				Place p = place.getDetails(); // sends a GET request for more details
				double distance = calculateDistanceFromSearchLocation(latitude, longitude, p.getLatitude(), p.getLongitude());
				businessData.add(new BusinessData(p.getName(), p.getAddress(), (distance == 0.0) , distance, "NA"));
			}
		} catch (UnsupportedEncodingException e) {
			throw new UnableToGetBusinessData();
		}
		return businessData;
	}

	private String getTypesWithDelimiter(final char delimiter) {
		List<PlacesType> placeTypes = Arrays.asList(PlacesType.values());
		List<String> types = new ArrayList<String>();
		for (PlacesType pt : placeTypes) {
			types.add(pt.toString());
		}
		return StringUtils.join(types, delimiter);
	}

	private double calculateDistanceFromSearchLocation(double lat1, double lon1, double lat2, double lon2) {
		double theta = lon1 - lon2;
		double dist = Math.sin(convertsDecimalDegreesToRadians(lat1)) * Math.sin(convertsDecimalDegreesToRadians(lat2)) + Math.cos(convertsDecimalDegreesToRadians(lat1))
				* Math.cos(convertsDecimalDegreesToRadians(lat2)) * Math.cos(convertsDecimalDegreesToRadians(theta));
		dist = Math.acos(dist);
		dist = convertsRadiansToDecimalDegrees(dist);
		dist = dist * 60 * 1.1515;
		return (dist);
	}

	private double convertsDecimalDegreesToRadians(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private double convertsRadiansToDecimalDegrees(double rad) {
		return (rad * 180.0 / Math.PI);
	}
}
