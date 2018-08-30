package com.orzangleli.xplaceholder;

/**
 * <p>description：
 * <p>===============================
 * <p>creator：lixiancheng
 * <p>create time：2018/8/30 下午8:06
 * <p>===============================
 * <p>reasons for modification：
 * <p>Modifier：
 * <p>Modify time：
 * <p>@version
 */

class ImageAndTextPlaceHolderVo {
    public int loadingImageResource;
    public int emptyImageResource;
    public int errorImageResource;

    public String loadingText;
    public String emptyText;
    public String errorText;

    private Builder mBuilder;

    private ImageAndTextPlaceHolderVo() {

    }

    private ImageAndTextPlaceHolderVo(Builder builder) {
        mBuilder = builder;
        loadingImageResource = builder.loadingImageResource;
        emptyImageResource = builder.emptyImageResource;
        errorImageResource = builder.errorImageResource;

        loadingText = builder.loadingText;
        emptyText = builder.emptyText;
        errorText = builder.errorText;
    }

    public static class Builder {
        public int loadingImageResource;
        public int emptyImageResource;
        public int errorImageResource;

        public String loadingText;
        public String emptyText;
        public String errorText;

        public Builder setLoadingImageResource(int loadingImageResource) {
            this.loadingImageResource = loadingImageResource;
            return this;
        }

        public Builder setEmptyImageResource(int emptyImageResource) {
            this.emptyImageResource = emptyImageResource;
            return this;
        }

        public Builder setErrorImageResource(int errorImageResource) {
            this.errorImageResource = errorImageResource;
            return this;
        }

        public Builder setLoadingText(String loadingText) {
            this.loadingText = loadingText;
            return this;
        }

        public Builder setEmptyText(String emptyText) {
            this.emptyText = emptyText;
            return this;
        }

        public Builder setErrorText(String errorText) {
            this.errorText = errorText;
            return this;
        }

        public ImageAndTextPlaceHolderVo build() {
            return new ImageAndTextPlaceHolderVo(this);
        }
    }

    public Builder getBuilder() {
        return mBuilder;
    }
}
