package sk.stuba.fei.feidroid.feidroidbeta;

import java.util.List;

import sk.stuba.fei.feidroid.feidroidbeta.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MyAdapter extends ArrayAdapter<String> {
  private final Context context;
  private final List<String> values;

  public MyAdapter(Context context, List<String> values) {
    super(context, R.layout.perm_list, values);
    this.context = context;
    this.values = values;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
    LayoutInflater inflater = (LayoutInflater) context
        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    
    View rowView = inflater.inflate(R.layout.list_perm, parent, false);
    TextView textView = (TextView) rowView.findViewById(R.id.label);

    textView.setText(values.get(position).substring(1));
    
    if(values.get(position).startsWith("1"))  
    	textView.setBackgroundColor(Color.GREEN);
    else if(values.get(position).startsWith("2"))  
    	textView.setBackgroundColor(Color.YELLOW);
    else
    	textView.setBackgroundColor(Color.RED);

    return rowView;
  }
} 