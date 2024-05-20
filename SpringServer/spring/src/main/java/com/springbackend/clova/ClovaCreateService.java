package com.springbackend.clova;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.core.io.FileSystemResource;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class ClovaCreateService {

    private final WebClient webClient;
    private String timestamps;


    public ClovaCreateService(WebClient webClient) {
        this.webClient = webClient;
    }

    public Mono<String> getCreate() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        this.timestamps = String.valueOf(System.currentTimeMillis());
        try {
            MultiValueMap<String, Object> formData = new LinkedMultiValueMap<>();
            formData.add("name", "exRoutine4_create");
            formData.add("model", "HCX-003");
            formData.add("method", "LoRA");
            formData.add("taskType", "GENERATION"); 
            formData.add("trainEpochs", 10);
            formData.add("learningRate", 1.0E-6);
            formData.add("trainingDataset", new FileSystemResource("E:\\fineTuning_u8.csv"));

            return webClient.post()
                    .uri("https://clovastudio.apigw.ntruss.com/tuning/v2/tasks")
                    //.contentType(MediaType.MULTIPART_FORM_DATA)
                    .header("Content-Type","multipart/form-data")
                    .header("X-NCP-APIGW-TIMESTAMP", this.timestamps)
                    .header("X-NCP-IAM-ACCESS-KEY", "IkfaDWxyBjhP76LK9UDM")
                    .header("X-NCP-APIGW-SIGNATURE-V2", makeSignature())
                    .body(BodyInserters.fromMultipartData(formData))
                    .retrieve()
                    .bodyToMono(String.class);

        } catch (UnsupportedEncodingException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
            return Mono.error(new RuntimeException("Error creating signature", e));
        }

    }

    public String makeSignature() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        String space = " ";					// one space
        String newLine = "\n";					// new line
        String method = "POST";					// method
        String url = "/tuning/v2/tasks";	// url (include query string)
        //String timestamp = this.timestamps;			// current timestamp (epoch)
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
