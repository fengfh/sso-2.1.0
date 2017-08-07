/**
 * This file created at 2012-3-31.
 *
 * Copyright (c) 2002-2012 Bingosoft, Inc. All rights reserved.
 */
package bingo.sso.server.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bingo.sso.api.IWebSingleSignOnController;
import bingo.sso.api.authentication.Authentication;
import bingo.sso.api.authentication.principal.Principal;
import bingo.sso.openid.IOpenIdIdentifierParser;

/**
 * <code>{@link SecurityContextUtil}</code>
 *
 * TODO : document me
 *
 * @author yohn
 */
public class SecurityContextUtil {
	private static IWebSingleSignOnController webSingleSignOnController;
	private static IOpenIdIdentifierParser openIdIdentifierParser;
	
	public static boolean isLogined(HttpServletRequest req, HttpServletResponse resp){
		return webSingleSignOnController.getAuthentication(req, resp) != null;
	}

	public static String getAccount(HttpServletRequest req, HttpServletResponse resp){
		Authentication authentication = webSingleSignOnController.getAuthentication(req, resp);
		if (authentication != null && authentication.getPrincipal() != null){
			return authentication.getPrincipal().getId();
		}
		return null;
	}
	
	public static String getOpenIdIdentifier(HttpServletRequest req, HttpServletResponse resp){
		Authentication authentication = webSingleSignOnController.getAuthentication(req, resp);
		if (authentication != null && authentication.getPrincipal() != null){
			return openIdIdentifierParser.account2Identifier(req, authentication.getPrincipal().getId());
		}
		return null;
	}


	/**
	 * @param openIdIdentifierParser the openIdIdentifierParser to set
	 */
	public void setOpenIdIdentifierParser(
			IOpenIdIdentifierParser openIdIdentifierParser) {
		SecurityContextUtil.openIdIdentifierParser = openIdIdentifierParser;
	}

	public void setWebSingleSignOnController(
			IWebSingleSignOnController webSingleSignOnController) {
		SecurityContextUtil.webSingleSignOnController = webSingleSignOnController;
	}

		
}
