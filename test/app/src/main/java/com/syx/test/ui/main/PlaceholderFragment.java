package com.syx.test.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.syx.test.FullscreenActivity;
import com.syx.test.Main2Activity;
import com.syx.test.MainActivity;
import com.syx.test.MainActivity2;
import com.syx.test.MapsActivity;
import com.syx.test.NewListActivity;
import com.syx.test.R;
import com.syx.test.ScrollingActivity;
import com.syx.test.SettingsActivity;
import com.syx.test.ui.login.LoginActivity;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaceholderFragment extends Fragment {

    private static final String ARG_SECTION_NUMBER = "section_number";
    @BindView(R.id.section_label)
    TextView sectionLabel;
    @BindView(R.id.constraintLayout)
    ConstraintLayout constraintLayout;
    @BindView(R.id.materialbutton)
    MaterialButton materialbutton;

    private PageViewModel pageViewModel;
    private Class activity;

    public static PlaceholderFragment newInstance(int index) {
        PlaceholderFragment fragment = new PlaceholderFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_SECTION_NUMBER, index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel.class);
        int index = 1;
        if (getArguments() != null) {
            index = getArguments().getInt(ARG_SECTION_NUMBER);
        }
        pageViewModel.setIndex(index);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.bind(this, root);
        pageViewModel.getText().observe(this, text -> sectionLabel.setText(text));
        materialbutton.setText(getActivityName());
        return root;
    }

    @OnClick({R.id.section_label, R.id.constraintLayout, R.id.materialbutton})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.section_label:
                break;
            case R.id.constraintLayout:
                break;
            case R.id.materialbutton:
                if (activity != null) {
                    startActivity(new Intent(getContext(), activity));
                }
                break;
        }
    }

    private String getActivityName() {
        activity = null;
        switch (pageViewModel.getIndex()) {
            case 1:
                activity = SettingsActivity.class;
                break;
            case 2:
                activity = ScrollingActivity.class;
                break;
            case 3:
                activity = NewListActivity.class;
                break;
            case 4:
                activity = FullscreenActivity.class;
                break;
            case 5:
                activity = Main2Activity.class;
                break;
            case 6:
                activity = LoginActivity.class;
                break;
            case 7:
                activity = MapsActivity.class;
                break;
            case 8:
                activity = MainActivity2.class;
                break;
            case 9:
                activity = SettingsActivity.class;
                break;
            case 10:
                activity = SettingsActivity.class;
                break;
            case 11:
                activity = SettingsActivity.class;
                break;
            case 12:
                activity = SettingsActivity.class;
                break;
            case 13:
                activity = SettingsActivity.class;
                break;
            case 14:
                activity = SettingsActivity.class;
                break;
            case 15:
                activity = SettingsActivity.class;
                break;
            case 16:
                activity = SettingsActivity.class;
                break;
            default:
                break;
        }
        return activity == null ? "" : activity.getSimpleName();
    }
}