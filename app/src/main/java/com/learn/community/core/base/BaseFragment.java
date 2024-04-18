package com.learn.community.core.base;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.learn.community.utils.ThreadPoolUtils;

import java.lang.reflect.ParameterizedType;

public abstract class BaseFragment<DB extends ViewDataBinding, VM extends ViewModel> extends Fragment {
    protected DB binding;
    protected VM viewModel;
    protected Handler handler = new Handler(Looper.getMainLooper());
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getClass().getGenericSuperclass() == null || null == getActivity()) {
            return null;
        }
        // 获取viewModel对象
        Class<VM> clzVM = (Class<VM>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        viewModel = new ViewModelProvider(this).get(clzVM);
        // 获取DataBinding
        binding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        binding.setLifecycleOwner(this);
        // 初始化View
        View view = binding.getRoot();
        onCreateView(view);
        return view;
    }

    public abstract void onCreateView(View view);

    public abstract int getLayoutId();

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

    protected void runOnUiThread(Runnable runnable) {
        handler.post(runnable);
    }

    protected void runOnSubThread(Runnable runnable) {
        ThreadPoolUtils.getInstance().submit(runnable);
    }
}
