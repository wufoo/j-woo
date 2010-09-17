package jWufoo;

import java.text.ParseException;
import org.json.JSONException;
import org.json.JSONObject;

public class User {
	String User;
	String Email;
	String TimeZone;
	String Company;
	Boolean IsAccountOwner;
	Boolean CreateForms;
	Boolean CreateReports;
	Boolean CreateThemes;
	Boolean AdminAccess;
	String ApiKey;
	String Hash;
	//String Image;
	String ImageUrlSmall;
	String ImageUrlBig;
	jWufooAPI api;
	
	public String getUser() {
		return User;
	}
	public String getEmail() {
		return Email;
	}
	public String getTimeZone() {
		return TimeZone;
	}
	public String getCompany() {
		return Company;
	}
	public Boolean getIsAccountOwner() {
		return IsAccountOwner;
	}
	public Boolean getCreateForms() {
		return CreateForms;
	}
	public Boolean getCreateReports() {
		return CreateReports;
	}
	public Boolean getCreateThemes() {
		return CreateThemes;
	}
	public Boolean getAdminAccess() {
		return AdminAccess;
	}
	public String getApiKey() {
		return ApiKey;
	}
	public String getHash() {
		return Hash;
	}
	public String getImageUrlSmall() {
		return this.ImageUrlSmall;//String.format("https://%s.wufoo.com/images/avatars/small/%s.png", api.account, Image);
	}
	public String getImageUrlBig() {
		return this.ImageUrlBig;//String.format("https://%s.wufoo.com/images/avatars/big/%s.png", api.account, Image);
	}

	
	public User(JSONObject json, jWufooAPI api) throws JSONException, ParseException {
		this.api = api;
		this.User = json.getString("User");
		this.Email = json.getString("Email");
		this.TimeZone = json.getString("TimeZone");
		this.Company = json.getString("Company");
		this.IsAccountOwner = Utils.getBoolean(json.getString("IsAccountOwner"));
		this.CreateForms = Utils.getBoolean(json.getString("CreateForms"));
		this.CreateReports = Utils.getBoolean(json.getString("CreateReports"));
		this.CreateThemes = Utils.getBoolean(json.getString("CreateThemes"));
		this.AdminAccess = Utils.getBoolean(json.getString("AdminAccess"));
		this.ApiKey = json.getString("ApiKey");
		this.Hash = json.getString("Hash");
		//this.Image = json.getString("Image");
		this.ImageUrlSmall = json.getString("ImageUrlSmall");
		this.ImageUrlBig = json.getString("ImageUrlBig");
	}
}
