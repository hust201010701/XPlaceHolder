package com.orzangleli.xplaceholder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class ZZPlaceHolderFragmentTest extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getSupportFragmentManager().beginTransaction().replace(android.R.id.content, new BlankFragment()).commitAllowingStateLoss();
    }
}
