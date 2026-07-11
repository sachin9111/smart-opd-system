package com.smartopd.auth_service.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.smartopd.auth_service.entity.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    boolean existsByMobile(String mobile);

    Optional<User> findByEmail(String email);

    Optional<User> findByMobile(String mobile);

    Optional<User> findByEmailOrMobile(String email, String mobile);
    
	@Query("""
			SELECT u
			FROM User u
			LEFT JOIN FETCH u.userRoles ur
			LEFT JOIN FETCH ur.role
			WHERE u.id = :id
			""")
	Optional<User> findByIdWithRoles(@Param("id") Long id );
	
	@Query("""
		    SELECT DISTINCT u
		    FROM User u
		    LEFT JOIN FETCH u.userRoles ur
		    LEFT JOIN FETCH ur.role
		    WHERE u.email = :username
		       OR u.mobile = :username
		    """)
		Optional<User> findByEmailOrMobileWithRoles(@Param("username") String username);

}
