package br.com.btguth.howmany;

import android.os.Bundle;

import com.felipecsl.asymmetricgridview.library.Utils;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.library.widget.AsymmetricGridViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Parcelable;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import br.com.btguth.howmany.adapter.DefaultListAdapter;


public class MainActivity extends AppCompatActivity {
    private AsymmetricGridView listView;
    private ListAdapter adapter;
    private final DemoUtils demoUtils = new DemoUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (AsymmetricGridView) findViewById(R.id.listView);
        adapter = new DefaultListAdapter(this, demoUtils.moarItems(6));

        listView.setRequestedColumnCount(3);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 1));
        //listView.setDebugging(true);
        listView.setAllowReordering(true);
        listView.setAdapter(getNewAdapter());

        listView.setOnItemClickListener(this::onItemClick);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                listView.onSaveInstanceState();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentOffset",1);
        outState.putInt("itemCount", adapter.getCount());
        for (int i = 0; i < adapter.getCount(); i++) {
            outState.putParcelable("item_" + i, (Parcelable) adapter.getItem(i));
        }
    }


    private AsymmetricGridViewAdapter getNewAdapter() {
        return new AsymmetricGridViewAdapter(this, listView, adapter);
    }

    public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view,
                            int position, long id) {
        Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }

}