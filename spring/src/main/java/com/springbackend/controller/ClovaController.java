package com.springbackend.controller;

import com.springbackend.service.ClovaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ClovaController {

    private final ClovaService clovaService;

    public ClovaController(ClovaService clovaService) {
        this.clovaService = clovaService;
    }

    @GetMapping("/clova")
    public Mono<String> getResponse(@RequestParam(required = false, defaultValue = "나는 데드리프트 80kg을 들어올릴 수 있어. 내 신체조건에 맞는 적절한 상체운동 루틴을 구성해줘") String query) {
        return clovaService.getCompletion(query);
    }
}