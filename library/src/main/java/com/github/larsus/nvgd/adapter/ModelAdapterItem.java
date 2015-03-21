package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.model.BaseModel;
import com.github.larsus.nvgd.viewholder.BaseViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class ModelAdapterItem<T extends BaseModel> {
    int layoutResourceId;
    T model;
    Class<? extends BaseViewHolder<T>> viewHolder;

    public ModelAdapterItem(T model, Class<? extends BaseViewHolder<T>> viewHolder, int layoutResourceId) {
        this.model = model;
        this.viewHolder = viewHolder;
        this.layoutResourceId = layoutResourceId;
    }
}