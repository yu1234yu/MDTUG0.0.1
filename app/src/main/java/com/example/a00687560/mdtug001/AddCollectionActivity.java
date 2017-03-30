package com.example.a00687560.mdtug001;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.model.LibsInfo;

public class AddCollectionActivity extends Activity implements View.OnClickListener{
    private EditText etLibName,etTypeId;
    private Button btnAdd;
    private Intent intent;
    private int str_type_id;
    private String str_lib_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);

        etLibName= (EditText) findViewById(R.id.lib_name);
        etTypeId= (EditText) findViewById(R.id.type_id);
        btnAdd= (Button) findViewById(R.id.btn_add_book);


        //获取输入框的值
        str_lib_name=etLibName.getText().toString();
        str_type_id=etTypeId.getId();
        btnAdd.setOnClickListener(this);
        //获取传递过来的intent
        intent=getIntent();
    }


    public void onClick(View view) {
        LibsCollection libsCollection = new LibsCollection();
        libsCollection.setLib_name( str_lib_name);
        libsCollection.setType_id(str_type_id);
        boolean ifSaveSuccess = libsCollection.save();
        if (ifSaveSuccess) {
            Toast.makeText(AddCollectionActivity.this, "存储成功！", Toast.LENGTH_SHORT).show();
        }else
        {
            Toast.makeText(AddCollectionActivity.this, "存储失败！", Toast.LENGTH_SHORT).show();
        }
        setResult(RESULT_OK, intent);
        finish();
    }
}


