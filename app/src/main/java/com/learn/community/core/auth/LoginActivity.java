package com.learn.community.core.auth;

import android.content.Intent;
import android.view.View;

import com.learn.community.R;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.main.MainActivity;
import com.learn.community.databinding.LoginLayoutBinding;
import com.learn.community.utils.LoginUtils;
import com.learn.community.utils.VerifyUtils;
import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;

/**
 * Sign
 */
public class LoginActivity extends BaseActivity<LoginLayoutBinding, AuthVM> implements View.OnClickListener {
    @Override
    public void onCreate() {
        binding.setOnClick(this);
        binding.toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolBar);
    }

    @Override
    public int getLayoutId() {
        return R.layout.login_layout;
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.submit) {
            checkLogin();
        } else if (id == R.id.sign_up) {
            ActivityUtils.startActivity(SignUpActivity.class);
            finish();
        }
    }


    private void checkLogin() {
        // 输入合法性校验
        if (checkEdit(binding.sNo, "Input Account Please", binding.notice)
                && checkEdit(binding.sPassword, "Input Password Please", binding.notice)) {
            String no = String.valueOf(binding.sNo.getText());
            String pass = String.valueOf(binding.sPassword.getText());
            viewModel.getAccount(this, no).observe(this, account -> {
                if (null == account) {
                    ToastUtils.showLong("Login Failed");
                    return;
                }
                // 校验账号
                boolean flag = VerifyUtils.verifyPassword(pass, account.getPassword());
                if (flag) {
                    LoginUtils.getInstance().saveLoginUser(account);
                    ActivityUtils.startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    finish();
                } else {
                    ToastUtils.showLong("Login Failed");
                }
            });
        }
    }
}
