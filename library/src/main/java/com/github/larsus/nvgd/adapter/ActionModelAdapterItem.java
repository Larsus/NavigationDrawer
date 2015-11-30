package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.handler.OnActionItemClickListener;
import com.github.larsus.nvgd.model.ActionModel;
import com.github.larsus.nvgd.viewholder.BaseActionViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class ActionModelAdapterItem<TModel extends ActionModel> extends ModelAdapterItem<TModel> {

    protected OnActionItemClickListener<TModel> onActionItemClickListener;

    public ActionModelAdapterItem(TModel actionModel, Class<? extends BaseActionViewHolder<TModel>> actionViewHolder,
                                  int layoutResourceId, OnActionItemClickListener<TModel> onActionItemClickListener) {
        super(actionModel, actionViewHolder, layoutResourceId);
        this.onActionItemClickListener = onActionItemClickListener;
    }

    public void onActionItemClick(ModelAdapter modelAdapter) {
        if (onActionItemClickListener == null) return;
        onActionItemClickListener.OnActionItemClick(model);

        modelAdapter.notifyDataSetChanged();
    }
}
