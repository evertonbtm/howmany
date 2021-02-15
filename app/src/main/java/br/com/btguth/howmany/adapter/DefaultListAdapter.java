package  br.com.btguth.howmany.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import br.com.btguth.howmany.R;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

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

  @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
  @Override
  public View getView(int position, View convertView, @NonNull ViewGroup parent) {

    BaseItem item = getItem(position);

    View v = layoutInflater.inflate(R.layout.adapter_item , parent, false);

    RelativeLayout layout = v.findViewById(R.id.layout);
    TextView tag = (TextView) v.findViewById(R.id.counter_tag);
    TextView value = (TextView) v.findViewById(R.id.counter_value);
    TextView unity_alias = (TextView) v.findViewById(R.id.counter_unity_alias);

    tag.setText(String.valueOf(item.getCounter().getCounterName()));
    value.setText(String.valueOf(item.getCounter().getCounterValue()));
    unity_alias.setText(item.getCounter().getMeasureUnityAlias());

    switch (item.getColumnSpan()){
      case 2:
        tag.setTextSize(28);
        value.setTextSize(22);
        unity_alias.setTextSize(18);
        break;
      case 3:
        tag.setTextSize(36);
        value.setTextSize(26);
        unity_alias.setTextSize(24);
        break;
      default:
        tag.setTextSize(18);
        value.setTextSize(14);
        unity_alias.setTextSize(12);
    }

    if(item.getCounter().getCounterColor() == null || item.getCounter().getCounterColor() ==0){
      item.getCounter().setCounterColor(-1);
    }
    changeBackground(layout,item.getCounter().getCounterColor());
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

  private void changeBackground(RelativeLayout v , Integer color){

    StateListDrawable selector = (StateListDrawable) v.getBackground();
    DrawableContainer.DrawableContainerState drawableContainerState = (DrawableContainer.DrawableContainerState) selector.getConstantState();
    Drawable[] children = drawableContainerState.getChildren();

    LayerDrawable card_pressed = (LayerDrawable) children[0];
    LayerDrawable card = (LayerDrawable) children[1];
    Drawable back = card.getDrawable(1);
    Drawable back_pressed = card_pressed.getDrawable(1);

      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        back.setTint(color);
        back_pressed.setTint(manipulateColor(color,0.7f));
      }else{
        back.setColorFilter(color,PorterDuff.Mode.SRC_IN);
        back_pressed.setColorFilter(manipulateColor(color,0.7f),PorterDuff.Mode.SRC_IN);
      }

  }

  public static int manipulateColor(int color, float factor) {
    int a = Color.alpha(color);
    int r = Math.round(Color.red(color) * factor);
    int g = Math.round(Color.green(color) * factor);
    int b = Math.round(Color.blue(color) * factor);
    return Color.argb(a,
            Math.min(r,255),
            Math.min(g,255),
            Math.min(b,255));
  }
}