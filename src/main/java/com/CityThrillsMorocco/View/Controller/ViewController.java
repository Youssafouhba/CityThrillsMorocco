package com.CityThrillsMorocco.View.Controller;

import com.CityThrillsMorocco.View.Service.ViewService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/views")
public class ViewController {
    private final ViewService viewService;

    public ViewController(ViewService viewService) {
        this.viewService = viewService;
    }

    @PostMapping("/record")
    public void recordView() {
        viewService.saveView();
    }

    @GetMapping("/views/today")
    public Long getTodayViews() {
        return viewService.getTodayViews();
    }
}
