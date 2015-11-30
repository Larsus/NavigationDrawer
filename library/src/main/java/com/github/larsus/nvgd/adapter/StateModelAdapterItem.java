package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.StateModel;
import com.github.larsus.nvgd.viewholder.BaseStateViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 21.03.2015
 */
public class StateModelAdapterItem<TModel extends StateModel> extends ModelAdapterItem<TModel> {

    protected OnStateItemClickListener<TModel> onStateItemClickListener;

    public StateModelAdapterItem(TModel stateModel, Class<? extends BaseStateViewHolder<TModel>> stateViewHolder,
                                 int layoutResourceId, OnStateItemClickListener<TModel> onStateItemClickListener) {
        super(stateModel, stateViewHolder, layoutResourceId);
        this.onStateItemClickListener = onStateItemClickListener;
    }

    public OnStateItemClickListener<TModel> getOnStateItemClickListener() {
        return onStateItemClickListener;
    }
}
