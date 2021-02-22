package com.ledger.power.service;

import java.util.List;

import com.ledger.power.model.User;


/**
 * The Interface UserService.
 */
public interface UserService {

	/**
	 * Count by name.
	 *
	 * @return the long
	 */
	long countByName();

	/**
	 * Count by post code.
	 *
	 * @return the long
	 */
	long countByPostCode();

	/**
	 * Gets the all name by post code.
	 *
	 * @param postCode the post code
	 * @return the all name by post code
	 */
	List<String> getAllNameByPostCode(Integer postCode);


	/**
	 * Save users.
	 *
	 * @param users the users
	 * @return users Ids.
	 */
	List<Long> saveUsers(List<User> users);

	/**
	 * Gets the names within post code range.
	 *
	 * @param startRange the start range
	 * @param endRange the end range
	 * @return the names within post code range
	 */
	List<String> getNamesWithinPostCodeRange(int startRange, int endRange);

	/**
	 * Removes the all users.
	 */
	void removeAllUsers();

}
