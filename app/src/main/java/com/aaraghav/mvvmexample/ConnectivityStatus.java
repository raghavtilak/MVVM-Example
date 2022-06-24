package com.aaraghav.mvvmexample;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;

public class ConnectivityStatus extends LiveData<Boolean> {

    ConnectivityManager connectivityManager;
    ConnectivityManager.NetworkCallback networkCallback;

    public ConnectivityStatus(Context c) {
        connectivityManager =
                (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        networkCallback = new ConnectivityManager.NetworkCallback() {

            @Override
            public void onUnavailable() {
                super.onUnavailable();
                postValue(false);
            }

            @Override
            public void onAvailable(@NonNull Network network) {
                super.onAvailable(network);
                postValue(true);
            }

            @Override
            public void onLost(@NonNull Network network) {
                super.onLost(network);
                postValue(false);
            }
        };

    }

    private void checkInternet() {
        NetworkInfo network = this.connectivityManager.getActiveNetworkInfo();
        if (network == null) {
            this.postValue(false);
        }

        NetworkRequest networkRequest = new NetworkRequest.Builder()
                .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                .build();
        connectivityManager.registerNetworkCallback(networkRequest, networkCallback);

    }

    protected void onActive() {
        super.onActive();
        this.checkInternet();
    }

    protected void onInactive() {
        super.onInactive();
        this.connectivityManager.unregisterNetworkCallback(networkCallback);
    }
}


