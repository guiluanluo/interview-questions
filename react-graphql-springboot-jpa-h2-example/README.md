# Example using React (with Apollo) to make GraphQL call to Spring Boot Data JPA and H2 

## Instructions to build and start applications
1. `cd spring-data-jpa-graphql-ucp-h2`
2. Modify `src/main/resources/application.properties` to set values for `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password`
3. Run `mvn clean install`
4. Run  `mvn spring-boot:run   OR   java -jar target/spring-data-jpa-graphql-h2-0.0.1-SNAPSHOT.jar`
5. (In a separate terminal/console) `cd react-graphql`
6. Run `yarn add @apollo/client graphql` (this is only necessary once for the project), please install yarn if needs: brew install yarn
7. Run `npm run build`
8. Run `npm start` 

#### A browser window should open to http://localhost:3000/ which is a React app that will use Apollo to make a GraphQL query against a Spring Boot service running on localhost:8080 which in turn uses JPA to query an Oracle database via a connection obtained from UCP.



## Tech/Framework 
1. GraphQL:
	a) GraphQL Tutorial: https://www.tutorialspoint.com/graphql/index.htm
	b) Introduction to GraphQL: https://graphql.org/learn/
2. React Tutorla:
	a) React Getting Started: https://legacy.reactjs.org/docs/getting-started.html
	b) React Tutorial: https://www.w3schools.com/REACT/DEFAULT.ASP
3. Apollo Client(React): a state management library that simplifies managing remote and local data with GraphQL
	a) Introduciton to Apollo Client: https://www.apollographql.com/docs/react/
	b) Get started with Apollo Client: https://www.apollographql.com/docs/react/get-started/
4. Spring Boot:
	a) Spring Boot Tutorial(GeeksforGeeks): https://www.geeksforgeeks.org/spring-boot/
	b) Spring Boot Tutorial(Javapoint): https://www.javatpoint.com/spring-boot-tutorial
	c) Spring tutoirals: https://spring.io/guides#tutorials; https://spring.io/guides/tutorials/react-and-spring-data-rest
5. SQL Toturial: https://www.w3schools.com/sql/sql_intro.asp
