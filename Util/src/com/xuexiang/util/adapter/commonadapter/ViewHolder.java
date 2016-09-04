package com.xuexiang.util.adapter.commonadapter;

/**
 * Created by su on 2015/11/12.
 */


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * ͨ�� ViewHolder ������
 *
 */
public class ViewHolder {

    private final SparseArray<View> mViews;
    private int mPosition;
    private View mConvertView;
    private Context mContext;

    private ViewHolder(Context context, ViewGroup parent, int layoutId, int position)
    {
        mContext = context;
        this.mPosition = position;
        this.mViews = new SparseArray<View>();
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,false);
        mConvertView.setTag(this);   // setTag
    }

    /**
     * �õ�һ��ViewHolder����
     *
     * @param context
     * @param convertView
     * @param parent
     * @param layoutId
     * @param position
     * @return
     */
    public static ViewHolder get(Context context, View convertView,ViewGroup parent, int layoutId, int position)
    {
        if (convertView == null){
            //����ViewHolder���� ,����View����
            return new ViewHolder(context, parent, layoutId, position);
        }
        return (ViewHolder)convertView.getTag();
    }

    public View getConvertView()
    {
        return mConvertView;
    }


    /**
     * ͨ���ؼ���Id��ȡ���ڵĿؼ������û�������views
     *
     * @param viewId
     * @return
     */
    @SuppressWarnings("unchecked")
	public <T extends View> T getView(int viewId){

        View view = mViews.get(viewId);
        if (view == null){
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (T)view;
    }


    /**
     * ΪTextView�����ַ���
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setEditText(int viewId, String text)
    {
        EditText view = getView(viewId);
        view.setText(text);
        return this;
    }

    /**
     * ΪTextView�����ַ���
     *
     * @param viewId
     * @param text
     * @return
     */
    public ViewHolder setText(int viewId, String text)
    {
        TextView view = getView(viewId);
        if (view != null) {
            view.setText(text);
        }
        return this;
    }


    public ViewHolder setText(int viewId, int strId)
    {
        TextView view = getView(viewId);
        view.setText(strId);
        return this;
    }

    public ViewHolder setTextDrawLeft(int viewId,Drawable drawable){
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(drawable, null, null, null);
        return this;
    }

    public ViewHolder setTextDrawRight(int viewId,Drawable drawable){
        TextView view = getView(viewId);
        view.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
        return this;
    }

    public ViewHolder setTextColor(int textViewId, int color) {
        TextView view = getView(textViewId);
        view.setTextColor(color);
        return this;
    }


    /**
     * ΪImageView����ͼƬ
     *  setImageResource
     * @param viewId
     * @param drawableId
     * @return
     */
    public ViewHolder setImageResource(int viewId, int drawableId)
    {
        ImageView view = getView(viewId);
        view.setImageResource(drawableId);
        return this;
    }


    public ViewHolder setTextViewBackgroundColor(int viewId, int color) {
        TextView textView = getView(viewId);
        if (textView != null) {
            textView.setBackgroundColor(color);
        }
        return this;
    }



    public ViewHolder setViewBackgroundColor(int viewId, int color) {
        View textView = getView(viewId);
        if (textView != null) {
            textView.setBackgroundColor(color);
        }
        return this;
    }

    /**
     * ΪImageView����ͼƬ
     *  setImageBitmap
     * @param viewId
     * @return
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bm)
    {
        ImageView view = getView(viewId);
        view.setImageBitmap(bm);
        return this;
    }


    /**
     * ����view�Ŀɼ���
     * @param viewId
     * @param visibility
     */
    public void setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        if (view != null) {
            view.setVisibility(visibility);
        }
    }

    /**
     * Ϊview���ü����¼�
     * @param viewId
     * @param listener
     */
    public void setViewClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        if (view != null) {
            view.setOnClickListener(listener);
        }
    }

    public int getPosition(){

        return mPosition;
    }

    /**
     * ����view�ı�����ɫ
     * @param viewId
     * @param colorId
     */
    public void setViewBackground(int viewId, int colorId) {
        View view = getView(viewId);
        if (view != null) {
            view.setBackgroundColor(mContext.getResources().getColor(colorId));
        }
    }

}
