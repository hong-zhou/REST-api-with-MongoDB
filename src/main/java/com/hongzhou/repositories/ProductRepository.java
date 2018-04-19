package com.hongzhou.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.hongzhou.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, String> {
	
	@Override
	Optional<Product> findById(String id);
	
	@Override
	void delete(Product deleted);

}
