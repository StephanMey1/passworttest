
fun testPassword(pw: String): Int {
    //Passwörter mit weniger als 8 Zeichen sind immer untauglich
    if (pw.length < 8)
        return 0
    //auschließlich Ziffern: untauglich
    if (pw.all { it.isDigit() })
        return 0
    //langes Passwort mit unterschiedlichen Zeichen: gut
    var result = 0
    val distinct = pw.toSet().size
    if (pw.length >= 10 && distinct >= 7)
        result++
    // sehr lang: noch besser
    if (pw.length > 10)
        result++
    //enthält zumindestens eine Ziffer, ein Sonderzeichen oder einen Großbuchstaben
    val special = "0123456789!$%&/(),;.:-_#+*"
    for (c in pw)
        if (c.isUpperCase() || special.contains(c)){
            result++
            break
        }
    //Enhält keines der populär schlechten Passwörter
    val pwlower = pw.toLowerCase()
    result++ // optimistisch das das so ist :-)
    val badwords = listOf("123" , "111" , "abc" , "pass" , "word" ,
        "qwe" , "asd" , "love" , "login" , "admin")
    for (word in badwords)
        if (pwlower.contains(word)) {
            result-- // aber werden enttäuscht ;-)
            break
        }
    return result
}

fun main() {
    val pws = listOf("123456", "123123213123213", "abc123xyz", "sdfssfdafdafdafdf", "einGutesPasswort", "Lorem ipsum dolores est")
    for (pw in pws) {
        val n = testPassword(pw)
        val fmt = "Passwort: %-30s    Punkte: %d"
        println(fmt.format(pw, n)) }
}
