import java.io.File;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class Driver {

    public static void main(String[] args) throws Exception {

        if (args.length == 0) {
            System.out.println("No arguments provided. Please enter a command.");
        }

        final String command = args[0];

        switch (command) {
            case "1":
                String option1 = args[1];
                if (option1 == null || option1.isEmpty()) {
                    option1 = "classLoadDir";
                }
                runPart1(option1);
                break;
            case "2":
                runPart2();
                break;
            case "3":
                // runPart3;
                break;
            default:
                System.out.println("Command entered is not supported: " + command);
                break;
        }

    }

    private static void runPart1(final String option1) throws Exception {
        System.out.println("----- Homework 2 - Part 1 -----");
        try (final DirectoryClassLoader cl1 = new DirectoryClassLoader(new File(option1).toURI().toURL());
            final DirectoryClassLoader cl2 = new DirectoryClassLoader(new File(option1).toURI().toURL());) {
            System.out.println("----- Testing Same ClassLoader -----");
            testClassLoaders(cl1, cl1);
            System.out.println("----- Testing Different ClassLoader -----");
            testClassLoaders(cl1, cl2);
        }
    }

    /*
     * Classloaders in java help prevent attackers from injecting class implementations into java.lang in place of existing classes.
     * Through namespaces, they also prevent bad classes from being injected into other packages. Availability wise, Java's classloaders enable dynamic class upgrades. This means no-outage or JVM downtime is necessarily required for updating a specific class.
     * 
     * https://www.javaworld.com/article/2077009/core-java/security-and-the-class-loader-architecture.html?page=2
     */

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

    private static void runPart2() throws Exception {
        System.out.println("----- Homework 2 - Part 2 -----");

        System.out.println("----- Part 2 - Test Case 1 -----");
        Part2 part2_1 = new Part2(5);
        Part2 part2_2 = new Part2(6);

        System.out.println("Instance of Part2 constructed with internal value 5");
        System.out.println("Instance of Part2 constructed with internal value 6");
        System.out.println("Calling protected method, #addPart2(Part2) using the two instances returns: " + part2_1.addTestCase1(part2_2));

        System.out.println("\n----- Part 2 - Test Case 2 -----");
        System.out.println("Instance of Part2 constructed with internal value 5");
        System.out.println("Instance of Part2 constructed with internal value 6");
        System.out.println("First instance calling second's #callPrivateMethod(Part2)");
        part2_1.callPrivateMethod(part2_2);
        System.out.println("Second instance calling first's #callPrivateMethod(Part2)");
        part2_2.callPrivateMethod(part2_1);

        System.out.println("\n----- Part 2 - Test Case 3 -----");
        part2_1 = new Part2(5, 10);
        System.out.println("Instance of Part2 constructed with private field 'internalValue'=5 and protected field 'protectValue'=10");

        System.out.println("Directly accessing private field of Part2 using reflection...");
        final Field privateField = part2_1.getClass().getDeclaredField("internalValue");
        privateField.setAccessible(true);
        final int internalValue = (int)privateField.get(part2_1);
        System.out.println("Retrieved value: " + internalValue);

        System.out.println("Directly accessing protected field of Part2 using reflection...");
        final Field protectedField = part2_1.getClass().getDeclaredField("protectValue");
        protectedField.setAccessible(true);
        final int protectValue = (int)protectedField.get(part2_1);
        System.out.println("Retrieved value: " + protectValue);

        System.out.println("\n----- Part 2 - Test Case 3 (cont.)-----");
        AccessControlViolator.main(new String[]{});

        //  Method privateMethod = part2_1.getClass().getMethod("privateMethod");
        // privateMethod.setAccessible(true);
        //  privateMethod.invoke(part2_1);
        // System.out.println("Direclty invoking private method of Part2 using reflection");

    }
    
    private static void part3(){
        
    }

}
