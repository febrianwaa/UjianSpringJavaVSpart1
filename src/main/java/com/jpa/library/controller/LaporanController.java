package com.jpa.library.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jpa.library.entity.Laporan;
import com.jpa.library.repository.LaporanRepository;

import com.jpa.library.utility.FileUtility;

@RestController
@RequestMapping("/laporan")
public class LaporanController {
	@Autowired
	LaporanRepository laporanRepo;
	
//	private final String UPLOAD_DIR = "./src/main/resources/static/uploads/";

	@GetMapping("/")
	public List <Laporan> getAll(){
		return laporanRepo.findAll();
	}
	
	
	@PostMapping("/addLaporan")
	public String addLaporan (@RequestBody Laporan laporan) {
		laporanRepo.save(laporan);
		return "Insert Berhasil";
	}
	
	
//	@PostMapping("/pertanyaan/add")
//	public String addLaporan(@RequestParam(value = "file")MultipartFile file,@ModelAttribute Pertanyaan pertanyaan, Model model) throws IOException {
//		 String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		   pertanyaan.setStatusGambar(fileName);
//	         
//	        String uploadDir = "user-photos/" ;
//	
//	        FileUtility.saveFile(uploadDir, fileName, file);
//	 
//        pertanyaan.setStatusGambar("/"+uploadDir + fileName);
//		// buat penampung data mahasiswa di halaman htmlnya
//	
//			
//		return "add_pertanyaan";
//	}
	
	
	@DeleteMapping("/deleteLaporan/{id}")
	public String deleteLaporan (@PathVariable String id) {
	laporanRepo.deleteById(Long.parseLong(id))	;
	return "Delete Berhasil";
	}

	
	@PutMapping("/updateLaporan/{id}")
	public String updateLaporan (@PathVariable String id, @RequestBody Laporan laporan) {
		laporan.setId(Long.parseLong(id));
		laporanRepo.save(laporan);
		return "Update Berhasil";
	}
	
//-----------------------------------------------------------------------------------
	
//	@GetMapping("/searchby/{type}/{value}")
//	public List<Laporan> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
//		return laporanRepo.findBySearchBy(type, value);
//	}
	
//	@GetMapping("/nama/{value}")
//	public Laporan getByNama(@PathVariable("value") String value) {
//		return laporanRepo.findByNama(value);
//	}
	
}

