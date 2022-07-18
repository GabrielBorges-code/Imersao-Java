import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
      
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

        String body = response.body();
        
        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

     
        for (Map<String,String> filme : listaDeFilmes) {
            System.out.println("Rank: \u001b[1m" + filme.get("rank") + "\u001b[m");
            System.out.println("Título do filme: \u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("Poster: \u001b[1m" + filme.get("image") + "\u001b[m");
            System.out.println("\u001b[44mClassificação: \u001b[1m" +  filme.get("imDbRating") + "\u001b[m");
            
            System.out.println("------------------------------------");

        }

    }
}
