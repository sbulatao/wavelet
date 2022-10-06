import java.io.IOException; // imports package java.io.IOException
import java.net.URI; // imports java net URI

class Handler implements URLHandler { // a class called HAndler that implements URLHandler
    // The one bit of state on the server: a number that will be manipulated by
    // various requests.
    int num = 0; // variable called nums with 0 initiallized

    public String handleRequest(URI url) { // this is a public string called handleRequest with parameters URI url
        if (url.getPath().equals("/")) { // if the path is equal to / then it returns
            return String.format("Number: %d", num); // the format of Number: 0 since num is equal to 0
        } else if (url.getPath().equals("/increment")) { // else is the path equals to increment
            num += 1; // we increase the num by 1
            return String.format("Number incremented!"); // then returns this format
        } else { // all else this happens
            System.out.println("Path: " + url.getPath()); // this prints out the path
            if (url.getPath().contains("/add")) { // if path contains add we go here
                String[] parameters = url.getQuery().split("="); // this gets the query abnd splits it
                if (parameters[0].equals("count")) { // if the paramenter within the [0] equals count
                    num += Integer.parseInt(parameters[1]); // the num increases
                    return String.format("Number increased by %s! It's now %d", parameters[1], num); // then returns this format and updates %s and %d
                }
            }
            return "404 Not Found!"; // else overall this happens
        }
    }
}

class NumberServer { // class name NumberServer
    public static void main(String[] args) throws IOException { // this is the main
        if(args.length == 0){ // if the args length is 0
            System.out.println("Missing port number! Try any number between 1024 to 49151"); // this prints out
            return;
        }

        int port = Integer.parseInt(args[0]); // this is port

        Server.start(port, new Handler()); // this is where server stars with port and new handler
    }
}
