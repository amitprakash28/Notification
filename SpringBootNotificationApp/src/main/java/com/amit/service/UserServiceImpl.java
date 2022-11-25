package com.amit.service;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amit.exception.LoginException;
import com.amit.exception.UserNotRegisterException;
import com.amit.models.CurrentUserSession;
import com.amit.models.DetailDto;
import com.amit.models.Notification;
import com.amit.models.User;
import com.amit.repository.SessionDao;
import com.amit.repository.UserDao;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import com.twilio.Twilio;
import com.twilio.type.PhoneNumber;

import com.twilio.rest.api.v2010.account.Message;

@Service
public class UserServiceImpl implements UserService{
	
	
	@Autowired
	private UserDao uDao; 
	
	@Autowired
	private SessionDao sDao; 
	

	@Override
	public User saveUser(User user) {
		if(user.getMessages().size() == 0) {
			User savedUser =  uDao.save(user); 
			return savedUser;
		}
		else {
			throw new LoginException("Without login as user you can't send the Message !"); 
		}
	}

	
	
	@Override
	public ResponseEntity<DetailDto> sendMessage(Notification notification, String uuid) {
		
		//1. check whether the sender is logged or not
		Optional<CurrentUserSession> cusOpt =  sDao.findByUuid(uuid); 
		if(cusOpt.isPresent()) {
			
			//2. check whether the target user exists in the DB or not
			Optional<User> userOpt =  uDao.findByUserId(notification.getReceiverId()); 
			if(userOpt.isPresent()) {
				User targetUser =  userOpt.get();
				String targetUserMobileNumber = targetUser.getUserMobile(); 
				
				
				//3. Then get the details of senderUser 
				CurrentUserSession cus = cusOpt.get(); 
				Integer activeUserId = cus.getUserId();
			    Optional<User> senderOpt = uDao.findByUserId(activeUserId);
			    User senderUser = senderOpt.get(); 
			  
			    /*4. Twilllio codes
			     * -> we have target and sender mobile numbers 
			     * -> we have the message object also
			     * -> write the logic of Twillio to send the message Phone-Phone
			     * */
			    
			    String response = null; 
			    if(notification.getType().equalsIgnoreCase("Mobile SMS")) {
			    	response = UserServiceImpl.sendSmsNotification(targetUserMobileNumber, notification);
			    }
			    else {
			    	try{
			    		response = UserServiceImpl.sendEmailNotification(notification, targetUser.getUserEmail());
			    	}catch(IOException io) {
			    		io.printStackTrace();
			    	}
			    }
			    
				
                notification.setReceiverEmail(targetUser.getUserEmail());
                notification.setReceiverMob(targetUser.getUserMobile());
                senderUser.getMessages().add(notification); 
                uDao.save(senderUser); 
                
                DetailDto dt = new DetailDto(response, LocalDateTime.now()); 
                return new ResponseEntity<DetailDto>(dt, HttpStatus.ACCEPTED); 
			}
			else {
				throw new UserNotRegisterException("The receiver is not registered with the App"); 
			}
			
		}
		else {
			throw new LoginException("You're not logged-in. Please login to use messaging features"); 
		}
	}


	
	//Twilio code
	/*
	* targetMobileNumber also must be registered with the twilio to send sms
	*/
	private static String sendSmsNotification(String targetUserMobileNumber, Notification notification) {
		 Twilio.init("YOUR_TWILIO_SID", "YOUR_TWILIO_AUTH_TOKEN");
		  Message.creator(new PhoneNumber("+91" + targetUserMobileNumber),
                         new PhoneNumber("YOUR_TWILIO_NUMBER"), notification.getMessage()).create();
		return "Sms Sent successfull"; 
	}
	
	
	// Sendgrid code 
	/*
	* receiverEmail also must be registered with the Sendgrid to send email notifications
	*/
	private static String sendEmailNotification(Notification notification, String receiverEmail) throws IOException{
	    Email from = new Email(receiverEmail);
	    System.out.println(receiverEmail);
	    Email to = new Email("hammerjack935@gmail.com"); 
		Content content = new Content("text/html", notification.getMessage());
		Mail mail = new Mail(from, "Email", to, content);
		SendGrid sg = new SendGrid("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");
	    Request request = new Request();
	    request.setMethod(Method.POST);
	    request.setEndpoint("mail/send");
	    request.setBody(mail.build());
	    Response response = sg.api(request);
	    System.out.println(response);
		return "Email sent successful"; 
	}



	@Override
	public ResponseEntity<List<Notification>> getAllMessages(String uuid) {
		Optional<CurrentUserSession> cusOpt =  sDao.findByUuid(uuid); 
		if(cusOpt.isPresent()) {
			Integer userId = cusOpt.get().getUserId(); 
			List<Notification> allMessages =  uDao.findByUserId(userId).get().getMessages(); 
			return new ResponseEntity<List<Notification>>(allMessages, HttpStatus.OK); 
		}
		else {
			throw new LoginException("No User logged in with the uuid : " + uuid);
		}
	}
	
	


}













