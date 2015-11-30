package com.github.larsus.nvgd.viewholder;

import android.view.View;

import com.github.larsus.nvgd.model.BaseModel;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public class DefaultViewHolderBuilder implements ViewHolderBuilder {

    private Map<Class<? extends BaseViewHolder<? extends BaseModel>>, Constructor<?>> typeCache = new ConcurrentHashMap<>();

    @Override
    public <TViewHolder extends BaseViewHolder<? extends BaseModel>, TModel extends BaseModel> TViewHolder build(View convertView, TModel baseModel, Class<TViewHolder> type) {
        TViewHolder baseViewHolder = null;

        Constructor<?> constructor = null;

        if (!typeCache.containsKey(type)) {
            try {
                constructor = type.getConstructor(View.class, baseModel.getClass());
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            if (constructor != null)
                typeCache.put(type, constructor);
        } else {
            constructor = typeCache.get(type);
        }

        try {
            assert constructor != null;
            baseViewHolder = (TViewHolder) constructor.newInstance(convertView, baseModel);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return baseViewHolder;
    }

    @Override
    protected void finalize() throws Throwable {
        typeCache.clear();
        super.finalize();
    }
}
