import java.io.File;

public class Driver {

    public static void main(String[] args) throws Exception {
        if(args.length == 0){
            System.out.println("No arguments provided. Please enter a command.");
        }
        
        ClassLoader cl = new DirectoryClassLoader(new File(args[0]).toURI().toURL());
        Object o = cl.loadClass("TestClass");
        System.out.println(o);        

    }
}
