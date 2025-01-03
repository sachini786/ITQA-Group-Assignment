package org.example.actions;


import net.serenitybdd.core.annotations.findby.By;
import net.serenitybdd.core.steps.UIInteractionSteps;
import net.thucydides.core.annotations.Step;


public class LeaveAction extends UIInteractionSteps {

    private String getMonthName(int month) {
        String[] months = {
                "January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"
        };
        return months[month - 1];
    }
    @Step
    public void selectFromDate(String fromDate) {
        String[] dateParts = fromDate.split("-");
        String year = dateParts[0];
        String day = String.valueOf(Integer.parseInt(dateParts[1]));
        String month = dateParts[2];

        $(By.xpath("//label[contains(text(), 'From Date')]/following::input[1]")).click();

        while (!$(By.xpath("//div[contains(@class, 'oxd-calendar-selector-year-selected')]/p"))
                .getText().equals(year)) {
            $(By.xpath("//button[contains(@class, 'oxd-icon-button') and contains(@class, 'bi-chevron-right')]")).click();
        }

        while (!$(By.xpath("//div[contains(@class, 'oxd-calendar-selector-month-selected')]/p"))
                .getText().equals(getMonthName(Integer.parseInt(month)))) {
            $(By.xpath("//button[contains(@class, 'oxd-icon-button') and contains(@class, 'bi-chevron-right')]")).click();
        }


        $(By.xpath(String.format("//div[@class='oxd-calendar-date-wrapper']//div[text()='%s']", day))).click();
    }

    @Step
    public  void selectLeaveStatus(String leaveStatus){
        $(By.xpath("//label[contains(text(), 'Show Leave with Status')]/following::div[@class='oxd-select-text-input']")).click();
        $(By.xpath(String.format("//div[@role='listbox']//div[@role='option']//span[text()='%s']", leaveStatus))).click();
    }

    @Step
    public void selectLeaveType(String leaveType){
        $(By.xpath("//label[contains(text(), 'Leave Type')]/following::div[@class='oxd-select-text-input']")).click();
        $(By.xpath(String.format("//div[@role='listbox']//div[@role='option']//span[text()='%s']", leaveType))).click();
    }

    @Step
    public  void  selectEmployeeName(String empName){
        $(By.xpath("//label[contains(text(), 'Employee Name')]/following::input[1]")).type(empName);
        $(By.xpath(String.format("//div[@role='listbox']//div[@role='option']//span[text()='%s']", empName))).click();
    }

    @Step
    public void clickSearch(){
        $(By.xpath("//button[contains(., 'Search')]")).click();
    }

    @Step
    public void checkLeave(){
        assert $(By.xpath("//span[contains(text(), 'Records Found')]")).getText().contains("Records Found");
    }
}
