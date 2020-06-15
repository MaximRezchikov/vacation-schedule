package com.mr13.vacationschedule.components.employee.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mr13.vacationschedule.components.vacation.domain.Vacation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
@Entity
@Builder
@Table
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = "username")
@EqualsAndHashCode(of = {"id"})
public class Employee {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate birthday;

  @Column(nullable = false)
  private Long personnelNumber;

  @Column(nullable = false)
  private String post;

  @Column(nullable = false)
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
  private LocalDate startDate;

  @Column(unique = true)
  private String login;

  private String password;

  @JsonIgnore
  @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
  @JoinTable(name = "employee_vacation",
      joinColumns = @JoinColumn(name = "emplyee_id"),
      inverseJoinColumns = @JoinColumn(name = "vacation_id"))
  private Set<Vacation> vacations;
}
