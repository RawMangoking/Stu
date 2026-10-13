## 8. Jenkins Declarative Pipeline

The project uses a root-level `Jenkinsfile` to automate the build, test, packaging, and artifact publishing workflow using Jenkins Declarative Pipeline.

### Jenkins Environment and Configuration

- Jenkins was accessed locally at `http://localhost:8080`.
- A Pipeline job named `Student-Starter-CI` was created.
- The job was configured to retrieve the `Jenkinsfile` from the GitHub repository: `https://github.com/RawMangoking/Stu.git`.
- The branch specifier was configured as `*/main`.
- The Jenkins controller ran inside a Linux-based Docker container.
- Maven was configured through Jenkins' tool configuration, allowing the pipeline to execute Maven commands in the Jenkins build environment.
- The pipeline uses `agent any` to run on an available Jenkins executor.

### Pipeline Stages

1. **Checkout:** Retrieves the project from GitHub.
2. **Build and Test:** Runs `mvn clean test` from the repository root.
3. **JUnit Results:** Publishes the Surefire XML test reports.
4. **Package:** Runs `mvn package` after the build-and-test stage succeeds.
5. **Artifact Archiving:** Archives the packaged JAR after a successful pipeline.

### Test Report and Artifact Paths

JUnit reports:

```text
course-registration-app/target/surefire-reports/TEST-*.xml
```

Packaged artifacts:

```text
course-registration-app/target/*.jar
```

### Jenkins Execution and Verification

The Jenkins pipeline was executed successfully. During configuration, an initial build remained queued because no Windows agent with the `windows` label was configured. The pipeline was subsequently updated to use an available executor compatible with the Linux-based Jenkins environment.

A later build initially failed because Maven was not available in the Jenkins execution environment. Maven was then configured through Jenkins' tool configuration, and the pipeline was executed again.

The successful Jenkins build, **build #3**, demonstrated the following:

- The repository was retrieved from GitHub.
- The Maven build and test workflow completed successfully.
- Jenkins displayed the latest test results with no reported failures.
- The packaged artifact `course-registration-app-1.0-SNAPSHOT.jar` was archived successfully. The Jenkins job page displayed the artifact with a size of approximately 4.60 KiB.

The successful build page and console output provide evidence of the pipeline execution. The detailed JUnit report should also be retained as submission evidence to show the exact test count, failures, and errors.

### Failure Handling

The pipeline runs the packaging stage only after the build-and-test stage succeeds. Artifact archiving is performed only after the pipeline completes successfully. JUnit reports are published from the module's Surefire report directory.

## 11. Evidence and Limitations

The project was verified through local Maven builds, JUnit tests, Git operations, Jenkins pipeline execution, and Minikube deployment operations.

### Evidence to Include

- Git feature branch and commit history, including the integration into `main`.
- Successful `mvn clean test` and `mvn package` output.
- JUnit test report showing the total number of tests, failures, and errors.
- Jenkins build #3 status and console output.
- Jenkins test results and archived JAR artifact.
- Kubernetes Deployment and Service manifests.
- Genuine command output showing the initial two replicas, scaling to three replicas, Pod deletion, and replacement.
- Browser evidence showing successful access to the Nginx welcome page.

### Known Limitations

- The Jenkins pipeline builds and tests the Course Registration Maven project; the Kubernetes deployment task separately deploys the specified `nginx:1.27` image.
- The Jenkins controller runs in a Linux Docker container. The pipeline therefore uses Linux-compatible execution steps rather than Windows `bat` commands.
- The exact test count and failure/error totals should be taken from the detailed Jenkins or Surefire test report.
- Generated Maven `target/` directories should not be committed to Git.
- All submitted screenshots and command outputs must be genuine and must correspond to operations actually performed.

Docker was used as the Minikube driver. A custom Docker image for the Java Course Registration application was not required by the Kubernetes task.
