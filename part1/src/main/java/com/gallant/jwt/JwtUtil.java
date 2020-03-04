package com.gallant.jwt;


import io.jsonwebtoken.*;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;


/**
 * Created by huangjunhao on 20/2/21.
 */
public class JwtUtil {
    /**
     * 解密
     * @param jsonWebToken
     * @param base64Security
     * @return
     */
    public static Claims parseJWT(String jsonWebToken, String base64Security) {
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(base64Security.getBytes())
                    .parseClaimsJws(jsonWebToken).getBody();
            return claims;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * 前三个参数为自己用户token的一些信息比如id，权限，名称等。不要将隐私信息放入（大家都可以获取到）
     * @param map
     * @param base64Security
     * @return
     */
//    public static String createJWT(Map<String, Object> map, String base64Security) {
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//        //添加构成JWT的参数
//        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JWT")
//                .setPayload(new Gson().toJson(map))
//                .signWith(signatureAlgorithm,base64Security.getBytes()); //估计是第三段密钥
//        //生成JWT
//        return builder.compact();
//    }

    public static void main(String[] args) throws InvalidKeySpecException, NoSuchAlgorithmException {
//        Map<String, Object> map = new HashMap<String, Object>();
//        map.put("iss", "R33482C953");
//        map.put("city", "898765");
//        map.put("appkey", "HMu1H/cmyKDOiHv41Y9lLROuOlOo+PPG8F4/RotRmNc=");
//        map.put("iat", new Date().getTime());
        //String token="eyJraWQiOiJlWGF1bm1MIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmF1dG9ob21lLm5ldyIsImV4cCI6MTU4MjI3MTAwNSwiaWF0IjoxNTgyMjcwNDA1LCJzdWIiOiIwMDA2OTQuZDk0Yjc1Y2Q4MTA0NDhkNmI3YzRiMzk4OTE2ZmYzM2UuMDkxMSIsImF0X2hhc2giOiJHZmEzS3NEOUt2LWlIZlBtYXlSc1ZBIiwiZW1haWwiOiJxc25wOHV1YTYyQHByaXZhdGVyZWxheS5hcHBsZWlkLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImlzX3ByaXZhdGVfZW1haWwiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNTgyMjcwMjcwfQ.cAVRN2mEUceP6GDMguQ01i4QrTfDnYopmLW8nJLTJRnUtjc2pIuBN8Sutk-HDddYoykZoeS38Ma4S28BEt_StnIvNXQdZ7SMzmVzcYpTMA6H89EMcN31pgZw_bKaxgwcnya7iIyGL6CYSszEcTLrO5JPx9Ly8lG3xXJ-7HuFhD6iZsBkkzUJWxNqpllGnvw-lyUNt30XAsP3fmqnWlXk0OaLe8ZhxgHrVb8UB4dzXfpj4lcqRt8MEJCSdkEveAkOwwDlvkAX7lNGoe8QYuKufzzF72eImVfnZSToqA1NO1fHkr4ikkZZZSYV5OJy72v4hONJJAfPF8nTdzqv892URA";
        //密钥
        ///String keyt = "RSA";
        //System.out.println("JWT解密的结果："+ parseJWT(token, keyt));
        Post();
        PublicKey pk=getPublicKey("iGaLqP6y-SJCCBq5Hv6pGDbG_SQ11MNjH7rWHcCFYz4hGwHC4lcSurTlV8u3avoVNM8jXevG1Iu1SY11qInqUvjJur--hghr1b56OPJu6H1iKulSxGjEIyDP6c5BdE1uwprYyr4IO9th8fOwCPygjLFrh44XEGbDIFeImwvBAGOhmMB2AD1n1KviyNsH0bEB7phQtiLk-ILjv1bORSRl8AK677-1T8isGfHKXGZ_ZGtStDe7Lu0Ihp8zoUt59kx2o9uWpROkzF56ypresiIl4WprClRCjz8x6cPZXU2qNWhu71TQvUFwvIvbkE1oYaJMb0jcOTmBRZA2QuYw-zHLwQ",
                "AQAB");
        //String id_token="eyJraWQiOiI4NkQ4OEtmIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmF1dG9ob21lLm5ldyIsImV4cCI6MTU4MjI3NDczOSwiaWF0IjoxNTgyMjc0MTM5LCJzdWIiOiIwMDA2OTQuZDk0Yjc1Y2Q4MTA0NDhkNmI3YzRiMzk4OTE2ZmYzM2UuMDkxMSIsImF0X2hhc2giOiJzbTNrR3RPN2N3Rm9sX3hqczlJaFZBIiwiZW1haWwiOiJxc25wOHV1YTYyQHByaXZhdGVyZWxheS5hcHBsZWlkLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImlzX3ByaXZhdGVfZW1haWwiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNTgyMjc0MDgxfQ.GKYO-D-sjS4_z13NRucYJ0gt-SCxRwkBX1UfB8x36SI6ETJ7peNNI2XrqU3swg-ff76ucsRxlw8I4hsulWQv_lMNkxjXFtkKtPK_8VsUZcn-XpDPgBdIm80BrEetj9P40FCpMAB2N7K5zUuk5-hMFXqltr996Ma2iDc_6ZHVmWHX2YEXUZsP3OUSTfr-JpnxjAzEcCtDZnteb0KiGrq7DvdzdnLtkSCJdqVaLButkMizR3wcdm4ftlUTyf45-Gjsbq7PhvnUFziTCDGmiF0kAn7PZmuw34EnvM20kHW1Rdyal2HhV-8WbNIWXtoKsZwOR8lgIZjqMrYj05-LHvLSQA";
        String id_token = "eyJraWQiOiJlWGF1bm1MIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiJodHRwczovL2FwcGxlaWQuYXBwbGUuY29tIiwiYXVkIjoiY29tLmF1dG9ob21lLm5ldyIsImV4cCI6MTU4MjUzNTk5MCwiaWF0IjoxNTgyNTM1MzkwLCJzdWIiOiIwMDA2OTQuZDk0Yjc1Y2Q4MTA0NDhkNmI3YzRiMzk4OTE2ZmYzM2UuMDkxMSIsImF0X2hhc2giOiJVOXRlbjA2XzF0N0owMFVLNGMzei1RIiwiZW1haWwiOiJxc25wOHV1YTYyQHByaXZhdGVyZWxheS5hcHBsZWlkLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjoidHJ1ZSIsImlzX3ByaXZhdGVfZW1haWwiOiJ0cnVlIiwiYXV0aF90aW1lIjoxNTgyNTM1MzMwfQ.jO_boNaz9copEK8B_TD7PKdYR_oPgcwXKhf-vqNZ2vTymhTx4Hlhp4cCySpxTciqu7DuZ20xILCK-NJ4WCsWgQIAi86YnUYUN1Mjcr-UVj7TxfhEih2bOMArBhYdkEf93d2ig1vhXItZu-rUWJkEOycv2Pk_cFmfb6LcypVfqSA_jgIU-ac6-LPUiSHYKmlkcn6OY37vXxTEFYOtMoDW414WQjecByfEn5N9WK_mCMahWfB8P0TuQx5clfbIH-mYDTGbsYZAND-3UE8knfkKWDMhbJI_h0x5GvfKLekSJs4bLmTislFhl_0sy7-WaGsZY0A9prg2XWsKtOrTPAOPgQ";
        verify(pk, id_token, "com.autohome.new", "000694.d94b75cd810448d6b7c4b398916ff33e.0911");

        //System.out.println(build("iGaLqP6y-SJCCBq5Hv6pGDbG_SQ11MNjH7rWHcCFYz4hGwHC4lcSurTlV8u3avoVNM8jXevG1Iu1SY11qInqUvjJur--hghr1b56OPJu6H1iKulSxGjEIyDP6c5BdE1uwprYyr4IO9th8fOwCPygjLFrh44XEGbDIFeImwvBAGOhmMB2AD1n1KviyNsH0bEB7phQtiLk-ILjv1bORSRl8AK677-1T8isGfHKXGZ_ZGtStDe7Lu0Ihp8zoUt59kx2o9uWpROkzF56ypresiIl4WprClRCjz8x6cPZXU2qNWhu71TQvUFwvIvbkE1oYaJMb0jcOTmBRZA2QuYw-zHLwQ","AQAB"));
    }


    static public void Post() {
        try {
            String postURL = "https://appleid.apple.com/auth/token";
            PostMethod postMethod = null;
            postMethod = new PostMethod(postURL);
            postMethod.setRequestHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");
            NameValuePair[] data = {
                    new NameValuePair("code", "c2431bfc65cc345ee83451d123043cd08.0.nwzu.JNw4sqtT0wdowsI6qZGd3w"),
                    new NameValuePair("client_id", "com.autohome.new"),
                    new NameValuePair("client_secret", "eyJraWQiOiJZTkNLMlFOVkdTIiwiYWxnIjoiRVMyNTYifQ.eyJpc3MiOiJSMzM0ODJDOTUzIiwiaWF0IjoxNTgyMjAxMTkyLCJleHAiOjE1OTc3NTMxOTIsImF1ZCI6Imh0dHBzOi8vYXBwbGVpZC5hcHBsZS5jb20iLCJzdWIiOiJjb20uYXV0b2hvbWUubmV3In0.9Zvz2uBGJPLim335A_gBJf_fWA2FEsG9uoC6CPkHp7Y_apXazljpXs_i9mwZ7VTnn51vTnou20yzkIz9Renk3g"),
                    new NameValuePair("grant_type", "authorization_code"),
            };

            postMethod.setRequestBody(data);

            org.apache.commons.httpclient.HttpClient httpClient = new org.apache.commons.httpclient.HttpClient();
            int response = httpClient.executeMethod(postMethod); // 执行POST方法
            String result = postMethod.getResponseBodyAsString();
            System.out.println(result);
        } catch (Exception e) {

            throw new RuntimeException(e.getMessage());
        }
    }

    static public RSAPublicKeySpec build(String n, String e) {
        BigInteger modulus = new BigInteger(1, Base64.decodeBase64(n));
        BigInteger publicExponent = new BigInteger(1, Base64.decodeBase64(e));
        return new RSAPublicKeySpec(modulus, publicExponent);
    }

//    #获取验证所需的PublicKey
    static public PublicKey getPublicKey(String n,String e)throws NoSuchAlgorithmException, InvalidKeySpecException {
        BigInteger bigIntModulus = new BigInteger(1,Base64.decodeBase64(n));
        BigInteger bigIntPrivateExponent = new BigInteger(1,Base64.decodeBase64(e));
        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(bigIntModulus, bigIntPrivateExponent);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        return publicKey;
    }

//    #通过下面这个方法验证JWT的有效性
//    # jwt 就是 identityToken：授权用户的JWT凭证
//    # audience就是APPID
//    # subject 就是 就是userId
    static public int verify(PublicKey key, String jwt, String audience, String subject) {
        JwtParser jwtParser = Jwts.parser().setSigningKey(key);
        jwtParser.requireIssuer("https://appleid.apple.com");
        jwtParser.requireAudience(audience);
        jwtParser.requireSubject(subject);
        try {
            Jws<Claims> claim = jwtParser.parseClaimsJws(jwt);
            if (claim != null && claim.getBody().containsKey("auth_time")) {
                //return GlobalCode.SUCCESS;
            }
            //return GlobalCode.THIRD_AUTH_CODE_INVALID;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            //log.error("apple identityToken expired", e);
            //return GlobalCode.THIRD_AUTH_CODE_INVALID;
        } catch (Exception e) {
            e.printStackTrace();

            //log.error("apple identityToken illegal", e);
            //return GlobalCode.FAIL_ILLEGAL_REQ;
        }
        return 0;
    }

}
