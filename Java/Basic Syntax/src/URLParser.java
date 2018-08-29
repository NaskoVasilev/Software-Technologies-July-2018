import java.util.Scanner;

public class URLParser {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        String protocol = "";
        String server ="";
        String resource = "";

        if (url.contains("://")) {
            String[] tokens = url.split("://");
            protocol = tokens[0];
            String extension = tokens[1];

            if (extension.contains("/")) {
                server = extension.substring(0, extension.indexOf('/'));
                resource = extension.substring(extension.indexOf("/") + 1);
            } else {
                server = extension;
            }
        } else {
            if (url.contains("/")) {
                server = url.substring(0, url.indexOf('/'));
                resource = url.substring(url.indexOf("/") + 1);
            } else {
                server = url;
            }
        }

        System.out.println("[protocol] = \"" + protocol + "\"");
        System.out.println("[server] = \"" + server + "\"");
        System.out.println("[resource] = \"" + resource + "\"");
    }
}
