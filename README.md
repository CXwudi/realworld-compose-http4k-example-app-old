# ![RealWorld Example App](logo.png)

> ### Compose Multiplatform + http4k codebase containing real world examples (CRUD, auth, advanced patterns, etc) that adheres to the [RealWorld](https://github.com/gothinkster/realworld) spec and API.

This codebase was created to demonstrate a fully fledged fullstack application built with **[Compose Multiplatform](https://www.jetbrains.com/lp/compose-multiplatform/)** and **[http4k](https://www.http4k.org/)** including CRUD operations, authentication, routing, pagination, and more.

We've gone to great lengths to adhere to the community styleguides & best practices of **Compose Multiplatform** and **http4k**.

For more information on how to this works with other frontends/backends, head over to the [RealWorld](https://github.com/gothinkster/realworld) repo.

## How it works

The project is divided into 4 modules:

1. [`conduit-common`](./conduit-common) - the shared code between the client and the server.
2. [`conduit-frontend`](./conduit-frontend) - the KMP client source code.
3. [`conduit-backend`](./conduit-backend) - the server source code.
4. [`build-src`](./build-src) - shared Gradle build logic, including the [version catalog](./build-src/libs.versions.toml) that is used globally across the project.

## Getting started

To develop this project, you need a recent version of Android Studio and IntelliJ IDEA.

- Frontend: Open the `conduit-frontend` directory in Android Studio.
- Backend: Open the `conduit-backend` directory in IntelliJ IDEA.

For frontend development, you need to follow this [guide](https://www.jetbrains.com/help/kotlin-multiplatform-dev/multiplatform-setup.html#check-your-environment) to set up the Compose Multiplatform development environment.