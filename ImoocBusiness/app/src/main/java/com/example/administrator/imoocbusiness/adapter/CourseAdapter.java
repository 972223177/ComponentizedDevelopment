package com.example.administrator.imoocbusiness.adapter;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.imoocbusiness.R;
import com.example.administrator.imoocbusiness.module.recommand.RecommandBodyValue;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by ly on 2018/12/26.
 */
public class CourseAdapter extends BaseAdapter {
    /**
     * listview里不同类型的标识
     */
    private static final int CARD_COUNT = 4;
    private static final int VIDEO_TYPE = 0x00;
    private static final int CARD_SIGNAL_PIC = 0x01;
    private static final int CARD_MUTIL_PIC = 0x02;
    private static final int CARD_VIEW_PAGER = 0x03;

    private Context mContext;
    private ViewHolder mViewHolder;
    private LayoutInflater mInflater;

    private ArrayList<RecommandBodyValue> mData;


    public CourseAdapter(Context context, ArrayList<RecommandBodyValue> list) {
        this.mContext = context;
        this.mData = list;
        this.mInflater = LayoutInflater.from(mContext);
        //这里直接用glide，不对imageloader初始化

    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int i) {
        return mData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getViewTypeCount() {
        return CARD_COUNT;
    }

    @Override
    public int getItemViewType(int position) {
        RecommandBodyValue value = (RecommandBodyValue) getItem(position);
        return value.type;
    }

    /**
     * 注意当设置getViewTypeCount()返回值大于1时也就是说布局类型有多个,
     * 要确保case 判断的类型也有相应的个数,当前getViewTypeCount()返回为5,那么case判断类型的个数也为5个
     * 否则也会出现空指针引发getImportantForAccessibility();
     * @param i
     * @param view
     * @param viewGroup
     * @return
     */
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        //获取数据的type类型,初始化控件
        int type = getItemViewType(i);
        final RecommandBodyValue value = (RecommandBodyValue) getItem(i);
        if (view == null) {
            switch (type) {
                case CARD_SIGNAL_PIC:
                    mViewHolder = new ViewHolder();
                    view = mInflater.inflate(R.layout.item_product_card_one_layout, viewGroup, false);
                    mViewHolder.mLogoView = view.findViewById(R.id.item_logo_view);
                    mViewHolder.mTitileView = view.findViewById(R.id.item_title_view);
                    mViewHolder.mInfoView = view.findViewById(R.id.item_info_view);
                    mViewHolder.mFooterView = view.findViewById(R.id.item_footer_view);
                    mViewHolder.mPriceView = view.findViewById(R.id.item_price_view);
                    mViewHolder.mFromView = view.findViewById(R.id.item_from_view);
                    mViewHolder.mZanView = view.findViewById(R.id.item_zan_view);
                    mViewHolder.mProductLayout = view.findViewById(R.id.product_photo_layout);
                    view.setTag(mViewHolder);
                    break;
                case  VIDEO_TYPE:

                    break;
                case CARD_MUTIL_PIC:

                    break;
                case CARD_VIEW_PAGER:

                    break;
            }
        } else {
            mViewHolder = (ViewHolder) view.getTag();
        }
        //开始绑定数据
        switch (type) {
            case CARD_SIGNAL_PIC:
                mViewHolder.mTitileView.setText(value.title);
                mViewHolder.mInfoView.setText(value.info
                        .concat(mContext.getString(R.string.tian_qian)));
                mViewHolder.mFooterView.setText(value.text);
                mViewHolder.mPriceView.setText(value.price);
                mViewHolder.mFromView.setText(value.from);
                mViewHolder.mZanView.setText(mContext.getString(R.string.dian_zan)
                        .concat(value.zan));
                Glide.with(mContext).load(value.logo).into(mViewHolder.mLogoView);
                Glide.with(mContext).load(value.url.get(0)).into(mViewHolder.mProductView);
                break;
            case  VIDEO_TYPE:

                break;
            case CARD_MUTIL_PIC:

                break;
            case CARD_VIEW_PAGER:

                break;
        }

        return view;
    }


    private class ViewHolder {
        //所有card共有属性
        private CircleImageView mLogoView;
        private TextView mTitileView;
        private TextView mInfoView;
        private TextView mFooterView;
        //video card特有属性
        private RelativeLayout mVideoContentLayout;
        private ImageView mShareView;
        //video card外所有card具有属性
        private TextView mPriceView;
        private TextView mFromView;
        private TextView mZanView;
        //card one 特有属性
        private LinearLayout mProductLayout;
        //card two 特有属性
        private ImageView mProductView;
        //card three 特有属性
        private ViewPager mViewPager;
    }
}
