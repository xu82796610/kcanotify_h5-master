package com.antest1.kcanotify.h5.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.text.TextUtils
import com.google.zxing.WriterException
import com.antest1.kcanotify.h5.AppConfig

import com.antest1.kcanotify.h5.util.Utils

class TaskerReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {

        try {
            val bundle = intent?.getBundleExtra(AppConfig.TASKER_EXTRA_BUNDLE)
            val switch = bundle?.getBoolean(AppConfig.TASKER_EXTRA_BUNDLE_SWITCH, false)
            val guid = bundle?.getString(AppConfig.TASKER_EXTRA_BUNDLE_GUID, "")

            if (switch == null || guid == null || TextUtils.isEmpty(guid)) {
                return
            } else if (switch) {
                if (guid == AppConfig.TASKER_DEFAULT_GUID) {
                    Utils.startVService(context)
                } else {
                    Utils.startVService(context, guid)
                }
            } else {
                Utils.stopVService(context)
            }
        } catch (e: WriterException) {
            e.printStackTrace()
        }
    }
}
