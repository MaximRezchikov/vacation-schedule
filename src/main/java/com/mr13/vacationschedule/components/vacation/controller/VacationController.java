package com.mr13.vacationschedule.components.vacation.controller;

import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import com.mr13.vacationschedule.components.vacation.dto.VacationForm;
import com.mr13.vacationschedule.components.vacation.service.VacationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

import static com.mr13.vacationschedule.core.constants.UrlConstant.VACATION_URL;

@RestController
@RequestMapping(VACATION_URL)
@RequiredArgsConstructor
public class VacationController {

  private final VacationService vacationService;

  @GetMapping("/{id}")
  public Vacation getOne(@PathVariable("id") Long vacationId) {
    return vacationService.getOne(vacationId);
  }

  @GetMapping
  public List<Vacation> getAll() {
    return vacationService.getAll();
  }

//  @PostMapping
//  @ResponseStatus(HttpStatus.CREATED)
//  public Vacation saveVacation(@Valid @RequestBody VacationForm vacationForm) {
//    return vacationService.save(vacationForm);
//  }

  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public Vacation updateVacation(@PathVariable("id") Long vacationId, @Valid @RequestBody VacationForm vacationForm) {
    return vacationService.update(vacationId, vacationForm);
  }

  @DeleteMapping("{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Long vacationId) {
    vacationService.delete(vacationId);
  }
}
