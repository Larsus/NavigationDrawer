package com.larsus.navigationdrawer.demo;

import com.github.larsus.nvgd.NavigationActivity;
import com.github.larsus.nvgd.adapter.DefaultActionModelAdapterItem;
import com.github.larsus.nvgd.adapter.DefaultHeaderModelAdapterItem;
import com.github.larsus.nvgd.adapter.DefaultStateModelAdapterItem;
import com.github.larsus.nvgd.adapter.ModelAdapterItems;
import com.github.larsus.nvgd.handler.OnActionItemClickListener;
import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.ActionModel;
import com.github.larsus.nvgd.model.StateModel;
import com.larsus.navigationdrawer.demo.nvgd.adapter.ActionCountModelAdapterItem;
import com.larsus.navigationdrawer.demo.nvgd.model.ActionCountModel;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class MainActivity extends NavigationActivity {

    @Override
    protected int getActivityLayoutResourceId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onInitModelAdapterItems(ModelAdapterItems modelAdapterItems) {
        modelAdapterItems
                .add(new ActionCountModelAdapterItem(R.mipmap.inbox, "Inbox", 0, new OnActionItemClickListener<ActionCountModel>() {
                    @Override
                    public void OnActionItemClick(ActionCountModel model) {
                        model.setCount(model.getCount() + 1);
                    }
                }))
                .add(new DefaultActionModelAdapterItem(R.mipmap.grade, "Starred", onActionItemClickListener))
                .add(new DefaultActionModelAdapterItem(R.mipmap.send, "Sent mail", onActionItemClickListener))
                .add(new DefaultActionModelAdapterItem(R.mipmap.drafts, "Drafts", onActionItemClickListener))
                .add(new DefaultHeaderModelAdapterItem("Subheader"))
                .add(new DefaultActionModelAdapterItem(R.mipmap.markunread, "All mail", onActionItemClickListener))
                .add(new DefaultActionModelAdapterItem(R.mipmap.delete, "Trash", onActionItemClickListener))
                .add(new DefaultActionModelAdapterItem(R.mipmap.report, "Spam", onActionItemClickListener))
                .add(new DefaultHeaderModelAdapterItem("Settings"))
                .add(new DefaultStateModelAdapterItem(false, "State element1", onStateItemClickListener))
                .add(new DefaultStateModelAdapterItem(true, "State element2", onStateItemClickListener))
                .add(new DefaultStateModelAdapterItem(true, "State element3", onStateItemClickListener));
    }

    OnStateItemClickListener<StateModel> onStateItemClickListener = new OnStateItemClickListener<StateModel>() {
        @Override
        public void OnStateItemClick(StateModel model, List<? extends StateModel> models) {
            for (int i = 0; i < models.size(); i++) {
                models.get(i).setState(false);
            }
        }
    };

    OnActionItemClickListener<ActionModel> onActionItemClickListener = new OnActionItemClickListener<ActionModel>() {
        @Override
        public void OnActionItemClick(ActionModel model) {

        }
    };
}
