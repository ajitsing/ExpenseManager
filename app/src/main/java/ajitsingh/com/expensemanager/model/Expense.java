package ajitsingh.com.expensemanager.model;

import java.util.Date;

public class Expense {
  private String type;
  private Date date;
  private Long amount;
  private Integer id;

  public Expense(Long amount, String type, Date date) {
    this.type = type;
    this.date = date;
    this.amount = amount;
  }

  public Expense(Integer id, Long amount, String type, Date date) {
    this(amount, type, date);
    this.id = id;
  }

  public Long getAmount() {
    return amount;
  }

  public String getType() {
    return type;
  }

  public Date getDate() {
    return date;
  }

  public Integer getId() {
    return id;
  }
}
