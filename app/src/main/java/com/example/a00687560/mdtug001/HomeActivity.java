package com.example.a00687560.mdtug001;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.a00687560.adapter.OfferAdapter;
import com.example.a00687560.model.LibsOffer;

import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private GridLayout gridLayout;
    private LinearLayout linear_tag;
    private ImageView up;
    private Button menu_6;
    private boolean isButton=true;
    private EditText search_edit_text;
    private RadioGroup rgMain;
    private ListView offerList;
    private List<LibsOffer> LibsOfferList;
    private OfferAdapter Adapter;
    private RadioButton rbRecommend, rbBook, rbMagazine;
    private ListView mlistcontentview;
    private LinearLayout mhinderview;
    private float mFirstY,mCurrentY,mTouchSlop=0;
    private List<List<String>> mList = new ArrayList<List<String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //创建数据库
        SQLiteDatabase db= Connector.getDatabase();
        saveOfferBook();

        /**
         * 以下为滑动侧菜单效果
         */
        Toolbar toolbar = (Toolbar) findViewById(R.id.home_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        navigationView.setItemIconTintList(null);//去除默认navigationItem的图标

        /**
         * 以下为默认隐藏顶部网格图标，点击更多后 显示，并隐藏文字标签
         */
        gridLayout =(GridLayout) findViewById(R.id.gridlayout);
        menu_6 =(Button)findViewById(R.id.menu_6);
        up =(ImageView)findViewById(R.id.up);
        linear_tag =(LinearLayout)findViewById(R.id.linear_tag);
        menu_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
                    if (isButton) {
                        linear_tag.setVisibility(View.GONE);
                        isButton = false;
                }
                    isButton=false;
                } else {
                        gridLayout.setVisibility(View.GONE);
                        isButton = true;
                    }
            }
        });
        up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isButton) {
                    gridLayout.setVisibility(View.VISIBLE);
                    isButton=false;
                } else {
                    gridLayout.setVisibility(View.GONE);
                    isButton = true;
                    if (isButton) {
                        linear_tag.setVisibility(View.VISIBLE);
                    }
                }
            }
        });

        search_edit_text=(EditText)findViewById(R.id.search_edit_text);
        search_edit_text.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                startActivity(new Intent(HomeActivity.this,SearchActivity.class));
            }
        } );


        rgMain = (RadioGroup) findViewById(R.id.rgMain);
        rbRecommend = (RadioButton) findViewById(R.id.rbRecommend);
        rbBook = (RadioButton) findViewById(R.id.rbBook);
        rbMagazine = (RadioButton) findViewById(R.id.rbMagazine);
        rgMain.check(R.id.rbRecommend);//默认选中 为我推荐

        offerList=(ListView)findViewById(R.id.offer_me_list);
        getAllOffer();//获取全部推荐图书

/**
 * 为list添加手势监听

    mlistcontentview.setOnTouchListener(new View.OnTouchListener(){
        public boolean onTouch(View v, MotionEvent event){
            switch(event.getAction()){
                case MotionEvent.ACTION_DOWN:
                     mFirstY = event.getY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    mCurrentY=event.getY();
                    if (mCurrentY-mFirstY>mTouchSlop){
                        //下滑  显示导航和搜索
                        mhinderview.setVisibility(View.VISIBLE);
                    }else if (mFirstY-mCurrentY>mTouchSlop){
                        //上滑 隐藏导航和搜索
                        mhinderview.setVisibility(View.GONE);
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    break;
            }
            return false;
        }
    });
 */
    }

    /**
     * 事先存储一些推荐信息进入数据库
     */

    private void saveOfferBook(){
        for (int i=0;i<8;i++){
            LibsOffer libsOffer=new LibsOffer(i,1,1,"11122432","Effective Java","Joshua Bloch");
            libsOffer.save();
        }
    }



    /**
    获取全部推荐图书 方法
     */
    private void getAllOffer(){
        List<LibsOffer> all= DataSupport.findAll(LibsOffer.class);
        if (all!=null && all.size()>0){
            LibsOfferList=all;
            displayDataFromDB();
            Adapter.notifyDataSetChanged();
        }else{
            Toast.makeText(this,"没找到推荐内容",Toast.LENGTH_SHORT).show();
        }
    }

    private void displayDataFromDB(){
        mList.clear();
        List<String> columnList= new ArrayList<String>();
        columnList.add("book_name");
        columnList.add("book_author");
        columnList.add("book_id");
        mList.add(columnList);
        if (LibsOfferList!=null&& LibsOfferList.size()>0){
            for (int i=0;i<LibsOfferList.size();i++){
                LibsOffer offer=LibsOfferList.get(i);
                List<String> stringList=new ArrayList<String>();
                stringList.add(String.valueOf(offer.getId()));
                stringList.add(offer.getBook_name());
                stringList.add(offer.getBook_author());
                stringList.add(offer.getBook_id());
                mList.add(stringList);
            }
            Adapter = new OfferAdapter(HomeActivity.this, LibsOfferList);
            offerList.setAdapter(Adapter);
        }
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.my_book) {
            // Handle the camera action
            Intent intent = new Intent(this,MyBookActivity.class);
            startActivity(intent);
            //跳转到 我的订阅
        } else if (id == R.id.my_collection) {
            Intent intent = new Intent(this,MyCollectionActivity.class);
            startActivity(intent);
            //跳转到 我的收藏
        } else if (id == R.id.my_like) {
            Intent intent = new Intent(this,MyLikeActivity.class);
            startActivity(intent);
            //跳转到 我的爱好
        } else if (id == R.id.setting) {
            Intent intent = new Intent(this,SettingActivity.class);
            startActivity(intent);
            //跳转到 设置
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
