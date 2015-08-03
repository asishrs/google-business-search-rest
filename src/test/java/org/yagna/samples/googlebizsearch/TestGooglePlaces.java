package org.yagna.samples.googlebizsearch;

import java.util.List;

import org.junit.Test;

import se.walkercrou.places.GooglePlaces;
import se.walkercrou.places.Param;
import se.walkercrou.places.Place;

public class TestGooglePlaces {

	GooglePlaces client = new GooglePlaces("AIzaSyAcGqQBsjV7YCDMiMAMv7y5rGI5jM0_lZU");

	
	@Test
	public void testFindPlacesByLocationAndType() throws Exception {
		 List<Place> places = client.getPlacesByRadar(33.7044371,-112.1131205, 2000,
				 GooglePlaces.MAXIMUM_RESULTS, Param.name("types").value("cafe%7Cfood"));
		for(Place place : places){
			Place p = place.getDetails(); // sends a GET request for more details
		    // Just an example of the amount of information at your disposal:
		    System.out.println("Name: " + p.getName());
		    System.out.println("Phone: " + p.getPhoneNumber());
		    System.out.println("Website: " + p.getWebsite());
		    System.out.println("Always Opened: " + p.isAlwaysOpened());
		    System.out.println("Status: " + p.getStatus());
		    System.out.println("Google Place URL: " + p.getGoogleUrl());
		    System.out.println("Price: " + p.getPrice());
		    System.out.println("Address: " + p.getAddress());
		    System.out.println("Vicinity: " + p.getVicinity());
		    System.out.println("Reviews: " + p.getReviews().size());
		    System.out.println("Hours:\n " + p.getHours());
		    System.out.println("Exact location: [" + p.getLatitude() + "," + p.getLongitude() + "]");
		}
		
	}

}
