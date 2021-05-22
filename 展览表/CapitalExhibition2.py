import requests
import bs4
import time
import re
from bs4 import BeautifulSoup
f = open("exhibition.txt","rt",encoding='utf-8')
for line in f.readlines():pass
i = eval(line[0:2])
f = open("exhibition.txt", "a+", encoding='utf-8')
r = requests.get("http://www.capitalmuseum.org.cn/zlxx/jbcl.htm")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find(id = '__').descendants:
    if isinstance(link, bs4.element.Tag) and link.find_all('td','btitle') and link.string!=None:
        i+=1
        f.write("{}|".format(i))
        f.write("首都博物馆|")
        f.write("{}|".format(link.string))
    if isinstance(link, bs4.element.Tag) and link.find('a','hh') and link.string=='详细':
        url='http://www.capitalmuseum.org.cn/zlxx/'+link.find('a','hh').get('href')
        s = requests.get(url)
        s.encoding = s.apparent_encoding
        childsoup = BeautifulSoup(s.text,"html.parser")
        clink = childsoup.find('span','wcontent')
        brief = clink.find('p').string
        f.write("{}\n".format(brief))
r = requests.get("http://www.capitalmuseum.org.cn/zlxx/ztyscl.htm")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find(id = '__').descendants:
    if isinstance(link, bs4.element.Tag) and link.find_all('td','btitle') and link.string!=None:
        i+=1
        f.write("{}|".format(i))
        f.write("首都博物馆|")
        f.write("{}|".format(link.string))
    if isinstance(link, bs4.element.Tag) and link.find('a','hh') and link.string=='详细':
        url='http://www.capitalmuseum.org.cn/zlxx/'+link.find('a','hh').get('href')
        s = requests.get(url)
        s.encoding = s.apparent_encoding
        childsoup = BeautifulSoup(s.text,"html.parser")
        clink = childsoup.find('span','wcontent')
        brief = clink.find('p').string
        f.write("{}\n".format(brief))
r = requests.get("http://www.capitalmuseum.org.cn/zlxx/zxzl.htm")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('a','btitle'):
    i+=1
    f.write("{}|".format(i))
    f.write("首都博物馆|")
    f.write("{}|".format(link.string))
    url='http://www.capitalmuseum.org.cn/zlxx/'+link.get('href')
    s = requests.get(url)
    s.encoding = s.apparent_encoding
    childsoup = BeautifulSoup(s.text,"html.parser")
    clink = childsoup.find('span','wcontent')
    brief=clink.find('p')
    while True:
        if brief.string == None:
            brief = brief.next
        elif len(brief.string) < 10:
            brief = brief.next
        else:break
    f.write("{}|".format(brief.string))
    f.write("{}\n".format(childsoup.find(string = re.compile("展览时间"))[5:9]))
r = requests.get("http://www.capitalmuseum.org.cn/zlxx/zlhf.htm")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('a','btitle'):
    i+=1
    f.write("{}|".format(i))
    f.write("首都博物馆|")
    f.write("{}|".format(link.string))
    url='http://www.capitalmuseum.org.cn/zlxx/'+link.get('href')
    s = requests.get(url)
    s.encoding = s.apparent_encoding
    childsoup = BeautifulSoup(s.text,"html.parser")
    clink = childsoup.find('span','wcontent')
    brief=clink.find('p')
    while True:
        if brief.string == None:
            brief = brief.next
        elif len(brief.string) < 20:
            brief = brief.next
        else:break
    f.write("{}|".format(brief.string))
    f.write("{}\n".format(childsoup.find(string = re.compile("展览时间"))[5:9]))

j=1
for j in range(15):
    r = requests.get("http://www.capitalmuseum.org.cn/zlxx/zlhf_{}.htm".format(j))
    time.sleep(1)
    r.encoding = r.apparent_encoding
    demo = r.text
    soup = BeautifulSoup(demo,"html.parser")
    for link in soup.find_all('a','btitle'):
        i+=1
        f.write("{}|".format(i))
        f.write("首都博物馆|")
        f.write("{}|".format(link.string))
        url='http://www.capitalmuseum.org.cn/zlxx/'+link.get('href')
        s = requests.get(url)
        s.encoding = s.apparent_encoding
        childsoup = BeautifulSoup(s.text,"html.parser")
        clink = childsoup.find('span','wcontent')
        brief=clink.find(['p','br'])
        if brief == None:
            break
        while True:
            if brief.string == None:
                brief = brief.next
            elif len(brief.string) < 20:
                brief = brief.next
            else:break
        f.write("{}\n".format(brief.string))

f.close()