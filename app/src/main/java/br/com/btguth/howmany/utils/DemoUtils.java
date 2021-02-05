package br.com.btguth.howmany.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.btguth.howmany.model.BaseItem;
import br.com.btguth.howmany.model.Counter;

public class DemoUtils {
  int currentOffset;

  public DemoUtils() {
  }

  public List<BaseItem> createList(List<Counter> list) {
    List<BaseItem> items = new ArrayList<>();
    BaseItem  bi = null;

    int position = 0;
    for(Counter item : list){
      items.add(new BaseItem(1, 1, position, item.getCounterName(), item.getCounterValue()));
      position++;
    }
    Collections.sort(items, new Comparator<BaseItem>(){
      public int compare(BaseItem b1, BaseItem b2) {
        return b1.getCounterValue().compareTo(b2.getCounterValue());
      }
    });

    /*
    for (int i = 0; i < items.size(); i++) {
      int colSpan = Math.random()  < 0.2f ? 2 : 1;

      int rowSpan = colSpan;
      BaseItem item = new BaseItem(colSpan, rowSpan, i,);

      items.add(item);
    }

    currentOffset += qty; */

    return items;
  }
}
