Feature: Deposit initate event

@Desktop
Scenario: Verify initiate deposit for web portal
Given KhelPlay rummy portal 
And create new user and inititate deposit
And Using below data login to clever Tap
   | manisha.mishra@thekhelgroup.com | Password24! |
Then Deposit initiate should be recorded in clevertap
  | Event | Domain Name | Amount | Current URL | Browser | State | City | Country | OS | Client Type | Device Type | Alias Name | OS Version | Email Verified | Phone Verified | CT Source |
  | Deposit Initiate | test.khelplayrummy.com | 100 | https://test.khelplayrummy.com/before-payment | Chrome| NA | NA | NA | Windows | Full-Web | PC | test.khelplayrummy.com | Windows 10 | No | No | Web |
