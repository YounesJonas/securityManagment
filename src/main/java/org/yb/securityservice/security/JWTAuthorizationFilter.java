package org.yb.securityservice.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

public class JWTAuthorizationFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Headers", "Origin, Accept, X-Requested-With, Content-Type, Access-Control-Request-Method, Access-Control-Request-Headers,authorization");
        response.addHeader("Access-Control-Expose-Headers", "Access-Control-Allow-Origin, Access-Control-Allow-Credentials, authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET,POST,DELETE,PUT,PATCH");
        
        if (request.getMethod().equals("OPTIONS")) {
            response.setStatus(HttpServletResponse.SC_OK);
        } else if(request.getRequestURI().equals("/login")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        
        else {
		String jwt = request.getHeader(SecurityParams.JWT_HEADER_NAME);
		System.out.println("valeur de jwt "+ jwt);
		if(jwt==null || !jwt.startsWith(SecurityParams.HEADER_PREFIX)) {
			filterChain.doFilter(request, response);
			return;
		}else {
			// verify signature
			Algorithm algorithm = Algorithm.HMAC256(SecurityParams.SECRET);
			JWTVerifier jwtVerifier = JWT.require(algorithm).build();
			
			// decoder le jwt
			DecodedJWT decodedJWT = jwtVerifier.verify(jwt.substring(SecurityParams.HEADER_PREFIX.length(),jwt.length()));
			String tocken = jwt.substring(SecurityParams.HEADER_PREFIX.length(),jwt.length());
			
		String username = decodedJWT.getSubject();
		System.out.println("valeur de username envoyee dans le jwt " + username);
		System.out.println("valeur de tocken "+ tocken);
		List<String> roles = decodedJWT.getClaims().get("roles").asList(String.class);
		System.out.println("valeur de roles est " + roles);
		Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		roles.stream().forEach(
				
				(r) -> authorities.add(new SimpleGrantedAuthority(r))
				
				);
		
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username,null, authorities);
		SecurityContextHolder.getContext().setAuthentication(token);
		filterChain.doFilter(request, response);
		
		}
	}
		
	}

}
