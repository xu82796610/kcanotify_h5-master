package com.antest1.kcanotify.h5.extension

import android.preference.Preference

fun Preference.onClick(listener: () -> Unit) {
    setOnPreferenceClickListener {
        listener()
        true
    }
}