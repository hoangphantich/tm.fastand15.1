package com.hoangphan.tutor0302_exttask;

public class Work {
  private String name;
  private int hour;
  private int minute;
  private boolean isDone;
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public int getHour() {
    return hour;
  }
  public void setHour(int hour) {
    this.hour = hour;
  }
  public int getMinute() {
    return minute;
  }
  public void setMinute(int minute) {
    this.minute = minute;
  }
  
  
  @Override
  public String toString() {
    return hour+":"+minute+" - "+name;
  }
  public boolean isDone() {
    return isDone;
  }
  public void setDone(boolean isDone) {
    this.isDone = isDone;
  }
  
  
}
