package com.cw.data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.cw.domain.Offer;
import com.cw.dto.OfferDTO;

public class OfferDataFixture {

	public static Offer getOffer2() {
		Offer offer = new Offer();
		offer.setId(Long.valueOf(2));
		offer.setName("Laptop");
		offer.setDescription("Made in America");
		offer.setPrice(new BigDecimal(1223.34));
		offer.setCurrency("GBP");
		offer.setCreatedTime(new Date());
		offer.setVat(new BigDecimal(20.00));

		return offer;
	}

	public static Offer getOffer1() {
		Offer offer = new Offer();
		offer.setId(Long.valueOf(1));
		offer.setName("Magnetic Board");
		offer.setDescription("Brand New");
		offer.setPrice(new BigDecimal(123.34));
		offer.setCurrency("GBP");
		offer.setCreatedTime(new Date());
		offer.setVat(new BigDecimal(20.00));

		return offer;
	}

	public static Offer getOffer3() {
		Offer offer = new Offer();
		offer.setName("Apple");
		offer.setDescription("Test description");
		offer.setPrice(new BigDecimal(100));
		offer.setCurrency("USD");

		return offer;
	}
	
	public static Offer getOffer3WithId() {
		Offer offer = new Offer();
		offer.setId(Long.valueOf(3));
		offer.setName("Apple");
		offer.setDescription("Test description");
		offer.setPrice(new BigDecimal(100));
		offer.setCurrency("USD");

		return offer;
	}

	public static OfferDTO getOfferDTO() {
		OfferDTO offerDTO = new OfferDTO();
		offerDTO.setId(Long.valueOf(1));
		offerDTO.setName("ABC");
		offerDTO.setDescription("Test offer");
		offerDTO.setPrice(new BigDecimal(100));
		offerDTO.setCurrency("USD");
		return offerDTO;
	}

	public static List<Offer> getOffers() {

		List<Offer> list = new ArrayList<>();
		list.add(getOffer1());
		list.add(getOffer2());
		list.add(getOffer3WithId());
		
		return list;

	}

	public static Offer getOffer3Duplicate() {
		Offer offer = new Offer();
		offer.setName("Apple");
		offer.setDescription("Duplicate offer description");
		offer.setPrice(new BigDecimal(200));
		offer.setCurrency("GBP");				
		
		return offer;
	}
}
