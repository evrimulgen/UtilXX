package com.xuexiang.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import java.util.List;

import com.xuexiang.util.resource.RUtils;

/**
 * �����ɲ�
 */
public class GuideView extends RelativeLayout implements ViewTreeObserver.OnGlobalLayoutListener {
    private final String TAG = getClass().getSimpleName();
    private Context mContext;
    private List<View> mViews;
    private boolean first = true;
    /**
     * targetViewǰ׺��SHOW_GUIDE_PREFIX + targetView.getId()��Ϊ������SP�ļ���key��
     */
    private static final String SHOW_GUIDE_PREFIX = "show_guide_on_view_";
    /**
     * GuideView ƫ����
     */
    private int offsetX, offsetY;
    /**
     * targetView ������Բ�뾶
     */
    private int radius;
    /**
     * ��Ҫ��ʾ��ʾ��Ϣ��View
     */
    private View targetView;
    /**
     * �Զ���View
     */
    private View customGuideView;
    /**
     * ͸��Բ�λ���
     */
    private Paint mCirclePaint;
    /**
     * ����ɫ����
     */
    private Paint mBackgroundPaint;
    /**
     * targetView�Ƿ��Ѳ���
     */
    private boolean isMeasured;
    /**
     * targetViewԲ��
     */
    private int[] center;
    /**
     * ��ͼ���ģʽ
     */
    private PorterDuffXfermode porterDuffXfermode;
    /**
     * ����ǰ��bitmap
     */
    private Bitmap bitmap;
    /**
     * ����ɫ��͸���ȣ���ʽ #aarrggbb
     */
    private int backgroundColor;
    /**
     * Canvas,����bitmap
     */
    private Canvas temp;
    /**
     * �����targetView��λ��.��target���Ǹ�����
     */
    private Direction direction;

    /**
     * ��״
     */
    private MyShape myShape;
    /**
     * targetView���Ͻ�����
     */
    private int[] location;
    private boolean onClickExit;
    private OnClickCallback onclickListener;
    private RelativeLayout guideViewLayout;

    public void restoreState() {
        offsetX = offsetY = 0;
        radius = 0;
        mCirclePaint = null;
        mBackgroundPaint = null;
        isMeasured = false;
        center = null;
        porterDuffXfermode = null;
        bitmap = null;
        needDraw = true;
        //        backgroundColor = Color.parseColor("#00000000");
        temp = null;
        //        direction = null;

    }

    public int[] getLocation() {
        return location;
    }

    public void setLocation(int[] location) {
        this.location = location;
    }

    public GuideView(Context context) {
        super(context);
        this.mContext = context;
        init();
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setOffsetX(int offsetX) {
        this.offsetX = offsetX;
    }

    public void setOffsetY(int offsetY) {
        this.offsetY = offsetY;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void setShape(MyShape shape) {
        this.myShape = shape;
    }

    public void setCustomGuideView(View customGuideView) {
        this.customGuideView = customGuideView;
        if (!first) {
            restoreState();
        }
    }

    public void setBgColor(int background_color) {
        this.backgroundColor = background_color;
    }

    public View getTargetView() {
        return targetView;
    }

    public void setTargetView(View targetView) {
        this.targetView = targetView;
        //        restoreState();
        if (!first) {
            //            guideViewLayout.removeAllViews();
        }
    }

    private void init() {
    }

    public void showOnce() {
        if (targetView != null) {
            mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE).edit().putBoolean(generateUniqId(targetView), true).commit();
        }
    }

    private boolean hasShown() {
        if (targetView == null)
            return true;
        return mContext.getSharedPreferences(TAG, Context.MODE_PRIVATE).getBoolean(generateUniqId(targetView), false);
    }

    private String generateUniqId(View v) {
        return SHOW_GUIDE_PREFIX + v.getId();
    }

    public int[] getCenter() {
        return center;
    }

    public void setCenter(int[] center) {
        this.center = center;
    }

    @SuppressLint("NewApi")
	public void hide() {
        if (customGuideView != null) {
            targetView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            this.removeAllViews();
            ((FrameLayout) ((Activity) mContext).getWindow().getDecorView()).removeView(this);
            restoreState();
        }
    }

    public void show() {
        if (hasShown())
            return;

        if (targetView != null) {
            targetView.getViewTreeObserver().addOnGlobalLayoutListener(this);
        }

        this.setBackgroundResource(RUtils.getColor(mContext, "transparent"));

        ((FrameLayout) ((Activity) mContext).getWindow().getDecorView()).addView(this);
        first = false;
    }

    /**
     * �����ʾ���֣�λ����targetView���±�
     * ����Ļ���ڣ�����ɲ㣬�ɲ�����ܱ�����͸��Բ�Σ�Բ���±߻���˵������
     */
    private void createGuideView() {
        LayoutParams guideViewParams;
        guideViewParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        guideViewParams.setMargins(0, center[1] + radius + 10, 0, 0);

        if (customGuideView != null) {

            //            LayoutParams guideViewParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            if (direction != null) {
                int width = this.getWidth();
                int height = this.getHeight();

                int left = center[0] - radius;
                int right = center[0] + radius;
                int top = center[1] - radius;
                int bottom = center[1] + radius;
                switch (direction) {
                    case TOP:
                        this.setGravity(Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL);
                        guideViewParams.setMargins(offsetX, offsetY - height + top, -offsetX, height - top - offsetY);
                        break;
                    case LEFT:
                        this.setGravity(Gravity.RIGHT);
                        guideViewParams.setMargins(offsetX - width + left, top + offsetY, width - left - offsetX, -top - offsetY);
                        break;
                    case BOTTOM:
                        this.setGravity(Gravity.CENTER_HORIZONTAL);
                        guideViewParams.setMargins(offsetX, bottom + offsetY, -offsetX, -bottom - offsetY);
                        break;
                    case RIGHT:
                        guideViewParams.setMargins(right + offsetX, top + offsetY, -right - offsetX, -top - offsetY);
                        break;
                    case LEFT_TOP:
                        this.setGravity(Gravity.RIGHT | Gravity.BOTTOM);
                        guideViewParams.setMargins(offsetX - width + left, offsetY - height + top, width - left - offsetX, height - top - offsetY);
                        break;
                    case LEFT_BOTTOM:
                        this.setGravity(Gravity.RIGHT);
                        guideViewParams.setMargins(offsetX - width + left, bottom + offsetY, width - left - offsetX, -bottom - offsetY);
                        break;
                    case RIGHT_TOP:
                        this.setGravity(Gravity.BOTTOM);
                        guideViewParams.setMargins(right + offsetX, offsetY - height + top, -right - offsetX, height - top - offsetY);
                        break;
                    case RIGHT_BOTTOM:
                        guideViewParams.setMargins(right + offsetX, bottom + offsetY, -right - offsetX, -top - offsetY);
                        break;
                }
            } else {
                guideViewParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                guideViewParams.setMargins(offsetX, offsetY, -offsetX, -offsetY);
            }

            //            guideViewLayout.addView(customGuideView);

            this.addView(customGuideView, guideViewParams);
        }
    }

    /**
     * ���targetView �Ŀ�ߣ����δ���������أ�-1�� -1��
     *
     * @return
     */
    private int[] getTargetViewSize() {
        int[] location = {-1, -1};
        if (isMeasured) {
            location[0] = targetView.getWidth();
            location[1] = targetView.getHeight();
        }
        return location;
    }

    /**
     * ���targetView �İ뾶
     *
     * @return
     */
    private int getTargetViewRadius() {
        if (isMeasured) {
            int[] size = getTargetViewSize();
            int x = size[0];
            int y = size[1];

            return (int) (Math.sqrt(x * x + y * y) / 2);
        }
        return -1;
    }

    boolean needDraw = true;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isMeasured)
            return;

        if (targetView == null)
            return;

        drawBackground(canvas);

    }

    private void drawBackground(Canvas canvas) {
        needDraw = false;
        // �Ȼ���bitmap���ٽ�bitmap���Ƶ���Ļ
        bitmap = Bitmap.createBitmap(canvas.getWidth(), canvas.getHeight(), Bitmap.Config.ARGB_8888);
        temp = new Canvas(bitmap);

        // ��������
        Paint bgPaint = new Paint();
        if (backgroundColor != 0)
            bgPaint.setColor(backgroundColor);
        else
            bgPaint.setColor(getResources().getColor(RUtils.getColor(mContext, "shadow_guidview")));

        // ������Ļ����
        temp.drawRect(0, 0, temp.getWidth(), temp.getHeight(), bgPaint);

        // targetView ��͸��Բ�λ���
        if (mCirclePaint == null)
            mCirclePaint = new Paint();
        porterDuffXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);// ����CLEAR
        mCirclePaint.setXfermode(porterDuffXfermode);
        mCirclePaint.setAntiAlias(true);

        if (myShape != null) {
            RectF oval = new RectF();
            switch (myShape) {
                case CIRCULAR://Բ��
                    temp.drawCircle(center[0], center[1], radius, mCirclePaint);//����Բ��
                    break;
                case ELLIPSE://��Բ
                    //RectF����
                    oval.left = center[0] - 150;                              //���
                    oval.top = center[1] - 50;                                   //�ϱ�
                    oval.right = center[0] + 150;                             //�ұ�
                    oval.bottom = center[1] + 50;                                //�±�
                    temp.drawOval(oval, mCirclePaint);                   //������Բ
                    break;
                case RECTANGULAR://Բ�Ǿ���
                    //RectF����
                    oval.left = center[0] - 150;                              //���
                    oval.top = center[1] - 50;                                   //�ϱ�
                    oval.right = center[0] + 150;                             //�ұ�
                    oval.bottom = center[1] + 50;                                //�±�
                    temp.drawRoundRect(oval, radius, radius, mCirclePaint);                   //����Բ�Ǿ���
                    break;
            }
        } else {
            temp.drawCircle(center[0], center[1], radius, mCirclePaint);//����Բ��
        }

        // ���Ƶ���Ļ
        canvas.drawBitmap(bitmap, 0, 0, bgPaint);
        bitmap.recycle();
    }

    public void setOnClickExit(boolean onClickExit) {
        this.onClickExit = onClickExit;
    }

    public void setOnclickListener(OnClickCallback onclickListener) {
        this.onclickListener = onclickListener;
    }

    private void setClickInfo() {
        final boolean exit = onClickExit;
        setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onclickListener != null) {
                    onclickListener.onClickedGuideView();
                }
                if (exit) {
                    hide();
                }
            }
        });
    }

    @Override
    public void onGlobalLayout() {
        if (isMeasured)
            return;
        if (targetView.getHeight() > 0 && targetView.getWidth() > 0) {
            isMeasured = true;
        }

        // ��ȡtargetView����������
        if (center == null) {
            // ��ȡ���Ͻ�����
            location = new int[2];
            targetView.getLocationInWindow(location);
            center = new int[2];
            // ��ȡ��������
            center[0] = location[0] + targetView.getWidth() / 2;
            center[1] = location[1] + targetView.getHeight() / 2;
        }
        // ��ȡtargetView����Բ�뾶
        if (radius == 0) {
            radius = getTargetViewRadius();
        }
        // ���GuideView
        createGuideView();
    }

    /**
     * ����GuideView�����targetView�ķ�λ�������֡���������Ĭ����targetView�·�
     */
    public enum Direction {
        LEFT, TOP, RIGHT, BOTTOM,
        LEFT_TOP, LEFT_BOTTOM,
        RIGHT_TOP, RIGHT_BOTTOM
    }

    /**
     * ����Ŀ��ؼ�����״����3�֡�Բ�Σ���Բ����Բ�ǵľ��Σ���������Բ�Ǵ�С������������Ĭ����Բ��
     */
    public enum MyShape {
        CIRCULAR, ELLIPSE, RECTANGULAR
    }

    /**
     * GuideView���Callback
     */
    public interface OnClickCallback {
        void onClickedGuideView();
    }

    public static class Builder {
        static GuideView guiderView;
        static Builder instance = new Builder();
        Context mContext;

        private Builder() {
        }

        public Builder(Context ctx) {
            mContext = ctx;
        }

        public static Builder newInstance(Context ctx) {
            guiderView = new GuideView(ctx);
            return instance;
        }

        public Builder setTargetView(View target) {
            guiderView.setTargetView(target);
            return instance;
        }

        public Builder setBgColor(int color) {
            guiderView.setBgColor(color);
            return instance;
        }

        public Builder setDirction(Direction dir) {
            guiderView.setDirection(dir);
            return instance;
        }

        public Builder setShape(MyShape shape) {
            guiderView.setShape(shape);
            return instance;
        }

        public Builder setOffset(int x, int y) {
            guiderView.setOffsetX(x);
            guiderView.setOffsetY(y);
            return instance;
        }

        public Builder setRadius(int radius) {
            guiderView.setRadius(radius);
            return instance;
        }

        public Builder setCustomGuideView(View view) {
            guiderView.setCustomGuideView(view);
            return instance;
        }

        public Builder setCenter(int X, int Y) {
            guiderView.setCenter(new int[]{X, Y});
            return instance;
        }

        public Builder showOnce() {
            guiderView.showOnce();
            return instance;
        }

        public GuideView build() {
            guiderView.setClickInfo();
            return guiderView;
        }

        public Builder setOnclickExit(boolean onclickExit) {
            guiderView.setOnClickExit(onclickExit);
            return instance;
        }

        public Builder setOnclickListener(final OnClickCallback callback) {
            guiderView.setOnclickListener(callback);
            return instance;
        }
    }
}
