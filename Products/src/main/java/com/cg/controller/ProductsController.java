package com.cg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.Dto.ProductsDTO;
import com.cg.Service.ProductService;

@RestController
@RequestMapping("/products")
@CrossOrigin("http://localhost:4200")
public class ProductsController {

	
	@Autowired
	private ProductService productService;
	
	@GetMapping(path="/GetList")
	public ResponseEntity<Iterable<ProductsDTO>> getList() {
		 Iterable<ProductsDTO> products = productService.getAllOders();
		 return new ResponseEntity<>(products,HttpStatus.OK); 
	  }

}
