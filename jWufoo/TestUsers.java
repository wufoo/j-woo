package jWufoo;

import java.util.ArrayList;

public class TestUsers extends TestBase {

	public void testGetUsers() {
		ArrayList<User> users = api.getUsers();
		assertEquals(1, users.size());
		User user = users.get(0);
		assertEquals("apprabbit", user.getUser());
		assertTrue(user.getIsAccountOwner());
		assertEquals("megamark16@gmail.com", user.getEmail());
	}
	
	public void testGetUserImages() {
		ArrayList<User> users = api.getUsers();
		User user = users.get(0);
		assertEquals("https://wufoo.com/images/avatars/big/animal_10.png", user.getImageUrlBig());
		assertEquals("https://wufoo.com/images/avatars/small/animal_10.png", user.getImageUrlSmall());
	}
}
