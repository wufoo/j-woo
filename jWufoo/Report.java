package jWufoo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Report {
	String Name;
	Boolean IsPublic;
	String Url;
    String Description;
    Date DateCreated;
    Date DateUpdated;
    String Hash;
    String LinkEntries;
    String LinkFields;
    String LinkEntriesCount;
    String LinkWidgets;
    jWufooAPI api;
    ArrayList<Field> fields;
    ArrayList<Widget> widgets;
    
    public String getName() {return this.Name; }
    public String getDescription() {return this.Description;}
    public String getUrl() {return this.Url;}
    public Boolean getIsPublic() {return this.IsPublic;}
    public Date getDateCreated() {return this.DateCreated;}
    public Date getDateUpdated() {return this.DateUpdated;}
    public String getHash() {return this.Hash;}
    
	public Report(JSONObject json, jWufooAPI api) throws JSONException, ParseException {
		this.api = api;
		this.Name = json.getString("Name");
		this.Description = json.getString("Description");
		this.Url = json.getString("Url");
		this.IsPublic = Utils.getBoolean(json.getString("IsPublic"));
		this.DateCreated = Utils.getDate(json.getString("DateCreated"));
		this.DateUpdated = Utils.getDate(json.getString("DateUpdated"));
		this.Hash = json.getString("Hash");
		this.LinkEntries = json.getString("LinkEntries");
		this.LinkFields = json.getString("LinkFields");
		this.LinkEntriesCount = json.getString("LinkEntriesCount");
		this.LinkWidgets = json.getString("LinkWidgets");
	}
	
	public Field getField(String title) {
		ArrayList<Field> fields = new ArrayList<Field>();
		fields = this.getFields();
		for (Field field : fields) {
			if (field.Title.contains(title)) {
				return field;
			}
			else if (field.ID.contains(title)){
				return field;
			}
		}
		return null;
	}

	public ArrayList<Field> getFields() {
		if (this.fields == null) {
			this.fields = new ArrayList<Field>();
			try {
				JSONObject json = this.api.makeRequest(this.LinkFields);
				JSONObject fields = json.getJSONObject("Fields");
				@SuppressWarnings("unchecked")
				Iterator<String> keys = fields.keys(); 
				while (keys.hasNext()) {
					Field field = new Field(fields.getJSONObject(keys.next()));
					this.fields.add(field);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.fields;
	}
	
	public ArrayList<Widget> getWidgets() {
		if (this.widgets == null) {
			this.widgets = new ArrayList<Widget>();
			try {
				JSONObject json = this.api.makeRequest(this.LinkWidgets);
				JSONArray rawNodes = json.getJSONArray("Widgets");
				int rawCount = rawNodes.length();
				for (int i = 0; i < rawCount; i++) {
					this.widgets.add(new Widget(rawNodes.getJSONObject(i), this));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return this.widgets;
	}
	
	public ArrayList<Entry> getEntries(int pageStart, int pageSize, String sort) {
		return getEntries(pageStart, pageSize, sort, "DESC");
	}
	public ArrayList<Entry> getEntries(int pageStart, int pageSize) {
		return getEntries(pageStart, pageSize, "DateCreated", "DESC");
	}
	public ArrayList<Entry> getEntries(String sort, String sortDirection) {
		return getEntries(0, 100, sort, sortDirection);
	}
	public ArrayList<Entry> getEntries(String sort) {
		return getEntries(0, 100, sort, "DESC");
	}
	public ArrayList<Entry> getEntries() {
		return getEntries(0, 100, "DateCreated", "DESC");
	}

	public ArrayList<Entry> getEntries(int pageStart, int pageSize, String sort, String sortDirection) {
	    ArrayList<Entry> entries = new ArrayList<Entry>();
		String url = String.format("%s?system=true&pageStart=%d&pageSize=%d&sort=%s&sortDirection=%s", 
				this.LinkEntries, 
				pageStart,
				pageSize,
				sort,
				sortDirection);
		System.out.println(url);
	    JSONObject json = new JSONObject();
	    JSONArray rawNodes = new JSONArray();
		int rawCount = 0;
		
	    try {
			json = this.api.makeRequest(url);
			rawNodes = json.getJSONArray("Entries");
			rawCount = rawNodes.length();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		for (int i = 0; i < rawCount; i++) {
			try {
				entries.add(new Entry(rawNodes.getJSONObject(i), null));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return entries;
	}
	
	public String getLink() {
		return String.format("https://%s.wufoo.com/reports/%s/", this.api.account, this.Hash);
	}
}
