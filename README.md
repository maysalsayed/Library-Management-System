# Library-Management-System
A console-based Java application designed to manage a small library's operations. This system provides core functionalities such as managing books, members, and transactions (borrowing and returning books).\
✨ Features\
-Book Management

- Add, remove, update, and search books

- Validate ISBN format (ISBN-13 standard)

-Member Management

- Add, remove, update, and list members

- Validate email and phone format

-Borrow/Return System

- Borrow books by member ID and book ID

- Return books using either transaction ID or book ID

- Track and list all transactions

🧱 Project Structure\
The main class is Main.java, which handles the user interface and interacts with the following assumed classes:

Book.java: Handles book data and operations

Member.java: Manages member records

Transaction.java: Records and manages borrow/return events

📂 How to Use

1- Run the Program\
Compile and run Main.java using any Java compiler or IDE.

2-Main Menu Options:

=== Library Management System ===
1. Manage Books
2. Manage Members
3. Borrow/Return Books
4. Exit
   Input Requirements:

ISBN Format: Must start with 978 or 979 followed by 10 digits (total of 13 digits).

Email Format: Valid email format (e.g., name@example.com)

Phone Number Format: Must start with 077, 078, or 079 and contain 10 digits.

🧪 Sample Scenarios

- Add a new book with valid ISBN, title, and author

- Register a new library member with valid email and phone

- Borrow a book by entering correct member ID and book ID

- Return a book using transaction ID or book ID

- List all books, members, or transaction history

✅ Requirements

--->Java 11 or higher

-->Console environment

🚀 Future Improvements

- Add persistent storage (e.g., files or database)

- Improve user interface with GUI (e.g., JavaFX or Swing)

- Track due dates and late returns

- Add login system for librarians and members

👨‍💻 Author\
Mays Alsayed