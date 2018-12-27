package org.calminfotech.email.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.calminfotech.email.bo.inter.ActivityLogBoInter;
import org.calminfotech.email.bo.inter.BroadCastAllBoInter;
import org.calminfotech.email.bo.inter.ClientTypeBoInter;
import org.calminfotech.email.bo.inter.DepositSmsBoInter;
import org.calminfotech.email.bo.inter.Financial_InstituteBoInter;
import org.calminfotech.email.bo.inter.LoanSmsBoInter;
import org.calminfotech.email.bo.inter.NetWorthBoInter;
import org.calminfotech.email.bo.inter.SetEmailBoInter;
import org.calminfotech.email.bo.inter.SetSMSBoInter;
import org.calminfotech.email.bo.inter.SettingsAuditSMSBoInter;
import org.calminfotech.email.bo.inter.SmsActivityLogBoInter;
import org.calminfotech.email.bo.inter.StockBroadCastBoInter;
import org.calminfotech.email.dao.inter.SettingsAuditSMSDaoInter;
import org.calminfotech.email.form.BroadCastForm;
import org.calminfotech.email.form.ClientTypBrdcstForm;
import org.calminfotech.email.form.ClientTypeForm;
import org.calminfotech.email.form.DepositSmsForm;
import org.calminfotech.email.form.LoanSmsForm;
import org.calminfotech.email.form.NetWorthForm;
import org.calminfotech.email.form.SetEmailForm;
import org.calminfotech.email.form.SetSMSForm;
import org.calminfotech.email.form.SmsClientTypeBrdcstForm;
import org.calminfotech.email.form.SmsForm;
import org.calminfotech.email.form.SmsNetWorthForm;
import org.calminfotech.email.form.SmsStockForm;
import org.calminfotech.email.form.StockBroadCastForm;
import org.calminfotech.email.model.ActivityLogger;
import org.calminfotech.email.model.BroadCastAll;
import org.calminfotech.email.model.ClientType;
import org.calminfotech.email.model.Deposit;
import org.calminfotech.email.model.Loan;
import org.calminfotech.email.model.NetWorth;
import org.calminfotech.email.model.SetSMS;
import org.calminfotech.email.model.SettingsAuditSMS;
import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.email.model.StockBroadCast;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utility.annotations.Layout;
import org.calminfotech.utilities.Alert;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Layout(value = "layouts/default")
public class SmsController {
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Alert alert;
	
	@Autowired
	private DepositSmsBoInter depositSmsBo;
	
	@Autowired
	private LoanSmsBoInter loanSmsBo;
	
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private SmsActivityLogBoInter smsActivityLogBo;
	
	@Autowired
	private BroadCastAllBoInter broadCastAllBoInter;
	
	@Autowired
	private Financial_InstituteBoInter financial_InstituteBoInter;
	
	@Autowired
	private SetEmailBoInter setEmailBoInter;
	
	@Autowired
	private SetSMSBoInter setSMSBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private StockBroadCastBoInter stockBroadCastBoInter;
	
	@Autowired
	private NetWorthBoInter netWorthBoInter;
	
	@Autowired
	private ClientTypeBoInter clientTypeBoInter;
	
	@Autowired
	private SettingsAuditSMSBoInter settingsAuditSMSBo;
	
	String destination="";
	String recipient1 ="";
	
	private String saveDirectory = "C:/Test/Uploadsms/";
	
	String smsDisallowed = "you are not allowed to send sms";
	String smsLimitReached = "you have reached your sms limit";
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/clearlog/{userId}")
	public String clear(@PathVariable("userId") Integer userId, Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
				//User user3 = this.userBo.getUserById(userId);
				//SmsActivityLogger smsActt  =	 smsActivityLogBo.fetchLogByUserId1(userId);
				//smsActt .
			this.smsActivityLogBo.deletebyUserId(userId);
				User user4 = this.userBo.getUserById(userId);
				user4.setSmsmSent(0);
				this.userBo.update(user4);
				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		//model.addAttribute("smsClientTypeBrdcstForm", new SmsClientTypeBrdcstForm());
		return "redirect:/index";
				
			}
			else{
			/* final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
			
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/clearAllLogs")
	public String clearAll(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
				//User user3 = this.userBo.getUserById(userId);
				//SmsActivityLogger smsActt  =	 smsActivityLogBo.fetchLogByUserId1(userId);
				//smsActt .
			this.smsActivityLogBo.deleteLogs();
				/*User user4 = this.userBo.getUserById(userId);
				user4.setSmsmSent(0);
				this.userBo.update(user4);*/
				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		//model.addAttribute("smsClientTypeBrdcstForm", new SmsClientTypeBrdcstForm());
		return "redirect:/listsmslog";
				
			}
			else{
				
				model.addAttribute("smsDisallowed", smsDisallowed);
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");*/return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendsms")
	public String smsForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 //List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
				 //int smsLogLength = smsLog.size();
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){
				model.addAttribute("smsForm", new SmsForm());
				model.addAttribute("smsLimit", user.getSmsLimit());
				model.addAttribute("smsSent", user.getSmsmSent());
					return "/crm/sms/sendsms";
			}
			 else{
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true); 
				 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");*/
				 model.addAttribute("smsLimitReached", smsLimitReached);
				 return "/user/index";}
			}
			 else{
				/* final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true); 
				 JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");*/
				 model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}//end if
		else{
			return "redirect:/";
			}
	}


		
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendsmsbroadcast", method=RequestMethod.POST)
	public String postSmsForm(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("smsForm") SmsForm smsForm
			,@RequestParam CommonsMultipartFile[] attachFile,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		
		 /*List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
		  
		 int smsLogLength = smsLog.size();*/
		
		 System.out.println("id is" + userIdentity.getUserId());
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			System.out.println("id is" + userIdentity.getUserId());
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){
				List <SetSMS> sms = setSMSBo.fetchactive();
		String smsurl = sms.get(0).getUrl();
		String username = sms.get(0).getUserName();
		String password = sms.get(0).getPassword();
		String source = sms.get(0).getSource();
		String dlr = sms.get(0).getDlr();
		String type = sms.get(0).getType();
		int port = sms.get(0).getPort();
		/*String username = "3ck-caml";
	    String password = "calm1234";
	    String source = "calm";
	    String dlr = "1";
	    String type = "0";
	    int port = 8080;*/
		String choice = request.getParameter("choice");
		 String recipient = request.getParameter("to");
		 //String sub = request.getParameter("subject");
		 String message =request.getParameter("message");
		 //String from = "Kend";
		 //String email = "ibrowiz@gmail.com";
		
		
		//test;
		//if iption1 is 1 then return:redirect sendfrom sndto controller sendto/{subjet}/{message}
		 if("multirecipient".equals(choice)){
			//do what you want 
			 System.out.println("i am in multirecipient");
			 System.out.println(recipient);
			 
			 model.addAttribute("smsForm", new SmsForm());	
				//return "redirect:/sendfromsendto/"+recipient+"/"+sub+"/"+mes+"/"+attachFile2;
			 final String[] recipientList1 = recipient.trim().split(",");
				//final InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
				 
				
				for (String destination : recipientList1) {
				  // recipientAddress[counter] = new InternetAddress(recipient1.trim());
				    System.out.println("destination");
				    System.out.println(destination);
				    System.out.println(smsurl);
				   SmsActivityLogger sActivityLogger = new SmsActivityLogger();
				   User user2 = userBo.getUserById(userIdentity.getUserId());
				   //sActivityLogger.setId(smsForm.getId());
				   sActivityLogger.setChoice(smsForm.getChoice());
				   sActivityLogger.setUser(user2);
				   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
				   sActivityLogger.setTo(destination);
				    //activityLogger.setSubject(sub);
				   sActivityLogger.setMessage(message);
				   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.smsActivityLogBo.save(sActivityLogger);
					model.addAttribute("sActivityLogger", sActivityLogger);
				    //this.sendmail1(email,from,recipient1,sub,mes,attachFile2);
				    this.httpRequest(destination,smsurl, message, username, password, source, dlr, type, port);
				   
				  List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
				   int smsLogLength = smsLog.size();
		
				   User logUser = this.userBo.getUserById(userIdentity.getUserId());
				   logUser.setId(userIdentity.getUserId());
				   logUser.setFirstName(userIdentity.getFirstName());
				   logUser.setLastName(userIdentity.getLastName());
				   logUser.setUserName(userIdentity.getUserName());
				   logUser.setUserPassword(userIdentity.getPassword());
				   logUser.setRole(userIdentity.getRole());
				   logUser.setSmsLimit(userIdentity.getSmsLimit());
				   logUser.setSmsmSent(smsLogLength);
				   logUser.setCreatedBy(userIdentity.getCreatedBy());
				   logUser.setDateCreated(userIdentity.getDateCreated());
				   logUser.setModifiedBy(userIdentity.getModifiedBy());
				   logUser.setDateModified(userIdentity.getDateModified());
				   logUser.setStatus("active");
				   logUser.setIsDeleted(userIdentity.getIsDeleted());
				   this.userBo.update(logUser);
				   
				   
				   
				  			   
		 }
				int Alength = recipientList1.length;
				/*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true); 
				JOptionPane.showMessageDialog(dialog , "Message sent to " + Alength + " recipient(s)");*/
				/*this.alert.setAlert(redirectAttributes, Alert.SUCCESS,
						"Message sent to " + Alength + " recipient(s)!");*/
				String successMsg = "Sent to " + Alength + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
				 return "/crm/sms/sendsms";	
		 }
		 int lines = 0;	
		
		 
		 if("csvupload".equals(choice)){
			 System.out.println("i am in csvrecipient");
			 
			 
				
				if (attachFile != null && attachFile.length > 0) {
					
					for (CommonsMultipartFile aFile : attachFile){
						
						System.out.println("Saving file: " + aFile.getOriginalFilename());
						
						if (!aFile.getOriginalFilename().equals("")) {
							
							
							String[] parts = aFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");
							String part1 = parts[0]; // 004
							String part2 = parts[1]; // 034556
							
							Date date = new Date();
							SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhmmss");
							String formattedDate = sdf.format(date);
							System.out.println(formattedDate);
							// System.out.println(System.getProperty("user.dir"));
							
							//System.out.println(date);
							String newbasename =part1.concat(formattedDate);
							String ext = part2;
							 String newname1=newbasename+"."+ext;
							 System.out.println(newbasename);
							 System.out.println(newname1);
			//newname1=newname1.replace(" ","");	
			
							aFile.transferTo(new File(saveDirectory + newname1));
							
							System.out.println("start");
							System.out.println(newname1);
							//System.out.println(sub);
							System.out.println(message);
							System.out.println("end");
							
							 //return "redirect:/sendfromcsv/" +newname1+"/"+sub+"/"+mes;
							BufferedReader reader =new BufferedReader(new FileReader("C:\\Test\\Uploadsms\\"+newname1));
							while((destination=reader.readLine())!=null){
					           lines++;
					         
					            System.out.println(destination);
					            User user2 = userBo.getUserById(userIdentity.getUserId());
					            SmsActivityLogger sActivityLogger = new SmsActivityLogger();
								   sActivityLogger.setChoice(smsForm.getChoice());
								   sActivityLogger.setUser(user2);
								   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
								   sActivityLogger.setTo(destination);
								    //activityLogger.setSubject(sub);
								   sActivityLogger.setMessage(message);
								   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
								    this.smsActivityLogBo.save(sActivityLogger);
									model.addAttribute("sActivityLogger", sActivityLogger);
					            
					            //sendmail1(email,from,addr,sub,mes,attachFile2);
					            this.httpRequest(destination, smsurl, message, username, password, source, dlr, type, port);
					            
					            List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
								   int smsLogLength = smsLog.size();
						
								   User logUser = this.userBo.getUserById(userIdentity.getUserId());
								   logUser.setId(userIdentity.getUserId());
								   logUser.setFirstName(userIdentity.getFirstName());
								   logUser.setLastName(userIdentity.getLastName());
								   logUser.setUserName(userIdentity.getUserName());
								   logUser.setUserPassword(userIdentity.getPassword());
								   logUser.setRole(userIdentity.getRole());
								   logUser.setSmsLimit(userIdentity.getSmsLimit());
								   logUser.setSmsmSent(smsLogLength);
								   logUser.setCreatedBy(userIdentity.getCreatedBy());
								   logUser.setDateCreated(userIdentity.getDateCreated());
								   logUser.setModifiedBy(userIdentity.getModifiedBy());
								   logUser.setDateModified(userIdentity.getDateModified());
								   logUser.setStatus("active");
								   logUser.setIsDeleted(userIdentity.getIsDeleted());
								   this.userBo.update(logUser);
					            
							}
							model.addAttribute("smsForm", new SmsForm());
							/*final JDialog dialog = new JDialog();
							 dialog.setAlwaysOnTop(true);
							JOptionPane.showMessageDialog(dialog ,  "Message sent to " + lines + " recipient(s)");*/
							String successMsg = "Sent to " + lines + " recipient(s)";
							model.addAttribute("successMsg", successMsg);
							reader.close();
						}		 
		
					}
				}
				/*int Alength = addr.length();
				JOptionPane.showMessageDialog(new JFrame() , "Message sent to " + Alength + " recipient(s)");*/
			 model.addAttribute("smsForm", new SmsForm());
			 return "/crm/sms/sendsms";
			
			}
		 
		 if("fromview".equals(choice)){
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 
				 List <BroadCastAll> allclient = broadCastAllBoInter.fetchAll();
				 for(BroadCastAll g : allclient ){
					 System.out.println("Check out "+g.getEmailAddress());
					 System.out.println("Check out "+g.getName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   
					   sActivityLogger.setChoice(smsForm.getChoice());
					   sActivityLogger.setUser(user2);
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(g.getTel());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[name]",g.getName());
						try{
							message = message.replace("[email]",g.getEmailAddress());
						}catch(Exception e ){
							e.printStackTrace();
						}
						
						
					 System.out.println(message);
					 
					 
					// this.sendmail1(email,from,g.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
			            this.httpRequest(g.getTel(), smsurl, message, username, password, source, dlr, type, port);
			            
			            List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
						   int smsLogLength = smsLog.size();
				
						   User logUser = this.userBo.getUserById(userIdentity.getUserId());
						   logUser.setId(userIdentity.getUserId());
						   logUser.setFirstName(userIdentity.getFirstName());
						   logUser.setLastName(userIdentity.getLastName());
						   logUser.setUserName(userIdentity.getUserName());
						   logUser.setUserPassword(userIdentity.getPassword());
						   logUser.setRole(userIdentity.getRole());
						   logUser.setSmsLimit(userIdentity.getSmsLimit());
						   logUser.setSmsmSent(smsLogLength);
						   logUser.setCreatedBy(userIdentity.getCreatedBy());
						   logUser.setDateCreated(userIdentity.getDateCreated());
						   logUser.setModifiedBy(userIdentity.getModifiedBy());
						   logUser.setDateModified(userIdentity.getDateModified());
						   logUser.setStatus("active");
						   logUser.setIsDeleted(userIdentity.getIsDeleted());
						   this.userBo.update(logUser);

				 }
				 System.out.println(allclient.size());
				/* final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + allclient.size() + " recipient(s)");*/
				 
				 String successMsg = "Sent to " + allclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
				
					model.addAttribute("broadCastForm", new BroadCastForm());
					
					return "/crm/sms/sendsms";	
		
		}//end if fromview
		 return null;
				 }
				 else{
					 /*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";
					 /*return "redirect:/index";*/}
		}
		 else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			 JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
			 model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
		}//end if
		else{return "redirect:/index";}
	}//end newBroadcastAtchFormpost method
		

	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/stockbroadcastsms")
	public String stockBroadcastsms(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
				model.addAttribute("smsLimit", user.getSmsLimit());
				model.addAttribute("smsSent", user.getSmsmSent());
		model.addAttribute("smsStockForm", new SmsStockForm());
		//return "/crm/sms/clientstocksms";
		
				}
				 else{
					 /*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
			}
			 else{
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/	
				 model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}

		
		}//end if
		else{
			return "redirect:/";
		}
		return "/crm/sms/clientstocksms";
	}

	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendstockbroadcastsms", method=RequestMethod.POST)
	public String stockBroadcastAtchForm(HttpServletRequest request,Model model,
			@Valid @ModelAttribute("smsStockForm") SmsStockForm smsStockForm,HttpSession session,
			BindingResult result,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		//get request parameter tovar
		//CommonsMultipartFile[] fil = request.getParameter("attachFile");
		//List <ClientNb> client = clientNbBoInter.fetchAll();
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				List <SetSMS> sms = setSMSBo.fetch();
		String smsurl = sms.get(0).getUrl();
		String username = sms.get(0).getUserName();
		String password = sms.get(0).getPassword();
		String source = sms.get(0).getSource();
		String dlr = sms.get(0).getDlr();
		String type = sms.get(0).getType();
		int port = sms.get(0).getPort();
		String message =request.getParameter("message");
		
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 
				 List <StockBroadCast> allstockclient = stockBroadCastBoInter.fetchBankId(smsStockForm.getStockCode());
				 for(StockBroadCast f : allstockclient ){
					 System.out.println("Check out "+f.getEmailAddress());
					 System.out.println("Check out "+f.getFirstName());
					 System.out.println("Check out "+f.getSurName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   //sActivityLogger.setId(smsStockForm.getClientId());
					   //sActivityLogger.setChoice(smsForm.getChoice());
					   sActivityLogger.setUser(user2);
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(f.getPhoneNumber());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[fname]",f.getFirstName()).replace("[Lname]",f.getSurName()).replace("[email]",f.getEmailAddress())
								.replace("[stock]",f.getStockCode());
					 System.out.println(message);
					 
					// this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
			           this.httpRequest(f.getPhoneNumber(), smsurl, message, username, password, source, dlr, type, port);

			            List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
						   int smsLogLength = smsLog.size();
				
						   User logUser = this.userBo.getUserById(userIdentity.getUserId());
						   logUser.setId(userIdentity.getUserId());
						   logUser.setFirstName(userIdentity.getFirstName());
						   logUser.setLastName(userIdentity.getLastName());
						   logUser.setUserName(userIdentity.getUserName());
						   logUser.setUserPassword(userIdentity.getPassword());
						   logUser.setRole(userIdentity.getRole());
						   logUser.setSmsLimit(userIdentity.getSmsLimit());
						   logUser.setSmsmSent(smsLogLength);
						   logUser.setCreatedBy(userIdentity.getCreatedBy());
						   logUser.setDateCreated(userIdentity.getDateCreated());
						   logUser.setModifiedBy(userIdentity.getModifiedBy());
						   logUser.setDateModified(userIdentity.getDateModified());
						   logUser.setStatus("active");
						   logUser.setIsDeleted(userIdentity.getIsDeleted());
						   this.userBo.update(logUser);
			            
				 }
				 System.out.println(allstockclient.size());
				/* final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + allstockclient.size() + " recipient(s)");*/
				 //model.addAttribute("stockBroadCastForm", new StockBroadCastForm());
				 String successMsg = "Sent to " + allstockclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
					return "/crm/sms/clientstocksms";
				 }
				 else{
					/* final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
			}
			else{
			/* final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/	
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
			}//endif
		else{
			return "redirect:/";
			}
	}//end newBroadcastAtchFormpost method
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/networthsms")
	public String netWorthsms(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("smsNetWorthForm", new SmsNetWorthForm());
		model.addAttribute("smsLimit", user.getSmsLimit());
		model.addAttribute("smsSent", user.getSmsmSent());
		return "/crm/sms/networthsms";

				}
				 else{
					/* final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
			}
			else{
			/* final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendnetworthsms", method=RequestMethod.POST)
	public String networthForm(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("smsNetWorthForm") SmsNetWorthForm smsNetWorthForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

		//get request parameter tovar
		//CommonsMultipartFile[] fil = request.getParameter("attachFile");
		//List <ClientNb> client = clientNbBoInter.fetchAll();
		List <SetSMS> sms = setSMSBo.fetch();
		String smsurl = sms.get(0).getUrl();
		String username = sms.get(0).getUserName();
		String password = sms.get(0).getPassword();
		String source = sms.get(0).getSource();
		String dlr = sms.get(0).getDlr();
		String type = sms.get(0).getType();
		int port = sms.get(0).getPort();
		 String message =request.getParameter("message");
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 
				 List <NetWorth> alltypeclient = netWorthBoInter.fetchNetWorth(smsNetWorthForm.getNetWorthFrom(),smsNetWorthForm.getNetWorthTo());
				 for(NetWorth w : alltypeclient ){
					 System.out.println("Check out "+w.getEmailAddress());
					 System.out.println("Check out "+w.getFirstName());
					 System.out.println("Check out "+w.getSurName());
					 System.out.println("Check out "+w.getEmailAddress());
					 System.out.println("Check out "+w.getFirstName());
					 System.out.println("Check out "+w.getSurName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   //sActivityLogger.setChoice(smsForm.getChoice());
					   sActivityLogger.setUser(user2);
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(w.getPhoneNumber());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[fname]",w.getFirstName()).replace("[Lname]",w.getSurName()).replace("[email]",w.getEmailAddress()).replace("[worth]",String.valueOf(w.getNetWorth()));
							
					 System.out.println(message);
					 
					 
					 //this.sendmail1(email,from,w.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
					 this.httpRequest(w.getPhoneNumber(), smsurl, message, username, password, source, dlr, type, port);
				 
					 List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
					   int smsLogLength = smsLog.size();
			
					   User logUser = this.userBo.getUserById(userIdentity.getUserId());
					   logUser.setId(userIdentity.getUserId());
					   logUser.setFirstName(userIdentity.getFirstName());
					   logUser.setLastName(userIdentity.getLastName());
					   logUser.setUserName(userIdentity.getUserName());
					   logUser.setUserPassword(userIdentity.getPassword());
					   logUser.setRole(userIdentity.getRole());
					   logUser.setSmsLimit(userIdentity.getSmsLimit());
					   logUser.setSmsmSent(smsLogLength);
					   logUser.setCreatedBy(userIdentity.getCreatedBy());
					   logUser.setDateCreated(userIdentity.getDateCreated());
					   logUser.setModifiedBy(userIdentity.getModifiedBy());
					   logUser.setDateModified(userIdentity.getDateModified());
					   logUser.setStatus("active");
					   logUser.setIsDeleted(userIdentity.getIsDeleted());
					   this.userBo.update(logUser);
					 
				 }
				 System.out.println(alltypeclient.size());
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + alltypeclient.size() + " recipient(s)");*/
				 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
					
				 return "/crm/sms/networthsms";
			}
			 else{
			 /*final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
			 return "redirect:/index";}*/
				 model.addAttribute("smsLimitReached", smsLimitReached);
				 return "/user/index";}
			}
			else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
		// }
		//end if fromview
		// return null;
	}//end newBroadcastAtchFormpost method
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/client_typesms")
	public String clientTypesms(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("smsClientTypeBrdcstForm", new SmsClientTypeBrdcstForm());
		model.addAttribute("smsLimit", user.getSmsLimit());
		model.addAttribute("smsSent", user.getSmsmSent());
		return "/crm/sms/client_typesms";
				}
				 else{
					 /*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
			}
			else{
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendclienttypesms", method=RequestMethod.POST)
	public String clienttypeForm(HttpServletRequest request,Model model,
			@Valid @ModelAttribute("smsClientTypeBrdcstForm") SmsClientTypeBrdcstForm smsClientTypeBrdcstForm,
			BindingResult result,HttpSession session,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

			//get request parameter tovar
		//CommonsMultipartFile[] fil = request.getParameter("attachFile");
		//List <ClientNb> client = clientNbBoInter.fetchAll();
		//List <SetEmail> mail = setEmailBoInter.fetch();
		//String email = mail.get(0).getEmailAddress();
		//String from = mail.get(0).getName();
		//String choice = request.getParameter("choice");
		// String recipient = request.getParameter("BankId");
		 //String sub = request.getParameter("subject");
		//String clientType =request.getParameter("clientType");
		 String message =request.getParameter("message");
		 List <SetSMS> sms = setSMSBo.fetch();
		 String smsurl = sms.get(0).getUrl();
			String username = sms.get(0).getUserName();
			String password = sms.get(0).getPassword();
			String source = sms.get(0).getSource();
			String dlr = sms.get(0).getDlr();
			String type = sms.get(0).getType();
			int port = sms.get(0).getPort();
			
		
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 System.out.println("check here "+smsClientTypeBrdcstForm.getClientType());
				 List <ClientType> alltypeclient = clientTypeBoInter.fetchType(smsClientTypeBrdcstForm.getClientType());
				 for(ClientType z : alltypeclient ){
					 System.out.println("Check out "+z.getEmailAddress());
					 System.out.println("Check out "+z.getFirstName());
					 System.out.println("Check out "+z.getSurName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   //sActivityLogger.setId(smsClientTypeBrdcstForm.getClientId());
					   //sActivityLogger.setChoice(smsForm.getChoice());
					   sActivityLogger.setUser(user2);
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(z.getPhoneNumber());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[fname]",z.getFirstName()).replace("[Lname]",z.getSurName()).replace("[email]",z.getEmailAddress());
							
					 System.out.println(message);
					 
					 //this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
					 this.httpRequest(z.getPhoneNumber(), smsurl, message, username, password, source, dlr, type, port);
				
					 List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
					   int smsLogLength = smsLog.size();
			
					   User logUser = this.userBo.getUserById(userIdentity.getUserId());
					   logUser.setId(userIdentity.getUserId());
					   logUser.setFirstName(userIdentity.getFirstName());
					   logUser.setLastName(userIdentity.getLastName());
					   logUser.setUserName(userIdentity.getUserName());
					   logUser.setUserPassword(userIdentity.getPassword());
					   logUser.setRole(userIdentity.getRole());
					   logUser.setSmsLimit(userIdentity.getSmsLimit());
					   logUser.setSmsmSent(smsLogLength);
					   logUser.setCreatedBy(userIdentity.getCreatedBy());
					   logUser.setDateCreated(userIdentity.getDateCreated());
					   logUser.setModifiedBy(userIdentity.getModifiedBy());
					   logUser.setDateModified(userIdentity.getDateModified());
					   logUser.setStatus("active");
					   logUser.setIsDeleted(userIdentity.getIsDeleted());
					   this.userBo.update(logUser);
					 
				 }
				 System.out.println(alltypeclient.size());
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + alltypeclient.size() + " recipient(s)");*/
				 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
				 model.addAttribute("clientTypBrdcstForm", new ClientTypBrdcstForm());
					
			 return "/crm/sms/client_typesms";
			}
			 else{
			/* final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
			 return "redirect:/index";}*/
				 model.addAttribute("smsLimitReached", smsLimitReached);
				 return "/user/index";}
			}
			else{
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
		// }
		//end if fromview
		// return null;
	}//end newBroadcastAtchFormpost method

	@Layout(value = "layouts/default")
	@RequestMapping(value = "/loanclientsms")
	public String loansms(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("loanSmsForm", new LoanSmsForm());
		model.addAttribute("smsLimit", user.getSmsLimit());
		model.addAttribute("smsSent", user.getSmsmSent());
		return "/crm/sms/loan_sms";
				}
				 else{
					 /*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
		}
		else{
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/sendloanclientsms", method=RequestMethod.POST)
	public String loanForm(HttpServletRequest request,Model model,
			@Valid @ModelAttribute("loanSmsForm") LoanSmsForm loanSmsForm,
			BindingResult result,HttpSession session,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

			//get request parameter tovar
		//CommonsMultipartFile[] fil = request.getParameter("attachFile");
		//List <ClientNb> client = clientNbBoInter.fetchAll();
		//List <SetEmail> mail = setEmailBoInter.fetch();
		//String email = mail.get(0).getEmailAddress();
		//String from = mail.get(0).getName();
		//String choice = request.getParameter("choice");
		// String recipient = request.getParameter("BankId");
		 //String sub = request.getParameter("subject");
		//String clientType =request.getParameter("clientType");
		 String message =request.getParameter("message");
		 List <SetSMS> sms = setSMSBo.fetch();
		 String smsurl = sms.get(0).getUrl();
			String username = sms.get(0).getUserName();
			String password = sms.get(0).getPassword();
			String source = sms.get(0).getSource();
			String dlr = sms.get(0).getDlr();
			String type = sms.get(0).getType();
			int port = sms.get(0).getPort();
		
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 //System.out.println("check here "+LoanSmsForm.get);
				 List <Loan> allloanclient = loanSmsBo.fetchLoanClients();
				 for(Loan z : allloanclient ){
					 System.out.println("Check out "+z.getEmailAddress());
					 System.out.println("Check out "+z.getFirstName());
					 System.out.println("Check out "+z.getSurName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   sActivityLogger.setUser(user2);
					   //sActivityLogger.setChoice(smsForm.getChoice());
					   //sActivityLogger.setAttachFile(smsStockForm.getAttachFile());
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(z.getPhoneNumber());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[fname]",z.getFirstName()).replace("[Lname]",z.getSurName()).replace("[email]",z.getEmailAddress());
							
					 System.out.println(message);
					 
					 //this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
					 this.httpRequest(z.getPhoneNumber(), smsurl, message, username, password, source, dlr, type, port);
				 
					 List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
					   int smsLogLength = smsLog.size();
			
					   User logUser = this.userBo.getUserById(userIdentity.getUserId());
					   logUser.setId(userIdentity.getUserId());
					   logUser.setFirstName(userIdentity.getFirstName());
					   logUser.setLastName(userIdentity.getLastName());
					   logUser.setUserName(userIdentity.getUserName());
					   logUser.setUserPassword(userIdentity.getPassword());
					   logUser.setRole(userIdentity.getRole());
					   logUser.setSmsLimit(userIdentity.getSmsLimit());
					   logUser.setSmsmSent(smsLogLength);
					   logUser.setCreatedBy(userIdentity.getCreatedBy());
					   logUser.setDateCreated(userIdentity.getDateCreated());
					   logUser.setModifiedBy(userIdentity.getModifiedBy());
					   logUser.setDateModified(userIdentity.getDateModified());
					   logUser.setStatus("active");
					   logUser.setIsDeleted(userIdentity.getIsDeleted());
					   this.userBo.update(logUser);
					 
				 }
				 System.out.println(allloanclient.size());
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + allloanclient.size() + " recipient(s)");*/
				 String successMsg = "Sent to " + allloanclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
				 //model.addAttribute("clientTypBrdcstForm", new ClientTypBrdcstForm());
					
			 return "/crm/sms/loan_sms";
			}
			 else{
			/* final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
			 return "redirect:/index";}*/
				 model.addAttribute("smsLimitReached", smsDisallowed);
				 return "/user/index";}
			}
			else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
		// }
		//end if fromview
		// return null;
	}//end newBroadcastAtchFormpost method

	@Layout(value = "layouts/default")
	@RequestMapping(value = "/depositclientsms")
	public String depositsms(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

				//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("depositSmsForm", new DepositSmsForm());
		model.addAttribute("smsLimit", user.getSmsLimit());
		model.addAttribute("smsSent", user.getSmsmSent());
		return "/crm/sms/deposit_sms";
				}
				 else{
					 /*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true); 
					 JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
					 return "redirect:/index";}*/
					 model.addAttribute("smsLimitReached", smsLimitReached);
					 return "/user/index";}
		}
		else{
/*		 final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send sms");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/senddepositclientsms", method=RequestMethod.POST)
	public String depositForm(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("depositSmsForm") DepositSmsForm depositSmsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			//,@RequestParam CommonsMultipartFile attachFile2
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))||("sms".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
				 User logUsers = this.userBo.getUserById(userIdentity.getUserId());
				 if(("admin".equals(logUsers.getRole()))||(logUsers.getSmsmSent()<logUsers.getSmsLimit())){

			//get request parameter tovar
		//CommonsMultipartFile[] fil = request.getParameter("attachFile");
		//List <ClientNb> client = clientNbBoInter.fetchAll();
		//List <SetEmail> mail = setEmailBoInter.fetch();
		//String email = mail.get(0).getEmailAddress();
		//String from = mail.get(0).getName();
		//String choice = request.getParameter("choice");
		// String recipient = request.getParameter("BankId");
		 //String sub = request.getParameter("subject");
		//String clientType =request.getParameter("clientType");
		 String message =request.getParameter("message");
		 List <SetSMS> sms = setSMSBo.fetchactive();
		 String smsurl = sms.get(0).getUrl();
			String username = sms.get(0).getUserName();
			String password = sms.get(0).getPassword();
			String source = sms.get(0).getSource();
			String dlr = sms.get(0).getDlr();
			String type = sms.get(0).getType();
			int port = sms.get(0).getPort();		
			 String myhold ="";
			//String hold ="";
				 System.out.println("i am in yanchi");
				 //System.out.println("check here "+LoanSmsForm.get);
				 List <Deposit> alldepositclient = depositSmsBo.fetchDepositClients();
				 for(Deposit z : alldepositclient ){
					 System.out.println("Check out "+z.getEmailAddress());
					 System.out.println("Check out "+z.getFirstName());
					 System.out.println("Check out "+z.getSurName());
					 User user2 = userBo.getUserById(userIdentity.getUserId());
					 SmsActivityLogger sActivityLogger = new SmsActivityLogger();
					   sActivityLogger.setUser(user2);
					   //sActivityLogger.setChoice(smsForm.getChoice());
					   //sActivityLogger.setAttachFile(smsStockForm.getAttachFile());
					   // activityLogger.setAttachFile2(smsForm.getAttachFile2());
					   sActivityLogger.setTo(z.getPhoneNumber());
					    //activityLogger.setSubject(sub);
					   sActivityLogger.setMessage(message);
					   sActivityLogger.setDateSent(new GregorianCalendar().getTime());
					    this.smsActivityLogBo.save(sActivityLogger);
						model.addAttribute("sActivityLogger", sActivityLogger);
						message = message.replace("[fname]",z.getFirstName()).replace("[Lname]",z.getSurName()).replace("[email]",z.getEmailAddress());
							
					 System.out.println(message);
					 
					 //this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
					 //this.sendmail1(recipient1,sub,mes,attachFile2);
					 this.httpRequest(z.getPhoneNumber(), smsurl, message, username, password, source, dlr, type, port);
				 
					 List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchLogByUserId(userIdentity.getUserId());
					   int smsLogLength = smsLog.size();
			
					   User logUser = this.userBo.getUserById(userIdentity.getUserId());
					   logUser.setId(userIdentity.getUserId());
					   logUser.setFirstName(userIdentity.getFirstName());
					   logUser.setLastName(userIdentity.getLastName());
					   logUser.setUserName(userIdentity.getUserName());
					   logUser.setUserPassword(userIdentity.getPassword());
					   logUser.setRole(userIdentity.getRole());
					   logUser.setSmsLimit(userIdentity.getSmsLimit());
					   logUser.setSmsmSent(smsLogLength);
					   logUser.setCreatedBy(userIdentity.getCreatedBy());
					   logUser.setDateCreated(userIdentity.getDateCreated());
					   logUser.setModifiedBy(userIdentity.getModifiedBy());
					   logUser.setDateModified(userIdentity.getDateModified());
					   logUser.setStatus("active");
					   logUser.setIsDeleted(userIdentity.getIsDeleted());
					   this.userBo.update(logUser);
				 
				 }
				 System.out.println(alldepositclient.size());
				 /*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog ,  "Message sent to " + alldepositclient.size() + " recipient(s)");*/
				 String successMsg = "Sent to " + alldepositclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
				 //model.addAttribute("clientTypBrdcstForm", new ClientTypBrdcstForm());
					
			 return "/crm/sms/depositclientsms";
			}
			 else{
			 /*final JDialog dialog = new JDialog();
			dialog.setAlwaysOnTop(true); 
			JOptionPane.showMessageDialog(dialog ,  "you have reached your sms Limit");
			 return "redirect:/index";}*/
				 model.addAttribute("smsLimitReached", smsLimitReached);
				 return "/user/index";}
			}
			else{
				model.addAttribute("smsDisallowed", smsDisallowed);
				 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/listsms")
	public String viewActn(Model model,HttpSession session) {
			if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
				User user = this.userBo.getUserById(this.userIdentity.getUserId());
				if(("admin".equals(user.getRole()))){
				List<SetSMS> setsms = this.setSMSBo.fetch();
		model.addAttribute("setsms",setsms );
		return "/crm/sms/listsms";
				}
		else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "Restricted Acess");return "redirect:/index";}*/
			model.addAttribute("restriction", "Restricted Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/listsmslog")
	public String viewSmsLog(Model model,HttpSession session) {
			if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
				User user = this.userBo.getUserById(this.userIdentity.getUserId());
				if(("admin".equals(user.getRole()))){
				List<SmsActivityLogger> smsLog = this.smsActivityLogBo.fetchAllSmsLog();
		model.addAttribute("smsLog",smsLog );
		return "/crm/sms/listsmslog";
				}
		else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "Restricted Acess");return "redirect:/index";}*/
			model.addAttribute("restriction", "Restricted Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/smsauditsettings")
	public String view(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		model.addAttribute("setsmsaudit", this.settingsAuditSMSBo.fetch());
		return "/crm/sms/listsmsaudit";
			}
			else{
				/* final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog ,  "Restricted Access");return "redirect:/index";}*/
				model.addAttribute("restriction", "Restricted Area");
				 return "/user/index";}

		}
		else{
			return "redirect:/";
			}
	}
	
	
	@RequestMapping(value =  "/addsmsvendor", method = RequestMethod.GET)
	public String addpenForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			model.addAttribute("setSMSForm", new SetSMSForm());
		return "/crm/sms/addsmsvendor";
			}
			else{
				/* final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog ,  "Restricted Access");return "redirect:/index";}*/
				model.addAttribute("restriction", "Restricted Area");
				 return "/user/index";}

		}
		else{
			return "redirect:/";
			}
	}
	
	
	@RequestMapping(value = "/savesmsvendor")
	public String addAction(
			@Valid @ModelAttribute("setSMSForm") SetSMSForm setSMSForm,
			BindingResult result, Model model,HttpSession session,
			RedirectAttributes redirectAttributes) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		SetSMS setSMS  = new SetSMS();
		System.out.println("in now");
		setSMS.setVendor(setSMSForm.getVendor());
		setSMS.setUrl(setSMSForm.getUrl());
		setSMS.setUserName(setSMSForm.getUserName());
		setSMS.setPassword(setSMSForm.getPassword());
		setSMS.setSource(setSMSForm.getSource());
		setSMS.setDlr(setSMSForm.getDlr());
		setSMS.setType(setSMSForm.getDlr());
		setSMS.setPort(setSMSForm.getPort());
		setSMS.setCreatedBy((Integer)session.getAttribute("userId"));
		setSMS.setCreatedDate(new GregorianCalendar().getTime());
		setSMS.setModifiedBy(setSMSForm.getModifiedBy());
		setSMS.setLastModifiedDate(setSMSForm.getLastModifiedDate());
		setSMS.setStatus("active");
		System.out.println("done");
		
		setSMSBo.add(setSMS);
		System.out.println("done did it");

		return "redirect:/listsms";
		}
		else{
			return "redirect:/";
			}
	}


	@RequestMapping(value = "/showEdt/{Id}", method = RequestMethod.GET)
	public String editAct(@PathVariable("Id") Integer Id, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		SetSMS setSMS = this.setSMSBo.getSMSViaId(Id);
		if (null == setSMS) {

			return "redirect:/home";
		}

		SetSMSForm setSMSForm = new SetSMSForm();
		
		setSMSForm.setId(setSMS.getId());
		
		setSMSForm.setVendor(setSMS.getVendor());
		
		setSMSForm.setUrl(setSMS.getUrl());
		
		setSMSForm.setUserName(setSMS.getUserName());
		
		setSMSForm.setPassword(setSMS.getPassword());
		
		setSMSForm.setSource(setSMS.getSource());
		
		setSMSForm.setDlr(setSMS.getDlr());
		
		setSMSForm.setType(setSMS.getType());
		
		setSMSForm.setPort(setSMS.getPort());
		
		setSMSForm.setCreatedBy(setSMS.getCreatedBy());
		
		setSMSForm.setCreatedDate(setSMS.getCreatedDate());
		
		setSMSForm.setModifiedBy(setSMS.getModifiedBy());

		setSMSForm.setLastModifiedDate(setSMS.getLastModifiedDate());
		
		//setSMSForm.setStatus("active");
				
		model.addAttribute("setSMSForm", setSMSForm);

		return "/crm/sms/smssettings";
		}
		else{
			return "redirect:/";
			}
	}

	@RequestMapping(value = "/update/{Id}", method = RequestMethod.POST)
	public String updateAct(
			@Valid @ModelAttribute("setSMSForm") SetSMSForm setSMSForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){

		if (result.hasErrors()) {
			System.out.println("can't update");
			return "redirect:/index";
		}
		
		SetSMS setSMS = new SetSMS();
		
		setSMS.setId(setSMSForm.getId());
		
		setSMS.setVendor(setSMSForm.getVendor());
		
		setSMS.setUrl(setSMSForm.getUrl());
		
		setSMS.setUserName(setSMSForm.getUserName());
		
		setSMS.setPassword(setSMSForm.getPassword());
		
		setSMS.setSource(setSMSForm.getSource());
		
		setSMS.setDlr(setSMSForm.getDlr());
		
		setSMS.setType(setSMSForm.getType());
		
		setSMS.setPort(setSMSForm.getPort());
		
		setSMS.setCreatedBy(setSMSForm.getCreatedBy());
		
		setSMS.setCreatedDate(setSMSForm.getCreatedDate());
		
		setSMS.setModifiedBy(userIdentity.getUserId());
		
		setSMS.setLastModifiedDate(new GregorianCalendar().getTime());
		
		setSMS.setStatus(setSMSForm.getStatus());
		
		this.setSMSBo.update(setSMS);
		
		SettingsAuditSMS auditSMS = new SettingsAuditSMS();
		auditSMS.setVendor(setSMS.getVendor());
		auditSMS.setUrl(setSMS.getUrl());
		auditSMS.setUserName(setSMS.getUserName());
		auditSMS.setPassword(setSMS.getPassword());
		auditSMS.setSource(setSMS.getSource());
		auditSMS.setDlr(setSMS.getDlr());
		auditSMS.setType(setSMS.getType());
		auditSMS.setPort(setSMS.getPort());
		auditSMS.setStatus(setSMS.getStatus());
		auditSMS.setModifiedBy((Integer)session.getAttribute("userId"));
		auditSMS.setModifiedDate(new GregorianCalendar().getTime());
		
		this.settingsAuditSMSBo.add(auditSMS);
		 return "redirect:/listsms";
		}
		else{
			return "redirect:/";
			}
	}

	
	
	public void httpRequest(String destination,String smsurl,
    String message,
    String username,
    String password,
    String source,
    String dlr,
    String type,
    int port) {
		
		 
	        try {
	         
	                String requestUrl  = smsurl +
	                					 "username=" +  URLEncoder.encode(username, "UTF-8")  +
	                					 "&password=" + URLEncoder.encode(password, "UTF-8") +
	                					 "&type=" + URLEncoder.encode(type, "UTF-8") +
	                					 "&dlr=" + URLEncoder.encode(dlr, "UTF-8") + 
	                					  "&destination=" + URLEncoder.encode(destination, "UTF-8") +
	                					  "&source=" + URLEncoder.encode(source, "UTF-8") +
	                					 "&message=" + URLEncoder.encode(message, "UTF-8");
	                
	                System.out.println(requestUrl);
	                
	                System.out.println(message);
	                URL url = new URL(requestUrl);
	                HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	                uc.connect();
	                System.out.println(uc.getResponseMessage());

	                

	        } catch(Exception ex) {
	                System.out.println(ex.getMessage());

	        }
	        
		}
		
		
		
	}

	
