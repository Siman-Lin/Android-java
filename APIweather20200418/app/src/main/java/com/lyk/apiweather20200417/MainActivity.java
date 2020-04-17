package com.lyk.apiweather20200417;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String control="false";
    private Button mbottom;
    List<dataTextImag> myDataset=new ArrayList<>();
    String[] lists={"","","",""};
    int stringSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);
        mbottom=findViewById(R.id.butter);
        // use a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

        String url2 = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-079A2DB7-D565-42E5-99E6-ED4845E11590&locationName=%E8%87%BA%E5%8C%97%E5%B8%82";
        getData(url2); //取得網址字串
        mbottom.setOnClickListener(OK_GO);

    }

    private View.OnClickListener OK_GO =new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            mbottom.setText(lists[1]);

            dataTextImag MinT=new dataTextImag(lists[0],R.drawable.apple_pic);
            myDataset.add(MinT);
            dataTextImag MinT2=new dataTextImag(lists[1],R.drawable.apple_pic);
            myDataset.add(MinT2);
            dataTextImag MinT3=new dataTextImag(lists[2],R.drawable.apple_pic);
            myDataset.add(MinT3);
            Log.d("text","enter");

        }
    };
    @Override
    protected void onStart() {
        super.onStart();
        Log.d("chart","onStart");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.d("chart","onPostResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("chart","onPause");
    }

    private String getData(String urlString) {
        String result = "";
        //使用JsonObjectRequest類別要求JSON資料。
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(urlString, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            //Velloy採非同步作業，Response.Listener  監聽回應
                            public void onResponse(JSONObject response) {
                                Log.d("回傳結果", "結果=" + response.toString());
                                try {
                                    parseJSON(response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Response.ErrorListener 監聽錯誤
                        Log.e("回傳結果", "錯誤訊息：" + error.toString());
                    }
                });
        Volley.newRequestQueue(this).add(jsonObjectRequest);
        return result;
    }

    private void parseJSON(JSONObject jsonObject) throws JSONException{
        Log.d("text","enter");
        String list = "";
        Log.d("text","000");
//        "---------MinT------------"
        JSONArray data = jsonObject.getJSONObject("records").getJSONArray("location")
                .getJSONObject(0).getJSONArray("weatherElement")
                .getJSONObject(2).getJSONArray("time");
        Log.d("text","111");
        stringSize=data.length();
        for  (int i = 0 ; i < data.length(); i++){
            JSONObject o = data.getJSONObject(i);
            Log.d("text","222");
            list=("\n"+String.valueOf(data.length()));
            list=list+(o.getString("startTime")+"\n");
            list=list+(o.getString("endTime")+"\n");
            Log.d("text","333");
            list=list+("              "+o.getJSONObject("parameter").getString("parameterName")+" °"
                    +o.getJSONObject("parameter").optString("parameterUnit")+"\n");

            Log.d("text",list);
            lists[i]=list;

            mbottom.setText("請按下按鈕");
        }


    }

}
