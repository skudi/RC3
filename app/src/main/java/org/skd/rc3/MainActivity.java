package org.skd.rc3;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import org.skd.rc3.blgatt.DeviceScanActivity;

public class MainActivity extends AppCompatActivity {

    private String TAG = "RC3.MainActivity";
    private final int BLUETOOTHLE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fabNewDevice);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                uiConnectionPicker();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void uiConnectionPicker(){
        String[] connectionTypes = {"BlueTooth Gatt", "WiFi"};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Connection type");
        builder.setItems(connectionTypes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Log.d(TAG, String.format("onClick: which:[%d]", which) );
                Toast.makeText(MainActivity.this,"You picked: " + which,Toast.LENGTH_LONG).show();
                switch (which) {
                    case BLUETOOTHLE: {
                            final Intent blScannerUi = new Intent();
                            blScannerUi.setClassName(DeviceScanActivity.class.getPackage().getName()
                                    , DeviceScanActivity.class.getName() );
                            startActivity(blScannerUi);
                    }
                }
                dialog.dismiss();
            }
        });
        builder.show();
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
}
