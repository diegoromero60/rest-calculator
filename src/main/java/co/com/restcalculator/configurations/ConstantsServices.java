package co.com.restcalculator.configurations;

public final class ConstantsServices {
	public static final String SERVICE_CREATE_SESSION_PATH = "/create-session";
	public static final String SERVICE_ADD_NUMBERS_PATH = "/add-numbers";
	public static final String SERVICE_PROCESS_NUMBERS_PATH = "/process-numbers";

	public static final int SUCCES_CODE = 0;
	public static final int ERROR_CODE = 100;
	public static final int ERROR_CODE_SESSION_NOT_FOUND = 101;
	
	public static final String ERROR_MESSAGE = "An Error has been occured, please reintent or contact with the administrator";
	public static final String ERROR_MESSAGE_SESSION_NOT_FOUND = "The Session Code given does not exist";
	public static final String ERROR_MESSAGE_LIST_NUMBERS_EMPTY = "Please add more numbers to process";
	public static final String ERROR_MESSAGE_PROCESSING_NUMBERS = "The operator selected hasn't been processed, the result number is not posible to calculate";
	public static final String SUCCESS_MESSAGE_ADD_NUMBER = "Some numbers has been added successfully";
	public static final String SUCCESS_MESSAGE_PROCESS_NUMBERS = "Some numbers has been added successfully";
	
}
