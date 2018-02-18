package com.anthonini.wines.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.anthonini.wines.model.Wine;
import com.anthonini.wines.repository.WineRepository;
import com.anthonini.wines.service.exception.WineNameAlreadyExistsException;

@Service
public class WineService {

	@Autowired
	private WineRepository wineRepository;
	
	public void save(Wine wine) {
		Optional<Wine> existingWine = wineRepository.findByNameIgnoreCase(wine.getName());
		
		if(existingWine.isPresent() && !existingWine.get().equals(wine) ) {
			throw new WineNameAlreadyExistsException(String.format("%s name already exists", wine.getName()));
		}
		wineRepository.save(wine);
	}
}
