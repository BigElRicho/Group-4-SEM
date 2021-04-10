
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

	//This should be called by a menu prompting the user to enter their current and new password.
	//This method will return false if the current password doesnt match, 
	//they should be prompted to enter again
	public boolean ResetPassword(String oldPword, String newPword)
	{
		if(getPassword() == oldPword)
		{
			setPassword(newPword);
			return true;
		}

		return false;
	}

	/* Getters and Setters */

    public String getEmail() 
	{
		return Email;
	}

	//Doesnt need be called externally
	private void setEmail(String email) 
	{
		Email = email;
	}
	
	public String getName() 
	{
		return Name;
	}

	//Doesnt need be called externally
	private void setName(String name) 
	{
		Name = name;
	}

	public String getPhoneNumber() 
	{
		return PhoneNumber;
	}

	//Doesnt need be called externally
	private void setPhoneNumber(String pnum) 
	{
		PhoneNumber = pnum;
	}

	public String getPassword() 
	{
		return Password;
	}

	//Doesnt need be called externally
	private void setPassword(String password) 
	{
		Password = password;
	}

	/* Override methods */

    @Override
    public String toString()
    {
        return String.format("Emplopyee: %s (%s)", getName(), getEmail());
    }

}