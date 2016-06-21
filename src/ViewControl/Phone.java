package ViewControl;

import javafx.beans.property.SimpleStringProperty;

public class Phone {

	private SimpleStringProperty s_phone;
	private SimpleStringProperty image;
	
	public Phone(String s_phone, String image)
	{
		this.s_phone = new SimpleStringProperty(s_phone);
		this.image = new SimpleStringProperty(image);
	}
	
	public String getSmartphone()
	{
		return s_phone.get();
	}
	
	public void setSmartphone(String s_phone)
	{
		this.s_phone.set(s_phone);
	}
	
	public void setImage(String image)
	{
		this.image.set(image);
	}
	
	public String getImage()
	{
		return image.get();
	}
	
}
