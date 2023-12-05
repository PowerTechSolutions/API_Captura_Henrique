import com.github.britooo.looca.api.core.Looca
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Scanner

fun main() {
    val repositorio = Repositorio()
    repositorio.iniciar()

    var sn = Scanner(System.`in`)

    val looca = Looca()
    val grupojanela = looca.grupoDeJanelas
    var janelas = grupojanela.janelasVisiveis

    println("Qual seu Email?")
    var email = sn.next()

    println("Qual seu Senha?")
    var senha = sn.next()

    if(repositorio.autenticar(email,senha)>0){

        var usuario = repositorio.buscarInfo(email,senha)

        var maquinas = repositorio.buscarMaquina(usuario.IDUsuario)

        var texto = ""

        for (maquina in maquinas){

            texto += "\n\r${maquina.IDMaquina} | ${maquina.Apelido}"

        }

        println("""
Digite a numeração a maquina para fazer a captura,$texto
        """.trimIndent())

        var idMaquina = sn.next().toInt()
        val dataHoraAtual = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")

            repositorio.insert(idMaquina)


    }





    //repositorio.inserirBanco(captacao)
}