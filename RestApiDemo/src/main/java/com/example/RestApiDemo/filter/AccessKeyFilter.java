package com.example.RestApiDemo.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.OncePerRequestFilter;

@WebFilter(urlPatterns = "/api/*")
@Slf4j
public class AccessKeyFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response
            , FilterChain filterChain)
            throws jakarta.servlet.ServletException, java.io.IOException {

        log.info("AccessKeyFilter.doFilterInternal() begins");
        String authorization = request.getHeader("Authorization");
        if(authorization != null && authorization.startsWith("Bearer")){
            String token = authorization.replace("Bearer ", "").trim();
            if("hanbit-access-key".equals(token)){
                filterChain.doFilter(request, response);
                log.info("AccessKeyFilter.doFilterInternal() returns");
                return;
            }
        }
        response.setStatus(HttpStatus.UNAUTHORIZED.value());
        log.info("AccessKeyFilter.doFilterInternal() returns Unautorized");

    }

}
