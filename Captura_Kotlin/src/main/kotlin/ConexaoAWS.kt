import org.apache.commons.dbcp2.BasicDataSource
import org.springframework.jdbc.core.JdbcTemplate

object ConexaoAWS {
    var jdbcTemplate: JdbcTemplate? = null
        get() {
            if (field == null) {
                val dataSource = BasicDataSource()
                dataSource.driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver"
                val servername = "34.194.127.191"
                val dataBase = "PowerTechSolutions"
                dataSource.url = "jdbc:sqlserver://$servername;database=$dataBase;encrypt=true;trustServerCertificate=true"
                dataSource.username = "sa"
                dataSource.password = "myLOVEisthe0506"
                val jdbcTemplate = JdbcTemplate(dataSource)
                field = jdbcTemplate
            }
            return field
        }
    fun iniciarSqlServer(){
        jdbcTemplate = jdbcTemplate!!
    }
}