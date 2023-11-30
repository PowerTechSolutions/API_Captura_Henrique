import com.github.britooo.looca.api.core.Looca
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun main() {
    val repositorio = Repositorio()
    repositorio.iniciar()

    val looca = Looca()
    val janela = looca.grupoDeJanelas
    val qtdJanela = janela.totalJanelasVisiveis
    println(qtdJanela)

    val dataHoraAtual = LocalDateTime.now()
    val formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    val dataHoraFormatada = dataHoraAtual.format(formato)

    val captacao = Janela()
    captacao.dado = qtdJanela
    captacao.dtHora = dataHoraFormatada

    repositorio.add(captacao)
}