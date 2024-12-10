Description:
A Spring Boot-based Journal Entry Application that securely manages user accounts and journal entries. It integrates MongoDB for data storage and Spring Security for authentication and role-based access control. The application demonstrates a robust, scalable, and secure system for personal and administrative data management.

Key Features:
User Management:

Register, update, and retrieve user accounts.
Passwords are securely encrypted for protection.
Role-based access for regular users and admins.
Authentication & Security:

Username and password-based login using Spring Security.
Encrypted password verification for secure access.
Restricted endpoints based on user roles (user/admin).
Journal Entry Management:

Create, update, delete, and fetch journal entries.
Entries are linked to individual users, ensuring secure data segregation.
Full CRUD operations protected by authentication.
Admin-Specific Features:

Admins can promote users, manage accounts, and access admin-only endpoints.
The first admin is manually configured, with additional admins managed programmatically.
Database:

Uses MongoDB Atlas for cloud-based, scalable NoSQL storage.
User and journal entry data stored as flexible JSON-like documents.
Tech Stack:
Backend: Spring Boot, Spring Security
Database: MongoDB Atlas (NoSQL)
Build Tool: Maven
This application is a practical demonstration of building secure, scalable Java applications using modern frameworks and tools. It is ideal for learning about the integration of Spring Boot, Spring Security, and MongoDB
