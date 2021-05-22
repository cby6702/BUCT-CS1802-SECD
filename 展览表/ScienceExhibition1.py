import requests
import bs4
from bs4 import BeautifulSoup
f = open("exhibition.txt","a+",encoding='utf-8')
i = 0
r = requests.get("https://cstm.org.cn/cszl/cszljs/")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('div','cszl-fent-s'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("科学技术馆|")
        f.write("{}|".format(link.find('span','float-l').string))
        url='https://cstm.org.cn/cszl/'+link.find('a', 'cszl-fent-pic').get('href')[3:-1]
        s = requests.get(url)
        s.encoding = s.apparent_encoding
        childsoup = BeautifulSoup(s.text,"html.parser")
        clink=childsoup.find('div',['fenzhanqu-docc','kxly-content'])
        brief = clink.find('p').string
        f.write("{}\n".format(brief))
r = requests.get("https://cstm.org.cn/dqzl/zlhg/")
r.encoding = r.apparent_encoding
demo = r.text
soup = BeautifulSoup(demo,"html.parser")
for link in soup.find_all('ul','fenzlhg-list'):
    if isinstance(link, bs4.element.Tag):
        i+=1
        f.write("{}|".format(i))
        f.write("科学技术馆|")
        f.write("{}|".format(link.find('a','fenzlhg-name').string))
        url='https://cstm.org.cn/dqzl/zlhg/'+link.find('a', 'fenzlhg-pic').get('href')[1:-1]+'l'
        s = requests.get(url)
        s.encoding = s.apparent_encoding
        childsoup = BeautifulSoup(s.text,"html.parser")
        clink = childsoup.find('div','fen-info-cont')
        brief = clink.find(['p','div']).string
        date = childsoup.find('p','fen-info-title-p')
        f.write("{}|".format(brief))
        f.write("{}\n".format(date.string[5:9]))
f.close()