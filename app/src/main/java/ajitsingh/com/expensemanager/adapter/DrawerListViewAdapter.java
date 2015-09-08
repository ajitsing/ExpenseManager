package ajitsingh.com.expensemanager.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ajitsingh.com.expensemanager.R;

public class DrawerListViewAdapter extends BaseAdapter{

  private final String[] drawerItems;
  private Context context;

  public DrawerListViewAdapter(Context context) {
    this.context = context;
    drawerItems = context.getResources().getStringArray(R.array.drawer_items);
  }

  @Override
  public int getCount() {
    return drawerItems.length;
  }

  @Override
  public String getItem(int position) {
    return drawerItems[position];
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup viewGroup) {
    if(convertView==null) {
      LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.drawer_list_item, null);
    }

    TextView item = (TextView) convertView.findViewById(R.id.drawer_list_item);
    item.setText(drawerItems[position]);

    return convertView;
  }
}
