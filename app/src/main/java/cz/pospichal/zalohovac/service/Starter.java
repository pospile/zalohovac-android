package cz.pospichal.zalohovac.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class Starter extends BroadcastReceiver {
    public Starter() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent pushIntent = new Intent(context, SocketService.class);
            context.startService(pushIntent);
        }
    }
}
