package com.example.binarytreefinishexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowBalanceTree extends AppCompatActivity {
private TextView tv_show_tree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_balance_tree);
        tv_show_tree=findViewById(R.id.tv_show_tree);
        Intent intent= getIntent();
      String fullTree=intent.getStringExtra("TREE_INFO");
      tv_show_tree.setText("");
      tv_show_tree.setText(fullTree);


    }
}
