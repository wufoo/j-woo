package jWufoo;

import java.text.ParseException;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Field {
	String Title;
    String Type;
    String ID;
    Boolean IsRequired;
    ArrayList<SubField> subFields;
    
    public String getTitle() {return this.Title; }
    public String getType() {return this.Type;}
    public String getID() {return this.ID;}
    public Boolean getIsRequired() {return this.IsRequired;}
    public ArrayList<SubField> getSubFields() {return this.subFields;}
    
	public Field(JSONObject json) throws JSONException, ParseException {
		this.Title = json.getString("Title");
		this.Type = json.getString("Type");
		this.ID = json.getString("ID");
		if (json.has("IsRequired")) {
			this.IsRequired = Utils.getBoolean(json.getString("IsRequired"));
		}
		else {
			this.IsRequired = false;
		}
		this.subFields = new ArrayList<SubField>();
		if (json.has("SubFields")) {
			JSONArray subFieldArray = json.getJSONArray("SubFields");
			for (int x = 0; x < subFieldArray.length(); x++) {
				this.subFields.add(new SubField(subFieldArray.getJSONObject(x)));
			}
		}
		
	}
}
