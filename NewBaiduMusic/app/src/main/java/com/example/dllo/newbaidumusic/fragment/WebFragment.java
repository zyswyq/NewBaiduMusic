package com.example.dllo.newbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dllo.newbaidumusic.R;

/**
 * Created by dllo on 17/2/18.
 */

public class WebFragment extends AbsFragment{

    private WebView webview;
    private String url;
    private TextView text;
    private ImageView back;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_web,container,false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webview=bindView(R.id.webview_webfragment);
        text=bindView(R.id.tv_web);
        back=bindView(R.id.img_webfragment_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager manager=getActivity().getSupportFragmentManager();
                FragmentTransaction transaction=manager.beginTransaction();
                transaction.remove(WebFragment.this);
                transaction.commit();
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Bundle bundle=getArguments();
        url=bundle.getString("url");
        webview.loadUrl(url);
//        webview.setWebViewClient(new WebViewClient() {
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                view.loadUrl(url);
//                return true;
//            }
//        });
        webview.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onReceivedTitle(WebView view, String title) {
                text.setText(title);//a textview
            }
        });
        WebSettings webSettings=webview.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }

    public static WebFragment newInstance(String url) {
        
        Bundle args = new Bundle();
        String url1=url;
        args.putString("url",url1);
        WebFragment fragment = new WebFragment();
        fragment.setArguments(args);
        return fragment;
    }



}
