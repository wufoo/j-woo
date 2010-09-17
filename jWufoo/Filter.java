package jWufoo;

public class Filter {
	public enum OPERATOR {
		Contains,
		Does_not_contain,
		Begins_with,
		Ends_with,
		Is_less_than,
		Is_greater_than,
		Is_on,
		Is_before,
		Is_after,
		Is_not_equal_to,
		Is_equal_to,
		Is_not_NULL
	}
	Field field;
    OPERATOR operator;
    String value;
    
    public Field getField() {return this.field; }
    public OPERATOR getOperator() {return this.operator;}
    @SuppressWarnings("deprecation")
	public String getValue() {return java.net.URLEncoder.encode(this.value); }
    
	public Filter(Field field, OPERATOR operator, String value) {
		this.field = field;
		this.operator = operator;
		this.value = value;
	}
}
