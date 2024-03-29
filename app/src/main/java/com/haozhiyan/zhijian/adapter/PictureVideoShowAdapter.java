package com.haozhiyan.zhijian.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.haozhiyan.zhijian.R;
import com.haozhiyan.zhijian.utils.ImageRequest;
import com.haozhiyan.zhijian.utils.LogUtils;
import com.haozhiyan.zhijian.utils.PVAUtils;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.DateUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PictureVideoShowAdapter extends BaseQuickAdapter<LocalMedia, BaseViewHolder> {

    private Context context;
    private List<LocalMedia> mediaList;

    public PictureVideoShowAdapter(Context context) {
        super(R.layout.gv_filter_image_show);
        this.context = context;
    }

    @Override
    public void setNewData(@Nullable List<LocalMedia> data) {
        if (mediaList == null) {
            mediaList = new ArrayList<>();
        }
        try {
            mediaList.clear();
            mediaList.addAll(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.setNewData(mediaList);
    }

    @Override
    protected void convert(BaseViewHolder helper, LocalMedia media) {
        helper.setGone(R.id.ll_del, false);
        String path = "";
        if (media.isCut() && !media.isCompressed()) {
            // 裁剪过
            path = media.getCutPath();
        } else if (media.isCompressed() || (media.isCut() && media.isCompressed())) {
            // 压缩过,或者裁剪同时压缩过,以最终压缩过图片为准
            path = media.getCompressPath();
        } else {
            // 原图
            path = media.getPath();
        }
        // 图片
        if (media.isCompressed()) {
            LogUtils.i("compress image result:", new File(media.getCompressPath()).length() / 1024 + "k");
            LogUtils.i("压缩地址::", media.getCompressPath());
        }
        try {
            LogUtils.i("原图地址::", media.getPath());
            if (PVAUtils.getFileLastType(media.getPath()).equals("video/3gp")) {
                long duration = media.getDuration();
                helper.setText(R.id.tv_dutration, DateUtils.timeParse(duration))
                        .setGone(R.id.tv_dutration, true)
                        .setGone(R.id.play_img, true);
            } else {
                helper.setGone(R.id.tv_dutration, false).setGone(R.id.play_img, false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //int pictureType = PictureMimeType.isPictureType(media.getPictureType());
        if (media.isCut()) {
            try {
                LogUtils.i("裁剪地址::", media.getCutPath());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        try {
            new ImageRequest(context).get(path, (ImageView) helper.getView(R.id.fiv));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
