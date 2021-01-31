package br.com.btguth.howmany.model;

import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;


import com.felipecsl.asymmetricgridview.AsymmetricItem;

import java.util.Comparator;

public class BaseItem implements AsymmetricItem {
  private int columnSpan;
  private int rowSpan;
  private int position;
  private String counter_tag;
  private Integer counter_value;

  public BaseItem() {
    this(1, 1, 0,"Sample",1);
  }

  public BaseItem(int columnSpan, int rowSpan, int position,String counter_tag,int counter_value) {
    this.columnSpan = columnSpan;
    this.rowSpan = rowSpan;
    this.position = position;
    this.counter_tag = counter_tag;
    this.counter_value = counter_value;
  }

  public BaseItem(Parcel in) {
    readFromParcel(in);
  }

  @Override public int getColumnSpan() {
    return columnSpan;
  }

  @Override public int getRowSpan() {
    return rowSpan;
  }

  public String getCounterTag() {
    return counter_tag;
  }

  public Integer getCounterValue() {
    return counter_value;
  }

  public int getPosition() {
    return position;
  }

  @Override public String toString() {
    return String.format("%s: %sx%s%s%s", position, rowSpan, columnSpan,counter_tag,counter_value);
  }

  @Override public int describeContents() {
    return 0;
  }

  private void readFromParcel(Parcel in) {
    columnSpan = in.readInt();
    rowSpan = in.readInt();
    position = in.readInt();
    counter_tag = in.readString();
    counter_value = in.readInt();
  }

  @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeInt(columnSpan);
    dest.writeInt(rowSpan);
    dest.writeInt(position);
    dest.writeString(counter_tag);
    dest.writeInt(counter_value);
  }

  /* Parcelable interface implementation */
  public static final Parcelable.Creator<BaseItem> CREATOR = new Parcelable.Creator<BaseItem>() {
    @Override
    public BaseItem createFromParcel(@NonNull Parcel in) {
      return new BaseItem(in);
    }

    @Override
    @NonNull
    public BaseItem[] newArray(int size) {
      return new BaseItem[size];
    }
  };

}
