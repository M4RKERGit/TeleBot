package WBPackage.BotPack;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Random;
import java.net.*;
import java.io.*;

public class BAneks
{
    public static String getHTML() throws IOException {
        Random random = new Random();
        URL anekURL = new URL("https://baneks.ru/" + (1 + random.nextInt(1142)));
        BufferedReader html = new BufferedReader(new InputStreamReader(anekURL.openConnection().getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();

        while ((inputLine = html.readLine()) != null)
        {
            response.append(inputLine);
        }
        return response.toString();
    }

    public static String getAnek()
    {
        String returnAnek;
        Document parsed = new Document("");
        try
        {
            parsed = Jsoup.parse(getHTML());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        returnAnek = parsed.select(".anek-view p").text();
        return returnAnek;
    }

    public static void appendLog(Message message, String curDate, String user)
    {
        String log = curDate + " Received: " + message.getText() + " " + "From: " + user;
        System.out.println(log);
        try {
            Files.write(Path.of("E://botlog.txt"), (log + "\n").getBytes(), new StandardOpenOption[]{StandardOpenOption.APPEND});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
