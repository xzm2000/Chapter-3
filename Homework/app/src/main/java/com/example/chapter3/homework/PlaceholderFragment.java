package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.airbnb.lottie.LottieAnimationView;

import java.io.InputStream;
import java.util.List;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        return inflater.inflate(R.layout.fragment_placeholder, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                LottieAnimationView animationView = getView().findViewById(R.id.animation_view);
                ListView listView = getView().findViewById(R.id.ListView);

                String data[] = {"抖音小助手", "游戏小助手", "系统消息", "陌生人消息", "王火火", "吴佳煜", "周杰伦"};
                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(), R.layout.item, data);
                listView.setAdapter(arrayAdapter);

                animationView.playAnimation();
                ObjectAnimator animator1 = ObjectAnimator.ofFloat(animationView,"alpha", (float)1.0, (float)0);
                animator1.setDuration(100);

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(listView, "alpha", (float)0, (float)1.0);
                animator2.setDuration(100);

                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animator1, animator2);
                animatorSet.start();

            }
        }, 5000);
    }
}
