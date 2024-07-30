# Jobber: Your Ultimate Job Portal 🚀

Welcome to Jobber, the place where your next career move starts! This Spring Boot application is designed to help job seekers find the perfect job and employers find the perfect candidate. Dive in and explore the features I've packed into this app!

## Objectives 🎯

Here's what we've set out to achieve with Jobber:

1. **User Registration & Authentication**: 
   - Secure user registration with password validation.
   - Automatic role assignment based on email domain (e.g., `@admin.com` for admins).
   - Redirect users post-login based on their role.

2. **User Roles & Permissions**:
   - Create and manage user roles (e.g., ADMIN, USER).
   - Assign permissions based on roles to ensure secure access control.

3. **Personalized User Experience**:
   - Display a personalized greeting on the user's profile page.
   - Track login activity and show the last login time.
   - Redirect users based on their role (e.g., admins to `/admin`, users to `/userprofile`).

4. **Job Postings**:
   - Integrate with external APIs to populate job postings.
   - Web scrape job listings from Indeed (educational purposes only!).

5. **Enhanced User Dashboard**:
   - Show an overview of recent activities (e.g., job applications, saved jobs).
   - Provide personalized job recommendations based on user profiles.

6. **Security Enhancements**:
   - Implement two-factor authentication.
   - Encourage periodic password changes.

7. **User Engagement**:
   - Prompt users to complete their profiles.
   - Notify users of unread messages and application status updates.
   - Display relevant blog posts and articles.

## How to Get Started 🛠️

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-username/jobber.git
   cd jobber

2. **Set Up the Database**:
   - Ensure MySQL is installed and running.
   - Create a database named `jobber`.
  
3. **Configure the Application**:
   - Update `application.yaml` with you MySQL database credentials:
     ```yaml
     spring:
      datasource:
        url: jdbc:mysql://localhost:3306/jobber
        username: your-username
        password: your-password
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
     
  4. **Run the Application**:
     ```bash
     ./mvnw spring-boot:run

  5. **Explore Jobber**:
     - Open your browser and navigate to `http://localhost:8080`.
     - Register a new account and start exploring!

## Project Structure 📂 ##
  Here's a quick look at the project's structure:
  
    jobber/
    ├── src/
    │   ├── main/
    │   │   ├── java/
    │   │   │   └── com/
    │   │   │       └── example/
    │   │   │           └── jobber/
    │   │   ├── resources/
    │   │   │   └── templates/
    │   └── test/
    ├── .gitignore
    ├── pom.xml
    └── README.md
    
## Contributing 🤝 ##
I welcome contributions! Whether you're fixing bugs, adding new features, or improving documentation, your help is appreciated. Just fork the repository, create a branch, and submit a pull request.
   
