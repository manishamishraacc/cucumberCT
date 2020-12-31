Feature: Login events tracking 
          Verify login events are getting tracked in clever tap

@Desktop
@Ignore
Scenario: Track login event for web drowser
	Given Perform registration and logout after updating profile info.
	And Login with newly registred user.
	And Login to cleverTap with below credentials
		| manisha.mishra@thekhelgroup.com | Password24! |
	And Search login events for created user.
	Then Below mentioned data should be reflected in cleverTap
	   | Action Date | Event | Domain Name | adCampagin | CurrenrURL | Gender | RegState | Email verified | phone verified | player Profile | Promo current Balance | ClientType | OS | Reg Device | device | Device type | Soure | OS version | Login Via | Browser | Browser Version | cash Balance | Ct Source |
	   | Tue, October 20, 2020  | Login | test.khelplayrummy.com | NA | https://test.khelplayrummy.com/ | M | Maharashtra | No | No | FULL | 10000 | Full-Web | Windows | PC |  PC | PC | Direct | Windows 10 | Default | Chrome | 5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.75 Safari/537.36 | 0 | Web |
	
@HybridApp
Scenario: Hybrid App login event tracking 
	Given Hybrid app login event with mentioned username and password
		| manishamishraacc@gmail.com | Password24! |
	And Login to cleverTap with below credentials 
		| manisha.mishra@thekhelgroup.com | Password24! |
	And Perform search for below mentioned user
		| manishamishraacc@gmail.com |
	Then For Hybrid app, mentioned data should be reflected in clever Tap
	| Action Date | Event | Domain Name | Ad campaign | email | Gender | Reg City | Player Profile | Promo Current Balance | Client Type | First Deposit Date | OS | Reg Date| Device Type | Source | OS Version | Login Via | CT Source | Carrier | Brand | City | App Version | wifi | Screen Height | State | Screen Width | Device Model | CT App Version |
	| Tue, October 20, 2020 | Login | test.khelplayrummy.com | NA | manishamishraacc@gmail.com | M | mumbai | FULL | 10000 | Android-Full | 16/03/2020 05:22:20 am | Android | 26/02/2020 04:52:05 am | MOBILE | Direct | 10 | Default | Mobile | Jio 4G | OPPO | mumbai | 10.8.9.5 | Yes | 2208 | Maharashtra | 1080 | CPH1969 | 10.8.9.5 |