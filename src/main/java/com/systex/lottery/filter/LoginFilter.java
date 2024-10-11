package com.systex.lottery.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.systex.lottery.exception.InputParameterException;
import com.systex.lottery.model.Account;
import com.systex.lottery.model.AccountRepository;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *  登陸請求的過濾以及驗證
 */
public class LoginFilter implements Filter {

	private AccountRepository accountRepository;
	
	public LoginFilter(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
			// http 設定
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        HttpSession session = httpRequest.getSession();
	        
	        // 設定為 UTF-8
	        httpRequest.setCharacterEncoding("UTF-8");
	        httpResponse.setCharacterEncoding("UTF-8");
	        httpResponse.setContentType("application/json; charset=UTF-8");

	        // 重置 session 的 errorMsg，用於 jsp 的 EL 錯誤顯示
	        session.setAttribute("errorMsg", "");
	        
	        /**
	         *  獲取當前的請求路徑
	         */
	        String requestURI = httpRequest.getRequestURI();
	        
	        /**
	         * 專案路徑(web網址進入點)
	         */
	        String contextPath = httpRequest.getContextPath();
	        
	        /**
	         * HTTP 請求方式
	         */
	        String method = httpRequest.getMethod();

	        /**
	         * 是否已登入
	         */
	        boolean isLoggedIn = false;
	        
	        /**
	         * 確認請求路徑是否為 首頁結尾 或 login 開頭或 resource 開頭 
	         */
	        boolean isIndexOrLoginOrResourcePath = requestURI.endsWith(contextPath + "/") 
	        		|| requestURI.startsWith(contextPath + "/login")
	        		|| requestURI.startsWith(contextPath + "/resource");
	        
	        /**
	         * 確認請求路徑是否為 login 開頭
	         */
	        boolean isLoginPath = requestURI.startsWith(contextPath + "/login");
	        
	        /**
	         * 確認請求路徑是否為要求登入(Post /ajaxlogin)
	         */
	        boolean isLoginAsk = "POST".equalsIgnoreCase(method) && requestURI.equals(contextPath + "/ajaxlogin");
	        
	        /**
	         * 確認是否為登出請求
	         */
	        boolean isLogoutAsk = "GET".equalsIgnoreCase(method) && requestURI.equals(contextPath + "/login/logout"); 
	        

	        // 如果有 session 與 user 名稱的話，就是已登入
	        if (session != null && session.getAttribute("user") != null) {
	        	isLoggedIn = true;
	        }
	        
	        // 登入邏輯驗證
	        if(!isLoggedIn && isLoginAsk) {
	        	
	        	// 讀取 ajax 請求( json 資料)
	            StringBuilder bufferedReader = new StringBuilder();
	            try (BufferedReader reader = httpRequest.getReader()) {
	                String line;
	                while ((line = reader.readLine()) != null) {
	                	bufferedReader.append(line);
	                }
	            }
	            
	            // 將資料從 json 轉為 Map
	            String jsonData = bufferedReader.toString();
	            ObjectMapper objectMapper = new ObjectMapper();
	            Map<String, String> data = objectMapper.readValue(jsonData, new TypeReference<Map<String, String>>() {});

	        	String acc = data.get("acc");
	        	String pwd = data.get("pwd");
	        	Account account = new Account();
	        	System.out.println("嘗試登入帳密: " + acc + " , " + pwd);
	        	
	        	// 驗證帳密
	        	try {
	        		account.checkAcc(acc);
	        		account.checkPwd(pwd);
	        	}
	        	catch (InputParameterException e) {
		    		session.setAttribute("errorMsg", "帳密格式錯誤");
		    		loginFail(response, httpResponse, "帳密格式錯誤");
	        		return;
	        	}
	        	
	        	// 輸入帳密
	        	account.setAcc(acc);
	        	account.setPwd(pwd);	        		


	        	
	        	// 搜尋帳號
	        	Account a = this.accountRepository.findByAcc(account.getAcc());
	        	
	        	// 驗證帳號
	        	boolean isAccCorrect = a != null;
	        	System.out.println("驗證帳號中");
	        	
	        	if(!isAccCorrect) {
	        		session.setAttribute("errorMsg", "帳號不存在");
		    		System.out.println("帳號不存在");
		            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 設置 401 拒絕授權 
		            httpResponse.setContentType("application/json; charset=UTF-8");  // 設置內容類型和編碼

		            String jsonResponse = "{\"message\": \"帳號不存在！\"}";
		            PrintWriter out = response.getWriter();
		            out.print(jsonResponse);
		            out.flush();
	        		return;
	        	}
	        	
	        	// 驗證密碼
	        	System.out.println("驗證密碼中");
	        	boolean isPwdCorrect = a.getPwd().equals(account.getPwd());
	        	
	        	if (!isPwdCorrect) {
	        		session.setAttribute("errorMsg", "密碼錯誤");
		    		System.out.println("密碼錯誤");
		            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 設置 401 拒絕授權 
		            httpResponse.setContentType("application/json; charset=UTF-8");  // 設置內容類型和編碼

		            String jsonResponse = "{\"message\": \"密碼錯誤！\"}";
		            PrintWriter out = response.getWriter();
		            out.print(jsonResponse);
		            out.flush();
	        		return;
	        	}
	        	
	        	// 登入成功
	        	session.setAttribute("user", a.getAcc());
	        	System.out.println("登入成功，跳轉回首頁 ");
	        	
	        	// 回傳 ajax 訊息
	            httpResponse.setStatus(HttpServletResponse.SC_OK);  // 設置 200 OK 
	            httpResponse.setContentType("application/json; charset=UTF-8");  // 設置內容類型和編碼

	            String jsonResponse = "{\"message\": \"登入成功！\"}";
	            PrintWriter out = response.getWriter();
	            out.print(jsonResponse);
	            out.flush();
		        return;
	        }
	        
	        if(isLoggedIn && isLogoutAsk) {
	        	session.removeAttribute("user");
	        	httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
	        	return;
	        }
	        
        	// 如果還想要到 login 與 login 內的頁面，就送回去首頁，除了logout以外
        	if(isLoggedIn && isLoginPath) {
        		httpResponse.sendRedirect(httpRequest.getContextPath() + "/");
	            return;
        	}
        	
        	// 如果已登入，且瀏覽登入頁以外的頁面，就放行
        	if(isLoggedIn) {
	            chain.doFilter(request, response);
	            return;
        	}

	        // 在非登入的狀態，過濾首頁與 login、login內、resouce 以外的頁面
	        if (isIndexOrLoginOrResourcePath) {
	            // 如果是這些頁面就繼續
	            chain.doFilter(request, response);
	            return;
	        }

	        // 都不是就強制跳轉到 login 頁面
	        httpResponse.sendRedirect(httpRequest.getContextPath() + "/login");
	        System.out.println("拒絕訪問");
            return;
	    }
	
	private void loginFail(ServletResponse response, HttpServletResponse httpResponse, String string) throws IOException {
		System.out.println(string);
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);  // 設置 401 拒絕授權 
        httpResponse.setContentType("application/json; charset=UTF-8");  // 設置內容類型和編碼

        String jsonResponse = "{\"message\": \"" + string + "\"}";
        PrintWriter out = response.getWriter();
        out.print(jsonResponse);
        out.flush();
	}
}
