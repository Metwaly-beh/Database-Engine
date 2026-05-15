# Database-Engine



This project involves developing the core architectural layers of a Database Management System (DBMS), focusing on persistent storage, page management, and data manipulation. The system is structured into three distinct levels: DBMS, Table, and Page.
Project Overview

  Persistent Storage: Implements a storage engine using a FileManager to serialize and deserialize tables and their data pages to the hard disk.

  Core Functionalities: Supports table creation, record insertion (appended to the end of the table), and multiple selection methods.

  Data Access: Includes "Select All" queries, conditional filtering based on column values, and direct record access via pointers (specific page and record index).

  Performance Monitoring: A "Trace" system logs every operation, documenting the specific pages accessed and execution times to analyze system behavior.

  Simplified Data Handling: To focus on underlying storage logic, all data is currently treated as strings, and queries default to star selects (SELECT *).

## Testing & Validation

The implementation was rigorously validated using JUnit via a comprehensive test suite. The testing process was extensive, with the full suite running for 3.5 hours to ensure the stability, accuracy, and performance of the DBMS components.

Please see the attached PNG for the successful JUnit test execution results.
<img width="1111" height="127" alt="image" src="https://github.com/user-attachments/assets/7bd1762f-8837-4559-beca-29786888d849" />


More Features to be added soon

