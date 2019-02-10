package com.mindex.challenge.service.impl;

import com.mindex.challenge.data.Employee;
import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.NumberOfReportsService;
import com.mindex.challenge.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NumberOfReportsServiceImpl implements NumberOfReportsService {
  private static final Logger LOG = LoggerFactory.getLogger(NumberOfReportsServiceImpl.class);

  @Autowired
  private EmployeeService employeeService;

  @Override
  public ReportingStructure read(String id) {
    ReportingStructure rs = new ReportingStructure();
    Employee e = employeeService.read(id);
    if(e != null) {
      rs.setEmployee(e);
      rs.setNumberOfReports(calculateNumberOfReports(e));
    }
    return rs;
  }
  private Integer calculateNumberOfReports(Employee e) {
    Integer numberOfEmployees = 0;
    if(e.getDirectReports() != null && !e.getDirectReports().isEmpty()) {
      for (Employee report : e.getDirectReports()) {
        numberOfEmployees++;
        Employee readEmployee = employeeService.read(report.getEmployeeId());
        if(readEmployee != null) {
          numberOfEmployees += calculateNumberOfReports(readEmployee);
        }
      }
    }
    return numberOfEmployees;
  }

}