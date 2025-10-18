# TaskTrackerCLI

TaskTrackerCLI is a small command-line Java application for managing tasks (add, update, delete, mark, list). This project is built with Maven and the main class is `com.ojas.Main`.

## Build

Use Maven to build the project and produce a runnable JAR.

```bash
mvn clean package
```

The built artifact will be `target/TaskTrackerCLI-1.0-SNAPSHOT.jar` (artifactId and version come from `pom.xml`). The JAR manifest's `Main-Class` is set to `com.ojas.Main`.

## Run

Run commands by invoking the JAR produced by the Maven build. General form:

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar <command> [args...]
```

Examples:

- Add a task

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar add "Buy groceries"
```

- Update a task

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar update 1 "Buy groceries and cook dinner"
```

- Delete a task

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar delete 1
```

- Mark a task in-progress

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar mark-in-progress 1
```

- Mark a task done

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar mark-done 1
```

- List tasks (all or by status)

```bash
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar list
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar list todo
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar list in-progress
java -jar target/TaskTrackerCLI-1.0-SNAPSHOT.jar list done
```

## Data file

The application reads and writes tasks to `src/main/java/com/ojas/tasks.json` by default (see `TaskManager.PATH`). For development you can edit that file directly or change the code to use a different path (recommended for production deployments).

Task example (inside `tasks.json`):

```json
{
  "id": "3",
  "description": "learn javascript",
  "status": "in-progress",
  "createdAt": "Thu Oct 23 12:29:01 IST 2025",
  "updatedAt": "Tue Oct 21 05:44:01 IST 2025"
}
```

## Notes and suggestions

- The project stores `tasks.json` under the Java source tree. Consider moving it to `src/main/resources` or an external data directory so it isn't treated as source code.
- `TaskManager.convertToDate` handles the textual date format used in the existing `tasks.json`, but other ISO formats may be present; make sure parsing is robust if you add different timestamp formats.

## Contributing

Read the key files listed above to modify or extend behavior. If you change the artifactId/version or main class, update `README.md` accordingly.

---
Updated to reflect project structure and Maven build (Main class: `com.ojas.Main`).

### Project URL 

https://roadmap.sh/projects/task-tracker