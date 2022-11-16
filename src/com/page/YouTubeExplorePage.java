package com.page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class YouTubeExplorePage {

	// 1) Define Web Driver
	public WebDriver Driver;

	// 2) Identify the Web elements using Factory Method
	@FindBy(xpath = "/html/body/ytd-app/div[1]/tp-yt-app-drawer/div[2]/div/div[2]/div[2]/ytd-guide-renderer/div[1]/ytd-guide-section-renderer[3]/div/ytd-guide-entry-renderer[1]/a/tp-yt-paper-item")
	// *[@id=\"endpoint\" and @title=\"Explore\"]]
	private WebElement explorebtn;

	@FindBy(xpath = "//*[@id=\"grid-container\"]/ytd-video-renderer[2]")
	private WebElement secondVideo;

	@FindBy(xpath = "//*[@id=\"grid-container\"][1]//*[@class=\"style-scope ytd-expanded-shelf-contents-renderer\"][2]//*[@id=\"video-title\"]")
	private WebElement videotitle;

	@FindBy(xpath = "//*[@id=\"search\" and @placeholder=\"Search\"]")
	private WebElement searchBox;

	@FindBy(xpath = "//*[@id=\"contents\"]/ytd-video-renderer[1]")
	private WebElement firstVideo;

	// 3) create a constructor which is same as class name
	public YouTubeExplorePage(WebDriver driver) {
		this.Driver = driver;
		// Initilalize the drive and elements
		PageFactory.initElements(driver, this);
	}

	// 4) Initialize methods
	public WebDriver getDriverInstance() {
		return Driver;
	}

	public void viewExplore() {
		explorebtn.click();
	}

	public void viewSecondVideo() {
		secondVideo.click();
	}

	public String getvideoTitle() {
		String newvideotitle = videotitle.getAttribute("title");
		System.out.println("Video title is" + newvideotitle);
		return newvideotitle;
	}

	public String getpageTitle() {
		// String newpagetitle = pagetitle.getAttribute("title");
		String newpagetitle2 = Driver.getTitle();
		System.out.println("page title is" + newpagetitle2);
		return newpagetitle2;
	}

	public void searchVideo(String searchTxt) {
		searchBox.clear();
		searchBox.sendKeys(searchTxt);
		searchBox.sendKeys(Keys.RETURN);
	}

	public void clickFirstVideo() {
		firstVideo.click();
	}
}
