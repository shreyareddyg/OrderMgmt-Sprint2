package com.cg.Dto;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity 
@Table(name = "products_data")
public class ProductsDTO {

	@Id
	private String productid;
	private String	 productUin;
	private	 boolean  productispresent;
	public String getProductid() {
		return productid;
	}
	public void setProductid(String productid) {
		this.productid = productid;
	}
	public String getProductUin() {
		return productUin;
	}
	public void setProductUin(String productUin) {
		this.productUin = productUin;
	}
	public boolean isProductispresent() {
		return productispresent;
	}
	public void setProductispresent(boolean productispresent) {
		this.productispresent = productispresent;
	}

	
	
}
