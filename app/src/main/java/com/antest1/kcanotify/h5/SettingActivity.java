package com.antest1.kcanotify.h5;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.media.RingtoneManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;

import java.util.Locale;

import static com.antest1.kcanotify.h5.KcaConstants.PREF_AKASHI_FILTERLIST;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_AKASHI_STARLIST;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_AKASHI_STAR_CHECKED;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_ALARM_DELAY;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_APK_DOWNLOAD_SITE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_CHECK_UPDATE_START;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_DATALOAD_ERROR_FLAG;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_DISABLE_CUSTOMTOAST;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_EQUIPINFO_FILTCOND;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FAIRY_AUTOHIDE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FAIRY_DOWN_FLAG;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FAIRY_ICON;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FAIRY_NOTI_LONGCLICK;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FAIRY_REV;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_FIX_VIEW_LOC;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCAQSYNC_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCARESOURCE_VERSION;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_ACTIVATE_DROPLOG;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_ACTIVATE_RESLOG;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_BATTLENODE_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_BATTLEVIEW_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_DATA_VERSION;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_EXP_TYPE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_EXP_VIEW;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_LANGUAGE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_MORALE_MIN;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_AKASHI;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_DOCK;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_EXP;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_MORALE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_NOTIFYATSVCOFF;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_QUEST_FAIRY_GLOW;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_RINGTONE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_SOUND_KIND;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_V_HD;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_NOTI_V_NS;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_QUESTVIEW_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_SEEK_CN;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_SET_PRIORITY;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_LAST_QUEST_CHECK;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_LAST_UPDATE_CHECK;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_OPENDB_API_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_PACKET_LOG;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_POIDB_API_USE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_RES_USELOCAL;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_SHIPINFO_FILTCOND;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_SHIPINFO_SORTKEY;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_SHOWDROP_SETTING;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_SHOW_CONSTRSHIP_NAME;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_SNIFFER_MODE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_TIMER_WIDGET_STATE;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_UPDATE_SERVER;
import static com.antest1.kcanotify.h5.KcaConstants.PREF_VIEW_YLOC;
import static com.antest1.kcanotify.h5.KcaConstants.SEEK_33CN1;
import static com.antest1.kcanotify.h5.KcaUtils.getStringPreferences;
import static com.antest1.kcanotify.h5.NestedPreferenceFragment.NESTED_TAG;

public class SettingActivity extends AppCompatActivity implements MainPreferenceFragment.Callback {
    public static String currentVersion = BuildConfig.VERSION_NAME;
    public static final int REQUEST_OVERLAY_PERMISSION = 2;
    public static final int REQUEST_USAGESTAT_PERMISSION = 3;

    Toolbar toolbar;
    public SettingActivity() {
        LocaleUtils.updateConfig(this);
    }

    public String getStringWithLocale(int id) {
        return KcaUtils.getStringWithLocale(getApplicationContext(), getBaseContext(), id);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(getString(R.string.action_settings));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (savedInstanceState == null) {
            FragmentManager fm = getFragmentManager();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, new MainPreferenceFragment()).commit();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onNestedPreferenceSelected(int key) {
        Log.e("KCA", "onNestedPreferenceSelected");
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, NestedPreferenceFragment.newInstance(key), NESTED_TAG)
                .addToBackStack(NESTED_TAG).commit();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Log.e("KCA", "lang: " + newConfig.getLocales().get(0).getLanguage() + " " + newConfig.getLocales().get(0).getCountry());
            KcaApplication.defaultLocale = newConfig.getLocales().get(0);
        } else {
            Log.e("KCA", "lang: " + newConfig.locale.getLanguage() + " " + newConfig.locale.getCountry());
            KcaApplication.defaultLocale = newConfig.locale;
        }
        if (getStringPreferences(getApplicationContext(), PREF_KCA_LANGUAGE).startsWith("default")) {
            LocaleUtils.setLocale(Locale.getDefault());
        } else {
            String[] pref = getStringPreferences(getApplicationContext(), PREF_KCA_LANGUAGE).split("-");
            LocaleUtils.setLocale(new Locale(pref[0], pref[1]));
        }
        super.onConfigurationChanged(newConfig);
    }

    public static String getDefaultValue(String prefKey) {
        switch (prefKey) {
            case PREF_KCA_SEEK_CN:
                return String.valueOf(SEEK_33CN1);
            case PREF_OPENDB_API_USE:
            case PREF_POIDB_API_USE:
            case PREF_KCAQSYNC_USE:
            case PREF_AKASHI_STAR_CHECKED:
            case PREF_KCA_SET_PRIORITY:
            case PREF_DISABLE_CUSTOMTOAST:
            case PREF_FAIRY_AUTOHIDE:
            case PREF_KCA_NOTI_AKASHI:
            case PREF_SHOW_CONSTRSHIP_NAME:
            case PREF_DATALOAD_ERROR_FLAG:
            case PREF_FIX_VIEW_LOC:
            case PREF_PACKET_LOG:
            case PREF_RES_USELOCAL:
            case PREF_FAIRY_DOWN_FLAG:
                return "boolean_false";
            case PREF_KCA_EXP_VIEW:
            case PREF_KCA_NOTI_NOTIFYATSVCOFF:
            case PREF_KCA_NOTI_DOCK:
            case PREF_KCA_NOTI_EXP:
            case PREF_KCA_NOTI_MORALE:
            case PREF_KCA_BATTLEVIEW_USE:
            case PREF_KCA_BATTLENODE_USE:
            case PREF_KCA_QUESTVIEW_USE:
            case PREF_KCA_NOTI_V_HD:
            case PREF_KCA_NOTI_V_NS:
            case PREF_SHOWDROP_SETTING:
            case PREF_FAIRY_NOTI_LONGCLICK:
            case PREF_KCA_NOTI_QUEST_FAIRY_GLOW:
            case PREF_KCA_ACTIVATE_DROPLOG:
            case PREF_KCA_ACTIVATE_RESLOG:
            case PREF_CHECK_UPDATE_START:
                return "boolean_true";
            case PREF_KCA_LANGUAGE:
                return "R.string.default_locale";
            case PREF_KCA_NOTI_SOUND_KIND:
                return "R.string.sound_kind_value_vibrate";
            case PREF_KCA_NOTI_RINGTONE:
                return RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION).toString();
            case PREF_APK_DOWNLOAD_SITE:
                return "R.string.app_download_link_playstore";
            case PREF_AKASHI_STARLIST:
            case PREF_AKASHI_FILTERLIST:
            case PREF_SHIPINFO_FILTCOND:
                return "|";
            case PREF_SHIPINFO_SORTKEY:
                return "|1,true|";
            case PREF_FAIRY_ICON:
            case PREF_KCA_EXP_TYPE:
            case PREF_VIEW_YLOC:
            case PREF_LAST_UPDATE_CHECK:
            case PREF_SNIFFER_MODE:
            case PREF_KCARESOURCE_VERSION:
            case PREF_LAST_QUEST_CHECK:
            case PREF_FAIRY_REV:
                return "0";
            case PREF_ALARM_DELAY:
                return "61";
            case PREF_KCA_MORALE_MIN:
                return "40";
            case PREF_EQUIPINFO_FILTCOND:
                return "all";
            case PREF_KCA_DATA_VERSION:
                return "R.string.default_gamedata_version";
            case PREF_UPDATE_SERVER:
                return "R.string.server_nova";
            case PREF_TIMER_WIDGET_STATE:
                return "{}";
            default:
                return "";
        }
    }
}