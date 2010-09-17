package jWufoo;

import java.text.ParseException;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class Comment {
    int CommentId;
	int EntryId;
	String Text;
	String CommentedBy;
	Date DateCreated;
    	
    public int getCommentID() {return this.CommentId;}
    public int getEntryID() {return this.EntryId;}
    public String getText() {return this.Text; }
    public String getCommentedBy() {return this.CommentedBy;}
    public Date getDateCreated() {return this.DateCreated;}
    
	public Comment(JSONObject json) throws JSONException, ParseException {
		this.CommentId = json.getInt("CommentId");
		this.EntryId = json.getInt("EntryId");
		this.Text = json.getString("Text");
		this.CommentedBy = json.getString("CommentedBy");
		this.DateCreated = Utils.getDate(json.getString("DateCreated"));	
	}
}
