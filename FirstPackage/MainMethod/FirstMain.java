package MainMethod;

import java.util.List;

import org.testng.TestNG;
import org.testng.collections.Lists;

public class FirstMain {
	
	public static void main(String[] args) {
		TestNG testng = new TestNG();
		List<String> suites = Lists.newArrayList();
		suites.add("C:\\Users\\Novastrid IT\\eclipse-workspace\\SalixNew\\testng.xml");
		testng.setTestSuites(suites);
		testng.run();
		}
}
