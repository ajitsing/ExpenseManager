package ajitsingh.com.expensemanager.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import ajitsingh.com.expensemanager.model.Expense;

public class ExpenseCollection {
  private List<Expense> expenses;

  public ExpenseCollection(List<Expense> expenses) {
    this.expenses = expenses;
  }

  public Long getTotalExpense(){
    Long totalExpense = 0l;
    for (Expense expense : expenses) {
      totalExpense += expense.getAmount();
    }

    return totalExpense;
  }

  public Map<String, List<Expense>> groupByDate() {
    Map<String, List<Expense>> expensesByDate = new HashMap<>();
    for (Expense expense : expenses) {
      if(expensesByDate.get(expense.getDate()) == null){
        List<Expense> expensesList = new ArrayList<>();
        expensesList.add(expense);
        expensesByDate.put(expense.getDate(), expensesList);

      } else {
        expensesByDate.get(expense.getDate()).add(expense);
      }
    }

    return expensesByDate;
  }

  public List<Expense> withoutMoneyTransfer() {
    ArrayList<Expense> expenses = new ArrayList<>();
    for (Expense expense : this.expenses) {
      if(!Objects.equals(expense.getType(), "Money-Transfer"))
        expenses.add(expense);
    }

    return expenses;
  }
}
