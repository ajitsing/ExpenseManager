package ajitsingh.com.expensemanager.activity;

import android.app.Activity;
import android.os.Bundle;

import com.echo.holographlibrary.Bar;
import com.echo.holographlibrary.BarGraph;

import java.util.ArrayList;
import java.util.List;

import ajitsingh.com.expensemanager.R;
import ajitsingh.com.expensemanager.database.ExpenseDatabaseHelper;
import ajitsingh.com.expensemanager.presenter.CurrentMonthExpensePresenter;
import ajitsingh.com.expensemanager.view.CurrentMonthExpenseView;

public class CurrentMonthExpenseActivity extends Activity implements CurrentMonthExpenseView {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.expense_graph);

    ExpenseDatabaseHelper expenseDatabaseHelper = new ExpenseDatabaseHelper(this);
    CurrentMonthExpensePresenter presenter = new CurrentMonthExpensePresenter(this, expenseDatabaseHelper);

    presenter.plotGraph();
  }

  @Override
  public void renderGraph(List<Bar> points) {
    BarGraph graph = (BarGraph)findViewById(R.id.graph);
    graph.setBars((ArrayList<Bar>) points);
  }
}
