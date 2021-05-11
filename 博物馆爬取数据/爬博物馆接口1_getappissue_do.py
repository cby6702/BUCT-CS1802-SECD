import request
import time
import hashlib 
import json

headers={
    "Accept":"application/json, text/javascript, */*; q=0.01",
    "Accept-Language":"zh-CN,zh;q=0.9",
    "X-Requested-With":"XMLHttpRequest",
    "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
    "Origin":"http://app.gjzwfw.gov.cn",
    "User-Agent": "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/89.0.4389.114 Safari/537.36",
    "Cookie":"HWWAFSESID=10fadb513ed28c135b; HWWAFSESTIME=1619620435531; JMOPENSESSIONID=f0e79e98-a47a-4fd2-9601-4d0c091a5e42",
    "Referer": "http://app.gjzwfw.gov.cn/jmopen/webapp/html5/gjwwjqgbwgmlcx/index.html"
}
times=str(int(time.time()*1000))
string=times+'318qwegjzwfwpc_lightapp'
time_key=hashlib.md5(string.encode('utf8')).hexdigest()
# print(times)
# print(time_key)
page_num=1
save_file=open("数据2.txt","wt+",encoding='utf-8')
# for i in range(1,172):
#     print(i)
for i in range(1,172):
    print("当前页码",page_num)
    bodys={
    #     "param":'''{"from":"1","key":"b8d515ed3de145d487830b4d21ef377d","requestTime":"'''+times+'''","sign":"'''+time_key+'''"\
    # ,"tableName":"InstBaseCollection","currentPageNumber":'''+str(page_num)+''',"pagesize":10,"instName":"","province":"","instType":"","qualityLevel":" "}'''
    "uniquecode":times,
    "udid":"gjzwfwpc_lightapp",
    "startTime":0,
    "groupName":"国家文物局",
    "trenchType":1,
    "appTypeName":"",
    "tokenuuid":time_key
    }
    # print(bodys)
    s=request.post("http://app.gjzwfw.gov.cn/jmopen/interfaces/getAppIssue.do",headers=headers,data=bodys,verify=False)

    sp= json.loads(s.text)
    # print(sp)
    page_num=page_num+1
    for sp in sp["appinfo"]:
        # print(sp)
        save_file.write(str(sp))
        time.sleep(0.1)
save_file.close()