package  br.com.btguth.howmany.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import br.com.btguth.howmany.R;
import androidx.annotation.NonNull;

import java.util.List;

import br.com.btguth.howmany.model.BaseItem;


public class DefaultListAdapter extends ArrayAdapter<BaseItem> implements BasicAdapter {

  private final LayoutInflater layoutInflater;

  public DefaultListAdapter(Context context, List<BaseItem> items) {
    super(context, 0, items);
    layoutInflater = LayoutInflater.from(context);
  }

  public DefaultListAdapter(Context context) {
    super(context, 0);
    layoutInflater = LayoutInflater.from(context);
  }

  @Override
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {
    View v;

    BaseItem item = getItem(position);
    boolean isRegular = getItemViewType(position) == 0;

    if (convertView == null) {
      v = layoutInflater.inflate(
          isRegular ? R.layout.adapter_item : R.layout.adapter_item_odd, parent, false);
    } else {
      v = convertView;
    }

    TextView tag;
    TextView value;
    if (isRegular) {
      tag = (TextView) v.findViewById(R.id.counter_tag);
      value = (TextView) v.findViewById(R.id.counter_value);
    } else {
      tag = (TextView) v.findViewById(R.id.counter_tag_odd);
      value = (TextView) v.findViewById(R.id.counter_value_odd);
    }

    value.setText(String.valueOf(item.getPosition()));
    tag.setText("Contador");

    return v;
  }

  @Override
  public int getViewTypeCount() {
    return 2;
  }

  @Override
  public int getItemViewType(int position) {
    return position % 2 == 0 ? 1 : 0;
  }

  public void appendItems(List<BaseItem> newItems) {
    addAll(newItems);
    notifyDataSetChanged();
  }

  public void setItems(List<BaseItem> moreItems) {
    clear();
    appendItems(moreItems);
  }
}