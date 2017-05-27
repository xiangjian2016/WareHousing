package com.zigcloud.warehousing.activity.channel;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.google.gson.Gson;
import com.zigcloud.warehousing.R;
import com.zigcloud.warehousing.activity.base.BaseActivity;
import com.zigcloud.warehousing.activity.vitamio.VitamioActivity;
import com.zigcloud.warehousing.adapter.Icon;
import com.zigcloud.warehousing.adapter.MyAdapter;
import com.zigcloud.warehousing.entity.tvlive.Channel;

import java.util.ArrayList;


/**
 * Created by xiang on 2017/5/23.
 */

public class tvlive extends BaseActivity {

    private Context mContext;
    private GridView grid_photo;
    private BaseAdapter mAdapter = null;
    private ArrayList<Icon> mData = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tvlive_channel);
        mContext = tvlive.this;
        grid_photo = (GridView) findViewById(R.id.grid_photo);

        mData = new ArrayList<Icon>();
        mData.add(new Icon(R.mipmap.iv_icon_1, "CCTV5" ,"http://ivi.bupt.edu.cn/hls/cctv5hd.m3u8"));
        mData.add(new Icon(R.mipmap.iv_icon_2, "CCTV5" ,"http://tll888.w56.youdns.net/migu.php?id=608788135"));
        mData.add(new Icon(R.mipmap.iv_icon_3, "五星体育" ,"http://tll888.w56.youdns.net/migu.php?id=617290049"));
        mData.add(new Icon(R.mipmap.iv_icon_4, "湖南卫视HD" ,"http://tll888.w56.youdns.net/migu.php?id=603996975"));
        mData.add(new Icon(R.mipmap.iv_icon_5, "CCTV5+" ,"http://112.89.38.24/PLTV/88888894/224/3221225503/1.m3u8"));
        mData.add(new Icon(R.mipmap.iv_icon_6, "凤凰电影" ,"http://121.251.49.204/hls/fhdy.m3u8"));
        mData.add(new Icon(R.mipmap.iv_icon_7, "天下卫视" ,"http://64.77.236.178/live/ch2.m3u8"));
        mData.add(new Icon(R.mipmap.iv_icon_7, "CHC动作电影" ,"http://tll888.w56.youdns.net/migu.php?id=621135508"));
        mData.add(new Icon(R.mipmap.iv_icon_7, "天津卫视" ,"http://tll888.w56.youdns.net/migu.php?id=608779370"));

        mAdapter = new MyAdapter<Icon>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder holder, Icon obj) {
                holder.setImageResource(R.id.img_icon, obj.getiId());
                holder.setText(R.id.txt_icon, obj.getiName());
            }
        };

        grid_photo.setAdapter(mAdapter);

        grid_photo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                GridView gridView = (GridView)parent;
                Icon icon = (Icon) gridView.getItemAtPosition(position);
                String currentUrl = icon.getCurrentUrl();
                Channel channel = new Channel();
                channel.setCurrentUrl(currentUrl);
                Intent intent= new Intent(tvlive.this,VitamioActivity.class);
                intent.putExtra("channel",new Gson().toJson(channel));
                startActivity(intent);
//                Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
