# 1. 需求
## 1.1 需求整理
1. 记录想看还未看的电影，电视剧
2. 记录在追着看的电视剧的进度：
    1. 集数
    2. 分钟数
    3. 观看平台

## 1.2 功能整理
1. 收藏感兴趣的影视
2. 搜索影视
3. 浏览影视：最新，热门，即将上映的影视
4. 记录观看影视进度
    + 集数
    + 分钟数
    + 观看平台

# 2. 设计
## 2.1 收藏
1. 单独一个页面显示已收藏的影视
2. 用户可以在影视详情页面 **添加、取消** 收藏该影视
3. 对于已收藏的影视项可以：
    + 查看该影视详情
    + 取消收藏
    + 显示更新进度：已更新集数、总集数
    + 显示观看进度：集数，分钟数，平台
4. 收藏功能只在本地保存，并不同步到服务器

### 2.1.1 功能点
1. 收藏感兴趣的影视
2. 取消收藏
3. 显示影视信息：评分，更新信息
4. 设置显示观看记录

### 2.1.2 逻辑概述
用户可以从影视详情页面添加 收藏，可以更新观看记录，查看更新进度，最后可以去收藏

### 2.1.3 详细设计
1. 所有收藏影视以列表形式呈现，不用刷新
2. 影视项呈现：**封面，名字，评分，演员，更新进度，观看记录，去收藏**
3. 点击影视项后，进入影视详情页面 

## 2.2 搜索
1. 用户输入**影视名字、演员**等关键字提交查询
2. 服务返回所有相关数据项作为备选项
3. 用户选择目标项
4. 再获取选择是影视的详情数据，打开这详情页面

### 2.2.1 功能点
1. 关键字输入提交
2. 搜索结果预览
3. 选择结果，进入该影视详情页面

### 2.2.2 逻辑概述
用户输入关键字后，应用实时返回搜索到的结果预览，用户再点击想查看的影视结果，进入其详情页面，搜索完毕

### 2.2.3 详细设计
1. 应用标题栏以菜单形式提供搜索入口
2. 点击后输入关键字，停顿500ms后，自动提交搜索请求，请求前，比对当前搜索关键字，与用户输入，一致，则请求，否则忽略当前请求
3. 以列表形式呈现返回的结果预览项
4. 用户再点击结果预览中的某项，进入该项详情页面，搜索完毕

## 2.3 浏览
1. **电影，电视剧**分页面、分类呈现
2. 影视呈现时，又分类型呈现：动作、犯罪。。

### 2.3.1 功能点
1. 浏览分 **电视，电影** 两部分
2. 两部分又分别可以按类型筛选
3. 点击影视项可查看其详情
4. 详情页面可以 收藏/去收藏 该影视

### 2.3.2 逻辑概述


### 2.3.3 详细设计
#### 1. 影视浏览页面
1. 以ViewPager显示大种类
2. 以下拉选择菜单显示分类
3. 以列表显示影视内容
4. 影视项呈现：**封面，名字，评分，更新进度**
5. 点击影视项后，进入影视详情页面

#### 2. 影视详情页面
1. 显示内容：
    + 封面
    + 名字
    + 评分
    + 演员
    + 导演
    + 更新进度
    + 观看记录
    + 图片/预告片
    + 内容简介
    + 短评



# 客户端
### 获取数据流程

### 用户主动搜索部分
1. 用户在应用内输入关键字，如 ‘花千骨’， ‘无心法师’等
2. 用户点击搜索后，关键字上传服务器
3. 服务器先从 **豆瓣网** 上获取 *基本信息* 及 *评分*
4. 服务器再从 **电驴** 上获取 *更新进度*

### 应用主动展示部分


---
# 附录
1. [Fragment使用 http://blog.csdn.net/lmj623565791/article/details/37970961](http://blog.csdn.net/lmj623565791/article/details/37970961)

---

# 服务器端
## 服务器端数据来源
视频相关信息来自 [电驴](http://www.verycd.com)  
电驴搜索接口:  

    http://www.verycd.com/search/entries/XXXX  
    XXXX: 表示搜索的关键字  

视频评分来自于 [豆瓣](http://movie.douban.com/)  
豆瓣搜索接口:  

    http://movie.douban.com/subject_search?search_text=XXXX&cat=1002  
    XXXX: 表示搜索的关键字  
    

## Service Interface
### 1. 请求返回外层封装
#### 1.1 请求成功返回
    {
        "result" : "ok"
        "data" : []
    }
    result: 请求响应结果
    data: 请求返回的数据，格式为 JsonArray

#### 1.2 请求失败返回
    {
        "result" : "error"
        "data" : "error message"
    }
    result: 请求响应结果
    data: 请求返回的错误原因


### 2. 数据接口
#### 2.1 /movies/hot
#### 2.2 /movies/coming
#### 2.3 /echo


#### 2.4 查询接口
    /movies/search?query=

##### 2.4.1 请求格式
    GET http://xxxx/movies/search?query=value
其中 *query* 为查询参数名，支持 *电影名字，电视剧名字，演员名字* 等查询

##### 2.4.2 返回格式
    {
        "result":"ok",
        "extra":{
            "query":""
        },
        "data":[
            {
                "name":"",
                "actor":"",
                "homeUrl":"",
                "type":"",
                "typeName":""
            },
            ...    
        ]
    }
其中 *data* 为查询到的数据


#### 2.5 详情接口
    /movies/detail

##### 2.5.1 请求格式
    Content-Type: application/x-www-form-urlencoded
    
    POST http://xxxx/movies/detail
    url=value1&type=value2
    
    url:请求页面的网址
    type:请求数据的类型
其中 *url=value1&type=value2* 为 POST 上传的内容，且必须指定此次 HTTP 请求的 Content-Type 为 *application/x-www-form-urlencoded*  


##### 2.5.2 返回格式
**电视剧：**

    {
        "result":"ok",
        "extra":{
            "url":"",
            "type":"tv"
        },
        "data":{
            "name":"",
            "cover":"",
            "category":"",
            "area":"",
            "episodes":"",
            "director":"",
            "writer":"",
            "actor":"",
            "TVStation":"",
            "releaseDate":"",
            "platform":[
                {
                    "":0
                },
                ...
            ],
            "poster":[],
            "summary":""
        }
    }

**电影：**

    {
       "result":"ok",
       "extra":{
           "url":"",
           "type":"movie"
       },
        "data":{
            "name":"",
            "cover":"",
            "category":"",
            "area":"",
            "director":"",
            "writer":"",
            "actor":"",
            "releaseDate":"",
            "poster":[],
            "summary":""
        }
    }
其中 *data* 为详情数据，*platform* 为支持的播放平台列表，格式为 JsonArray， *posters* 为剧照列表，格式为 JsonArray

#### 2.6 收藏接口
    本接口暂时在客户端使用，并未上传至服务器，即收藏数据只保留在本地

##### 2.6.1 保存数据结构
```
{
    "library":{
        "id":{
            "searchItem":{},
            "detailItem":{},
            "catagory":"",
            "id":""
        },
        "id":{
            "searchItem":{},
            "detailItem":{},
            "catagory":"",
            "id":""
        },
        ...
    }
}
```
其中 *id*


电影与电视剧共有的属性:
"名字":name
"封面":cover
"类型":category
"地区":area
"导演":director
"编剧":writer
"演员":actor
"简介":summary
"剧照":poster
"首映日期":releasedDate

电视剧额外属性：
"集数":episodes
"电视台":TVStation
"播放平台-更新":platform

电影额外属性：
//"imdb号":imdb


hot
coming


query:Universal
detail
    movie
    tv
    book


DataController

UniversalDataController

MovieDataController

TVDataController