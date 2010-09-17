package jWufoo;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Entry {
	int EntryId;
    Hashtable<String, Object> Fields;
	String CreatedBy;
	Date DateCreated;
	String UpdatedBy;
	Date DateUpdated;
	Form Form;
	ArrayList<Comment> comments;
	
    public int getEntryId() {return this.EntryId; }
    public Hashtable<String, Object> getFields() {return this.Fields;}
    public String getCreatedBy() {return this.CreatedBy; }
    public Date getDateCreated() {return this.DateCreated; }
    public String getUpdatedBy() {return this.UpdatedBy; }
    public Date getDateUpdated() {return this.DateUpdated; }
    public ArrayList<Comment> getComments() {
    	if (this.comments == null)  {
    		String url = String.format("https://%s.wufoo.com/api/v3/forms/%s/comments.json?entryId=%s", this.Form.api.account, this.Form.Hash, this.EntryId);
    		this.comments = new ArrayList<Comment>();
    		try {
    			JSONObject json = this.Form.api.makeRequest(url);
				JSONArray rawNodes = json.getJSONArray("Comments");
				int rawCount = rawNodes.length();
				for (int i = 0; i < rawCount; i++) {
					this.comments.add(new Comment(rawNodes.getJSONObject(i)));
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
    	return this.comments;     		
    }
		
    public Entry() {
    	this.Fields = new Hashtable<String, Object>();
    }
    
	public Entry(JSONObject json, Form form) throws JSONException, ParseException {
		this.EntryId = json.getInt("EntryId");
		this.CreatedBy = json.optString("CreatedBy", "");
		this.DateCreated = Utils.getDate(json.optString("DateCreated", ""));
		this.UpdatedBy = json.optString("UpdatedBy", "");
		this.DateUpdated = Utils.getDate(json.optString("DateUpdated", ""));
		this.Fields = new Hashtable<String, Object>();
		this.Form = form;
		
		JSONArray fields = json.names();
		for (int x = 0; x < fields.length(); x++){
			String field = (String) fields.get(x);
			//if (field.startsWith("Field")) {
			this.Fields.put(field, json.get(field));
			//}
		}
	}
}
