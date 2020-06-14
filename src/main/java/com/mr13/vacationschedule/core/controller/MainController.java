package com.mr13.vacationschedule.core.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class MainController {

  @Value("${spring.profiles.active:prod}")
  private String profile;

  @GetMapping
  public String main(Model model) {

    model.addAttribute("isDevMode", "dev".equals(profile));

    return "index";
  }
}
