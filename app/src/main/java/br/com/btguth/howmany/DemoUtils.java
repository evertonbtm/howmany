package br.com.btguth.howmany;

import java.util.ArrayList;
import java.util.List;

import br.com.btguth.howmany.model.BaseItem;

final class DemoUtils {
  int currentOffset;

  DemoUtils() {
  }

  public List<BaseItem> moarItems(int qty) {
    List<BaseItem> items = new ArrayList<>();

    List<Integer> peso = new ArrayList<>();

    for (int i = 0; i < qty; i++) {
      //int colSpan = peso.get(i) < 0.2f ? 2 : 1;
      int colSpan = 1;
      if(i % 3 == 0){
        colSpan = 2;
      }else if (i % 5 == 0){
        colSpan = 3;
      }
      // Swap the next 2 lines to have items with variable
      // column/row span.
      // int rowSpan = Math.random() < 0.2f ? 2 : 1;
      int rowSpan = colSpan;
      BaseItem item = new BaseItem(colSpan, rowSpan, currentOffset + i);
      items.add(item);
    }

    currentOffset += qty;

    return items;
  }
}
