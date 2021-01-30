package br.com.btguth.howmany.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.btguth.howmany.model.BaseItem;

public class DemoUtils {
  int currentOffset;

  public DemoUtils() {
  }

  public List<BaseItem> moarItems(int qty) {
    List<BaseItem> items = new ArrayList<>();

    BaseItem  bi = new BaseItem(1, 1, 4,"Cerveja",3);
    items.add(bi);
    bi = new BaseItem(2, 3, 0,"Jambrada",499);
    items.add(bi);
    bi = new BaseItem(1, 1, 2,"Médico",10);
    items.add(bi);
    bi = new BaseItem(1, 1, 3,"Dorgas",1);
    items.add(bi);
    bi = new BaseItem(1, 2, 1,"FastFood",50);
    items.add(bi);
    bi = new BaseItem(1, 1, 5,"Trabalho",0);
    items.add(bi);

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
