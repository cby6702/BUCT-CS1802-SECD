import re
import requests
from urllib import error
from bs4 import BeautifulSoup
import os
 
num = 0
numPicture = 0
List = []
 
 
def dowmloadPicture(html, keyword):
    global num    
    pic_url = re.findall('"objURL":"(.*?)",', html, re.S) 
    print("找到关键词:" + keyword + "的图片，即将开始下载图片...")
    for temp in pic_url:
        print("正在下载第" + str(num + 1) + "张图片，图片地址:" + str(temp))
        try:
            if temp is not None:
                pic = requests.get(temp, timeout=7)
            else:
                continue
        except BaseException:
            print("错误，当前图片无法下载")
            continue
        else:
            string = "pictures" + r"\\" + keyword + ".jpg"
            fp = open(string, "wb")
            fp.write(pic.content)
            fp.close()
            num += 1
        if num >= numPicture:
            return

 
if __name__ == "__main__":  # 主函数入口
    
    
    headers = {
        'Accept-Language': 'zh-CN,zh;q=0.8,zh-TW;q=0.7,zh-HK;q=0.5,en-US;q=0.3,en;q=0.2',
        'Connection': 'keep-alive',
        'User-Agent': 'Mozilla/5.0 (X11; Linux x86_64; rv:60.0) Gecko/20100101 Firefox/60.0',
        'Upgrade-Insecure-Requests': '1'
        }
 
    A = requests.Session()
    A.headers = headers
      

    numPicture = 1
    line_list = []
    line_list1=[]
    m_name=open("museum.txt","r",encoding="utf-8")
    content = m_name.readlines()
    m_name.close
    for temp in content:
        line_list1.append(temp.strip())
    for i in line_list1:
        line_list.append(i.replace('"',""))
    for word in line_list:
        url = "https://image.baidu.com/search/flip?tn=baiduimage&ie=utf-8&word=" + word + "&pn="       
        t = 0
        tmp = url
        while t < numPicture:
            try:
                url = tmp + str(t)                
                result = A.get(url, timeout=10, allow_redirects=False)
                print(url)
            except error.HTTPError as e:
                print("网络错误，请调整网络后重试")
                t = t + 1
            else:
                dowmloadPicture(result.text, word)
                m_list=open("list.txt","a")
                m_list.write(word+" "+"http://8.140.3.158/pictures/"+word+".jpg"+"\n")
                m_list.close()
                t = t + 1
    print("爬取结束")