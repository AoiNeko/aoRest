import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;

public class JWTTest {

    final static String privateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQC5NI/6aKa3wg4a6g3oBCgZmBgUQxbJ1AXwPF1wsS5SicyRtwKAJX84oHZ9KAQRLBVE/UC0dO0DrTPCXQE/Nzv1cG+BDlF86dCDT3IEyy3899bFsKLd6wbJw2yx7XXQpJ0w9QHxQpL697a/JqxE3pigQPmrvlJJyIUwbOUT5PYKsC/B2FjYmLTZzyZ0qBawF1L2NOsR/0I2czdsiwVaztS2ER+CT/uht5Xm3IfDWUPlH1HZhlea0eY9SaHlDvhujXE7Smqzijn/6RVniosdvlqghuuERz3sWrWlC+OmiiDBl1j6N/FewlVJe1U6adUwr6lyIQRdy0YCn4WrNcCtyJMZAgMBAAECggEAeC9883IwJnVes+aJSbRQ1XMWxSdYRXc6t1BlDrlcJyHXSAQsMj6jFXtECSoLoZ0q3E9ASxrJqCYgvZOfOIe+eCMTqPtCtD4DGwNWKXg0isHGdRmQR1S6XfpcsgY3+0Kn41pLfWXHfed8hwUwq6yL/QrNOr9SJSFkFS3FZqihZgMdW3TXwlnCQa7CS3cjrzKLJ1hof2ftgS8oR1ZtsifCUs9zDPjxwIJ3XWrje5+k7vo/OWuoSrgx5GatSeDdClCeUYoqWX87+RAmodyl/MrQYKK3Mpjw7Gte7MaRtoOjJnV/qVK03CYVPlFBhZHU5QalCR+U9AL2EM8fkkSgE30+0QKBgQDy/9KjXFLfLspr5zN9wMTonBSF79zzwp2s3FeA8eRO3ZkWlUPQ95n2D9hCSqKQmTpZFJBCbiu8JxS8eRmBoUOe6Q2DA4816D4s1+MyW6PIKUpzzmG+F+zjwq4BZQMPb28LVK7DLFxYL6vPKXkMvZ9miKslc7PIMr9zcYoh8t2WrQKBgQDDHS3hmcJCKlv35SfkGOu40/NDKrcTtts+sQbjl2hKLXn99L8T3DbMYNkO/JYesoAaY81vbvGuLety9YUQWzXYZmBAciIrZDdCVbDk+NPa3VrqND5J59iKybhiBIfbtJIyPcPL5oxJs5qXAYEvpz+obk+2kx+oysUJIjMRJxQ3nQKBgBRrQMTvZhtQ8Dt+8wm3IBS3wNW8YSGukddLsKKqMNgbsNh/9HHjzHErxa1UXjKuXYPMwY6DeXNXCVwJBQaqiWcaCEOhEfCisk7MWVAK+UlBhvsSNY5mrkY5PqvpVAeBAqC+He1SlfPnFZXT01Mpv/I6u77q6QmCkineOZA+uzYFAoGAG4qr5lORA0v9bXGwftcxtwZcKVgHPcYrDp9ojInb09S1iq6YplIIfjMRkLcA7dZelNsPrbIodWDQAos7vEJTyHczEQXLYvqjfj6gWMHzDcr/QV4ciMwsWfL9jwB6uP21QVhMoiSqGuE6aiRxOuvN5ZWktO3xox70T0S/lqVAilUCgYBG8qPFk3Gcimg4WYBiaP/MRLRr/M3XXUOg7tn3ex9UmLWtIfbaw9lLFexMMcNSQTfDTrn3j07Ljq7W1KmatTvzHF0Hess+QohFflXq1J4FFoWaxaGVXRYTxA09FEdm+9uo3udhkF6quioUH6S8Pii1IwL+7gBC+zflJSriBWA9NQ==";
    final static String publicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuTSP+mimt8IOGuoN6AQoGZgYFEMWydQF8DxdcLEuUonMkbcCgCV/OKB2fSgEESwVRP1AtHTtA60zwl0BPzc79XBvgQ5RfOnQg09yBMst/PfWxbCi3esGycNsse110KSdMPUB8UKS+ve2vyasRN6YoED5q75SSciFMGzlE+T2CrAvwdhY2Ji02c8mdKgWsBdS9jTrEf9CNnM3bIsFWs7UthEfgk/7obeV5tyHw1lD5R9R2YZXmtHmPUmh5Q74bo1xO0pqs4o5/+kVZ4qLHb5aoIbrhEc97Fq1pQvjpoogwZdY+jfxXsJVSXtVOmnVMK+pciEEXctGAp+FqzXArciTGQIDAQAB";


    @Test
    public void testSignJwt() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        BASE64Decoder decoder = new BASE64Decoder();
        byte[] privatekeyDecode = decoder.decodeBuffer(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privatekeyDecode);
        X509EncodedKeySpec pKeySpec = new X509EncodedKeySpec(decoder.decodeBuffer(publicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(pKeySpec);
        Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

        String token = JWT.create()
                .withIssuer("auth0").withClaim("ectDate", new Date().getTime()).withClaim("eprDay", 3).
                        withExpiresAt(DateUtils.addDays(new Date(), 5))
                .sign(algorithmRS);
        System.out.println(token);
    }

    @Test
    public void testVerifyToken() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {

        String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImVjdERhdGUiOjE1MjY5Nzc2MTAxMjksImV4cCI6MTUyNzQwOTYxMCwiZXByRGF5IjozfQ.KA2FtaAarRhRE4TUr1OdOhCZa9fOEE1E1iY_cmybfoBCdHiMsSj-Aa0CExKXksrJPEzLVNmpkqDO70xECc1xAEm4xJ1LQMa7gOyF4xlJrcGX_0TwxhrPQLNHAbXeUzh_Jn79F4Y_tNdUiBAESSzbvAsaQXoxqIV1MnjuepHZgUQfCfBiaSNWwnWpxJ3uvHPHAcH9wfqU9F2QsLlh_-Nv8HHNHTib5NE60ecbSLn_PblDTwMIolVSLYw2q2QhJ_g4nPPIPHQtQCV2nETKuglPIY2ZPOq8r431BvARbZd8y-ljvuxLp5Ia24ZvmHmUgVzHZkPFF3ttze0g5_n9sffYYA";
        BASE64Decoder decoder = new BASE64Decoder();
        byte[] privatekeyDecode = decoder.decodeBuffer(privateKey);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privatekeyDecode);
        X509EncodedKeySpec pKeySpec = new X509EncodedKeySpec(decoder.decodeBuffer(publicKey));
        KeyFactory kf = KeyFactory.getInstance("RSA");
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
        RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(pKeySpec);
        Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);

        try {

            JWTVerifier verifier = JWT.require(algorithmRS)
                    .withIssuer("auth0")
                    .build(); //Reusable verifier instance
            DecodedJWT jwt = verifier.verify(token);

            System.out.println(jwt.getIssuer());
            System.out.println(jwt.getClaim("ectDate").asDate());
            System.out.println(jwt.getClaim("eprDay").asInt());
        } catch (JWTVerificationException exception) {
            //Invalid signature/claims
            System.out.println("1ihyaoishdoiahd");
        }
    }


    @Test
    public void timezoneTest() {

        long dayMillis =  1000 * 60 *60 * 24;

        Instant now = Instant.now();
        System.out.println(Timestamp.valueOf(now.atZone(ZoneId.of("GMT")).toLocalDateTime()));

        Long timeStamp = 1528133742131l;

        Long dayStart = timeStamp / dayMillis * dayMillis;

        Long dayEnd = dayStart + dayMillis -1 ;
        System.out.println(dayStart);
        System.out.println(dayEnd);
        System.out.println(new Date(dayStart).toInstant().atZone(ZoneId.of("GMT")).toString());
        System.out.println(new Date(dayEnd));
    }

    @Test
    public void testDateUtils() {
        System.out.println(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
    }
}
