package com.learn.community.core.detail;

import androidx.databinding.ObservableField;

import com.learn.community.common.model.Post;
import com.learn.community.core.base.BaseViewModel;
import com.blankj.utilcode.util.TimeUtils;

public class PostVM extends BaseViewModel {
    ObservableField<String> publishTime = new ObservableField<>();
    ObservableField<String> content = new ObservableField<>();
    ObservableField<String> title = new ObservableField<>();

    /**
     * 初始化数据，通知DataBinding设置xml上的字段展示内容
     * @param bean
     */
    public void initData(Post bean) {
        publishTime.set(TimeUtils.millis2String(bean.getPostTime(), "yyyy-MM-dd HH:mm:ss"));
        content.set(bean.getContent());
        title.set(bean.getTitle());
    }
    // 以下用于在xml布局中获取数据
    public ObservableField<String> getPublishTime() {
        return publishTime;
    }
    public ObservableField<String> getContent() {
        return content;
    }
    public ObservableField<String> getTitle() {
        return title;
    }

}
