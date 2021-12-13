package com.rustfisher.tutorial2020.lottie;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.rustfisher.baselib.AbsActivity;
import com.rustfisher.tutorial2020.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Lottie使用示例
 * 2020-11-17
 */
public class LottieDemo1 extends AbsActivity implements View.OnClickListener {

    private LinearLayout mRoot;
    private LottieAnimationView mLa1;
    private LottieAnimationView mLa2;
    private LottieAnimationView mLa3;
    private LottieAnimationView mLa4;
    private Button mBtn4;

    private LottieAnimationView mLa5;
    private Button mBtn5;
    private LottieAnimationView mLa6;
    private Button mBtn6;
    private LottieAnimationView mLa7;
    private Button mBtn7;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_lottie_demo1);
        mRoot = findViewById(R.id.root_field);
        mLa1 = findViewById(R.id.la1);
        mLa2 = findViewById(R.id.la2);
        mLa3 = findViewById(R.id.la3);
        mLa4 = findViewById(R.id.la4);
        mLa5 = findViewById(R.id.la5);
        mLa6 = findViewById(R.id.la6);
        mLa7 = findViewById(R.id.la7);

        mBtn4 = findViewById(R.id.btn4);
        mBtn5 = findViewById(R.id.btn5);
        mBtn6 = findViewById(R.id.btn6);
        mBtn7 = findViewById(R.id.btn7);

        mLa4.pauseAnimation();
        mLa4.setSpeed(3f);
        mBtn4 = findViewById(R.id.btn4);

        mLa2.setAnimation("star_lottie.json");
        mLa2.setRepeatCount(LottieDrawable.INFINITE);
        mLa2.setSpeed(1.5f);

        mLa3.setSpeed(2f);
        mLa3.pauseAnimation();

        findViewById(R.id.btn1).setOnClickListener(this);
        findViewById(R.id.btn2).setOnClickListener(this);
        findViewById(R.id.btn3).setOnClickListener(this);
        findViewById(R.id.btn4).setOnClickListener(this);
        findViewById(R.id.btn5).setOnClickListener(this);
        findViewById(R.id.btn6).setOnClickListener(this);
        findViewById(R.id.btn7).setOnClickListener(this);
        findViewById(R.id.reset_la4).setOnClickListener(this);
        findViewById(R.id.reset_la5).setOnClickListener(this);
        findViewById(R.id.reset_la6).setOnClickListener(this);
        findViewById(R.id.reset_la7).setOnClickListener(this);

        RecyclerView recyclerView = findViewById(R.id.re_view);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setAdapter(adapter);
        try {
            List<String> list = new ArrayList<>();
            for (String s : getAssets().list("lottie")) {
                list.add("lottie/" + s);
            }
            adapter.setDataList(list);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e(TAG, "onCreate: ", e);
        }
//        initExtraLottie1();
    }

    private Adapter adapter = new Adapter();

    private void initExtraLottie1() {
        final LottieAnimationView view = new LottieAnimationView(this);
        view.setRepeatCount(LottieDrawable.RESTART);
        view.setRepeatMode(LottieDrawable.RESTART);
        view.setRepeatCount(0); // 就播放一次，不重复
        view.setAnimation("star_lottie.json");
        view.setMinimumWidth(100);
        view.setMinimumHeight(100);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                50);
        view.setLayoutParams(lp);
        mRoot.addView(view, lp);

        LinearLayout.LayoutParams btnLp = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        Button eBtn1 = new Button(this);
        eBtn1.setLayoutParams(btnLp);
        eBtn1.setText("额外播放1");
        eBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.playAnimation();
            }
        });
        mRoot.addView(eBtn1, btnLp);
    }

    private void replay(LottieAnimationView animationView) {
        animationView.playAnimation();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                replay(mLa1);
                break;
            case R.id.btn2:
                replay(mLa2);
                break;
            case R.id.btn3:
                replay(mLa3);
                break;
            case R.id.reset_la4:
                resetBtnAndAni(mBtn4, mLa4);
                break;
            case R.id.reset_la5:
                resetBtnAndAni(mBtn5, mLa5);
                break;
            case R.id.reset_la6:
                resetBtnAndAni(mBtn6, mLa6);
                break;
            case R.id.reset_la7:
                resetBtnAndAni(mBtn7, mLa7);
                break;
            case R.id.btn4:
                mBtn4.setVisibility(View.INVISIBLE);
                mLa4.playAnimation();
                break;
            case R.id.btn5:
                mBtn5.setVisibility(View.INVISIBLE);
                mLa5.playAnimation();
                break;
            case R.id.btn6:
                mBtn6.setVisibility(View.INVISIBLE);
                mLa6.playAnimation();
                break;
            case R.id.btn7:
                mBtn7.setVisibility(View.INVISIBLE);
                mLa7.playAnimation();
                break;
        }
    }

    private void resetBtnAndAni(Button btn, LottieAnimationView animationView) {
        btn.setVisibility(View.VISIBLE);
        animationView.setProgress(0);
        animationView.pauseAnimation();
    }

    public static class Adapter extends RecyclerView.Adapter<VH> {
        private static final String TAG = "rustApp";

        private List<String> dataList = new ArrayList<>();

        public void setDataList(List<String> list) {
            if (list != null) {
                dataList = list;
            } else {
                this.dataList = new ArrayList<>();
            }
            Log.d("rustApp", "setDataList: " + dataList);
            notifyDataSetChanged();
        }

        @NonNull
        @Override
        public VH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return new VH(LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.lottie_item, parent, false));
        }

        @Override
        public void onBindViewHolder(@NonNull VH holder, int position) {
            final String path = dataList.get(position);
            Log.d(TAG, "onBindViewHolder: " + path);
            holder.lottieAnimationView.setAnimation(path);
            holder.tv1.setText(path);
        }

        @Override
        public int getItemCount() {
            return dataList.size();
        }
    }

    public static class VH extends RecyclerView.ViewHolder {
        LottieAnimationView lottieAnimationView;
        TextView tv1;

        public VH(@NonNull View itemView) {
            super(itemView);
            lottieAnimationView = itemView.findViewById(R.id.la);
            tv1 = itemView.findViewById(R.id.tv1);

            lottieAnimationView.setImageAssetsFolder("lottie");
        }
    }
}