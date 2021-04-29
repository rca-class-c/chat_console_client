package utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;

import java.time.Instant;
import java.time.temporal.TemporalUnit;
import java.util.*;
import io.jsonwebtoken.security.SignatureException;
import io.jsonwebtoken.security.InvalidKeyException;


/**
 * Token controller
 *
 * @author Ntwari Clarance Liberiste
 * @since 1.0
 */
public class Token {

    private TreeMap<String,String> payload;
    private final String secretKey = "V3uZKw7RPYW85FNTwiqm8JjZ9ep/jn2bZ8xy0vl8S+1OmHjJoCSNbXLl+hb8ulmnUTkxaPtBcYKM/60PjoMyPw==";
    private final String subject;

    /**
     * @author Ntwari Clarance Liberiste
     * Token controller
     *
     * @param subject Who you are issuing card for, this tend to be unique identifier of the token consumer
     *                like email,id,username or something, of String type
     * @param payload Data to be included in the jwt token, of TreeMap type [optional]
     */
    public Token(String subject,TreeMap<String,String> payload){
        this.subject = subject;
        this.payload = payload;
    }

    /**
     * @author Ntwari Clarance Liberiste
     * Token controller
     *
     * @param subject Who you are issuing card for, this tend to be unique identifier of the token consumer
     *                like email,id,username or something, of String type
     */
    public Token(String subject){
        this.subject = subject;
        this.payload = new TreeMap<>();
    }

    /**
     * @author Ntwari Clarance Liberiste
     * Token controller
     */
    public Token(){
        this.subject = "";
        this.payload = new TreeMap<>();
    }



    /**
     * @author Ntwari Clarance Liberiste
     * Get payload data
     *
     * @return payload data, of TreeMap type
     */
    public TreeMap<String, String> getPayload() {
        return payload;
    }

    /**
     * @author Ntwari Clarance Liberiste
     * Re-assign jwt payload data
     *
     * @param payload data to be included in payload, of TreeMap type
     */
    public void setPayload(TreeMap<String, String> payload) {
        this.payload = payload;
    }


    /**
     *
     * Generates jwt based token basing on given data in class initialisation,
     * given data in initialization will be used as claims so they will appear
     * in the token
     *
     * @param timeToLast Time that it will take for token to be active, of type long
     * @param unit Unit of the time, of java.time.temporal.TemporalUnit type
     *
     * @return token, of String type
     *
     * @throws InvalidKeyException Detecting usage of invalid key in signing jwt token signature
     *
     *
     * @author Ntwari Clarance Liberiste
     */
    public String generateToken(long timeToLast, TemporalUnit unit) throws InvalidKeyException{
        Instant now = Instant.now();
        byte[] secrete  = Base64.getDecoder().decode(this.secretKey);
        String jwtToken = "";
        JwtBuilder jwt;

        try{
            jwt= Jwts.builder().setSubject(this.subject);

            //adding claims to the jwt token only if payload contains data
            if(!payload.isEmpty()){

                for(Map.Entry<String,String> entry: this.payload.entrySet())
                    jwt.claim(entry.getKey(),entry.getValue());
            }

            jwtToken = jwt.setIssuedAt(Date.from(now))
                    .setExpiration(Date.from(now.plus(timeToLast, unit)))
                    .signWith(Keys.hmacShaKeyFor(secrete))
                    .compact();

        }catch(InvalidKeyException e){
            throw new InvalidKeyException(e.getMessage());
        }

        return jwtToken;
    }


    /**
     * Decoding jwt token created by Token class, make sure that you
     * decode token created by Token Class so that it will match secret Key
     * unless you will get an io.jsonwebtoken.security.SignatureException exception
     *
     * @param jwt jwt token, of String type
     *
     * @return decoded string, of String type
     *
     * @throws SignatureException invalid signature exception
     * @throws ExpiredJwtException token expiration exception
     *
     *
     * @author Ntwari Clarance Liberiste
     */
    public String decodeToken(String jwt) throws SignatureException, ExpiredJwtException {
        byte[] secrete  = Base64.getDecoder().decode(this.secretKey);
        String results = "";

        results = Jwts.parser()
                .setSigningKey(Keys.hmacShaKeyFor(secrete))
                .parseClaimsJws(jwt)
                .toString();

        return results;
    }

    /**
     * @author Ntwari Clarance Liberiste
     * Validating token, when token is valid it will return true where as token has expired
     * or has invalid signature it will return false
     * @param token token to validate, of String type
     * @return boolean that indicates validity of token
     */
    public boolean isValidToken(String token){
        String decodedData = "";
        boolean tokenIsValid = false;
        try{
            decodedData = this.decodeToken(token);

            if(decodedData.length() > 1)
                tokenIsValid = true;

        } catch (SignatureException | ExpiredJwtException ignored){
        }

        return tokenIsValid;
    }

}

