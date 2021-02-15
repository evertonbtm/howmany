package br.com.btguth.howmany;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
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

import android.util.Log;
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

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Logger;

import br.com.btguth.howmany.adapter.DefaultListAdapter;
import br.com.btguth.howmany.dao.CounterDAO;
import br.com.btguth.howmany.model.BaseItem;
import br.com.btguth.howmany.model.Counter;
import br.com.btguth.howmany.utils.ArrayGenUtils;
import br.com.btguth.howmany.utils.ScreenUtils;
import petrov.kristiyan.colorpicker.ColorPicker;


public class MainActivity extends AppCompatActivity {
    private AsymmetricGridView listView;
    private DefaultListAdapter adapter;
    private final ArrayGenUtils demoUtils = new ArrayGenUtils();

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initialize();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addCounter(null);
                listView.onSaveInstanceState();
            }
        });


    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void initialize(){
        listView = (AsymmetricGridView) findViewById(R.id.listView);
        CounterDAO dao = new CounterDAO(this);
        adapter = new DefaultListAdapter(this, demoUtils.createList(dao.getAllCounters()));
        ScreenUtils scr = new ScreenUtils(this);

        Log.d("howmany","Screen Density "+scr.getDensityName());
        listView.setRequestedColumnCount(scr.getBetterColumnCount());
        //listView.setRequestedHorizontalSpacing(Utils.dpToPx(this, 3));
        //listView.setRequestedColumnWidth(Utils.dpToPx(this, 85));
        //listView.setDebugging(true);
        listView.setAllowReordering(true);
        listView.setAdapter(getNewAdapter());
        listView.setOnItemClickListener(this::onItemClick);
        listView.setOnItemLongClickListener(this::onItemLongClick);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onResume() {
        super.onResume();
        initialize();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        //if (id == R.id.action_settings) {
        //    return true;
        //}

        if (id == R.id.action_list) {
            Intent intent = new Intent(this, ListActivity.class);
            this.startActivity(intent);
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void onItemClick(@NonNull AdapterView<?> parent, @NonNull View view,
                            int position, long id) {

        Counter counter = ((BaseItem) adapter.getItem(position)).getCounter();

        String[] arrayClick = getResources().getStringArray(R.array.counter_click_act);
        for(int i=0;i <arrayClick.length;i++){
            if(arrayClick[i].equals(counter.getClickAction())){
                if(i==1){
                    addCounter(counter);
                }else{
                    increment(counter);
                }
            }
        }
        //Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
    }

    public Boolean onItemLongClick(@NonNull AdapterView<?> parent, @NonNull View view,
                            int position, long id) {
        //Toast.makeText(this, "Item " + position + " clicked", Toast.LENGTH_SHORT).show();
        addCounter(((BaseItem) adapter.getItem(position)).getCounter());
        return false;
    }

    private void addCounter(Counter counter) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogBody = inflater.inflate(R.layout.dialog_counter_body, null);
        final View dialogTitle = inflater.inflate(R.layout.dialog_counter_title, null);
        dialogBuilder.setView(dialogBody);
        dialogBuilder.setCustomTitle(dialogTitle);
        dialogBuilder.setCancelable(false);

        final Button delete = (Button) dialogTitle.findViewById(R.id.counterDeleteButton);

        final EditText name = (EditText) dialogBody.findViewById(R.id.addTagEdit);
        if(counter != null && counter.getCounterName() != null) {
            name.setText(counter.getCounterName());
            delete.setVisibility(View.VISIBLE);
        }

        final Spinner unityType = (Spinner) dialogBody.findViewById(R.id.addUnityTypeSpinner);
        unityType.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.measure_unity)));
        if(counter != null && counter.getMeasureUnityName() != null){
            String[] arrayUnity = getResources().getStringArray(R.array.measure_unity);
            for(int i=0;i <arrayUnity.length;i++){
                if(arrayUnity[i].equals(counter.getMeasureUnityName())){
                    unityType.setSelection(i);
                }
            }
        }

        final Spinner unityTypeMult = (Spinner) dialogBody.findViewById(R.id.addUnityMultSpinner);
        unityTypeMult.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.unity_multiplier)));

        if(counter != null && counter.getMultiplier() != null){
            String[] arrayMult = getResources().getStringArray(R.array.unity_multiplier);
            for(int i=0;i <arrayMult.length;i++){
                if(arrayMult[i].equals(counter.getMultiplier().toString())){
                    unityTypeMult.setSelection(i);
                }
            }
        }
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

        if(counter != null && counter.getCounterColor() != null){
            tagColor.setBackgroundColor(counter.getCounterColor());
        }

        final Spinner clickAction = (Spinner) dialogBody.findViewById(R.id.addClickActSpinner);
        clickAction.setAdapter(new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.counter_click_act)));

        if(counter != null && counter.getClickAction() != null){
            String[] arrayClick = getResources().getStringArray(R.array.counter_click_act);
            for(int i=0;i <arrayClick.length;i++){
                if(arrayClick[i].equals(counter.getClickAction())){
                    clickAction.setSelection(i);
                }
            }
        }

        Button addMore = (Button)  dialogBody.findViewById(R.id.addCounterMore);
        Button addMinus = (Button)  dialogBody.findViewById(R.id.addCounterMinus);
        EditText counterValue = (EditText)  dialogBody.findViewById(R.id.addCounterValueEdit);

        if(counter != null && counter.getCounterValue() != null){
            counterValue.setText(counter.getCounterValue().toString());
        }

        addMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer multiplier = Integer.valueOf(unityTypeMult.getSelectedItem().toString());
                BigDecimal value  = new BigDecimal(counterValue.getText().toString());

                counterValue.setText(String.valueOf(value.add(new BigDecimal(1).multiply(new BigDecimal(multiplier)))));
            }
        });

        addMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer multiplier = Integer.valueOf(unityTypeMult.getSelectedItem().toString());
                BigDecimal value  = new BigDecimal(counterValue.getText().toString());
                BigDecimal result = value.subtract(new BigDecimal(1).multiply(new BigDecimal(multiplier)));
                counterValue.setText(result.compareTo(new BigDecimal(0)) <= 0 ? "0" : result.toString());
            }
        });

        dialogBuilder.setPositiveButton(getResources().getText(R.string.tag_add_save), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) { }
        });

        dialogBuilder.setNegativeButton(getResources().getText(R.string.tag_add_cancel), new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {

            }
        });

        AlertDialog alertDialog = dialogBuilder.create();
        alertDialog.setCancelable(false);
        alertDialog.show();

        alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v)
            {
                Boolean wantToCloseDialog = false;

                if (name.getText().toString().trim().length() == 0) {
                    name.setError(getResources().getText(R.string.tag_description_validator));
                } else {
                    String counterName = name.getText().toString();
                    String measureUnityName = unityType.getSelectedItem().toString();
                    String[] unityAliasArray = getResources().getStringArray(R.array.measure_unity_alias);
                    String measureUnityAlias = unityAliasArray[unityType.getSelectedItemPosition()];
                    Integer multiplier = Integer.valueOf(unityTypeMult.getSelectedItem().toString());

                    Drawable bg =  tagColor.getBackground();
                    Integer counterColor = -1;
                    if (bg instanceof ColorDrawable){
                        counterColor = ((ColorDrawable) bg).getColor();
                    }
                    String click = clickAction.getSelectedItem().toString();
                    Integer value = Integer.valueOf(counterValue.getText().toString());


                    CounterDAO dao = new CounterDAO(getApplicationContext());
                    Counter counterNew = new Counter();
                    counterNew.setCounterName(counterName);
                    counterNew.setMeasureUnityName(measureUnityName);
                    counterNew.setMeasureUnityAlias(measureUnityAlias);
                    counterNew.setMultiplier(multiplier);
                    counterNew.setCounterColor(counterColor);
                    counterNew.setClickAction(click);
                    counterNew.setCounterValue(value);
                    if(counter != null){
                        counterNew.setIdCounter(counter.getIdCounter());
                        dao.updateCounter(counterNew);
                    }else{
                        dao.addCounter(counterNew);
                    }
                    wantToCloseDialog = true;
                }

                if(wantToCloseDialog)
                    initialize();
                    alertDialog.dismiss();
                //else dialog stays open. Make sure you have an obvious way to close the dialog especially if you set cancellable to false.
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
                delete(counter);
                alertDialog.dismiss();
                initialize();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void increment(Counter counter){
        BigDecimal multiplier = new BigDecimal(1);
        String[] arrayMult = getResources().getStringArray(R.array.unity_multiplier);
        for(int i=0;i <arrayMult.length;i++){
            if(arrayMult[i].equals(counter.getMultiplier().toString())){
                multiplier = new BigDecimal(arrayMult[i]);
            }
        }
        CounterDAO dao = new CounterDAO(getApplicationContext());
        BigDecimal result = (new BigDecimal(counter.getCounterValue()).add(new BigDecimal(1).multiply(multiplier)));
        counter.setCounterValue(result.intValue());
        dao.updateCounter(counter);

        listView.setAdapter(getNewAdapter());

    }

    public void delete(Counter counter){
        CounterDAO dao = new CounterDAO(getApplicationContext());
        dao.deleteCounter(counter);
    }
}