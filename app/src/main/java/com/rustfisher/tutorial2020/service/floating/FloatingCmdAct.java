package com.rustfisher.tutorial2020.service.floating;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.rustfisher.tutorial2020.R;
import com.rustfisher.tutorial2020.databinding.ActFloatingCmdBinding;

/**
 * 启动服务前的界面
 *
 * @author an.rustfisher.com
 * @date 2022-01-05 14:57
 */
public class FloatingCmdAct extends AppCompatActivity {
    private ActFloatingCmdBinding mBinding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.act_floating_cmd);

        mBinding.settingWindowBtn.setOnClickListener(v -> {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())));
            } else {
                Toast.makeText(getApplicationContext(), "API < " + android.os.Build.VERSION_CODES.M, Toast.LENGTH_SHORT).show();
            }
        });
        mBinding.startBtn.setOnClickListener(v -> {
            startService(new Intent(getApplicationContext(), FloatingWindowService.class));
            Toast.makeText(getApplicationContext(), "启动服务", Toast.LENGTH_SHORT).show();
        });
        mBinding.endBtn.setOnClickListener(v -> {
            stopService(new Intent(getApplicationContext(), FloatingWindowService.class));
            Toast.makeText(getApplicationContext(), "停止服务", Toast.LENGTH_SHORT).show();
        });

        if (!checkOverlayDisplayPermission()) {
            Toast.makeText(getApplicationContext(), "请允许应用显示悬浮窗", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean checkOverlayDisplayPermission() {
        // API23以后需要检查权限
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M) {
            return Settings.canDrawOverlays(this);
        } else {
            return true;
        }
    }
}
