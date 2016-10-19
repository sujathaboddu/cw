package com.cw.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.cw.domain.Offer;
import com.cw.dto.OfferDTO;
import com.cw.exception.DuplicateOfferException;
import com.cw.repository.OfferRepository;

@RestController
@RequestMapping("/api/offers")
public class MerchantController {

	@Autowired
	OfferRepository offerRepository;
	
	@Autowired
    private ModelMapper modelMapper;
	

	@RequestMapping(value = "", method = RequestMethod.GET)
	public @ResponseBody List<OfferDTO> listAllOffers() {
	    List<Offer> offers = offerRepository.findAll(); 
	    return offers.stream()
	            .map(offer -> convertToDto(offer)).collect(Collectors.toList());
	}
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public @ResponseBody ResponseEntity<OfferDTO> getOfferById(@PathVariable final Long id) {
		
		Offer offer = offerRepository.findOne(id);
		if(offer != null)
			return new ResponseEntity<OfferDTO>(convertToDto(offer), HttpStatus.OK);
		else
			return new ResponseEntity<OfferDTO>(HttpStatus.NOT_FOUND);
		
	}
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createNewOffer(@RequestBody OfferDTO offerDTO, UriComponentsBuilder ucBuilder) {

		try {
			Offer offer = offerRepository.save(convertToEntity(offerDTO));
			
			HttpHeaders headers = new HttpHeaders();
		    headers.setLocation(ucBuilder.path("/api/offers/{id}").buildAndExpand(offer.getId()).toUri());
		    return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			
		} catch (DataIntegrityViolationException e) {
			throw new DuplicateOfferException();
		}
	}

	private OfferDTO convertToDto(Offer offer) {
		return modelMapper.map(offer, OfferDTO.class);			    
	}
	
	private Offer convertToEntity(OfferDTO offerDTO) {
		return modelMapper.map(offerDTO, Offer.class);	   
	}

	public void setModelMapper(ModelMapper modelMapper) {
		this.modelMapper = modelMapper;				
	}
	
	
}
