import kotlinx.serialization.*
import kotlinx.serialization.json.*
import java.net.HttpURLConnection
import java.net.URL

@Serializable
data class Video(
    val id: Int = 0,
    val videoUrl: String  = "null",
    val title: String = "null",
    val speaker: String  = "null",
)

fun getVideo(id: Int): Video {
    var obj: Video = Video()
    try {
        val url: URL =  URL ("https://my-json-server.typicode.com/kotlin-hands-on/kotlinconf-json/videos/$id")
        val con: HttpURLConnection = url.openConnection() as HttpURLConnection
        con.connectTimeout = 5000
        con.readTimeout = 5000
        val reader = con.inputStream.reader()
        var json = reader.readText()
        reader.close()
        con.disconnect()
        var encode: JsonElement = Json.parseToJsonElement(json)
        obj = Json.decodeFromJsonElement(encode,)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return obj
}


fun main(){
    var video = getVideo(1)
    println(video)
}