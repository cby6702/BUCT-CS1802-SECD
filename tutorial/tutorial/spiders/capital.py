import scrapy
from scrapy import item

from tutorial.items import MuseumNewsSpiderItem


class CapitalSpider(scrapy.Spider):
    name = 'capital'
    allowed_domains = ['capitalmuseum.org.cn']
    start_urls = ['http://www.capitalmuseum.org.cn/zjsb/sbkx.htm']

    def parse(self, response):
        list1 = response.xpath("//table[@width='1024']/tr/td[@width='754']/table/tr[4]/td/table[@width='85%']")
        for text in list1:
            item = MuseumNewsSpiderItem()
            item["time"] = text.xpath("../tr[1]/td/text()").extract_first()
            item["titlt"] = text.xpath("../tr[1]/td/a/text()").extract_first()
            item["href"] = text.xpath("../tr[1]/td/a/@href").extract_first()

            yield scrapy.Request(
                item["href"],
                callback=self.parse_detail,
                meta={"item": item}
            )

    def parse_detail(self, response):
        item = response.meta["item"]
        item["content"] = response.xpath("/html/body/table[2]/tbody/tr/td[2]/table/tbody/tr[4]/td/table/tbody/tr["
                                         "1]/td/table/tbody/tr[2]/td/span/p/span/text()")
        yield item
