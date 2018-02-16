package com.anthonini.wines.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anthonini.wines.model.Wine;

public interface WineRepository extends JpaRepository<Wine, Long> {

	public Optional<Wine> findByNameIgnoreCase(String name);
}
