package com.learn.community.core.base;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.learn.community.utils.ThreadPoolUtils;

import java.lang.reflect.ParameterizedType;

public abstract class BaseActivity<DB extends ViewDataBinding, VM extends ViewModel> extends AppCompatActivity {
    protected DB binding;
    protected VM viewModel;
    protected Bundle savedInstanceState;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        bindLayout();
        onCreate();
    }

    public abstract void onCreate();
    public abstract int getLayoutId();

    private void bindLayout() {
        if (getClass().getGenericSuperclass() == null)
            return;
        // 获取viewModel对象
        Class<VM> clzVM = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        viewModel = new ViewModelProvider(this).get(clzVM);
        // 根据布局，获取DataBinding对象
        binding = DataBindingUtil.setContentView(this, getLayoutId());
        binding.setLifecycleOwner(this);
    }

    protected boolean checkEdit(EditText editText, String message, TextView errorTextView) {
        CharSequence text = editText.getText();
        if (TextUtils.isEmpty(text)) {
            errorTextView.setText(message);
            return false;
        } else {
            errorTextView.setText("");
            return true;
        }
    }

    protected void runOnSubThread(Runnable runnable) {
        ThreadPoolUtils.getInstance().submit(runnable);
    }
}
