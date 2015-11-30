package com.larsus.navigationdrawer.demo.nvgd.adapter;

import com.github.larsus.nvgd.adapter.ActionModelAdapterItem;
import com.github.larsus.nvgd.handler.OnActionItemClickListener;
import com.larsus.navigationdrawer.demo.R;
import com.larsus.navigationdrawer.demo.nvgd.model.ActionCountModel;
import com.larsus.navigationdrawer.demo.nvgd.viewholder.ActionCountViewHolder;

/**
 * @author Alexander Evdokimov (Larsus)
 * @version 1.0
 * @since 30.11.2015
 */
public final class ActionCountModelAdapterItem extends ActionModelAdapterItem<ActionCountModel> {
    public ActionCountModelAdapterItem(int icon, String title, int count,
                                         OnActionItemClickListener<ActionCountModel> onActionItemClickListener) {
        super(new ActionCountModel(icon, title, count), ActionCountViewHolder.class, R.layout.nvgd_drawer_list_item_action_counter, onActionItemClickListener);
    }
}