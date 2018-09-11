# RoundImageView
可以自定义各个角的圆角

该控件包含2种圆角效果一种是常见的外圆弧第二种是内圆弧
使用方法如下：
<com.guang.myapplication.view.RoundImageView
android:id="@+id/iv_test1"
android:layout_width="300dp"
android:layout_height="100dp"
android:scaleType="centerCrop"
android:src="@mipmap/bg"
app:isBottomLeft="true"
app:isBottomLeftOuterCircle="false"
app:isBottomRight="true"
app:isBottomRightOuterCircle="false"
app:isTopLeft="true"
app:isTopLeftOuterCircle="true"
app:isTopRight="true"
app:isTopRightOuterCircle="true"
app:radius="15dp"/>
其中isBottomLeft，isBottomRight，isTopLeft，isTopRight控制是否需要圆弧 true为需要 false为不需要
isBottomLeftOuterCircle等属性则表示是外圆弧还是内圆弧，true为外圆弧，false为内圆弧
注意：只有使用src是才生效，用background时会失效
