package com.cw.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cw.domain.Offer;


@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {
	
}
