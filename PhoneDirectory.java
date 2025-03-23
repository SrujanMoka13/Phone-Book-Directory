package Project;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;


public class PhoneDirectory {
	    private Contact head;
	    private Contact tail;
	    public static int contactCount =0;
	    Scanner scanner = new Scanner(System.in);
	    public ArrayList<Contact> contacts = new ArrayList<Contact>();
	    
	    public PhoneDirectory() {
	        head = null;
	        tail = null;
	    }

	    public boolean addContact(Contact searchName) {
	    	
	        // Check if the contact name already exists in the LinkedList
	        Contact current = head;
	        while (current != null) {
	            if (current.getName().equalsIgnoreCase(searchName.getName())) {
	                //System.out.println("Contact with the same name already exists. Please provide a unique name.");
	                return false; // Return without adding the contact
	            }
	            current = current.next;
	        }

	        // Add the contact to the LinkedList
	        if (head == null) {
	            head = searchName;
	            tail = searchName;
//	            System.out.println("Contact added suscessfully!\n");
	            contactCount+=1;
	            return true;
	        } else {
	            tail.next = searchName;
	            searchName.prev = tail;
	            tail = searchName;
	            contactCount+=1;
	            return true;
//	            System.out.println("Contact added suscessfully!\n");
	        }
//	        System.out.println(contactCount);
	    }


	    public Contact searchContact(String name) {
	        Contact current = head;
	        while (current != null) {
	            if (current.getName().equalsIgnoreCase(name)) {
	                return current;
	            }
	            current = current.next;
	        }
	        return null;
	    }
	    
	    public Contact searchContactByNumber(long phn) {
	        Contact current = head;
	        while (current != null) {
	            if (current.getPhone_number() == (phn)) {
	                return current;
	            }
	            current = current.next;
	        }
	        return null;
	    }

	    public boolean deleteContact(String name) {
	        Contact contact = searchContact(name);
	        if (contact != null) {
	            if (contact == head) {
	                head = contact.next;
	                if (head != null) {
	                    head.prev = null;
	                }
	            } else if (contact == tail) {
	                tail = contact.prev;
	                if (tail != null) {
	                    tail.next = null;
	                }
	            } else {
	                contact.prev.next = contact.next;
	                contact.next.prev = contact.prev;
	            }
	            contactCount-=1;
	            return true;
	        }
	        return false;
	    }
	    
	        public void updateContact(String name, int choice, String newValue) {
	            Contact contact = searchContact(name);
	            if (contact != null) {
	                switch (choice) {
	                    case 1:
	                        contact.setName(newValue);
	                        break;
	                    case 2:
	                        contact.setPhone_number(Long.parseLong(newValue));
	                        break;
	                    case 3:
	                        contact.setEmail_adress(newValue);
	                        break;
	                    case 4:
	                        contact.setDob(newValue);
	                        break;
	                    case 5:
	                        contact.setCity(newValue);
	                        break;
	                    default:
	                        throw new IllegalArgumentException("Invalid choice.");
	                }
	            } else {
	                throw new NoContactFoundException("No Contact found with " + name + " to update!");
	            }
	        }


	    
	    /*
	    public void displayAllContacts() {
			Contact temp;
			temp = head;
			if (temp == null)
				System.out.println("No Contacts to display in directory!\n");
			else {

				while (temp != null) {
					//System.out.println("Name:"+temp.name + "  "+"Phone_Number:"+temp.phone_number+"  "+"Emain_Adress:"+temp.email_adress+"  "+"Date Of Birth:"+temp.dob+" "+"City:"+temp.city);
					System.out.println(temp);
					temp = temp.next;
				}
				System.out.println("--------------END---------------");

			}
		}
		*/
	    
	    public ArrayList<Contact> getAllContacts() {
	    	contacts.clear();
	    	contactCount = 0;
			Contact temp;
			temp = head;
			

				while (temp != null) {
					//System.out.println("Name:"+temp.name + "  "+"Phone_Number:"+temp.phone_number+"  "+"Emain_Adress:"+temp.email_adress+"  "+"Date Of Birth:"+temp.dob+" "+"City:"+temp.city);
					//System.out.println(temp);
					contacts.add(temp);
					temp = temp.next;
				}
				System.out.println("--------------END---------------");
			System.out.println(contacts);
			return contacts;
		}
	    
	    public boolean deleteAllContacts() {
			if (head == null) {
				return false;
			}
			
			else {
				head = null;
				return true;
			}
			
			/*
			else {
				
				while (temp.next != null) {
					System.out.println("This is while loop...");
					System.out.println(temp);
					System.out.println("Temp.next: "+temp.next);
					temp1 = temp.next;
					temp = null;
					temp = temp1;
				}
				return true;
			}
			*/
	    }
	    
	    public Contact prevContact(Contact contact) {
	    	Contact temp;
	    	temp = contact;
	    	if(temp == head) {
	    		return null;
	    	}
	    	
	    	else {
	    		return temp.prev;
	    	}
	    }
	    
	    public Contact nextContact(Contact contact) {
	    	Contact temp;
	    	temp = contact;
	    	if(temp == tail) {
	    		return null;
	    	}
	    	
	    	else {
	    		return temp.next;
	    	}
	    }
	    
	    
	    public int totalContacts() {
	    	return contactCount;
	    }

	}
}

