package com.example.alex.testtask.firebase;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by alex on 20.02.18.
 */

public class PlayerReceiver extends BroadcastReceiver{
    private static final String TYPE = "type";
    private static final int ID_ACTION_PLAY = 0;
    private static final int ID_ACTION_STOP = 1;

    @Override
    public void onReceive(Context context, Intent intent)
    {
        Log.e("onReceive: ", intent.getStringExtra("body"));
        Toast.makeText(context, intent.getStringExtra("body"), Toast.LENGTH_SHORT).show();
//        int type = intent.getIntExtra(TYPE, ID_ACTION_STOP);
//        switch (type)
//        {
//            case ID_ACTION_PLAY:
//                // выполнение полученного намерения
//                context.startService(new Intent(context, PlayService.class));
//                break;
//        }
    }
}
