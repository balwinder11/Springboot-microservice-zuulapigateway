package com.springboot.graphql.geolocationapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.geo.Point;
import org.springframework.data.geo.Polygon;

public class LocationEntry{
	
		// TODO Auto-generated constructor stub
	@Id
    private String id;
	private String longitude;
	private String latitude;
	
	public String getLongitude() {
		return longitude;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	

	

}
