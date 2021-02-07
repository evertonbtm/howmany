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
    int size = list.size();
    int max = getMax(list);

    Collections.sort(items, new Comparator<BaseItem>(){
      public int compare(BaseItem b1, BaseItem b2) {
        return b2.getCounter().getCounterValue().compareTo(b1.getCounter().getCounterValue());
      }
    });

    for(Counter item : list){
      int columnSpan = 1;
      int rowSpan = 1;
      if(max == item.getCounterValue()){
        columnSpan = 2;
        rowSpan = 2;
      }
      items.add(new BaseItem(columnSpan, rowSpan, position, item));
      position++;
    }


    return items;
  }

  public int getMax(List<Counter> list){
    int max = Integer.MIN_VALUE;
    Counter maxCounter = new Counter();
    for(int i=0; i<list.size(); i++){
      if(list.get(i).getCounterValue() > max){
        max = list.get(i).getCounterValue();
        maxCounter = list.get(i);
      }
    }
    return max;
  }
}
