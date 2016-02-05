# ShapableLoadingView
----
##Intro

ShapableLoadingView 是高度可配置且可自定义的进度条控件，你可以设置任何可通过 Path 绘制的图形作为动画元素。

[English Version](README_ENGLISH.md)

## Preview

![](snapshot.gif)

## 使用

### 1. 添加依赖

`
dependencies {
    ...
    compile 'com.github.chaossss:ShapableLoadingView:1.0.1'
}
`

### 2. 创建 xml 代码

such as:

```xml
<com.github.chaossss.shapableloadingview.ShapableLoadingView
            android:layout_margin="30dp"
            android:layout_width="100dp"
            android:layout_height="100dp"
            lib:numbers="3"
            lib:movement_cycle_time="3000"
            lib:rotate_cycle_time="100"
            lib:enable_rotate_animation="true"
            lib:enable_size_animation="true"
            lib:size_cycle_time="500"
            lib:min_shape_size="5dp"
            lib:max_shape_size="12dp"
            lib:shape_colors="@array/colors"
            android:layout_gravity="center" />
```

##Attr

| Attr | usage |
|---------|--------|
| numbers | 动画元素的数量 |
| size_cycle_time | 动画元素从最小变为最大的时间 |
| rotate_cycle_time | 动画元素旋转360度所需时间 |
| movement_cycle_time | 动画元素从路径起点到终点所需时间 |
| shape_colors | 动画元素颜色集 |
| shape_size | 动画元素大小 |
| enable_size_animation | 是否允许大小变化的动画 |
| enable_rotate_animation | 是否允许旋转动画 |
| min_shape_size | 动画元素的最小尺寸 |
| max_shape_size | 动画元素的最大尺寸 |
| path_type | 动画元素的运动路径 |
| shape_type | 动画元素的形状 |

###Plus

属性 "path_type" 可用项:
-  infinite
-  square
-  triangle
-  circle
-  diamond
-  star
 
属性 "shape_type" 可用项:
-  ball
-  star

## 自定义

### 1. 创建你需要的动画路径或图形
```
public class YourPath extends AbstractPath {
    public YourPath(Point center, int pathWidth, int pathHeight, int maxBallSize) {
        super(center, pathWidth, pathHeight, maxBallSize);
    }

	//Draw your path here, and return it
    @Override
    public Path draw() {
        return null;
    }
}
```

```java
public class YourShape extends Shape {
    public YourShape(int size, int color) {
        super(size, color);
    }

	//Init points u need. Be careful, don't delete
	//the line of code 'super.initPoint();'
    @Override
    protected void initPoint() {
        super.initPoint();
    }

	//Update your point if needed
    @Override
    protected void updatePoint() {
        super.updatePoint();
    }

	//Draw your shape here
    @Override
    public Path getShapePath() {
        return null;
    }
}
```

### 创建你的自定义 View
```java
public class YourShapableLoadingView extends ShapableLoadingView {
    public YourShapableLoadingView(Context context) {
        super(context);
    }

    public YourShapableLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public YourShapableLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

	//Return your path
    @Override
    protected Path getPath(int w, int h) {
        return super.getPath(w, h);
    }

	//Return your shape
    @Override
    protected Shape getShape(int color) {
        return super.getShape(color);
    }
}
```

## 感谢

非常感谢 [**glomadrian**](https://github.com/glomadrian) 同意让我基于他的库 [**loading-balls**](https://github.com/glomadrian/loading-balls) 开发本项目，为本项目添加了新的特性以及更好的可拓展性。

License
============

    Copyright 2014 chaossss

	Licensed under the Apache License, Version 2.0 (the "License");
	you may not use this file except in compliance with the License.
	You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

	Unless required by applicable law or agreed to in writing, software
	distributed under the License is distributed on an "AS IS" BASIS,
	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
	See the License for the specific language governing permissions and
	limitations under the License.
