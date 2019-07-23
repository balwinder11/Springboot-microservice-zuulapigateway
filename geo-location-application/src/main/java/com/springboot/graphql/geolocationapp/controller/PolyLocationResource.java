package com.springboot.graphql.geolocationapp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.graphql.geolocationapp.model.PolyLocation;
import com.springboot.graphql.geolocationapp.service.LocationSerachService;

import graphql.ExecutionResult;
import graphql.GraphQL;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;

@RestController
public class PolyLocationResource {

	@Autowired
	private LocationSerachService locationService;

	@Value("classpath:model.graphqls")
	private Resource schemaResource;

	private GraphQL graphQL;
	
	

	@PostConstruct
	public void loadSchema() throws IOException {
		File schemaFile = schemaResource.getFile();
		TypeDefinitionRegistry registry = new SchemaParser().parse(schemaFile);
		RuntimeWiring wiring = buildWiring();
		GraphQLSchema schema = new SchemaGenerator().makeExecutableSchema(registry, wiring);
		graphQL = GraphQL.newGraphQL(schema).build();

	}

	private RuntimeWiring buildWiring() {
		DataFetcher<List<PolyLocation>> fetcher1 = data -> {
			return (List<PolyLocation>) locationService.getAllLocation();
		};

		DataFetcher<PolyLocation> fetcher2 = data -> {
			return (PolyLocation) locationService.findByQuards(data.getArgument("polyQuards"));
		};
		return RuntimeWiring.newRuntimeWiring().type("Query", typeWriting -> typeWriting
				.dataFetcher("getAllLocation", fetcher1).dataFetcher("findByLatLong", fetcher2)).build();
	}

	@PostMapping("/addLocation")
	public String addLocations(@RequestBody PolyLocation location) {

		locationService.createLocation(location);
		return "location inserted " + location.getId();
	}

	@GetMapping("/getAllLocations")
	public List<PolyLocation> getAllLocations() {
		return locationService.getAllLocation();

	}
	@PostMapping("/getAll")
	public ResponseEntity<Object> getAllLoc(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

	@GetMapping("/getByQuardPoints")
	public ResponseEntity<Object> getLocationByQuards(@RequestBody String query) {
		ExecutionResult result = graphQL.execute(query);
		return new ResponseEntity<Object>(result, HttpStatus.OK);
	}

}
