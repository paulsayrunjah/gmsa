package sayrunjah.gmsa.util

import com.j256.ormlite.dao.DaoManager
import com.j256.ormlite.jdbc.JdbcConnectionSource
import com.j256.ormlite.table.TableUtils
import sayrunjah.gmsa.model.User
import java.sql.DriverManager
import java.sql.SQLException

class DbService{

    fun connectionSource() : JdbcConnectionSource {
        val connectionString = "jdbc:sqlite:/media/sayrunjah/pauuz/Share/gmsa.db"
        return JdbcConnectionSource(connectionString)
    }


    fun dropTable(table: String){
        try {
            val connectionString = "jdbc:sqlite:/media/sayrunjah/pauuz/Share/gmsa.db"
            val conn = DriverManager.getConnection(connectionString)
            val stmt = conn.createStatement()
            val sql = "DROP TABLE IF EXISTS $table"
            stmt.executeUpdate(sql)
            println("Connected")

        }catch (e : SQLException){
            println(e.message)
        }
    }

    fun connectionOrm(){
        val connectionString = "jdbc:sqlite:/media/sayrunjah/pauuz/Share/gmsa.db"
        val connectionSource = JdbcConnectionSource(connectionString)
        val userDao = DaoManager.createDao(connectionSource, User::class.java)

        dropTable("users")
        dropTable("accounts")
        TableUtils.createTable(connectionSource, User::class.java)
    }


}