package org.calminfotech.user.controller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.validation.Valid;

import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.boInterface.UserSessionBo;
import org.calminfotech.user.forms.UserForm;
import org.calminfotech.user.models.User;
import org.calminfotech.user.models.UserSession;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utility.annotations.Layout;
import org.hibernate.HibernateException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Layout(value = "layouts/loginlayout")
public class LoginController {
	
	@Autowired
	private UserBo userBo;
	
	@Autowired
	private UserSessionBo userSessionBo;
	
	@Autowired
	private UserIdentity userIdentity;
	
	/*@Autowired
	private AssetBo assetBo;*/
	

	
	@RequestMapping(value =  "/", method = RequestMethod.GET)
	public String login(HttpServletRequest request,Model model) {
		model.addAttribute("userForm", new UserForm());
		
		/*HttpSession session = request.getSession(true);

		session.setAttribute("userName", request.getParameter("email"));
		session.setAttribute("password", request.getParameter("password"));

		// to get the username and password
		String userName = (String) session.getAttribute("userName");
		String password = (String) session.getAttribute("password");*/
		
		return "/user/login";
	}
	
	@RequestMapping(value =  "/login2", method = RequestMethod.GET)
	public String passwordChange(HttpServletRequest request,Model model) {
		model.addAttribute("userForm", new UserForm());
		
		return "/user/login2";
	}
	
	
	
	@RequestMapping(value =  "/logout")
	public String logout(HttpServletRequest request,Model model,HttpSession session) {
		model.addAttribute("userForm", new UserForm());
		session.invalidate();
		/*HttpSession session = request.getSession(true);

		session.setAttribute("userName", request.getParameter("email"));
		session.setAttribute("password", request.getParameter("password"));

		// to get the username and password
		String userName = (String) session.getAttribute("userName");
		String password = (String) session.getAttribute("password");*/
		
		return "/user/login";
	}
	
	
	@RequestMapping(value="/processlogin", method = RequestMethod.POST)
	public String loginAction(@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result, final RedirectAttributes redirectAttributes,
			HttpServletRequest httpRequest, HttpSession session, Model model){
		
		 session = httpRequest.getSession(true);

		session.setAttribute("userName", httpRequest.getParameter("userName"));
		session.setAttribute("password", httpRequest.getParameter("password"));
		
		
		// to get the username and password
		String userName = (String) session.getAttribute("userName");
		String password = (String) session.getAttribute("password");
		
		

			
		if (result.hasErrors()){
				//logger.info("There was an error with the result" +result.toString());
				return "/user/login";
			}
		
			List <User> list = userBo.checkByUserNameAndPassword(userForm.getUserName(), userForm.getPassword());
			//UserSession responseData = new UserSession();
			
			
			if(list.size()!=1){
			
				
				return "/user/login";
			}
			
			User user = list.get(0);
		
			session.setAttribute("userId", user.getUserId());
			
			/*if(user. == user.getUserName() && password == user.getPassword())*/
			System.out.println(userName);
			System.out.println(password);
			if(((userName.equals(user.getUserName())) && (password.equals(user.getPassword())))||((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null))){
				
				System.out.println( "username is "+userName);
				System.out.println(password);
				UserSession userSession  = new UserSession();
				userSession.setUser(user);
				userSession.setDateCreated(new GregorianCalendar().getTime());
				UserSession uSession = userSessionBo.save(userSession);
				session.setAttribute("firstName", userIdentity.getFirstName());
				session.setAttribute("smsLimit", userIdentity.getSmsLimit());
				session.setAttribute("smsSent", userIdentity.getSmsmSent());
				userIdentity.setUserId(uSession.getUser().getUserId());
				userIdentity.setUserName(user.getUserName());
				userIdentity.setFirstName(user.getFirstName());
				userIdentity.setLastName(user.getLastName());
				userIdentity.setPassword(user.getPassword());
				userIdentity.setRole(user.getRole());
				userIdentity.setSmsLimit(user.getSmsLimit());
				userIdentity.setSmsmSent(user.getSmsmSent());
				userIdentity.setCreatedBy(user.getCreatedBy());
				userIdentity.setDateCreated(user.getDateCreated());
				userIdentity.setModifiedBy(user.getModifiedBy());
				userIdentity.setDateModified(user.getDateModified());
				userIdentity.setIsDeleted(user.getIsDeleted());
				session.setAttribute("firstName", userIdentity.getFirstName());
				session.setAttribute("smsLimit", userIdentity.getSmsLimit());
				session.setAttribute("smsSent", userIdentity.getSmsmSent());
				if("active".equals(user.getStatus())){
				/*String client_server = httpRequest.getServerName();
				String client_ip = httpRequest.getRemoteAddr();
				model.addAttribute("sessionid", session.getId());
				model.addAttribute("sessionNew", session.isNew());
				
				String hey = session.getId();*/
				
				/*System.out.println("let me see it " +hey);*/
			
				
				/*try{
					responseData.setId(user.getUserId());
					responseData.setUsername(userForm.getEmail());
					responseData.setSessionIp(httpRequest.getRemoteAddr());
					responseData.setDescription(userForm.getEmail());
				}catch (HibernateException e){
					e.printStackTrace();
				}*/
				
				/*
				System.out.println("After Setting  " +user.getUserName().toString());
				System.out.println("Here is the client's server name " +client_server);
				
				System.out.println("Here is the client's IP " +client_ip);
				
				System.out.println("Thank you, you can now manage accounts!");*/
				
				
					return "redirect:/index";
					
				}else
				{
					/*final JDialog dialog = new JDialog();
					 dialog.setAlwaysOnTop(true);
					JOptionPane.showMessageDialog(dialog ,  "Please Change your Password to Proceed");*/
				//model.addAttribute("passwordUnsuccess", "Unsuccesful!!!Old Password Incorrect or New Password doesnt match confirmation Password/less than 6 characters");
					return "redirect:/editpassword2";}
			}else
			{return "redirect:/";}
			
						
	}
	
	@RequestMapping(value =  "/newuser", method = RequestMethod.GET)
	public String newUserForm(Model model) {
		model.addAttribute("userForm", new UserForm());
		
		return "/user/newuser";
	}
	
	@RequestMapping(value="/listusers")
	public String viewUsers( Model model){
		
		model.addAttribute("user" , this.userBo.fetchAll());
		
		return null;
		
	}
}
