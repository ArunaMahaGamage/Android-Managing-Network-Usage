package com.example.managingnetworkusage;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv_enable;
    TextView tv_wifi_connected;
    TextView tv_mobile_connected;

    Button btn_is_online;
    Button btn_connected;

    Boolean isOnline;

    private static final String DEBUG_TAG = "NetworkStatusExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv_enable = (TextView) findViewById(R.id.tv_enable);
        tv_wifi_connected = (TextView) findViewById(R.id.tv_wifi_connected);
        tv_mobile_connected= (TextView) findViewById(R.id.tv_mobile_connected);

        btn_is_online = (Button) findViewById(R.id.btn_is_online);
        btn_connected = (Button) findViewById(R.id.btn_connected);

        btn_is_online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isOnline = isOnline();
                tv_enable.setText(isOnline.toString());
            }
        });

        btn_connected.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isWifiOrMobile();
            }
        });
    }


    public boolean isOnline() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void isWifiOrMobile() {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        boolean isWifiConn = networkInfo.isConnected();
        networkInfo = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        boolean isMobileConn = networkInfo.isConnected();
        Log.d(DEBUG_TAG, "Wifi connected: " + isWifiConn);
        Log.d(DEBUG_TAG, "Mobile connected: " + isMobileConn);

        tv_wifi_connected.setText(String.valueOf(isWifiConn));
        tv_mobile_connected.setText(String.valueOf(isMobileConn));
    }
}
