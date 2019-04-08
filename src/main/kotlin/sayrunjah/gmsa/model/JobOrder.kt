package sayrunjah.gmsa.model

class JobOrder{
    var id: Int = 0
    var desc: String = ""
    var date: String = ""

    constructor(id: Int, desc: String, date: String){
        this.id = id
        this.desc = desc
        this.date = date
    }
}