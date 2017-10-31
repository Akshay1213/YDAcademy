package com.example.user.ydacademy;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by android on 10/25/17.
 */

public class CheckInternet {
    public static boolean checkinternet(Context context) {
        ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connMgr.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }
}
