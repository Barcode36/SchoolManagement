package gui.util;

import com.jfoenix.validation.RequiredFieldValidator;

public class Validators {
	
	public static RequiredFieldValidator getRequiredFieldValidator(){
		RequiredFieldValidator validator = new RequiredFieldValidator();
		validator.setMessage("Campo necess�rio");
		return validator;
	}

}
