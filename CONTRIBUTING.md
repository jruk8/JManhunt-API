# Contributing to JManhunt-API

Thank you for your interest in contributing to JManhunt-API! This
document covers the build, local development, versioning, and pull
request guidelines.

## Build and Test

Requires JDK 25 (Temurin):

```sh
./gradlew build
```

While iterating, run one suite with `./gradlew test --tests
"com.jruk8.jmanhunt.api.ApiVersionTest"`.

## Local Development

Companions consume the API through JitPack
(`com.github.jruk8:JManhunt-API:<tag>`). For local cross-repo work,
`./gradlew publishToMavenLocal` from this checkout and add
`mavenLocal()` to the consumer.

## Versioning and Releases

Versions come from git tags (`v1.0.0`) via axion-release: pushing a
tag versions the jar and fires the release workflow, which uploads it
to GitHub releases. Untagged builds resolve to a snapshot and still
build.

The `ApiVersion` contract is semantic: same major plus enough minor
stays compatible (see `ApiVersion.compatible`). Additive changes bump
the minor, breaking changes bump the major — and the tag follows the
same bump.

## Coding Conventions

- **Java 25** is the target. Use modern language features (records,
  switch expressions, pattern matching) where appropriate.
- **Checkstyle** is enforced. Run `./gradlew check` before submitting.
  Key rules: 4-space indentation, 120-column lines, no star imports,
  braces always.
- **Null safety:** unknown ids read as empty (empty optionals, empty
  collections), never null and never thrown. Keep it that way.
- **Thread rules:** every API call runs on the server main thread
  unless its Javadoc says otherwise.

## Testing Expectations

- **Logic-based tests** are required for new behavior. Use **JUnit 5**
  (plus Mockito where Bukkit types intrude).
- Tests must not require a running Minecraft server, a database, or
  network access.
- Place tests in `src/test/java/` mirroring the main source package
  structure.

## Commit Message Style

Use [Conventional Commits](https://www.conventionalcommits.org/):

```
<type>[optional scope]: <description>

[optional body]
```

Types: `feat`, `fix`, `docs`, `refactor`, `test`, `chore`..

## Pull Request Guidelines

1. **Branch** from `main` with a descriptive name (e.g.
   `feat/compass-service`). Never commit directly on `main`.
2. **Commit** using the style above. Squash fixup commits before
   submitting.
3. **Build**: ensure `./gradlew build` passes with zero warnings.
4. **Describe**: write a clear PR description explaining what and why.
5. **Review**: respond to feedback promptly. CI must pass before merge.

## Code of Conduct

Be respectful and constructive in all interactions. Harassment or
discriminatory behavior of any kind will not be tolerated.
