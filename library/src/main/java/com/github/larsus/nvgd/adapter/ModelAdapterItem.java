package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.model.BaseModel;
import com.github.larsus.nvgd.viewholder.BaseViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class ModelAdapterItem<TModel extends BaseModel> {
    protected int layoutResourceId;
    protected TModel model;
    protected Class<? extends BaseViewHolder<TModel>> viewHolder;

    public ModelAdapterItem(TModel model, Class<? extends BaseViewHolder<TModel>> viewHolder, int layoutResourceId) {
        this.model = model;
        this.viewHolder = viewHolder;
        this.layoutResourceId = layoutResourceId;
    }
}