import db
import request
import time
import hashlib 
import json
from retrying import retry 

@retry()
def pa(museum_name,page_num):
    print(museum_name)
    headers={
            "Accept":"application/json, text/javascript, */*; q=0.01",
            "Accept-Language":"zh-CN,zh;q=0.9",
            "X-Requested-With":"XMLHttpRequest",
            "Content-Type":"application/x-www-form-urlencoded; charset=UTF-8",
            "Origin":"http://app.gjzwfw.gov.cn",
            "User-Agent": "",
            "Cookie":"",
            "Referer": "http://app.gjzwfw.gov.cn/jmopen/webapp/html5/gjwwjqgbwgmlcx/index.html"
    }

    times=str(int(time.time()*1000))
    string='gjwwjqgbwgmlcx'+times
    time_key=

  
    bodys={
            "param":'''{"from":"1","key":"b8d515ed3de145d487830b4d21ef377d","requestTime":"'''+times+'''","sign":"'''+time_key+'''"\
            ,"tableName":"BaseCollectionCr","currentPageNumber":'''+str(page_num)+''',"pagesize":10,"museumName":"'''+museum_name+'''","province":"","instType":"","qualityLevel":" "}'''
        }
    # print(bodys)
    
    s=request.post("",headers=headers,data=bodys,verify=False)
    if "timed out" in s.text:
        raise
    sp= json.loads(s.text)
    return sp



if __name__=="__main__":
    db_set=db.sql_sql()
    collect_num=1
    
    for isa in range(1,1708):        #对着全国博物馆表进行遍历
        print("当前进行的博物馆",str(isa))
        st=db_set.read_sql('''SELECT `name` FROM `museums` WHERE mid='''+str(isa))
        # print(st[0])
        time.sleep(1)
        spa=pa(st[0],1)
        # print(spa)
        if int(spa["data"]["pageCount"])>=10:
            page_num_num=11
        else:
            page_num_num=int(spa["data"]["pageCount"])+1
            
        for page_num in range(1,page_num_num):
            spa=pa(st[0],page_num)
            time.sleep(2)
            for spa in spa["data"]["data"]:
                # print(spa["museumName"])
                if spa["museumName"]==st[0]:
                    sql='''INSERT INTO `collection`(`cid`, `cname`, `name`, `mid`,  `cintroduce`)\
                        VALUES ('''+str(collect_num)+''',\''''+spa["antiqueName"]+'''\',\''''+st[0]+'''\',\''''+str(isa)+'''\',\''''+spa["culturalClass"]+"+"+spa["age"]+'''\')'''
                    # print(sql)
                    db_set.set_sql(sql.encode("utf8"))
                    collect_num+=1
                # print(spa["museumName"])
    
        # for i in range(1,172):
        #     print("当前页码",page_num)
        #     # save_log.write(str(page_num))
            
            
        #     page_num=page_num+1
        #     print(sp)
        #     for sp in sp["data"]["data"]:
        #         print(sp)

        #         time.sleep(1)
    db_set.set_close()