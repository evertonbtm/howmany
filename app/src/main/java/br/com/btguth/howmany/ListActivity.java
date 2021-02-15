package br.com.btguth.howmany;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import br.com.btguth.howmany.adapter.CardArrayAdapter;
import br.com.btguth.howmany.dao.CounterDAO;
import br.com.btguth.howmany.model.BaseItem;
import br.com.btguth.howmany.model.Counter;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "HowMany";
    private CardArrayAdapter cardArrayAdapter;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        initialize();

    }


    public void initialize(){
        listView = (ListView) findViewById(R.id.card_listView);

        cardArrayAdapter = new CardArrayAdapter(getApplicationContext(), R.layout.layout_list);

        CounterDAO dao = new CounterDAO(this);
        cardArrayAdapter.addAll(dao.getAllCounters());
        listView.setAdapter(cardArrayAdapter);
    }

}