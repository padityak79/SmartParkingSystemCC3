/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parkingsystem;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

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

/**
 *
 * @author user
 */

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
    }
    
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
                    int j = 0, sz = parkedSlots.size();
                    for(int i = 0; i < sz; i++) {
                        if((boolean)parkConfiguration.model.getValueAt(i,4)) {
                            String parkingId = parkConfiguration.model.getValueAt(i,0).toString();
                            int x = Character.getNumericValue(parkingId.charAt(0)), y = Character.getNumericValue(parkingId.charAt(2));
                            parkedSlots.remove(i-j);
                            ParkingLot.get(x).set(y,new parkingSlot(Integer.toString(x) + "-" + Integer.toString(y)));
                            availableSlots.add(new Point(x,y));
                            j++;
                        }
                    }
                    System.out.println(parkedSlots);
                    int[] options = {2,3,4,5,6,7,8};
                    if( nRows != options[parkConfiguration.rowComboBox.getSelectedIndex()] || nCols != options[parkConfiguration.colComboBox.getSelectedIndex()]) {
                        nRows = options[parkConfiguration.rowComboBox.getSelectedIndex()];
                        nCols = options[parkConfiguration.colComboBox.getSelectedIndex()];
                        maxDays = Integer.parseInt(parkConfiguration.maxTimeTextField.getText());
                        constructPark();
                    }
                    parkConfiguration.dispose();
                }
            });
            
            parkConfiguration.toExtensionButton.addActionListener( new ActionListener() {
                public void actionPerformed(ActionEvent evt) {
                    extensionPane = new bookingExtensionPane(maxDays);
                    extensionPane.setVisible(true);
                    extensionPane.doneButton.addActionListener( new ActionListener() {
                        public void actionPerformed(ActionEvent evt) {
                            String parkingId = extensionPane.parkingSlotField.getText();
                            for(parkingSlot slot : parkedSlots) {
                                if(slot.getParkingId().equals(parkingId)) {
                                    slot.setExpiryDate(extensionPane.expiryDate.getDate());
                                    sortParkedSlots(parkedSlots);
                                    parkConfiguration.fillTable(parkedSlots);
                                    int x = Character.getNumericValue(parkingId.charAt(0)), y = Character.getNumericValue(parkingId.charAt(2));
                                    ParkingLot.get(x).set(y,slot);
                                    break;
                                }
                            }
                            extensionPane.dispose();
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
                    fillParkingSlot( true, registryWindow.nameField.getText(), registryWindow.contactnoField.getText(), registryWindow.vehicleIdField.getText(), registryWindow.emailidField.getText(), registryWindow.expiryDate.getDate());
                    registryWindow.nameField.setText("");
                    registryWindow.vehicleIdField.setText("");
                    registryWindow.contactnoField.setText("");     
                    registryWindow.emailidField.setText("");
                    registryWindow.issueDate.setDate(new Date());
                    registryWindow.expiryDate.setDate(new Date());
                    registryWindow.dispose();
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
    Queue<Point> availableSlots = new LinkedList<>();
    
    
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
            return;
        } 
    }
    
    
    public void constructPark() {
        ParkingLot.clear();
        availableSlots.clear();
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
} 
