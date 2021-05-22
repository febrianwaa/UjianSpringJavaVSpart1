package com.jpa.library.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jpa.library.entity.Biodata;
import com.jpa.library.repository.BiodataRepository;

@RestController
@RequestMapping("/biodata")
public class BiodataController {
	@Autowired
	BiodataRepository biodataRepo;

	@GetMapping("/")
	public List <Biodata> getAll(){
		return biodataRepo.findAll();
	}
	
	@PostMapping("/addBiodata")
	public String addBiodata (@RequestBody Biodata biodata) {
		biodataRepo.save(biodata);
		return "Insert Berhasil";
	}
	
	@DeleteMapping("/deleteBiodata/{id}")
	public String deleteBiodata (@PathVariable String id) {
	biodataRepo.deleteById(Long.parseLong(id))	;
	return "Delete Berhasil";
	}

	@PutMapping("/updateBiodata/{id}")
	public String updateBiodata (@PathVariable String id, @RequestBody Biodata biodata) {
		biodata.setId(Long.parseLong(id));
		biodataRepo.save(biodata);
		return "Update Berhasil";
	}
	
//-----------------------------------------------------------------------------------
	
	@GetMapping("/searchby/{type}/{value}")
	public List<Biodata> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
		return biodataRepo.findBySearchBy(type, value);
	}
	
	@GetMapping("/nama/{value}")
	public Biodata getByNama(@PathVariable("value") String value) {
		return biodataRepo.findByNama(value);
	}
	
}

