package org.calminfotech.email.controller;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;
import javax.mail.Message;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.commons.lang.StringUtils;
import org.calminfotech.email.bo.inter.ActivityLogBoInter;
import org.calminfotech.email.bo.inter.BroadCastAllBoInter;
import org.calminfotech.email.bo.inter.ClientBoInter;
import org.calminfotech.email.bo.inter.ClientTypeBoInter;
import org.calminfotech.email.bo.inter.DepositMailBoInter;
import org.calminfotech.email.bo.inter.Financial_InstituteBoInter;
import org.calminfotech.email.bo.inter.LoanMailBoInter;
import org.calminfotech.email.bo.inter.NetWorthBoInter;
import org.calminfotech.email.bo.inter.SetEmailBoInter;
import org.calminfotech.email.bo.inter.SettingsAuditEmailBoInter;
import org.calminfotech.email.bo.inter.SettingsAuditSMSBoInter;
import org.calminfotech.email.bo.inter.StockBroadCastBoInter;
import org.calminfotech.email.dao.inter.Financial_InstituteDaoInter;
import org.calminfotech.email.form.BroadCastCsvForm;
import org.calminfotech.email.form.BroadCastForm;
import org.calminfotech.email.form.ClientTypBrdcstForm;
import org.calminfotech.email.form.ClientTypeForm;
import org.calminfotech.email.form.DepositMailForm;
import org.calminfotech.email.form.EmailLogForm;
import org.calminfotech.email.form.InvestMaturityForm;
import org.calminfotech.email.form.InvestmentForm;
import org.calminfotech.email.form.LoanMailForm;
import org.calminfotech.email.form.NetWorthForm;
import org.calminfotech.email.form.StockBroadCastForm;
import org.calminfotech.email.form.CsvMailBody;
import org.calminfotech.email.form.NbClientForm;
import org.calminfotech.email.form.SetEmailForm;
import org.calminfotech.email.form.SmsForm;
import org.calminfotech.email.model.ActivityLogger;
import org.calminfotech.email.model.Client;
import org.calminfotech.email.model.BroadCastAll;
import org.calminfotech.email.model.ClientType;
import org.calminfotech.email.model.Deposit;
import org.calminfotech.email.model.Loan;
import org.calminfotech.email.model.NetWorth;
import org.calminfotech.email.model.SetEmail;
import org.calminfotech.email.model.SettingsAuditEmail;
import org.calminfotech.email.model.SettingsAuditSMS;
import org.calminfotech.email.model.SmsActivityLogger;
import org.calminfotech.email.model.StockBroadCast;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
 
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utilities.*;
import org.calminfotech.utility.annotations.Layout;

import java.sql.Timestamp;
import java.util.Date;
@Controller
@Layout(value = "layouts/default")
public class EmailController1 {
	
 // private String [] filename;
	@Autowired
	private DepositMailBoInter depositMailBo;
	
	@Autowired
	private LoanMailBoInter loanMailBo;
	
	@Autowired
	private NetWorthBoInter netWorthBoInter;

	@Autowired
	private ClientTypeBoInter clientTypeBoInter;
	
	@Autowired
	private Financial_InstituteBoInter financial_InstituteBoInter;
	
	@Autowired
	private ActivityLogBoInter activityLogBoInter;
	
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private SetEmailBoInter setEmailBoInter;
	
	@Autowired
	private StockBroadCastBoInter stockBroadCastBoInter;
	
	@Autowired
	private BroadCastAllBoInter broadCastAllBoInter;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private SettingsAuditEmailBoInter settingsAuditEmailBo;
	
	String addr="";
	String recipient1 ="";
	
	String smsDisallowed = "you are not allowed to send Email";
	//String smsLimitReached = "you have reached your sms limit";
	

private String saveDirectory = "C:/Test/Upload/";
	
@Layout(value = "layouts/default")
@RequestMapping(value = "/broadcastpage")
public String newBroadcastAtchForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		model.addAttribute("broadCastForm", new BroadCastForm());
	return "/crm/email/broadcastmail";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}


	
@Layout(value = "layouts/default")
@RequestMapping(value = "/broadcastpagesend", method=RequestMethod.POST)
public String newBroadcastAtchFormpost(HttpServletRequest request,Model model,
		@Valid @ModelAttribute("broadCastForm") BroadCastForm broadCastForm,HttpSession session
		,@RequestParam CommonsMultipartFile[] attachFile
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	String choice = request.getParameter("choice");
	 String recipient = request.getParameter("to");
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	 //String from = "Kend";
	 //String email = "ibrowiz@gmail.com";
	
	
	//test;
	//if iption1 is 1 then return:redirect sendfrom sndto controller sendto/{subjet}/{message}
	 if("multirecipient".equals(choice)){
		//do what you want 
		 System.out.println("i am in multirecipient");
		 System.out.println(recipient);
		 System.out.println(new GregorianCalendar().getTime());
		 model.addAttribute("broadCastForm", new BroadCastForm());	
			//return "redirect:/sendfromsendto/"+recipient+"/"+sub+"/"+mes+"/"+attachFile2;
		 final String[] recipientList1 = recipient.trim().split(",");
			//final InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
			 
			
			for (String recipient1 : recipientList1) {
			  // recipientAddress[counter] = new InternetAddress(recipient1.trim());
			    System.out.println("recipient1");
			    System.out.println(recipient1);
			    ActivityLogger activityLogger = new ActivityLogger();
			    activityLogger.setRecipientId(broadCastForm.getRecipientId());
			    //activityLogger.setChoice(broadCastForm.getChoice());
			   // activityLogger.setAttachFile(broadCastForm.getAttachFile());
			   // activityLogger.setAttachFile2(broadCastForm.getAttachFile2());
			    activityLogger.setTo(recipient1);
			    activityLogger.setSubject(sub);
			    activityLogger.setMesssage(mes);
			    activityLogger.setDateSent(new GregorianCalendar().getTime());
			    this.activityLogBoInter.save(activityLogger);
				model.addAttribute("activityLogger", activityLogger);
			    this.sendmail1(email,from,recipient1,sub,mes,attachFile2);
			    
			  			   
	 }
			int Alength = recipientList1.length;
			
			String successMsg = "Sent to " +Alength + " recipient(s)";
			model.addAttribute("successMsg", successMsg);
			 return "/crm/email/broadcastmail";	
	 }
	 int lines = 0;	
	 
	 if("csvupload".equals(choice)){
		 System.out.println("i am in csvrecipient");
		 
		 
			
			if (attachFile != null && attachFile.length > 0) {
				
				for (CommonsMultipartFile aFile : attachFile){
					
					System.out.println("Saving file: " + aFile.getOriginalFilename());
					
					if (!aFile.getOriginalFilename().equals("")) {
						
						//String[] parts = aFile.getOriginalFilename().split("[!.?:;-s+]");
						String[] parts = aFile.getOriginalFilename().split("\\.(?=[^\\.]+$)");
						String part1 = parts[0]; // 004
						String part2 = parts[1]; // 034556
						
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("MMddyyhmmss");
						String formattedDate = sdf.format(date);
						System.out.println(formattedDate);
						
						String newbasename =part1.concat(formattedDate);
						String ext = part2;
						 String newname1=newbasename+"."+ext;
						 System.out.println(newbasename);
						 System.out.println(newname1);
			
		
						aFile.transferTo(new File(saveDirectory + newname1));
						
						System.out.println("start");
						System.out.println(newname1);
						System.out.println(sub);
						System.out.println(mes);
						System.out.println("end");
						
						BufferedReader reader =new BufferedReader(new FileReader("C:\\Test\\Upload\\"+newname1));
						while((addr=reader.readLine())!=null){
				           lines++;
				            System.out.println(addr);
				            ActivityLogger activityLogger = new ActivityLogger();
						    activityLogger.setRecipientId(broadCastForm.getRecipientId());
						    
						    activityLogger.setTo(addr);
						    activityLogger.setSubject(sub);
						    activityLogger.setMesssage(mes);
						   
						    activityLogger.setDateSent(new GregorianCalendar().getTime());
						    this.activityLogBoInter.save(activityLogger);		
						    model.addAttribute("activityLogger", activityLogger);
				            sendmail1(email,from,addr,sub,mes,attachFile2);
						};
						
						model.addAttribute("broadCastForm", new BroadCastForm());
						
						String successMsg = "Sent to " +lines + " recipient(s)";
						model.addAttribute("successMsg", successMsg);
						reader.close();
					}		 
	
				}
			}
			
		 model.addAttribute("broadCastForm", new BroadCastForm());
		 return "/crm/email/broadcastmail";
		
		}
	 
	 if("fromview".equals(choice)){
		 String myhold ="";
	
			 System.out.println("i am in yanchi");
			 
			 List <BroadCastAll> allclient = broadCastAllBoInter.fetchAll();
			 for(BroadCastAll g : allclient ){
				 System.out.println("Check out "+g.getEmailAddress());
				 System.out.println("Check out "+g.getName());
				 ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(broadCastForm.getRecipientId());
				    activityLogger.setTo(g.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				    activityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
				
					
					mes = mes.replace("[fname]",g.getName());
					
					
				 System.out.println(mes);
				 
				 this.sendmail1(email,from,g.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(allclient.size());
			 
			 String successMsg = "Sent to " + allclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
				model.addAttribute("broadCastForm", new BroadCastForm());
				
				return "/crm/email/broadcastmail";	
	
	}//end if fromview
	 return null;
	}
	else{
		return "redirect:/";
		}
}//end newBroadcastAtchFormpost method





@Layout(value = "layouts/default")
@RequestMapping(value = "/stockbroadcastpage")
public String stockBroadcastForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("stockBroadCastForm", new StockBroadCastForm());
	return "/crm/email/clientstockbroadcast";
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
@RequestMapping(value = "/stockbroadcastpagesend", method=RequestMethod.POST)
public String stockBroadcastAtchForm(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("stockBroadCastForm") StockBroadCastForm stockBroadCastForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	//get request parameter tovar
	//CommonsMultipartFile[] fil = request.getParameter("attachFile");
	//List <ClientNb> client = clientNbBoInter.fetchAll();
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	//String choice = request.getParameter("choice");
	// String recipient = request.getParameter("BankId");
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	
		 String myhold ="";
		//String hold ="";
			 System.out.println("i am in yanchi");
			 
			 List <StockBroadCast> allstockclient = stockBroadCastBoInter.fetchBankId(stockBroadCastForm.getStockCode());
			 for(StockBroadCast f : allstockclient ){
				 System.out.println("Check out "+f.getEmailAddress());
				 System.out.println("Check out "+f.getFirstName());
				 System.out.println("Check out "+f.getSurName());
				ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(stockBroadCastForm.getClientId());
				    //activityLogger.setChoice(broadCastForm.getChoice());
				    //activityLogger.setAttachFile(broadCastForm.getAttachFile());
				    activityLogger.setAttachFile2(stockBroadCastForm.getAttachFile2());
				    activityLogger.setTo(f.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				    activityLogger.setDateSent(new GregorianCalendar().getTime());
					
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
				// mes =  "Hi " + g.getName() + " your email address is "+g.getEmailAddress();
					mes = mes.replace("[fname]",f.getFirstName()).replace("[Lname]",f.getSurName()).replace("[email]",f.getEmailAddress())
							.replace("[stock]",f.getStockCode());
				 System.out.println(mes);
				 
				 this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(allstockclient.size());
			 
			 String successMsg = "Sent to " + allstockclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
			 //model.addAttribute("stockBroadCastForm", new StockBroadCastForm());
				
				return "/crm/email/clientstockbroadcast";
	}
	else{
		return "redirect:/";
		}
	// }
	//end if fromview
	// return null;
}//end newBroadcastAtchFormpost method


@Layout(value = "layouts/loginlayout")
@RequestMapping(value = "/sendpassword", method=RequestMethod.POST)
public String recoverPassword(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("userForm") UserForm userForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		) throws Exception {

	//get request parameter tovar
	//CommonsMultipartFile[] fil = request.getParameter("attachFile");
	//List <ClientNb> client = clientNbBoInter.fetchAll();
	List <SetEmail> mail = setEmailBoInter.fetch();
	//User user = this.userBo.getUserByEmail(userForm.getUserName());
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	//String choice = request.getParameter("choice");
	// String recipient = request.getParameter("BankId");
	 String sub = "Password Recovery ";
	 //String mes ="your password is " + user.getPassword();
	 String emaillAdd = userForm.getUserName();
	
		 String myhold ="";
		//String hold ="";
			System.out.println("i am in yanchi");
			try{
			User user = this.userBo.getUserByEmail(userForm.getUserName());
			/*if (null == user) {
				 final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog , "the email you provided does not exist in our database");
				return "redirect:/";
				
			}	*/ 
			
			
			String mes ="your password is " + user.getPassword();
			if(userForm.getUserName().equals(user.getUserName())){
				this.sendmail2(email,from,emaillAdd,sub,mes);
				 final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog , "email sent");
				 return "/user/login";
			}
			}catch (NullPointerException e) {
				 final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				 JOptionPane.showMessageDialog(dialog , "the email you provided does not exist in our database"); 
	        }
			/*else{
			 
			 final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			 JOptionPane.showMessageDialog(dialog ,  "the email you provided does not exist in our database");
			}*/
				return "/user/login";
	
	
}//end newBroadcastAtchFormpost m


@Layout(value = "layouts/default")
@RequestMapping(value = "/networth")
public String netWorthForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("netWorthForm", new NetWorthForm());
	return "/crm/email/networthclients";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}

@Layout(value = "layouts/default")
@RequestMapping(value = "/networthbroadcast", method=RequestMethod.POST)
public String networthForm(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("netWorthForm") NetWorthForm netWorthForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	
		 String myhold ="";
		//String hold ="";
			 System.out.println("i am in yanchi");
			 
			 List <NetWorth> alltypeclient = netWorthBoInter.fetchNetWorth(netWorthForm.getNetWorthFrom(),netWorthForm.getNetWorthTo());
			 for(NetWorth w : alltypeclient ){
				 System.out.println("Check out "+w.getEmailAddress());
				 System.out.println("Check out "+w.getFirstName());
				 System.out.println("Check out "+w.getSurName());
				 ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(netWorthForm.getClientId());
				    //activityLogger.setChoice(broadCastForm.getChoice());
				    //activityLogger.setAttachFile(broadCastForm.getAttachFile());
				    activityLogger.setAttachFile2(netWorthForm.getAttachFile2());
				    activityLogger.setTo(w.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				     activityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
					mes = mes.replace("[fname]",w.getFirstName()).replace("[Lname]",w.getSurName()).replace("[email]",w.getEmailAddress()).replace("[worth]",String.valueOf(w.getNetWorth()));
						
				 System.out.println(mes);
				 
				 
				 this.sendmail1(email,from,w.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(alltypeclient.size());
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);*/
			 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
			 //JOptionPane.showMessageDialog(dialog ,  "Message sent to " + alltypeclient.size() + " recipient(s)");
		
				
				return "/crm/email/networthclients";
	}
	else{
		return "redirect:/";
		}
	// }
	//end if fromview
	// return null;
}//end newBroadcastAtchFormpost method


/*@Layout(value = "layouts/default")
@RequestMapping(value = "/networthsms")
public String netWorthsms(Model model) {
	//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("netWorthForm", new NetWorthForm());
	return "/crm/sms/networthsms";
}*/

@Layout(value = "layouts/default")
@RequestMapping(value = "/client_type")
public String clientTypeForm(Model model,HttpSession session) {
if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	User user = this.userBo.getUserById(this.userIdentity.getUserId());
	if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
	//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("clientTypBrdcstForm", new ClientTypBrdcstForm());
	
	return "/crm/email/client_type";
	}
	else{
	/* final JDialog dialog = new JDialog();
	 dialog.setAlwaysOnTop(true);
	JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
		model.addAttribute("smsDisallowed", smsDisallowed);
		 return "/user/index";}
}
else{
	return "redirect:/";
	}
}


@Layout(value = "layouts/default")
@RequestMapping(value = "/clienttypebroadcast", method=RequestMethod.POST)
public String clienttypeForm(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("clientTypBrdcstForm") ClientTypBrdcstForm clientTypBrdcstForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//get request parameter tovar
	//CommonsMultipartFile[] fil = request.getParameter("attachFile");
	//List <ClientNb> client = clientNbBoInter.fetchAll();
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	//String choice = request.getParameter("choice");
	// String recipient = request.getParameter("BankId");
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	
		 String myhold ="";
		//String hold ="";
			 System.out.println("i am in yanchi");
			 System.out.println(clientTypBrdcstForm.getClientType());
			 List <ClientType> alltypeclient = clientTypeBoInter.fetchType(clientTypBrdcstForm.getClientType());
			 for(ClientType f : alltypeclient ){
				 System.out.println("Check out "+f.getEmailAddress());
				 System.out.println("Check out "+f.getFirstName());
				 System.out.println("Check out "+f.getSurName());
				 ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(clientTypBrdcstForm.getClientId());
				    //activityLogger.setChoice(broadCastForm.getChoice());
				    //activityLogger.setAttachFile(broadCastForm.getAttachFile());
				    activityLogger.setAttachFile2(clientTypBrdcstForm.getAttachFile2());
				    activityLogger.setTo(f.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				    activityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
				// mes =  "Hi " + g.getName() + " your email address is "+g.getEmailAddress();
					mes = mes.replace("[fname]",f.getFirstName()).replace("[Lname]",f.getSurName()).replace("[email]",f.getEmailAddress());
						
				 System.out.println(mes);
				 
				 this.sendmail1(email,from,f.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(alltypeclient.size());
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);*/
			 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
			 //JOptionPane.showMessageDialog(dialog,  "Message sent to " + alltypeclient.size() + " recipient(s)");
		 model.addAttribute("clientTypBrdcstForm", new ClientTypBrdcstForm());
				
				return "/crm/email/client_type";
		}
		else{
		/* final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
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



/*@Layout(value = "layouts/default")
@RequestMapping(value = "/client_typesms")
public String clientTypesms(Model model) {
	//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("clientTypeForm", new ClientTypeForm());
	return "/crm/sms/client_typesms";
}*/

@Layout(value = "layouts/default")
@RequestMapping(value = "/loanclient")
public String loanclientForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("loanMailForm", new LoanMailForm());
	return "/crm/email/loan_email";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			/*ErrorForm errorForm  =new ErrorForm();
			model.addAttribute("errorMSG", errorForm.setMsg("I am sleeping"));*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}

	}
	else{
		return "redirect:/";
		}
}


@Layout(value = "layouts/default")
@RequestMapping(value = "/sendloanmail", method=RequestMethod.POST)
public String loanForm(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("loanMailForm") LoanMailForm loanMailForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){

	//get request parameter tovar
	//CommonsMultipartFile[] fil = request.getParameter("attachFile");
	//List <ClientNb> client = clientNbBoInter.fetchAll();
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	//String choice = request.getParameter("choice");
	// String recipient = request.getParameter("BankId");
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	
		 String myhold ="";
		//String hold ="";
			 System.out.println("i am in yanchi");
			 //System.out.println(loanMailForm.);
			 List <Loan> allloanclient = loanMailBo.fetchLoanClients();
			 for(Loan l : allloanclient ){
				 System.out.println("Check out "+l.getEmailAddress());
				 System.out.println("Check out "+l.getFirstName());
				 System.out.println("Check out "+l.getSurName());
				 ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(loanMailForm.getId());
				   // activityLogger.setChoice(broadCastForm.getChoice());
				   // activityLogger.setAttachFile(broadCastForm.getAttachFile());
				    activityLogger.setAttachFile2(loanMailForm.getAttachFile2());
				    activityLogger.setTo(l.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				    activityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
				// mes =  "Hi " + g.getName() + " your email address is "+g.getEmailAddress();
					mes = mes.replace("[fname]",l.getFirstName()).replace("[Lname]",l.getSurName()).replace("[email]",l.getEmailAddress());
						
				 System.out.println(mes);
				 
				 this.sendmail1(email,from,l.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(allloanclient.size());
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);*/
			 String successMsg = "Sent to " + allloanclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
			 //JOptionPane.showMessageDialog(dialog ,  "Message sent to " + allloanclient.size() + " recipient(s)");
		 model.addAttribute("loanMailForm", new LoanMailForm());
				
				return "/crm/email/loan_email";
				
	}
	else{
		return "redirect:/";
		}
	// }
	//end if fromview
	// return null;
}//end newBroadcastAtchFormpost method

@Layout(value = "layouts/default")
@RequestMapping(value = "/depositclient")
public String depositclientForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("depositMailForm", new DepositMailForm());
	return "/crm/email/deposit_email";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}

@Layout(value = "layouts/default")
@RequestMapping(value = "/senddepositmail", method=RequestMethod.POST)
public String depositForm(HttpServletRequest request,Model model,HttpSession session,
		@Valid @ModelAttribute("depositMailForm") DepositMailForm depositMailForm,
		BindingResult result,
		RedirectAttributes redirectAttributes
		,@RequestParam CommonsMultipartFile attachFile2
		) throws Exception {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	//get request parameter tovar
	//CommonsMultipartFile[] fil = request.getParameter("attachFile");
	//List <ClientNb> client = clientNbBoInter.fetchAll();
	List <SetEmail> mail = setEmailBoInter.fetch();
	String email = mail.get(0).getEmailAddress();
	String from = mail.get(0).getName();
	//String choice = request.getParameter("choice");
	// String recipient = request.getParameter("BankId");
	 String sub = request.getParameter("subject");
	 String mes =request.getParameter("message");
	
		//String hold ="";
			 System.out.println("i am in yanchi");
			 //System.out.println(loanMailForm.);
			 List <Deposit> alldepositclient = depositMailBo.fetchDepositClients();
			 for(Deposit d : alldepositclient ){
				 System.out.println("Check out "+d.getEmailAddress());
				 System.out.println("Check out "+d.getFirstName());
				 System.out.println("Check out "+d.getSurName());
				 ActivityLogger activityLogger = new ActivityLogger();
				    activityLogger.setRecipientId(depositMailForm.getId());
				    //activityLogger.setChoice(broadCastForm.getChoice());
				    //activityLogger.setAttachFile(broadCastForm.getAttachFile());
				    activityLogger.setAttachFile2(depositMailForm.getAttachFile2());
				    activityLogger.setTo(d.getEmailAddress());
				    activityLogger.setSubject(sub);
				    activityLogger.setMesssage(mes);
				    activityLogger.setDateSent(new GregorianCalendar().getTime());
				    this.activityLogBoInter.save(activityLogger);
					model.addAttribute("activityLogger", activityLogger);
				// mes =  "Hi " + g.getName() + " your email address is "+g.getEmailAddress();
					mes = mes.replace("[fname]",d.getFirstName()).replace("[Lname]",d.getSurName()).replace("[email]",d.getEmailAddress());
						
				 System.out.println(mes);
				 
				 this.sendmail1(email,from,d.getEmailAddress(),sub,mes,attachFile2);
				 //this.sendmail1(recipient1,sub,mes,attachFile2);
			 }
			 System.out.println(alldepositclient.size());
			 String successMsg = "Sent to " + alldepositclient.size() + " recipient(s)";
				model.addAttribute("successMsg", successMsg);
			 //JOptionPane.showMessageDialog(dialog ,  "Message sent to " + alldepositclient.size() + " recipient(s)");
				
				return "/crm/email/deposit_email";
	}
	else{
		return "redirect:/";
		}
	// }
	//end if fromview
	// return null;
}//end newBroadcastAtchFormpost method



@Layout(value = "layouts/default")
@RequestMapping(value = "/invst_typesms")
public String investTypesms(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("investTypeForm", new InvestmentForm());
	return "/crm/sms/invest_typesms";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}

@Layout(value = "layouts/default")
@RequestMapping(value = "/invst_maturity")
public String investMatForm(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
	//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("investMaturityForm", new InvestMaturityForm());
	return "/crm/email/invest_Maturity";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", smsDisallowed);
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}

@Layout(value = "layouts/default")
@RequestMapping(value = "/invst_maturitysms")
public String investMatms(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))||("email".equals(user.getRole()))||("emailsms".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
	model.addAttribute("investMaturityForm", new InvestMaturityForm());
	return "/crm/sms/invest_Maturitysms";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "Restricted Area");return "redirect:/index";}*/
			model.addAttribute("restriction", "Restricted Area");
			 return "/user/index";}

	}
	else{
		return "redirect:/";
		}
}


@Layout(value = "layouts/mydatatablelayout")
@RequestMapping(value = "/listemail")
public String viewAction(Model model,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))){
		model.addAttribute("setEmail", this.setEmailBoInter.fetch());
	return "/crm/email/listemail";
		}
		else{
		/* final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "Restricted Area");return "redirect:/index";}*/
			model.addAttribute("restriction", "Restricted Area");
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}
}

@Layout(value = "layouts/mydatatablelayout")
@RequestMapping(value = "/listemaillog")
public String viewSmsLog(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			
			List<ActivityLogger> emailLog = this.activityLogBoInter.fetchAllLog();
			model.addAttribute("emailLogForm",new EmailLogForm() );		
	model.addAttribute("emailLog",emailLog );
	return "/crm/email/listemaillog";
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

@Layout(value = "layouts/default")
@RequestMapping(value = "/emaillogform")
public String viewemLog(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			
			//List<ActivityLogger> emailLog = this.activityLogBoInter.fetchAllLog();
			model.addAttribute("emailLogForm",new EmailLogForm());		
	//model.addAttribute("emailLog",emailLog );
	return "/crm/email/emaillogsearch";
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

@RequestMapping(value = "/showEdit/{emailId}", method = RequestMethod.GET)
public String editAct(@PathVariable("emailId") Integer emailId, Model model,
		RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		User user = this.userBo.getUserById(this.userIdentity.getUserId());
		if(("admin".equals(user.getRole()))){
		SetEmail setEmail = this.setEmailBoInter.getEmailViaId(emailId);
	if (null == setEmail) {

		return "redirect:/home";

	}

	SetEmailForm setEmailForm = new SetEmailForm();
	
	setEmailForm.setEmailId(setEmail.getEmailId());
	
	setEmailForm.setEmailAddress(setEmail.getEmailAddress());
	
	setEmailForm.setName(setEmail.getName());
	
	setEmailForm.setModifiedBy(setEmail.getModifiedBy());

	setEmailForm.setLastModifiedDate(new GregorianCalendar().getTime());
			
	model.addAttribute("setEmailForm", setEmailForm);

	return "/crm/email/emailAddressSettings";
		}
		else{
		 /*final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "Restricted Area");return "redirect:/index";}*/
			model.addAttribute("restriction", "Restricted Area");
			 return "/user/index";}
	}
	else{
		return "redirect:/";
		}

}

@RequestMapping(value = "/update/{emailId}", method = RequestMethod.POST)
public String updateAct(
		@Valid @ModelAttribute("setEmailForm") SetEmailForm setEmailForm,
		BindingResult result, Model model,
		RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
	if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
	if (result.hasErrors()) {
		System.out.println("can't update");
		return "redirect:/index";
	}
	
	SetEmail setEmail = new SetEmail();
	
	setEmail.setEmailId(setEmailForm.getEmailId());
	
	setEmail.setEmailAddress(setEmailForm.getEmailAddress());
	
	setEmail.setName(setEmailForm.getName());
	
	setEmail.setModifiedBy(userIdentity.getUserId());
	
	setEmail.setLastModifiedDate(new GregorianCalendar().getTime());
	
	this.setEmailBoInter.update(setEmail);
	
	SettingsAuditEmail auditEmail = new SettingsAuditEmail();
	
	auditEmail.setSourceEmail(setEmail.getEmailAddress());
	auditEmail.setSourceName(setEmail.getName());
	auditEmail.setModifiedBy(setEmail.getModifiedBy());
	auditEmail.setModifiedDate(setEmail.getLastModifiedDate());
	
	this.settingsAuditEmailBo.add(auditEmail);

	 return "redirect:/listemail";
	}
	else{
		return "redirect:/";
		}
}


public void sendmail1(final String email,final String from, final String to,final String sub, final String mes,final CommonsMultipartFile attachFile2)
{
    mailSender.send(new MimeMessagePreparator() {
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8" );
			int counter = 0;
			
			messageHelper.setTo(to);
			messageHelper.setFrom(email, from);
			messageHelper.setSubject(sub);
			messageHelper.setText(mes, true);
			//messageHelper.setText(mimeMessage.setContent(mes, "text/html"), true);
			 //msg.setContent(message, "text/html");
			String attachName = attachFile2.getOriginalFilename();
			if (!attachFile2.getOriginalFilename().equals("")) {

				messageHelper.addAttachment(attachName, new InputStreamSource() {
					
					@Override
					public InputStream getInputStream() throws IOException {
						return attachFile2.getInputStream();
					}
				});
			} 
			else{}
		}

	});
   
    }

public void sendmail2(final String email,final String from, final String to,final String sub, final String mes)
{
    mailSender.send(new MimeMessagePreparator() {
		@Override
		public void prepare(MimeMessage mimeMessage) throws Exception {
			MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8" );
			int counter = 0;
			
			messageHelper.setTo(to);
			messageHelper.setFrom(email, from);
			messageHelper.setSubject(sub);
			messageHelper.setText(mes, true);
		}

	});
   
    }
	
}




