/*
 * Copyright (c) Joaquim Ley 2016. All Rights Reserved.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.xuexiang.view.faboptions;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.view.SupportMenuInflater;
import android.support.v7.view.menu.MenuBuilder;
import android.transition.ChangeBounds;
import android.transition.ChangeTransform;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.xuexiang.R;

/**
 * FabOptions component
 */
public class FabOptions extends FrameLayout implements View.OnClickListener {

    private static final int NO_DIMENSION = 0;
    private static final long CLOSE_MORPH_TRANSFORM_DURATION = 70;

    private boolean mIsOpen;
    private View.OnClickListener mListener;

    private Menu mMenu; 
    private FloatingActionButton mFab;

    private View mBackground;
    private View mSeparator;
    private FabOptionsButtonContainer mButtonContainer;

    public FabOptions(Context context) {
        this(context, null, 0);
    }

    public FabOptions(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabOptions(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews(context);
        if (attrs != null) {
            inflateButtonsFromAttrs(context, attrs);
        }
    }

    private void initViews(Context context) {
        inflate(context, R.layout.faboptions_layout, this);
        mIsOpen = false;
        mBackground = findViewById(R.id.background);
        mFab = (FloatingActionButton) findViewById(R.id.faboptions_fab);
        mFab.setOnClickListener(this);
        mButtonContainer = (FabOptionsButtonContainer) findViewById(R.id.button_container);
    }

    private void inflateButtonsFromAttrs(Context context, AttributeSet attrs) {
        TypedArray attributes = context.getTheme().obtainStyledAttributes(attrs, R.styleable.FabOptions, 0, 0);
        if (attributes.hasValue(R.styleable.FabOptions_button_menu)) {
            setButtonsMenu(context, attributes.getResourceId(R.styleable.FabOptions_button_menu, 0));
        }
    }

    public void setButtonsMenu(Context context, int menuId) {
        mMenu = new MenuBuilder(context);
        SupportMenuInflater menuInf = new SupportMenuInflater(context);
        menuInf.inflate(menuId, mMenu);

        addButtonsFromMenu(context, mMenu);
        mSeparator = mButtonContainer.addSeparator(context);
        close();
    }

    private void addButtonsFromMenu(Context context, Menu menu) {
        for (int i = 0; i < menu.size(); i++) {
            MenuItem menuItem = menu.getItem(i);
            addButton(context, menuItem);
        }
    }

    private void addButton(Context context, MenuItem menuItem) {
       ImageView button = mButtonContainer.addButton(context, menuItem.getItemId(),
                menuItem.getTitle(), menuItem.getIcon());
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if (viewId == R.id.faboptions_fab) {
            if (mIsOpen) {
                close();
            } else {
                open();
            }
        } else {
            if (mListener != null) {
                mListener.onClick(v);
                close();
            }
        }
    }

    public void setOnClickListener(View.OnClickListener listener) {
        mListener = listener;
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if (mSeparator != null) {
            ViewGroup.LayoutParams separatorLayoutParams = mSeparator.getLayoutParams();
            separatorLayoutParams.width = mFab.getMeasuredWidth();
            separatorLayoutParams.height = mFab.getMeasuredHeight();
            mSeparator.setLayoutParams(separatorLayoutParams);
        }

        if (mIsOpen) {
            ViewGroup.LayoutParams backgroundLayoutParams = mBackground.getLayoutParams();
            backgroundLayoutParams.width = mButtonContainer.getMeasuredWidth();
            backgroundLayoutParams.height = mButtonContainer.getMeasuredHeight();
            mBackground.setLayoutParams(backgroundLayoutParams);
        }
    }

    private void open() {
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.faboptions_ic_menu_animatable, null);
        mFab.setImageDrawable(drawable);
        drawable.start();
        TransitionManager.beginDelayedTransition(this, new OpenMorphTransition(mButtonContainer));
        animateButtons(true);
        animateBackground(true);
        mIsOpen = true;
    }

    private void close() {
        AnimatedVectorDrawable drawable = (AnimatedVectorDrawable) getResources().getDrawable(R.drawable.faboptions_ic_close_animatable, null);
        mFab.setImageDrawable(drawable);
        drawable.start();
        TransitionManager.beginDelayedTransition(this, new CloseMorphTransition(mButtonContainer));
        animateButtons(false);
        animateBackground(false);
        mIsOpen = false;
    }

    private void animateBackground(final boolean isOpen) {
        ViewGroup.LayoutParams backgroundLayoutParams = mBackground.getLayoutParams();
        backgroundLayoutParams.width = isOpen ? mButtonContainer.getMeasuredWidth() : NO_DIMENSION;
        mBackground.setLayoutParams(backgroundLayoutParams);
    }

    private void animateButtons(boolean isOpen) {
        for (int i = 0; i < mButtonContainer.getChildCount(); i++) {
            mButtonContainer.getChildAt(i).setScaleX(isOpen ? 1 : 0);
            mButtonContainer.getChildAt(i).setScaleY(isOpen ? 1 : 0);
        }
    }

    public boolean isOpen() {
        return mIsOpen;
    }

    private static class OpenMorphTransition extends TransitionSet {
        OpenMorphTransition(ViewGroup viewGroup) {

            ChangeBounds changeBound = new ChangeBounds();
            changeBound.excludeChildren(R.id.button_container, true);

            ChangeTransform changeTransform = new ChangeTransform();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeTransform.addTarget(viewGroup.getChildAt(i));
            }
            addTransition(changeBound);
            addTransition(changeTransform);
            setOrdering(TransitionSet.ORDERING_SEQUENTIAL);
        }
    }

    private static class CloseMorphTransition extends TransitionSet {
        CloseMorphTransition(ViewGroup viewGroup) {
            ChangeBounds changeBound = new ChangeBounds();
            changeBound.excludeChildren(R.id.button_container, true);

            ChangeTransform changeTransform = new ChangeTransform();
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                changeTransform.addTarget(viewGroup.getChildAt(i));
            }
            changeTransform.setDuration(CLOSE_MORPH_TRANSFORM_DURATION);
            addTransition(changeTransform);
            addTransition(changeBound);
            setOrdering(TransitionSet.ORDERING_TOGETHER);
        }
    }
}