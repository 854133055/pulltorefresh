/*******************************************************************************
 * Copyright 2011, 2012 zitian.zhang.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *******************************************************************************/
package com.handmark.pulltorefresh.library.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.widget.ImageView.ScaleType;

import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.pulltorefresh.hank.pulltorefreshdemo.R;

/**
 * 
 * @author zitian.zhang
 * @since 2013-1-22 下午4:35:03
 * 
 */
@SuppressLint("ViewConstructor")
public class AnimationDrawableLoadingLayout extends LoadingLayout {

    private AnimationDrawable mImageDrawable;

    public AnimationDrawableLoadingLayout(Context context, final Mode mode, final Orientation scrollDirection,
            TypedArray attrs) {
        super(context, mode, scrollDirection, attrs);
    }

    @Override
    protected void onLoadingDrawableSet(Drawable imageDrawable) {
        if (null != imageDrawable && imageDrawable instanceof AnimationDrawable) {
            this.mImageDrawable = (AnimationDrawable) imageDrawable;
            mHeaderImage.setScaleType(ScaleType.CENTER_INSIDE);
        }
    }

    @Override
    protected void onPullImpl(float scaleOfLayout) {

    }

    @Override
    protected void pullToRefreshImpl() {
    }

    @Override
    protected void refreshingImpl() {
        if (mImageDrawable != null) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    mImageDrawable.start();
                }
            });
        }
    }

    @Override
    protected void releaseToRefreshImpl() {
    }

    @Override
    protected void resetImpl() {
        if (mImageDrawable != null) {
            this.post(new Runnable() {
                @Override
                public void run() {
                    mImageDrawable.stop();
                    mImageDrawable.setVisible(true, true);
                }
            });
        }
    }

    @Override
    protected int getDefaultDrawableResId() {
        return R.drawable.pub_fw_loading;
    }

}
