package com.orzangleli.xplaceholder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.orzangleli.placeholder.IPlaceHolderCallback;
import com.orzangleli.placeholder.State;
import com.orzangleli.placeholder.XPlaceHolderUtil;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout;
import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderVo;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView case1, case2, case3, case4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zzplace_holder_demo);

        initView();

    }

    private void initView() {
        case1 = this.findViewById(R.id.case1);
        case2 = this.findViewById(R.id.case2);
        case3 = this.findViewById(R.id.case3);
        case4 = this.findViewById(R.id.case4);

        case1.setOnClickListener(this);
        case2.setOnClickListener(this);
        case3.setOnClickListener(this);
        case4.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.case1:
                intent = new Intent(this, ZZPlaceHolderActivityTest.class);
                break;
            case R.id.case2:
                intent = new Intent(this, ZZPlaceHolderFragmentTest.class);
                break;
            case R.id.case3:
                intent = new Intent(this, ZZPlaceHolderFragmentViewPagerTest.class);
                break;
            case R.id.case4:
                intent = new Intent(this, ZZPlaceHolderViewTest.class);
                break;
        }
        this.startActivity(intent);
    }
}
