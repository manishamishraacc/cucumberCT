Feature: Track registration event in clever tap

@Desktop
Scenario: Verify clever tap for registration event.
    Given navigate to khelPlay rummy web site
    And Perform registration and create new profile
    And Using below credential login to clevertap
       | manisha.mishra@thekhelgroup.com | Password24!|
    Then Mentioned data should be reflected in CleverTap
    | Event | Domain Name | Alias Name | Ad Campagin | Currenr Url | Client Type | OS | RegDevice | Device | Device Type | Source | Browser | Browser Version | Registration Via | OS Version | Gender | Reg City | Player Profile | Cash Current Balance | CT Source |
    |Registration | test.khelplayrummy.com | test.khelplayrummy.com | NA | https://test.khelplayrummy.com/after-registration | Full-Web | Windows | PC | PC | PC | Direct | Chrome | 5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36 | Default | Windows 10 | NA | NA | MINI | 0 | Web | 
    
@HybridApp
Scenario: HybridApp registration event tracking
 Given Hybrid App is launched
 And Register and create new user
 And Using below credential login to clevertap
      | manisha.mishra@thekhelgroup.com | Password24!|
 Then verify below data should be shown in cleverTap
   | Event | Domain Name | Alias Name | Ad Campaign | Client Type | OS | Reg Device | Device | Device Type | Source | Registration Via | OS Version | Player Profile | Cash Current Balance | CT Source | Screen Width | Device Model | Carrier | wifi | App Version | Brand | State | Screen Height | CT App Version |
   | Registration | test.khelplayrummy.com | test.khelplayrummy.com | NA | Android-Full | Android | Mobile | Android | Mobile | Direct | Default | 10 | MINI | 0 | Mobile | 1080 | CPH1969 | Jio 4G | yes | 10.8.9.4 | OPPO | NA | 2208 | 10.8.9.4 |  