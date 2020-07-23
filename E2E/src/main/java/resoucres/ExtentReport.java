package resoucres;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {

	static ExtentReports extent;
	public static ExtentReports getReports() {

		String setPath=System.getProperty("user.dir")+ ("\\reports\\index.html");
		ExtentSparkReporter reporter=new ExtentSparkReporter(setPath);
		reporter.config().setDocumentTitle("Extent Reports");
		extent=new ExtentReports();
		extent.attachReporter(reporter);
		extent.setSystemInfo("Tester", "Raina Bhowal");
		return extent;
	}
}