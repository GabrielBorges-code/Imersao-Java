import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class App {
    public static void main(String[] args) throws Exception {

        // String url = "https://mocki.io/v1/9a7c1ca9-29b4-4eb3-8306-1adb9d159060"; //IMDB
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        // String url = "https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY&start_date=2022-05-17&end_date=2022-05-19"; // só mudar a data
        // ExtratorDeConteudo extrator = new ExtratorDeConteudoDaNasa();
        
        String url = "http://localhost:8080/linguagens"; // só mudar a data
        ExtratorDeConteudo extrator = new ExtratorDeConteudoDoIMDB();

        var http = new ClienteHttp();
        String json = http.buscaDados(url);

        List<Conteudo> conteudos = extrator.extraiConteudos(json);

        var geradora = new GeradoraDeFigurinhas();

        for (int i = 0; i < 2; i++) {

            Conteudo conteudo = conteudos.get(i);
            
            InputStream inputStream = new URL(conteudo.getUrlImagem()).openStream();
            String nomeArquivo = conteudo.getTitulo().replace(":", "-") + ".png";

            geradora.cria(inputStream, nomeArquivo);

            System.out.println(i + " - Título do Conteudo: \u001b[1m" + conteudo.getTitulo() + "\u001b[m");
            // System.out.println("Rank: \u001b[1m" + conteudo.get("rank") + "\u001b[m");
            // System.out.println("Poster: \u001b[1m" + filme.get("image") + "\u001b[m");
            // System.out.println("\u001b[44mClassificação: \u001b[1m" +  filme.get("imDbRating") + "\u001b[m");
            
            System.out.println("------------------------------------");

        }
    }
}
