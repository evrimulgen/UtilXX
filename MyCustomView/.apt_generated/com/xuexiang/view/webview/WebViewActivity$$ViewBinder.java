// Generated code from Butter Knife. Do not modify!
package com.xuexiang.view.webview;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.ViewBinder;

public class WebViewActivity$$ViewBinder<T extends com.xuexiang.view.webview.WebViewActivity> implements ViewBinder<T> {
  @Override public void bind(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131034265, "field 'videoFullView'");
    target.videoFullView = finder.castView(view, 2131034265, "field 'videoFullView'");
    view = finder.findRequiredView(source, 2131034267, "field 'mProgressBar'");
    target.mProgressBar = finder.castView(view, 2131034267, "field 'mProgressBar'");
    view = finder.findRequiredView(source, 2131034266, "field 'webView'");
    target.webView = finder.castView(view, 2131034266, "field 'webView'");
  }

  @Override public void unbind(T target) {
    target.videoFullView = null;
    target.mProgressBar = null;
    target.webView = null;
  }
}