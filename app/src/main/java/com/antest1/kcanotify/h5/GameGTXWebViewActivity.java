package com.antest1.kcanotify.h5;

import android.content.Intent;
import android.content.res.Configuration;
import android.net.VpnService;
import android.os.Bundle;

import com.antest1.kcanotify.h5.util.Utils;

public class GameGTXWebViewActivity extends GameBaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = VpnService.prepare(this);

        if (KcaApplication.authorizing_flag == 0) {
            if (intent == null) {
                startV2ray();
                KcaApplication.authorizing_flag = 1;
            } else {
                startActivityForResult(intent, 0);
            }
        } else {
            startV2ray();
        }

        gameView.loadGame(prefs, GameConnection.Type.GTX);
    }

    @Override
    public void onMultiWindowModeChanged(boolean isInMultiWindowMode, Configuration newConfig) {
        super.onMultiWindowModeChanged(isInMultiWindowMode, newConfig);

        gameView.fitGameLayout();
    }

    public void startV2ray() {
        Utils.INSTANCE.startVService(this);
    }

    int getLayoutResID() {
        return R.layout.activity_game_webview_ori;
    }
}
