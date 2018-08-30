### 交互流程图
https://github.com/qianzhangdu/bear-web/blob/master/src/main/resources/doc/flow.jpg


### 数据结构
https://github.com/qianzhangdu/bear-web/blob/master/src/main/resources/doc/db.jpg


### 接口
> 爬虫触发接口
> myibear.com/news/crawl/run?net='wangyi'
> GET
> 参数
```
新闻网站集合：http://top.chinaz.com/hangye/index_news.html（找一些low一点的网站去爬）
```

> 爬虫结果入库
> myibear.com/news/crawldatainseart
> POST
```
参数 body
{
	"data": [{
		"maintype": "1", //1:科技,2:体育
		"uuid": "xxx",
		"title": "标题",
		"type": "1", //1:新闻,2:视频,3:广告
		"ctime": 1535512047000, //新闻发布时间
		"content": "详细新闻内容"
		"smallpic": [{
			"width": "218",
			"height": "164",
			"src": "图片链接"
		}],
		"publish": "家乡足球",//创建人
		"crawlinfo": {
			"from": "1", //1:网易
			"url": "xxx" //爬取链接
		}
		"remark": [{
			"nick": "张三",
			"message": "棒",
			"location": "浙江杭州市",
			"ctime": 1535512047000, //留言时间
			"children": "把上面评论的属性再嵌套一遍"
		}]

	}]
}


返回response
{
	code: 0,
	error: null
}
```



> 新闻查询接口
> myibear.com/news/list
> POST
```
参数 body
{
	"request": {
		"companycode": "xxx", //后台对接的时候线下给的
		"uid": "xxx",
		"citycode": "xxx",
		"maintypeinfo": [{
			"maintype": "1",
			"ltime": 1535512047000, //上一次更新时间
			"lid": 1535512047000, //上一次更新的新闻id
		}]
	}
}


返回response
{
	code: 0,
	error: null,
	data: {
		"reponse": [{
			"maintype": "1", //1:科技,2:体育
			"type": "1", //1:新闻,2:视频
			"newid": "xxx", //新闻id
			"title": "标题",
			"ctime": 1535512047000, //新闻发布时间
			"smallpic": [{
				"width": "218",
				"height": "164",
				"src": "图片链接"
			}],
			"publish": "家乡足球", //创建人
			"rcount": 68, //评论数

		}]
	}
}
```








### 枚举


* 类目枚举
```
1：科技
2：体育
3：房产
4：视频
5：热点
6：娱乐
7：健康
8：养生
9：励志
10：生活
11：财经
12：汽车
13：星座
14：美食
15：时尚
16：旅行
17：育儿
18：军事
19：历史
20：三农
21；游戏
22：国际
23：家居
24：推荐
25：收藏
26：自己城市（杭州）
```
