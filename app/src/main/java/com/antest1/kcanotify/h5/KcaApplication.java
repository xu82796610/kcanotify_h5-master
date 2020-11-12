package com.antest1.kcanotify.h5;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;

import org.acra.ACRA;
import org.acra.annotation.AcraCore;

import java.util.Locale;

import me.dozen.dpreference.DPreference;

import static com.antest1.kcanotify.h5.KcaConstants.PREF_KCA_LANGUAGE;

import com.antest1.kcanotify.h5.util.AngConfigManager;
import com.antest1.kcanotify.h5.util.Utils;

@AcraCore(buildConfigClass = BuildConfig.class)
/*@AcraMailSender(
        mailTo = "",
        reportAsFile = false
)*/

public class KcaApplication extends MultiDexApplication {
    public static Locale defaultLocale;
    public static Activity gameActivity;
    public static boolean isCheckVersion = false;
    public static long checkVersionDate = 0;
    private static final String PROCESSNAME = "com.antest1.kcanotify.h5";
    public int curIndex = -1; //Current proxy that is opened. (Used to implement restart feature)
    public boolean firstRun = false;

    public DPreference defaultDPreference = new DPreference(this, null);

    public static String secretcode;

    public static int authorizing_flag = 0;

    public static int config_import_flag = 0;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        ACRA.init(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        application = this;
        String language, country;
        defaultLocale = Locale.getDefault();
        SharedPreferences pref = getSharedPreferences("pref", MODE_PRIVATE);
        String[] pref_locale = pref.getString(PREF_KCA_LANGUAGE, "").split("-");

        MyCrashHandler myCrashHandler = MyCrashHandler.getInstance();
        myCrashHandler.init(getApplicationContext());

        AngConfigManager.inject(this);
        secretcode = "{\n" +
                "  \"dns\": {\n" +
                "    \"hosts\": {\n" +
                "      \"domain:googleapis.cn\": \"googleapis.com\"\n" +
                "    },\n" +
                "    \"servers\": [\n" +
                "      \"1.1.1.1\"\n" +
                "    ]\n" +
                "  },\n" +
                "  \"inbounds\": [\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10808,\n" +
                "      \"protocol\": \"socks\",\n" +
                "      \"settings\": {\n" +
                "        \"auth\": \"noauth\",\n" +
                "        \"udp\": true,\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"sniffing\": {\n" +
                "        \"destOverride\": [\n" +
                "          \"http\",\n" +
                "          \"tls\"\n" +
                "        ],\n" +
                "        \"enabled\": true\n" +
                "      },\n" +
                "      \"tag\": \"socks\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"listen\": \"127.0.0.1\",\n" +
                "      \"port\": 10809,\n" +
                "      \"protocol\": \"http\",\n" +
                "      \"settings\": {\n" +
                "        \"userLevel\": 8\n" +
                "      },\n" +
                "      \"tag\": \"http\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"log\": {\n" +
                "    \"loglevel\": \"warning\"\n" +
                "  },\n" +
                "  \"outbounds\": [\n" +
                "    {\n" +
                "      \"mux\": {\n" +
                "        \"concurrency\": -1,\n" +
                "        \"enabled\": false\n" +
                "      },\n" +
                "      \"protocol\": \"vmess\",\n" +
                "      \"settings\": {\n" +
                "        \"vnext\": [\n" +
                "          {\n" +
                "            \"address\": \"150.95.155.136\",\n" +
                "            \"port\": 8886,\n" +
                "            \"users\": [\n" +
                "              {\n" +
                "                \"alterId\": 8,\n" +
                "                \"id\": \"8374a0dc-1a54-4ce5-a7ca-7c05fbb52b05\",\n" +
                "                \"level\": 8,\n" +
                "                \"security\": \"auto\"\n" +
                "              }\n" +
                "            ]\n" +
                "          }\n" +
                "        ]\n" +
                "      },\n" +
                "      \"streamSettings\": {\n" +
                "        \"network\": \"ws\",\n" +
                "        \"security\": \"\",\n" +
                "        \"wssettings\": {\n" +
                "          \"connectionReuse\": true,\n" +
                "          \"headers\": {\n" +
                "            \"Host\": \"\"\n" +
                "          },\n" +
                "          \"path\": \"/veekxt_websocket_test\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"proxy\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"freedom\",\n" +
                "      \"settings\": {},\n" +
                "      \"tag\": \"direct\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"protocol\": \"blackhole\",\n" +
                "      \"settings\": {\n" +
                "        \"response\": {\n" +
                "          \"type\": \"http\"\n" +
                "        }\n" +
                "      },\n" +
                "      \"tag\": \"block\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"policy\": {\n" +
                "    \"levels\": {\n" +
                "      \"8\": {\n" +
                "        \"connIdle\": 300,\n" +
                "        \"downlinkOnly\": 1,\n" +
                "        \"handshake\": 4,\n" +
                "        \"uplinkOnly\": 1\n" +
                "      }\n" +
                "    },\n" +
                "    \"system\": {\n" +
                "      \"statsInboundUplink\": true,\n" +
                "      \"statsInboundDownlink\": true\n" +
                "    }\n" +
                "  },\n" +
                "  \"routing\": {\n" +
                "    \"domainStrategy\": \"IPIfNonMatch\",\n" +
                "    \"rules\": []\n" +
                "  },\n" +
                "  \"stats\": {}\n" +
                "}";

        if (pref_locale.length == 2) {
            if (pref_locale[0].equals("default")) {
                LocaleUtils.setLocale(defaultLocale);
            } else {
                language = pref_locale[0];
                country = pref_locale[1];
                LocaleUtils.setLocale(new Locale(language, country));
            }
        } else {
            pref.edit().remove(PREF_KCA_LANGUAGE).apply();
            LocaleUtils.setLocale(defaultLocale);
        }

        LocaleUtils.updateConfig(this, getBaseContext().getResources().getConfiguration());
        ACRA.init(this);

        /*if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            String processName = getProcessName();
            if (!PROCESSNAME.equals(processName)) {
                WebView.setDataDirectorySuffix("modWeb");
            }
        }*/
    }

    private static KcaApplication application;
    public static KcaApplication getInstance(){
        return application;
    }
}
