package database;

//import java.math.BigDecimal;

public class RecordDetails{
    private int id;
    private int status;
    private String statusName;
    private String delivery_date;
    private String route;
    private String driver;
    private int type;
    private String typeName;
    private String price;

    public RecordDetails() {
    }

    public RecordDetails(int id, int status, String delivery_date, String route, String driver,int type,String price) {
        this.id = id;
        this.status = status;
        this.delivery_date = delivery_date;
        this.route = route;
        this.driver = driver;
        this.type = type;
        this.price = price;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(String delivery_date) {
		this.delivery_date = delivery_date;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}
	
	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}
	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
