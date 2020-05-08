package com.cg.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cg.Dto.ProductsDTO;


@Repository
public interface ProductsRepository extends CrudRepository<ProductsDTO, Integer>{

}
