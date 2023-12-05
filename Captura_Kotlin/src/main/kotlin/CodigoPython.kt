import com.github.britooo.looca.api.group.janelas.Janela
import java.io.File

object CodigoPython {

    fun execPython(idMaquina:Int){

        var codigo = """
import psutil
import win32gui
import win32process
import pyodbc
import datetime

 # Configuração da conexão com o SQL Server
server = 'ec2-34-194-127-191.compute-1.amazonaws.com'
database = 'PowerTechSolutions'
username = 'sa'
password = 'myLOVEisthe0506'
 
 # Substitua os valores acima pelas suas configurações reais
conn_str = f'DRIVER={{SQL Server}};SERVER={server};DATABASE={database};UID={username};PWD={password}'
conn = pyodbc.connect(conn_str)

def insert_ram_info(conn,memory_percent,window_title):
     cursor = conn.cursor()
 
     query = f"INSERT INTO Henry (Janela,Uso_Ram,FKMaquina) VALUES ('{window_title}', {memory_percent:.2f}, $idMaquina)"
 
     cursor.execute(query)
     conn.commit()
     
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
        
        if(window_title != ""):
            insert_ram_info(conn,memory_percent,window_title)        
    except (psutil.NoSuchProcess, psutil.AccessDenied, psutil.ZombieProcess):
        pass

# Obtém e exibe a porcentagem de uso de memória para todas as janelas
 
 # Obtém o identificador da máquina
#machine_id = get_machine_id()
"""

        var nomeArquivo = "CodigoPythonHenry.py"

        File(nomeArquivo).writeText(codigo)
        Runtime.getRuntime().exec("py $nomeArquivo")

    }

}