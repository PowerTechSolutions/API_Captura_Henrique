import psutil
import win32gui
import win32process

def get_window_memory_usage():
    # Obtém todas as janelas abertas
    windows = []
    win32gui.EnumWindows(lambda hwnd, windows: windows.append(hwnd), windows)

    for window_handle in windows:
        try:
            # Obtém o ID do processo associado à janela
            pid = win32process.GetWindowThreadProcessId(window_handle)
           
            # Obtém o objeto de processo
            process = psutil.Process(pid[-1])
           
            # Obtém as informações de uso de memória
            memory_info = process.memory_info()
            total_memory = psutil.virtual_memory().total
            memory_percent = (memory_info.rss / total_memory) * 100
           
            # Obtém o título da janela
            window_title = win32gui.GetWindowText(window_handle)
           
            print(f"A porcentagem de RAM usada pela janela '{window_title}' é: {memory_percent:.2f}%")
        except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
            pass

# Obtém e exibe a porcentagem de uso de memória para todas as janelas
get_window_memory_usage()

def insert_ram_info(conn, machine_id, ram_info):
     cursor = conn.cursor()
     current_time = datetime.now().strftime('%Y-%m-%d %H:%M:%S')
 
     query = """
     INSERT INTO RamInfo (MachineID, Total, Available, PercentUsed, Timestamp)
     VALUES (?, ?, ?, ?, ?)
     """
 
     cursor.execute(query, (machine_id, ram_info['total'], ram_info['available'], ram_info['memory_percent'], current_time))
     conn.commit()
 
 # Configuração da conexão com o SQL Server
 server = '34.194.127.191'
 database = 'PowerTechSolutions'
 username = 'sa'
 password = 'myLOVEisthe0506'
 
 # Substitua os valores acima pelas suas configurações reais
 conn_str = f'DRIVER={{SQL Server}};SERVER={34.194.127.191};DATABASE={PowerTechSolutions};UID={sa};PWD={myLOVEisthe0506}'
 conn = pyodbc.connect(conn_str)
 
 # Obtém o identificador da máquina
 machine_id = get_machine_id()
 
 # Loop para monitorar e inserir dados no SQL Server
 while True:
     ram_info = get_ram_info()
     insert_ram_info(conn, machine_id, ram_info)
     
     # Intervalo de espera (em segundos) entre as medições
     interval_seconds = 60
     time.sleep(interval_seconds)
 
 
