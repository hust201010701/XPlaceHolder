package com.orzangleli.xplaceholder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView case1, case2, case3, case4, case5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplace_holder_demo);

        initView();

    }

    private void initView() {
        case1 = this.findViewById(R.id.case1);
        case2 = this.findViewById(R.id.case2);
        case3 = this.findViewById(R.id.case3);
        case4 = this.findViewById(R.id.case4);
        case5 = this.findViewById(R.id.case5);

        case1.setOnClickListener(this);
        case2.setOnClickListener(this);
        case3.setOnClickListener(this);
        case4.setOnClickListener(this);
        case5.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.case1:
                intent = new Intent(this, XPlaceHolderActivityTest.class);
                break;
            case R.id.case2:
                intent = new Intent(this, XPlaceHolderFragmentTest.class);
                break;
            case R.id.case3:
                intent = new Intent(this, XPlaceHolderFragmentViewPagerTest.class);
                break;
            case R.id.case4:
                intent = new Intent(this, XPlaceHolderViewTest.class);
                break;
            case R.id.case5:
                intent = new Intent(this, XPlaceHolderXmlActivity.class);
                break;
        }
        this.startActivity(intent);
    }
}
