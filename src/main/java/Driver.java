import java.io.File;

public class Driver {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("No arguments provided. Please enter a command.");
        }

        final String command = args[0];

        if (command.equals("1")) {
            System.out.println("----- Homework 2 - Part 1 -----");
            try (final DirectoryClassLoader cl1 = new DirectoryClassLoader(new File(args[0]).toURI().toURL());
                final DirectoryClassLoader cl2 = new DirectoryClassLoader(new File(args[0]).toURI().toURL());) {
                System.out.println("----- Testing Same ClassLoader -----");
                testClassLoaders(cl1, cl1);
                System.out.println("----- Testing Different ClassLoader -----");
                testClassLoaders(cl1, cl2);
            }
        }

    }

    private static void testClassLoaders(ClassLoader cl1, ClassLoader cl2) throws Exception {
        final Class< ? > class1 = cl1.loadClass("TestClass");
        final Class< ? > class2 = cl2.loadClass("TestClass");

        final Object o1 = class1.newInstance();
        final Object o2 = class2.newInstance();

        //Print hashcodes
        System.out.println("Hashcode of ClassLoaders and TestClass instances");
        System.out.println("TestClass [" + class1.hashCode() + "] loaded by classloader DirectoryClassLoader [" + cl1.hashCode() + "]");
        System.out.println("TestClass [" + class2.hashCode() + "] loaded by classloader DirectoryClassLoader [" + cl2.hashCode() + "]");
        System.out.println("Object 1 [" + o1.hashCode() + "] an instance of class TestClass [" + class1.hashCode() + "]");
        System.out.println("Object 2 [" + o2.hashCode() + "] an instance of class TestClass [" + class2.hashCode() + "]");

        System.out.println("Comapring TestClass objects 1 & 2");
        if (o1.equals(o2)) {
            System.out.println("Objects are equal");
        } else {
            System.out.println("Objects are not equal");
        }
    }

}
