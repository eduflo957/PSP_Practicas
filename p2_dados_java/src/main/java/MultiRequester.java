import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Logger;

public class MultiRequester {
    private static Logger logging = PSPLogger.getLogger(MultiRequester.class.getName());

    private static final String URIBASE ="http://192.168.56.203:5000/dec2bin";
    
    private static void testHttpSynchro(long ncalls) {
        HttpResponse response = null;
        HttpRequest request = null;

        List<URI> uris = new ArrayList<URI>();
        for (int i = 0; i<ncalls; i++) {
            try {
                uris.add(new URI(String.format("%s/%d", URIBASE, i)));
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(10))
        .build();

        for (URI uri : uris) {
            try {
                request = HttpRequest.newBuilder()
                        .uri(uri)
                        .build();
                response = httpClient.send(request, 
                        HttpResponse.BodyHandlers.ofString());
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
            logging.fine("URI: " + request.uri().toString());
            logging.fine("Status code: " + response.statusCode());
            logging.fine("Headers: " + response.headers()
                    .allValues("content-type"));
            logging.fine("Body: " + response.body());
        }
    }

    private static void testHttpAsynchro(long ncalls) {

        
    

        List<URI> uris = new ArrayList<URI>();
        for (int i = 0; i<ncalls; i++) {
            try {
                uris.add(new URI(String.format("%s/%d", URIBASE, i)));
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .followRedirects(HttpClient.Redirect.ALWAYS)
                .build();

        CompletableFuture[] futures = uris.stream()
                .map(uri -> verifyUri(httpClient, uri))
                .toArray(CompletableFuture[]::new);
        
        CompletableFuture.allOf(futures).join(); 
        logging.info("Completadas todas las tareas");     
    }   

    private static CompletableFuture<Void> verifyUri(HttpClient httpClient, URI uri) {
        HttpRequest request = HttpRequest.newBuilder()
                .timeout(Duration.ofSeconds(5))
                .uri(uri)
                .build();
        return httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::statusCode)
                .thenApply(statusCode -> statusCode == 200)
                .exceptionally(__ -> false)
                .thenAccept(valid -> logging.fine("Resultado para " + uri + ": " + valid));
    }

    private static void httpUnitaryRequest (HttpClient client, HttpRequest request) {
        try {
            HttpResponse response = client.send(request,
                                      HttpResponse.BodyHandlers.ofString());
            logging.fine("URI: " + request.uri().toString());
            logging.fine("Status code: " + response.statusCode());
            logging.fine("Headers: " + response.headers()
                    .allValues("content-type"));
            logging.fine("Body: " + response.body());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private static void testHttpThreads (long ncalls) {
        Thread[] th = new Thread[(int)ncalls];
        HttpRequest[] requests = new HttpRequest[(int)ncalls];
        int i;
        
        List<URI> uris = new ArrayList<URI>();
        for (i = 0; i<ncalls; i++) {
            try {
                uris.add(new URI(String.format("%s/%d", URIBASE, i)));
            } catch (URISyntaxException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        HttpClient httpClient = HttpClient.newBuilder()
        .connectTimeout(Duration.ofSeconds(60))
        .build();

        for (i = 0; i < ncalls; i++) {
            try {
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(uris.get(i))
                        .build();
                requests[i] = request;
                
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            
        }

        for (i = 0; i < ncalls ; i++) {    
            HttpRequest request = requests[i];
            th[i] = new Thread(()-> httpUnitaryRequest(httpClient, request));
        }

        for (i = 0; i < ncalls; i++) {
            th[i].start();
        }

        for (i = 0; i < ncalls; i++) {
            try {
                th[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

    private static void testHttp() {
        final long NCALLS = 5000;

        // logging.info("Comienza el test sincro");
        // testHttpSynchro(NCALLS);
        // logging.info("Finaliza el test sincro");

        logging.info("Comienza el test con threads");
        testHttpThreads(NCALLS);
        logging.info("Termina el test con threads");

        // logging.info("Comienza el test asincro");
        // testHttpAsynchro(NCALLS);
        // logging.info("Finaliza el test asincro");
    }
    
    
    private static void testLogging() {
        logging.finest("Esto es finest");
        logging.finer("Esto es finer");
        logging.fine("Esto es fine");
        logging.config("Esto es config");
        logging.info("Esto es info");
        logging.warning("Esto es warning");
        logging.severe("Esto es error");
    }

    public static void main(String[] args) {
        
        logging.info("Empieza el programa");
        testLogging();
        // testHttp();
        logging.info("Acaba el programa");
    }
}