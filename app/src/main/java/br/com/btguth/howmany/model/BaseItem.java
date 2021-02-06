package br.com.btguth.howmany.model;

import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;


import com.felipecsl.asymmetricgridview.AsymmetricItem;

import java.util.Comparator;

public class BaseItem implements AsymmetricItem {
  private int columnSpan;
  private int rowSpan;
  private int position;
  private Counter counter;

  public BaseItem() {
    this(1, 1, 0,null);
  }

  public BaseItem(int columnSpan, int rowSpan, int position,Counter counter) {
    this.columnSpan = columnSpan;
    this.rowSpan = rowSpan;
    this.position = position;
    this.counter = counter;
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  public BaseItem(Parcel in) {
    readFromParcel(in);
  }

  @Override public int getColumnSpan() {
    return columnSpan;
  }

  @Override public int getRowSpan() {
    return rowSpan;
  }

  public Counter getCounter() {
    return counter;
  }

  public int getPosition() {
    return position;
  }

  @Override public String toString() {
    return String.format("%s: %sx%s%s", position, rowSpan, columnSpan,counter);
  }

  @Override public int describeContents() {
    return 0;
  }

  @RequiresApi(api = Build.VERSION_CODES.M)
  private void readFromParcel(Parcel in) {
    columnSpan = in.readInt();
    rowSpan = in.readInt();
    position = in.readInt();
    counter = (Counter) in.readValue(Counter.class.getClassLoader());
  }

  @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeInt(columnSpan);
    dest.writeInt(rowSpan);
    dest.writeInt(position);
    dest.writeValue(counter);
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
