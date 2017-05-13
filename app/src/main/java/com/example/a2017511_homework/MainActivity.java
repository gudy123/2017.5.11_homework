package com.example.a2017511_homework;

import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<Item> itemList = new ArrayList<>();
    private ItemAdapter adapter = new ItemAdapter(itemList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        StaggeredGridLayoutManager layoutManager = new
                StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(layoutManager);//指定RecyclerView的布局格式
        recyclerView.setAdapter(adapter);
        //隐藏标题栏
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        String address = "https://qcloud.waydrow.com/LoveInn/index.php/Home/App/getInfoList";//指定数据获取地址
        HttpUtil.sendHttpRequest(address, new HttpCallBackListener() {
            @Override
            public//隐藏标题栏
            void onFinish(String response) {
                /**此处写根据返回内容的逻辑**/
                parseJSONWithJSONObject(response);
            }
            @Override
            public void onError(Exception e) {
                /**在这里对异常情况进行处理**/
                e.printStackTrace();
            }
        });

    }

    //定义解析JSON的方法
    private void parseJSONWithJSONObject(String jsonData) {
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                //得到jsonObject里的Json数据
                String id = jsonObject.getString("id");
                String name = jsonObject.getString("name");
                String summary = jsonObject.getString("summary");
                String photo = jsonObject.getString("photo");
                Item item = new Item(id, name, summary, "https://qcloud.waydrow.com" + photo);
                itemList.add(item);
                Log.d("a",id);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

