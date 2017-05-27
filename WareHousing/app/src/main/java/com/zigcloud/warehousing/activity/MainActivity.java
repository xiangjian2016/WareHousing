package com.zigcloud.warehousing.activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.activity.channel.tvlive;
import com.zigcloud.warehousing.activity.vitamio.VitamioActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 主界面
 * */
public class MainActivity extends BaseActivity{

    private TextView titlebar_left;
    private TextView titlebar_title;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        MainApplication.getInstance().addActivity(this);

        titlebar_left = (TextView) findViewById(R.id.titlebar_left);
        titlebar_left.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        titlebar_title = (TextView) findViewById(R.id.titlebar_title);
        titlebar_title.setText(R.string.app_name);

        GridView gridView = (GridView) findViewById(R.id.gdv_main);
        ArrayList<HashMap<String, Object>> lstImageItem = new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_environment);
        map.put("ItemText", "环境监控");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_alarm);
        map.put("ItemText", "安防报警");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_import);
        map.put("ItemText", "物品入库");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_outport);
        map.put("ItemText", "物品出库");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_search);
        map.put("ItemText", "物品查询");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_setting);
        map.put("ItemText", "设置");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_add);
        map.put("ItemText", "预留");
        lstImageItem.add(map);
        map = new HashMap<String, Object>();
        map.put("ItemImage", R.drawable.icon_add);
        map.put("ItemText", "预留");
        lstImageItem.add(map);
        SimpleAdapter saImageItems = new SimpleAdapter(this,
                lstImageItem,
                R.layout.view_squared_item,
                new String[] {"ItemImage","ItemText"},
                new int[] {R.id.itemImage,R.id.itemText});
        gridView.setAdapter(saImageItems);
        gridView.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {
                // TODO Auto-generated method stub
                switch(arg2){
                    case 0:
                        startActivity(new Intent(MainActivity.this,EnvironmentMonitorActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MainActivity.this,VitamioActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MainActivity.this,GoodsImportActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MainActivity.this,GoodsOutportActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MainActivity.this,GoodsSearchActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MainActivity.this,tvlive.class));
                        break;

                }
            }

        });
        gridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // TODO Auto-generated method stub
        if(keyCode == KeyEvent.KEYCODE_BACK)
        {
            exitBy2Click();
        }
        return false;
    }
    private static Boolean isExit = false;

    private void exitBy2Click() {
        Timer tExit = null;
        if (isExit == false) {
            isExit = true;
            Toast.makeText(this,getResources().getString(R.string.exit_dialog_message), Toast.LENGTH_SHORT).show();
            tExit = new Timer();
            tExit.schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            }, 2000);

        } else {
            MainApplication.getInstance().exit();
        }
    }
}