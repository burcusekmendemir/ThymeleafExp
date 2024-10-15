package com.bilgeadam.springbootthymeleafjpa.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

@Controller
@RequestMapping(path = "")
//dilerseniz bu class yerine controladvice yapabilirsiniz.
public class MyErrorController implements ErrorController
{

	@GetMapping(path = "error")
	public ModelAndView errorPage(HttpServletRequest request)
	{
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		Object message = request.getAttribute(RequestDispatcher.ERROR_MESSAGE);
		Object exception = request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
		if (message != null)
		{
			System.err.println("message -> " + message.toString());
		}
		if (exception != null)
		{
			ServletException exc = (ServletException) exception;
			System.err.println("Exception message = " + exc.getMessage());
		}
		if (status != null)
		{
			Integer statusCode = Integer.valueOf(status.toString());
			if (statusCode == HttpStatus.NOT_FOUND.value())
			{
				return new ModelAndView("404");
			}
			else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value())
			{
				return new ModelAndView("500");
			}
		}
		return new ModelAndView("redirect:/");
	}

	//	jakarta.servlet.error.message
	//	jakarta.servlet.error.request_uri
	//	jakarta.servlet.error.servlet_name
	//	jakarta.servlet.error.status_code
	//	jakarta.servlet.forward.context_path
	//	jakarta.servlet.forward.mapping
	//	jakarta.servlet.forward.request_uri
	//	jakarta.servlet.forward.servlet_path
	//	org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR
	//	org.springframework.core.convert.ConversionService
	//	org.springframework.security.web.FilterChainProxy.APPLIED
	//	org.springframework.security.web.access.intercept.AuthorizationFilter@32c77b84.APPLIED
	//	org.springframework.security.web.context.SecurityContextHolderFilter.APPLIED
	//	org.springframework.web.context.request.async.WebAsyncManager.WEB_ASYNC_MANAGER
	//	org.springframework.web.filter.ServerHttpObservationFilter.context
	//	org.springframework.web.filter.ServerHttpObservationFilter.observation
	//	org.springframework.web.servlet.DispatcherServlet.CONTEXT
	//	org.springframework.web.servlet.DispatcherServlet.EXCEPTION
	//	org.springframework.web.servlet.DispatcherServlet.FLASH_MAP_MANAGER
	//	org.springframework.web.servlet.DispatcherServlet.LOCALE_RESOLVER
	//	org.springframework.web.servlet.DispatcherServlet.OUTPUT_FLASH_MAP
	//	org.springframework.web.servlet.DispatcherServlet.THEME_RESOLVER
	//	org.springframework.web.servlet.DispatcherServlet.THEME_SOURCE
	//	org.springframework.web.servlet.HandlerMapping.bestMatchingHandler
	//	org.springframework.web.servlet.HandlerMapping.bestMatchingPattern
	//	org.springframework.web.servlet.HandlerMapping.introspectTypeLevelMapping
	//	org.springframework.web.servlet.HandlerMapping.matrixVariables
	//	org.springframework.web.servlet.HandlerMapping.pathWithinHandlerMapping
	//	org.springframework.web.servlet.HandlerMapping.uriTemplateVariables
	//	org.springframework.web.servlet.handler.HandlerMappingIntrospector.CachedResult
	//	org.springframework.web.servlet.i18n.CookieLocaleResolver.LOCALE
	//	org.springframework.web.servlet.resource.ResourceUrlProvider
	//	org.springframework.web.util.ServletRequestPathUtils.PATH
	//	requestContextFilter.FILTERED
}