package com.cw.dto;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.modelmapper.ModelMapper;

import com.cw.data.OfferDataFixture;
import com.cw.domain.Offer;

public class OfferDTOConverterTest {

		 
	    private ModelMapper modelMapper = new ModelMapper();
	 
	    @Test
	    public void testConvertEntityToDTO() {	       
	        	 
	    	Offer offer = OfferDataFixture.getOffer1();
	    	
	        OfferDTO offerDto = modelMapper.map(offer, OfferDTO.class);
	        assertEquals(offer.getId(),offerDto.getId());
	        assertEquals(offer.getName(), offerDto.getName());
	        assertEquals(offer.getDescription(), offerDto.getDescription());
	        assertEquals(offer.getCurrency(), offerDto.getCurrency());
	        assertEquals(offer.getPrice(), offerDto.getPrice());	        
	    }
	 
	    @Test
	    public void testConvertDTOtoEntity() {
	                
	    	OfferDTO offerDTO = OfferDataFixture.getOfferDTO();
	 
	        Offer offer = modelMapper.map(offerDTO, Offer.class);
	        assertEquals(offerDTO.getId(),offer.getId());
	        assertEquals(offerDTO.getName(), offer.getName());
	        assertEquals(offerDTO.getDescription(), offer.getDescription());
	        assertEquals(offerDTO.getCurrency(), offer.getCurrency());
	        assertEquals(offerDTO.getPrice(), offer.getPrice());	        
	    }
	 
	    

}
