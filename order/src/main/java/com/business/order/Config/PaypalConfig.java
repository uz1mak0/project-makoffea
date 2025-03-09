package com.business.order.Config;

//import com.paypal.base.rest.APIContext;
//import com.paypal.base.rest.OAuthTokenCredential;
import com.paypal.core.PayPalEnvironment;
import com.paypal.core.PayPalHttpClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaypalConfig {
    @Value("${paypal.client.id}")
    private String clientId;

    @Value("${paypal.client.secret}")
    private String clientSecret;

    @Value("${paypal.mode}")
    private String mode;

//    @Bean
//    public APIContext apiContext() throws Exception {
//        APIContext apiContext = new APIContext(apiContext().getAccessToken());
//        apiContext.setMode(mode);
//        return apiContext;
//    }
    
    @Bean
    public PayPalEnvironment payPalEnvironment() {
    	return new PayPalEnvironment.Sandbox(clientId, clientSecret);
    }

//    private String getAccessToken() throws Exception {
//        String accessToken = new OAuthTokenCredential(clientId, clientSecret).getAccessToken();
//        return accessToken;
//    }
    
    @Bean
    public PayPalHttpClient payPalHttpClient(PayPalEnvironment environment) {
    	return new PayPalHttpClient(environment);
    }
    
    //...other configuration method if needed...
}
