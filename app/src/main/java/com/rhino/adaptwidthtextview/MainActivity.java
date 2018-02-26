package com.rhino.adaptwidthtextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.rhino.adaptwidthtextview.view.AdaptWidthTextView;

public class MainActivity extends AppCompatActivity {

    private AdaptWidthTextView mAdaptWidthTextView;
    private EditText mEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAdaptWidthTextView = findViewById(R.id.text);
        mEditText = findViewById(R.id.edit);

        findViewById(R.id.ok).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAdaptWidthTextView.setText(mEditText.getText().toString());
            }
        });
    }
}
