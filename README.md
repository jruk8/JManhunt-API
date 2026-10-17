# JManhunt-API

Public API for both free and premium JManhunt users. Companion
plugins read live matches, lobbies, players, compass targets,
and scores, run match actions, resolve `%jmanhunt_*%`
placeholders, toggle modifiers, and listen to match events.
The engine bundles these classes, so companions compile
against this artifact and ship nothing extra.

## Coordinates

```groovy
repositories {
    maven { url = 'https://jitpack.io' }
}

dependencies {
    compileOnly 'com.github.jruk8:JManhunt-API:<commit-sha>'
}
```

Pin a full commit SHA (the engine does the same). For local
development, `./gradlew publishToMavenLocal` from this
checkout and add `mavenLocal()` to the consumer. Companions
also need Paper API itself (compileOnly), which this
artifact does not bring.

Your plugin needs JManhunt present:

```yaml
depend:
  - JManhunt
```

## Usage

```java
Optional<JManhuntApi> api = JManhuntApi.lookup();
api.ifPresent(present -> present.registerAddon(this, new ApiVersion(1, 0, 0)));
```

The full reference (services, models, events, versioning,
thread rules, examples) lives on the engine docs page:
`docs/advanced/api.md` in the JManhunt repository.

## Versioning

Semantic: same major plus enough minor stays compatible
(see `ApiVersion.compatible`). Additive changes bump the
minor, breaking changes bump the major. JManhunt-Challenges
and friends pin a commit SHA per release line.

## License

Dual-licensed:

- Everyone: AGPL-3.0 (see LICENSE). Companions you
  distribute or host publicly must share their source.
- JMH-Premium owners: additionally MIT (see
  LICENSE-COMMERCIAL). Your purchase covers your team, for
  all API versions and any engine edition, and companions
  you release under it may stay closed source. Your receipt
  is your proof; there are no keys or activation. Refunding
  ends the exception for future work, and it does not
  transfer.

The license files are authoritative; this summary is not
legal advice.
