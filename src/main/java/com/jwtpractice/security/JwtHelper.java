package com.jwtpractice.security;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	public static final long JWT_TOKEN_VALIDITY = 5*60*60;
	
	private String secret="sdcfgjfukfmsghdvcsdgjmcsgeghdfibksdbsdjHFJIHK SKDYUGJU";
	
	//retrive username from jwt token
	
	public String getUsernameFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getSubject);
	}
	
	//retrive expiration date from jwt token
	public Date getExpirationDateFromToken(String token)
	{
		return getClaimFromToken(token,Claims::getExpiration);
	}
	
	public<T> T getClaimFromToken(String token,Function<Claims, T> claimsResolver)
	{
		final Claims claims=getClaimFromToken(token);
		return claimsResolver.apply(claims);
	}
	
	//for retriveing any information from token we will need the secret key
	private Claims getClaimFromToken(String token)
	{
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}
	
	private Boolean isTokenExpired(String token)
	{
		final Date expiration=getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}
	
	
	//generatetoken for user
	public String generateToken(UserDetails userDetails)
	{
		Map<String, Object> claims=new HashMap<>();
		return doGenerateToken(claims,userDetails.getUsername());
	}
	
	private String doGenerateToken(Map<String, Object> claims, String subject) {
		
		
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new java.util.Date())
				.setExpiration(new Date(System.currentTimeMillis()+JWT_TOKEN_VALIDITY*1000))
				.signWith(SignatureAlgorithm.HS512,secret).compact();
	}

	Boolean validateToken(String token ,UserDetails userDetails)
	{
		final String username=getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
