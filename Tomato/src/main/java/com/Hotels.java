package com;

public class Hotels {

	String id = new String();
	String name = new String(); 
	String location = new String();
	
	public Hotels()
	{}
	
	public Hotels(String id,String name,String location)
	{
		this.id=id;
		this.name=name;
		this.location=location;
	}
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id=id;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name=name;
	}
	
	public String getLocation()
	{
		return location;
	}

	public void setLocation(String location)
	{
		this.location=location;
	}
	
}
