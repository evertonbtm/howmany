package br.com.btguth.howmany;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;


import com.felipecsl.asymmetricgridview.AsymmetricGridView;
import com.felipecsl.asymmetricgridview.AsymmetricGridViewAdapter;
import com.felipecsl.asymmetricgridview.Utils;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;

import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import br.com.btguth.howmany.adapter.DefaultListAdapter;
import br.com.btguth.howmany.dao.CounterDAO;
import br.com.btguth.howmany.model.Counter;
import br.com.btguth.howmany.utils.DemoUtils;
import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity {
    private AsymmetricGridView listView;
    private ListAdapter adapter;
    private final DemoUtils demoUtils = new DemoUtils();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        listView = (AsymmetricGridView) findViewById(R.id.listView);
        adapter = new DefaultListAdapter(this, demoUtils.createList(7));

        listView.setRequestedColumnCount(3);
        listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        listView.setDebugging(true);
        listView.setAllowReordering(true);
        listView.setAdapter(getNewAdapter());
        listView.setOnItemClickListener(this::onItemClick);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCounter();
                listView.onSaveInstanceState();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private AsymmetricGridViewAdapter getNewAdapter() {
        return new AsymmetricGridViewAdapter(this, listView, adapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setNumColumns(int numColumns) {
        listView.setRequestedColumnCount(numColumns);
        listView.determineColumns();
        listView.setAdapter(getNewAdapter());
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void setColumnWidth(int columnWidth) {
        listView.setRequestedColumnWidth(Utils.dpToPx(this, columnWidth));
        listView.determineColumns();
        listView.setAdapter(getNewAdapter());
    }

    public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view,
                            int position, long id) {
        Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }

    private void addCounter() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogBody = inflater.inflate(R.layout.dialog_counter_body, null);
        final View dialogTitle = inflater.inflate(R.layout.dialog_counter_title, null);
        dialogBuilder.setView(dialogBody);
        dialogBuilder.setCustomTitle(dialogTitle);

        final EditText name = (EditText) dialogBody.findViewById(R.id.addTagEdit);

        final Spinner unityType = (Spinner) dialogBody.findViewById(R.id.addUnityTypeSpinner);
        unityType.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.measure_unity)));

        final Spinner unityTypeMult = (Spinner) dialogBody.findViewById(R.id.addUnityMultSpinner);
        unityTypeMult.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unity_multiplier)));

        ArrayList<String> colorArray = new ArrayList<String>();
        Collections.addAll(colorArray, getResources().getStringArray(R.array.tag_color_array));
        Button tagColor = (Button)  dialogBody.findViewById(R.id.addCounterColorButton);
        tagColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ColorPicker colorPicker = new ColorPicker(MainActivity.this);
                colorPicker.setColors(colorArray);
                colorPicker.show();
                colorPicker.setOnChooseColorListener(new ColorPicker.OnChooseColorListener() {
                    @Override
                    public void onChooseColor(int position,int color) {
                        tagColor.setBackgroundColor(color);
                    }

                    @Override
                    public void onCancel(){
                        // put code
                    }
                });
            }
        });

        final Spinner clickAction = (Spinner) dialogBody.findViewById(R.id.addClickActSpinner);
        clickAction.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.counter_click_act)));

        //final EditText email = (EditText) dialogBody.findViewById(R.id.customEmail);
        //final EditText message = (EditText) dialogBody.findViewById(R.id.customFeedback);

        //dialogBuilder.setTitle("Send FeedBack");
        //dialogBuilder.setMessage("please send me to your feedback.");
        dialogBuilder.setPositiveButton(getResources().getText(R.string.tag_add_save), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nameStr = name.getText().toString().trim();
                //String emailStr = email.getText().toString();
                //String messageStr = message.getText().toString().trim();
                CounterDAO dao = new CounterDAO(getApplicationContext());
                Counter counter = new Counter();


            }
        });
        dialogBuilder.setNegativeButton(getResources().getText(R.string.tag_add_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}