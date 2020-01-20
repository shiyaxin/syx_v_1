package com.syx.test.ui.home;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.syx.test.MainActivity;
import com.syx.test.R;

import java.io.File;
import java.io.IOException;
import java.util.Random;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class HomeFragment extends Fragment {

    @BindView(R.id.swiperefreshlayout)
    SwipeRefreshLayout swiperefreshlayout;
    @BindView(R.id.text_home)
    TextView textHome;
    @BindView(R.id.materialbutton)
    MaterialButton materialbutton;
    @BindView(R.id.text)
    TextView text;
    @BindView(R.id.materialbutton1)
    MaterialButton materialbutton1;
    @BindView(R.id.group_linearlayout)
    LinearLayout groupLinearlayout;
    private Unbinder unbinder;

    private HomeViewModel homeViewModel;
    private Handler mMainHandler;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        unbinder = ButterKnife.bind(this, root);
        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        homeViewModel.getText().observe(this, s -> textHome.setText(s));
        swiperefreshlayout.setOnRefreshListener(() -> {
            //模拟网络请求需要3000毫秒，请求完成，设置setRefreshing 为false
            mMainHandler.postDelayed(() -> swiperefreshlayout.setRefreshing(false), 2000);
        });
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMainHandler = new Handler();
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    @OnClick({R.id.swiperefreshlayout, R.id.text_home, R.id.materialbutton, R.id.materialbutton1})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.swiperefreshlayout:
                break;
            case R.id.text_home:
                //test();
                break;
            case R.id.materialbutton:
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
            case R.id.materialbutton1:
                testAnim();
                break;
        }
    }

    private void testAnim() {
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.test, null, false);
        //ViewGroup parent = materialbutton.getParent() instanceof ViewGroup ? ((ViewGroup) materialbutton.getParent()) : null;
        //parent.removeView(materialbutton);
        Random random = new Random();
        random.nextInt(256);
        //随机色
        view.setBackgroundColor(Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256)));
        groupLinearlayout.addView(view);
    }

    /**
     * android 文件存储test
     */
    private void test() {
        //系统内部存储根目录
        File dataDirectory = Environment.getDataDirectory();
        File test = new File(dataDirectory, "test");
        String path = test.getPath();
        boolean mkdirs = test.mkdirs();
        Log.i("syx", "result:" + mkdirs + ", path:" + path);
        File shiyaxian = new File(getContext().getExternalFilesDir(null), "shiyaxian.txt");
        try {
            boolean newFile = shiyaxian.createNewFile();
            Log.i("syx", "createNewFile:" + shiyaxian.getPath());
        } catch (IOException e) {
            Log.i("syx", "createNewFile:" + e.toString());
        }
        //createNewFile:/data/user/0/com.syx.test/files/shiyaxian.txt
        //saveFile:     /storage/emulated/0/Android/data/com.sinosun.tchats/files/.cache/hd/27144.png
    }
}