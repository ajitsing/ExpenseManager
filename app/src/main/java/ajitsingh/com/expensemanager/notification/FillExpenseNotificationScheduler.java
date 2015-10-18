package ajitsingh.com.expensemanager.notification;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class FillExpenseNotificationScheduler {

  public void schedule(Context context) {
    AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);

    Intent notificationIntent = new Intent("android.media.action.DISPLAY_NOTIFICATION");
    notificationIntent.addCategory("android.intent.category.DEFAULT");

    PendingIntent broadcast = PendingIntent.getBroadcast(context, 100, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 21);
    calendar.set(Calendar.MINUTE, 00);
    calendar.set(Calendar.SECOND, 00);

    alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), broadcast);
  }
}
