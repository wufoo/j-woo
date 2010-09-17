package jWufoo;

import org.apache.commons.httpclient.methods.PostMethod;

public class MultiPurposeMethod extends PostMethod {

	String method;
	
	public MultiPurposeMethod(String uri, String method) {
		super(uri);
		this.method = method;
	}
	
	public String getName() {
		return this.method;
	}

}
