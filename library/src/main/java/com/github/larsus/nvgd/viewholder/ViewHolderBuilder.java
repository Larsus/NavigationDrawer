package com.github.larsus.nvgd.viewholder;

import android.view.View;

import com.github.larsus.nvgd.model.BaseModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public interface ViewHolderBuilder {
    <T extends BaseViewHolder<? extends BaseModel>> T build(View convertView, BaseModel baseModel, Class<T> type);
}
