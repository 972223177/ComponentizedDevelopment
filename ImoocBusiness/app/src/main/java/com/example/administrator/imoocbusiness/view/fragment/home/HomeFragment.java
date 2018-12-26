package com.example.administrator.imoocbusiness.view.fragment.home;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.imoocbusiness.R;
import com.example.administrator.imoocbusiness.adapter.CourseAdapter;
import com.example.administrator.imoocbusiness.module.recommand.BaseRecommandModel;
import com.example.administrator.imoocbusiness.network.RequestCenter;

import learning.ly.com.imoocsdk.okhttp.listener.DisposeDataListener;

import static android.content.ContentValues.TAG;


public class HomeFragment extends Fragment implements View.OnClickListener
    ,AdapterView.OnItemClickListener {
    /**
     * UI
     */
    private View mContentView;
    private ListView mListView;
    private TextView mCategoryView;
    private TextView mSearchView;
    private ImageView mLoadingView;

    /**
     * data
     */
    private CourseAdapter mAdapter;
    private BaseRecommandModel mRecommandModel;


    private Context mContext;


    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestRecommandData();

    }

    /**
     * 发送首页列表数据请求
     */
    private void requestRecommandData() {
        RequestCenter.requestRecommondData(new DisposeDataListener() {
            @Override
            public void onSuccess(Object responseObj) {
                Log.e(TAG, "onSuccess: "+responseObj.toString() );
                //获取到数据更新UI
                mRecommandModel = (BaseRecommandModel) responseObj;
                showSuccessView();
            }

            @Override
            public void onFailure(Object reasonObj) {
                Log.e(TAG, "onFailure: "+reasonObj.toString() );
            }
        });

    }

    /**
     *
     */
    private void showSuccessView() {

        if (mRecommandModel.data.list != null && mRecommandModel.data.list.size() > 0) {
            mLoadingView.setVisibility(View.GONE);
            mListView.setVisibility(View.VISIBLE);
            mAdapter=new CourseAdapter(mContext,mRecommandModel.data.list);
            mListView.setAdapter(mAdapter);
        }else {
            showErrorView();
        }
    }

    private void showErrorView() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mContext=getActivity();
        mContentView=inflater.inflate(R.layout.fragment_home, container, false);
        initView();
        return mContentView;
    }

    private void initView() {
        mCategoryView=mContentView.findViewById(R.id.category_view);
        mCategoryView.setOnClickListener(this);
        mSearchView=mContentView.findViewById(R.id.search_view);
        mSearchView.setOnClickListener(this);
        mListView=mContentView.findViewById(R.id.list_view);
        mListView.setOnItemClickListener(this);
        mLoadingView=mContentView.findViewById(R.id.loading_view);
        //启动loadingview动画
        AnimationDrawable anim= (AnimationDrawable) mLoadingView.getDrawable();
        anim.start();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
