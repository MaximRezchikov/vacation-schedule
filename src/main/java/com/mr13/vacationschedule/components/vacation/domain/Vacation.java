package com.mr13.vacationschedule.components.vacation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mr13.vacationschedule.components.employee.domain.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
public class Vacation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private LocalDate startVacation;

  private LocalDate endVacation;

  @JsonIgnore
  @ManyToMany(mappedBy = "vacations", fetch = FetchType.LAZY)
  private Set<Employee> employees;
}
