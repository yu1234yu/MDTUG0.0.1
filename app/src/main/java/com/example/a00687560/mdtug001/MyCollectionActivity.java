package com.example.a00687560.mdtug001;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.a00687560.model.LibsCollection;
import com.example.a00687560.model.LibsInfo;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MyCollectionActivity extends AppCompatActivity implements View.OnClickListener{

    private Button btnAddCollection;
    private ListView myCollectionListView;
    private MyCollectionAdapter myCollectionAdapter;
    private List<LibsCollection> libsCollectionList;
    private SQLiteDatabase db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_collection);


        Toolbar toolbar = (Toolbar) findViewById(R.id.for_password_toolbar);
        setSupportActionBar(toolbar);

        //后台设置ToolBar的图标，传递给对象toolbar
        toolbar.setNavigationIcon(R.drawable.btn_back);
        //让原始toolBar的title不显示。
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onBackPressed();
            }
        });


        btnAddCollection=(Button)findViewById(R.id.btn_add_collection);
        btnAddCollection.setOnClickListener(this);
        SQLiteDatabase db= Connector.getDatabase();

        //获取全部图书信息
        List<LibsCollection> libsCollections= DataSupport.select("lib_name").find(LibsCollection.class);
        //Toast.makeText(MyBookActivity.this, libsInFoList.get(0).getId()+"",0).show();

        //以下，创建并设置Adapter
        myCollectionAdapter=new MyCollectionAdapter(this,libsCollectionList);
        myCollectionListView.setAdapter(myCollectionAdapter);


    }


 //点击 添加 按钮监听
    public void onClick(View v) {
        //普通存储
        Intent i = new Intent(MyCollectionActivity.this, AddCollectionActivity.class);
        startActivityForResult(i, 1);
    }



}

