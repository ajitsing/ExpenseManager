package ajitsingh.com.expensemanager.model;

public class Expense {
  private String type;
  private String date;
  private Long amount;
  private Integer id;

  public Expense(Long amount, String type, String date) {
    this.type = type;
    this.date = date;
    this.amount = amount;
  }

  public Expense(Integer id, Long amount, String type, String date) {
    this(amount, type, date);
    this.id = id;
  }

  public Long getAmount() {
    return amount;
  }

  public String getType() {
    return type;
  }

  public String getDate() {
    return date;
  }

  public Integer getId() {
    return id;
  }
}
