import com.github.britooo.looca.api.core.Looca
import com.github.britooo.looca.api.group.janelas.Janela

fun main() {
    var looca = Looca()
    var grupoJanelas = looca.grupoDeJanelas
    var janelas:MutableList<Janela> = grupoJanelas.janelasVisiveis
    var qtdJanela = grupoJanelas.totalJanelasVisiveis

    for (janela in janelas){
        if (janela.isVisivel){
            println("""
                ${janela.pid}
                ${janela.janelaId}
                ${janela.titulo}
                ${janela.comando}
            """.trimIndent())
        }
    }
}