package Project;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import java.util.ArrayList;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

public class PhoneDirectoryGUI extends JFrame {
    private PhoneDirectory phoneDirectory;
    private JTextField nameField, phoneNumberField, searchNameField, searchNumberField, deleteContactField, updateNameField,
            emailField, dobField, cityField;
    public JTextArea resultArea;

    public ArrayList<Contact> conts = new ArrayList<Contact>();

    public PhoneDirectoryGUI() {

        JEditorPane editorPane = new JEditorPane();
        getContentPane().add(editorPane, BorderLayout.SOUTH);
        phoneDirectory = new PhoneDirectory();
        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Phone Directory App");
        setSize(900, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        resultArea = new JTextArea();
        resultArea.setEditable(true);
        JScrollPane scrollPane = new JScrollPane(resultArea);
        getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create the FormLayout for inputPanel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FormLayout(
                new ColumnSpec[]{
                        ColumnSpec.decode("pref"),
                        FormSpecs.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("150dlu"),
                        FormSpecs.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("pref"),
                        FormSpecs.RELATED_GAP_COLSPEC,
                        ColumnSpec.decode("150dlu")
                },
                new RowSpec[]{
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC,
                        FormSpecs.RELATED_GAP_ROWSPEC,
                        FormSpecs.DEFAULT_ROWSPEC
                }));

        // Add components to the inputPanel
        inputPanel.add(new JLabel("Name:"), "1, 1");
        nameField = new JTextField();
        inputPanel.add(nameField, "3, 1, 5, 1");

        inputPanel.add(new JLabel("Enter Name to search:"), "5, 1");
        searchNameField = new JTextField();
        inputPanel.add(searchNameField, "7, 1");
        JButton searchNameButton = new JButton("Search by Name");
        searchNameButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchNameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchName = searchNameField.getText();
                Contact foundContact = phoneDirectory.searchContact(searchName);
                if (foundContact != null) {
                    resultArea.setText("Contact found:\n" +
                            "Name: " + foundContact.getName() + "\n" +
                            "Phone number: " + foundContact.getPhone_number());
                } else {
                    JOptionPane.showMessageDialog(null, "Contact Not Found!!");
                }
            }
        });
        inputPanel.add(searchNameButton, "9, 1");

        inputPanel.add(new JLabel("Phone Number:"), "1, 3");
        phoneNumberField = new JTextField();
        inputPanel.add(phoneNumberField, "3, 3, 5, 1");

        inputPanel.add(new JLabel("Enter PhoneNumber to search:"), "5, 3");
        searchNumberField = new JTextField();
        inputPanel.add(searchNumberField, "7, 3");
        JButton searchNumberButton = new JButton("Search by Phone Number");
        searchNumberButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        searchNumberButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchNumber = searchNumberField.getText();
                Contact foundContact = phoneDirectory.searchContactByNumber(Long.parseLong(searchNumber));
                if (foundContact != null) {
                    resultArea.setText("Contact found:\n" +
                            "Name: " + foundContact.getName() + "\n" +
                            "Phone number: " + foundContact.getPhone_number());
                } else {
                    JOptionPane.showMessageDialog(null, "Contact Not Found!!");
                }
            }
        });
        inputPanel.add(searchNumberButton, "9, 3");

        inputPanel.add(new JLabel("Email Address:"), "1, 5");
        emailField = new JTextField();
        inputPanel.add(emailField, "3, 5, 5, 1");

        inputPanel.add(new JLabel("Enter name to Delete the Contact:"), "5, 5");
        deleteContactField = new JTextField();
        inputPanel.add(deleteContactField, "7, 5");
        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String deleteContact = deleteContactField.getText();
                Contact foundContact = phoneDirectory.searchContact(deleteContact);
                if (foundContact != null) {
                    phoneDirectory.deleteContact(deleteContact);
                    JOptionPane.showMessageDialog(null, "Contact Deleted Successfully!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Contact Not Found!!");
                }
            }
        });
        inputPanel.add(deleteButton, "9, 5");

        inputPanel.add(new JLabel("Date of Birth:"), "1, 7");
        dobField = new JTextField();
        inputPanel.add(dobField, "3, 7, 5, 1");

        inputPanel.add(new JLabel("Enter Name to Update:"), "5, 7");
        updateNameField = new JTextField();
        inputPanel.add(updateNameField, "7, 7");
        JButton updateButton = new JButton("Update Contact");
        updateButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        updateButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nameToUpdate = updateNameField.getText();
                int updateChoice = 0;
                String newValue = "";

                try {
                    updateChoice = Integer.parseInt(JOptionPane.showInputDialog(null,
                            "Choose what to update:\n1. Name\n2. Phone Number\n3. Email Address\n4. Date of Birth\n5. City",
                            "Update Choice", JOptionPane.PLAIN_MESSAGE));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Invalid choice. Please enter a valid number.");
                    return;
                }

                if (updateChoice >= 1 && updateChoice <= 5) {
                    newValue = JOptionPane.showInputDialog(null, "Enter the new value:", "Update Value", JOptionPane.PLAIN_MESSAGE);
                    if (newValue != null) {
                        try {
                            phoneDirectory.updateContact(nameToUpdate, updateChoice, newValue);
                            JOptionPane.showMessageDialog(null, "Contact updated successfully!");
                            displayContacts();
                        } catch (NoContactFoundException ex) {
                            JOptionPane.showMessageDialog(null, ex.getMessage());
                        } catch (IllegalArgumentException e1) {
                        	JOptionPane.showMessageDialog(null, e1.getMessage());
                        }
                    }
                }
            }
        });


            JButton prevContactButton = new JButton("Previous Contact");
            prevContactButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            prevContactButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Contact contact = null;
                    String searchNumber = searchNumberField.getText();
                    String searchName = searchNameField.getText();
                    if (!searchNumber.equals("")) {
                        contact = phoneDirectory.searchContactByNumber(Long.parseLong(searchNumber));
                    } else if (!searchName.equals("")) {
                        contact = phoneDirectory.searchContact(searchName);
                    }
                    Contact prevContact = phoneDirectory.prevContact(contact);
                    if (prevContact != null) {
                        JOptionPane.showMessageDialog(null, "Previous Contact:\n" +
                                "Name: " + prevContact.getName() + "\n" +
                                "Phone Number: " + prevContact.getPhone_number());
                    } else {
                        JOptionPane.showMessageDialog(null, "No Previous Contact to display");
                    }
                }
            });
            inputPanel.add(prevContactButton, "3, 13");

            JButton nextContactButton = new JButton("Next Contact");
            nextContactButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            nextContactButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Contact contact = null;
                    String searchNumber = searchNumberField.getText();
                    String searchName = searchNameField.getText();
                    if (!searchNumber.equals("")) {
                        contact = phoneDirectory.searchContactByNumber(Long.parseLong(searchNumber));
                    } else if (!searchName.equals("")) {
                        contact = phoneDirectory.searchContact(searchName);
                    }
                    Contact nextContact = phoneDirectory.nextContact(contact);
                    if (nextContact != null) {
                        JOptionPane.showMessageDialog(null, "Next Contact:\n" +
                                "Name: " + nextContact.getName() + "\n" +
                                "Phone Number: " + nextContact.getPhone_number());
                    } else {
                        JOptionPane.showMessageDialog(null, "No Next Contact to display");
                    }
                }
            });
            inputPanel.add(nextContactButton, "5, 13");

            JButton totalContactsButton = new JButton("Total Contacts");
            totalContactsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
            totalContactsButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JOptionPane.showMessageDialog(null, "Total Number of Contacts in Directory: " + phoneDirectory.totalContacts());
                }
            });
            inputPanel.add(totalContactsButton, "7, 13");
  }
            
            public void displayContacts(){
            	resultArea.setText("");
            	conts = phoneDirectory.getAllContacts();
                if (conts.size() == 0) {
                    resultArea.setText("Phone directory is empty.");
                } else {
                    StringBuilder displayText = new StringBuilder("----------Contacts List-----------:\n");
                    for (Contact contact : conts) {
                    	//displayText.append("Contact: "+phoneDirectory.contactCount).append("\n");
                        displayText.append("Name: ").append(contact.getName()).append("\n");
                        displayText.append("Phone number: ").append(contact.getPhone_number()).append("\n");
                        displayText.append("Email: ").append(contact.getEmail_adress()).append("\n");
                        displayText.append("Date of Birth: ").append(contact.getDob()).append("\n");
                        displayText.append("City: ").append(contact.getCity()).append("\n");
                        displayText.append("\n");
                    }
                    resultArea.setText(displayText.toString());
                   
                }
    }
	public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new PhoneDirectoryGUI().setVisible(true);
            }
        });
	}
}

