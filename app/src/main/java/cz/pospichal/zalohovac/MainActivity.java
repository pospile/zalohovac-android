package cz.pospichal.zalohovac;

import android.content.Intent;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.URISyntaxException;

import cz.pospichal.zalohovac.service.SocketService;
import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

public class MainActivity extends AppCompatActivity {


    Socket socket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("destroy","created");
        socket = null;
        try {
            socket = IO.socket("http://138.68.67.174:2579");
            Log.e("socket", "connecting");
        } catch (URISyntaxException e) {
            Log.e("error", e.getMessage());
            e.printStackTrace();
        }
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {
                Log.e("socket", "connected");
                socket.emit("auth", "me");
            }

        }).on("init", new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        }).on(Socket.EVENT_DISCONNECT, new Emitter.Listener() {

            @Override
            public void call(Object... args) {}

        });
        socket.connect();
        /*
        i= new Intent(this, SocketService.class);
        this.startService(i);
        */
    }

    @Override
    protected void onDestroy() {
        Log.e("destroy", "Now");
        /*
        this.stopService(i);
        */
        super.onDestroy();
    }
}
