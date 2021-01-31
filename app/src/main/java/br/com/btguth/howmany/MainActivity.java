package br.com.btguth.howmany;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.view.View;

import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import br.com.btguth.howmany.adapter.DefaultListAdapter;
import br.com.btguth.howmany.utils.DemoUtils;


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
                feedback();
                listView.onSaveInstanceState();
            }
        });


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

    private void feedback() {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogBody = inflater.inflate(R.layout.dialog_counter_body, null);
        final View dialogTitle = inflater.inflate(R.layout.dialog_counter_title, null);
        dialogBuilder.setView(dialogBody);
        dialogBuilder.setCustomTitle(dialogTitle);

        final EditText name = (EditText) dialogBody.findViewById(R.id.addTagEdit);
        final Spinner unityType = (Spinner) dialogBody.findViewById(R.id.addUnityTypeSpinner);
        String array_unityType[] = new String[5];
        array_unityType[0]="Litros";
        array_unityType[1]="Kilos";
        array_unityType[2]="Unidades";
        array_unityType[3]="Metros";
        array_unityType[4]="Metros";

        ArrayAdapter adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_unityType);
        unityType.setAdapter(adapter);

        final Spinner unityTypeMult = (Spinner) dialogBody.findViewById(R.id.addUnityMultSpinner);
        Integer array_unityTypeMult[] = new Integer[5];
        array_unityTypeMult[0]=1;
        array_unityTypeMult[1]=2;
        array_unityTypeMult[2]=3;
        array_unityTypeMult[3]=4;
        array_unityTypeMult[4]=5;

        adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, array_unityTypeMult);
        unityTypeMult.setAdapter(adapter);

        //final EditText email = (EditText) dialogBody.findViewById(R.id.customEmail);
        //final EditText message = (EditText) dialogBody.findViewById(R.id.customFeedback);

        //dialogBuilder.setTitle("Send FeedBack");
        //dialogBuilder.setMessage("please send me to your feedback.");
        dialogBuilder.setPositiveButton("Send", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String nameStr = name.getText().toString().trim();
                //String emailStr = email.getText().toString();
                //String messageStr = message.getText().toString().trim();

            }
        });
        dialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                //pass
            }
        });

        AlertDialog b = dialogBuilder.create();
        b.show();
    }

}