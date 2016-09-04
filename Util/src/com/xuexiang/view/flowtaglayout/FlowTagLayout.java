package com.xuexiang.view.flowtaglayout;

import android.content.Context;
import android.database.DataSetObserver;
import android.util.AttributeSet;
import android.util.SparseBooleanArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * ��ʽ��ǩ����
 * ԭ����д{@link ViewGroup#onMeasure(int, int)}
 * ��{@link ViewGroup#onLayout(boolean, int, int, int, int)}����
 *
 * Created by yexiuliang on 2016/7/11.
 */

public class FlowTagLayout extends ViewGroup {
    /**
     * FlowLayout not support checked
     */
    public static final int FLOW_TAG_CHECKED_NONE = 0;
    /**
     * FlowLayout support single-select
     */
    public static final int FLOW_TAG_CHECKED_SINGLE = 1;
    /**
     * FlowLayout support multi-select
     */
    public static final int FLOW_TAG_CHECKED_MULTI = 2;

    /**
     * Should be used by subclasses to listen to changes in the dataset
     */
    AdapterDataSetObserver mDataSetObserver;

    /**
     * The adapter containing the data to be displayed by this view
     */
    ListAdapter mAdapter;

    /**
     * the tag click event callback
     */
    OnTagClickListener mOnTagClickListener;

    /**
     * the tag select event callback
     */
    OnTagSelectListener mOnTagSelectListener;

    /**
     * ��ǩ��ʽ����ѡ��ģʽ��Ĭ���ǲ�֧��ѡ�е�
     */
    private int mTagCheckMode = FLOW_TAG_CHECKED_NONE;

    /**
     * �洢ѡ�е�tag
     */
    private SparseBooleanArray mCheckedTagArray = new SparseBooleanArray();
    /**
     * ��View�Ŀ�ȣ����Ϊ0 ��Ϊwarp_content
     */
    private int mWidth;


    public FlowTagLayout(Context context) {
        super(context);

    }

    public FlowTagLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public FlowTagLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //��ȡPadding
        // ������ĸ�����Ϊ�����õĲ���ģʽ�ʹ�С
        int sizeWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = View.MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = View.MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = View.MeasureSpec.getMode(heightMeasureSpec);

        //FlowLayout���յĿ�Ⱥ͸߶�ֵ
        int resultWidth = 0;
        int resultHeight = 0;

        //����ʱÿһ�еĿ��
        int lineWidth = 0;
        //����ʱÿһ�еĸ߶ȣ�����������FlowLayout�ĸ߶�
        int lineHeight = 0;

        //����ÿ����Ԫ��
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);
            //����ÿһ����view�Ŀ�͸�
            measureChild(childView, widthMeasureSpec, heightMeasureSpec);

            //��ȡ�������Ŀ�͸�
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //��Ϊ��View��������margin������Ҫ����margin�ľ���
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();
            int realChildWidth = childWidth + mlp.leftMargin + mlp.rightMargin;
            int realChildHeight = childHeight + mlp.topMargin + mlp.bottomMargin;

            //�����ǰһ�еĿ�ȼ���Ҫ�������view�Ŀ�ȴ��ڸ��������Ŀ�ȣ��ͻ���
            if ((lineWidth + realChildWidth) > sizeWidth) {
                //����
                resultWidth = Math.max(lineWidth, realChildWidth);
                resultHeight += realChildHeight;
                //�����ˣ�lineWidth��lineHeight������
                lineWidth = realChildWidth;
                lineHeight = realChildHeight;
            } else {
                //�����У�ֱ�����
                lineWidth += realChildWidth;
                //ÿһ�еĸ߶�ȡ�������ֵ
                lineHeight = Math.max(lineHeight, realChildHeight);
            }

            //���������һ����ʱ�򣬿϶��ߵ��ǲ�����
            if (i == childCount - 1) {
                resultWidth = Math.max(lineWidth, resultWidth);
                resultHeight += lineHeight;
            }

            setMeasuredDimension(modeWidth == View.MeasureSpec.EXACTLY ? sizeWidth : resultWidth,
                    modeHeight == View.MeasureSpec.EXACTLY ? sizeHeight : resultHeight);

        }

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int flowWidth = getWidth();

        int childLeft = 0;
        int childTop = 0;

        //�����ӿؼ�����¼ÿ����view��λ��
        for (int i = 0, childCount = getChildCount(); i < childCount; i++) {
            View childView = getChildAt(i);

            //����View.GONE����View
            if (childView.getVisibility() == View.GONE) {
                continue;
            }

            //��ȡ�������Ŀ�͸�
            int childWidth = childView.getMeasuredWidth();
            int childHeight = childView.getMeasuredHeight();

            //��Ϊ��View��������margin������Ҫ����margin�ľ���
            ViewGroup.MarginLayoutParams mlp = (ViewGroup.MarginLayoutParams) childView.getLayoutParams();

            if (childLeft + mlp.leftMargin + childWidth + mlp.rightMargin > flowWidth) {
                //���д���
                childTop += (mlp.topMargin + childHeight + mlp.bottomMargin);
                childLeft = 0;
            }
            //����
            int left = childLeft + mlp.leftMargin;
            int top = childTop + mlp.topMargin;
            int right = childLeft + mlp.leftMargin + childWidth;
            int bottom = childTop + mlp.topMargin + childHeight;
            childView.layout(left, top, right, bottom);

            childLeft += (mlp.leftMargin + childWidth + mlp.rightMargin);
        }
    }

    @Override
    public ViewGroup.LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new ViewGroup.MarginLayoutParams(getContext(), attrs);
    }

    public ListAdapter getAdapter() {
        return mAdapter;
    }

    class AdapterDataSetObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
            reloadData();
        }

        @Override
        public void onInvalidated() {
            super.onInvalidated();
        }
    }


    /**
     * ��View����
     *
     * @param width
     */
    public void setChildWidth(int width) {
        this.mWidth = width;
    }

    /**
     * ���¼���ˢ������
     */
    private void reloadData() {
        removeAllViews();

        ViewGroup.MarginLayoutParams mMarginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        if (mWidth != 0) {
            mMarginLayoutParams.width = mWidth;
        }
        boolean isSetted = false;
        for (int i = 0; i < mAdapter.getCount(); i++) {
            final int j = i;
            mCheckedTagArray.put(i, false);
            final View childView = mAdapter.getView(i, null, this);
            addView(childView, mMarginLayoutParams);

            if (mAdapter instanceof OnInitSelectedPosition) {
                boolean isSelected = ((OnInitSelectedPosition) mAdapter).isSelectedPosition(i);
                //�ж�һ��ģʽ
                if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                    //��ѡֻ�е�һ��������
                    if (isSelected && !isSetted) {
                        mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                        isSetted = true;
                    }
                } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {
                    if (isSelected) {
                        mCheckedTagArray.put(i, true);
                        childView.setSelected(true);
                    }
                }
            }

            childView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mTagCheckMode == FLOW_TAG_CHECKED_NONE) {
                        if (mOnTagClickListener != null) {
                            mOnTagClickListener.onItemClick(FlowTagLayout.this, childView, j);
                        }
                    } else if (mTagCheckMode == FLOW_TAG_CHECKED_SINGLE) {
                        //�ж�״̬
                        if (mCheckedTagArray.get(j)) {
                            //��ѡģʽ�£�����ѡ��һ��
//                            mCheckedTagArray.put(j, false);
//                            childView.setSelected(false);
//                            if (mOnTagSelectListener != null) {
//                                mOnTagSelectListener.onItemSelect(FlowTagLayout.this, j,new ArrayList<Integer>());
//                            }
                            return;
                        }
                        //����ȫ��״̬Ϊfasle

                        for (int k = 0; k < mAdapter.getCount(); k++) {
                            mCheckedTagArray.put(k, false);
                            getChildAt(k).setSelected(false);
                        }
                        //���µ��״̬
                        mCheckedTagArray.put(j, true);
                        childView.setSelected(true);

                        if (mOnTagSelectListener != null) {
                            mOnTagSelectListener.onItemSelect(FlowTagLayout.this, j, Arrays.asList(j));
                        }
                    } else if (mTagCheckMode == FLOW_TAG_CHECKED_MULTI) {
                        if (mCheckedTagArray.get(j)) {
                            mCheckedTagArray.put(j, false);
                            childView.setSelected(false);
                        } else {
                            mCheckedTagArray.put(j, true);
                            childView.setSelected(true);
                        }
                        //�ص�
                        if (mOnTagSelectListener != null) {
                            List<Integer> list = new ArrayList<Integer>();
                            for (int k = 0; k < mAdapter.getCount(); k++) {
                                if (mCheckedTagArray.get(k)) {
                                    list.add(k);
                                }
                            }
                            mOnTagSelectListener.onItemSelect(FlowTagLayout.this, j, list);
                        }
                    }
                }
            });
        }
    }

    public void setOnTagClickListener(OnTagClickListener onTagClickListener) {
        this.mOnTagClickListener = onTagClickListener;
    }

    public void setOnTagSelectListener(OnTagSelectListener onTagSelectListener) {
        this.mOnTagSelectListener = onTagSelectListener;
    }

    /**
     * ��ListView��GridViewһ��ʹ��FlowLayout
     *
     * @param adapter
     */
    public void setAdapter(ListAdapter adapter) {
        if (mAdapter != null && mDataSetObserver != null) {
            mAdapter.unregisterDataSetObserver(mDataSetObserver);
        }

        //������е�����
        removeAllViews();
        mAdapter = adapter;

        if (mAdapter != null) {
            mDataSetObserver = new AdapterDataSetObserver();
            mAdapter.registerDataSetObserver(mDataSetObserver);
        }
    }

    /**
     * ��ȡ��ǩģʽ
     *
     * @return
     */
    public int getmTagCheckMode() {
        return mTagCheckMode;
    }

    /**
     * ���ñ�ǩѡ��ģʽ
     *
     * @param tagMode
     */
    public void setTagCheckedMode(int tagMode) {
        this.mTagCheckMode = tagMode;
    }
}
