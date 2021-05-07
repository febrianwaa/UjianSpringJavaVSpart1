package com.jpa.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jpa.library.entity.Buku;

public interface BukuRepository extends JpaRepository<Buku, Long>{
//	public List<Buku> findAll();
	@Query(value = "select * from buku where judul_buku LIKE %?1%", nativeQuery=true)
	List<Buku> findByJudulBuku(String judulBuku);
	
	
}
