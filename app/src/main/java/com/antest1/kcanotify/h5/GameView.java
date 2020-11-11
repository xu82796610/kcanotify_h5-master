package com.antest1.kcanotify.h5;

import android.content.SharedPreferences;
import android.view.MotionEvent;

public interface GameView {
    void setLayoutParams(int width, int height);

    void handleTouch(MotionEvent event);

    // TODO: use the callbacks approach to decouple further
    void assignActivity(GameBaseActivity activity);

    void loadGame(SharedPreferences prefs, GameConnection.Type connectionType);

    void pauseGame();

    void resumeGame();

    void reloadGame();

    void destroy();

    // TODO: make it depend on connection, or one JS fits all cases
    void fitGameLayout();
}
