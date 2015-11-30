package com.github.larsus.nvgd.viewholder;

import android.view.View;

import com.github.larsus.nvgd.model.BaseModel;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public interface ViewHolderBuilder {
    <TViewHolder extends BaseViewHolder<? extends BaseModel>, TModel extends BaseModel> TViewHolder build(View convertView, TModel baseModel, Class<TViewHolder> type);
}
