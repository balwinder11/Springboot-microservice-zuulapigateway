package com.springboot.graphql.geolocationapp.repo;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.mongodb.client.model.geojson.Polygon;
import com.springboot.graphql.geolocationapp.model.PolyLocation;

public interface LocationRepository extends MongoRepository<PolyLocation,String>{
	
	List<PolyLocation> findByLocationWithin(Polygon polygon);

}
