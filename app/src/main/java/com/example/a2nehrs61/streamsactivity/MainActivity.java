package com.example.a2nehrs61.streamsactivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Environment;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public boolean onCreateOptionsMenu(Menu menu)
    {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Read the file from the preferences...
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String filename = prefs.getString("filename", "textedit");
        if(item.getItemId() == R.id.save) {
            try {
                PrintWriter pw = new PrintWriter(new FileWriter(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+filename+".txt", true));
                EditText textBox = (EditText)findViewById(R.id.text);
                String text = textBox.getText().toString();
                pw.println(text);
                pw.close();
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setPositiveButton("OK", null).
                        setMessage("ERROR: " + e).show();
            }
        }else if(item.getItemId() == R.id.load){
            try
            {
                BufferedReader reader = new BufferedReader(new FileReader(Environment.getExternalStorageDirectory().getAbsolutePath() + "/"+filename+".txt"));
                String line = "";
                EditText textBox = (EditText)findViewById(R.id.text);
                textBox.setText("");
                while((line = reader.readLine()) != null)
                {
                    textBox.append(line + "\n");
                }
            }
            catch(IOException e)
            {
                new AlertDialog.Builder(this).setPositiveButton("OK", null).
                        setMessage("ERROR: " + e).show();
            }

        }else{
            Intent intent = new Intent(this,PrefActivity.class);
            startActivityForResult(intent,1);
            return true;
        }
        return false;
    }
}
