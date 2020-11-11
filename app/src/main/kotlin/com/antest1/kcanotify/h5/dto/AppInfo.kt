package com.antest1.kcanotify.h5.dto

import android.graphics.drawable.Drawable

data class AppInfo(val appName: String,
                   val packageName: String,
                   val appIcon: Drawable,
                   val isSystemApp: Boolean,
                   var isSelected: Int)