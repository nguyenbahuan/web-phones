package com.example.springmvc.jwt;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.springmvc.service.UserService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	@Autowired
	private JWTUtils jwtUtils;

	@Autowired
	private UserService userService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String authHeader = request.getHeader("Authorization");
		final String jwtToken;
		final String userEmail;
		if (authHeader == null || authHeader.isBlank()) {
			filterChain.doFilter(request, response);
			return;
		}
		jwtToken = authHeader.substring(7);
		userEmail = jwtUtils.extractUsername(jwtToken);
		UserDetails userDetails = userService.loadUserByUsername(userEmail);

		if (jwtUtils.isTokenValid(jwtToken, userDetails)) {
			SecurityContext securityContext = SecurityContextHolder.createEmptyContext();
			UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			token.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			securityContext.setAuthentication(token);
			SecurityContextHolder.setContext(securityContext);
		}
		filterChain.doFilter(request, response);

	}

	
}
