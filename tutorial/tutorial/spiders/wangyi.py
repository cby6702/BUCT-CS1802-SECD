import scrapy
from scrapy.http import response
from tutorial.items import MuseumNewsSpiderItem


class WangyiSpider(scrapy.Spider):
    name = 'wangyi'
    allowed_domains = ['wangyi.cn']
    start_urls = ['http://www.gmc.org.cn/toboalerts.html']

    def parse(self, response):
        list1 = response.xpath("/html/body/div[4]/div/div/div[3]/div[1]/div[@class='li']")
        for text in list1:
            item = MuseumNewsSpiderItem()
            item["title"] = text.xpath("./a/div/div[1]/text()").extract()
            item["time"] = text.xpath("./a/div/div[2]/text()").extract()
            item["abstract"] = text.xpath("./a/div/div[3]/text()").extract()
            item["href"] = "http://www.gmc.org.cn"+text.xpath("./a/@href").extract_first()
            print(item)
            yield scrapy.Request(
                item["href"],
                callback=self.parse_detail,
                meta={"item": item}
            )

    def parse_detail(self, response):
        item = response.meta["item"]
        item["content"] = response.xpath("/html/body/div[4]/div/div/div[2]/p[3]/span/text()").extract()
        yield item
