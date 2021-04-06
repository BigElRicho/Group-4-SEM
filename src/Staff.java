
class Staff
{
    private String Email;
    private String Name;
    private String PhoneNumber;
	private String Password;
	
	Staff(String email, String name, String pnum, String pword)
	{
		try 
		{
			setEmail(email);
            setName(name);
            setPhoneNumber(pnum);
            setPassword(pword);
		}
		catch(Exception exc)
		{
			System.out.println(String.format("Unable to create staff profile (%s)", exc));
		}
	}

	/* Getters and Setters */

    public String getEmail() {
		return Email;
	}


	public void setEmail(String email) {
		Email = email;
	}
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPhoneNumber() {
		return PhoneNumber;
	}


	public void setPhoneNumber(String pnum) {
		PhoneNumber = pnum;
	}

	public String getPassword() {
		return Password;
	}


	public void setPassword(String password) {
		Password = password;
	}

	/* Override methods */

    @Override
    public String toString()
    {
        return String.format("Emplopyee: %s (%s)", getName(), getEmail());
    }

}