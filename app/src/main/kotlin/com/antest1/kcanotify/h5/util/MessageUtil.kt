package com.antest1.kcanotify.h5.util

import android.content.Context
import android.content.Intent
import com.antest1.kcanotify.h5.AppConfig


object MessageUtil {

    fun sendMsg2Service(ctx: Context, what: Int, content: String) {
        sendMsg(ctx, AppConfig.BROADCAST_ACTION_SERVICE, what, content)
    }

    fun sendMsg2UI(ctx: Context, what: Int, content: String) {
        sendMsg(ctx, AppConfig.BROADCAST_ACTION_ACTIVITY, what, content)
    }

    private fun sendMsg(ctx: Context, action: String, what: Int, content: String) {
        try {
            val intent = Intent()
            intent.action = action
            intent.`package` = AppConfig.ANG_PACKAGE
            intent.putExtra("key", what)
            intent.putExtra("content", content)
            ctx.sendBroadcast(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}