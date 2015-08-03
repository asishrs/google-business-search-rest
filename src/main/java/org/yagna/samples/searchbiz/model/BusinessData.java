package org.yagna.samples.searchbiz.model;

public class BusinessData {
	private String name;
	private String address;
	private boolean target;
	private double distance;
	private String points;
	
	public BusinessData(String name, String address, boolean target, double distance, String points) {
		super();
		this.name = name;
		this.address = address;
		this.target = target;
		this.distance = distance;
		this.points = points;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean isTarget() {
		return target;
	}
	
	public void setTarget(boolean target) {
		this.target = target;
	}
	
	public double getDistance() {
		return distance;
	}
	public void setDistance(double distance) {
		this.distance = distance;
	}
	
	public String getPoints() {
		return points;
	}
	
	public void setPoints(String points) {
		this.points = points;
	}
	
	@Override
	public String toString() {
		return "PlaceData [name=" + name + ", address=" + address + ", target=" + target + ", distance=" + distance
				+ ", points=" + points + "]";
	}
	
}
