package sayrunjah.gmsa.model

import com.j256.ormlite.field.DatabaseField
import com.j256.ormlite.table.DatabaseTable

@DatabaseTable(tableName = "users")
class User{
    @DatabaseField(generatedId = true)
    var id: Int = 0

    @DatabaseField(columnName = "username", canBeNull = false)
    var username: String = ""

    @DatabaseField(columnName = "password", canBeNull = false)
    var password: String = ""

    constructor()
    constructor(username: String, password: String){
        this.username = username
        this.password = password
    }

}