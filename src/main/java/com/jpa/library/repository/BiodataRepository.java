package com.jpa.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jpa.library.entity.Biodata;

public interface BiodataRepository extends JpaRepository<Biodata, Long>{
//	public List<Buku> findAll();
	@Query(value = "SELECT *\n"
			+ "from biodata\n"
			+ "Where (CASE "
			+ "WHEN 'nama'=:type THEN nama LIKE %:value% "
			+ "WHEN 'email'=:type THEN email LIKE %:value% "
			+ "WHEN 'phone'=:type THEN phone LIKE %:value% "
			+ "WHEN 'address'=:type THEN address LIKE %:value% "
			+ "END)",nativeQuery=true)
	List<Biodata> findBySearchBy(@Param("type")String type,@Param("value")String value);
	
	Biodata findByNama(String nama);
}
