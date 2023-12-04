import pyodbc

conn = pyodbc.connect(
	'DRIVER=ODBC Driver 17 for SQL Server;'
	'SERVER=ec2-34-194-127-191.compute-1.amazonaws.com;'
	'DATABASE=PowerTechSolutions;'
	'UID=sa;'
	'PWD=myLOVEisthe0506;'
	'Encrypt = Optional;'
)

cursor = conn.cursor()

SQL_QUERY = "INSERT INTO Monitoramento_RAW (Uso) VALUES (20)"
cursor.execute(SQL_QUERY)
conn.commit()