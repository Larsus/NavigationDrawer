package com.github.larsus.nvgd.activity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.github.larsus.nvgd.R;
import com.github.larsus.nvgd.adapter.ActionModelAdapterItem;
import com.github.larsus.nvgd.adapter.ModelAdapter;
import com.github.larsus.nvgd.adapter.ModelAdapterItem;
import com.github.larsus.nvgd.adapter.ModelAdapterItems;
import com.github.larsus.nvgd.viewholder.DefaultViewHolderBuilder;
import com.github.larsus.nvgd.viewholder.ViewHolderBuilder;

import java.util.List;

/**
 * @author Larsus
 * @version 1.0
 * @since 19.03.2015
 */
public abstract class BaseActivity extends ActionBarActivity {

    private static final String CURRENT_ITEM_POSITION = "nvgd.current.item.position";

    private DrawerLayout mDrawerLayout;
    private View mDrawerView;

    private ActionBarDrawerToggle mDrawerToggle;

    private int mCurrentItemPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCurrentPosition(savedInstanceState);

        setContentView(getActivityLayoutResourceId());

        mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutResourceId());
        mDrawerView = findViewById(getDrawerViewResourceId());

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        initDrawerList();
        initDrawerFooterList();

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.nvgd_drawer_open, R.string.nvgd_drawer_close){
            public void onDrawerClosed(View v){
                super.onDrawerClosed(v);
                invalidateOptionsMenu();
                syncState();
            }
            public void onDrawerOpened(View v){
                super.onDrawerOpened(v);
                invalidateOptionsMenu();
                syncState();
            }
        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
    }

    private void initDrawerList() {
        final ModelAdapterItems modelAdapterItems = new ModelAdapterItems();

        onInitModelAdapterItems(modelAdapterItems);

        final ModelAdapter modelAdapter = new ModelAdapter(this, getViewHolderBuilder(), modelAdapterItems.getModelAdapterItems(), getSaveItemPositionState() == SaveItemPositionState.SAVE);

        final ListView drawerList = (ListView) findViewById(getDrawerListResourceId());
        drawerList.setAdapter(modelAdapter);
        drawerList.setOnItemClickListener(new ActionItemClickListener(modelAdapter));

        modelAdapter.selectItem(mCurrentItemPosition);
    }

    private void initDrawerFooterList() {
        final ModelAdapterItems footerModelAdapterItems = new ModelAdapterItems();

        onInitFooterModelAdapterItems(footerModelAdapterItems);

        if (footerModelAdapterItems.getModelAdapterItems().size() == 0)
            return;

        final ModelAdapter footerModelAdapter = new ModelAdapter(this, getViewHolderBuilder(), footerModelAdapterItems.getModelAdapterItems(), false);

        final ListView mDrawerFooterList = (ListView) findViewById(getDrawerFooterListResourceId());
        mDrawerFooterList.setAdapter(footerModelAdapter);
        mDrawerFooterList.setOnItemClickListener(new ActionItemClickListener(footerModelAdapter));
    }

    protected abstract SaveItemPositionState getSaveItemPositionState();

    protected abstract int getActivityLayoutResourceId();

    protected int getDrawerLayoutResourceId() {
        return R.id.nvgd_drawer;
    }

    protected int getDrawerViewResourceId() { return R.id.nvgd_drawer_items_list; }

    protected int getDrawerListResourceId() {
        return R.id.nvgd_drawer_items_list;
    }

    protected int getDrawerFooterListResourceId() { return R.id.nvgd_drawer_footer_items_list; }

    protected abstract void onInitModelAdapterItems(ModelAdapterItems modelAdapterItems);

    protected void onInitFooterModelAdapterItems(ModelAdapterItems modelAdapterItems) {
    }

    protected ViewHolderBuilder getViewHolderBuilder() {
        return new DefaultViewHolderBuilder();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(CURRENT_ITEM_POSITION, mCurrentItemPosition);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState){
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig){
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                if (mDrawerLayout.isDrawerOpen(mDrawerView)){
                    mDrawerLayout.closeDrawer(mDrawerView);
                } else {
                    mDrawerLayout.openDrawer(mDrawerView);
                }
                return true;
            }
            default: return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {
        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerView);
        if (drawerOpen) {
            mDrawerLayout.closeDrawer(mDrawerView);
        } else {
            super.onBackPressed();
        }
    }

    public void setDefaultPosition(int position){
        this.mCurrentItemPosition = position;
    }

    private void setCurrentPosition(int position){
        this.mCurrentItemPosition = position;
    }

    private void loadCurrentPosition(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            setCurrentPosition(savedInstanceState.getInt(CURRENT_ITEM_POSITION));
        }
    }

    private class ActionItemClickListener implements ListView.OnItemClickListener {

        private final ModelAdapter modelAdapter;

        public ActionItemClickListener(ModelAdapter modelAdapter) {
            this.modelAdapter = modelAdapter;
        }

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (modelAdapter == null || !(modelAdapter.getItem(position) instanceof ActionModelAdapterItem)) return;

            ActionModelAdapterItem actionModelAdapterItem = (ActionModelAdapterItem) modelAdapter.getItem(position);
            actionModelAdapterItem.onActionItemClick(modelAdapter);

            if (mCurrentItemPosition != position && modelAdapter.selectItem(position)) {
                setCurrentPosition(position);
            }

            mDrawerLayout.closeDrawer(mDrawerView);
        }
    }

    public enum SaveItemPositionState {
        SAVE,
        DO_NOT_SAVE
    }
}
