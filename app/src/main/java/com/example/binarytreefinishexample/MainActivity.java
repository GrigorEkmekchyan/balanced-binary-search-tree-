package com.example.binarytreefinishexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TREE_INFO ="TREE_INFO";
    private BinaryTree stringBinaryTree;
    private TextView tvFindSingleChild;
    private Button btnFindNode;
    private Button btnSearchAll;
    private Button btnDelete;
    private Button btnInsert;
    private EditText etFind;
    private EditText etDelete;
    private EditText etInsert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stringBinaryTree = new BinaryTree();
        etInsert = findViewById(R.id.et_insert);
        etFind = findViewById(R.id.et_find_number);
        etDelete = findViewById(R.id.et_delete_number);
        tvFindSingleChild = findViewById(R.id.tv_find_info);
        btnFindNode = findViewById(R.id.btn_find);
        btnDelete = findViewById(R.id.btn_delete);
        btnSearchAll = findViewById(R.id.btn_show_child);
        btnInsert = findViewById(R.id.btn_insert);
        btnFindNode.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnInsert.setOnClickListener(this);
        btnSearchAll.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_delete:
                if (!checkValidate(etDelete)){
                    stringBinaryTree.delete(getTextValue(etDelete));
                }
                break;

            case R.id.btn_find:
           String have="Tree have this element ";
           String dontHave="Tree don't have this element ";
                if (!checkValidate(etFind)) {
                    Node node = stringBinaryTree.find(getTextValue(etFind));
                    if (node != null) {
                        tvFindSingleChild.setText(have);
                    }
                  else {
                        tvFindSingleChild.setText(dontHave);
                    }
                }
                break;

            case R.id.btn_show_child:
                String fullTree= stringBinaryTree.display();
                Intent intent= new Intent(this,ShowBalanceTree.class);
                intent.putExtra(TREE_INFO,fullTree);
                startActivity(intent);

                break;

            case R.id.btn_insert:
                if (!checkValidate(etInsert)) {
                    stringBinaryTree.insert(getTextValue(etInsert));
                }
                break;
        }
    }
    private boolean checkValidate(EditText editText) {
        //+other check
        return editText.getText().toString().equals("");

    }

    private int getTextValue(EditText editText) {
        return Integer.valueOf(editText.getText().toString());

    }
}
