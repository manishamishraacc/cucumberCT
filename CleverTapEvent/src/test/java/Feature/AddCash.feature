Feature: Add cash 
	I want to use this template for my feature file

@Ignore @Desktop 
Scenario: Sucessfully add cash to user account and verfiy in clever Tap
	Given Login to khelPlay rummy with username and password 
		| manishamishraacc@gmail.com | Password24! |
	And Click on add cash, Enter amount and Proceed 
	And complete transaction successfully 
	And Clevertap login with username and password 
		| manisha.mishra@thekhelgroup.com | Password24! |
	And Enter email as search crieria for add cash 
		| manishamishraacc@gmail.com |
	Then Webbrowser and user related data should be recorded in clevertap as mentioned below 
	| Event | AdCampaign | Amount | BonusAmount | BonusCode | BonusType | Browser | BrowserVersion | cashCurrentBalance | ClientType | CurrentUrl | DepositStatus | DeviceType | Device | DomainName | email | FirstDepositeDate | Gender | Source | isBonusApplied | OS | PaymentSubType | PaymentType | phone | identify | playerID | plyaerProfile | RegCity | RegDate | RegDevice | RegIP | RegState | State | username | OSVersion | Gateway | lastLoginDate | aliasName | CTSource | DOB |
		| Deposit | NA | 100 | 500 | BONUS500 | BONUS | Chrome | browserVersion | 87169 | Full-Web | https://test.khelplayrummy.com/after-payment-success | SUCCESS | PC | PC | test.khelplayrummy.com| manisha.mishra@thekhelgroup.com | 25/05/2020 04:24:36 pm | M | Direct | Yes | Windows | Axis Bank |  Netbanking | +917898260783 | 4707370 | 4707370 | FULL | mumbai | 25/05/2020 04:04:50 pm | PC| 115.248.108.130 | Maharashtra | Maharashtra | manisha.mishra0 | Windows 10 | CASHFREE | xyz | test.khelplayrummy.com | Web | 09/02/1919 12:00:00 am |

@HybridApp
@Ignore
Scenario: Mobile add cash verification 
	Given launch app and Login with username and password 
		| manishamishraacc@gmail.com | Password24! |
	Given Set width and resolution of testing device
	    | xResolution | yResolution |
	    | 1080 | 2208 |
	And Perform add cash action 
	And Clevertap login with username and password 
		| manisha.mishra@thekhelgroup.com | Password24! |
	And Enter search criteria add cash 
	Then App add cash data should be reflected

@Ignore	
Scenario: cleverTap Event 
	Given Clever tap event tracking 
	And Clevertap login with username and password 
		| manisha.mishra@thekhelgroup.com | Password24! |
	And Enter email as search crieria for add cash 
		|manisha.mishra@thekhelgroup.com |
	Then Webbrowser and user related data should be recorded in clevertap as mentioned below 
		| Event | AdCampaign | Amount | BonusAmount | BonusCode | BonusType | Browser | BrowserVersion | cashCurrentBalance | ClientType | CurrentUrl | DepositStatus | DeviceType | Device | DomainName | email | FirstDepositeDate | Gender | Source | isBonusApplied | OS | PaymentSubType | PaymentType | phone | identify | playerID | plyaerProfile | RegCity | RegDate | RegDevice | RegIP | RegState | State | username | OSVersion | Gateway | lastLoginDate | aliasName | CTSource | DOB |
		| Deposit | NA | 100 | 500 | BONUS500 | BONUS | Chrome | browserVersion | 87169 | Full-Web | https://test.khelplayrummy.com/after-payment-success | SUCCESS | PC | PC | test.khelplayrummy.com| manisha.mishra@thekhelgroup.com | 25/05/2020 04:24:36 pm | M | Direct | Yes | Windows | Axis Bank |  Netbanking | +917898260783 | 4707370 | 4707370 | FULL | mumbai | 25/05/2020 04:04:50 pm | PC| 115.248.108.130 | Maharashtra | Maharashtra | manisha.mishra0 | Windows 10 | CASHFREE | xyz | test.khelplayrummy.com | Web | 09/02/1919 12:00:00 am |