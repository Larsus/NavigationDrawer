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
    private ListView mDrawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private ModelAdapter mModelAdapter;

    private int mCurrentItemPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadCurrentPosition(savedInstanceState);

        setContentView(getActivityLayoutResourceId());

        mDrawerLayout = (DrawerLayout) findViewById(getDrawerLayoutResourceId());
        assert mDrawerLayout != null;

        mDrawerView = findViewById(getDrawerViewResourceId());
        assert mDrawerList != null;

        mDrawerList = (ListView) findViewById(getDrawerListResourceId());
        assert mDrawerList != null;

        mDrawerList.setOnItemClickListener(new ActionItemClickListener());

        final ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        ModelAdapterItems modelAdapterItems = new ModelAdapterItems();

        onInitModelAdapterItems(modelAdapterItems);

        mModelAdapter = new ModelAdapter(this, getViewHolderBuilder(), modelAdapterItems.getModelAdapterItems(), getSaveItemPositionState() == SaveItemPositionState.SAVE);
        mModelAdapter.selectItem(mCurrentItemPosition);

        mDrawerList.setAdapter(mModelAdapter);
        mDrawerList.setOnItemClickListener(new ActionItemClickListener());

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

    protected abstract SaveItemPositionState getSaveItemPositionState();

    protected abstract int getActivityLayoutResourceId();

    protected int getDrawerLayoutResourceId() {
        return R.id.nvgd_drawer;
    }

    protected int getDrawerViewResourceId() { return R.id.nvgd_drawer_items_list; }

    protected int getDrawerListResourceId() {
        return R.id.nvgd_drawer_items_list;
    }

    protected abstract void onInitModelAdapterItems(ModelAdapterItems modelAdapterItems);

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

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (mModelAdapter == null || !(mModelAdapter.getItem(position) instanceof ActionModelAdapterItem)) return;

            ActionModelAdapterItem actionModelAdapterItem = (ActionModelAdapterItem) mModelAdapter.getItem(position);
            actionModelAdapterItem.onActionItemClick(mModelAdapter);

            if (mCurrentItemPosition != position && mModelAdapter.selectItem(position)) {
                setCurrentPosition(position);
            }

            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    public enum SaveItemPositionState {
        SAVE,
        DO_NOT_SAVE
    }
}
