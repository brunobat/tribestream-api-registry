/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.tomitribe.tribestream.registryng.functionaltests.pages;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.support.FindBy;

import java.io.FileOutputStream;
import java.io.IOException;

import static org.jboss.arquillian.graphene.Graphene.guardAjax;
import static org.jboss.arquillian.graphene.Graphene.waitGui;

public class ApplicationDetailsPage {

    @Drone
    private PhantomJSDriver driver;

    @FindBy(css = "div[data-value='swagger.info.title'] div.text")
    private WebElement titleField;

    @FindBy(css = "div[data-value='swagger.info.version'] div.text")
    private WebElement versionField;

    @FindBy(css = "div[x-ng-click='create()']")
    private WebElement createApplicationButton;

    @FindBy(css = "div[x-ng-click='save()']")
    private WebElement saveButton;

    @FindBy(css = "div[data-value='swagger.info.description']")
    private WebElement descriptionField;

    @FindBy(css = "div.tribe-field-actions-body div[x-ng-click='confirm()']")
    private WebElement confirmButton;


    @FindBy(css = "a[href='.']")
    private WebElement homeLink;

    public void enterApplicationName(final String newTitle) throws IOException {
        titleField.click();

        waitGui();

        WebElement textField = titleField.findElement(By.cssSelector("input[type='text']"));
        // No Ctrl+A because on Mac it's probably COMMAND+A
        textField.sendKeys(Keys.END);
        while (textField.getText().length() > 0) {
            textField.sendKeys(Keys.BACK_SPACE);
        }
        textField.sendKeys(newTitle, "\n");
    }


    public void enterVersion(final String newVersion) throws IOException {
        versionField.click();
        waitGui();
        WebElement textField = versionField.findElement(By.cssSelector("input[type='text']"));
        textField.sendKeys(newVersion, "\n");
    }

    public void clickCreateApplicationButton() throws IOException {
        createApplicationButton.click();

        waitGui();
    }

    public void clickHomeButton() {
        guardAjax(homeLink).click();
    }

    public void createScreenshot(final String filename) throws IOException {
        try (FileOutputStream fout = new FileOutputStream(filename)) {
            fout.write(driver.getScreenshotAs(OutputType.BYTES));
        }

    }

}
