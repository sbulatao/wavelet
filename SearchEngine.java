import java.io.IOException; // imports package java.io.IOException
import java.net.URI; // imports java net URI

class Handle implements URLHandler { // a class called HAndler that implements URLHandler
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    String str = ""; 
    int count = 0;

    public String handleRequest(URI url) { // this is a public string called handleRequest with parameters URI url
        if (url.getPath().equals("/")) { // if the path is equal to / then it returns
            return "Welcome to Search Engine!!!" + str; 

        } else if (url.getPath().contains("/search")) {
            count = 0;
            String[] parameter = url.getQuery().split("=");
                if (parameter[0].equals("s")) { // if the paramenter within the [0] equals count
                    count =+ 1;
                    str += parameter[count] + "\n";
                    return str;
                }
           return "Not Found";

        } else { // all else this happens
            System.out.println("Path: " + url.getPath()); // this prints out the path
            if (url.getPath().contains("/add")) { // if path contains add we go here
                count = 0;
                String[] parameters = url.getQuery().split("="); // this gets the query abnd splits it
                if (parameters[0].equals("s")) { // if the paramenter within the [0] equals count
                    count =+ 1;
                    str += parameters[count] + "\n"; 
                    return "String added: " + parameters[1] + "! It's now " + str;
                }
            }
            return "404 Not Found!"; // else overall this happens
        }
    }
}

class SearchEngine { // class name NumberServer
    public static void main(String[] args) throws IOException { // this is the main
        if(args.length == 0){ // if the args length is 0
            System.out.println("Missing port number! Try any number between 1024 to 49151"); // this prints out
            return;
        }

        int port = Integer.parseInt(args[0]); // this is port

        Server.start(port, new Handle()); // this is where server stars with port and new handler
    }
}