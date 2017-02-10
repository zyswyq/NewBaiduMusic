package com.example.dllo.newbaidumusic.bean;

/**
 * Created by dllo on 17/2/10.
 */

public class URLBean {
    /**
     * 音乐 --K歌轮播图
     *http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.active.showList
     *
     * 动态  list 数据
     * http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcfriend.getList&format=json&param=Vzj1F5tjOnKDcBtupE2xkK1h0%2FBmOlwiPpt7MdO6UD0urZf0rC9NZeQrfa2qCikoGqN8AC%2FEYvNNLX4LkJH%2BjA%3D%3D&timestamp=1486632418&sign=eb7efd3c076c7f7c4cd2402b364d9c2f
     *
     *  发现  以及发现二级界面分类的图标
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.plaza.indexFind&param=WV%2FB0OohBbFuGg0xKpldarz7CAZJJXK4auqEmfBO1bA7r5yF%2F1ts1cMw%2FqXkgAkH7eNZQOWL5ksdBTJ6dz5gNxIRZboV1KztZxa0%2F3Zsdi4%3D&timestamp=1486632421&sign=2ed28fc410e34aba61e0a7f5369bc02f
     *
     *  音乐 ---推荐 --数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=0&method=baidu.ting.plaza.index&cuid=F5143A6FD911991081385C23B22A9C99&focu_num=8
     *
     *  音乐  --- 歌单  --分类
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChannels&param=yJxNHh3bMIYFcEaQxwK83tq5%2F5DBqbVtMHuq1g4fxssjKoOo9wn%2Bn5pjNDi%2FJFLa&timestamp=1486632431&sign=256a5151e9fd6bcbeef0b6f42b5c1919
     *
     *  音乐 ---歌单  --热门数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=aUeJzjQd0Bxv60bsMl1nzvktSPLjc5EcGxFAt77r3ORvfYOi0G0UiMU15Gu9rmiLXpXaecx%2BVhS3VNWrDDHaz%2FdPLIB52H4GjQR8wkaLFrrkLECGMiGJkF9toxnAK5KX&timestamp=1486632431&sign=39afa305d4a82eb67ac1f191f47f64e6
     *
     *  音乐 ---歌单 --最新数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=KJ8H1UO3TQ2Et%2FEveTV%2Bt8a%2Br2jCR7fbcqsY9ZTHc%2FgPcxyOAIcHfe4iGZVwp1xZsf4wFqntc9HdlL2sikphWMvxv46i1a0Lv2h0Fsugw3e5dDTIseo7qevOW3Bi5BA3&timestamp=1486633214&sign=3cf9f2490198c06166322d80ee105843
     *
     *  音乐 ---歌单 --二级数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getBaseInfo&param=XSDJHVvg1vydDv8aY83q2koIiDazkmYMUP4PvFXVlGWWwOe1w6V8c6klWEVGA77qIc8u%2FnvD62NAm7dZnpthVI3BQZLGX1XkixuGIloMzk5I4kJnLzAqnNOZ4Ih6t8Uu6DrdLeMulMkJ%2B%2BJ0pJTZyg%3D%3D&timestamp=1486633612&sign=c0ee6e7a9e6b5589c69b3f038349ea22
     *
     *  音乐 --推荐 --会员专区
     *  http://music.baidu.com/cms/webview/VIP_area/index.html?operator=3&musicch=1426d&fr=android&ver=5.9.8.1
     *
     *  音乐 ---榜单 数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2
     *
     *  音乐 ---场景电台 --语言  (最后的数字变化)
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.scene.getCategoryScene&category_id=4
     *
     *  音乐 ---新碟上架---推荐
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.plaza.getRecommendAlbum&format=json&offset=0&limit=50&tagname=%E6%8E%A8%E8%8D%90
     *
     *  发现--直播二级界面 (最后的数字变化)
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=4
     *
     */

}
