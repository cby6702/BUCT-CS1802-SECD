import pymysql
class sql_sql:

    def __init__(self):
        self.conn=pymysql.connect(host='8.140.3.158', user='buctcs1802',password='cs1802..',database='museum',charset='utf8')
        self.conn.select_db('museum')
        self.cur=self.conn.cursor()#获取游标
        
    def set_sql(self,sql):
        self.cur.execute(sql)
        
        
    def read_sql(self,sql):
        self.cur.execute(sql)
        return self.cur.fetchone()
        
    def set_close(self):
        self.cur.close()
        self.conn.commit()
        self.conn.close()
