package org.calminfotech.user.controller;


import java.util.GregorianCalendar;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.calminfotech.email.bo.inter.SetEmailBoInter;
import org.calminfotech.email.model.SetEmail;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.forms.PasswordChangeForm;
import org.calminfotech.user.forms.PasswordRecoveryForm;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utilities.AutoGen;
import org.calminfotech.utility.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Layout(value = "layouts/default")
public class UserController {
	
	@Autowired
	private SetEmailBoInter setEmailBoInter;
	
	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private UserIdentity userIdentity;
	
	@Autowired
	private UserBo userBo;
	
	@RequestMapping(value="/index" , method=RequestMethod.GET)
	public String indexAction(HttpSession session){
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		
		return "/user/index";
		}
		else{return "redirect:/";}
	}
	

	@RequestMapping(value="/adduser" , method=RequestMethod.GET)
	public String addAction(Model model,HttpSession session){
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			if(("admin".equals(userIdentity.getRole()))){
			model.addAttribute("userForm", new UserForm());	
			}
			else{
				/*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog ,  "Restricted Area");
				return "redirect:/index";}*/
				model.addAttribute("restriction", "Restricted Area");
				 return "/user/index";}
		}
		else{return "redirect:/";}
		return "/user/newuser";
	}
	
	
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String addAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		List<User> userl = userBo.fetchUserByEmail(userForm.getUserName());
		if(userl.size()==0){
		List <SetEmail> mail = setEmailBoInter.fetch();
		//User user = this.userBo.getUserByEmail(userForm.getUserName());
		String email = mail.get(0).getEmailAddress();
		String from = mail.get(0).getName();
		//String choice = request.getParameter("choice");
		// String recipient = request.getParameter("BankId");
		 String sub = "CRM SMSMAILER Account Creation";
		 //String mes ="your password is " + user.getPassword();
		 String emaillAdd = userForm.getUserName();
		User user = new User();
		System.out.println("in now");
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setUserName(userForm.getUserName());
		user.setUserPassword(new AutoGen().genPass());
		//this.sendmail2(email,from,emaillAdd,sub,mes);
		user.setRole(userForm.getRole());
		user.setSmsLimit(userForm.getSmsLimit());
		user.setSmsmSent(0);
		user.setCreatedBy(userForm.getCreatedBy());
		user.setDateCreated(new GregorianCalendar().getTime());
		user.setModifiedBy(userForm.getModifiedBy());
		user.setDateModified(userForm.getDateModified());
		user.setStatus("inactive");
		user.setIsDeleted("1");
		
		User users = userBo.save(user);
		String mes ="your CRMSMSMAILER password is " + users.getPassword();
		this.sendmail2(email,from,user.getUserName(),sub,mes);

		
		/* final JDialog dialog = new JDialog();
		 dialog.setAlwaysOnTop(true);
		JOptionPane.showMessageDialog(dialog ,  "user created");*/
		}
		else{
			/*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "user already exists");*/
			model.addAttribute("existingUser", "user already exists");
			return "/user/newuser";
		}
		return "redirect:/listuser";
		
	}
	
	
	@Layout(value = "layouts/loginlayout")
	@RequestMapping(value="/editpassword" , method=RequestMethod.GET)
	public String editAction2(Model model,HttpSession session){
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		
			model.addAttribute("userForm", new UserForm());	
		return "/user/editpassword";
		}
		else{return "redirect:/";}
	}
	
	@Layout(value = "layouts/loginlayout")
	@RequestMapping(value="/editpassword2" , method=RequestMethod.GET)
	public String newEditAction(Model model,HttpSession session){
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		
			model.addAttribute("userForm", new UserForm());	
		return "/user/editpassword2";
		}
		else{return "redirect:/";}
	}
	
	
	@RequestMapping(value = "/updatepassword", method = RequestMethod.POST)
	public String updateAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			 Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			//User users = this.userBo.getUserById(this.userIdentity.getUserId());
			//PasswordChangeForm passwordChangeForm =new PasswordChangeForm();
			if((userForm.getPassword().equals(userIdentity.getPassword()))
			&&(userForm.getPassword1().equals(userForm.getPassword2())&&(userForm.getPassword2().length()>=6))
			/*&&(!passwordChangeForm.getPassword().equals(passwordChangeForm.getPassword2()))||
			(!passwordChangeForm.getPassword().equals(passwordChangeForm.getPassword1()))*/){
			
		/*	if (result.hasErrors()) {
			System.out.println("can't update");
			return "/";
		}*/
		
		User user = userBo.getUserById(userIdentity.getUserId());
		user.setId(userIdentity.getUserId());
		user.setUserName(userIdentity.getUserName());
		user.setFirstName(userIdentity.getFirstName());
		user.setLastName(userIdentity.getLastName());
		user.setUserPassword(userForm.getPassword2());
		user.setRole(userIdentity.getRole());
		user.setSmsLimit(userIdentity.getSmsLimit());
		user.setSmsmSent(userIdentity.getSmsmSent());
		user.setCreatedBy(userIdentity.getCreatedBy());
		user.setDateCreated(userIdentity.getDateCreated());
		user.setModifiedBy(userIdentity.getModifiedBy());
		user.setDateModified(userIdentity.getDateModified());
		user.setStatus("active");
		user.setIsDeleted(userIdentity.getIsDeleted());
		
		this.userBo.update(user);
		model.addAttribute("passwordSuccess", "Password Change Successful!!!Login to Continue");
		return "redirect:/login2";

		}
		else{
			model.addAttribute("passwordUnsuccess", "Unsuccesful!!!Old Password Incorrect or New Password doesnt match confirmation Password/less than 6 characters");
			return "/user/index";
		
		}
		}
		else{
			
			return "redirect:/";
		
		}
		
		
		 /*return "redirect:/";*/
	}
	
	
	@Layout(value = "layouts/loginlayout")
	@RequestMapping(value="/editpassword3" , method=RequestMethod.GET)
	public String newEditAction1(Model model,HttpSession session){
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
		
			model.addAttribute("userForm", new UserForm());	
		return "/user/editpassword3";
		}
		else{return "redirect:/";}
	}
	
	@RequestMapping(value = "/updatepassword3", method = RequestMethod.POST)
	public String NewUpdateAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			 Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			//User users = this.userBo.getUserById(this.userIdentity.getUserId());
			//PasswordChangeForm passwordChangeForm =new PasswordChangeForm();
			if((userForm.getPassword().equals(userIdentity.getPassword()))
			&&(userForm.getPassword1().equals(userForm.getPassword2())&&(userForm.getPassword2().length()>=6))
			/*&&(!passwordChangeForm.getPassword().equals(passwordChangeForm.getPassword2()))||
			(!passwordChangeForm.getPassword().equals(passwordChangeForm.getPassword1()))*/){
			
		/*	if (result.hasErrors()) {
			System.out.println("can't update");
			return "/";
		}*/
		
		User user = userBo.getUserById(userIdentity.getUserId());
		user.setId(userIdentity.getUserId());
		user.setUserName(userIdentity.getUserName());
		user.setFirstName(userIdentity.getFirstName());
		user.setLastName(userIdentity.getLastName());
		user.setUserPassword(userForm.getPassword2());
		user.setRole(userIdentity.getRole());
		user.setSmsLimit(userIdentity.getSmsLimit());
		user.setSmsmSent(userIdentity.getSmsmSent());
		user.setCreatedBy(userIdentity.getCreatedBy());
		user.setDateCreated(userIdentity.getDateCreated());
		user.setModifiedBy(userIdentity.getModifiedBy());
		user.setDateModified(userIdentity.getDateModified());
		user.setStatus("active");
		user.setIsDeleted(userIdentity.getIsDeleted());
		
		this.userBo.update(user);
		model.addAttribute("passwordSuccess", "Password Change Successful!!!Login to Continue");
		return "redirect:/login2";

		}
		else{
			model.addAttribute("passwordUnsuccess", "Unsuccesful!!!Old Password Incorrect or New Password doesnt match confirmation Password/less than 6 characters");
			return "redirect:/editpassword3";
		
		}
		}
		else{
			
			return "redirect:/";
		
		}
		
		
		 /*return "redirect:/";*/
	}
	
	@RequestMapping(value = "/edituser/{userId}")
	public String editAction(@PathVariable("userId") Integer userId, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		User user = this.userBo.getUserById(userId);
		if (null == user) {

			return "redirect:/home";
		}

		UserForm userForm = new UserForm();
		
		userForm.setUserId(user.getUserId());
		
		userForm.setFirstName(user.getFirstName());
		
		userForm.setLastName(user.getLastName());	
		
		userForm.setUserName(user.getUserName());
		
		userForm.setPassword(user.getPassword());
		
		userForm.setRole(user.getRole());
		
		userForm.setSmsLimit(user.getSmsLimit());
		
		userForm.setSmsmSent(user.getSmsmSent());
		
		userForm.setCreatedBy(user.getCreatedBy());
		
		userForm.setDateCreated(user.getDateCreated());
		
		userForm.setModifiedBy(user.getModifiedBy());
		
		userForm.setDateModified(user.getDateModified());
		
		userForm.setStatus(user.getStatus());
		
		userForm.setIsDeleted(user.getIsDeleted());
		
		model.addAttribute("userForm", userForm);

		return "/user/edituser";
	}
	
	
	@RequestMapping(value = "/updateuser")
	public String updateAction(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		if (result.hasErrors()) {
			System.out.println("can't update");
			return "/";
		}
		
		User user = this.userBo.getUserById(userForm.getUserId());
		user.setFirstName(userForm.getFirstName());
		user.setLastName(userForm.getLastName());
		user.setUserName(userForm.getUserName());
		user.setUserPassword(userForm.getPassword());
		user.setRole(userForm.getRole());
		user.setSmsLimit(userForm.getSmsLimit());
		user.setSmsmSent(userForm.getSmsmSent());
		user.setCreatedBy(userForm.getCreatedBy());
		user.setDateCreated(new GregorianCalendar().getTime());
		user.setModifiedBy(userForm.getModifiedBy());
		user.setDateModified(userForm.getDateModified());
		user.setStatus(userForm.getStatus());
		user.setIsDeleted(userForm.getIsDeleted());
		
		
		this.userBo.update(user);

		 return "redirect:/listuser";
	}
	
	
	@Layout(value = "layouts/loginlayout")
	@RequestMapping(value="/recover" , method=RequestMethod.GET)
	public String recoverAction(Model model,HttpSession session){
	
		model.addAttribute("userForm", new UserForm());	
		return "/user/recoverpasswordform";
		
	}
	
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/listuser")
	public String viewAction(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			if(("admin".equals(userIdentity.getRole()))){
			model.addAttribute("user", this.userBo.fetchAll());
			}
			else{
				/*final JDialog dialog = new JDialog();
				 dialog.setAlwaysOnTop(true);
				JOptionPane.showMessageDialog(dialog ,  "Restricted Area");
				return "redirect:/index";}*/
				model.addAttribute("restriction", "Restricted Area");
				 return "/user/index";}
	
		}
		else{return "redirect:/";}
			return "/user/listuser";
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
