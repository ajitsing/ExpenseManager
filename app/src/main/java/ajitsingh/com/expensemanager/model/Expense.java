package ajitsingh.com.expensemanager.model;

import java.util.Date;

public class Expense {
  private String type;
  private Date date;
  private Double amount;

  public Expense(String type, Date date, Double amount) {
    this.type = type;
    this.date = date;
    this.amount = amount;
  }
}
