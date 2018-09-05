![XPlaceHolder](http://7xvdj7.com1.z0.glb.clouddn.com/20180903153590637725724.png)

## What is XPlaceHolder?
:fire: An elegant and highly expandable android library for displaying different state of app like loading, empty, error and successful state.

## Features

- :+1: Support multiple states such as loading, empty, error, successful state.
- :+1: Less invasion of the business code.
- :+1: Reduce layout levels as much as possible.
- :+1: Support activity, fragment, view, and view in xml.
- :+1: Support set which state can be clicked?
- :+1: Support for customizing different state layouts.
- :+1: Support ViewPager + Fragment.
- :+1: Expand Easily.

## ScreenShots

The following screenshots are respectively：
1. All demos
2. activity
3. Fragment
4. fragment + ViewPager
5. View
6. used as common widget in xml

![20180906153616828034829.png](http://7xvdj7.com1.z0.glb.clouddn.com/20180906153616828034829.png)![2018090615361682989840.gif](http://7xvdj7.com1.z0.glb.clouddn.com/2018090615361682989840.gif)![20180906153616830825231.gif](http://7xvdj7.com1.z0.glb.clouddn.com/20180906153616830825231.gif)![20180906153616831752910.gif](http://7xvdj7.com1.z0.glb.clouddn.com/20180906153616831752910.gif)![20180906153616832942007.gif](http://7xvdj7.com1.z0.glb.clouddn.com/20180906153616832942007.gif)![20180906153616833871218.gif](http://7xvdj7.com1.z0.glb.clouddn.com/20180906153616833871218.gif)


## Have a Try

Download [xplaceholder-demo.apk](https://github.com/hust201010701/XPlaceHolder/blob/master/apk/xplaceholder-demo.apk).

## Getting Started

We need to take 3 steps to show placeholder for activty or view.

### 1. Add library

Add below code to your `build.gradle`: `compile 'com.orzangleli:xplaceholder:1.0.0'``

### 2. Configuring custom layout

Different apps have different fail, empty and loading layout.So you need to configure
your own placeholder layout.

#### 1. Create your own Entity class for placeholder

You can add some variables in your class.

For more details, you can refer [ImageAndTextPlaceHolderVo.java](https://github.com/hust201010701/XPlaceHolder/blob/master/app/src/main/java/com/orzangleli/xplaceholder/placeholder/ImageAndTextPlaceHolderVo.java)

#### 2. Implements IPlaceHolderLayout

You need to implements all methods of `IPlaceHolderLayout`. Be careful, `bindView` is not be called when initializing, `bindView` is be call when the first time you show empty, error, or loading layout.So when you override `bindState`, you need to check whether view is null.

For more details, you can refer [ImageAndTextPlaceHolderLayout.java](https://github.com/hust201010701/XPlaceHolder/blob/master/app/src/main/java/com/orzangleli/xplaceholder/placeholder/ImageAndTextPlaceHolderLayout.java).

### 3. Attach activty or view to placeholder layout

- In activity, you can add code like this:

```
mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
mImageAndTextPlaceHolderLayout.setPlaceHolderVo(new ImageAndTextPlaceHolderVo.Builder()
        .setLoadingImageResource(R.drawable.icon_loading)
        .setEmptyImageResource(R.drawable.icon_empty)
        .setErrorImageResource(R.drawable.icon_error)
        .setLottieFileName("lottie/lego_loader.json")
        .setLoadingText(getString(R.string.loading_tip))
        .setEmptyText(getString(R.string.empty_tip))
        .setErrorText(getString(R.string.error_tip))
        .setEnableLottie(true)
        .build());
XPlaceHolderUtil.attach(this, mImageAndTextPlaceHolderLayout, this);
```
- In fragment, in `onCreateView` you need to return placeholder layout not your original view, like this:

```
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
                         Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.fragment_blank, container, false);
    ...
    mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this.getContext());
    ImageAndTextPlaceHolderVo imageAndTextPlaceHolderVo = new ImageAndTextPlaceHolderVo.Builder()
            .setLoadingText("loading...")
            .setLoadingImageResource(R.drawable.icon_loading)
            .setEmptyText("empty data")
            .setEmptyImageResource(R.drawable.icon_empty)
            .setErrorText("load error, click to retry")
            .setErrorImageResource(R.drawable.icon_error)
            .build();
    mImageAndTextPlaceHolderLayout.setPlaceHolderVo(imageAndTextPlaceHolderVo);
    XPlaceHolderUtil.attach(view, mImageAndTextPlaceHolderLayout, this);

    // 默认只有错误页面可以点击重试，可以设置
    mImageAndTextPlaceHolderLayout.setAvailableStateForClick(new State[]{State.EMPTY, State.ERROR});

    // 注意这里不再是返回之前的view
    return mImageAndTextPlaceHolderLayout;
}
```
- In view, just use `XPlaceHolderUtil.attach`

```
mTextView = findViewById(R.id.textView);

mImageAndTextPlaceHolderLayout = new ImageAndTextPlaceHolderLayout(this);
ImageAndTextPlaceHolderVo imageAndTextPlaceHolderVo = new ImageAndTextPlaceHolderVo.Builder()
        .setLoadingText("loading...")
        .setLoadingImageResource(R.drawable.icon_loading)
        .setEmptyText("empty data")
        .setEmptyImageResource(R.drawable.icon_empty)
        .setErrorText("load error")
        .setErrorImageResource(R.drawable.icon_error)
        .build();
mImageAndTextPlaceHolderLayout.setPlaceHolderVo(imageAndTextPlaceHolderVo);

XPlaceHolderUtil.attach(mTextView, mImageAndTextPlaceHolderLayout, this);
```
- Used as common widget
Define in xml:
```
<com.orzangleli.xplaceholder.placeholder.ImageAndTextPlaceHolderLayout
    android:id="@+id/placeHolderLayout"
    android:layout_width="match_parent"
    android:layout_height="400dp"
    android:background="#0aa"
    app:layout_constraintTop_toTopOf="parent"
    />
```
Set placeholder layout state in Java:
```
mImageAndTextPlaceHolderLayout = this.findViewById(R.id.placeHolderLayout);
mImageAndTextPlaceHolderLayout.showEmpty();
```
For more details, you can refer [ImageAndTextPlaceHolderLayout Demos ](https://github.com/hust201010701/XPlaceHolder/tree/master/app/src/main/java/com/orzangleli/xplaceholder).

## How does XPlaceHolder works?

This section will be added soon.

## Support

- Issues
