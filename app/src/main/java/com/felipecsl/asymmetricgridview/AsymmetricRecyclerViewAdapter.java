package com.felipecsl.asymmetricgridview;

import android.content.Context;
import android.os.Build;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

public final class AsymmetricRecyclerViewAdapter<T extends RecyclerView.ViewHolder>
    extends RecyclerView.Adapter<AdapterImpl.ViewHolder> implements AGVBaseAdapter<T> {
  private final AsymmetricRecyclerView recyclerView;
  private final AGVRecyclerViewAdapter<T> wrappedAdapter;
  private final AdapterImpl adapterImpl;

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  public AsymmetricRecyclerViewAdapter(Context context, AsymmetricRecyclerView recyclerView,
                                       AGVRecyclerViewAdapter<T> wrappedAdapter) {
    this.recyclerView = recyclerView;
    this.wrappedAdapter = wrappedAdapter;
    this.adapterImpl = new AdapterImpl(context, this, recyclerView);
    wrappedAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
      @Override public void onChanged() {
        recalculateItemsPerRow();
      }
    });
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override public AdapterImpl.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    return adapterImpl.onCreateViewHolder();
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override public void onBindViewHolder(AdapterImpl.ViewHolder holder, int position) {
    adapterImpl.onBindViewHolder(holder, position, recyclerView);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  @Override public int getItemCount() {
    // This is the row count for RecyclerView display purposes, not the actual item count
    return adapterImpl.getRowCount();
  }

  @Override public int getActualItemCount() {
    return wrappedAdapter.getItemCount();
  }

  @Override public AsymmetricItem getItem(int position) {
    return wrappedAdapter.getItem(position);
  }

  @Override public AsymmetricViewHolder<T> onCreateAsymmetricViewHolder(
      int position, ViewGroup parent, int viewType) {
    return new AsymmetricViewHolder<>(wrappedAdapter.onCreateViewHolder(parent, viewType));
  }

  @Override public void onBindAsymmetricViewHolder(
      AsymmetricViewHolder<T> holder, ViewGroup parent, int position) {
    wrappedAdapter.onBindViewHolder(holder.wrappedViewHolder, position);
  }

  @Override public int getItemViewType(int position) {
    return wrappedAdapter.getItemViewType(position);
  }

  @RequiresApi(api = Build.VERSION_CODES.KITKAT)
  void recalculateItemsPerRow() {
    adapterImpl.recalculateItemsPerRow();
  }
}
