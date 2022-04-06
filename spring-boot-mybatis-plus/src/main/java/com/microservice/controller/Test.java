package com.microservice.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author guofei
 * @date 2022/4/2 5:51 PM
 */
public class Test {
  public static void main(String[] args) {

  }

  int calculateDaysBetweenDates(String startDate, String endDate) {
    int daysBetween = 0;
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      Date start = sdf.parse(startDate);
      Date end = sdf.parse(endDate);
      daysBetween = (int) ((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return daysBetween;
  }
  int calculateDaysBetweenDates(Date startDate, Date endDate) {
    int daysBetween = 0;
    try {
      daysBetween = (int) ((endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24));
    } catch (Exception e) {
      e.printStackTrace();
    }
    return daysBetween;
  }

}
