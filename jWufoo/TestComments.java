package jWufoo;

import java.util.ArrayList;

public class TestComments extends TestBase {

	public void testGetFormComments() {
		ArrayList<Comment> comments = api.getForms().get(0).getComments();
		assertEquals(3, comments.size());
	}
	
	public void testGetEntryComments() {
		ArrayList<Comment> comments = api.getForms().get(0).getEntries().get(2).getComments();
		assertEquals(2, comments.size());
	}
}
