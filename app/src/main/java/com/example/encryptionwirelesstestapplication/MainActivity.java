package com.example.encryptionwirelesstestapplication;

import android.app.Activity;
import android.content.Context;
import android.content.IntentFilter;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.encryptionwirelesstestapplication.service.EncryptionService;
import com.example.encryptionwirelesstestapplication.service.FileReader;
import com.example.encryptionwirelesstestapplication.service.impl.EncryptionServiceImpl;
import com.example.encryptionwirelesstestapplication.service.impl.FileReaderImpl;

import java.util.ArrayList;

public class MainActivity extends Activity {

    public static final String TAG = "===MainActivity";
    private final IntentFilter intentFilter = new IntentFilter();
    WifiP2pManager.Channel channel;
    WifiP2pManager manager;
    private EncryptionServiceImpl encryptionService;
    private FileReaderImpl fileReader;
    ListView listViewDevices;

    ArrayAdapter mAdapter;
    WifiP2pDevice[] deviceListItems;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION);
        fileReader = new FileReaderImpl();
        encryptionService = new EncryptionServiceImpl();

        intentFilter.addAction(WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_CONNECTION_CHANGED_ACTION);
        intentFilter.addAction(WifiP2pManager.WIFI_P2P_THIS_DEVICE_CHANGED_ACTION);

        manager = (WifiP2pManager) getSystemService(Context.WIFI_P2P_SERVICE);
        channel = manager.initialize(this, getMainLooper(), null);
        String result = fileReader.fileToString(getApplicationContext());
        final String[] encryptedText = {null};
        final Button button = (Button) findViewById(R.id.Encryption);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    encryptedText[0] = encryptionService.SHA1(result);
                    TextView text = (TextView) findViewById(R.id.textView);
                    text.append(encryptedText[0].toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }


    public void setDeviceList(ArrayList<WifiP2pDevice> deviceDetails) {

        deviceListItems = new WifiP2pDevice[deviceDetails.size()];
        String[] deviceNames = new String[deviceDetails.size()];
        for(int i=0 ;i< deviceDetails.size(); i++){
            deviceNames[i] = deviceDetails.get(i).deviceName;
            deviceListItems[i] = deviceDetails.get(i);
        }
        mAdapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,android.R.id.text1,deviceNames);
        listViewDevices.setAdapter(mAdapter);
    }
}

