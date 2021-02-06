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
    //boolean isRegular = getItemViewType(position) == 0;

    /*if (convertView == null) {
      v = layoutInflater.inflate(
          isRegular ? R.layout.adapter_item : R.layout.adapter_item_odd, parent, false);
    } else {
      v = convertView;
    }*/

    v = layoutInflater.inflate(R.layout.adapter_item , parent, false);

    TextView tag = (TextView) v.findViewById(R.id.counter_tag);
    TextView value = (TextView) v.findViewById(R.id.counter_value);
    TextView unity_alias = (TextView) v.findViewById(R.id.counter_unity_alias);

    tag.setText(String.valueOf(item.getCounter().getCounterName()));
    value.setText(String.valueOf(item.getCounter().getCounterValue()));
    unity_alias.setText(item.getCounter().getMeasureUnityAlias());

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