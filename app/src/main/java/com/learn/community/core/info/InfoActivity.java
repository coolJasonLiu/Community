package com.learn.community.core.info;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.blankj.utilcode.util.ToastUtils;
import com.learn.community.R;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.search.SearchListAdapter;
import com.learn.community.core.search.SearchVM;
import com.learn.community.databinding.SearchListActivityBinding;
import com.learn.community.databinding.UniversityInformationBinding;

public class InfoActivity extends BaseActivity<UniversityInformationBinding, SearchVM> {
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        binding.cover.setImageResource(R.drawable.university_information);
        binding.name.setText("The University of Liverpool");
        binding.description.setText("The University of Liverpool, abbreviated as UoL or Liv Uni, was established in 1881. It is located in Liverpool, one of the core cities in the UK. It is a prestigious research university consistently ranked in the top 1% globally. Known as one of the red brick universities in the UK, it is also a founding member of the Russell Group, which represents leading UK universities.");
    }

    @Override
    public int getLayoutId() {
        return R.layout.university_information;
    }
}
