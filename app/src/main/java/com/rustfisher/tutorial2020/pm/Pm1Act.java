package com.rustfisher.tutorial2020.pm;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActPm1Binding;

import java.util.ArrayList;
import java.util.List;

public class Pm1Act extends AbsActivity {

    private ActPm1Binding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.act_pm1);
        PackageManager pm = getPackageManager();
        for (ResolveInfo info : scanApp()) {
            try {
                PackageInfo pi = pm.getPackageInfo(info.activityInfo.packageName, PackageManager.GET_META_DATA);
                Log.d(TAG, "app: "
                        + " pkg: " + pi.packageName
                        + ", v: " + pi.versionName + ", code: " + pi.versionCode);
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                Log.e(TAG, "onCreate: ", e);
            }
        }
    }

    private List<ResolveInfo> scanApp() {
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> list = new ArrayList<>();
        for (ResolveInfo info : getPackageManager().queryIntentActivities(mainIntent, 0)) {
            if (info.activityInfo.packageName.equals(getPackageName())) {
                continue; // 跳过自己
            }
            list.add(info);
//            Log.d(TAG, "scanApp: " + info);
        }
        return list;
    }
}
