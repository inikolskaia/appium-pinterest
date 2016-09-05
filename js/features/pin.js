"use strict";

var wd = require("wd");
var chai = require("chai");
var chaiAsPromised = require("chai-as-promised");
var expect = require("chai").expect;
var user = 'mad2tyhgdd@fduv.de';
var pass = 'dsdfdr56g';
var firstname = 'Max Mustermann';
var age = '28';


chai.config.includeStack = true;
chai.use(chaiAsPromised);
chai.should();
chaiAsPromised.transferPromiseness = wd.transferPromiseness;

describe("pinterest", function () {
    this.timeout(300000);
    var browser = wd.promiseChainRemote("http://appium.testobject.com/wd/hub");
    var allPassed = true;

    before(function () {
        var desired = {
            "appium-version": "1.5.3",
            platformName: "android",
            platformVersion: "5.1",
            "app-package": "com.pinterest",
            //"app-activity": "",
            'testobject_api_key': '319C2953432D4B5F977997844F96BF15',
            'testobject_app_id': '1',
            'testobject_device': 'LG_Nexus_5X_real'

        };
        return browser
            .init(desired)
            .setImplicitWaitTimeout(5000);
    });

    after(function () {
        return browser.quit();
    });

    afterEach(function () {
        allPassed = allPassed && this.currentTest.state === 'passed';
    });

    it("Registration by email", function () {
        return browser
            .waitForElementById("com.pinterest:id/email_address",4000)
            .sendKeys(user)
            .waitForElementById("com.pinterest:id/continue_email_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/password",4000)
            // IMPORTANT - use clear to remove any existing usernames before sending keys.
            // If not cleared the pre-filled username will disappear and then be typed before appending the new username.
            .clear()
            .sendKeys(pass)
            .waitForElementById("com.pinterest:id/next_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/full_name",4000)
            .sendKeys(firstname)
            .waitForElementById("com.pinterest:id/next_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/age_et",4000)
            .sendKeys(age)
            .waitForElementById("com.pinterest:id/next_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/male_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/done_bt",4000)
            .click()
            .waitForElementById("com.pinterest:id/nux_header_title",4000)
            .text().should.eventually.include("What are you interested in?");
    });

    it("Pick that interesting", function () {
        return browser
            .waitForElementByXPath("//*[@text='Design']",4000)
            .click()
            .waitForElementByXPath("//*[@text='Art']",4000)
            .click()
            .waitForElementByXPath("//*[@text='Photography']",4000)
            .click()
            .waitForElementByXPath("//*[@text='Travel']",4000)
            .click()
            .waitForElementByXPath("//*[@text='Technology']",4000)
            .click()
            .waitForElementById("com.pinterest:id/nux_interests_picker_continue_btn",4000)
            .click();
    });

    it("Create a board to pin things to", function () {
        return browser
            .waitForElementById("com.pinterest:id/profile_menu_view",4000)
            .click()
            .waitForElementById("com.pinterest:id/board_add_iv",4000)
            .click()
            .waitForElementById("com.pinterest:id/board_name_et",4000)
            .sendKeys("ASDF")
            .waitForElementById("com.pinterest:id/create_btn",4000)
            .click()
            .sleep(4000);
    });

    it("Click the Search menu, and search for something", function () {
        return browser
            .deviceKeyEvent(4)
            .waitForElementById("com.pinterest:id/menu_explore",5000)
            .click()
            .waitForElementById("com.pinterest:id/search_tv",4000)
            .click()
            .waitForElementById("com.pinterest:id/query_input",4000)
            .sendKeys("loadstorm infographics importance")
            .deviceKeyEvent(66);
    });

    it("Like and Pin something", function () {
        return browser
            .waitForElementByXPath("//android.widget.AdapterView[1]/android.view.View[1]",4000)
            .click()
            .waitForElementById("com.pinterest:id/menu_like",8000)
            .click()
            .waitForElementById("com.pinterest:id/save_pinit_bt",8000)
            .click()
            .waitForElementById("com.pinterest:id/tooltip_complete_button",4000)
            .click()
            .waitForElementById("com.pinterest:id/create_board_btn",4000)
            .click()
            .waitForElementById("com.pinterest:id/board_name_et",4000)
            .sendKeys("NEW TEST")
            .waitForElementById("com.pinterest:id/create_btn",4000)
            .click()
            .sleep(4000);
    });

    it("Navigate back to Profile", function () {
        return browser
            .deviceKeyEvent(4)
            .deviceKeyEvent(4)
            .deviceKeyEvent(4)
            .waitForElementById("com.pinterest:id/profile_menu_view",4000)
            .click()
            .sleep(3000);
    });

    it("Should fail to find Waldo", function () {
        return browser
            .waitForElementByXPath("//*[@content-desc='Waldo']",1000).should.eventually.be.rejected;
    });

});
