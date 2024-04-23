package com.learn.community.core.auth;

import android.text.TextUtils;
import android.view.View;

import com.learn.community.R;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.databinding.SignUpLayoutBinding;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

public class SignUpActivity extends BaseActivity<SignUpLayoutBinding, AuthVM> implements View.OnClickListener {
    @Override
    public void onCreate() {
        binding.setOnClick(this);
        binding.toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolBar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.sign_up_layout;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.submit) {
            checkLogin();
        }
    }

    private void checkLogin() {
        if (checkEdit(binding.sNo, "Input Account please", binding.notice)
                && checkEdit(binding.sPassword, "Input Password please", binding.notice)
                && checkEdit(binding.sConfirmPassword, "Input Password again please", binding.notice)) {
            String no = String.valueOf(binding.sNo.getText());
            String pass = String.valueOf(binding.sPassword.getText());
            String confirmPass = String.valueOf(binding.sConfirmPassword.getText());
            // 校验密码是否正确
            if (!TextUtils.equals(pass, confirmPass)) {
                binding.notice.setText("Password Check Failed");
                return;
            }
            viewModel.saveAccount(this, no, pass, () -> {
                ActivityUtils.startActivity(LoginActivity.class);
                ToastUtils.showLong("Success!");
                finish();
            });
        }
    }
}
