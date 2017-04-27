# MsgViewProject
加载,错误提示组件

##效果演示:

###主要提供两种效果,正在加载和加载失败,并且提供了加载失败的重试事件,方法列表如下:

方法名 | 作用
-------- | ---
`setText(String text)` | 设置显示的文字
`setStatue(int)` | 设置当前的状态
`setLoadingDrawable(Drawable drawable)` | 设置加载的图标
`setErrorDrawable(Drawable errorDrawable)` | 设置失败的时候显示的图标
`setReloadListener` | 设置失败时候点击图标的事件
`build()` | 生效以上的设置选项

###  注 :在进行设置之后必须调用`build()`方法,不然设置不会生效
### 代码示例:
#### 设置正在加载:
```
mMsgView.setText("加载中").setStatue(MsgView.STATUE_LOADING).setLoadingDrawable(getResources().getDrawable(R.mipmap.ic_launcher)).build();
```
#### 设置加载失败:
```
mMsgView.setText("加载失败,点击重试").setStatue(MsgView.STATUE_FAILED).setReloadListener(new MsgView.OnReloadListener() {
                    @Override
                    public void reload() {
                        ...
                    }
                }).build();
```
### 使用方式:
#### MsgView主要用于加载的提示,所以应该与要显示内容的布局处于同一个FrameLayout或者RelativeLayout内,并且在适当的时候设置Visiable属性
