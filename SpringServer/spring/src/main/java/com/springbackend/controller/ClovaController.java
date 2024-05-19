package com.springbackend.controller;

import com.springbackend.clova.ClovaCreateService;
import com.springbackend.clova.ClovaBasicService;
import com.springbackend.clova.ClovaCustomService;
import com.springbackend.clova.ClovaViewService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
public class ClovaController {

    private final ClovaBasicService clovaBasicService;
    private final ClovaViewService clovaViewService;
    private final ClovaCreateService clovaCreateService;
    private final ClovaCustomService clovaCustomService;
    public ClovaController(ClovaBasicService clovaBasicService, ClovaCreateService clovaCreateService, ClovaViewService clovaViewService, ClovaCustomService clovaCustomService) {
        this.clovaBasicService = clovaBasicService;
        this.clovaCreateService =clovaCreateService;
        this.clovaViewService = clovaViewService;
        this.clovaCustomService = clovaCustomService;
    }


    //Clova Studio 호출
    @PostMapping("/clova_custom")
    public Mono<String> getResponseCustom(@RequestBody String data) {
        System.out.println(data);
        return clovaCustomService.getCompletion(data);
    }

    @PostMapping("/clova")
    public Mono<String> getResponse(@RequestBody String data) {
        return clovaBasicService.getCompletion(data);
    }

    @PostMapping("/create/clova")
    public Mono<String> create() throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return clovaCreateService.getCreate();
    }

   @GetMapping("/clova-view")
    public Mono<String> view(@RequestParam(value ="query",required = false, defaultValue="ef9swe3v") String query) throws UnsupportedEncodingException, NoSuchAlgorithmException, InvalidKeyException {
        return clovaViewService.getView(query);
    }

}