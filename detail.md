## Data Source
视频相关信息来自 [电驴](http://www.verycd.com)  
电驴搜索接口:  

    http://www.verycd.com/search/entries/XXXX  
    XXXX: 表示搜索的关键字  

视频评分来自于 [豆瓣](http://movie.douban.com/)  
豆瓣搜索接口:  

    http://movie.douban.com/subject_search?search_text=XXXX&cat=1002  
    XXXX: 表示搜索的关键字  
    

## 获取数据流程

### 用户主动搜索部分
1. 用户在应用内输入关键字，如 ‘花千骨’， ‘无心法师’等
2. 用户点击搜索后，关键字上传服务器
3. 服务器先从 **豆瓣网** 上获取 *基本信息* 及 *评分*
4. 服务器再从 **电驴** 上获取 *更新进度*

### 应用主动展示部分
