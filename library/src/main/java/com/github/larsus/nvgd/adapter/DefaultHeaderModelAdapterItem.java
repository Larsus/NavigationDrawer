package com.github.larsus.nvgd.adapter;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.model.HeaderModel;
import com.github.larsus.nvgd.viewholder.DefaultHeaderViewHolder;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public final class DefaultHeaderModelAdapterItem extends ModelAdapterItem<HeaderModel> {
    public DefaultHeaderModelAdapterItem(String title) {
        super(new HeaderModel(title), DefaultHeaderViewHolder.class, R.layout.nvgd_drawer_list_item_header);
    }
}
