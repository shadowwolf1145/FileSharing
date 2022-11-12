import sqlite3  
  
class Database:  
    conn = ""  
cursor = ""  
def Error_Message(self,err):  
	print(f"""[ERROR:  {err}   ]""")  


def CREATE_TABLE(self,table_name:str,values:str):  
	try:  
		self.cursor.execute("""  
		CREATE TABLE {Name}            (ID INTEGER PRIMARY KEY AUTOINCREMENT ,            {Values});            """.format(Name=table_name,Values=values))  
		self.conn.commit()  
	except sqlite3.Error as err:  
		self.Error_Message(err)  


def DROP_TABLE(self,table_name:str):  
	try:  
		self.cursor.execute(f"""  
		DROP TABLE {table_name};  
		""")  
		self.conn.commit()  
	except sqlite3.Error as err:  
		self.Error_Message(err)  


def GET_TABLES(self,):  
	self.cursor.execute("""  
	SELECT name FROM sqlite_master WHERE type ='table' AND name NOT LIKE 'sqlite_%';        """)  
	tables = self.cursor.fetchall()  
	self.conn.commit()  
	return tables  


def INSERT_INTO_TABLE(self,table_name:str,values:str):  
	data = self.cursor.execute(f"SELECT * FROM {table_name}")  
	columns = ""  
	for I in range(0,len(data.description)):  
		column = data.description[I]  
		if column[0] != "ID":  
			columns += (column[0])  
			if I != len(data.description)-1:  
				columns += ","  
	command = f"""  
				  INSERT INTO {table_name} ({columns}) VALUES({values})  
				  """        print(command)  
	self.cursor.execute(command)  
	self.conn.commit()  

test = [[1,2],[4,5]]  
test.remove([4,5])  
for Item in test:  
	print(Item)  
def GET_TABLE_VALUES(self,table_name:str):  
	data = self.cursor.execute(f"SELECT * FROM {table_name}")  
	self.conn.commit()  
	columns = ""  
	for I in range(0,len(data.description)):  
		column = data.description[I]  
		if (I+1) != len(data.description):  
			print(f"if I {I}")  
			columns = columns + f"{column[0]}:"  
		else:  
			print(f"else I {I}")  
			columns = columns + f"{column[0]}"  
	return columns  

def GET_FROM_TABLE(self,table_name:str,columns:str = "*",conditions=""):  
	conditionsString = ""  
	if conditions != "":  
		conditionsString = f'WHERE {conditions[0][0]} {conditions[0][1]} {conditions[0][2]}'  
		conditions.pop(0)  
		for Set in conditions:  
			conditionsString = conditionsString + f'\n      AND {Set[0]} {Set[1]} {Set[2]}'  
	command = f"""  
	SELECT {columns} FROM {table_name}  
	{conditionsString}  
	;        """        self.cursor.execute(command)  
	output = str(self.cursor.fetchall())  
	self.conn.commit()  
	return output  

def __init__(self,database_location:str = ":memory:"):  
	self.conn = sqlite3.connect(database_location)  
	self.cursor = self.conn.cursor()
