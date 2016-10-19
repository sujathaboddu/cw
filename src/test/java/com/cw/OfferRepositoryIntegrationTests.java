package com.cw;

import static com.cw.data.OfferDataFixture.getOffer3;
import static org.assertj.core.api.Assertions.assertThat;


import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.cw.domain.Offer;
import com.cw.repository.OfferRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OfferRepositoryIntegrationTests {

	@Autowired
	OfferRepository repository;

	@Test
	public void findAll() {

		List<Offer> offers = this.repository.findAll();
		assertThat(offers.size()).isEqualTo(2);
	}
	
	@Test
	public void findById() {

		Offer offer = this.repository.findOne(new Long(1));
		assertThat(offer).isNotNull();
		assertThat(offer.getId()).isEqualTo(1);
		assertThat(offer.getName()).isEqualTo("Magnetic Board");
	}

	@Test
	@Rollback(value=true)
	public void save() {
		Offer offer = getOffer3();
		
		offer = repository.save(offer);
		assertThat(offer).isNotNull();		
		assertThat(offer.getName()).isEqualTo("Apple");
		assertThat(offer.getDescription()).isEqualTo("Test description");
		assertThat(offer.getPrice()).isEqualTo(new BigDecimal(100));
		assertThat(offer.getCurrency()).isEqualTo("USD");
		assertThat(offer.getCreatedTime()).isNotNull();
	}

	@Test(expected=DataIntegrityViolationException.class)
	@Rollback(value=true)
	public void saveDuplicateOffer() {
		Offer offer = getOffer3();
		offer = repository.save(offer);

		Offer duplicateOffer = new Offer();
		BeanUtils.copyProperties(offer,  duplicateOffer);
		duplicateOffer.setId(null);
		duplicateOffer.setPrice(new BigDecimal(100));
		duplicateOffer = repository.save(duplicateOffer);
		assertThat(duplicateOffer).isNotNull();		
	}
	
}