package Project;

public class Contact {
	
	String name;
	long phone_number;
	String email_adress;
	String dob;
	String city;
	Contact prev;
	Contact next;
    int contactCount;
	PhoneDirectory pd = new PhoneDirectory();
	
	

	 public Contact() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Contact(String name, long phone_number, String email_adress, String dob, String city) {
		this.name = name;
		this.phone_number = phone_number;
		this.email_adress = email_adress;
		this.dob = dob;
		this.city = city;
		this.prev = null;
		this.next = null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getPhone_number() {
		return phone_number;
	}

	public void setPhone_number(long phone_number) {
		this.phone_number = phone_number;
	}

	public String getEmail_adress() {
		return email_adress;
	}

	public void setEmail_adress(String email_adress) {
		this.email_adress = email_adress;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	@Override
	public String toString() {
		return "Contact "+pd.totalContacts()+"[name=" + name + ", phone_number=" + phone_number + ", email_adress=" + email_adress + ", dob="
				+ dob + ", city=" + city + "]";
	}
}