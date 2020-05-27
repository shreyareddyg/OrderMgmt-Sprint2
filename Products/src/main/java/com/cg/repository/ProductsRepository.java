package com.cg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.Dto.Products;


@Repository
public interface ProductsRepository extends CrudRepository<Products, Integer>{

}
