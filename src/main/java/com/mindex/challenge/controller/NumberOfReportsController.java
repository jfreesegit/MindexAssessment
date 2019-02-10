package com.mindex.challenge.controller;

import com.mindex.challenge.data.ReportingStructure;
import com.mindex.challenge.service.NumberOfReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NumberOfReportsController {
  private static final Logger LOG = LoggerFactory.getLogger(EmployeeController.class);
  @Autowired
  private NumberOfReportsService numberOfReportsService;

  @GetMapping("/numberOfReports/{id}")
  public ReportingStructure read(@PathVariable String id) {
    LOG.debug("Received employee create request for id [{}]", id);
    ReportingStructure rs = numberOfReportsService.read(id);
    return rs;
  }

}