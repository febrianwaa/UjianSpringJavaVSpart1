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

import com.jpa.library.entity.Buku;
import com.jpa.library.repository.BukuRepository;

@RestController
@RequestMapping("/buku")
public class BukuController {
	@Autowired
	BukuRepository bukuRepo;

	@GetMapping("/")
	public List <Buku> getAll(){
		return bukuRepo.findAll();
	}
	
	@PostMapping("/addBuku")
	public String addBuku (@RequestBody Buku buku) {
		bukuRepo.save(buku);
		return "Insert Berhasil";
	}
	
	@DeleteMapping("/deleteBuku/{id}")
	public String deleteBuku (@PathVariable String id) {
	bukuRepo.deleteById(Long.parseLong(id))	;
	return "Delete Berhasil";
	}

	@PutMapping("/updateBuku/{id}")
	public String updateBuku (@PathVariable int id, @RequestBody Buku buku) {
		buku.setId(id);
		bukuRepo.save(buku);
		return "Update Berhasil";
	}
	
	@GetMapping ("/{title}")
	public List<Buku> getAllByJudulBuku(@PathVariable String title){
		return bukuRepo.findByJudulBuku(title);
	}
	
}

