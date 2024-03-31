//package io.sharing.passport.token;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.io.Decoders;
//import io.jsonwebtoken.security.Keys;
//
//import java.security.Key;
//
//public class TokenProvider {
//    private final Key key;
//    private final TokenProperties tokenProperties;
//
//    public TokenProvider(TokenProperties tokenProperties) {
//        byte[] keyBytes = Decoders.BASE64.decode(tokenProperties.getSecret());
//        this.key = Keys.hmacShaKeyFor(keyBytes);
//        this.tokenProperties = tokenProperties;
//    }
//
//    public static String generate(){
//        Jwts.builder()
//                .signWith()
//
//    }
//
//    public static Object parseToken(){
//        Jwts.parser()
//                .build();
//
//        return "";
//    }
//}
