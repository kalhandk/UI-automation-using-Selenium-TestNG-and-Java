package com.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.config.DriverFactory;
import com.page.YouTubeExplorePage;

public class YouTubeExploreTest {

	public WebDriver driver;
	public String videoTitle;
	public String pageTitle;
	public String videoUrl;
	public String videoId;

	@BeforeTest
	public void setup() {
		driver = DriverFactory.getInstanceOfDriverFactory().getDriverFactory();
	}

	@Test(priority = 1, enabled = true)
	public void navigateToYouTube() throws InterruptedException {
		String expectedTiltle = "YouTube";
		String actualTitle = driver.getTitle();
		Thread.sleep(2000);
		Assert.assertEquals(actualTitle, expectedTiltle);

		System.out.println(" =============== End of Test 1 verify Navigate To YouTube ==================== ");
	}

	// Navigate to YouTube Explore section
	@Test(priority = 2, enabled = true)
	public void navigateToExplore() throws InterruptedException {
		YouTubeExplorePage youTubeExplore = new YouTubeExplorePage(driver);
		Thread.sleep(5000);
		youTubeExplore.viewExplore();
		Thread.sleep(5000);

		System.out.println(" =============== End of Test 2 verify Navigate to YouTube Explore ==================== ");
	}

	// Click on the second video
	@Test(priority = 3, enabled = true)
	public void viewSecondVideo() throws InterruptedException {
		YouTubeExplorePage youTubeExplore = new YouTubeExplorePage(driver);
		Thread.sleep(5000);

		videoTitle = youTubeExplore.getvideoTitle();
		youTubeExplore.viewSecondVideo();
		Thread.sleep(5000);
		pageTitle = youTubeExplore.getpageTitle();
		Thread.sleep(5000);

		System.out.println(" =============== End of Test 3 verify view the second video ==================== ");
	}

	// Tracking the browser page title and verify it matches the title of the video
	@Test(priority = 4, enabled = true)
	public void verifyPageTitle() throws InterruptedException {
		String expectedTiltle = videoTitle;

		// in the page title, it includes " - YouTube" section, which is not appearing
		// in the video title, Therefore, replaced that section using an empty String
		String actualTitle = pageTitle.replace(" - YouTube", "");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Assert.assertEquals(actualTitle, expectedTiltle);

		System.out.println(" =========== End of Test 3 verify Page Title matahced to the VideoTitle =============== ");
	}

	// Get the video Id from the URL by spliting after the equal sign
	@Test(priority = 5, enabled = true)
	public void verifyVideoID() throws InterruptedException {

		videoUrl = driver.getCurrentUrl();
		System.out.println("current URL : " + videoUrl);
		
		//https://www.youtube.com/watch?v=POe9SOEKotk
		//assign to an array with 2 elements, 0th element is the http//... and 1st element is 
		//POe9SOEKotk
		//check the equal sign and split the 1st element
		videoId = videoUrl.split("=")[1];
		System.out.println("current videoId : " + videoId);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		System.out.println(" =============== End of Test 5 Get the video Id from the URL ==================== ");
	}

	// Verify search video and play the 1st video
	@Test(priority = 6, enabled = true)
	public void searchVideo() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		YouTubeExplorePage youTubeExplore = new YouTubeExplorePage(driver);

		youTubeExplore.searchVideo(videoTitle);
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		youTubeExplore.clickFirstVideo();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		System.out.println(" =============== End of Test 6 verify search video and click on it ==================== ");
	}

	// Verify the video has the same URL Id which found earlier in above Test #5
	@Test(priority = 7, enabled = true)
	public void confirmPageURL() throws InterruptedException {
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

		//Get the current URL of the web page
		String currentURL = driver.getCurrentUrl();
		System.out.println("current URL : " + currentURL);

		String currentvideoId = currentURL.split("=")[1];

		System.out.println("current videoId : " + currentvideoId);
		Assert.assertEquals(videoId, currentvideoId);

		// video has the same URL id as the one we found earlier, confirming it is the
		// same video.
		System.out.println(	" ========= End of Test 7 verify both video Ids are equal =========== ");
	}

	// terminate the browser session
	@AfterTest
	public void endSession() throws InterruptedException {
		System.out.println("====== Terminating the browser sessoin =======");
		Thread.sleep(7000);
		driver.close();
	}

}
