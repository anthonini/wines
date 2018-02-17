package com.anthonini.wines.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.anthonini.wines.model.Wine;
import com.anthonini.wines.model.WineType;
import com.anthonini.wines.repository.WineFilter;
import com.anthonini.wines.repository.WineRepository;
import com.anthonini.wines.service.WineService;
import com.anthonini.wines.service.exception.WineNameAlreadyExistsException;

@Controller
@RequestMapping("/wines")
public class WineController {
	
	@Autowired
	private WineService wineService;
	
	@Autowired
	private WineRepository wineRepository;

	@GetMapping("/new")
	public ModelAndView form(Wine wine) {
		ModelAndView modelAndView = new ModelAndView("wine/form");
		modelAndView.addObject(wine);
		modelAndView.addObject("wineTypes", WineType.values());
		
		return modelAndView;
	}

	@PostMapping("/new")
	public ModelAndView save(@Validated Wine wine, BindingResult result, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return form(wine);
		}

		try {
			wineService.save(wine);
		}catch (WineNameAlreadyExistsException e) {
			result.rejectValue("name", e.getMessage(), e.getMessage());
			return form(wine);
		}
		
		attributes.addFlashAttribute("message", "Wine successfully saved!");
		return new ModelAndView("redirect:/wines/new");
	}
	
	@GetMapping
	public ModelAndView search(@ModelAttribute WineFilter wineFilter) {
		ModelAndView modelAndView = new ModelAndView("wine/list");
		modelAndView.addObject("wines", wineRepository.findAllByNameContainingIgnoreCase(
				Optional.ofNullable(wineFilter.getName()).orElse("") ));
		
		return modelAndView;
	}
	
	@GetMapping("/{id}")
	public ModelAndView edit(@PathVariable Long id) {
		Wine wine = wineRepository.findOne(id);
		
		return form(wine);
	}
	
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Long id, RedirectAttributes attributes) {
		wineRepository.delete(id);
		
		attributes.addFlashAttribute("message", "Wine successfully removed!");
		return "redirect:/wines";
	}
}
