package com.orzangleli.xplaceholder;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout;

public class XPlaceHolderXmlActivity extends AppCompatActivity {

    private ImageAndTextPlaceHolderLayout mImageAndTextPlaceHolderLayout;
    private Button mButton;
    private int num =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xplace_holder_xml);

        mImageAndTextPlaceHolderLayout = this.findViewById(R.id.placeHolderLayout);
        mButton = this.findViewById(R.id.button);

        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                num ++ ;
                if (num % 3 == 0) {
                    mImageAndTextPlaceHolderLayout.showLoading();
                } else if (num % 3 == 1) {
                    mImageAndTextPlaceHolderLayout.showEmpty();
                } else if (num % 3 == 2) {
                    mImageAndTextPlaceHolderLayout.showError();
                }
            }
        });
    }
}
