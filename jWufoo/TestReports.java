package jWufoo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import org.json.JSONException;

public class TestReports extends TestBase {

	public void testGetReports() throws IOException, JSONException, ParseException {
		ArrayList<Report> reports = api.getReports();
		assertEquals(2, reports.size());
		assertEquals("Untitled Report", reports.get(1).getName());
	}
	
	public void testGetReportEntries() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(1);
		ArrayList<Entry> entries = report.getEntries();
		assertEquals(3, entries.size());
		assertEquals("Mark Ransom", entries.get(0).getFields().get("Field1"));
	}

	public void testGetReportFields() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(0);
		Entry entry = report.getEntries().get(0);
		
		Field type_field = report.getField("Type");
		assertTrue(entry.getFields().containsKey(type_field.ID)); 
		Field details_field = report.getField("Details");
		assertFalse(entry.getFields().containsKey(details_field.ID)); 
	}

	public void testReportEntriesAndFieldsMatch() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(1);
		ArrayList<Field> fields = report.getFields();
		Entry entry = report.getEntries().get(0);
		for (Field field : fields) {
			if (!field.ID.contains("LastUpdated")){
				if (field.getSubFields().size() > 0){
					for (SubField subfield : field.getSubFields()) {
						assertTrue(entry.Fields.containsKey(subfield.ID));
					}
				}
				else {
					if (field.getIsRequired()) {
						assertTrue(entry.Fields.containsKey(field.ID));
					}
				}
			}
		}
	}

	public void testReportWidgetsGetter() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(1);
		ArrayList<Widget> widgets = report.getWidgets();
		assertEquals(1, widgets.size());
		assertEquals("fieldChart", widgets.get(0).getType());
	}

	public void testReportWidgetCodeGetter() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(1);
		Widget widget = report.getWidgets().get(0);
		assertEquals("<script type=\"text/javascript\">var host = ((\"https:\" == document.location.protocol) ? \"https://\" : \"http://\");document.write(unescape(\"%3Cscript src=\'\" + host + \"apprabbit.wufoo.com/scripts/widget/embed.js?w=JlLuLGXrZ8sGfSR6D2lwuslashIQwuBey8dw1CIWDhDIJyLHRJQY=\' type=\'text/javascript\'%3E%3C/script%3E\"));</script>", widget.getEmbedCode());
	}

	public void testReportLinkGetter() throws IOException, JSONException, ParseException {
		Report report = api.getReports().get(0);
		assertEquals("https://apprabbit.wufoo.com/reports/m5p8q8/", report.getLink());
	}	
}
