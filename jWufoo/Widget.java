package jWufoo;

import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class Widget {
	String Name;
	String Size;
	String Type;
	String TypeDesc;
	String Hash;
	Report report;
		
	public String getName() {return this.Name; }
	public String getSize() {return this.Size; }
	public String getType() {return this.Type; }
	public String getTextDesc() {return this.TypeDesc; }
	public String getHash() {return this.Hash; }
	
	
	public Widget(JSONObject json, Report report) throws JSONException, ParseException {
		this.Name = json.getString("Name");
		this.Size = json.getString("Size");
		this.Type = json.getString("Type");
		this.TypeDesc = json.getString("TypeDesc");
		this.Hash = json.getString("Hash");
		this.report = report;
	}

	public String getEmbedCode() {
		return String.format("<script type=\"text/javascript\">var host = ((\"https:\" == document.location.protocol) ? \"https://\" : \"http://\");document.write(unescape(\"%%3Cscript src=\'\" + host + \"%s.wufoo.com/scripts/widget/embed.js?w=%s\' type=\'text/javascript\'%%3E%%3C/script%%3E\"));</script>", this.report.api.account, this.Hash);
	}
}
