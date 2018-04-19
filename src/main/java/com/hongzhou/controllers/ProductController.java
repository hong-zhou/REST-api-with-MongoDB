package com.hongzhou.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hongzhou.entity.Product;
import com.hongzhou.repositories.ProductRepository;

@RestController
public class ProductController {
	
	@Autowired
	ProductRepository productRepository;
	
	@RequestMapping(method=RequestMethod.GET, value="/products")
	public Iterable<Product> getAllProduct(){
		return productRepository.findAll();
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/products" )
	public String save(@RequestBody Product product) {
		productRepository.save(product);
		return product.getId();
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/products/{id}")
	public Product show(@PathVariable String id) {
		return productRepository.findById(id).orElse(null);
	}

	@RequestMapping(method=RequestMethod.PUT, value="/products/{id}")
	public Product update(@PathVariable String id, @RequestBody Product product) {
		Product prod = productRepository.findById(id).orElse(null);
		
		if( prod.getProdName() != null) {
			prod.setProdName(product.getProdName());
		}
		if(prod.getProdDesc() != null) {
			prod.setProdDesc(product.getProdDesc());
		}
		if(prod.getProdPrice() != null) {
			prod.setProdPrice(product.getProdPrice());
		}
		if(prod.getProdImage() != null) {
			prod.setProdImage(product.getProdImage());
		}
		
		productRepository.save(prod);
		
		return prod;
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/products/{id}")
	public String delete(@PathVariable String id) {
		Product product = productRepository.findById(id).orElse(null);
		if (product != null) {		
			productRepository.delete(product);
		}
		
		return "Product deleted with ID: " + product.getId();
 	}
}
