package com.github.larsus.nvgd.model;

/**
 * @author Larsus
 * @version 1.0
 * @since 15.03.2015
 */
public abstract class BaseModel {

    private ModelType modelType;

    protected BaseModel(ModelType modelType) {
        this.modelType = modelType;
    }

    public int getViewType() {
        return modelType.viewType;
    }

    public boolean isEnabled() {
        return modelType.enabled;
    }

    public enum ModelType {
        HEADER(0, false),
        ACTION(1, true),
        SEPARATOR(2, false),
        STATE(3, false);

        private final int viewType;
        private boolean enabled;

        ModelType(int viewType, boolean enabled) {
            this.viewType = viewType;
            this.enabled = enabled;
        }

        public static ModelType getFromViewType(int viewType) {
            switch (viewType) {
                case 0:
                    return ModelType.HEADER;
                case 1:
                    return ModelType.ACTION;
                case 2:
                    return ModelType.SEPARATOR;
                case 3:
                    return ModelType.STATE;
                default:
                    return null;
            }
        }
    }
}