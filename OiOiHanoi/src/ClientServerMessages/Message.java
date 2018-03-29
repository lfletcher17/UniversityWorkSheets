package ClientServerMessages;

import java.io.Serializable;

/**
 * An Abstract class which forms the basis of the OIOIHanoi applications communication protocol
 * Extensions of this class are created to handle specific Client / Server interactions. Messages work
 * by a requester constructing a given Message type, sending the Message to the receiver who sets a response before
 * sending back
 * @author lxf736
 * @version 2018-03-01
 */

public abstract class Message implements Serializable {

	private String requestMessage;
	private String responseMessage;
	private Boolean responseSuccess;

	/**
	 * constructor for Message, takes a String identifier of the extended Message type
	 * @param requestMessage
	 */
	public Message(String requestMessage) {
		this.requestMessage = requestMessage;
	}

	/**
	 * @return the requestMessage
	 */
	public String getRequestMessage() {
		return requestMessage;
	}

	/**
	 * @return the responseMessage
	 */
	public String getResponseMessage() {
		return responseMessage;
	}

	/**
	 * @return the responseSuccess
	 */
	public Boolean getResponseSuccess() {
		return this.responseSuccess;
	}

	/**
	 * sets the response of the receiver - i.e. if the Message was successfully handled by the receiver
	 * responseSuccess = true, responseMessage is set to an appropriate value to be sent back to the requester.
	 * @param responseMessage -  the responseMessage
	 * @param responseSuccess - the
	 */
	public void setResponse(String responseMessage, boolean responseSuccess) {
		this.responseMessage = responseMessage;
		this.responseSuccess = responseSuccess;
	}

}
