import org.springframework.jdbc.core.JdbcTemplate

class Repositorio {
    lateinit var jdbcTemplate: JdbcTemplate

    fun iniciar(){
        jdbcTemplate = Conexao.jdbcTemplate!!
    }

    fun add(captacao: Janela){
        jdbcTemplate.execute("""
            INSERT INTO teste VALUES
            (null, ${captacao.dado}, "${captacao.dtHora}")
        """)
    }
}