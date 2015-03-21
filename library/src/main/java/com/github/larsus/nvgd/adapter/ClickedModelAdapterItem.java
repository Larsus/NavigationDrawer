package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.handler.OnItemClickListener;
import com.github.larsus.nvgd.model.BaseModel;
import com.github.larsus.nvgd.viewholder.BaseViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public abstract class ClickedModelAdapterItem<T extends BaseModel> extends ModelAdapterItem<T> {

    private OnItemClickListener<T> onItemClickListener;

    public ClickedModelAdapterItem(T baseModel, Class<? extends BaseViewHolder<T>> baseViewHolder,
                                 int layoutResourceId, OnItemClickListener<T> onItemClickListener) {
        super(baseModel, baseViewHolder, layoutResourceId);
        this.onItemClickListener = onItemClickListener;
    }

    public void onItemClick(ModelAdapter modelAdapter) {
        if (onItemClickListener == null) return;
        onItemClickListener.OnItemClick(model);

        modelAdapter.notifyDataSetChanged();
    }
}