package com.nettox.nettoxwapps;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.webkit.WebView;

public class ProgressDialog extends DialogFragment {

//    WebView webView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.progress_layout, container);

        // webView = (WebView) view.findViewById(R.id.webV_progressLayout_gears);

        getDialog().setCancelable(true);
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().setCanceledOnTouchOutside(false);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // call method to set webView
//        runGears();
    }

//    private void runGears() {
//
//        webView.loadUrl("file:///android_asset/gears_rolling.html");
//        webView.getSettings();
//        webView.setBackgroundColor(Color.TRANSPARENT);
//    }
}
