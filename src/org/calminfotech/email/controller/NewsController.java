package org.calminfotech.email.controller;

import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.calminfotech.email.bo.inter.BroadCastAllBoInter;
import org.calminfotech.email.bo.inter.ClientTypeBoInter;
import org.calminfotech.email.bo.inter.DepositMailBoInter;
import org.calminfotech.email.bo.inter.Financial_InstituteBoInter;
import org.calminfotech.email.bo.inter.LoanMailBoInter;
import org.calminfotech.email.bo.inter.NetWorthBoInter;
import org.calminfotech.email.bo.inter.NewsBoInter;
import org.calminfotech.email.bo.inter.NewsOwnerBoInter;
import org.calminfotech.email.bo.inter.StockBroadCastBoInter;
import org.calminfotech.email.form.NewsForm;
import org.calminfotech.email.form.StockBroadCastForm;
import org.calminfotech.email.model.ActivityLogger;
import org.calminfotech.email.model.BroadCastAll;
import org.calminfotech.email.model.ClientType;
import org.calminfotech.email.model.Deposit;
import org.calminfotech.email.model.Loan;
import org.calminfotech.email.model.NetWorth;
import org.calminfotech.email.model.News;
import org.calminfotech.email.model.NewsOwner;
import org.calminfotech.email.model.SetEmail;
import org.calminfotech.email.model.StockBroadCast;
import org.calminfotech.user.boInterface.UserBo;
import org.calminfotech.user.models.User;
import org.calminfotech.user.utils.UserIdentity;
import org.calminfotech.utility.annotations.Layout;
import org.springframework.beans.factory.annotation.Autowired;
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
public class NewsController {
	
@Autowired
private UserBo userBo;

@Autowired
private DepositMailBoInter depositMailBo;

@Autowired
private UserIdentity userIdentity;

@Autowired
private NewsBoInter newsBo;

@Autowired
private NewsOwnerBoInter ownerBo;

@Autowired
private Financial_InstituteBoInter financial_InstituteBoInter;

@Autowired
private NetWorthBoInter netWorthBoInter;

@Autowired
private NewsOwnerBoInter newsOwnerBo;

@Autowired
private ClientTypeBoInter clientTypeBoInter;

@Autowired
private BroadCastAllBoInter broadCastAllBoInter;

@Autowired
private LoanMailBoInter loanMailBo;

@Autowired
private StockBroadCastBoInter stockBroadCastBoInter;


	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/general")
	public String generalNewsForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/general";
			}
		else{
			 /*final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/savegeneral", method=RequestMethod.POST)
	public String clienttypeForm(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
				
				News news = new News();
				
				NewsOwner owner = new NewsOwner(); 
				
				news.setUserId(this.userIdentity.getUserId());
				
				news.setSubject(newsForm.getSubject());
			
				news.setBody(newsForm.getBody());
				
				news.setValue("General");
				
				news.setDateCreated(new GregorianCalendar().getTime());
				
				news.setDateModified(newsForm.getDateModified());
				
				news.setStatus("active");
				
				News news1 = this.newsBo.save(news);
				
				
				
						
				List <BroadCastAll> allclient = broadCastAllBoInter.fetchAll();
				for(BroadCastAll g : allclient ){
					owner.setUserId(news1.getUserId());
					owner.setClientId(g.getClientId());
					owner.setNews(news1);
					owner.setDateCreated(news1.getDateCreated());
					owner.setStatus("active");
					owner.setViewStatus("unread");
					this.newsOwnerBo.save(owner);
	
				}
	 
	 
				String successMsg = "Sent to " + allclient.size() + " recipient(s)";
					model.addAttribute("successMsg", successMsg);
				 //JOptionPane.showMessageDialog(dialog,  "Message sent to " + alltypeclient.size() + " recipient(s)");
			 model.addAttribute("newsForm", newsForm);
					
					//return "/crm/news/general";
			
			}
			else{
			/* final JDialog dialog = new JDialog();
			 dialog.setAlwaysOnTop(true);
			JOptionPane.showMessageDialog(dialog ,  "you are not allowed to send email");return "redirect:/index";}*/
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		// }
		//end if fromview
		// return null;
		return "/crm/news/general";
	}//end newBroadcastAtchFormpost method
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/stocknews")
	public String stockNewsForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/stocknews";
			}
		else{
			 
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/savestocknews", method=RequestMethod.POST)
	public String stockBroadcastAtchForm(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			News news = new News();
			
			NewsOwner owner = new NewsOwner(); 
			
			news.setUserId(this.userIdentity.getUserId());
			
			news.setSubject(newsForm.getSubject());
		
			news.setBody(newsForm.getBody());
			
			news.setValue(newsForm.getValue1());
			
			news.setDateCreated(new GregorianCalendar().getTime());
			
			news.setDateModified(newsForm.getDateModified());
			
			news.setStatus("active");
			
			News news1 = this.newsBo.save(news);
			
			
				 List <StockBroadCast> allstockclient = stockBroadCastBoInter.fetchBankId(newsForm.getValue1());
				 for(StockBroadCast f : allstockclient ){	
					 owner.setUserId(news1.getUserId());
						owner.setClientId(f.getClientId());
						owner.setNews(news1);
						owner.setDateCreated(news1.getDateCreated());
						owner.setStatus("active");
						owner.setViewStatus("unread");
						this.newsOwnerBo.save(owner);
					
					 
					
				 }
		
				 String successMsg = "Sent to " + allstockclient.size() + " recipient(s)";
				 model.addAttribute("successMsg", successMsg);
				
				 model.addAttribute("newsForm", newsForm);
					
				
			
			}
			else{
			
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		
		return "redirect:/stocknews";
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/networthnews")
	public String netWorthNewsForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/networthnews";
			}
		else{
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/savenetworthnews", method=RequestMethod.POST)
	public String savenetworthnews(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			News news = new News();
			
			NewsOwner owner = new NewsOwner(); 
			
			news.setUserId(this.userIdentity.getUserId());
			
			news.setSubject(newsForm.getSubject());
		
			news.setBody(newsForm.getBody());
			
			news.setValue(newsForm.getValue1());
			
			news.setDateCreated(new GregorianCalendar().getTime());
			
			news.setDateModified(newsForm.getDateModified());
			
			news.setStatus("active");
			
			News news1 = this.newsBo.save(news);
			
			
			 List <NetWorth> alltypeclient = netWorthBoInter.fetchNetWorth(Integer.parseInt(newsForm.getValue1()),Integer.parseInt(newsForm.getValue2()));
			 for(NetWorth w : alltypeclient ){	
					 owner.setUserId(news1.getUserId());
						owner.setClientId(w.getClientId());
						owner.setNews(news1);
						owner.setDateCreated(news1.getDateCreated());
						owner.setStatus("active");
						owner.setViewStatus("unread");
						this.newsOwnerBo.save(owner);
					
					 
					
				 }
		
			 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
				 model.addAttribute("successMsg", successMsg);
				
				 model.addAttribute("newsForm", newsForm);
					
				
			
			}
			else{
			
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		
		return "redirect:/networthnews";
	}
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/clienttypenews")
	public String clienttypeNewsForm(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/clienttypenews";
			}
		else{
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/saveclienttypenews", method=RequestMethod.POST)
	public String saveclienttypenews(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			News news = new News();
			
			NewsOwner owner = new NewsOwner(); 
			
			news.setUserId(this.userIdentity.getUserId());
			
			news.setSubject(newsForm.getSubject());
		
			news.setBody(newsForm.getBody());
			
			news.setValue(newsForm.getValue1());
			
			news.setDateCreated(new GregorianCalendar().getTime());
			
			news.setDateModified(newsForm.getDateModified());
			
			news.setStatus("active");
			
			News news1 = this.newsBo.save(news);
			
			
			 List <ClientType> alltypeclient = clientTypeBoInter.fetchType(newsForm.getValue1());
			 for(ClientType f : alltypeclient ){	
					 owner.setUserId(news1.getUserId());
						owner.setClientId(f.getClientId());
						owner.setNews(news1);
						owner.setDateCreated(news1.getDateCreated());
						owner.setStatus("active");
						owner.setViewStatus("unread");
						this.newsOwnerBo.save(owner);
					
					 
					
				 }
		
			 String successMsg = "Sent to " + alltypeclient.size() + " recipient(s)";
				 model.addAttribute("successMsg", successMsg);
				
				 model.addAttribute("newsForm", newsForm);
					
				
			
			}
			else{
			
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		
		return "redirect:/clienttypenews";
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/loanclientnews")
	public String loanclientnews(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/loanclientnews";
			}
		else{
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/saveloanclientnews", method=RequestMethod.POST)
	public String saveloanclientnews(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			News news = new News();
			
			NewsOwner owner = new NewsOwner(); 
			
			news.setUserId(this.userIdentity.getUserId());
			
			news.setSubject(newsForm.getSubject());
		
			news.setBody(newsForm.getBody());
			
			news.setValue("Loan");
			
			news.setDateCreated(new GregorianCalendar().getTime());
			
			news.setDateModified(newsForm.getDateModified());
			
			news.setStatus("active");
			
			News news1 = this.newsBo.save(news);
			
			
			 List<Loan> allloanclient = loanMailBo.fetchLoanClients();
			 for(Loan l : allloanclient ){	
					 owner.setUserId(news1.getUserId());
						owner.setClientId(l.getClientId());
						owner.setNews(news1);
						owner.setDateCreated(news1.getDateCreated());
						owner.setStatus("active");
						owner.setViewStatus("unread");
						this.newsOwnerBo.save(owner);
					
					 
					
				 }
		
			 String successMsg = "Sent to " + allloanclient.size() + " recipient(s)";
				 model.addAttribute("successMsg", "News Sent");
				
				 model.addAttribute("newsForm", newsForm);
					
				
			
			}
			else{
			
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		
		return "redirect:/loanclientnews";
	}
	
	
	@Layout(value = "layouts/default")
	@RequestMapping(value = "/depositclientnews")
	public String depositclientnews(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
		//model.addAttribute("instituteList",  financial_InstituteBoInter.fetchAllInst());
		model.addAttribute("newsForm", new NewsForm());
		return "/crm/news/depositclientnews";
			}
		else{
			model.addAttribute("smsDisallowed", "Restriced Area");
			 return "/user/index";}
		}
		else{
			return "redirect:/";
			}
	}
	
	

	@Layout(value = "layouts/default")
	@RequestMapping(value = "/savedepositclientnews", method=RequestMethod.POST)
	public String savedepositclientnews(HttpServletRequest request,Model model,HttpSession session,
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result,
			RedirectAttributes redirectAttributes
			) throws Exception {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			News news = new News();
			
			NewsOwner owner = new NewsOwner(); 
			
			news.setUserId(this.userIdentity.getUserId());
			
			news.setSubject(newsForm.getSubject());
		
			news.setBody(newsForm.getBody());
			
			news.setValue("deposit");
			
			news.setDateCreated(new GregorianCalendar().getTime());
			
			news.setDateModified(newsForm.getDateModified());
			
			news.setStatus("active");
			
			News news1 = this.newsBo.save(news);
			
			
			 List <Deposit> alldepositclient = depositMailBo.fetchDepositClients();
			 for(Deposit d : alldepositclient ){	
					 owner.setUserId(news1.getUserId());
						owner.setClientId(d.getClientId());
						owner.setNews(news1);
						owner.setDateCreated(news1.getDateCreated());
						owner.setStatus("active");
						owner.setViewStatus("unread");
						this.newsOwnerBo.save(owner);
					
					 
					
				 }
		
			 String successMsg = "Sent to " + alldepositclient.size() + " recipient(s)";
				 model.addAttribute("successMsg", "News Sent");
				
				 model.addAttribute("newsForm", newsForm);
					
				
			
			}
			else{
			
				model.addAttribute("smsDisallowed", "Restricted Area");
				 return "/user/index";}
		
		}else{
			return "redirect:/";
			}
		
		return "redirect:/depositclientnews";
	}
	
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/listnewsowner")
	public String listnewsowner(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			
			model.addAttribute("owner", this.ownerBo.fetchAll());
			
		}
		else{
			model.addAttribute("smsDisallowed", "Restricted Area");
			 return "/user/index";}
			}
		else{return "redirect:/";}
			return "/crm/news/listnewsowner";
	}
	
	
	@Layout(value = "layouts/mydatatablelayout")
	@RequestMapping(value = "/listnews")
	public String viewAction(Model model,HttpSession session) {
		if((((String) session.getAttribute("userName"))!= null) || (((String) session.getAttribute("password"))!= null)){
			User user = this.userBo.getUserById(this.userIdentity.getUserId());
			if(("admin".equals(user.getRole()))){
			
			model.addAttribute("news", this.newsBo.fetchAll());
			
		}
		else{
			model.addAttribute("smsDisallowed", "Restricted Area");
			 return "/user/index";}
			}
		else{return "redirect:/";}
			return "/crm/news/listnews";
	}
	
	
	
	@RequestMapping(value = "/editnews/{newsId}")
	public String editAction(@PathVariable("newsId") Integer newsId, Model model,
			RedirectAttributes redirectAttributes, HttpServletRequest request) {

		News news  = this.newsBo.fetchNewsById(newsId);
		if (null == news) {

			return "redirect:/home";
		}

		NewsForm newsForm = new NewsForm();
		
		newsForm.setId(news.getId());
		
		newsForm.setUserId(news.getUserId());
	
		newsForm.setSubject(news.getSubject());
		
		//newsForm.setValue(news.getValue());
		
		newsForm.setBody(news.getBody());
		
		//newsForm.setDateCreated(news.getDateCreated());
		
		newsForm.setStatus(news.getStatus());
		
		newsForm.setDateModified(news.getDateModified());
		
					
		List<News> news1 = this.newsBo.fetchAll();
		model.addAttribute("news", news1);
				
		model.addAttribute("newsForm", newsForm);

		return "/crm/news/editnews";

	}
	
	@RequestMapping(value = "/updatenews")
	public String updateAction(
			@Valid @ModelAttribute("newsForm") NewsForm newsForm,
			BindingResult result, Model model,
			RedirectAttributes redirectAttributes) {
		
		if (result.hasErrors()) {
			System.out.println("Failed to get the record");
			return "/user/index";
		}
		
		News news = newsBo.fetchNewsById(newsForm.getId());
		news.setId(newsForm.getId());
		news.setUserId(userIdentity.getUserId());
		news.setSubject(newsForm.getSubject());
		news.setBody(newsForm.getBody());
		news.setStatus("active");
		news.setDateModified(new GregorianCalendar().getTime());
		this.newsBo.update(news);
		return "redirect:/listnews";	
	}
	
}
