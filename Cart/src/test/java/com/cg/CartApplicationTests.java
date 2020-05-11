package com.cg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.Dto.CartDTO;

import com.cg.Service.CartService;
import com.cg.repository.CartRepository;
import org.junit.jupiter.api.Assertions;

@RunWith(SpringRunner.class)
@SpringBootTest
class CartApplicationTests {
 
@MockBean
	CartRepository cartRepository;
	
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void addItemToCart() throws Exception 
	{
		 RestTemplate restTemplate = new RestTemplate();
	     
		    final String baseUrl = "http://localhost:6501/cart/addtocart";
		    URI uri = new URI(baseUrl);
		 
		CartDTO cart = new CartDTO();
		cart.setProductId("103");
		cart.setUserId("10");
		cart.setQuantity(1);
		  HttpHeaders headers = new HttpHeaders();
	        headers.set("X-COM-PERSIST", "account successfully created ");      
	        HttpEntity<CartDTO> request = new HttpEntity<>(cart, headers);
	        
	        ResponseEntity<String> result = restTemplate.postForEntity(uri, request, String.class);
		    Assert.assertEquals(200, result.getStatusCodeValue());
		    Assert.assertNotNull(cart);

	}
	

	
	@Test
	public void getProductsFromCart() throws Exception {
		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:6501/cart/products/11";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.getForEntity(uri,String.class);
		String cart = result.getBody();
		assertEquals(200, result.getStatusCodeValue());	
		System.out.println(cart);
		assertNotNull(cart);

	}
	
	@Test
public void	removeProductFromCartTest() throws Exception
{

		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:6501/cart/11/101";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.exchange(uri, HttpMethod.DELETE,null,String.class);
		assertEquals(202, result.getStatusCodeValue());
}
	
	
	@Test
	public void updateProductsFromCart() throws URISyntaxException {

/*	RestTemplate restTemplate = new RestTemplate();
    
    final String baseUrl = "http://localhost:6501/cart/addtocart";
    URI uri = new URI(baseUrl);
 
    CartDTO cart=new CartDTO();
    
	HttpHeaders headers = new HttpHeaders();
    headers.set("X-COM-MERGE", "successfully updated");      
    HttpEntity<CartDTO> request = new HttpEntity<>(cart, headers);
  
    HttpEntity<String> result = restTemplate.exchange(uri, HttpMethod.PUT, request, String.class);
    Assert.assertEquals(200, ((ResponseEntity<String>) result).getStatusCodeValue());*/
		
		
	 }

}
