# SharedHelperLibrary
A library containing common utility and helper classes used across 
multiple microservices within the UpdateCenter system.

## Table of contents
1. [Application Startup](#application-startup)
   1. [Prerequisites](#prerequisites)
   2. [Application cloning](#application-cloning)
   3. [Containers startup](#containers-startup)
   4. [Additional information](#additional-information)
2. [Changelog](#changelog)

## Application Startup

### Prerequisites
Before you begin, make sure you have the following tools installed:
- Git
- Maven (3.9.7 +)

### Application cloning
If you do not have cloned repository yet, use following command:
  ```
  git clone git@github.com:JankowskiW/SoftwareManagementService.git
  ```

### Building application
To build the application using Maven, run the following command:
```
mvn clean test install
```

### Adding jar file as a dependency to the pom file
```xml
<dependencies>
    <dependency>
        <groupId>com.wj.updatecenter</groupId>
        <artifactId>shared-helper-library</artifactId>
        <version>${shared-helper-library.version}</version>
    </dependency>
</dependencies>
```

### Additional information
Work in progress...

## Changelog

### Version 1.0.0
- Created LoggerHelper:
  - provides frequently used log messages in REST controllers
- Created SortOrderConverter:
  - converts a 'sort by' fields string into a list of `Sort.Order` objects
- Created PaginationHelper:
  - converts page number, page size and 'sort by' fields string into a `Pageable` object
- Create CommonSpecificationBuilder:
  - provides a method that build `Specification` object for JPA using 
    field name and string value

### Version 1.0.1
- Changed CommonSpecificationBuilder:
  - added additional methods to handle more complex `Specification` cases