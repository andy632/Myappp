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
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;


public class SecondActivity extends ActionBarActivity {

    EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        edit = (EditText)findViewById(R.id.editText);

    }

    public void loadInternalCache(View view){
//        Toast.makeText(getBaseContext() , "In Internal", Toast.LENGTH_SHORT).show();
        File folder = getCacheDir();
        File myfile =  new File(folder , "mydata1.txt");
        String data = readData(myfile);
        if(data!=null){
            edit.setText(data);
        }else{
            edit.setText("No data was returned");
        }


    }

    public void loadExternalCache(View view){
        //Toast.makeText(getBaseContext() , "In External", Toast.LENGTH_SHORT).show();
        File folder = getExternalCacheDir();
        File myfile =  new File(folder , "mydata2.txt");
        String data = readData(myfile);
        last(data);
    }

    public void loadPrivateExternalFile(View view){
//        Toast.makeText(getBaseContext() , "In pvt" ,Toast.LENGTH_SHORT).show();
        File folder = getExternalFilesDir("Anish");
        File myfile =  new File(folder , "mydata3.txt");
        String data = readData(myfile);
        last(data);
    }

    public void loadPublicExternalFile(View view){
//        Toast.makeText(getBaseContext() , "In public" ,Toast.LENGTH_SHORT).show();
//        String data = edit.getText().toString();
        File folder = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
        File myfile =  new File(folder , "mydata4.txt");
//        readData(myfile);
        String data = readData(myfile);
        last(data);
    }

    public String readData(File myfile ){
        FileInputStream fis = null;

        try {
            fis = new FileInputStream(myfile);
            int read = -1;
            StringBuffer str = new StringBuffer();
            while((read = fis.read()) != -1){
                str.append((char)read);
            }
            return str.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally{
            if(fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public void last(String data){
        if(data!=null){
            edit.setText(data);
        }else{
            edit.setText("No data was returned");
        }
    }

    private void message(String message) {
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
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
