package com.cg.Order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.VerificationCollector;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import com.cg.Dto.Cart;
import com.cg.Dto.OrderProductMap;
import com.cg.Dto.Orders;
import com.cg.Service.OrderService;
import com.cg.repository.CartRepository;
import com.cg.repository.OrderRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
class OrderApplicationTests {

	
	@Rule
	public VerificationCollector verificationCollector = MockitoJUnit.collector();
	@Mock
	    private OrderRepository orderRepository;
	@InjectMocks
	private OrderService orderService;

	@Mock
    private CartRepository cartRepository;
	
	
	
	@Before
	public void setup(){
	MockitoAnnotations.initMocks(this);
	}
	
	
	@Test
	public void removeOrderTest() throws Exception
	{
		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:6502/order/907da6e0-d16a-41a5-8cda-1ef885f13cb2";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.exchange(uri, HttpMethod.DELETE,null,String.class);
		assertEquals(202, result.getStatusCodeValue());
	}

		
	@Test
	public void getAllOrders() throws Exception
	{
		RestTemplate rest=new RestTemplate();
		final String baseUrl ="http://localhost:6502/order/all";
		URI uri = new URI(baseUrl);
		ResponseEntity<String> result=rest.getForEntity(uri,String.class);
		String cart = result.getBody();
		assertEquals(200, result.getStatusCodeValue());	
		System.out.println(cart);
		assertNotNull(cart);
	}

	}

