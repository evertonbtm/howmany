package br.com.btguth.howmany.adapter;

import android.widget.ListAdapter;
import java.util.List;

import br.com.btguth.howmany.model.BaseItem;

public interface BasicAdapter extends ListAdapter {

  void appendItems(List<BaseItem> newItems);

  void setItems(List<BaseItem> moreItems);
}
