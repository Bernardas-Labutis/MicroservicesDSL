# MicroservicesDSL
Bachelor thesis project of a Domain Specific Language that generates Spring Boot projects

Prerequisites:
* Windows 10
* Java SE 11
* Node v14.15.4
* Yarn 1.22.10

1. Download java and install one of distributions of jdk 11, installation guide https://www.youtube.com/watch?v=1ZbHHLobt8A
1. Download node https://nodejs.org/download/release/v14.15.4/node-v14.15.4-x64.msi
1. Install yarn https://classic.yarnpkg.com/lang/en/docs/install/#windows-stable
1. Go to the root folder, click on start_projects.bat, your browser should open a window at http://localhost:3000, where the frontend app is run. The backend is run on http://localhost:9099.
1. Combine blockly blocks, click "Generate and download projects" on the bottom left of the page. 
IMPORTANT: ports 3000, 9099 and 4000 are reserved.
SUGGESTION: to see if the project is working, you should try to create a configuration mentioned in the thesis:
![Alt text](example_block_configuration.png?raw=true "Title")
3. To run the generated projects, unzip the downloaded file, run start_projects.bat in the "microservice-architecture" folder.
NOTE: there is a bug with the mvnw https://issues.apache.org/jira/browse/MWRAPPER-48, so if you getting while running the script, try to move the whole generated project to another location and run the script again.
5. After the microservice projects starts, you can check if they successfully registered in the service registry by opening url http://localhost:PORT where PORT is the value provided in service registry port block.
6. Navigate to microservice-architecture/test and run run_test.bat to test if microservice layers connected successfully.
