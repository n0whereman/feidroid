// Generated code from Butter Knife. Do not modify!
package pete.android.study.ui.fragments;

import android.view.View;
import butterknife.ButterKnife.Finder;

public class DetailFragment$$ViewInjector {
  public static void inject(Finder finder, final pete.android.study.ui.fragments.DetailFragment target, Object source) {
    View view;
    view = finder.findById(source, 2131361798);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361798' for field 'mAppIcon' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mAppIcon = (android.widget.ImageView) view;
    view = finder.findById(source, 2131361800);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361800' for field 'mPackageName' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mPackageName = (android.widget.TextView) view;
    view = finder.findById(source, 2131361808);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361808' for field 'mSourceDir' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mSourceDir = (android.widget.TextView) view;
    view = finder.findById(source, 2131361809);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361809' for field 'mDataDir' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mDataDir = (android.widget.TextView) view;
    view = finder.findById(source, 2131361805);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361805' for field 'mSdkVersion' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mSdkVersion = (android.widget.TextView) view;
    view = finder.findById(source, 2131361807);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361807' for field 'mLastUpdate' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mLastUpdate = (android.widget.TextView) view;
    view = finder.findById(source, 2131361811);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361811' for field 'mAppFlags' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mAppFlags = (android.widget.TextView) view;
    view = finder.findById(source, 2131361810);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361810' for field 'mAppPermissions' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mAppPermissions = (android.widget.TextView) view;
    view = finder.findById(source, 2131361804);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361804' for field 'mVersionCode' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mVersionCode = (android.widget.TextView) view;
    view = finder.findById(source, 2131361812);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361812' for field 'mySpinner' was not found. If this view is optional add '@Optional' annotation.");
    }
//    target.mySpinner = (android.widget.Spinner) view;
//    view = finder.findById(source, 2131361801);
//    if (view == null) {
//      throw new IllegalStateException("Required view with id '2131361801' for field 'mClassName' was not found. If this view is optional add '@Optional' annotation.");
//    }
    target.mClassName = (android.widget.TextView) view;
    view = finder.findById(source, 2131361806);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361806' for field 'mInstallDate' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mInstallDate = (android.widget.TextView) view;
    view = finder.findById(source, 2131361803);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361803' for field 'mVersionName' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mVersionName = (android.widget.TextView) view;
    view = finder.findById(source, 2131361802);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361802' for field 'mProcessName' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mProcessName = (android.widget.TextView) view;
    view = finder.findById(source, 2131361799);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361799' for field 'mAppName' was not found. If this view is optional add '@Optional' annotation.");
    }
    target.mAppName = (android.widget.TextView) view;
    view = finder.findById(source, 2131361813);
    if (view == null) {
      throw new IllegalStateException("Required view with id '2131361813' for method 'onAnalisisClick' was not found. If this view is optional add '@Optional' annotation.");
    }
    view.setOnClickListener(
      new android.view.View.OnClickListener() {
        @Override public void onClick(
          android.view.View p0
        ) {
          target.onAnalisisClick();
        }
      });
  }

  public static void reset(pete.android.study.ui.fragments.DetailFragment target) {
    target.mAppIcon = null;
    target.mPackageName = null;
    target.mSourceDir = null;
    target.mDataDir = null;
    target.mSdkVersion = null;
    target.mLastUpdate = null;
    target.mAppFlags = null;
    target.mAppPermissions = null;
    target.mVersionCode = null;
    //target.mySpinner = null;
    target.mClassName = null;
    target.mInstallDate = null;
    target.mVersionName = null;
    target.mProcessName = null;
    target.mAppName = null;
  }
}
