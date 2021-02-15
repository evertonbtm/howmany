package br.com.btguth.howmany.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import br.com.btguth.howmany.R;
import br.com.btguth.howmany.dao.CounterDAO;
import br.com.btguth.howmany.model.Counter;

public class CardArrayAdapter  extends ArrayAdapter<Counter> {
    private static final String TAG = "HowMany";
    private List<Counter> cardList = new ArrayList<Counter>();
    CounterDAO dao;

    static class CardViewHolder {
        TextView line1;
        TextView line2;
        TextView line3;
        TextView lineDelete;
    }

    public CardArrayAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
        dao = new CounterDAO(context);
    }

    @Override
    public void add(Counter object) {
        cardList.add(object);
        super.add(object);
    }

    public void addAll(List<Counter> object) {
        cardList.addAll(object);
        super.addAll(object);
    }

    @Override
    public int getCount() {
        return this.cardList.size();
    }

    @Override
    public Counter getItem(int index) {
        return this.cardList.get(index);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        CardViewHolder viewHolder;

        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.layout_list, parent, false);
            viewHolder = new CardViewHolder();
            viewHolder.line1 = (TextView) row.findViewById(R.id.line1);
            viewHolder.line2 = (TextView) row.findViewById(R.id.line2);
            viewHolder.line3 = (TextView) row.findViewById(R.id.line3);
            viewHolder.lineDelete = (TextView) row.findViewById(R.id.lineDelete);
            row.setTag(viewHolder);
        } else {
            viewHolder = (CardViewHolder) row.getTag();
        }

        Counter card = getItem(position);
        viewHolder.line1.setText(card.getCounterName());
        viewHolder.line2.setText(card.getCounterValue().toString());
        viewHolder.line3.setText(card.getMeasureUnityAlias());

        viewHolder.lineDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    delete(card);
                    cardList.remove(position);
                    notifyDataSetChanged();
            }
        });

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }

    public void delete(Counter counter){
        dao.deleteCounter(counter);
    }

    private void notifyListener(){

    }
}