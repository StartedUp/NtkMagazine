package org.naamtamilar.magazine.resource;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

public interface Resource<T> {
	@GetMapping
	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	ResponseEntity<Collection<T>> findAll();
	
	@GetMapping("{id}")
	ResponseEntity<T> findById(@PathVariable Long id);
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<T> save(@RequestBody T t);
	
	@PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<T> update(@RequestBody T t);
	
	@DeleteMapping("{id}")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	ResponseEntity<String> deleteById(@PathVariable Long id);
	
	@GetMapping("/invalid")
	ResponseEntity<String> invalid();
}
