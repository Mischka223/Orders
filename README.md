# Orders
Run instruction:
1. Instal java 11 or later;
2. Instal maven 3;
3. Clone this repository
In cmd:
4. Navigate to project directory
5. Run mvn spring-boot:run

GET http://localhost:8080/expenses
![Alt text](https://github.com/Mischka223/Orders/blob/master/screenshot/get_expenses.png "GET")

POST http://localhost:8080/expenses
![Alt text](https://github.com/Mischka223/Orders/blob/master/screenshot/post_expenses.png "POST")

DELETE http://localhost:8080/expenses?date=2020-01-02
![Alt text](https://github.com/Mischka223/Orders/blob/master/screenshot/delete_expenses.png "DELETE")

GET http://localhost:8080/total?base=USD
![Alt text](https://github.com/Mischka223/Orders/blob/master/screenshot/get_total.png "GET")
