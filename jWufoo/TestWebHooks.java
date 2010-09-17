package jWufoo;

public class TestWebHooks extends TestBase {

	public void testAddWebHook() {
		String hash = api.getForms().get(0).addWebHook("http://www.apprabbit.com", "gobblygook", false);
        assertEquals("o3p8z5", hash);
	}
	
	public void testGetEntryComments() {
        String addHash = api.getForms().get(1).addWebHook("http://www.ransomsoft.com", "delete", false);
        String deleteHash = api.getForms().get(1).deleteWebHook(addHash);
        assertEquals(deleteHash, addHash);
	}
}
