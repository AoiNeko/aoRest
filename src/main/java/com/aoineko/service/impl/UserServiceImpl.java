package com.aoineko.service.impl;

import com.aoineko.aop.LoginAop;
import com.aoineko.controller.UserController;
import com.aoineko.dao.LoginTokenDAO;
import com.aoineko.dao.UserDAO;
import com.aoineko.entity.LoginToken;
import com.aoineko.entity.User;
import com.aoineko.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
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
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

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
    private RSAPublicKey rsaPublicKey;
    @Autowired
    private RSAPrivateKey rsaPrivateKey;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private LoginTokenDAO loginTokenDAO;

    LoadingCache<String, User> userLoadingCache = CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(15, TimeUnit.DAYS).build(new CacheLoader<String, User>() {
        @Override
        public User load(String key) throws Exception {
            Long userId = loginTokenDAO.getUserIdByToken(key);
            LoginAop.logger.info("loading cache load user Id {}", userId);
            return userDAO.getById(userId);
        }
    });
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
    public boolean authVerify(String jwtString) {
        try {
            DecodedJWT jwt = jwtVerify(jwtString);
            Date now = new Date();
            if (jwt.getIssuer().equals(issuer) && jwt.getExpiresAt().after(now)) {
                Long userId = getTokenUserId(jwtString);
                if (userId == null) {
                    return false;
                }
                return true;
            }
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
        } catch (ExecutionException e) {
            LoginAop.logger.error("get cahce user fail");
        }
        return false;
    }


    private DecodedJWT jwtVerify(String jwtString) throws NoSuchAlgorithmException, InvalidKeySpecException {

        Algorithm algorithmRS = Algorithm.RSA256(rsaPublicKey, rsaPrivateKey);


        JWTVerifier verifier = JWT.require(algorithmRS)
                .withIssuer(issuer)
                .build(); //Reusable verifier instance
        DecodedJWT jwt = verifier.verify(jwtString);
        return jwt;
    }

    @Override
    public Long getTokenUserId(String key) throws ExecutionException {
        return userLoadingCache.get(key).getId();
    }


    @Override
    public void addUserLoginToken(String jwt, User user) {
        LoginToken loginToken =  new LoginToken();
        loginToken.setUserId(user.getId());
        loginToken.setToken(jwt);
        loginToken.setDeleted(false);
        loginTokenDAO.saveLoginToken(loginToken);
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
        return userDAO.getUserByNameAndPasswd(name, passwd);
    }

    private String getJwt() throws UnsupportedEncodingException {
        Algorithm algorithm = Algorithm.HMAC256("secret");
        String token = JWT.create()
                .withIssuer("aoineko")
                .sign(algorithm);
        return null;
    }

}
