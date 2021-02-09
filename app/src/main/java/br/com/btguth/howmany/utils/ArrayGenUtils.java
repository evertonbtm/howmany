package br.com.btguth.howmany.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import br.com.btguth.howmany.model.BaseItem;
import br.com.btguth.howmany.model.Counter;

public class ArrayGenUtils {

  public ArrayGenUtils() {

  }

  public List<BaseItem> createList(List<Counter> list) {
    List<BaseItem> items = new ArrayList<>();

    int maxCount =0;
      for(int i=1; i <=list.size(); i++) {
      if(i % 4 == 0){
        maxCount++;
      }
    }
    List<Counter> listAux = new ArrayList<>();
    ArrayList<Counter> maxList = new ArrayList<>();
    listAux.addAll(list);
    for(int h=0;h <maxCount; h++) {
      Counter current = getMax(listAux);
      if(!maxList.contains(current)){
        maxList.add(current);
        listAux.remove(current);
      }
    }

    int position = 6;
    int positionBigCard = 0;
    main:for(Counter item : list){
      int columnSpan = 1;
      int rowSpan = 1;

        boolean sizeValidator = (list.size() % 5) == 0;
        for(Counter maxItem : maxList){
          if((maxItem.getCounterName() == item.getCounterName()
                  && maxItem.getCounterValue() == item.getCounterValue())
                  && !sizeValidator){
                    columnSpan = 2;
                    rowSpan = 2;
                    items.add(new BaseItem(columnSpan, rowSpan, positionBigCard, item));
                    positionBigCard++;
                    continue main;
              }
        }
      items.add(new BaseItem(columnSpan, rowSpan, position, item));
      position++;
    }

    return items;
  }

  public List<BaseItem> increment(List<BaseItem> items){

    List<BaseItem> itemsNew = new ArrayList<>();
    for(BaseItem item : items) {
      itemsNew.add(new BaseItem(item.getColumnSpan(), item.getRowSpan(), item.getPosition()+1, item.getCounter()));
    }
    return itemsNew;
  }

  public Counter getMax(List<Counter> list){
    int max = Integer.MIN_VALUE;
    Counter maxCounter = new Counter();
    for(int i=0; i<list.size(); i++){
      if(list.get(i).getCounterValue() > max){
        max = list.get(i).getCounterValue();
        maxCounter = list.get(i);
      }
    }
    return maxCounter;
  }
}
