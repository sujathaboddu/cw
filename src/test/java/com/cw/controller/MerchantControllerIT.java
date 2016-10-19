package com.cw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.cw.data.OfferDataFixture;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class MerchantControllerIT {

	private MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));
	
    @SuppressWarnings("rawtypes")
	private HttpMessageConverter mappingJackson2HttpMessageConverter;
    
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;
    
    @Autowired
    void setConverters(HttpMessageConverter<?>[] converters) {

        this.mappingJackson2HttpMessageConverter = Arrays.asList(converters).stream().filter(
                hmc -> hmc instanceof MappingJackson2HttpMessageConverter).findAny().get();

        Assert.assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();		
	}

	@Test
	public void testGetAllOffers() throws Exception {
		mockMvc.perform(get("/api/offers")				
				.contentType(contentType))				
				.andExpect(content().contentType(contentType))
				.andExpect(content().json("[{\"id\":1,\"name\":\"Magnetic Board\",\"description\":\"Brand New\",\"price\":123.34,\"currency\":\"GBP\"},{\"id\":2,\"name\":\"Laptop\",\"description\":\"Made in America\",\"price\":1223.34,\"currency\":\"GBP\"},{\"id\":3,\"name\":\"Apple\",\"description\":\"Test description\",\"price\":100.00,\"currency\":\"USD\"}]"));	
	}
	
	@Test
	public void testGetOfferById() throws Exception {
		mockMvc.perform(get("/api/offers/2")				
				.contentType(contentType))				
				.andExpect(content().contentType(contentType))				
				.andExpect(status().isOk())
				.andExpect(content().json("{\"id\":2,\"name\":\"Laptop\",\"description\":\"Made in America\",\"price\":1223.34,\"currency\":\"GBP\"}"));	
	}
	
	@Test
	public void testGetOfferById_NotFound() throws Exception {	

		mockMvc.perform(get("/api/offers/4")
				.contentType(contentType))				
				.andExpect(status().isNotFound());					
		
	}
	
	@Test	
	public void testSaveNewOffer() throws Exception {
		
		mockMvc.perform(post("/api/offers")
				.content(this.json(OfferDataFixture.getOffer3()))
				.contentType(contentType))
				.andExpect(status().isCreated())
				.andExpect(header().string("Location", "http://localhost/api/offers/3"));								
								
	}
	
	@Test	
	public void testSaveDuplicateOffer() throws Exception {
		
		mockMvc.perform(post("/api/offers")
				.content(this.json(OfferDataFixture.getOffer3Duplicate()))
				.contentType(contentType))
				.andExpect(status().isConflict())				
				.andExpect(status().reason("Offer with same name already exist!"));
		
	}
	
	@SuppressWarnings("unchecked")
	protected String json(Object o) throws IOException {
        MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }
}