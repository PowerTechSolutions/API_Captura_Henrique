Python 3.12.0 (tags/v3.12.0:0fb18b0, Oct  2 2023, 13:03:39) [MSC v.1935 64 bit (AMD64)] on win32
Type "help", "copyright", "credits" or "license()" for more information.
>>> import psutil
... import pyodbc
... import platform
... import socket
... from datetime import datetime
... 
... def get_machine_id():
...     # Exemplo simples de geração de um identificador de máquina
...     return f"{platform.node()}_{socket.gethostname()}"
... 
... def get_ram_info():
...     ram = psutil.virtual_memory()
...     return {
...         'total': ram.total,
...         'available': ram.available,
...         'percent_used': ram.percent,
...     }
... 
... def insert_ram_info(conn, machine_id, ram_info):
...     cursor = conn.cursor()
...     current_time = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
... 
...     query = """
...     INSERT INTO RamInfo (MachineID, Total, Available, PercentUsed, Timestamp)
...     VALUES (?, ?, ?, ?, ?)
...     """
... 
...     cursor.execute(query, (machine_id, ram_info['total'], ram_info['available'], ram_info['percent_used'], current_time))
...     conn.commit()
... 
... # Configuração da conexão com o SQL Server
... server = '34.194.127.191'
... database = 'PowerTechSolutions'
... username = 'sa'
... password = 'myLOVEisthe0506'
... 
... # Substitua os valores acima pelas suas configurações reais
... conn_str = f'DRIVER={{SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'
... conn = pyodbc.connect(conn_str)
... 
... # Obtém o identificador da máquina
... machine_id = get_machine_id()
... 
... # Loop para monitorar e inserir dados no SQL Server
... while True:
...     ram_info = get_ram_info()
...     insert_ram_info(conn, machine_id, ram_info)
...     
...     # Intervalo de espera (em segundos) entre as medições
...     interval_seconds = 60
...     time.sleep(interval_seconds)
... 
... 
