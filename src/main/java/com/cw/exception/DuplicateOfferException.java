package com.cw.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.CONFLICT, reason="Offer with same name already exist!")
 public class DuplicateOfferException extends RuntimeException {

	private static final long serialVersionUID = 1L;
 }