package com.ledger.power.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ledger.power.model.User;

public interface UserRepository extends CrudRepository<User, Long> {

	List<User> findByPostCode(Integer postCode);

	@Query("select u from User u where u.postCode <= :startRange and u.postCode >= :endRange")
	List<User> findNamesWithinPostCodeRange(@Param("startRange") int startRange, @Param("endRange") int endRange);

	List<User> findByPostCodeBetween(int start, int end);

}
