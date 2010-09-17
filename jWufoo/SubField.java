package jWufoo;

import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class SubField {
	String Label;
    String ID;
    
    public String getLabel() {return this.Label; }
    public String getID() {return this.ID;}
    
	public SubField(JSONObject json) throws JSONException, ParseException {
		this.Label = json.getString("Label");
		this.ID = json.getString("ID");
	}
}
