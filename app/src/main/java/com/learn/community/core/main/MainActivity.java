package com.learn.community.core.main;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.blankj.utilcode.util.ActivityUtils;
import com.learn.community.R;
import com.learn.community.core.auth.LoginActivity;
import com.learn.community.core.base.BaseActivity;
import com.learn.community.core.base.BaseViewModel;
import com.learn.community.core.chat.ChatFragment;
import com.learn.community.core.community.CommunityFragment;
import com.learn.community.core.home.HomeFragment;
import com.learn.community.databinding.MainLayoutBinding;
import com.learn.community.utils.LoginUtils;
import com.learn.community.utils.ViewPager2Helper;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainLayoutBinding, BaseViewModel> {
    public static final String TAG = "MainActivity";
    List<String> title = new ArrayList<>();
    List<Fragment> fragments = new ArrayList<>();

    @SuppressLint("SetTextI18n")
    @Override
    public void onCreate() {
        binding.toolBar.setTitle(getString(R.string.app_name));
        setSupportActionBar(binding.toolBar);
        if (LoginUtils.getInstance().getLoginUser() < 0) {
            ActivityUtils.startActivity(LoginActivity.class);
            finish();
            return;
        }
        title.add("Community");
        title.add("HOME");
        title.add("Chat");
        fragments.add(new CommunityFragment());
        fragments.add(new HomeFragment());
        fragments.add(new ChatFragment());
        initData();
    }
    private void initData() {
        initPager();
        initTab();
    }

    private void initPager() {
        binding.viewPager.setAdapter(new FragmentStateAdapter(getSupportFragmentManager(), getLifecycle()) {
            @NonNull
            @Override
            public Fragment createFragment(int position) {
                return fragments.get(position);
            }

            @Override
            public int getItemCount() {
                return fragments.size();
            }
        });
    }

    private void initTab() {
        MagicIndicator magicIndicator = findViewById(R.id.magic_indicator);
        CommonNavigator commonNavigator = new CommonNavigator(this);
        commonNavigator.setReselectWhenLayout(false);
        commonNavigator.setAdjustMode(true);
        commonNavigator.setAdapter(new CommonNavigatorAdapter() {

            @Override
            public int getCount() {
                return title.size();
            }

            @Override
            public IPagerTitleView getTitleView(Context context, final int index) {
                // 设置tab样式和分类
                ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
                colorTransitionPagerTitleView.setNormalColor(R.color.md_theme_light_secondary);
                colorTransitionPagerTitleView.setSelectedColor(R.color.md_theme_light_primary);
                colorTransitionPagerTitleView.setText(title.get(index));
                colorTransitionPagerTitleView.setTextSize(16);
                colorTransitionPagerTitleView.setOnClickListener(view -> binding.viewPager.setCurrentItem(index));
                return colorTransitionPagerTitleView;
            }

            @Override
            public IPagerIndicator getIndicator(Context context) {
                // 设置指示器样式
                LinePagerIndicator indicator = new LinePagerIndicator(context);
                indicator.setMode(LinePagerIndicator.MODE_WRAP_CONTENT);
                return indicator;
            }
        });
        magicIndicator.setNavigator(commonNavigator);
        ViewPager2Helper.bind(magicIndicator, binding.viewPager);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_layout;
    }

}
