package com.example.sharedpreference;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etName, etProfession;
    private TextView txvName, txvProfession;
    private Switch pageColorSwitch;
    private LinearLayout pageLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = (EditText) findViewById(R.id.etName);
        etProfession = (EditText) findViewById(R.id.etProfession);

        txvName = (TextView) findViewById(R.id.txvName);
        txvProfession = (TextView) findViewById(R.id.txvProfssion);

        pageColorSwitch = (Switch) findViewById(R.id.pageColorSwitch);

        pageLayout = (LinearLayout) findViewById(R.id.pageLayout);

        pageColorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                setPageColor(isChecked);

            }
        });

        // Retrieve data from Activity Level SP..
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
        boolean isChecked = sp.getBoolean("green",false);
        pageColorSwitch.setChecked(isChecked);

    }

    private void setPageColor(boolean isChecked) {
        SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("green", isChecked);
        editor.apply();

        pageLayout.setBackgroundColor(isChecked ? Color.GREEN : Color.WHITE);
    }

    /**
     * @editor.apply(); This will save data Asyncronously.
     * @editor.commit(); This will save data and return boolean whether data is saved or not on main thread.
     * @getPreference() is for Activity Level storage of data ..
     * @getSharedPreferences() is for storing data at application level
     */

    public void saveAccountData(View view) {
        // For Activity level
        //    SharedPreferences sharedPreferences = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences sharedPreferences = getSharedPreferences
                (getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);


        //For storing data in shared preference
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Constants.KEY_NAME, etName.getText().toString());
        editor.putString(Constants.KEY_PROFESSION, etProfession.getText().toString());
        editor.putInt(Constants.KEY_PROF_ID, 287);

        editor.apply();
    }

    public void loadAccountData(View view) {
        //This method retrieve data from shared preference
        SharedPreferences sharedPreferences = getSharedPreferences
                (getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(Constants.KEY_NAME, "N/A");
        String profession = sharedPreferences.getString(Constants.KEY_PROFESSION, "N/A");
        int profId = sharedPreferences.getInt(Constants.KEY_PROF_ID, 0);

        txvName.setText(name);
        String profStr = profession + " -" + profId;
        txvProfession.setText(profStr);

    }

    public void openSecondActivity(View view) {
        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(intent);
    }
}
