package com.ledger.power.service;

import static org.assertj.core.api.Assertions.assertThatNoException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.ledger.power.model.User;

@SpringBootTest
class UserServiceImplTest {

//	@Autowired
//	UserRepository userRepository;
	@Autowired
	UserService userService;

	private static List<User> userInputList;

	@BeforeAll
	static void setUp() throws Exception {
		userInputList = new ArrayList<User>();
		User user1 = new User("Sumith", 6112);
		User user2 = new User("Alphy", 6000);
		User user3 = new User("Zean", 6060);
		User user4 = new User("Beffy", 6260);
		User user5 = new User("Betty", 6160);
		User user6 = new User("Rob", 6160);
		User user7 = new User("Steve", 6112);
		User user8 = new User("Robert", 6260);
		User user9 = new User("Robot", 6260);
		userInputList.add(user1);
		userInputList.add(user2);
		userInputList.add(user3);
		userInputList.add(user4);
		userInputList.add(user5);
		userInputList.add(user6);
		userInputList.add(user7);
		userInputList.add(user8);
		userInputList.add(user9);

	}

	@BeforeEach
	void setUpUserDataBase() {
		userService.saveUsers(userInputList);
	}

	@AfterEach
	void clearUserDataBase() {
		userService.removeAllUsers();
	}

	@AfterAll
	static void tearDown() throws Exception {
		userInputList.clear();
		userInputList = null;
	}

	@Test
	void testGetAllUsersInSetup() {

		assertEquals(9, userService.countByName());
		assertEquals(9, userService.countByPostCode());
	}

	@Test
	@DisplayName("Fetch user with in post code range 6000 - 6300")
	void testGetNamesWithinPostCodeFullRange() {
		// when : There is 7 users in the database
		// then: Pull out the user with post code range 6000 - 6159
		List<String> namesWithinPostCodeRange = userService.getNamesWithinPostCodeRange(6000, 6300);
		assertThatNoException();
		assertNotNull(namesWithinPostCodeRange);
		assertFalse(namesWithinPostCodeRange.isEmpty());
		assertEquals(9, namesWithinPostCodeRange.size());

	}

	@Test
	void testGetNamesWithInvalidPostCodeRange() {
		// when : There is 7 users in the database
		// then: Pull out the user with post code range -1 & -10
		List<String> namesWithinPostCodeRange = userService.getNamesWithinPostCodeRange(-1, -10);
		assertThatNoException();
		assertNotNull(namesWithinPostCodeRange);
		assertTrue(namesWithinPostCodeRange.isEmpty());
		assertEquals(0, namesWithinPostCodeRange.size());

	}

	@Test
	@DisplayName("Fetch user with in post code range 6000 - 6159")
	void testGetNamesWithinPostCodeRange() {
		// when : There is 7 users in the database
		// then: Pull out the user with post code range 6000 - 6159
		List<String> namesWithinPostCodeRange = userService.getNamesWithinPostCodeRange(6000, 6159);
		assertThatNoException();
		assertNotNull(namesWithinPostCodeRange);
		assertFalse(namesWithinPostCodeRange.isEmpty());
		assertEquals(4, namesWithinPostCodeRange.size());

	}

	@Test
	@DisplayName("Fetch Sorted user with in post code range 6000 - 6159")
	void testGetSortedNamesWithinPostCodeRange() {
		// when : There is 7 users in the database

		// then: Pull out the user with post code range 6000 - 6159
		final String EXPECTED_USER_AT_FIRST = "Alphy";
		final String EXPECTED_USER_AT_LAST = "Zean";
		List<String> namesWithinPostCodeRange = userService.getNamesWithinPostCodeRange(6000, 6159);
		assertThatNoException();
		assertNotNull(namesWithinPostCodeRange);
		assertFalse(namesWithinPostCodeRange.isEmpty());
		assertEquals(4, namesWithinPostCodeRange.size());
		String firstUserName = namesWithinPostCodeRange.get(0);
		String LastUserName = namesWithinPostCodeRange.get(3);
		assertEquals(EXPECTED_USER_AT_FIRST, firstUserName);
		assertEquals(EXPECTED_USER_AT_LAST, LastUserName);

	}
}
