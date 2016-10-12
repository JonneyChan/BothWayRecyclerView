# BothWayRecyclerView

双方向的列表，侧边，顶部菜单不变，其他实现双向滚动

公司要弄房况管理的app，需要一个双向列表，横向显示日期，纵向显示房间，需求，顶部日期，左边房间标题不动。
![](https://raw.githubusercontent.com/JonneyChan/BothWayRecyclerView/master/QQ20161012-0%402x.png)
最初方案，利用listview嵌套横向利用HorizontalScrollView，同步他们的滚动距离，实现横向滚动同步，这样横向的item没法复用，有时候会出现卡顿。 后看到recyclerview，可以横竖向，recyclerview滚动距离同步并不想scrollview那么容易处理，后面想到可以从事件上着手，垂直方向的RecyclerView的做事件拦截，阻止向下传递，并自行分发到全部的横向recyclerview，实现横向滚动同步。
最后效果图
![](https://raw.githubusercontent.com/JonneyChan/BothWayRecyclerView/master/device-2016-10-12-172903.png)

