package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.StateModel;
import com.github.larsus.nvgd.viewholder.DefaultStateViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 22.03.2015
 */
public class DefaultStateModelAdapterItem extends StateModelAdapterItem<StateModel> {
    public DefaultStateModelAdapterItem(boolean state, String title,
                                        OnStateItemClickListener<StateModel> onStateItemClickListener) {
        super(new StateModel(state, title), DefaultStateViewHolder.class, R.layout.nvgd_drawer_list_item_state, onStateItemClickListener);
    }
}
