package com.cw.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.cw.domain.Offer;

@Controller
public class HomeController {
	
	public static final String PAGE_INDEX = "index";
	
	@GetMapping("/")
	public String welcome(Map<String, Object> model) {
		model.put("offer", new Offer());		
		return PAGE_INDEX;
	}

	
}
