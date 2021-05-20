import json
from urllib.request import urlopen, quote
import requests,csv
file = open('museums.txt',encoding='utf-8')
lines = file.readlines()
address=[]
for line in lines:
    temp = line.replace('\n','')
    address.append(temp)
file.close()
def getlnglat(address):
    url = 'http://api.map.baidu.com/geocoding/v3/'
    output = 'json'
    ak = 'qsCmKj6mNTCzxTPTasv56TQdHBPEwMKT' #'密钥***'
    add = quote(address) #由于变量为中文，为防止乱码，先用quote进行编码
    uri = url + '?' + 'address=' + add + '&output=' + output + '&ak=' + ak
    req = urlopen(uri)
    res = req.read().decode() #将其他编码的字符串解码成unicode
    temp = json.loads(res) #对json数据进行解析
    return temp

f = open('museum_located.txt','w',encoding='utf-8',newline='')
csv_writer = csv.writer(f)
csv_writer.writerow(["museum", "lng", "lat"])
x=1
for i in address:
        temp= getlnglat(i)
        lng = temp['result']['location']['lng'] #采用构造的函数来获取经度
        lat = temp['result']['location']['lat'] #获取纬度
        str_temp = [i,lng,lat]
        csv_writer.writerow(str_temp) #写入文档
        print(x)
        x=x+1
        print('\n')
f.close()