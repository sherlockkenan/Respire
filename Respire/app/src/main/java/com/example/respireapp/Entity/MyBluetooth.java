package com.example.respireapp.Entity;


import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.util.Log;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;


/**
 * Created by vito on 7/6/16.
 */


public class MyBluetooth {
    private Thread initBluetoothThread = null;
    private int status = 0;
    private BluetoothSocket bluetoothSocket = null;
    private static String DevName = "HC-06";
    private static UUID MY_UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    public MyBluetooth(){
        initBluetoothThread = new Thread() {
            @Override
            public void run() {
                BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
                if (bluetoothAdapter==null) {
                    return;
                }
                try {
                    if (!bluetoothAdapter.isEnabled()) {
                        bluetoothAdapter.enable();
                    }
                } catch (Exception e) {
                    Log.e("Bluetooth", e.getMessage());
                    return;
                }
                BluetoothDevice bluetoothDevice;
                for (Object obj: bluetoothAdapter.getBondedDevices().toArray()) {
                    bluetoothDevice = (BluetoothDevice)obj;
                    if (bluetoothDevice.getName().equals(DevName)) {
                        try {
                            bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
                            status = 0;
                        } catch (IOException e) {
                            Log.e("Bluetooth", e.getMessage());
                        }
                        break;
                    }
                }
            }
        };
        initBluetoothThread.start();
    }

    public String connect() {
        if(bluetoothSocket != null && status ==0){
            try {
                bluetoothSocket.connect();
                return "Success";
            } catch (IOException e) {
                Log.e("Bluetooth", e.getMessage());
                return e.getMessage();
            }
        }else{
            BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
            if (bluetoothAdapter==null) {
                return "Bluetooth adapter unavailable";
            }
            try {
                if (!bluetoothAdapter.isEnabled()) {
                    bluetoothAdapter.enable();
                }
            } catch (Exception e) {
                Log.e("Bluetooth", e.getMessage());
                return e.getMessage();
            }
            BluetoothDevice bluetoothDevice;
            for (Object obj: bluetoothAdapter.getBondedDevices().toArray()) {
                bluetoothDevice = (BluetoothDevice)obj;
                if (bluetoothDevice.getName().equals(DevName)) {
                    try {
                        bluetoothSocket = bluetoothDevice.createInsecureRfcommSocketToServiceRecord(MY_UUID);
                        bluetoothSocket.connect();
                        status = 1;
                        return "Success";
                    } catch (IOException e) {
                        Log.e("Bluetooth", e.getMessage());
                        return e.getMessage();
                    }
                }
            }
            return "Success";
        }

    }

    public String disconnect(){
        initBluetoothThread.interrupt();
        if(bluetoothSocket == null) return "Success";
        try {
            bluetoothSocket.close();
            bluetoothSocket = null;
            status = -1;
        } catch (IOException e) {
            Log.e("Bluetooth", e.getMessage());
            return e.getMessage();
        }
        return "Success";
    }

    private int readFromDev(byte[] tempBuffer) throws Exception {
        try{
            InputStream inputStream= bluetoothSocket.getInputStream();
            int n = inputStream.read(tempBuffer);
            return n;
        }catch (Exception e) {
            return -1;
        }
    }

    public String[] getLines(int n){
        String[] returnValue = new String[n+1];
        if(bluetoothSocket == null){
            returnValue[0] = "Error:Device not found!";
            return returnValue;
        }

        //read n lines from bluetooth socket
        try {
            int lines = 0;
            returnValue[0] = "Success";
            byte[] tempBuffer = new byte[1024];
            int tempBufferSize;
            boolean flushed = false;
            StringBuffer strBuffer = new StringBuffer();

            while ((tempBufferSize = readFromDev(tempBuffer))!= -1){
                if(tempBufferSize == 0) continue;
                String tempStr = new String(tempBuffer);
                int i = 0;
                if(!flushed){
                    for(;i<tempBufferSize;i++){
                        if(tempStr.charAt(i) == '\n'){
                            flushed = true;
                            i++;
                            break;
                        }
                    }
                }

                for(; i < tempBufferSize; i++){
                    char c = tempStr.charAt(i);
                    strBuffer.append(c);
                    if(c == '\n'){
                        returnValue[lines+1] = new String(strBuffer);
                        strBuffer = new StringBuffer();
                        lines = lines + 1;
                        if(lines == n)
                            return returnValue;
                    }
                }
            }
        }catch(Exception e){

        }
        returnValue[0] = "Error:Device Unreachable!";
        return returnValue;
    }

}
