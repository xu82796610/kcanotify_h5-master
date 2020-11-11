package com.antest1.kcanotify.h5.ui

import com.antest1.kcanotify.h5.R
import com.antest1.kcanotify.h5.util.Utils
import android.os.Bundle
import com.antest1.kcanotify.h5.service.V2RayServiceManager

class ScSwitchActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        moveTaskToBack(true)

        setContentView(R.layout.activity_none)

        if (V2RayServiceManager.v2rayPoint.isRunning) {
            Utils.stopVService(this)
        } else {
            Utils.startVServiceFromToggle(this)
        }
        finish()
    }
}
