package com.github.larsus.nvgd.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.github.larsus.nvgd.model.ActionModel;
import com.github.larsus.nvgd.model.HeaderModel;
import com.github.larsus.nvgd.model.BaseModel;
import com.github.larsus.nvgd.model.SeparatorModel;
import com.github.larsus.nvgd.model.StateModel;
import com.github.larsus.nvgd.viewholder.BaseActionViewHolder;
import com.github.larsus.nvgd.viewholder.BaseStateViewHolder;
import com.github.larsus.nvgd.viewholder.BaseViewHolder;
import com.github.larsus.nvgd.viewholder.ViewHolderBuilder;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public final class ModelAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<? super ModelAdapterItem> mModelList;
    private final ViewHolderBuilder mViewHolderBuilder;

    private boolean mShowSelectedState = false;

    public ModelAdapter(Context context, ViewHolderBuilder viewHolderBuilder,
                        List<? super ModelAdapterItem> modelList) {
        this.mContext = context;
        this.mViewHolderBuilder = viewHolderBuilder;
        this.mModelList = modelList;
    }

    public ModelAdapter(Context context, ViewHolderBuilder viewHolderBuilder,
                        List<? super ModelAdapterItem> modelList, boolean showSelectedState) {
        this(context, viewHolderBuilder, modelList);
        this.mShowSelectedState = showSelectedState;
    }

    @Override
    public int getCount() {
        return mModelList.size();
    }

    @Override
    public ModelAdapterItem getItem(int position) {
        return (ModelAdapterItem) mModelList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        return getItem(position).model.getViewType();
    }

    @Override
    public boolean isEnabled(int position) {
        return getItem(position).model.isEnabled();
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        ModelAdapterItem modelAdapterItem = (ModelAdapterItem) mModelList.get(position);

        BaseModel.ModelType modelType = BaseModel.ModelType.getFromViewType(getItemViewType(position));

        if (modelType == null) return convertView;

        switch (modelType) {
            case HEADER:
                if (!(modelAdapterItem.model instanceof HeaderModel)) throw new AssertionError("");

                BaseViewHolder headerViewHolder;

                HeaderModel headerModel = (HeaderModel) modelAdapterItem.model;

                if (convertView == null) {
                    convertView = inflater.inflate(modelAdapterItem.layoutResourceId, parent, false);
                    Class<? extends BaseViewHolder<HeaderModel>> viewHolderClass = modelAdapterItem.viewHolder;
                    headerViewHolder = mViewHolderBuilder.build(convertView, headerModel, viewHolderClass);
                    convertView.setTag(headerViewHolder);
                }
                else {
                    headerViewHolder = (BaseViewHolder) convertView.getTag();
                    headerViewHolder.updateView();
                }
                break;
            case ACTION:
                if (!(modelAdapterItem.model instanceof ActionModel)) throw new AssertionError("");

                BaseActionViewHolder actionViewHolder;

                ActionModel actionModel = (ActionModel) modelAdapterItem.model;

                if (convertView == null) {
                    convertView = inflater.inflate(modelAdapterItem.layoutResourceId, parent, false);
                    Class<? extends BaseViewHolder<ActionModel>> viewHolderClass = modelAdapterItem.viewHolder;
                    actionViewHolder = (BaseActionViewHolder) mViewHolderBuilder.build(convertView, actionModel, viewHolderClass);
                    convertView.setTag(actionViewHolder);
                }
                else {
                    actionViewHolder = (BaseActionViewHolder) convertView.getTag();
                    actionViewHolder.updateView();
                }
                if (actionModel.isSelected() && mShowSelectedState)
                    actionViewHolder.selectView(convertView);
                else
                    actionViewHolder.deSelectView(convertView);

                break;
            case SEPARATOR:
                if (!(modelAdapterItem.model instanceof SeparatorModel)) throw new AssertionError("");

                BaseViewHolder separatorViewHolder;

                SeparatorModel separatorModel = (SeparatorModel) modelAdapterItem.model;

                if (convertView == null) {
                    convertView = inflater.inflate(modelAdapterItem.layoutResourceId, parent, false);
                    Class<? extends BaseViewHolder<SeparatorModel>> viewHolderClass = modelAdapterItem.viewHolder;
                    separatorViewHolder = mViewHolderBuilder.build(convertView, separatorModel, viewHolderClass);
                    convertView.setTag(separatorViewHolder);
                }
                else {
                    separatorViewHolder = (BaseViewHolder) convertView.getTag();
                    separatorViewHolder.updateView();
                }
                break;
            case STATE:
                if (!(modelAdapterItem.model instanceof StateModel)) throw new AssertionError("");

                BaseStateViewHolder stateViewHolder;

                final StateModel stateModel = (StateModel) modelAdapterItem.model;

                final StateModelAdapterItem stateModelAdapterItem = (StateModelAdapterItem) modelAdapterItem;

                if (convertView == null) {
                    convertView = inflater.inflate(modelAdapterItem.layoutResourceId, parent, false);
                    Class<? extends BaseStateViewHolder<StateModel>> viewHolderClass = modelAdapterItem.viewHolder;
                    stateViewHolder = mViewHolderBuilder.build(convertView, stateModel, viewHolderClass);
                    stateViewHolder.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            stateModelAdapterItem.getOnStateItemClickListener().OnStateItemClick(stateModel);
                            notifyDataSetChanged();
                        }
                    });
                    convertView.setTag(stateViewHolder);
                }
                else  {
                    stateViewHolder = (BaseStateViewHolder) convertView.getTag();
                    stateViewHolder.updateView();
                }

                break;
        }
        return convertView;
    }

    @Override
    protected void finalize() throws Throwable {
        mModelList.clear();
        super.finalize();
    }

    public boolean selectItem(int position) {
        if (position < 0) return false;
        if (position > mModelList.size() - 1) return false;
        if (!(mModelList.get(position) instanceof ActionModelAdapterItem)) return false;

        deSelectAll();

        ActionModelAdapterItem actionModelAdapterItem = (ActionModelAdapterItem) mModelList.get(position);

        ((ActionModel)actionModelAdapterItem.model).select();

        notifyDataSetChanged();

        return true;
    }

    private void deSelectAll() {
        int listCount = mModelList.size();
        for (int i = 0; i < listCount; i++) {
            if (!(mModelList.get(i) instanceof ActionModelAdapterItem)) continue;

            ActionModelAdapterItem actionModelAdapterItem = (ActionModelAdapterItem) mModelList.get(i);

            ((ActionModel)actionModelAdapterItem.model).deSelect();
        }
    }
}
