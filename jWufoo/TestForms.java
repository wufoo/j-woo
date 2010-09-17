package jWufoo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
import java.text.SimpleDateFormat;
import org.json.JSONException;

public class TestForms extends TestBase {

	public void testGetFormFields() throws IOException, JSONException, ParseException {
		ArrayList<Field> fields = api.getForms().get(0).getFields();
		assertEquals(11, fields.size());
		Field nameField = api.getForms().get(0).getField("Customer");
		assertEquals("Field1", nameField.ID);
		Form form = api.getForms().get(0);
		assertEquals("Robert Smith", form.getEntries().get(0).getFields().get(nameField.ID));
	}

	public void testFormEntriesAndFieldsMatch() {
		Form form = api.getForms().get(0);
		ArrayList<Field> fields = form.getFields();
		Entry entry = form.getEntries().get(0);
		for (Field field : fields) {
			System.out.println(field.ID);
			if (!field.ID.contains("LastUpdated")){
				assertTrue(entry.Fields.containsKey(field.ID));				
			}
		}
	}

	public void testGetForms() {
		ArrayList<Form> forms = api.getForms();
		assertEquals(2, forms.size());
		assertEquals("Contact Form", forms.get(0).getName());
		assertEquals("This is my form. Please fill it out. It's awesome!", forms.get(0).getDescription());
		assertTrue(forms.get(0).getIsPublic());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss", Locale.US);
		try {
			assertEquals(sdf.parse("2000-01-01 12:00:00"), forms.get(0).getStartDate());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals("Contact Form", forms.get(0).getName());
	}

	public void testGetFormsWithoutCredentials() {
		jWufooAPI bad_api = new jWufooAPI("","");
		ArrayList<Form> forms = bad_api.getForms();
		assertEquals(0, forms.size());
	}
	
	public void testGetLinkUrl() {
		Form form = api.getForms().get(1);
		assertEquals("http://apprabbit.wufoo.com/forms/m7x3k1/", form.getLinkUrl());
	}
	
	public void testGetEmbedUrl() {
		Form form = api.getForms().get(1);
		String embedUrl = "<script type=\"text/javascript\">var host = ((\"https:\" == document.location.protocol) ? \"https://secure.\" : \"http://\");document.write(unescape(\"%3Cscript src='\" + host + \"wufoo.com/scripts/embed/form.js' type='text/javascript'%3E%3C/script%3E\"));</script>\n<script type=\"text/javascript\">\nvar m7x3k1 = new WufooForm();\nm7x3k1.initialize({\n'userName':'apprabbit',\n'formHash':'m7x3k1', \n'autoResize':true,\n'height':'607'});\nm7x3k1.display();\n</script>";
		assertEquals(embedUrl, form.getEmbedUrl());
	}
	
	public void testGetIframeUrl() {
		Form form = api.getForms().get(1);
		String embedUrl = "<iframe height=\"607\" allowTransparency=\"true\" frameborder=\"0\" scrolling=\"no\" style=\"width:100%;border:none\"  src=\"http://apprabbit.wufoo.com/embed/m7x3k1/\"><a href=\"http://apprabbit.wufoo.com/forms/m7x3k1/\" title=\"Order Form\" rel=\"nofollow\">Fill out my Wufoo form!</a></iframe>";
		assertEquals(embedUrl, form.getIframeEmbedUrl());
	}
}
