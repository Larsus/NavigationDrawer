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
    public <T extends BaseViewHolder<? extends BaseModel>> T build(View convertView, BaseModel baseModel, Class<T> type) {
        BaseViewHolder baseViewHolder = null;

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
            baseViewHolder = (BaseViewHolder) constructor.newInstance(convertView, baseModel);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return (T) baseViewHolder;
    }

    @Override
    protected void finalize() throws Throwable {
        typeCache.clear();
        super.finalize();
    }
}
