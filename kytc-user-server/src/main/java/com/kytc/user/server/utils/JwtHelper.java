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
	public String generateJsonWebToken(JwtUser user) {
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
	
	/**
	 * 获取用户名
	 * @param token
	 * @return
	 */
	public String getUsername(String token){
		token = initToken(token);
    	Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    	return claims.get("username").toString();
    }

    /**
     * 是否过期
     * @param token
     * @return
     */
    public boolean isExpiration(String token){
		token = initToken(token);
    	Claims claims = Jwts.parser().setSigningKey(APPSECRET_KEY).parseClaimsJws(token).getBody();
    	//System.out.println(System.currentTimeMillis()-claims.getExpiration().getTime());
    	return claims.getExpiration().before(new Date());
    }
	
	public static void main(String[] args) {
		/*String name = "acong";
		String role = "rol";
		JwtUser jwtUser = new JwtUser();
		jwtUser.setId(111);
		jwtUser.setUsername("hezhitong");
		String token = getInstance().generateJsonWebToken(jwtUser);
		System.out.println(token);
		
		Claims claims = getInstance().checkJWT(token);
		System.out.println(claims.get("username"));
		
		System.out.println(getInstance().getUsername(token));
		System.out.println(getInstance().isExpiration(token));*/
		Jwts.parser();
		JwtUser jwtUser = new JwtUser();
		jwtUser.setId(111);
		jwtUser.setUsername("hezhitong");
		String token = new JwtHelper().generateJsonWebToken(jwtUser);
		System.out.println(token);
    }

}