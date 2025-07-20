package com.ecommerce.project.configs;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Component("userPublicEndpointConfig")
@Getter
public class PublicEndpointsConfig {

    private List<EndPoint> endPoints;
    private List<AntPathRequestMatcher> requestMatchers;

    public PublicEndpointsConfig() {
        this.endPoints = Arrays.asList(
                new EndPoint(HttpMethod.POST, "/auth/login"),
                new EndPoint(HttpMethod.GET, "/api/public/**")
        );
        this.requestMatchers = createRequestMatchers(endPoints);
    }

    private List<AntPathRequestMatcher> createRequestMatchers(List<EndPoint> endPoints) {
        return endPoints.stream()
        .filter(endpoint -> endpoint.getUrlPattern() != null)
        .map(endpoint -> createRequestMatcher(endpoint)).toList();
    }

    private AntPathRequestMatcher createRequestMatcher(EndPoint endPoint) {
        String method = endPoint.getMethod() != null ? endPoint.getMethod().name() : null;
        return new AntPathRequestMatcher(endPoint.getUrlPattern(), method);
    }

    public Boolean isPublicEndpoint ( HttpServletRequest request){
        // for( AntPathRequestMatcher matcher : requestMatchers){
        //     if(matcher.matches(request)){
        //         return true;
        //     }
        // }
        // return false;
        return requestMatchers.stream().anyMatch(matcher -> matcher.matches(request));
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class EndPoint {

        private HttpMethod method;
        private String urlPattern;
    }
}
