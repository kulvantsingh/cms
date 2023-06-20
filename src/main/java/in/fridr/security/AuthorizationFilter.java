package in.fridr.security;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.Jwts;

public class AuthorizationFilter extends BasicAuthenticationFilter{
	Environment env;



	
	public AuthorizationFilter(AuthenticationManager authenticationManager,Environment env) {
		super(authenticationManager);
		this.env=env;
		
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
	
		String authorizationHeader=request.getHeader(env.getProperty("authorization.token.header.name"));
		
		//System.out.println("hey authorization header is "+authorizationHeader+" "+authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix")));
		
		if(authorizationHeader==null||!authorizationHeader.startsWith(env.getProperty("authorization.token.header.prefix"))){
			
			chain.doFilter(request, response);
			return;
			
		}
		
		UsernamePasswordAuthenticationToken authentication=getAuthentication(request);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		chain.doFilter(request, response);
		
		
		
	}
	
 private UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest req) {
	 String authorizationHeader=req.getHeader(env.getProperty("authorization.token.header.name"));
	 if(authorizationHeader==null) {
		 return null;
	 }
	 String token=authorizationHeader.replace(env.getProperty("authorization.token.header.prefix")+" ","");
	 System.out.println("final token is "+token);
	 @SuppressWarnings("deprecation")
	String userId=Jwts.parser()
			 	   .setSigningKey(env.getProperty("token.secret"))
			 	   .parseClaimsJws(token)
			 	   .getBody()
			 	   .getSubject();
	 
	 if(userId==null) {
		 return null;
	 }
	
	 return new UsernamePasswordAuthenticationToken(userId,null,new ArrayList<>());
 }

}
