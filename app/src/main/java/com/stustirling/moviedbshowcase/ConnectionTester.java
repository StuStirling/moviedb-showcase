package com.stustirling.moviedbshowcase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Inject;

/**
 * Created by Stu Stirling on 15/06/16.
 */
public class ConnectionTester {

    private final Context context;

    @Inject
    public ConnectionTester(Context context) {
        this.context = context;
    }

    public boolean isThereAnInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
