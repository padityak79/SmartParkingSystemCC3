/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;

import java.lang.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.Timer;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.Point;
import java.util.Collections; 
import java.util.Comparator; 
import java.util.Iterator; 
import java.util.List; 
import java.util.stream.Collectors;
import java.util.regex.*;

/**
 *
 * @author user
 */

class callEmail extends Thread {

    int type;
    parkingSlot newParkingSlot;
    
    callEmail(int type, parkingSlot newParkingSlot) {
        this.type = type;
        this.newParkingSlot = newParkingSlot;
    }
    
    public void run() {
        try {
                EmailService.sendEmail(this.type,this.newParkingSlot);
        } catch(Exception e) {
                System.out.println(e.getMessage());
        }
    }
} 


public class Parkingsystem extends JFrame implements ActionListener{

    /*
     * @param args the command line arguments
    */
    
    MainFrame frame;
    Parkingsystem(){ 
        frame = new MainFrame();
        frame.setVisible(true);
        frame.exitButton.addActionListener(this);
        frame.configureButton.addActionListener(this);
        frame.registerButton.addActionListener(this);
        frame.parkingStatusButton.addActionListener(this);
        for(int i = 0; i < nRows; i++) {
            ArrayList<parkingSlot> row = new ArrayList<parkingSlot>();
            for(int j = 0; j < nCols; j++) {
                Point parkPos = new Point(i,j);
                availableSlots.add(parkPos);
                row.add(new parkingSlot(Integer.toString(i) + "-" + Integer.toString(j)));
            }            
            ParkingLot.add(row);
        }
        startCheck();
    }
    
    Timer checkStatusTimer = new Timer(5000, new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            for(int i = 0; i < parkedSlots.size(); i++) {
                if(parkedSlots.get(i).filled && parkedSlots.get(i).getExpiryDate().compareTo(new Date()) < 0 && !isRecorded.get(i)) {
                    JOptionPane.showMessageDialog(null,parkedSlots.get(i).getVehicleId() + " expired on " + parkedSlots.get(i).getExpiryDate() + "\n Name : " + parkedSlots.get(i).getName() + "  Contact Number : " + parkedSlots.get(i).getContactNo(),"Expired registration!!", JOptionPane.INFORMATION_MESSAGE);            
                    Thread emailFunction = new Thread(new callEmail(2,parkedSlots.get(i))); 
                    emailFunction.start();
                    isRecorded.set(i,true);
                }
            }
        }
    });
        
    public static void main(String[] args) {
        // TODO code application logic here
       new Parkingsystem();  
    }

    @Override
    public void actionPerformed(ActionEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        if(e.getSource() == frame.exitButton) {
            JFrame exitFrame = new JFrame("EXIT");
            if(JOptionPane.showConfirmDialog(exitFrame,"Confirm if you want to exit?", 
                "EXIT", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION) {
                    System.exit(0);
            } else {
                exitFrame.dispose();
            }
            return;
        }
        
        if(e.getSource() == frame.configureButton) {
            parkConfiguration = new ParkConfiguration(nRows, nCols, maxDays);
            parkConfiguration.fillTable(parkedSlots);
            parkConfiguration.setVisible(true);
            JOptionPane.showMessageDialog(null,"Warning!! If you change the dimensions of the parking space all the parked vehicles will be removed.","Warning configuration!!", JOptionPane.WARNING_MESSAGE);            
            
            parkConfiguration.cancelButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    parkConfiguration.dispose();
                }
            });
            
            parkConfiguration.confirmChangesButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    stopCheck();
                    int j = 0, sz = parkedSlots.size();
                    for(int i = 0; i < sz; i++) {
                        if((boolean)parkConfiguration.model.getValueAt(i,4)) {
                            String parkingId = parkConfiguration.model.getValueAt(i,0).toString();
                            int x = Character.getNumericValue(parkingId.charAt(0)), y = Character.getNumericValue(parkingId.charAt(2));
                            Thread emailFunction = new Thread(new callEmail(3,parkedSlots.get(i-j))); 
                            emailFunction.start();
                            parkedSlots.remove(i-j);
                            ParkingLot.get(x).set(y,new parkingSlot(Integer.toString(x) + "-" + Integer.toString(y)));
                            availableSlots.add(new Point(x,y));
                            isRecorded.set(i-j,false);
                            j++;
                        }
                    }
//                    System.out.println(parkedSlots);
                    int[] options = {2,3,4,5,6,7,8};
                    if( nRows != options[parkConfiguration.rowComboBox.getSelectedIndex()] || nCols != options[parkConfiguration.colComboBox.getSelectedIndex()]) {
                        nRows = options[parkConfiguration.rowComboBox.getSelectedIndex()];
                        nCols = options[parkConfiguration.colComboBox.getSelectedIndex()];
                        maxDays = Integer.parseInt(parkConfiguration.maxTimeTextField.getText());
                        constructPark();
                    }
                    startCheck();
                    parkConfiguration.dispose();
                }
            });
            
            parkConfiguration.toExtensionButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    extensionPane = new bookingExtensionPane(maxDays);
                    extensionPane.setVisible(true);
                    extensionPane.doneButton.addActionListener( new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            stopCheck();
                            String parkingId = extensionPane.parkingSlotField.getText();
                            for(parkingSlot slot : parkedSlots) {
                                if(slot.getParkingId().equals(parkingId)) {
                                    slot.setExpiryDate(extensionPane.expiryDate.getDate());
                                    sortParkedSlots(parkedSlots);
                                    parkConfiguration.fillTable(parkedSlots);
                                    int x = Character.getNumericValue(parkingId.charAt(0)), y = Character.getNumericValue(parkingId.charAt(2));
                                    ParkingLot.get(x).set(y,slot);
                                    Thread emailFunction = new Thread(new callEmail(1,slot)); 
                                    emailFunction.start();
                                    break;
                                }
                            }
                            extensionPane.dispose();
                            startCheck();
                        }
                    });
            
                    extensionPane.cancelButton.addActionListener( new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            extensionPane.dispose();
                        }
                    });                    
                }
            });
            
            parkConfiguration.resetTimeButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    parkConfiguration.maxTimeTextField.setText(Integer.toString(maxDays));
                }
            });
            
           
            return;
        }
        
        if(e.getSource() == frame.registerButton) {
            if(availableSlots.size() == 0) {
                JOptionPane.showMessageDialog(null,"No Parking Slots are Available till " + parkedSlots.get(0).getExpiryDate() +".", "Error: no slot available", JOptionPane.ERROR_MESSAGE);
                return;
            }
            Registry registryWindow = new Registry(maxDays);

            registryWindow.setVisible(true);
            
            registryWindow.resetFieldButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    registryWindow.nameField.setText("");
                    registryWindow.vehicleIdField.setText("");
                    registryWindow.contactnoField.setText("");     
                    registryWindow.emailidField.setText("");
                    registryWindow.issueDate.setDate(new Date());
                    registryWindow.expiryDate.setDate(new Date());
                }
            });
            
            registryWindow.submitButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    ArrayList<Integer> invalidEntries = performValidations( registryWindow.nameField.getText(), registryWindow.contactnoField.getText(), registryWindow.vehicleIdField.getText(), registryWindow.emailidField.getText());
                    if(invalidEntries.size() == 0) {
                        stopCheck();
                        fillParkingSlot( true, registryWindow.nameField.getText(), registryWindow.contactnoField.getText(), registryWindow.vehicleIdField.getText(), registryWindow.emailidField.getText(), registryWindow.expiryDate.getDate());
                        registryWindow.nameField.setText("");
                        registryWindow.vehicleIdField.setText("");
                        registryWindow.contactnoField.setText("");     
                        registryWindow.emailidField.setText("");
                        registryWindow.issueDate.setDate(new Date());
                        registryWindow.expiryDate.setDate(new Date());
                        registryWindow.dispose();
                        startCheck();
                    } else {
                        for(int i = 0; i < invalidEntries.size(); i++) {
                            if(invalidEntries.get(i) == 1) registryWindow.nameField.setText("");
                            if(invalidEntries.get(i) == 2)registryWindow.contactnoField.setText(""); 
                            if(invalidEntries.get(i) == 3)registryWindow.vehicleIdField.setText("");    
                            if(invalidEntries.get(i) == 4)registryWindow.emailidField.setText("");
                        }
                    }
                }
            });
            
            registryWindow.backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    registryWindow.dispose();
                }
            });
            return;
        }
        
        if(e.getSource() == frame.parkingStatusButton) {
            parkingPanel = new DisplayPanel();
            parkingPanel.fillTable(parkedSlots);
            parkingPanel.setVisible(true);
            parkingPanel.backButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    parkingPanel.dispose();
                }
            });
            
            parkingPanel.doneButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    parkingPanel.dispose();
                }
            });
            
            parkingPanel.displayParkingLotButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    plotDiagram parkingSpace = new plotDiagram( "Parking Space View", nRows, nCols);
                    parkingSpace.displayParkingLot( nRows, nCols, ParkingLot);
                    parkingSpace.setVisible(true);
                }
            });
            
            parkingPanel.displayAvailableButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    plotDiagram parkingSpace = new plotDiagram( "Occuupied Parking Slots", nRows, nCols);
                    parkingSpace.displayAvailableSlots( nRows, nCols, ParkingLot);
                    parkingSpace.setVisible(true);
                }
            });
            
            
            parkingPanel.displayOccupiedButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    plotDiagram parkingSpace = new plotDiagram( "Occupied Parking Slots", nRows, nCols);
                    parkingSpace.displayOccupiedSlots( nRows, nCols, ParkingLot);
                    parkingSpace.setVisible(true);
                }
            });
            
            return;
        }
    }
    
    
    // variables
    int maxDays = 31, nRows = 3, nCols = 3;
    ParkConfiguration parkConfiguration;
    Registry registryWindow;
    DisplayPanel parkingPanel;
    bookingExtensionPane extensionPane;
    ArrayList<ArrayList<parkingSlot> > ParkingLot = new ArrayList<ArrayList<parkingSlot>>();
    ArrayList<parkingSlot> parkedSlots = new ArrayList<parkingSlot>();
    ArrayList<Boolean> isRecorded = new ArrayList<Boolean>();
    Queue<Point> availableSlots = new LinkedList<>();
    
    public void startCheck() {
        checkStatusTimer.start();
    }
    
    public void stopCheck() {
        checkStatusTimer.stop();
    }
    
    //functions
    public void fillParkingSlot(Boolean filled, String name, String contactNo, String vehicleId, String emailId, Date expiryDate) {
        if(!availableSlots.isEmpty()) {
            Point newSlot = availableSlots.remove();
            int x = (int)newSlot.getX() , y = (int)newSlot.getY();
            String parkingId = Integer.toString(x) + "-" + Integer.toString(y);
            parkingSlot newparkingSlot = new parkingSlot( filled, parkingId, name, contactNo, vehicleId, emailId, expiryDate);
            ParkingLot.get(x).set(y,newparkingSlot);
            parkedSlots.add(newparkingSlot);
            sortParkedSlots(parkedSlots);
            isRecorded.add(false);
            Thread emailFunction = new Thread(new callEmail(0,newparkingSlot)); 
            emailFunction.start(); 
            return;
        } 
    }
    
    
    public void constructPark() {
        ParkingLot.clear();
        availableSlots.clear();
        isRecorded.clear();
        for(int i = 0; i < nRows; i++) {
            ArrayList<parkingSlot> row = new ArrayList<parkingSlot>();
            for(int j = 0; j < nCols; j++) {
                Point parkPos = new Point(i,j);
                availableSlots.add(parkPos);
                row.add(new parkingSlot(Integer.toString(i) + "-" + Integer.toString(j)));
            }            
            ParkingLot.add(row);
        }
    }    
    
    
    public void sortParkedSlots(ArrayList<parkingSlot> parkedSlots) {
        Comparator<parkingSlot> dateComparator = (p1,p2) -> p1.getExpiryDate().compareTo(p2.getExpiryDate());
        parkedSlots.sort(dateComparator);
    }
    
    
    public ArrayList<Integer> performValidations(String name, String contactNo, String vehicleID, String emailId) {
        ArrayList<Integer> invalidInfo = new ArrayList<Integer>();
        String regx = "^[\\p{L} .'-]+$";
        Pattern p = Pattern.compile(regx);
        Matcher m = p.matcher(name);
        if(!m.matches()) {
            invalidInfo.add(1);
            JOptionPane.showMessageDialog(null,"Invalid Name. \n (1) Name should not be empty\n (2) All words start with capital Letters\n (3) No Special Characters\n (4) No digits\n", "Validation Error!!", JOptionPane.ERROR_MESSAGE);            
        } 
        
        regx = "^[6-9]\\d{9}$";
        p = Pattern.compile(regx);
        m = p.matcher(contactNo);
        if(!m.matches()) {  
            invalidInfo.add(2);
            JOptionPane.showMessageDialog(null,"Invalid Phone Number. \n (1) Phone Number should not be empty\n (2) The Phone Number should be 10 digits long\n Please check your Phone number and try again.\n", "Validation Error!!", JOptionPane.ERROR_MESSAGE);            
        }
        
        regx = "^[A-Z]{2,3}\\s[0-9]{1,2}\\s[A-Z]{0,3}\\s[0-9]{4}$";
        p = Pattern.compile(regx);
        m = p.matcher(vehicleID);
        if(!m.matches()) {
            invalidInfo.add(3);
            JOptionPane.showMessageDialog(null,"Invalid Vehicle ID. \nVehicle ID should be of the format: MH(State Code)01(District Number) AW(series of RTO) 0121(Random Number)\n", "Validation Error!!", JOptionPane.ERROR_MESSAGE);            
        }
        
        regx = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        p = Pattern.compile(regx,Pattern.CASE_INSENSITIVE);
        m = p.matcher(emailId);
        if(!m.matches()) {
            invalidInfo.add(4);
            JOptionPane.showMessageDialog(null,"Invalid Email Id. \nPlease try Again!!","Warning configuration!!", JOptionPane.ERROR_MESSAGE); 
        }
        return invalidInfo;
    } 
} 
