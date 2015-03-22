package com.larsus.navigationdrawer.demo;

import com.github.larsus.nvgd.NavigationActivity;
import com.github.larsus.nvgd.adapter.DefaultActionModelAdapterItem;
import com.github.larsus.nvgd.adapter.DefaultHeaderModelAdapterItem;
import com.github.larsus.nvgd.adapter.DefaultStateModelAdapterItem;
import com.github.larsus.nvgd.adapter.ModelAdapterItem;
import com.github.larsus.nvgd.handler.OnActionItemClickListener;
import com.github.larsus.nvgd.handler.OnStateItemClickListener;
import com.github.larsus.nvgd.model.ActionModel;
import com.github.larsus.nvgd.model.StateModel;

import java.util.ArrayList;
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
    protected List<? super ModelAdapterItem> getModelAdapterItems() {

        List<? super ModelAdapterItem> models = new ArrayList<>();

        models.add(new DefaultActionModelAdapterItem(R.mipmap.inbox, "Inbox", onActionItemClickListener));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.grade, "Starred", onActionItemClickListener));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.send, "Sent mail", onActionItemClickListener));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.drafts, "Drafts", onActionItemClickListener));
        models.add(new DefaultHeaderModelAdapterItem("Subheader"));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.markunread, "All mail", onActionItemClickListener));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.delete, "Trash", onActionItemClickListener));
        models.add(new DefaultActionModelAdapterItem(R.mipmap.report, "Spam", onActionItemClickListener));
        models.add(new DefaultHeaderModelAdapterItem("Settings"));
        models.add(new DefaultStateModelAdapterItem(false, "State element1", onStateItemClickListener));
        models.add(new DefaultStateModelAdapterItem(true, "State element2", onStateItemClickListener));
        models.add(new DefaultStateModelAdapterItem(true, "State element3", onStateItemClickListener));

        return models;
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
