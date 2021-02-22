package com.ledger.power.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ledger.power.model.User;
import com.ledger.power.repository.UserRepository;



/**
 * The Class UserServiceImpl.
 */
@Service
public class UserServiceImpl implements UserService {
	
	Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	
	@Autowired
	UserRepository userRepository;

	/**
	 * Count by name.
	 *
	 * @return the long
	 */
	@Override
	public long countByName() {

		return userRepository.count();
	}

	/**
	 * Count by post code.
	 *
	 * @return the long
	 */
	@Override
	public long countByPostCode() {

		return userRepository.count();
	}

	/**
	 * Gets the all name by post code.
	 *
	 * @param postCode the post code
	 * @return the all name by post code
	 */
	@Override
	public List<String> getAllNameByPostCode(Integer postCode) {
		List<User> findByPostCode = userRepository.findByPostCode(postCode);
		List<String> userNames = findByPostCode.stream().map(User::getName).sorted().collect(Collectors.toList());
		;
		return userNames;
	}

	/**
	 * Gets the all users.
	 *
	 * @return the all users
	 */
//	@Override
//	public List<User> getAllUsers() {
//		// TODO Auto-generated method stub
//		List<User> resultSet = new ArrayList<User>();
//		Iterable<User> userIterator = userRepository.findAll();
//		userIterator.forEach(user -> {
//			resultSet.add(user);
//		});
//
//		return resultSet;
//	}

	/**
	 * Save a bunch of user users at once.
	 *
	 * @param users the users
	 */
	@Override
	public List<Long> saveUsers(List<User> users) {

		List<Long> userIds = new ArrayList<Long>();

		userRepository.saveAll(users).forEach(user -> {
			userIds.add(user.getId());

		});

		return userIds;

	}

	/**
	 * Gets the names within post code ranges.
	 *
	 * @param startRange the start range
	 * @param endRange   the end range
	 * @return the names within post code range
	 */
	@Override
	public List<String> getNamesWithinPostCodeRange(int startRange, int endRange) {
		List<User> users = userRepository.findByPostCodeBetween(startRange, endRange);

		logger.debug("******* UnSorted List **************");
		logger.debug("{}", users);

		List<String> userNames = users.stream()
				.sorted(Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER)).map(User::getName)
				.collect(Collectors.toList());

		logger.info("*******Sorted List **************");
		userNames.forEach(user->{logger.debug("{}", user.toString());});
		logger.debug("{}", userNames);
		return userNames;
	}

	/**
	 * Removes the all users.
	 */
	@Override
	public void removeAllUsers() {
		userRepository.deleteAll();
	}

}
