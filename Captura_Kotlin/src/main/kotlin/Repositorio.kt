import com.github.britooo.looca.api.group.janelas.Janela
import org.springframework.jdbc.core.BeanPropertyRowMapper
import org.springframework.jdbc.core.JdbcTemplate

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = ConexaoAWS.jdbcTemplate!!
    }

    fun autenticar(email:String,senha:String):Int{
        var usuario = jdbcTemplate.queryForObject(
            "SELECT Count(IDUsuario) FROM Usuario_Dashboard WHERE Email = '${email}' AND Senha = '${senha}'",
            Int::class.java
        )

        return usuario

    }

    fun buscarInfo(email:String,senha:String):Usuario{

        var usuario = jdbcTemplate.queryForObject(
            "SELECT * FROM Usuario_Dashboard WHERE Email = '${email}' AND Senha = '${senha}'",
            BeanPropertyRowMapper(Usuario::class.java)
        )

        return usuario
    }

    fun buscarMaquina(idUsuario: Int):MutableList<Maquina>{

        var maquinas = jdbcTemplate.query(
            "SELECT * FROM Maquinas WHERE FKFuncionario = ${idUsuario}"
            ,BeanPropertyRowMapper(Maquina::class.java)
        )

        return maquinas

    }

    fun insert(idmaquina:Int, Looca: Janela){

        CodigoPython.execPython(idmaquina,Looca)

    }

}