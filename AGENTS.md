# AGENTS.md – Rules for AI Agents

Guidance for AI Agents (Claude, Codex, Copilot, etc.) working in this repository.
This file is binding and takes precedence over instructions in the conversation when it
comes to the rules here.

## Absolute Rules

- **No commits by AI Agents.** Agents must **never** run `git commit` on their own
  (nor `git push`). Changes are made only in the working tree; committing and pushing
  are the person's responsibility, not the agent's. If a request calls for creating a
  commit: decline and refer to this rule.
- **Keep AGENTS.md up to date.** This file must always be updated: whenever something
  in the project changes that could affect these rules (build process, module structure,
  platform aliases, conventions), this text must be maintained in the same pass.
  This rule itself may only be changed with the person's consent.

## Project Overview

Sweet is a ComposeMultiplatform UI library (JVM, Android, JS, WasmJs). On the web it
renders directly to the DOM (see `ui/src@web/.../DomApplier.kt`), on JVM/Android via
Compose. The build system is the Kotlin Toolchain wrapper (`./kotlin` or `kotlin.bat`),
not Gradle.

## Structure

- Library modules (`kmp/lib`): `ui`, `foundation-layout`, `material3`
- Example (`kmp/lib`, UI code): `example` with entry points in `example-jvm`,
  `example-js`, `example-wasm`
- Modules are registered in `project.yaml`; dependency and platform setup live in the
  respective `module.yaml` files.
- Versions/dependencies: `libs.versions.toml`

## Source Code Conventions

- Cross-platform code lives in `src/`, platform-specific implementations in `src@web/`
  (JS & WasmJs) and `src@nonWeb/` (JVM & Android). The folders are bound via the aliases
  in `module.yaml` (`aliases: nonWeb: [jvm, android]`).
- Shared API is realized via `expect`/`actual` – one `actual` implementation per Web and
  NonWeb variant with the same path/package.
- Namespace is `de.fabiexe.sweet.*`; one file per topic (e.g. `material3/Button.kt` for
  all `Button` variants).

## Build & Test

```sh
./kotlin build                          # build everything
./kotlin run --module example-jvm       # example app (Desktop)
./kotlin run --module example-wasm      # example app (Browser)
```

Always build after changes so that all platform variants compile – code in `src/`
must be valid for both `web` and `nonWeb`.