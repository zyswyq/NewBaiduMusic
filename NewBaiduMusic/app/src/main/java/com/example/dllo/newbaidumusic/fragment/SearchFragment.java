package com.example.dllo.newbaidumusic.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.dllo.newbaidumusic.R;
import com.example.dllo.newbaidumusic.adapter.SearchingLVAdapter;
import com.example.dllo.newbaidumusic.bean.SearchingBean;
import com.example.dllo.newbaidumusic.bean.URLBean;
import com.example.dllo.newbaidumusic.minterface.CallBack;
import com.example.dllo.newbaidumusic.tool.NetTool;
import com.example.dllo.newbaidumusic.tool.StringTool;

/**
 * Created by dllo on 17/2/18.
 */

public class SearchFragment extends AbsFragment implements View.OnClickListener {

    private ImageView search,back;
    private EditText editText;
    private ListView listView;
    private SearchingLVAdapter adapter;
    private FragmentManager manager;
    private FragmentTransaction transcation;






    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        search=bindView(R.id.img_search);
        back=bindView(R.id.img_searchback);
        editText=bindView(R.id.edit_search);
        listView=bindView(R.id.listview_search);
        adapter=new SearchingLVAdapter();
        manager=getActivity().getSupportFragmentManager();

        search.setOnClickListener(this);
        back.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String url= URLBean.SEARCHING+ StringTool.getUTf(editText.getText().toString());
                NetTool.getInstance().startRequest(url, SearchingBean.class, new CallBack<SearchingBean>() {
                    @Override
                    public void onSuccess(SearchingBean responce) {
                        adapter.setIssearch(false);
                        adapter.setContext(context);
                        adapter.setData(responce);
                        listView.setAdapter(adapter);
                        View vi= LayoutInflater.from(context).inflate(R.layout.foot_item,null);
                        listView.addFooterView(vi);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "网络不好", Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.img_searchback:
                transcation=manager.beginTransaction();
                transcation.remove(SearchFragment.this);
                transcation.commit();

                break;
            case R.id.img_search:
                if (editText.getText().toString().equals("")){
                    Toast.makeText(context, "请输入搜索内容", Toast.LENGTH_SHORT).show();
                }
                else {
                    adapter.setIssearch(true);
                    listView.setAdapter(adapter);
                }
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search,container,false);
    }
}
