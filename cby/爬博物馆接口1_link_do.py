import request
import time
import hashlib 
import json
import db

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
string='gjwwjqgbwgmlcx'+times
time_key=hashlib.md5(string.encode('utf8')).hexdigest()
# print(times)
# print(time_key)
page_num=1

# for i in range(1,172):
#     print(i)
museum_num=1

db_opera=db.sql_sql()
save_file=open("数据1.txt","wt+",encoding='utf-8')
for i in range(1,172):
    print("当前页码",page_num)
    bodys={
        "param":'''{"from":"1","key":"b8d515ed3de145d487830b4d21ef377d","requestTime":"'''+times+'''","sign":"'''+time_key+'''"\
    ,"tableName":"InstBaseCollection","currentPageNumber":'''+str(page_num)+''',"pagesize":10,"instName":"","province":"","instType":"","qualityLevel":" "}'''
    }
    # print(bodys)
    s=request.post("http://app.gjzwfw.gov.cn/jimps/link.do",headers=headers,data=bodys,verify=False)

    sp= json.loads(s.text)
    page_num=page_num+1

    for sps in sp["data"]["data"]:
        # print(sps)
        # sql='''INSERT INTO `museums`(`mid`, `name`, `introduction`, `opentime`, `city`) \
        #     VALUES ('''+str(museum_num)+''',\''''+sps["instName"]+'''\',\''''+sps["summary"]+'''\',\''''+sps["openTime"]+'''\',\''''+sps["areaCode"]+'''\')'''
        #print(db_opera.set_sql(sql.encode("utf8")))
        museum_num+=1
        save_file.write(str(museum_num)+'\\'+sps["instName"]+'\\'+sps["summary"]+'\\'+sps["openTime"]+'\\'+sps["areaCode"]+'\n')
        # save_file.write(str(sp))
    time.sleep(1)
# db_opera.set_close()
save_file.close()