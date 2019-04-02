Feature: Check the booking ID of Luxe Studio

  Background: User is on Luxe Studio page
    Given User is on home page of Luxe Studio

  Scenario: To complete a booking in Website
    When USer navigates to Appointments Section
    And Selects a day from the calendar
    And Selects an hour
    And Proceed towards the next Step
    Then User needs to select the need
    And Proceed towards the next Step
    Then User needs to select any additional treatments if required
    And Proceed towards the next Step
    And An Assistant is also chosen by the user
    And Proceed towards the next Step
    Then User needs to either Login or Checkout as a Guest
    And User can apply any Coupon Code also, if applicable
    Then User needs to Finalize the booking
    And Complete the booking
