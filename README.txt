Authors
================================
- Name: Hanlin Zou
  Email: hzou7@bu.edu
  BU ID: U96634471

- Name: Xiansong Li
  Email: xiansong@bu.edu
  BU ID: U55619815

- Name: Xiaohan Zou
  Email: zxh@bu.edu
  BU ID: U18269004

Environments
================================
Tested on Windows Intelli J. After running Main, Bank GUI will show up.


Class Description
================================

Config files:

1. config.csv: Stores the exchange rate between three currencies and interest rate of loans.

-----------------------------------------------------

Database CSV files:

1. checking_accounts.csv: Stores all data of checking_accounts created by customers. Including checking account id, owner id, and balance of three currencies.
2. customers.csv: Stores all data of all customers. Including user id, login name, login password, and loans
3. loans.csv: Stores all data of all loans created by manager. Including loan id, loan name, loan interest rate, and the value that customer can get.
4. logs.csv: Stores all transactions of all customers and manager. Including type, transaction id, user id, action time, action content
5. managers: Stores all data of all manager accounts. Including user id, login name, login password.
6. saving_accounts.csv: Stores all data of saving_accounts created by customers. Including saving account id, owner id, and balance of three currencies.
7. stock_accounts.csv: Stores all data of stock accounts created by customers. Including stock account id, owner id, balance of US Dollar, and realized and unrealized profit.
8. stocks.csv: Stores all data of stocks created by managers. Including stock id, name, and current price

------------------------------------------------------

Java Files:

1. Account: An abstract class of accounts in this project. Abstract of saving, checking and stock account.
2. BankObject: An abstract class of objects that provided by bank, such as loans and stocks.
3. BankTimer: It helps access current time.
4. BasicAccount: An abstract class of saving and checking accounts.
5. CheckingAccount: The class of checking accounts. Includes all features that a checking account can do. Includes but no limited: deposit, withdraw, transfer, currency exchange.
6. CNY: One instance of currency. Stands for Chinese Yuan.
7. Currency: An abstract class of currencies. Including exchange rate between all currencies and other features.
8. Customer: The class that stands for customer of this project. Including all features that a customer can do in a bank ATM and accounts variables.
9. DatabaseFile: The class create a database file.
10. DatabaseFileProxy:  use Proxy Pattern to handle the real DatabaseFile object for avoiding creating File instance duplicate.
11. FileInterface: Interface of DatabaseFile IO.
12. FileIo: A static utility class for handling file IO.
13. HKD: One instance of currency. Stands for Hong Kong Dollar.
14. Loan: Includes all features of a loan, inherits from BankObject class.
15. Log: Serves as transaction object, contains transaction ID, transaction time, transaction details, etc.
16. Main: main class to initiate the program.
17. Manager: The class that stands for manager of this project. Including all features that a manager can do in a bank.
18. SavingAccount: The class of saving accounts. Includes all features that a saving account can do. Includes but no limited: deposit, withdraw, transfer, currency exchange.
19. Stock: Includes all features of a stock, inherits from BankObject class.
20. StockAccount: The class of stock accounts. Includes all features that a saving account can do. Includes but no limited: buy stock, sell stock and check profit.
21. TimeObserver: Observer Pattern: Objects that require time (for example, objects which handle interest) should implement TimeObserver interface to receive notice when time changes.
22. USD: One instance of currency. Stands for US Dollar.
23. User: An abstract class of cutomer and manager, should be extended bu Customer and Manager class.

------------------------------------------------------

Database Java Files:

1. AccountDao: AccountDao class use Data Access Object Pattern to provide a data accessing API for saving and loading account data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of account related DAO class.
2. CheckingAccountDao: CheckingAccountDao class use Data Access Object Pattern to provide a data accessing API for saving and loading checking account data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of checking account related DAO class.
3. ConfigDao: ConfigDao class use Data Access Object Pattern to provide a data accessing API for saving and loading Config data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of Config related DAO class.
4. CustomerDao: CustomerDao class use Data Access Object Pattern to provide a data accessing API for saving and loading Customer data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of Customer related DAO class.
5. Dao: Abstract class of all other Dao classes.
6. LoanDao: LoanDao class use Data Access Object Pattern to provide a data accessing API for saving and loading Loan data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of Loan related DAO class.
7. LogDao: LogDao class use Data Access Object Pattern to provide a data accessing API for saving and loading Log data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of Log related DAO class.
8. ManagerDao: LogDao class use Data Access Object Pattern to provide a data accessing API for saving and loading Log data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of Log related DAO class.
9. SavingAccountDao: SavingAccountDao class use Data Access Object Pattern to provide a data accessing API for saving and loading saving account data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of saving account related DAO class.
10. StockAccountDao: StockAccountDao class use Data Access Object Pattern to provide a data accessing API for saving and loading stock account data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of stock account related DAO class.
11. StockDao: StockDao class use Data Access Object Pattern to provide a data accessing API for saving and loading stock data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of stock related DAO class.
12. UserDao: UserDao class use Data Access Object Pattern to provide a data accessing API for saving and loading user data. It also uses Singleton Pattern to ensure it has only one instance. It's the super class of all instances of user related DAO class.

-------------------------------------------------------

GUI Files:

1. Frame: Frame of the GUI. Size, color buttons etc.
2. GUIAddLoan: GUI of process of manager adding a loan into database.
3. GUIAddStock: GUI of process of manager adding a stock into database.
4. GUIATM_MainPage: GUI of Main page when project initially launches.
5. GUIBalance: GUI of balance page showing balance of all accounts of a customer.
6. GUICheckCustomer: GUI of process of manager checking a specific customer's information.
7. GUICheckingAccount: GUI of checking account with its features.
8. GUICheckTransaction: GUI of process of manager checking a specific customer's transaction history.
9. GUICloseAccount: GUI of process of customer close his/her account.
10. GUICreateAccount: GUI of process of customer create a new account.
11. GUIEditLoan: GUI of process of manager editting an existed loan.
12. GUIEditStock: GUI of process of manager ediiting an existed stock.
13. GUIInputUtil: GUI of pop-up windows that asks for users' inputs.
14. GUILoan: GUI of loans.
15. GUILoginPage: GUI of login page for user to login as customer or staff.
16. GUIMainMenu: GUI of Main menu that displays after successful login.
17. GUIManagerMenu: GUI of Main menu that displays after manager successful login.
18. GUIOpenAccount: GUI of process of customer creating an account.
19. GUISavingAccount: GUI of saving account with its features.
20. GUIsetup: Interface file for GUI labels, buttons and panels.
21. GUIStock: GUI of stock account pages.
22. GUITransfer: GUI of transfer between saving and checking
