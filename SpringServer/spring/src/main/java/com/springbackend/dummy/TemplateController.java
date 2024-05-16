package com.springbackend.dummy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TemplateController {

    @GetMapping("/clova_create")
    public String createTask() {
        return "clova-create";
    }
    @GetMapping("/")
    public String index() {
        return "index";
    }
    @GetMapping("/clova_run")
    public String clovaRun(Model model, @RequestParam(defaultValue = "기본") String mode) {
        model.addAttribute("mode", mode);
        return "clova-run";
    }

    @GetMapping("/clova_run_custom")
    public String clovaRunCustom(Model model) {
        return clovaRun(model, "학습");
    }
}