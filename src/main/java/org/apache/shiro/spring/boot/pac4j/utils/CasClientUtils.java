/*
 * Copyright (c) 2017, vindell (https://github.com/vindell).
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.apache.shiro.spring.boot.pac4j.utils;

import org.apache.shiro.spring.boot.ShiroPac4jCasProperties;
import org.apache.shiro.spring.boot.ShiroPac4jProperties;
import org.apache.shiro.spring.boot.utils.StringUtils;
import org.pac4j.cas.client.CasClient;
import org.pac4j.cas.client.direct.DirectCasClient;
import org.pac4j.cas.client.direct.DirectCasProxyClient;
import org.pac4j.cas.client.rest.CasRestBasicAuthClient;
import org.pac4j.cas.client.rest.CasRestFormClient;
import org.pac4j.cas.config.CasConfiguration;
import org.springframework.boot.autoconfigure.web.ServerProperties;

public class CasClientUtils {

	public static CasClient casClient(CasConfiguration configuration,ShiroPac4jProperties pac4jProperties, ShiroPac4jCasProperties casProperties,ServerProperties serverProperties) {

		CasClient casClient = new CasClient(configuration);
		casClient.setCallbackUrl( pac4jProperties.getCallbackUrl());
		casClient.setName(StringUtils.hasText(casProperties.getCasClientName()) ? casProperties.getCasClientName() : "CasClient");
		
		return casClient;
	}
	
	public static DirectCasClient directCasClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		DirectCasClient casClient = new DirectCasClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getDirectCasClientName()) ? casProperties.getDirectCasClientName() : "DirectCasClient");
		
		return casClient;
	}

	public static DirectCasProxyClient directCasProxyClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties, String serverUrl) {
		
		DirectCasProxyClient casClient = new DirectCasProxyClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getDirectCasProxyClientName()) ? casProperties.getDirectCasProxyClientName() : "DirectCasProxyClient");
		casClient.setServiceUrl(serverUrl);
		
		return casClient;
	}

	public static CasRestBasicAuthClient casRestBasicAuthClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		CasRestBasicAuthClient casClient = new CasRestBasicAuthClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getCasRestBasicAuthClientName()) ? casProperties.getCasRestBasicAuthClientName() : "RestBasicAuthClient");
		if(StringUtils.hasText(casProperties.getHeaderName())) {	
			casClient.setHeaderName(casProperties.getHeaderName());
		}
		if(StringUtils.hasText(casProperties.getPrefixHeader())) {	
			casClient.setPrefixHeader(casProperties.getPrefixHeader());
		}
		
		return casClient;
	}

	/**
	 * 通过rest接口可以获取tgt，获取service ticket，甚至可以获取CasProfile
	 * @return
	 */
	public static CasRestFormClient casRestFormClient(CasConfiguration configuration,ShiroPac4jCasProperties casProperties) {
		
		CasRestFormClient casClient = new CasRestFormClient();
		
		casClient.setConfiguration(configuration);
		casClient.setName(StringUtils.hasText(casProperties.getCasRestFormClientName()) ? casProperties.getCasRestFormClientName() : "RestFormClient");
		if(StringUtils.hasText(casProperties.getUsernameParameterName())) {	
			casClient.setUsernameParameter(casProperties.getUsernameParameterName());
		}
		if(StringUtils.hasText(casProperties.getPasswordParameterName())) {	
			casClient.setPasswordParameter(casProperties.getPasswordParameterName());
		}

		return casClient;
	}
	
}
