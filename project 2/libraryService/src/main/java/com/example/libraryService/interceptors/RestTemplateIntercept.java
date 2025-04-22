package com.example.libraryService.interceptors;

import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
import org.springframework.security.core.context.SecurityContextHolder;

public class RestTemplateIntercept implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        String token = RequestContext.getToken();
        System.out.println(token);
        if (token != null) {
            request.getHeaders().add("Authorization", "Bearer " + token);
        }
        return execution.execute(request, body);
    }

}
