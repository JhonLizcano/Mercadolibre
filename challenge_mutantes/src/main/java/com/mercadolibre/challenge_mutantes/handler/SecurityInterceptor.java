package com.mercadolibre.challenge_mutantes.handler;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.mercadolibre.challenge_mutantes.utils.Constants;

@Component
public class SecurityInterceptor implements Filter {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SecurityInterceptor.class);

	@Override
	public void init(FilterConfig filterConfig) {
		LOGGER.info("Starting Filter");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		String contentType = httpServletRequest.getHeader(Constants.HTTP_HEADER_CONTENT_TYPE);
		String accept = httpServletRequest.getHeader(Constants.ACCEPT);
		
		if(contentType != null && 
				contentType.contains(MediaType.APPLICATION_JSON_VALUE) && 
				accept != null && 
				accept.contains(MediaType.APPLICATION_JSON_VALUE)) {
			chain.doFilter(httpServletRequest, httpServletResponse);
		} else {
			httpServletResponse.sendError(HttpStatus.BAD_REQUEST.value());
		}
		httpServletResponse.addHeader(Constants.HTTP_HEADER_X_CONTENT_TYPE_OPTION, 
				Constants.HTTP_HEADER_X_CONTENT_TYPE_OPTION_VALUE);
	}
	
	@Override
	public void destroy() {
		LOGGER.info("Destroyed Filter");
	}
}