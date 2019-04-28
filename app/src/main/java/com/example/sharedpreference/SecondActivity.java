package com.example.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private TextView txvName, txvProfession;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        txvName = (TextView) findViewById(R.id.txvName);
        txvProfession = (TextView) findViewById(R.id.txvProfssion);
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

    public void clearAccountData(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences
                (getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.clear();
        editor.apply();
    }

    public void removeProfessionKey(View view) {
        SharedPreferences sharedPreferences = getSharedPreferences
                (getPackageName() + Constants.PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.remove(Constants.KEY_PROFESSION);
        editor.apply();

    }
}
