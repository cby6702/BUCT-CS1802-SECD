import re
import time
import requests
import bs4
from bs4 import BeautifulSoup
f = open("exhibition.txt","rt",encoding='utf-8')
for line in f.readlines():pass
i = eval(line[0:3])
f = open("exhibitionnew.tx  t","a+",encoding='utf-8')
r = requests.get("http://www.cnap.org.cn/cnap/zlyg/zlsc_jlzzl_img.shtml")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('div','hysc'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("中国国家画院美术馆|")
        for innerlink in link.find_all('a'):
            if innerlink.string!=None:
                f.write("{}|".format(innerlink.string))
        innerlink = link.find(string=re.compile("展览简介"))
        x = re.sub('\s+', '', innerlink).strip()
        f.write("{}|".format(x[5:-1] + x[-1]))
        innerlink = link.find(string=re.compile("展览时间"))
        f.write("{}\n".format(innerlink.string[5:-1]+innerlink.string[-1]))
j=0
for j in range(7):
    r = requests.get("http://www.cnap.org.cn/cnap/zlyg/zlsc_jlzzl_img_{}.shtml".format(j+2))
    time.sleep(1)
    r.encoding = r.apparent_encoding
    demo = r.text
    soup = BeautifulSoup(demo, "html.parser")
    for link in soup.find_all('div', 'hysc'):
        if isinstance(link, bs4.element.Tag):
            i += 1
            f.write("{}|".format(i))
            f.write("中国国家画院美术馆|")
            for innerlink in link.find_all('a'):
                if innerlink.string != None:
                    f.write("{}|".format(innerlink.string))
            innerlink = link.find(string=re.compile("展览简介"))
            x = re.sub('\s+', '', innerlink).strip()
            f.write("{}|".format(x[5:-1] + x[-1]))
            innerlink = link.find(string=re.compile("展览时间"))
            f.write("{}\n".format(innerlink.string[5:-1] + innerlink.string[-1]))