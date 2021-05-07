package com.jpa.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.jpa.library.entity.Biodata;

public interface BiodataRepository extends JpaRepository<Biodata, Long>{
//	public List<Buku> findAll();
	@Query(value = "select * from biodata where nama LIKE %?1%", nativeQuery=true)
	List<Biodata> findByNama(String nama);
	
	@Query(value = "select * from biodata where email LIKE %?1%", nativeQuery=true)
	List<Biodata> findByEmail(String email);
	
	@Query(value = "select * from biodata where phone LIKE %?1%", nativeQuery=true)
	List<Biodata> findByPhone(int phone);
	
	@Query(value = "select * from biodata where address LIKE %?1%", nativeQuery=true)
	List<Biodata> findByAddress(String address);
}
