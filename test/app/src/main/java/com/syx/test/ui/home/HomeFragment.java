package com.syx.test.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.syx.test.MainActivity;
import com.syx.test.R;

import java.io.File;

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


    @OnClick({R.id.swiperefreshlayout, R.id.text_home, R.id.materialbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.swiperefreshlayout:
                break;
            case R.id.text_home:
                test();
                break;
            case R.id.materialbutton:
                startActivity(new Intent(getContext(), MainActivity.class));
                break;
        }
    }

    private void test() {
        File dataDirectory = Environment.getDataDirectory();
        File test = new File(dataDirectory, "test");
        String path = test.getPath();
        boolean mkdirs = test.mkdirs();
        text.setText("result:" + mkdirs + ", pathaaaaaaaa:" + path);
    }
}