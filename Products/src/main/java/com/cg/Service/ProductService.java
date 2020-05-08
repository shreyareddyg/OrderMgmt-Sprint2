package com.cg.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.ProductsDTO;
import com.cg.repository.ProductsRepository;


@Service
@Transactional
public class ProductService {

	 @Autowired
	    private ProductsRepository productsRepository;

	public Iterable<ProductsDTO> getAllOders() {
		return productsRepository.findAll();
	}

}
