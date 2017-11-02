package com.fambam.algorithmic.algorithmic;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Guthrie on 11/1/2017.
 */

public class MainActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void toGraphics(View view) {
        int[] drawables = new int[] {
                R.drawable.example,
                R.drawable.example1,
                R.drawable.example,
                R.drawable.example1};
        String key = getString(R.string.bubble_sort);
        Intent intent = new Intent(this, AlgorithmActivity.class);
        intent.putExtra(key, drawables);
        startActivity(intent);
    }
}
