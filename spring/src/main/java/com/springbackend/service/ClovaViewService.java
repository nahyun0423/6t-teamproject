package com.springbackend.service;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ClovaViewService {

    private final WebClient webClient;
    private String timestamps;


    public ClovaViewService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getView(String query) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        this.timestamps = String.valueOf(System.currentTimeMillis());
        try {

            return webClient.get()
                    .uri("https://clovastudio.apigw.ntruss.com/tuning/v2/tasks/ef9swe3v")
                    //.contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("Content-Type","application/json")
                    .header("X-NCP-APIGW-TIMESTAMP", this.timestamps)
                    .header("X-NCP-IAM-ACCESS-KEY", "IkfaDWxyBjhP76LK9UDM")
                    .header("X-NCP-APIGW-SIGNATURE-V2", makeSignature(query))
                    .retrieve()
                    .bodyToMono(String.class);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return Mono.error(new RuntimeException("Error creating signature", e));
        }

    }

    public String makeSignature(String query) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "GET";					// method
        String url = "/tuning/v2/tasks/ef9swe3v";	// url (include query string)
        String accessKey = "IkfaDWxyBjhP76LK9UDM";			// access key id (from portal or Sub Account)
        String secretKey = "8CK615AHxfoos5rMV4BGya0qEG7VyF763ZPQKB37";

        String message = new StringBuilder()
                .append(method)
                .append(space)
                .append(url)
                .append(newLine)
                .append(this.timestamps)
                .append(newLine)
                .append(accessKey)
                .toString();

        SecretKeySpec signingKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8), "HmacSHA256");
        Mac mac = Mac.getInstance("HmacSHA256");
        mac.init(signingKey);

        byte[] rawHmac = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
        return Base64.encodeBase64String(rawHmac);
    }
}
