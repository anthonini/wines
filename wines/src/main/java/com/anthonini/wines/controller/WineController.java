package com.anthonini.wines.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wines")
public class WineController {

	@GetMapping("/new")
	public ModelAndView form() {
		ModelAndView modelAndView = new ModelAndView("wine/form");
		
		return modelAndView;
	}
	
}
