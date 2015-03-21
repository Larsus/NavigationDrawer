package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.handler.OnActionItemClickListener;
import com.github.larsus.nvgd.model.ActionModel;
import com.github.larsus.nvgd.viewholder.DefaultActionViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public final class DefaultActionModelAdapterItem extends ActionModelAdapterItem<ActionModel> {
    public DefaultActionModelAdapterItem(int icon, String title,
                                         OnActionItemClickListener<ActionModel> onActionItemClickListener) {
        super(new ActionModel(icon, title), DefaultActionViewHolder.class, R.layout.nvgd_drawer_list_item_action, onActionItemClickListener);
    }
}
