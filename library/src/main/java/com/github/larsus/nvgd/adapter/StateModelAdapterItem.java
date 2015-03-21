package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.StateModel;
import com.github.larsus.nvgd.viewholder.BaseStateViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 21.03.2015
 */
public class StateModelAdapterItem<T extends StateModel> extends ModelAdapterItem<T> {


    private OnStateItemClickListener<T> onStateItemClickListener;

    public StateModelAdapterItem(T stateModel, Class<? extends BaseStateViewHolder<T>> stateViewHolder,
                                 int layoutResourceId, OnStateItemClickListener<T> onStateItemClickListener) {
        super(stateModel, stateViewHolder, layoutResourceId);
        this.onStateItemClickListener = onStateItemClickListener;
    }

    public OnStateItemClickListener<T> getOnStateItemClickListener() {
        return onStateItemClickListener;
    }
}
