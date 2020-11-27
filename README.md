VariedItemDecoration是通用的Recycler ItemDecoration。可实现在一个列表里显示多种不同样式的ItemDecoration；可定制每个item的ItemDecoration的显示和样式。使用方便，并且提供非常简单的自定义ItemDecoration方式，根据position返回对应item的装饰drawable和大小。

### 引入依赖

在Project的build.gradle在添加以下代码

```groovy
allprojects {
      repositories {
         ...
         maven { url 'https://jitpack.io' }
      }
   }
```
在Module的build.gradle在添加以下代码
```groovy
// 使用了Androidx
implementation 'com.github.donkingliang:VariedItemDecoration:1.0.0'

// 或者

// 使用Android support包
implementation 'com.github.donkingliang:VariedItemDecoration:1.0.0-support'
```

### 使用示例

简单使用：框架提供了LinearItemDecoration和GridItemDecoration两个ItemDecoration类。对应给使用LinearLayoutManager和GridLayoutManager的RecyclerView添加装饰。
```java
// 给recyclerView添加ItemDecoration，size是间隔大小，drawable是装饰，可以为空。
recyclerView.setLayoutManager(new LinearLayoutManager(this));
recyclerView.addItemDecoration(new LinearItemDecoration(size, drawable));

// 给recyclerView添加ItemDecoration，GridItemDecoration支持设置垂直间隔和水平间隔的装饰，drawable可以为null。
recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
recyclerView.addItemDecoration(new GridItemDecoration(rowDividerSize,rowDivider,columnDividerSize, columnDivider));


// 显示最后item的Divider，默认false
decoration.setShowLastDivider(true);
```

自定义ItemDecoration：框架提供了LinearVariedItemDecoration和GridVariedItemDecoration两个ItemDecoration基类。使用这两个类可以自定义出各种样式的间隔装饰。他们的使用也非常的简单，只有根据position返回对应item需要的装饰size和装饰Drawable对象即可。
```java

public class MyLinearItemDecoration extends LinearVariedItemDecoration {
    
    @Override
    public int getDividerSize(int position) {
        // 返回间隔的大小
        return 20;
    }

    @Override
    public Drawable getDivider(int position) {
        // 根据position返回Drawable  可以为null
        return drawable;
    }
}

public class MyGridItemDecoration extends GridVariedItemDecoration {

    @Override
    public int getRowDividerSize(int position) {
        // 返回间隔的大小
        return 20;
    }

    @Override
    public Drawable getRowDivider(int position) {
        // 根据position返回Drawable  可以为null
        return rowDrawable;
    }

    @Override
    public int getColumnDividerSize(int position) {
        // 返回间隔的大小
        return 20;
    }

    @Override
    public Drawable getColumnDivider(int position) {
        // 根据position返回Drawable 可以为null
        return columnDrawable;
    }
}

```
**注意：** Drawable对象不要在getxxxDivider方法中实例化。这个方法只返回对象，不创建对象，因为它会频繁调用。

在GridVariedItemDecoration类中有两个方法：isRightItem和isBottomItem，在自定义ItemDecoration时，使用这两个方法可以判断item是否是最右边或者最下边的item。
