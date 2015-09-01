## ActionBar

在 *menu_xxxx.xml* 中增加 Item：

    <item android:id="@+id/home_menu_search"
    android:title="Search"
    app:actionViewClass="android.widget.SearchView"
    android:icon="@android:drawable/ic_menu_search"
    app:showAsAction="ifRoom|collapseActionView" />

在 *Activity* 中创建及点击事件方法：

    public boolean onCreateOptionsMenu(Menu menu)
    public boolean onOptionsItemSelected(MenuItem item)

**获得SearchView实例**
    private SearchView mSearchView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) MenuItemCompat.getActionView(searchItem);

        return true;
    }

*PS:
当 *MenuItemCompat.getActionView(searchItem)* 总是返回 null 时，检查 menu_xxx.xml 中item 的 app:showAsAction 属性前缀是否与命名空间一致*


**有 menu 键时不显示更多**

    private void setOverflowShowingAlways() {
        try {
            ViewConfiguration config = ViewConfiguration.get(this);
            Field menuKeyField = ViewConfiguration.class.getDeclaredField("sHasPermanentMenuKey");
            menuKeyField.setAccessible(true);
            menuKeyField.setBoolean(config, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


## 使用 AppCompat 实现 ActivonBar
支持 2.1 以后所有版本


PS：

1. 要特别注意的是，通过XML文件来实现Action Item，一定要自定义命名空间，而且该命名空间的后缀一定要和item中showAsAction的前缀一致，本例中为“holo”，显示Menu需要重写onCreateOptionsMenu方法