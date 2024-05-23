package com.CityThrillsMorocco.View.Service;

import com.CityThrillsMorocco.View.Model.View;
import com.CityThrillsMorocco.View.Repository.ViewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ViewService {
    private final ViewRepository viewRepository;

    public ViewService(ViewRepository viewRepository) {
        this.viewRepository = viewRepository;
    }

    public void saveView() {
        View view = new View();
        view.setDate(LocalDate.now());
        viewRepository.save(view);
    }

    public Long getTodayViews() {
        LocalDate today = LocalDate.now();
        return viewRepository.countByDate(today);
    }
}
