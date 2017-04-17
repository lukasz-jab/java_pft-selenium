package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }


    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
        //if (creation){
          //  new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //}else {
          //  Assert.assertFalse(isElementPresent(By.name("new_group")));
        //}

    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }

    public void selectContact() {
        click(By.xpath("//input[@name='selected[]']"));
    }

    public void editContact() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitEditConact() {
        click(By.xpath("//input[@name='update']"));
    }


    public void createContact(ContactData contact) {
        fillContactForm(new ContactData("Grzegorz", "Brzęczyszczykiewicz", "Poland",null),true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    public List<ContactData> getContactList() {
        List<WebElement>elementContactData=wd.findElements(By.xpath("//table[@id='maintable']//tr//td[2]"));
        List<ContactData>contactData=new ArrayList<>();
        int i=0;
        for(WebElement element:elementContactData){
            String lastName=wd.findElement(By.xpath("//table[@id='maintable']//tr//td[2]")).getText();
            String name = wd.findElement(By.xpath("//table[@id='maintable']//tr//td[3]")).getText();
            String country = wd.findElement(By.xpath("//table[@id='maintable']//tr//td[4]")).getText();
            contactData.add(i,new ContactData(name,lastName,country,null));
            System.out.println(contactData.get(i).getFirstName()+" "+contactData.get(i).getLastName()+" "+contactData.get(i).getAddress());
            i++;
        }
        return contactData;
    }
}
