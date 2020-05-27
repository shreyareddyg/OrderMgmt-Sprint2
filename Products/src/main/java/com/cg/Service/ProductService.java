package com.cg.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cg.Dto.Products;
import com.cg.repository.ProductsRepository;


@Service
@Transactional
public class ProductService implements IProductService{

	 @Autowired
	    private ProductsRepository productsRepository;

	public Iterable<Products> getAllOders() {
		return productsRepository.findAll();
	}

}
