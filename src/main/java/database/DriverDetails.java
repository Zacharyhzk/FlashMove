package database;

//import java.math.BigDecimal;

public class DriverDetails{
    private int driverId;
    private String driverName;
    private String vehicle;
    private float rating;

    public DriverDetails() {
    }

    public DriverDetails(int driverId, String driverName, String vehicle, float rating) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.vehicle = vehicle;
        this.rating = rating;
    }
    
    
	public int getId() {
		return driverId;
	}

	public void setId(int driverId) {
		this.driverId = driverId;
	}


	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getVehicle() {
		return vehicle;
	}

	public void setVehicle(String vehicle) {
		this.vehicle = vehicle;
	}
	
	public float getRating() {
		return rating;
	}
	
	public void setRating(float rating) {
		this.rating = rating;
	}

}
