package com.songzio.songzio.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.songzio.songzio.model.User_Role;

@Repository
public interface User_RoleRepository extends JpaRepository<User_Role, Integer>{
	
	@Query(value = "SELECT * FROM user_role_seq WHERE role_name = :user_role_name", nativeQuery = true)
	public Optional<User_Role> findByUser_RoleName(@Param("user_role_name")String user_role_name);

}
