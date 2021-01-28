package br.com.btguth.howmany.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.felipecsl.asymmetricgridview.library.model.AsymmetricItem;

public class BaseItem implements AsymmetricItem {
  private int columnSpan;
  private int rowSpan;
  private int position;

  public BaseItem() {
    this(1, 1, 0);
  }

  public BaseItem(int columnSpan, int rowSpan, int position) {
    this.columnSpan = columnSpan;
    this.rowSpan = rowSpan;
    this.position = position;
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

  public int getPosition() {
    return position;
  }

  @Override public String toString() {
    return String.format("%s: %sx%s", position, rowSpan, columnSpan);
  }

  @Override public int describeContents() {
    return 0;
  }

  private void readFromParcel(Parcel in) {
    columnSpan = in.readInt();
    rowSpan = in.readInt();
    position = in.readInt();
  }

  @Override public void writeToParcel(@NonNull Parcel dest, int flags) {
    dest.writeInt(columnSpan);
    dest.writeInt(rowSpan);
    dest.writeInt(position);
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
