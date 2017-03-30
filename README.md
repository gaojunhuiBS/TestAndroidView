> CoordinatorLayout学习

# Demo



## 11、[CoordinatorLayout](http://blog.csdn.net/xyz_lmn/article/details/48055919)

### 11.1、CoordinatorLayout有什么作用

CoordinatorLayout作为“super-powered FrameLayout”基本实现两个功能： 
1、作为顶层布局 
2、调度协调子布局

CoordinatorLayout使用新的思路通过协调调度子布局的形式实现触摸影响布局的形式产生动画效果。CoordinatorLayout通过设置子View的 Behaviors来调度子View。系统（Support V7）提供了AppBarLayout.Behavior, AppBarLayout.ScrollingViewBehavior, FloatingActionButton.Behavior, SwipeDismissBehavior<V extends View> 等。

使用CoordinatorLayout需要在Gradle加入Support Design Library：

```groovy
compile 'com.android.support:design:22.2.1'
```

### 11.2、CoordinatorLayout与FloatingActionButton

Demo:

定义布局文件：

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent">


    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="16dp"
        android:src="@drawable/ic_done" />

</android.support.design.widget.CoordinatorLayout>
```

CoordinatorLayout作为“super-powered FrameLayout”，设置子视图的[Android](http://lib.csdn.net/base/android):layout_gravity属性控制位置。

Activity:

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.fab).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Snackbar.make(view,"FAB",Snackbar.LENGTH_LONG)
                        .setAction("cancel", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                //这里的单击事件代表点击消除Action后的响应事件

                            }
                        })
                        .show();
            }
        });
    }
}
```

![](res/android45.gif)

FloatingActionButton是最简单的使用CoordinatorLayout的例子，FloatingActionButton默认使用FloatingActionButton.Behavior

### 11.3、CoordinatorLayout与AppBarLayout

#### AppBarLayout嵌套TabLayout

布局文件代码：

```xml
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/main_content"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <android.support.design.widget.AppBarLayout
        android:id="@+id/appbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:popupTheme="@style/ThemeOverlay.AppCompat.Light"
            app:layout_scrollFlags="scroll|enterAlways" />

        <android.support.design.widget.TabLayout
            android:id="@+id/tabs"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

    </android.support.design.widget.AppBarLayout>

    <android.support.v4.view.ViewPager
        android:id="@+id/viewpager"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior" />

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        android:layout_margin="@dimen/fab_margin"
        android:src="@drawable/ic_done" />

</android.support.design.widget.CoordinatorLayout>
```

## 12、[FloatingActionButton](http://blog.csdn.net/lmj623565791/article/details/46678867)

### 简单使用

**布局：**

```xml
  <android.support.design.widget.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right|bottom"
            android:src="@drawable/ic_discuss"
            />
```

使用非常简单，直接当成ImageView来用即可。

**效果图：**

![](res/android46.png)

- 这个填充色以及rippleColor如何自定义呢？

  默认的颜色取的是，theme中的colorAccent，所以你可以在style中定义colorAccent。

  **colorAccent 对应EditText编辑时、RadioButton选中、CheckBox等选中时的颜色。详细请参考：[Android 5.x Theme 与 ToolBar 实战](http://blog.csdn.net/lmj623565791/article/details/45303349)**

  rippleColor默认取的是theme中的colorControlHighlight。

  我们也可以直接用过属性定义这两个的颜色：

```xml
app:backgroundTint="#ff87ffeb"
app:rippleColor="#33728dff"
```

- 立体感有没有什么属性可以动态指定？

  和立体感相关有两个属性，elevation和pressedTranslationZ，前者用户设置正常显示的阴影大小；后者是点击时显示的阴影大小。大家可以自己设置尝试下。

综上，如果你希望自定义颜色、以及阴影大小，可以按照如下的方式(当然，颜色你也可以在theme中设置)：

```xml
<android.support.design.widget.FloatingActionButton
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_gravity="right|bottom"
            android:src="@drawable/ic_discuss"
            app:backgroundTint="#ff87ffeb"
            app:rippleColor="#33728dff"
            app:elevation="6dp"
            app:pressedTranslationZ="12dp"
            />
```

至于点击事件，和View的点击事件一致就不说了~~

### 5.x存在的一些问题

在5.x的设备上运行，你会发现一些问题（[测试](http://lib.csdn.net/base/softwaretest)系统5.0）：

- 木有阴影

记得设置`app:borderWidth="0dp"`。

- 按上述设置后，阴影出现了，但是竟然有矩形的边界（未设置margin时，可以看出）

需要设置一个margin的值。在5.0之前，会默认就有一个外边距（不过并非是margin，只是效果相同）。

so，良好的实践是：

- 添加属性`app:borderWidth="0dp"`
- 对于5.x设置一个合理的margin

**如下：**

```xml
 <android.support.design.widget.FloatingActionButton
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="end|bottom"
        app:borderWidth="0dp"
        android:layout_margin="@dimen/fab_margin"
        android:src="@drawable/ic_headset" />
```

values

```xml
 <dimen name="fab_margin">0dp</dimen>11
```

values-v21

```xml
 <dimen name="fab_margin">16dp</dimen>
```

### 简单实现FloatActionButton

参考[参考资料4](http://www.myandroidsolutions.com/2015/01/01/android-floating-action-button-fab-tutorial/)

可以通过drawable来实现一个简单的阴影效果：

**drawable/fab.xml**

```xml
<?xml version="1.0" encoding="utf-8"?>
<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true">
        <layer-list>
            <!-- Shadow -->
            <item android:top="1dp" android:right="1dp">
                <layer-list>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#08000000"/>
                            <padding
                                android:bottom="3px"
                                android:left="3px"
                                android:right="3px"
                                android:top="3px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#09000000"/>
                            <padding
                                android:bottom="2px"
                                android:left="2px"
                                android:right="2px"
                                android:top="2px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#10000000"/>
                            <padding
                                android:bottom="2px"
                                android:left="2px"
                                android:right="2px"
                                android:top="2px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#11000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#12000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#13000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#14000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#15000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#16000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                </layer-list>
            </item>

            <!-- Blue button pressed -->
            <item>
                <shape android:shape="oval">
                    <solid android:color="#90CAF9"/>
                </shape>
            </item>
        </layer-list>
    </item>

    <item android:state_enabled="true">

        <layer-list>
            <!-- Shadow -->
            <item android:top="2dp" android:right="1dp">
                <layer-list>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#08000000"/>
                            <padding
                                android:bottom="4px"
                                android:left="4px"
                                android:right="4px"
                                android:top="4px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#09000000"/>
                            <padding
                                android:bottom="2px"
                                android:left="2px"
                                android:right="2px"
                                android:top="2px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#10000000"/>
                            <padding
                                android:bottom="2px"
                                android:left="2px"
                                android:right="2px"
                                android:top="2px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#11000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#12000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#13000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#14000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#15000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                    <item>
                        <shape android:shape="oval">
                            <solid android:color="#16000000"/>
                            <padding
                                android:bottom="1px"
                                android:left="1px"
                                android:right="1px"
                                android:top="1px"
                                />
                        </shape>
                    </item>
                </layer-list>
            </item>

            <!-- Blue button -->

            <item>
                <shape android:shape="oval">
                    <solid android:color="#03A9F4"/>
                </shape>
            </item>
        </layer-list>

    </item>

</selector>
```

一个相当长的drawable，不过并不复杂，也比较好记忆。首先为一个View添加阴影，使用的是color从`#08000000`到`#1500000`，取的padding值为4、2、2、1…1；这样就可以为一个View的四边都添加上阴影效果。

当然了，为了阴影更加逼真，把上述的Layer-list又包含到了一个item中，并为该item设置了top和right，为了让左，下的阴影效果比上、右重，当然你可以设置其他两边，改变效果。

最后添加一个item设置为按钮的填充色（注意该item的层级）。

该drawable为一个selector，所以press和默认状态写了两次基本一致的代码，除了填充色不同。

**使用：**

```xml
 <ImageButton
        android:layout_width="56dp"
        android:layout_height="56dp"
        android:layout_gravity="bottom|right"
        android:layout_margin="20dp"
        android:background="@drawable/fab"
        android:src="@drawable/ic_done"
        />
```

**效果图：**

![img](http://img.blog.csdn.net/20150629094409924)

ok，到此FloatActionButton就介绍完毕啦~~有兴趣的话，也可以从github挑选个库看看别人的实现。

ok~

## 13、[Android 5.x Theme](http://blog.csdn.net/lmj623565791/article/details/45303349)

### 1、概述

随着Material Design的逐渐的普及，业内也有很多具有分享精神的伙伴翻译了[material design specification](http://www.google.com/design/spec/material-design/introduction.html) ，中文翻译地址：[Material Design 中文版](http://design.1sters.com/)。So，我们也开始[Android](http://lib.csdn.net/base/android) 5.x相关的blog，那么首先了解的当然是其主题的风格以及app bar。

当然，5.x普及可能还需要一段时间，所以我们还是尽可能的去使用兼容包支持低版本的设备。

### 2、Material Design的Theme

md的主题有：

- @android:style/Theme.Material (dark version)
- @android:style/Theme.Material.Light (light version)
- @android:style/Theme.Material.Light.DarkActionBar

与之对应的Compat Theme:

- Theme.AppCompat
- Theme.AppCompat.Light
- Theme.AppCompat.Light.DarkActionBar

#### （1）个性化 Color Palette

我们可以根据我们的app的风格，去定制Color Palette(调色板)，重点有以下几个属性：

```xml
<resources>
    <!-- Base application theme. -->
    <style name="AppBaseTheme" parent="Theme.AppCompat">

        <!-- customize the color palette -->
        <item name="colorPrimary">@color/material_blue_500</item>
        <item name="colorPrimaryDark">@color/material_blue_700</item>
        <item name="colorAccent">@color/material_green_A200</item>
    </style>
</resources>
```

- colorPrimary 对应ActionBar的颜色。
- colorPrimaryDark对应状态栏的颜色
- colorAccent 对应EditText编辑时、RadioButton选中、CheckBox等选中时的颜色。

与之对应的图：

![](res/android47.png)



> metarial design的theme允许我们去设置status bar的颜色，如果你项目的最小支持版本为5.0，那么你可以使用`android:Theme.Material`，设置`android:statusBarColor`。当然了这种情况目前来说比较少，所以我们多数使用的是`Theme.AppCompat`，通过设置`android:colorPrimaryDark.`来设置status bar颜色。（ps：默认情况下，`android:statusBarColor`的值继承自`android:colorPrimaryDark`）.

#### （2）测试效果

values/styles.xml

```xml
<resources>
    <!-- Base application theme. -->

    <style name="AppTheme" parent="AppBaseTheme">


    </style>

    <style name="AppBaseTheme" parent="Theme.AppCompat.Light">

        <!-- customize the color palette -->
        <item name="colorPrimary">@color/material_blue_500</item>
        <item name="colorPrimaryDark">@color/material_blue_700</item>
        <item name="colorAccent">@color/material_green_A200</item>

    </style>


</resources>
```

values-v21/styles.xml

```xml
<resources>

    <style name="AppTheme" parent="AppBaseTheme">
        <item name="android:statusBarColor">@color/material_blue_700</item>
    </style>


</resources>
```

values/colors.xml

```xml
<?xml version="1.0" encoding="utf-8"?>
<resources>
    <color name="material_blue_500">#009688</color>
    <color name="material_blue_700">#00796B</color>
    <color name="material_green_A200">#FD87A9</color>
</resources>
```

![](res/android48.png)

可以看到：colorAccent也就是图中的粉色，EditText正在输入时，RadioButton选中时的颜色。ps:5.0以下设备，状态栏颜色不会变化。

## 14、 [ToolBar](http://blog.csdn.net/lmj623565791/article/details/45303349)

众所周知，在使用ActionBar的时候

一堆的问题：

- 这个文字能不能定制，位置能不能改变
- 图标的间距怎么控制神马的，

由此暴露出了ActionBar设计的不灵活。为此官方提供了**ToolBar**，并且提供了supprot library用于向下兼容。

Toolbar之所以灵活，是因为它其实就是一个ViewGroup，我们在使用的时候和普通的组件一样，在布局文件中声明。

### （1）ToolBar的引入

既然准备用ToolBar，首先看看如何将其引入到app中。

#### 1）隐藏原本的ActionBar

隐藏可以通过修改我们继承的主题为：`Theme.AppCompat.Light.NoActionBar`，当然也可以通过设置以下属性完成：

```xml
<item name="windowActionBar">false</item>
<item name="android:windowNoTitle">true</item>
```

我们这里选择前者：

```xml
<style name="AppBaseTheme" parent="Theme.AppCompat.Light.NoActionBar">

        <!-- customize the color palette -->
        <item name="colorPrimary">@color/material_blue_500</item>
        <item name="colorPrimaryDark">@color/material_blue_700</item>
        <item name="colorAccent">@color/material_green_A200</item>

    </style>
```

#### 2）在布局文件中声明

```xml
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:orientation="vertical"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto">

    <android.support.v7.widget.Toolbar
        android:id="@+id/id_toolbar"
        android:layout_height="wrap_content"
        android:layout_width="match_parent" />

    <android.support.v7.widget.GridLayout
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_gravity="center_horizontal"

        app:useDefaultMargins="true"
        app:columnCount="3">


        <TextView
            android:text="First Name:"
            app:layout_gravity="right" />

        <EditText
            android:ems="10"
            app:layout_columnSpan="2" />

        <TextView
            android:text="Last Name:"

            app:layout_column="0"
            app:layout_gravity="right" />

        <EditText
            android:ems="10"
            app:layout_columnSpan="2" />


        <TextView
            android:text="Visit Type:"

            app:layout_column="0"
            app:layout_gravity="right" />

        <RadioGroup app:layout_columnSpan="2">

            <RadioButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Business" />


            <RadioButton
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:text="Social" />

        </RadioGroup>

        <Button
            android:text="Ok"
            app:layout_column="1" />

        <Button
            android:text="Cancel"
            app:layout_column="2" />

    </android.support.v7.widget.GridLayout>

</LinearLayout>
```

ok，这里我们也贴出来上面图片的效果的xml，使用GridLayout实现的，有兴趣的可以研究下。可以看到我们在布局文件中定义了ToolBar。

#### 3）代码中设定

```java
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);
        setSupportActionBar(toolbar);
    }
```

ok,基本就是先隐藏ActionBar，然后在布局文件中声明，最后代码中设定一下。现在看一下效果图：

![](res/android49.png)



可以看到我们的ToolBar显示出来了，默认的Title为ToolBar，但是这个样式实在是不敢恭维，下面看我们如何定制它。

### （2）定制ToolBar

首先给它一个nice的背景色，还记得前面的colorPrimary么，用于控制ActionBar的背景色的。当然这里我们的ToolBar就是一个普通的ViewGroup在布局中，所以我们直接使用background就好，值可以为：`?attr/colorPrimary`使用主题中定义的值。

ToolBar中包含Nav Icon , Logo , Title , Sub Title , Menu Items 。

我们可以通过代码设置上述ToolBar中的控件：

```java
@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.id_toolbar);

        // App Logo
        toolbar.setLogo(R.mipmap.ic_launcher);
        // Title
        toolbar.setTitle("App Title");
        // Sub Title
        toolbar.setSubtitle("Sub title");

        setSupportActionBar(toolbar);
        //Navigation Icon
        toolbar.setNavigationIcon(R.drawable.ic_toc_white_24dp);
    }
```

`可选方案`当然如果你喜欢，也可以在布局文件中去设置部分属性：

```xml
 <android.support.v7.widget.Toolbar
        android:id="@+id/id_toolbar"
        app:title="App Title"
        app:subtitle="Sub Title"
        app:navigationIcon="@drawable/ic_toc_white_24dp"
        android:layout_height="wrap_content"
        android:minHeight="?attr/actionBarSize"
        android:layout_width="match_parent"
        android:background="?attr/colorPrimary"/>
```

至于Menu Item，依然支持在menu/menu_main.xml去声明，然后复写`onCreateOptionsMenu`和`onOptionsItemSelected`即可。

`可选方案`也可以通过`toolbar.setOnMenuItemClickListener`实现点击MenuItem的回调。

```java
 toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                return false;
            }
        });
```

效果图：

![](res/android50.png)



关于字体的样式

可以在布局文件设置属性`app:titleTextAppearance`、`app:subtitleTextAppearance`

或者代码`setTitleTextAppearance`、`setSubTitleTextAppearance`设置。

### （3）实战

简单介绍了Toolbar以后呢，我们决定做点有意思的事，整合ToolBar，DrawerLayout，ActionBarDrawerToggle写个实用的例子，效果图如下：



## 15、[TabLayout的用法](http://www.cnblogs.com/JohnTsai/p/4715454.html)

### 1、前言

Google官方在14年Google I/O上推出了全新的设计语言——Material Design。一并推出了一系列实现Material Design效果的控件库——Android Design Support Library。其中，有TabLayout, NavigationView,Floating labels for editing text,Floating Action Button,Snackbar, CoordinatorLayout, CollapsingToolbarLayout等等控件。在今后的学习中，我将一一介绍它们的特点和用法。

在移动应用中切换不同场景/功能，iOS中以底部三按钮、四按钮来实现的，而在Android中，则是抽屉式菜单或左右滑动式设计的。如何实现类似Google Play应用商店式的左右滑动，这就得靠TabLayout来实现了。

![](res/android51.png)

### 2、正文

1.获得Android Design Support Library库：

在Gradle文件中的dependency中添加

```
'compile 'com.android.support:design:22.2.1'
```

依赖。

2.定义布局文件：
通过使用可知，上面那些标签时通过TabLayout实现，而下面内容的变化则是ViewPager+Fragment实现的。
因此在MainActivity中：

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:orientation="vertical">
    <android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:tabMode="scrollable"
        />
    <android.support.v4.view.ViewPager
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        android:layout_weight="1"
        android:background="#ffffff"
        />
</LinearLayout>
```

Fragment：
切换ViewPager，显示不同的Fragment，在这里用一个布局相同的Fragment示例。

```xml
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical">
    <TextView
        android:id="@+id/textView"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:gravity="center"
        />
</LinearLayout>
```

3.具体实现代码：
1）创建Fragment

```java
public class PageFragment extends Fragment {
    public static final String ARGS_PAGE = "args_page";
    private int mPage;

    public static PageFragment newInstance(int page) {
        Bundle args = new Bundle();

        args.putInt(ARGS_PAGE, page);
        PageFragment fragment = new PageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARGS_PAGE);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page,container,false);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        textView.setText("第"+mPage+"页");
        return view;
    }
}
```

2)适配器类

```java
class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    public final int COUNT = 5;
    private String[] titles = new String[]{"Tab1", "Tab2", "Tab3", "Tab4", "Tab5"};
    private Context context;

    public MyFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        return PageFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}
```

相信Fragment+ViewPager+FragmentPagerAdapter的组合，大家已经用得很熟悉了，在这里我就不介绍了。

3）TabLayout的使用：

根据官方文档说明，TabLayout的使用有以下两种方式：

1. 通过TabLayout的addTab()方法添加新构建的Tab实例到TabLayout中：

   ```java
     TabLayout tabLayout = ...;
     tabLayout.addTab(tabLayout.newTab().setText("Tab 1"));
     tabLayout.addTab(tabLayout.newTab().setText("Tab 2"));
     tabLayout.addTab(tabLayout.newTab().setText("Tab 3"));
   ```

2. 第二种则是使用ViewPager和TabLayout一站式管理Tab，也就是说不需要像第一种方式那样手动添加Tab：

   ```java
    ViewPager viewPager = ...;
   TabLayout tabLayout = ...;
   viewPager.addOnPageChangeListener(new TabLayoutOnPageChangeListener(tabLayout));
   ```

   而我们TabLayout的Demo就是用得第二种方式：

   ```java
    //Fragment+ViewPager+FragmentViewPager组合的使用
           ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
           MyFragmentPagerAdapter adapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),
                   this);
           viewPager.setAdapter(adapter);

           //TabLayout
           TabLayout tabLayout = (TabLayout) findViewById(R.id.tabLayout);
           tabLayout.setupWithViewPager(viewPager);
   ```

   运行效果

   ![](res/android52.gif)

效果不错，但是TabLayout中的Tab似乎没有占满屏幕的宽度。如何解决呢？
有代码和XML两种方式：
1).代码

```java
 tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
    tabLayout.setTabMode(TabLayout.MODE_FIXED);
```

2).XML布局文件

```xml
<android.support.design.widget.TabLayout
        android:id="@+id/tabLayout"
        app:tabGravity="fill"
        app:tabMode="fixed"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        />
```

下面就来解释一下TabGravity和TabMode

TabGravity:放置Tab的Gravity,有

- GRAVITY_CENTER 

- GRAVITY_FILL

  两种效果。顾名思义，一个是居中

  另一个是尽可能的填充（**注意，GRAVITY_FILL需要和MODE_FIXED一起使用才有效果**）

TabMode:布局中Tab的行为模式（behavior mode），

有两种值：

- MODE_FIXED 
- MODE_SCROLLABLE。

MODE_FIXED:固定tabs，并同时显示所有的tabs。

MODE_SCROLLABLE：可滚动tabs，显示一部分tabs，在这个模式下能包含长标签和大量的tabs，最好用于用户不需要直接比较tabs。

下面用代码来比较这两种模式的不同：

```java
class MyFragmentPagerAdapter extends FragmentPagerAdapter {
    //tabs的数据集
    public final int COUNT = 10;
    private String[] titles = new String[]{"Tab2221", "T2", "Tb3", "Tab4", "Tab5555555555","Tab2221", "T2", "Tb3", "Tab4", "Tab5555555555"};
...
}


        //1.MODE_SCROLLABLE模式
       tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        //2.MODE_FIXED模式
        tabLayout.setTabMode(TabLayout.MODE_FIXED);
```

1.MODE_SCROLLABLE模式

![](res/android53.gif)

2.MODE_FIXED模式

![](res/android54.gif)



## 16、[AppBarLayout](http://www.jianshu.com/p/d159f0176576)

> AppBarLayout继承自LinearLayout，布局方向为垂直方向。所以你可以把它当成垂直布局的LinearLayout来使用。AppBarLayout是在LinearLayou上加了一些材料设计的概念，它可以让你定制当**某个可滚动View**的滚动手势发生变化时，其内部的子View实现何种动作。

**请注意**：上面提到的**某个可滚动View**，可以理解为某个ScrollView。怎么理解上面的话呢？就是说，当某个ScrollView发生滚动时，你可以定制你的“顶部栏”应该执行哪些动作（如跟着一起滚动、保持不动等等）。那某个可移动的View到底是哪个可移动的View呢？这是由你自己指定的！如何指定，我们后面说。

### 1 、AppBarLayout子View的动作

内部的子View通过在布局中加`app:layout_scrollFlags`设置执行的动作，那么`app:layout_scrollFlags`可以设置哪些动作呢？分别如下：

> （1） `scroll`:值设为`scroll`的View会跟随滚动事件一起发生移动。

什么意思呢？简单的说，就是当指定的ScrollView发生滚动时，该View也跟随一起滚动，就好像这个View也是属于这个ScrollView一样。

一张gif足以说明：

![scroll](res/android55.gif)

对应的布局文件

```xml
<android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?android:attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll" />
</android.support.design.widget.AppBarLayout>
```

完整布局示例

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/activity_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.pix.appbarlayoutdemo.MainActivity">
    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">
        <android.support.v7.widget.Toolbar
            android:id="@+id/toolbar"
            android:layout_width="match_parent"
            android:layout_height="?android:attr/actionBarSize"
            android:background="?attr/colorPrimary"
            app:layout_scrollFlags="scroll" />

    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        app:layout_behavior="@string/appbar_scrolling_view_behavior"
        >
        <TextView
            android:layout_width="match_parent"
            android:layout_height="match_parent"
            android:textSize="34sp"
            android:background="#fff"
            android:text="文字一大段。。。。。"


            />
    </android.support.v4.widget.NestedScrollView>
</android.support.design.widget.CoordinatorLayout>
```





> （2） `enterAlways`:值设为`enterAlways`的View,当ScrollView往下滚动时，该View会直接往下滚动。而不用考虑ScrollView是否在滚动。

看个动画片（Y(^o^)Y）（ToolBar高度设为：`?android:attr/actionBarSize`,`app:layout_scrollFlags="scroll|enterAlways"`）：

![](res/android56.gif)



> （3） `exitUntilCollapsed`：值设为`exitUntilCollapsed`的View，当这个View要往上逐渐“消逝”时，会一直往上滑动，直到剩下的的高度达到它的最小高度后，再响应ScrollView的内部滑动事件。

怎么理解呢？简单解释：在ScrollView往上滑动时，首先是View把滑动事件“夺走”，由View去执行滑动，直到滑动最小高度后，把这个滑动事件“还”回去，让ScrollView内部去上滑。看个gif感受一下（图中将高度设的比较大:200dp，并将最小高度设置为`?android:attr/actionBarSize`,`app:layout_scrollFlags="scroll|exitUntilCollapsed"`）：

![](res/android57.gif)

> （4） `enterAlwaysCollapsed`：是`enterAlways`的附加选项，一般跟`enterAlways`一起使用，它是指，View在往下“出现”的时候，首先是`enterAlways`效果，当View的高度达到最小高度时，View就暂时不去往下滚动，直到ScrollView滑动到顶部不再滑动时，View再继续往下滑动，直到滑到View的顶部结束。

来个gif感受一下（图中将高度设的比较大:200dp，并将最小高度设置为`?android:attr/actionBarSize`,`app:layout_scrollFlags="scroll|enerAlways|enterAlwaysCollapsed"`）

![](res/android58.gif)

### 2、将AppBarLayout与ScrollView关联起来

前面说了一直反复说“当ScrollView发生滚动时”，那么怎么将AppBarLayout与ScrollView关联起来呢？我们注意到，AppBarLayout与ScrollView之间动作“相互依赖”，这不就是我们上一篇[《CoordinateLayout的使用如此简单 》](http://blog.csdn.net/huachao1001/article/details/51554608)所学的内容吗？把ScrollView和AppBarLayout作为CoordinateLayout的子View，然后编写一个Behavior，在这个Behavior里面判断当前的操作是应该让ScrollView时刻保持在AppBarLayout之下（即只要改变AppBarLayout的位置就可以一起滑动），还是应该让ScrollView内部滚动而不让AppBarLayout位置发生变化等等这些需求，都是可以在Behavior里面处理的。你可以去针对你的ScrollView编写Behavior。然而，我们看到我们的AppBarLayout事先的功能比较复杂，如果我们自己去定义这样的效果，代码非常复杂，还要考虑很多方面，好在Android帮我们写好啦，我们直接用就是了，这个ScrollView就是NestedScrollView，请注意，它并没有继承ScrollView，它继承的是FrameLayout，但是它实现的效果把它可以看成是ScrollView。



把NestedScrollView放入到我们的layout文件里面就可以啦~~~，很方便~

```xml
<android.support.v4.widget.NestedScrollView

        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

       <!--将你的内容放在这里-->

    </android.support.v4.widget.NestedScrollView>
```

有没有注意到有个属性：`app:layout_behavior="@string/appbar_scrolling_view_behavior"`,它就是指定Behavior的，`appbar_scrolling_view_behavior`对应的类的名称是：`android.support.design.widget.AppBarLayout$ScrollingViewBehavior`感兴趣的可以去分析源码。

好了，我们现在会用AppBarLayout啦~是不是发现用起来so easy!接下来我们把剩下`CollapsingToolbarLayout`的给"消化"掉！



## 17、CollapsingToolbarLayout

`CollapsingToolbarLayout`是用来对`Toolbar`进行再次包装的`ViewGroup`，主要是用于实现折叠（其实就是看起来像伸缩~）的App Bar效果。它需要放在`AppBarLayout`布局里面，并且作为`AppBarLayout`的直接子`View`。`CollapsingToolbarLayout`主要包括几个功能（**参照了官方网站上内容，略加自己的理解进行解释**）：

> (1) 折叠Title（Collapsing title）：当布局内容全部显示出来时，title是最大的，但是随着View逐步移出屏幕顶部，title变得越来越小。你可以通过调用setTitle函数来设置title。
>
> (2)内容纱布（Content scrim）：根据滚动的位置是否到达一个阀值，来决定是否对View“盖上纱布”。可以通过setContentScrim(Drawable)来设置纱布的图片.
>
> (3)状态栏纱布（Status bar scrim)：根据滚动位置是否到达一个阀值决定是否对状态栏“盖上纱布”，你可以通过`setStatusBarScrim(Drawable)`来设置纱布图片，但是只能在`LOLLIPOP`设备上面有作用。
>
> (4)视差滚动子View(Parallax scrolling children):子View可以选择在当前的布局当时是否以“视差”的方式来跟随滚动。（**PS:其实就是让这个View的滚动的速度比其他正常滚动的View速度稍微慢一点**）。将布局参数`app:layout_collapseMode`设为`parallax`
>
> (5)将子View位置固定(Pinned position children)：子View可以选择是否在全局空间上固定位置，这对于Toolbar来说非常有用，因为当布局在移动时，可以将Toolbar固定位置而不受移动的影响。 将`app:layout_collapseMode`设为`pin`。

了解这些概念后，我们来看看布局吧~

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:layout_width="match_parent"
    android:layout_height="match_parent" >


    <android.support.design.widget.AppBarLayout
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:theme="@style/ThemeOverlay.AppCompat.Dark.ActionBar">

        <android.support.design.widget.CollapsingToolbarLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            app:expandedTitleMarginEnd="64dp"
            app:expandedTitleMarginStart="48dp"
            app:layout_scrollFlags="scroll|exitUntilCollapsed">

            <ImageView
                android:id="@+id/main.backdrop"
                android:layout_width="wrap_content"
                android:layout_height="300dp"
                android:scaleType="centerCrop"
                android:src="@drawable/material_img"
                app:layout_collapseMode="parallax" />

            <android.support.v7.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="?android:attr/actionBarSize"
                app:layout_collapseMode="pin"  />

        </android.support.design.widget.CollapsingToolbarLayout>
    </android.support.design.widget.AppBarLayout>

    <android.support.v4.widget.NestedScrollView

        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:paddingTop="50dp"
        app:layout_behavior="@string/appbar_scrolling_view_behavior">

        <TextView
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:text="@string/my_txt"
            android:textSize="20sp" />

    </android.support.v4.widget.NestedScrollView>

</android.support.design.widget.CoordinatorLayout>
```

上面的都看得懂吧，每个陌生的属性都是讲过的哦，忘记了的话回头看，稍微解释一下，图片被设置为有视差的滑动，Toolbar设置为固定不动，另外，CollapsingToolbarLayout会对title进行放大和缩小，我们看看效果吧~

如果你希望拖动过程中状态栏是透明的，可以在CollapsingToolbarLayout中加 app:statusBarScrim="@android:color/transparent"，并且在onCreate中调用getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)将状态栏设置为透明就好啦~