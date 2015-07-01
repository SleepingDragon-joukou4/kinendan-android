// Generated code from Butter Knife. Do not modify!
package com.sleepingdragon.joko4nen.nosmoke.home;

import android.view.View;
import butterknife.ButterKnife.Finder;
import butterknife.ButterKnife.Injector;

public class HomeActivity$$ViewInjector<T extends com.sleepingdragon.joko4nen.nosmoke.home.HomeActivity> implements Injector<T> {
  @Override public void inject(final Finder finder, final T target, Object source) {
    View view;
    view = finder.findRequiredView(source, 2131558501, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(finder.<android.widget.Button>castParam(p0, "doClick", 0, "onClick", 0));
        }
      });
    view = finder.findRequiredView(source, 2131558503, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(finder.<android.widget.Button>castParam(p0, "doClick", 0, "onClick", 0));
        }
      });
    view = finder.findRequiredView(source, 2131558502, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(finder.<android.widget.Button>castParam(p0, "doClick", 0, "onClick", 0));
        }
      });
    view = finder.findRequiredView(source, 2131558504, "method 'onClick'");
    view.setOnClickListener(
      new butterknife.internal.DebouncingOnClickListener() {
        @Override public void doClick(
          android.view.View p0
        ) {
          target.onClick(finder.<android.widget.Button>castParam(p0, "doClick", 0, "onClick", 0));
        }
      });
  }

  @Override public void reset(T target) {
  }
}
