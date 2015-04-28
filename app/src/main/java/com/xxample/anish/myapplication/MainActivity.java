package com.xxample.anish.myapplication;

import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class MainActivity extends ActionBarActivity {

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

     edit = (EditText)findViewById(R.id.editText);

    }

    public void saveInternalCache(View view){
//        Toast.makeText(getBaseContext() , "In Internal", Toast.LENGTH_SHORT).show();
          String data = edit.getText().toString();
          File folder = getCacheDir();
          File myfile =  new File(folder , "mydata1.txt");
          writeData(myfile , data);
    }

    public void saveExternalCache(View view){
        //Toast.makeText(getBaseContext() , "In External", Toast.LENGTH_SHORT).show();
        String data = edit.getText().toString();
        File folder = getExternalCacheDir();
        File myfile =  new File(folder , "mydata2.txt");
        writeData(myfile , data);
    }

    public void savePrivateExternalFile(View view){
//        Toast.makeText(getBaseContext() , "In pvt" ,Toast.LENGTH_SHORT).show();
        String data = edit.getText().toString();
        File folder = getExternalFilesDir("Anish");
        File myfile =  new File(folder , "mydata3.txt");
        writeData(myfile , data);
    }

    public void savePublicExternalFile(View view){
//        Toast.makeText(getBaseContext() , "In public" ,Toast.LENGTH_SHORT).show();
        String data = edit.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile =  new File(folder , "mydata4.txt");
        writeData(myfile , data);
    }

    public void writeData(File myfile , String data){
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myfile);
            fos.write(data.getBytes());
            message(data+" was written succesfully to "+myfile.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void message(String message) {
        Toast.makeText(getBaseContext() , message ,Toast.LENGTH_SHORT).show();
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
}
