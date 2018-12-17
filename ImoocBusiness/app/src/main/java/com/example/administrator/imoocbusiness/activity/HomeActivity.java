package com.example.administrator.imoocbusiness.activity;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.imoocbusiness.R;
import com.example.administrator.imoocbusiness.activity.base.BaseActivity;
import com.example.administrator.imoocbusiness.view.fragment.home.HomeFragment;
import com.example.administrator.imoocbusiness.view.fragment.home.MessageFragment;
import com.example.administrator.imoocbusiness.view.fragment.home.MineFragment;

/**
 * 首页及所有的fragment
 */
public class HomeActivity extends BaseActivity implements View.OnClickListener
    ,HomeFragment.OnFragmentInteractionListener
    ,MessageFragment.OnFragmentInteractionListener
    ,MineFragment.OnFragmentInteractionListener {
    /**
     * fragment相关
     */
    private FragmentManager fm;
    private HomeFragment homeFragment;
    private MessageFragment messageFragment;
    private MineFragment mineFragment;
    private Fragment mCurrent;


    private TextView mPondView;
    private TextView mMineView;
    private TextView mMessageView;
    private TextView mHomeView;

    private RelativeLayout mMineLayoutView;
    private RelativeLayout mHomeLayoutView;
    private RelativeLayout mPondLayoutView;
    private RelativeLayout mMessageLayoutView;

    private LinearLayout mLinearLayout;
    private RelativeLayout mContentLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();

        //添加默认要显示的fragment
        homeFragment =new HomeFragment();
        fm=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        fragmentTransaction.replace(R.id.content_layout,homeFragment);//replace 相当于先remove ,在add
        fragmentTransaction.commit();
    }


    public void initView() {
        mMessageView = findViewById(R.id.message_image_view);
        mMessageLayoutView = findViewById(R.id.message_layout_view);
        mMessageLayoutView.setOnClickListener(this);

        mMineView = findViewById(R.id.mine_image_view);
        mMineLayoutView = findViewById(R.id.mine_layout_view);
        mMineLayoutView.setOnClickListener(this);

        mHomeLayoutView = findViewById(R.id.home_layout_view);
        mHomeLayoutView.setOnClickListener(this);
        mPondLayoutView = findViewById(R.id.pond_layout_view);
        mPondLayoutView.setOnClickListener(this);

        mHomeView = findViewById(R.id.home_image_view);
        mLinearLayout = findViewById(R.id.linearLayout);
        mPondView = findViewById(R.id.fish_image_view);
        mContentLayout = findViewById(R.id.content_layout);

        mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
    }

    /**
     * 隐藏fragment
     * @param fragment
     * @param fragmentTransaction
     */
    private void hideFragment(Fragment fragment,FragmentTransaction fragmentTransaction){
        if (fragment!=null){
            fragmentTransaction.hide(fragment);
        }
    }
    @Override
    public void onClick(View v) {
        FragmentTransaction fragmentTransaction=fm.beginTransaction();
        switch (v.getId()){
            case  R.id.home_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home_selected);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);
                //先确保未显示fragment已隐藏
                hideFragment(mineFragment,fragmentTransaction);
                hideFragment(messageFragment,fragmentTransaction);
                if (homeFragment==null){
                    homeFragment=new HomeFragment();
                    fragmentTransaction.add(R.id.content_layout,homeFragment);
                }else {
                    //已经创建过了
                    fragmentTransaction.show(homeFragment);
                }
                break;
            case R.id.message_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message_selected);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person);
                //先确保未显示fragment已隐藏
                hideFragment(mineFragment,fragmentTransaction);
                hideFragment(homeFragment,fragmentTransaction);
                if (messageFragment==null){
                    messageFragment=new MessageFragment();
                    fragmentTransaction.add(R.id.content_layout,messageFragment);
                }else {
                    //已经创建过了
                    fragmentTransaction.show(messageFragment);
                }
                break;
            case R.id.mine_layout_view:
                mHomeView.setBackgroundResource(R.drawable.comui_tab_home);
                mPondView.setBackgroundResource(R.drawable.comui_tab_pond);
                mMessageView.setBackgroundResource(R.drawable.comui_tab_message);
                mMineView.setBackgroundResource(R.drawable.comui_tab_person_selected);
                //先确保未显示fragment已隐藏
                hideFragment(messageFragment,fragmentTransaction);
                hideFragment(homeFragment,fragmentTransaction);
                if (mineFragment==null){
                    mineFragment=new MineFragment();
                    fragmentTransaction.add(R.id.content_layout,mineFragment);
                }else {
                    //已经创建过了
                    fragmentTransaction.show(mineFragment);
                }
                break;
        }
        fragmentTransaction.commit();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
