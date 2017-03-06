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
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&method=baidu.ting.ugcdiy.getChannels&param=ifRHOrgQ3KC4mitugsxmLNNUgPNe6cfoDpOhx%2BxB%2F9tARzk9%2ByoskOwHeYMKtY95&timestamp=1486955312&sign=96f7b0027e5a68510c0db525612bb9b3
     *
     *  音乐 ---歌单  --热门数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=aUeJzjQd0Bxv60bsMl1nzvktSPLjc5EcGxFAt77r3ORvfYOi0G0UiMU15Gu9rmiLXpXaecx%2BVhS3VNWrDDHaz%2FdPLIB52H4GjQR8wkaLFrrkLECGMiGJkF9toxnAK5KX&timestamp=1486632431&sign=39afa305d4a82eb67ac1f191f47f64e6
     *
     *
     *  音乐 ---歌单 --最新数据
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=KJ8H1UO3TQ2Et%2FEveTV%2Bt8a%2Br2jCR7fbcqsY9ZTHc%2FgPcxyOAIcHfe4iGZVwp1xZsf4wFqntc9HdlL2sikphWMvxv46i1a0Lv2h0Fsugw3e5dDTIseo7qevOW3Bi5BA3&timestamp=1486633214&sign=3cf9f2490198c06166322d80ee105843
     *
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
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=-1&method=baidu.ting.plaza.index&cuid=47D66EFD52506DAC7B0C3A27F3F441A2&focu_num=8
     *
     *  音乐 ---视频--最新
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8
     *  视频--最热
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=1&page_size=20&query=%E5%85%A8%E9%83%A8
     *  现场版
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=1&page_size=20&query=%E7%8E%B0%E5%9C%BA%E7%89%88
     *
     *
     *  发现--直播二级界面 (最后的数字变化)
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.show.item&page_size=30&page_no=1&category=4
     *
     *
     * 查询的都是utf_8
     *  查询中
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.search.catalogSug&format=json&query=
     *  按查询腱子
     *  http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.search.merge&format=json&query=a&page_no=1&page_size=30&type=-1&data_source=0&isNew=1&use_cluster=1
     *
     *
     */

    //榜单    中间接type
    public static String LIST_DATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.billboard.billCategory&format=json&kflag=2";
    public static String LiST_DETAIL_1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&method=baidu.ting.billboard.billList&format=json&type=";
    public static String LiST_DETAIL_2="&offset=0&size=100&fields=song_id%2Ctitle%2Cauthor%2Calbum_title%2Cpic_big%2Cpic_small%2Chavehigh%2Call_rate%2Ccharge%2Chas_mv_mobile%2Clearn%2Csong_source%2Ckorean_bb_song";

    public static String DYNAMIC_DATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcfriend.getList&format=json&param=Vzj1F5tjOnKDcBtupE2xkK1h0%2FBmOlwiPpt7MdO6UD0urZf0rC9NZeQrfa2qCikoGqN8AC%2FEYvNNLX4LkJH%2BjA%3D%3D&timestamp=1486632418&sign=eb7efd3c076c7f7c4cd2402b364d9c2f";

    public static String SONGMENU_HOTDATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=aUeJzjQd0Bxv60bsMl1nzvktSPLjc5EcGxFAt77r3ORvfYOi0G0UiMU15Gu9rmiLXpXaecx%2BVhS3VNWrDDHaz%2FdPLIB52H4GjQR8wkaLFrrkLECGMiGJkF9toxnAK5KX&timestamp=1486632431&sign=39afa305d4a82eb67ac1f191f47f64e6";
    public static String SONGMENU_NEWDATA=" http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.ugcdiy.getChanneldiy&param=KJ8H1UO3TQ2Et%2FEveTV%2Bt8a%2Br2jCR7fbcqsY9ZTHc%2FgPcxyOAIcHfe4iGZVwp1xZsf4wFqntc9HdlL2sikphWMvxv46i1a0Lv2h0Fsugw3e5dDTIseo7qevOW3Bi5BA3&timestamp=1486633214&sign=3cf9f2490198c06166322d80ee105843";

    //音乐视频拼接  拼接类别的utf-8格式
    public static String VIDEO_NEW1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=1&page_num=";
    public static String VIDEO_NEW2="&page_size=20&query=";
    public static String VIDEO_HOT1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&provider=11%2C12&method=baidu.ting.mv.searchMV&format=json&order=0&page_num=";


    public static String FIND_DATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.plaza.indexFind&param=WV%2FB0OohBbFuGg0xKpldarz7CAZJJXK4auqEmfBO1bA7r5yF%2F1ts1cMw%2FqXkgAkH7eNZQOWL5ksdBTJ6dz5gNxIRZboV1KztZxa0%2F3Zsdi4%3D&timestamp=1486632421&sign=2ed28fc410e34aba61e0a7f5369bc02f";

    public static String KMUSIC_LISTDATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.learn.now&page_size=50";
    //中间加数字第几页,后面接类别的utf-8
    public static String KMUSIC_DITALDATA1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.learn.detail&page_no=";
    public static String KMUSIC_DITALDATA2="&page_size=50&query=all&value=0&desc=";
    public static String KMUSIC_BANNER="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.active.showList";

    public static String RECOMMEND_DATA="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=xiaomi&operator=0&method=baidu.ting.plaza.index&cuid=F5143A6FD911991081385C23B22A9C99&focu_num=8";
    //推荐页小图标
    public static String RECOMMEND_1="http://business.cdn.qianqian.com/qianqian/pic/bos_client_1465370722dfa35e762ce6c854d656eac195f7fcd5.jpg";
    public static String RECOMMEND_2= "http://business.cdn.qianqian.com/qianqian/pic/bos_client_146640780395497fb5d1cc59a4cb81580819fbc07b.jpg";
    public static String RECOMMEND_3="http://business.cdn.qianqian.com/qianqian/pic/bos_client_14653709721f134fb220693dff72cce9f8bae7d1ce.jpg";
    public static String RECOMMEND_4="http://business.cdn.qianqian.com/qianqian/pic/bos_client_1465370987dc19b57e5ee3cb4c5c705aeca66d6dab.jpg";


    //songid不影响网页
    String song="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=-1&method=baidu.ting.song.getInfos&format=json&songid=74134682&ts=1487378720943&e=PrHicSTLk8QDXMnH9u6FjhymNFyjImkk" +
            "&nw=2&ucf=1&res=1&l2p=464&lpb=&usup=1&lebo=0";


    public static String SEARCHING="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&method=baidu.ting.search.catalogSug&format=json&query=";


    public static String SONGMENUDETIL1="http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.diy.gedanInfo&from=ios&listid=";
    public static String SONGMENUDETIL2="&version=5.2.3&from=ios&channel=appstore";


    public static String ONLINESONGDATA1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=webapp_music&method=baidu.ting.song.play&format=json&callback=&songid=";
    public static String ONLINESONGDATA2="&_=1413017198449";

    public static String MVDATA1="http://tingapi.ting.baidu.com/v1/restserver/ting?from=android&version=5.9.8.1&channel=1426d&operator=3&provider=11%2C12&method=baidu.ting.mv.playMV&format=json&mv_id=";
    public static String MVDATA2="&song_id=&definition=0";


}
