package sms;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// This example requires Gson configured in the build path (for JSON support):
// https://github.com/google/gson
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Main {
	public static final String BASEURL = "https://api.skebby.it/API/v1.0/REST/";

	public static final String MESSAGE_HIGH_QUALITY = "GP";
	public static final String MESSAGE_MEDIUM_QUALITY = "TI";
	public static final String MESSAGE_LOW_QUALITY = "SI";
	public static final String USERNAME = "tagete";
	public static final String PASSWD = "pr08qwe03";
	public static final String MESSAGE_BODY = "Ciao Amore mio." + "\n" + "Test sms per INGSW.";
	public static final String RECIPIENT = "+393409489795";

	public static void main(String[] args) {
		try {
			String[] authKeys = login(USERNAME, PASSWD);

			SendSMSRequest sendSMS = new SendSMSRequest();
			sendSMS.setMessage(MESSAGE_BODY);
			sendSMS.setMessageType(MESSAGE_LOW_QUALITY);
			sendSMS.addRecipient(RECIPIENT);

			if (sendSMS(authKeys, sendSMS))
				System.out.println("SENT");

			else
				System.err.println("no");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This object is used to create an SMS message sending request. The JSon object
	 * is then automatically created starting from an instance of this class, using
	 * GSon.
	 */
	public static class SendSMSRequest {
		/** The message body */
		private String message;

		/** The message type */
		private String message_type;

		/** Should the API return the remaining credits? */
		private boolean returnCredits = false;

		/** The list of recipients */
		private List<String> recipient = new ArrayList<>();

		/** The sender Alias (TPOA) */
		private String sender;

		/** Postpone the SMS message sending to the specified date */
		private Date scheduled_delivery_time;

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public String getMessageType() {
			return message_type;
		}

		public void setMessageType(String messageType) {
			this.message_type = messageType;
		}

		public boolean isReturnCredits() {
			return returnCredits;
		}

		public void setReturnCredits(boolean returnCredits) {
			this.returnCredits = returnCredits;
		}

		public List<String> getRecipient() {
			return recipient;
		}

		public String getSender() {
			return sender;
		}

		public void setSender(String sender) {
			this.sender = sender;
		}

		public Date getScheduledDeliveryTime() {
			return scheduled_delivery_time;
		}

		public void setScheduledDeliveryTime(Date scheduled_delivery_time) {
			this.scheduled_delivery_time = scheduled_delivery_time;
		}

		public void addRecipient(String recipient) {
			this.recipient.add(recipient);
		}
	}

	/**
	 * This class represents the API Response. It is automatically created starting
	 * from the JSON object returned by the server, using GSon
	 */
	public static class SendSMSResponse {
		private String result;
		private String order_id;
		private int total_sent;
		private int remaining_credits;
		private String internal_order_id;

		public String getResult() {
			return result;
		}

		public String getOrderId() {
			return order_id;
		}

		public int getTotalSent() {
			return total_sent;
		}

		public int getRemainingCredits() {
			return remaining_credits;
		}

		public String getInternalOrderId() {
			return internal_order_id;
		}

		public boolean isValid() {
			return "OK".equals(result);
		}
	}

	/**
	 * Authenticates the user given it's username and password. Returns the pair
	 * user_key, Session_key
	 * 
	 * @param username The user username
	 * @param password The user password
	 * @return A list with 2 strings. Index 0 is the user_key, index 1 is the
	 *         Session_key
	 * @throws IOException If an error occurs
	 */
	private static String[] login(String username, String password) throws IOException {
		URL url = new URL(BASEURL + "/login?username=" + username + "&password=" + password);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		conn.setRequestMethod("GET");

		if (conn.getResponseCode() != 200) {
			throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
		}
		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String response = "";
		String output;
		while ((output = br.readLine()) != null) {
			response += output;
		}
		conn.disconnect();

		String[] parts = response.split(";");

		// System.out.println(parts[0] + " : " + parts[1] );
		return parts;
	}

	/**
	 * Sends an SMS message
	 * 
	 * @param authKeys The pair of user_key and Session_key
	 * @param sendSMS  The SendSMS object
	 * @throws IOException If an error occurs
	 */
	private static boolean sendSMS(String[] authKeys, SendSMSRequest sendSMS) throws IOException {
		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		URL url = new URL(BASEURL + "/sms");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();

		// Sending an SMS requires authentication
		conn.setRequestProperty("user_key", authKeys[0]);
		conn.setRequestProperty("Session_key", authKeys[1]);

		conn.setRequestMethod("POST");
		conn.setRequestProperty("Accept", "application/json");
		conn.setRequestProperty("Content-type", "application/json");
		conn.setDoOutput(true);

		String payload = gson.toJson(sendSMS);

		OutputStream os = conn.getOutputStream();
		os.write(payload.getBytes());
		os.flush();

		if (conn.getResponseCode() != 201) {
			String error = "";
			String output;
			BufferedReader errorbuffer = new BufferedReader(new InputStreamReader(conn.getErrorStream(), "UTF-8"));
			while ((output = errorbuffer.readLine()) != null) {
				error += output;
			}
			System.out.println("Error! HTTP error code : " + conn.getResponseCode() + ", \nBody message : " + error);
			throw new RuntimeException("Failed : HTTP error code ");
		}

		BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

		String response = "";
		String output;
		while ((output = br.readLine()) != null) {
			response += output;
		}
		conn.disconnect();

		SendSMSResponse responseObj = gson.fromJson(response, SendSMSResponse.class);

		return responseObj.isValid();
	}
}