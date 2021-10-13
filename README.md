**Code test hedvig**

For this test you will be given a number of events for a figurative insurance system. Your
task is to go over all events and generate a report. The choice of programming language and
the output format is up to you. During the interview, you will present your solution to us and
we can hopefully have some good discussions.
The events can be used to create, terminate and change the premium of the insurance.
The report should include the metrics defined below for every month between jan-dec 2020.

**Metrics:**

Number of contrafts: The number of contracts that started but not yet been terminated.
Expected gross written premium (EGWP): The expected sum of all premiums for the year.
Actual gross written premium (AGWP): The accumulated premium that should have been
paid in every month.

**1. Run application** 
- mvn spring-boot:run

or

**build and run jar**
- mvn package
- java -jar target/task-0.0.1-SNAPSHOT.jar

**2. Send requests**
- Test case 1  

curl --request POST \
   --url http://localhost:8080/api/reports \
   --header 'Content-Type: application/json' \
   --data '[ {
   "type": "ContractCreatedEvent",
   "value": 100,
   "contactId": 1,
   "date": "2020-01-01"
   },
   {
   "type": "ContractCreatedEvent",
   "value": 100,
   "contactId": 2,
   "date": "2020-02-01"
   },
   {
   "type": "ContractTerminatedEvent",
   "contactId": 1,
   "date": "2020-03-30"
   },
   {
   "type": "ContractTerminatedEvent",
   "contactId": 2,
   "date": "2020-04-30"
   }
   ]
   '
- Test case 2

curl --request POST \
   --url http://localhost:8080/api/reports \
   --header 'Content-Type: application/json' \
   --data '[ {
   "type": "ContractCreatedEvent",
   "value": 100,
   "contactId": 1,
   "date": "2020-01-01"
   },
   {
   "type": "PriceIncreasedEvent",
   "value": 100,
   "contactId": 1,
   "date": "2020-02-01"
   },
   {
   "type": "PriceDecreasedEvent",
   "contactId": 1,
   "value": 100,
   "date": "2020-03-01"
   },
   {
   "type": "ContractTerminatedEvent",
   "contactId": 1,
   "date": "2020-04-30"
   }
   ]
   '
