# Define here the models for your scraped items
#
# See documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html

import scrapy


class TutorialItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    pass

class DmozItem(scrapy.Item):
    title = scrapy.Field()
    link = scrapy.Field()
    desc = scrapy.Field()

class MuseumNewsSpiderItem(scrapy.Item):
    # define the fields for your item here like:
    # name = scrapy.Field()
    title = scrapy.Field()
    author = scrapy.Field()
    time = scrapy.Field()
    abstract = scrapy.Field()
    content = scrapy.Field()
    description = scrapy.Field()
    url = scrapy.Field()
    href = scrapy.Field()
    tag = scrapy.Field()
    news_id = scrapy.Field()
    main_content = scrapy.Field()
    content_list = scrapy.Field()
    museum_id = scrapy.Field()