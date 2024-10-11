package com.systex.lottery.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *  未登入用戶的可訪問路徑阻擋
 */
public class BlockFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
			// TODO Auto-generated method stub
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
//	        HttpSession session = httpRequest.getSession(false);

	        // 獲取當前的請求路徑
	        String requestURI = httpRequest.getRequestURI();
//	        String contextPath = httpRequest.getContextPath();

	        if (requestURI.contains(".")) {
	            // 重定向到首页
	            ((HttpServletResponse) response).sendRedirect(httpRequest.getContextPath() + "/");
	            return;
	        }
	        
	        chain.doFilter(httpRequest, httpResponse);
	        return;
	    }
}
