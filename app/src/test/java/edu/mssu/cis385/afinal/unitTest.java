package edu.mssu.cis385.afinal;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;



public class unitTest {
    //Test AnItem Class
    private final String testName = "Name", testType =  "Type", testRarity = "Rarity", testVendor = "500",
            testIcon = "https://render.guildwars2.com/file/C05DCA62E84D18EF46D5357C217956C55BC3063E/61013.png";

    AnItem item = new AnItem(testName, testType, testRarity, testVendor, testIcon);

    final String name = "Name", nameFail = "NotName", type = "Type", rarity = "Rarity", vendor = "500",
            icon = "https://render.guildwars2.com/file/C05DCA62E84D18EF46D5357C217956C55BC3063E/61013.png";


    //Test Validity of Strings after being put into AnItem class
    @Test
    public void inputAnItem_Test() {

        Assert.assertEquals(item.getName(), name);
        Assert.assertEquals(item.getType(), type);
        Assert.assertEquals(item.getRarity(), rarity);
        Assert.assertEquals(item.getVendor(), vendor);
        Assert.assertEquals(item.getIcon(), icon);

        //failure case
        //Assert.assertEquals(item.getName(), nameFail);
    }


    //test vendorStringConverter, which turns a number into guild wars 2 currency as a string
    @Test
    public void vendorConverter_Test(){
        String testExpected = "5 Silver and 0 Copper";
        String testExpectedFail = "Fail";
        String testActual = AnItem.vendorStringConverter(Integer.parseInt(item.getVendor()));
        Assert.assertEquals(testExpected, testActual);


        //failure case
        //Assert.assertEquals(testExpectedFail, testActual);
    }


    //test overridden toString for AnItem to verify output
    @Test
    public void AnItem_toString_Test(){
        String testExpected = "AnItem{name='Name', type='Type', icon='https://render.guildwars2.com/file/C05DCA62E84D18EF46D5357C217956C55BC3063E/61013.png', rarity='Rarity', vendor=500}";
        String testExpectedFail = "Fail";
        Assert.assertEquals(testExpected, item.toString());

        //failure case
        //Assert.assertEquals(testExpectedFail, item.toString());



    }
}
