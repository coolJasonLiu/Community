package com.learn.community.core.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.learn.community.R;
import com.learn.community.common.db.DBManager;
import com.learn.community.common.model.Account;
import com.learn.community.core.auth.LoginActivity;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.databinding.MineActivityBinding;
import com.learn.community.utils.LoginUtils;

public class MineActivity extends BaseActivity<MineActivityBinding, BaseViewModel> {
    private static final int PERMISSION_REQUEST_PHOTO = 3;
    Account mAccount;
    private ActivityResultLauncher<Intent> launcherPicture;
    private String picturePath;
    private Bitmap bitmap;
    @Override
    public void onCreate() {
        binding.toolBar.setNavigationIcon(R.drawable.ic_arrow_back);
        binding.toolBar.setNavigationOnClickListener(v -> finish());
        long id = LoginUtils.getInstance().getLoginUser();
        DBManager.getInstance(this).getAccountDao().queryById(id).observe(this, account -> {
            mAccount = account;
            binding.username.setText(account.getUsername());
            if (mAccount.getSex() == 0) {
                binding.male.setChecked(true);
            } else {
                binding.female.setChecked(true);
            }
            Glide.with(this).load(account.getAvatar())
                    .error(R.drawable.baseline_person_24)
                    .into(binding.avatar);
        });
        binding.avatar.setOnClickListener(v -> selectPicture());
        binding.submit.setOnClickListener(v -> {
            if (TextUtils.isEmpty(binding.username.getText())) {
                ToastUtils.showLong("Username should not be null");
                return;
            }
            int radioButtonId = binding.sex.getCheckedRadioButtonId();
            if (radioButtonId == R.id.male) {
                mAccount.setSex(0);
            } else {
                mAccount.setSex(1);
            }
            mAccount.setAvatar(picturePath);
            mAccount.setUsername(binding.username.getText().toString());
            runOnSubThread(() -> DBManager.getInstance(this).getAccountDao().update(mAccount));

            ToastUtils.showLong("Success!");
        });
        binding.logout.setOnClickListener(v -> {
            LoginUtils.getInstance().logout();
            ActivityUtils.startActivity(LoginActivity.class);
        });
        launcherPicture = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
            if (result.getResultCode() == Activity.RESULT_OK) {
                if (result.getData() != null) {
                    Uri selectedImage = result.getData().getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = null;
                    if (selectedImage != null) {
                        cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    }
                    if (cursor != null) {
                        cursor.moveToFirst();
                        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                        String picturePath = cursor.getString(columnIndex);
                        cursor.close();
                        this.picturePath = picturePath;
                        bitmap = BitmapFactory.decodeFile(picturePath);
                        binding.avatar.setImageBitmap(bitmap);
                    }
                } else {
                    Toast.makeText(this, "Photo Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("InlinedApi")
    private void selectPicture() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_MEDIA_IMAGES}, PERMISSION_REQUEST_PHOTO);
        } else {
            Intent intent = new Intent(Intent.ACTION_PICK, null);
            intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            launcherPicture.launch(intent);
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.mine_activity;
    }
}
