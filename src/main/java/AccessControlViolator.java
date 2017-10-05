import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;

/* Simple class to illustrate access control violation in Java. */
class X {
	private int x = 1;
}

/** This class violates normal access control rules: it accesses private data in other classes with impunity. */
class AccessControlViolator {

	public static void main(String[] ignoredArgs) throws Exception {
		new AccessControlViolator(new X());
	}

	AccessControlViolator(Object object) throws Exception {
	    System.out.println("Accessing field: x");
		Field xField = object.getClass().getDeclaredField("x");
		xField.setAccessible(true);
		System.out.println("Accessing field: x");
		System.out.println("Value before set call: " + xField.getInt(object));
		xField.setInt(object, 123);
		System.out.println("Value after set call: " + xField.getInt(object));
	}
}
