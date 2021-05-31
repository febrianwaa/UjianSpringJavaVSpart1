package com.jpa.library.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.jpa.library.entity.Jodoh;
import com.jpa.library.repository.JodohRepository;
import com.jpa.library.utility.FileUtility;

@RestController
@RequestMapping("/jodoh")
public class JodohController {
	@Autowired
	JodohRepository jodohRepo;

	@GetMapping("/")
	public List <Jodoh> getAll(){
		return jodohRepo.findAll();
	}
	
//	@PostMapping("/addjodoh")
//	public String addJodoh (@RequestBody Jodoh jodoh) {
//		jodohRepo.save(jodoh);
//		return "Insert Berhasil";
//	}
	
	@DeleteMapping("/deletejodoh/{id}")
	public String deleteJodoh (@PathVariable String id) {
	jodohRepo.deleteById(Long.parseLong(id))	;
	return "Delete Berhasil";
	}

	@PutMapping("/updateJodoh/{id}")
	public String updateJodoh (@PathVariable String id, @RequestBody Jodoh jodoh) {
		jodoh.setId(Long.parseLong(id));
		jodohRepo.save(jodoh);
		return "Update Berhasil";
	}
	
//-----------------------------------------------------------------------------------
	
	@GetMapping("/searchby/{type}/{value}")
	public List<Jodoh> getSearchBy(@PathVariable("type")String type, @PathVariable("value") String value) {
		return jodohRepo.findBySearchBy(type, value);
	}
	
	@GetMapping("/nama/{value}")
	public Jodoh getByNama(@PathVariable("value") String value) {
		return jodohRepo.findByNama(value);
	}
	
	
	@GetMapping("/login/")
	public Jodoh loginJodoh(@RequestParam ("username")String username,@RequestParam("phone")String phone) {
	return jodohRepo.loginJodoh(username, phone);
	}
	
	
	
	
	@PostMapping("/")
	public String addJodoh (@RequestParam(value="file")MultipartFile images, @ModelAttribute(value="data") String dataJson) throws IOException {
		String fileName = StringUtils.cleanPath(images.getOriginalFilename());
		
		String uploadDir = "src/main/java/user-photo/";
		FileUtility.saveFile(uploadDir, fileName, images);
		Jodoh jodoh = new Gson().fromJson(dataJson, Jodoh.class);
		
//		if(jodoh.getKejadian().equalsIgnoreCase("bencana")) {
//			jodoh.setStatus("bencana");
//		}else {
//			jodoh.setStatus("kriminal");
//		}
		jodoh.setImage(fileName);
//		Date date = new Date();
//		jodoh.setJam(String.valueOf(date.getHours())+":"+String.valueOf(date.getMinutes()));
		jodohRepo.save(jodoh);
		return "Berhasil memasukan data";
	}
	
	@GetMapping(value = "/image/{nama}", produces = MediaType.IMAGE_JPEG_VALUE)
	public @ResponseBody byte[] getImageWithMediaType(@PathVariable String nama) throws IOException {
	   final InputStream in = getClass().getResourceAsStream("/user-photo/"+nama);
	   return IOUtils.toByteArray(in);
	}
	
	
	
}
