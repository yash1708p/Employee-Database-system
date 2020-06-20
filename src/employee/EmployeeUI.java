package employee;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *  Author: Yash Patel
 *  Date: January 26 2020
 *  Description: This program lets the user input employee information such as name, id, salary etc and stores it. If the user wants to see the list, it can be visible. The user can also print the list. 
 * 
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;

public class EmployeeUI implements ActionListener {

public static String div = "------------------------------------------";
public static List<Employee> employees = new ArrayList<Employee>();
public static JTextArea display;
public static JButton[] buttons = new JButton[4];
public static JLabel[] subTitles = new JLabel[5];
public static JTextField[] inputs = new JTextField[5];

public static void main(String[] args) {

    // Fonts
    Font titleFont = new Font("Courier New", 1, 24);
    Font subFont = new Font("Courier New", 1, 16);

    // Frame
    JFrame frame = new JFrame("Employee Records");
    frame.setSize(550, 450);
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);

    // Container
    JPanel container = new JPanel();
    container.setLayout(null);
    frame.setContentPane(container);

    // Title
    JLabel title = new JLabel("Employee Records");
    title.setFont(titleFont);
    title.setForeground(Color.blue);
    title.setBounds(160, 10, 250, 24);

    // Lablels and text fields
    for (int i = 0; i < subTitles.length; i++) {
        subTitles[i] = new JLabel();
        subTitles[i].setFont(subFont);
        subTitles[i].setBounds(5, 50 + (i * 35), 190, 16);
    }
    subTitles[0].setText("Employee ID#: ");
    subTitles[1].setText("First Name: ");
    subTitles[2].setText("Last Name: ");
    subTitles[3].setText("Annual Salary: ");
    subTitles[4].setText("Start Date: ");

    for (int i = 0; i < subTitles.length; i++) {
        inputs[i] = new JTextField();
        inputs[i].setBounds(160, 47 + (35 * i), 150, 22);
    }

    // Buttons
    for (int i = 0; i < buttons.length; i++) {
        buttons[i] = new JButton();
        buttons[i].addActionListener(new EmployeeUI());
        buttons[i].setBounds(330, 47 + (35 * i), 200, 20);
    }
    buttons[0].setText("Add (REQUIRES ALL FIELDS)");
    buttons[1].setText("Remove (by ID#)");
    buttons[2].setText("List");
    buttons[3].setText("Print");

    // Text area
    display = new JTextArea();
    display.setEditable(false);
    JScrollPane scrollPane = new JScrollPane(display);
    scrollPane.setBounds(5, 217, 535, 200);

    // Adding everything
    container.add(title);
    container.add(scrollPane);
    // Since # of textfields will always equal # of subtitles, we can use the
    // max value of subtitles for the loop
    for (int i = 0; i < subTitles.length; i++) {
        container.add(subTitles[i]);
        container.add(inputs[i]);
    }
    for (int i = 0; i < buttons.length; i++) {
        container.add(buttons[i]);
    }

    // Extras
    frame.toFront();
    frame.setVisible(true);
}

public void actionPerformed(ActionEvent event) {
    if (event.getSource().equals(buttons[0])) {
        // Pass boolean to check if the program should continue or not
        boolean pass = true;
        String message = null;
        // Loop to check if all textfields have data
        for (int i = 0; i < inputs.length; i++) {
            if (inputs[i].getText().equals("")) {
                //display.setText("Error: enter data for ALL fields.");
                message = "Error: enter data for ALL fields.";
                pass = false;
            }
        }
        // If the user passed, the program continues
        if(null == message){
        for (Iterator<Employee> iterator = employees.iterator(); iterator.hasNext();) {// Checking if ID# already exists
                Employee next = iterator.next();// Checking if ID# already exists
                if(next.getId()==Integer.parseInt(inputs[0].getText())){ // Checking if ID# already exists
                    pass=false;
                    message = "Error: employee ID# exists, use another.";
                }
                
            }
        }
       
            if(pass==true){
                employees.add(new Employee(Integer.parseInt(inputs[0].getText()),//id
                        inputs[1].getText(),                //firstname
                        inputs[2].getText(),                //last name
                        Integer.parseInt(inputs[3].getText()),  //salary
                        new Date(inputs[4].getText())                   //startDate
                        ));
                display.setText("Employee #" + inputs[0].getText() + " added to record(s).");
                // Loop to set all textfields to empty
                for (int i = 0; i < inputs.length; i++) {
                    inputs[i].setText(null);
                }
            }
            else{
                display.setText(message);//Prints the appropriate message
            }
            
        
    } else if (event.getSource().equals(buttons[1])) {
        // Loop to search list for requested removal
        for (int i = employees.size() - 1; i >= 0; i--) {
            // If the request is found, it removes all data
            if (Integer.parseInt(inputs[0].getText()) == employees.get(i).getId()) {
                display.setText("Employee #" + employees.get(i).getId() + " has been removed from the records.");
                employees.remove(i);
                break;
                // If not, the ID# does not exist
            } else { 
                display.setText("Error: employee ID# does not exist, try again.");// Displaying error message if entered ID# exists
            }
        }
    } 
    else if (event.getSource().equals(buttons[3])) {//Print button
        try {
            display.print();//Calls the available printers
        } catch (PrinterException ex) {
            Logger.getLogger(EmployeeUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }else {
        // Resets text area and lists all the data
        display.setText(null);
        for (int i = 0; i < employees.size(); i++) {
            display.append(div + "\nEmployee ID#: " + employees.get(i).getId() + "\nFirst Name: " + employees.get(i).getFirstName()
                    + "\nLast Name: " + employees.get(i).getLastName() + "\nAnnual Salary: $" + employees.get(i).getSalary()
                    + "\nStart Date: " + employees.get(i).getStartDate() + "\n");
        }
    }
    
    
}

}
