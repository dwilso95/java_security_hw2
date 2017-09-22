import java.net.URLClassLoader;
import java.net.URL;

public class DirectoryClassLoader extends URLClassLoader {
    
    final URL url;
    
    public DirectoryClassLoader(final URL url){
        super(new URL[] { url });
        this.url = url;
    }

}
