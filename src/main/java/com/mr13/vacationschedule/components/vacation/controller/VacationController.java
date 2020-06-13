package com.mr13.vacationschedule.components.vacation.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mr13.vacationschedule.core.constants.UrlConstant.VACATION_URL;

@RestController
@RequestMapping(VACATION_URL)
@RequiredArgsConstructor
public class VacationController {

}
