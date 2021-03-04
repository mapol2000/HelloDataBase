package mapol2000.hellodatabase

data class Book(val brand: String, val bkName: String, val author: String, val pubDate: String, val price: String) {
    constructor(bid: String, bkName: String, author: String, pubDate: String) : this("", bkName, author, pubDate, "") {
        this.bid = bid
    }

    constructor(bid: String, brand: String, bkName: String, author: String, pubDate: String, price: String, regDate: String) : this(brand,bkName,author,pubDate,price) {
        this.bid = bid
        this.regDate = regDate
    }

    var bid: String = ""
    var regDate: String = ""
}
