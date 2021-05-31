package com.jpa.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jpa.library.entity.Jodoh;

public interface JodohRepository extends JpaRepository<Jodoh, Long>{
	@Query(value = "SELECT *\n"
			+ "from jodoh\n"
			+ "Where (CASE "
			+ "WHEN 'username'=:type THEN username LIKE %:value% "
			+ "WHEN 'nama'=:type THEN nama LIKE %:value% "
			+ "WHEN 'jenisKelamin'=:type THEN jenisKelamin LIKE %:value% "
			+ "WHEN 'phone'=:type THEN phone LIKE %:value% "
			+ "WHEN 'umur'=:type THEN umur LIKE %:value% "
			+ "WHEN 'image'=:type THEN image LIKE %:value% "
			+ "END)",nativeQuery=true)
	List<Jodoh> findBySearchBy(@Param("type")String type,@Param("value")String value);
	
	Jodoh findByNama(String nama);
	
	@Query(value="SELECT * from jodoh where username=?1 and phone=?2", nativeQuery = true)
	Jodoh loginJodoh(String username,String phone);
}
