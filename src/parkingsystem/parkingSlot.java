/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;
import java.util.Date;

/**
 *
 * @author user
 */
public class parkingSlot {
    
    public Boolean filled;
    private String parkingId;
    private String name;
    private String contactNo;
    private String vehicleId;
    private String emailId;
    private Date expiryDate;
    
    parkingSlot(String parkingId) {
        this.filled = false;
        this.parkingId = parkingId;
        this.name = "";
        this.contactNo = "";
        this.vehicleId = "";
        this.emailId = "";
        this.expiryDate = new Date();
    }
    
    parkingSlot(Boolean filled, String parkingId, String name, String contactNo, String vehicleId, String emailId, Date expiryDate) {
        this.filled = filled;
        this.parkingId = parkingId;
        this.name = name;
        this.contactNo = contactNo;
        this.vehicleId = vehicleId;
        this.emailId = emailId;
        this.expiryDate = expiryDate;
    }
    
    public String getParkingId() {
        return this.parkingId;
    }
    
    public String getVehicleId() {
        return this.vehicleId;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getContactNo() {
        return this.contactNo;
    }
    
    public Date getExpiryDate() {
        return this.expiryDate;
    }
    
    
}
