package org.example.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.actions.LeaveAction;
import org.example.actions.CommonAction;
import org.example.actions.LoginAction;
import org.example.actions.NavigationAction;

import static org.assertj.core.api.Assertions.assertThat;

public class LeaveStepDefinitions {
    NavigationAction navigateAction;
    LoginAction loginAction;
    CommonAction commonAction;
    LeaveAction leaveAction;

    @Given("user is in dashboard")
    public void userIsInDashboard() {
        navigateAction.toOrangeHRMLoginPage();
        loginAction.loginWithUsernameAndPassword("Admin", "admin123");
        assertThat(commonAction.getUrl()).isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/dashboard/index");
    }

    @When("user click leave section in sidebar")
    public void userClickLeaveSectionInSidebar() {
        navigateAction.toOrangeHRMLeavePage();
    }

    @Then("user expect redirection to leave section")
    public void userExpectRedirectionToClaimSection() {
        assertThat(commonAction.getUrl()).isEqualTo("https://opensource-demo.orangehrmlive.com/web/index.php/leave/viewLeaveList");
    }

    @Given("user is in leave page wants to search for leaves")
    public void userIsInLeavePageWantsToSearchForLeaves() {
        userIsInDashboard();
        userClickLeaveSectionInSidebar();
    }


    @And("the user enters From Date = {string}")
    public void theUserEntersFromDateToDate(String fromDate) {
        leaveAction.selectFromDate(fromDate);
    }

    @And("the user selects leave status {string}")
    public void theUserSelectsLeaveStatus(String leaveStatus) {
        leaveAction.selectLeaveStatus(leaveStatus);
    }

    @And("the user selects leave type {string}")
    public void theUserSelectsLeaveType(String leaveType) {
        leaveAction.selectLeaveType(leaveType);
    }

    @And("the user enters {string} as the Employee Name")
    public void theUserEntersAsTheEmployeeName(String empName) {
        leaveAction.selectEmployeeName(empName);    
    }

    @When("the user clicks the Search button")
    public void theUserClicksTheSearchButton() {
        leaveAction.clickSearch();
    }

    @Then("user expect to see the leave requests for the employee")
    public void userExpectToSeeTheLeaveRequests() {
        leaveAction.checkLeave();
    }
}