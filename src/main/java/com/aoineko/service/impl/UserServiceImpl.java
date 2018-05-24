package com.aoineko.service.impl;

import com.aoineko.dao.UserDAO;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Decoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Date;

/**
 * Created by com.aoineko on 2018/5/15.
 */
@Service
public class UserServiceImpl implements UserService {

    @Value("${aoi.jwt.pkey}")
    private String jwtPublicKey;

    @Value("${aoi.jwt.privateKey}")
    private String jwtPrivateKey;

    @Value("${aoi.jwt.issuer}")
    private String issuer;

    @Autowired
    private UserDAO userDAO;
    @Override
    public User validate(String name, String passwd) {
        User user = getUserByNameAndPasswd(name, passwd);
        return user;
    }

    @Override
    public int addUser(User user) {
        return userDAO.addUser(user);
    }

    @Override
    public String genJWT(User user) {
        String token = null;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] privatekeyDecode = decoder.decodeBuffer(jwtPrivateKey);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privatekeyDecode);
            X509EncodedKeySpec pKeySpec = new X509EncodedKeySpec(decoder.decodeBuffer(jwtPublicKey));
            KeyFactory kf = KeyFactory.getInstance("RSA");
            RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) kf.generatePrivate(keySpec);
            RSAPublicKey rsaPublicKey = (RSAPublicKey) kf.generatePublic(pKeySpec);
            Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);
            Date now = new Date();
            token = JWT.create()
                    .withIssuer(issuer).withClaim("userName", user.getName()).withIssuedAt(now).
                            withExpiresAt(DateUtils.addDays(now, 5))
                    .sign(algorithmRS);
        } catch (NoSuchAlgorithmException e) {
        } catch (IOException e) {
        } catch (InvalidKeySpecException e) {
        }

        return token;
    }

    private User getUserByNameAndPasswd(String name, String passwd) {
        return  userDAO.getUserByNameAndPasswd(name, passwd);
    }

    private String getJwt() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("aoineko")
                .sign(algorithm);
        return null;
    }

}
