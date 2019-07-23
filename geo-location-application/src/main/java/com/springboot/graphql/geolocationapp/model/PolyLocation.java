package com.springboot.graphql.geolocationapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJson;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
@Document(collection ="locations")
public class PolyLocation {
	@Id
	private String  id;
	private String name;
	private GeoJson location;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public GeoJson getLocation() {
		return location;
	}
	public void setLocation(GeoJson geoJson) {
		this.location = geoJson;
	}
	

}
