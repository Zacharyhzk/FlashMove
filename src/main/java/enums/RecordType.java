package enums;

public enum RecordType {
	Completed(0, "Completed"),
	Processing(1, "Processing"),
	Canceled(2, "Canceled");
	
	int key;
	String value;
	
	RecordType(int key, String value) {
		this.key = key;
		this.value = value;
	}

	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	
	public static String getByKey(int key) {
		for (RecordType recordType : RecordType.values()) {
			if (recordType.getKey() == key) {
				return recordType.getValue();
			}
		}
		return "";
	}

}
