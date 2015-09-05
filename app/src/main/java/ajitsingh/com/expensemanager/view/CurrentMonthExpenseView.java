package ajitsingh.com.expensemanager.view;

import com.echo.holographlibrary.Bar;

import java.util.List;

public interface CurrentMonthExpenseView {
  void renderGraph(List<Bar> points);
}
