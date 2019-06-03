package com.example.autclub.AppModel;

import android.app.AlertDialog;
import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;

public class App extends Application {
    public static final String CHANNEL_1_ID = "channel1";

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannels();
    }

    private void createNotificationChannels() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel1 = new NotificationChannel(
                    CHANNEL_1_ID,
                    "Channel 1",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel1.setDescription("This is Channel 1");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel1);
        }
    }
    public static void buildDialog(Context context, String message, String buttonTxt) {
        AlertDialog.Builder alertdialog = new AlertDialog.Builder(context);
        alertdialog.setMessage(message);
        alertdialog.setPositiveButton(buttonTxt, null);//when ok button is pressed then the error message will go away
        alertdialog.show();
    }
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();
        return (netinfo != null && netinfo.isConnected());
    }
    public static void newActivityPage(Context context ,Class nextClass, String value) {
        Intent intent = new Intent(context, nextClass);
        intent.putExtra("value", value);
        context.startActivity(intent);
    }
}
