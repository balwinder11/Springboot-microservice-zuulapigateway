package com.springboot.graphql.geolocationapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mongodb.client.model.geojson.Polygon;
import com.springboot.graphql.geolocationapp.model.PolyLocation;
import com.springboot.graphql.geolocationapp.repo.LocationRepository;

//@Component
@Service
public class LocationSerachService {
	
	@Autowired
	private MongoOperations mongoOperations;
	
	@Autowired
	private LocationRepository locationRepositpory;
	
	
	private Polygon polygon;
	
	public void createLocation(PolyLocation location) {
	 
		
		location.setName(location.getName());
		location.setLocation(location.getLocation());
		
		mongoOperations.save(location);
	}
	
	public List<PolyLocation> findByQuards(@RequestBody String query){
		return locationRepositpory.findByLocationWithin(polygon);
		
	}
	@GetMapping("/findAllLocations")
	public List<PolyLocation> getAllLocation() {
		return (List<PolyLocation>) locationRepositpory.findAll();
	}


}
