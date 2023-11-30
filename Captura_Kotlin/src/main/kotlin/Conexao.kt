import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object Conexao {
    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.mysql.cj.jdbc.Driver"
                val servername = "Localhost"
                val dataBase = "PowerTechSolutions"
                dataSource.url = "jdbc:mysql://$servername/$dataBase"
                dataSource.username = "aluno"
                dataSource.password = "sptech"
                val jdbcTemplate = JdbcTemplate(dataSource)
                field = jdbcTemplate
            }
            return field
        }
}