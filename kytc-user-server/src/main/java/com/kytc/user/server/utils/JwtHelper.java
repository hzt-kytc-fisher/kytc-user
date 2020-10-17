package com.kytc.user.server.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import java.util.Date;

public class JwtHelper {
	private final String TOKEN_PREFIX = "Bearer ";
	private final String SUBJECT = "congge";
	private final long EXPIRITION = 1000 * 60;
	private final String APPSECRET_KEY = "com.kytc.secret";
	public String generateJwt(JwtUser user) {
		if (StringUtils.isEmpty(user.getId()) || StringUtils.isEmpty(user.getUsername()) ) {
			return null;
		}
		String token = Jwts
				.builder()
				.setSubject(SUBJECT)
				.claim("id", user.getId())
				.claim("username", user.getUsername())
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRITION))
				.signWith(SignatureAlgorithm.HS256, APPSECRET_KEY).compact();
		return TOKEN_PREFIX + token;
	}

	public String generateJwt(Long id,String username){
		JwtUser user = new JwtUser();
		user.setId(id);
		user.setUsername(username);
		return generateJwt(user);
	}
	
	public Claims checkJWT(String token) {
		try {
			token = initToken(token);
			final Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
			return claims;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	private String initToken(String token){
		if(token.startsWith(TOKEN_PREFIX)){
			token = token.substring(TOKEN_PREFIX.length());
		}
		return token;
	}

	public String getUsername(String token){
		token = initToken(token);
    	Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    	return claims.get("username").toString();
    }

	public Long getUserId(String token){
		token = initToken(token);
		Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
		return Long.valueOf(claims.get("id").toString());
	}

    public boolean isExpiration(String token){
		token = initToken(token);
    	Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    	//System.out.println(System.currentTimeMillis()-claims.getExpiration().getTime());
    	return claims.getExpiration().before(new Date());
    }

	public static void main(String[] args) {
		JwtHelper jwtHelper = new JwtHelper();
		//String jwt = jwtHelper.generateJwt(1L,"hezhitong");
		String jwt = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjb25nZ2UiLCJpZCI6MSwidXNlcm5hbWUiOiJoZXpoaXRvbmciLCJpYXQiOjE2MDI4NTI2NjYsImV4cCI6MTYwMjg1MjcyNn0.xHPssAM-iYD-FRLL50n4A7vO5EEI98hfTLAbTI5iGUw";
		System.out.println(jwt);
		System.out.println(jwtHelper.getUserId(jwt));
		System.out.println(jwtHelper.getUsername(jwt));
	}
}
