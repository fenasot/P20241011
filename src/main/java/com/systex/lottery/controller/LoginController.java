package com.systex.lottery.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.systex.lottery.exception.InputParameterException;
import com.systex.lottery.model.Account;
import com.systex.lottery.model.AccountRepository;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/login")
public class LoginController {

	@Autowired
	private AccountRepository accountRepository;
	
	@GetMapping
	public String getLogin(Model model, HttpSession session) {
		if(session.getAttribute("user") != null) {
			return "redirect:/";
		}
		return "login/login";
	}
	
	@PostMapping
	public String postLogin(@ModelAttribute Account account, HttpSession session, Model model) {
		Account a = this.accountRepository.findByAcc(account.getAcc());
		model.addAttribute("account", a);
		if( (a != null)
				&& ( account.getPwd().equals(a.getPwd()) ) 
		) {	
			session.setAttribute("user", a.getAcc());
			return"redirect:/";
		}
		model.addAttribute("errorMsg", "登入失敗");
		return "login/login";
	}
	
//	@GetMapping("/logout")
//	public String getLogout(HttpSession session, Model model) {
//		session.removeAttribute("user");
//		return "redirect:/";
//	}
	
	@GetMapping("/signup")
	public String getSignup() {
		return "login/signup";
	}
	
	@PostMapping("/signup")
	public String postSignup(@ModelAttribute Account account, HttpSession session, Model model) {
		
		String acc = account.getAcc();
		String pwd = account.getPwd();

		try {
			account.checkAcc(acc);
			account.checkPwd(pwd);
		} catch (InputParameterException e) {
			model.addAttribute("errorMsg", e);
			model.addAttribute("acc", account.getAcc());
			return "login/signup";
		}
		
		Account a = this.accountRepository.findByAcc(account.getAcc());
		
		System.out.println(a);
		if (a != null) {
			model.addAttribute("errorMsg", "帳號已被使用");
			model.addAttribute("acc", account.getAcc());
			return "login/signup";
		}
		
		try {
			this.accountRepository.save(account);
		} catch (Exception e) {
			model.addAttribute("errorMsg", "新增失敗");
			return "login/signup";
		}
		
		model.addAttribute("errorMsg", "新增成功，請重新登入");
		return "login/login";
	}
}