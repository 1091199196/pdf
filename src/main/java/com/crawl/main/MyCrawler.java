package com.crawl.main;

import com.crawl.link.LinkFilter;
import com.crawl.link.Links;
import com.crawl.page.Page;
import com.crawl.page.PageParserTool;
import com.crawl.page.RequestAndResponseTool;
import com.crawl.util.FileTool;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Set;

public class MyCrawler {
    /**
     * 使用种子初始化 URL 队列
     *
     * @param seeds 种子 URL
     * @return
     */
    private void initCrawlerWithSeeds(String[] seeds) {
        for (int i = 0; i < seeds.length; i++) {
            Links.addUnvisitedUrlQueue(seeds[i]);
        }
    }


    /**
     * 抓取过程
     *
     * @param seeds
     * @return
     */
    public void crawling(String[] seeds) {

        //初始化 URL 队列
        initCrawlerWithSeeds(seeds);

        //定义过滤器，提取以 http://www.baidu.com 开头的链接
        LinkFilter filter = new LinkFilter() {
            public boolean accept(String url) {
                if (url.startsWith("http://www.baidu.com"))
                    return true;
                else
                    return true;
            }
        };

        //循环条件：待抓取的链接不空且抓取的网页不多于 1000
        while (!Links.unVisitedUrlQueueIsEmpty() && Links.getVisitedUrlNum() <= 1000) {

            //先从待访问的序列中取出第一个；
            String visitUrl = (String) Links.removeHeadOfUnVisitedUrlQueue();
            if (visitUrl == null) {
                continue;
            }

            //根据URL得到page;
            Page page = RequestAndResponseTool.sendRequstAndGetResponse(visitUrl);

            //对page进行处理： 访问DOM的某个标签
            Elements es = PageParserTool.select(page, "a");
            Elements els = new Elements();
            for (int i = 0; i < es.size(); i++) {
                Element element = es.get(i);
                boolean b = element.hasClass("btn btn-default btn-block no-bordered");
                if (b) {
                    els.add(element);
                }
            }
            for (int i = 0; i < els.size(); i++) {
                Element element = els.get(i);
                String code1 = element.parentNode().attr("code");
                String name = element.parentNode().attr("name");
                String val = element.childNode(0).attr("text");
                System.out.println(code1+"--"+val+"--"+name);
            }

            if (!es.isEmpty()) {
                System.out.println("下面将打印所有a标签： ");
              //  System.out.println(es);
            }

            //将保存文件
            FileTool.saveToLocal(page);

            //将已经访问过的链接放入已访问的链接中；
            Links.addVisitedUrlSet(visitUrl);

            //得到超链接
            Set<String> links = PageParserTool.getLinks(page, "img");
            for (String link : links) {
                Links.addUnvisitedUrlQueue(link);
                System.out.println("新增爬取路径: " + link);
            }
        }
    }


    //main 方法入口
    public static void main(String[] args) {
        MyCrawler crawler = new MyCrawler();
        crawler.crawling(new String[]{"http://zwfw.cq.gov.cn/bnq/govservice/project?type=fr"});
    }
}
