package com.xuexiang.util.adapter.commonadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

import com.xuexiang.util.adapter.commonadapter.ViewHolder;

/**
 * Created by su on 2016/5/28.
 */
public  abstract class  CommonAdapter<T> extends BaseAdapter {

    private static final int TYPE_SECTION_ITEM = 0;
    private static final int TYPE_ITEM = 1;
    private int viewTypeCount = 1;
    private SectionCallBack mCallBack;
    private int mSectionLayoutId;

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<T> mDatas;
    protected final int mItemLayoutId;

    /**
     * ��ʼ��ͨ��Adapter
     *
     * @param context      ������
     * @param mDatas       ��Ҫ��ʾ�����ݼ���
     * @param itemLayoutId �Ӳ����ļ�
     */
    public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId) {
        this.mContext = context;
        this.mInflater = LayoutInflater.from(mContext);
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public void refresh(List<T> mDatas) {
        this.mDatas = mDatas;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public T getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return viewTypeCount;
    }

    public void setViewTypeCount(int viewTypeCount) {
        this.viewTypeCount = viewTypeCount;
        if (this.viewTypeCount > 1 && mCallBack != null) {
            mSectionLayoutId = mCallBack.addSectionLayout();
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (null == mDatas || position < 0 || position > getCount()) {
            return TYPE_ITEM;
        }

        if (mCallBack != null && mCallBack.isSection(position)) {
            return TYPE_SECTION_ITEM;
        }
        return TYPE_ITEM;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case TYPE_ITEM:
                //��ViewHolder�л�ȡ�ؼ�view����Ϊ���򴴽�
                final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
                convert(viewHolder, getItem(position), position);

                return viewHolder.getConvertView();

            case TYPE_SECTION_ITEM:
                View view = mInflater.inflate(mSectionLayoutId, null);
                mCallBack.setView(view,position);
                return view;
        }
        return null;
    }

    /**
     * ��ȡ��getView�м�ı�Ĳ���
     *
     * @param helper holder�������
     * @param item   Bean����
     */
    public abstract void convert(ViewHolder helper, T item, int position);

    /**
     * ���ViewHolder�е�view
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {
        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }

    public void addCallBack(SectionCallBack callBack) {
        this.mCallBack = callBack;
    }

    /**
     *section�ص��ӿ�
     */
    public interface SectionCallBack {

        boolean isSection(int position);

        int addSectionLayout();

        void setView(View view, int position);
    }
}