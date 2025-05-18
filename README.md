# Oshop
A SpringBoot application which runs a RESTful API for managing a simple E-Commerce Website. <br>
Shop Owner and Customer class interacts with products. <br> A Shop Owner can add a product and a Customer can add product to a cart and order.

## Technologies
![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.1-green?logo=springboot&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.6%2B-blue?logo=apachemaven&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-Containerized--db-blue?logo=docker&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15%2B-blue?logo=postgresql&logoColor=white)

## Prerequisites
- Java 17+
- Docker
- **Optional** - API Client (Postman, Yaak, Insominia etc.). You can always use curl
- **Optional** - Database Management Tool (Dbeaver, Navicat, DataGrip etc.) I think some IDE's come inbuilt with them
- **Optional** - An IDE such as IntelliJ, VSCode, Netbeans
    
## Installation (Will work on both powershell and bash/zsh terminals)
1. Clone the repository:
   ```bash
   git clone https://github.com/ZeleOeO/Oshop.git
   ```
2. Navigate to the project directory:
   ```bash
   cd Oshop
   ```   
3. Open docker if not already running (spring-boot-docker-compose is installed so no need for docker-compose)
4. Run with
   ```bash
   ./mvnw spring-boot:run
   ```

## Usage
- Database
  - To connect and check the database, it is hosted on `jdbc:postgresql://localhost:5432/ishop_db`
  - username: `zele`
  - password: `password`
- Application
  - port : `8080`
- API Endpoints
  - Unfortunately did not know about swagger-ui on time and I'm not coming back to this

## Running Tests
Unfortunately no configured tests in this repo...

## Contributing
Contributions are more than welcome as I've abandoned this project (I don't care about it lk)

### Steps to Contribute
1. Open an issue first so I can like keep track, but if that's too much stress that's fine too
2. Fork the Repository
3. Clone your fork
4. Create a new branch
   ```bash
   git checkout -b your-branch-name
   ```
5. Make your change
6. Commit your change, please use [Conventional Commits](https://gist.github.com/qoomon/5dfcdf8eec66a051ecd85625518cfd13) if you can, I didn't really use it here tbh
7. Push your change
8. Make a pull request and reference your issue <br>
   Please stick to conventional methods of programming java and springboot applications, don't mess up my already spaghetti code

## License
[MIT](https://choosealicense.com/licenses/mit/)
